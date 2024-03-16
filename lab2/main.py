from fastapi import FastAPI, Request, Form, HTTPException
from fastapi.responses import HTMLResponse
import requests

app = FastAPI()

# HTML z formularzem
html_form = """
<!DOCTYPE html>
<html>
    <head>
        <title>API Service</title>
    </head>
    <body>
        <h1>Check word definition</h1> <!-- Dodany nagłówek -->
        <form action="/result" method="post">
            <label for="word">Word:</label>
            <input type="text" id="word" name="word"><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>"""

# wyświetlanie formularza


@app.get("/", response_class=HTMLResponse)
async def get_form():
    return html_form

# przetwarzanie danych z formularza


@app.post("/result")
async def result(request: Request, word: str = Form(...)):
    if len(word.split()) != 1:
        return HTMLResponse(content="<h2>Error: Word must contain exactly one word!</h2>", status_code=400)
    if not word.isalpha():
        return HTMLResponse(content="<h2>Error: Word must contain only English alphabet characters!</h2>", status_code=400)

    # URL requests
    dictionary_api_url = f"https://api.dictionaryapi.dev/api/v2/entries/en/{word}"
    detect_language_api_url = "https://ws.detectlanguage.com/0.2/detect"
    detect_language_api_url_languages = "https://ws.detectlanguage.com/0.2/languages"
    detect_language_headers = {
        "Authorization": "Bearer 41bfa8566fa216f921075191b4c133f7"}
    detect_language_payload = {"q": word}
    try:
        # DetectLanguageAPI
        detect_language_response = requests.post(
            detect_language_api_url, headers=detect_language_headers, data=detect_language_payload)
        detect_language_response.raise_for_status()
        detect_language_data = detect_language_response.json()

        # sprawdzanie, czy słowo jest angielskie
        is_english = any(
            d['language'] == 'en' for d in detect_language_data['data']['detections'])

        if is_english:

            # DictionaryAPI

            dictionary_api_response = requests.get(dictionary_api_url)
            dictionary_api_response.raise_for_status()
            dictionary_api_data = dictionary_api_response.json()

            # definicje słowa
            definitions_html = ""
            for i, meanings in enumerate(dictionary_api_data[0]['meanings']):
                definitions_html += f"<h4>Part of speech: {meanings['partOfSpeech']}</h4>"
                for index, definition in enumerate(dictionary_api_data[0]['meanings'][i]['definitions'], start=1):
                    example_temp, definition_temp = "", ""
                    if 'example' in definition:
                        example_temp = f" - > \"{definition['example']}\""

                    if 'definition' in definition:
                        definition_temp = f"{index}. {definition['definition']}"
                    definitions_html += f"<p>{definition_temp}{example_temp}</p>"

            # fonetyka
            phonetics_html = ""
            for index, phonetic in enumerate(dictionary_api_data[0]['phonetics'], start=1):
                phonetics_html += f"<p>{index}. "
                if 'text' in phonetic and phonetic['text'] != "":
                    phonetics_html += f"{phonetic['text']}</p>"
                else:
                    phonetics_html += "</p>"
                if 'audio' in phonetic and phonetic['audio'] != "":
                    phonetics_html += f"""
                        <audio controls>
                            <source src="{phonetic['audio']}" type="audio/mpeg">
                            Your browser does not support the audio element.
                        </audio>
                        """
            # strona html z odpowiedzią
            response_html = f"""
            <!DOCTYPE html>
            <html>
                <head>
                    <title>API Service Results</title>
                </head>
                <body>
                    <h2>Results for word "{word}":</h2>
                    <h3>Definitions:</h3>
                    {definitions_html}
                    <h3>Phonetics:</h3>
                    {phonetics_html}
                </body>
            </html>
            """
            return HTMLResponse(content=response_html)

        else:
            # zapytanie o słownik skrótow języków
            languages = requests.get(detect_language_api_url_languages)
            languages.raise_for_status()
            languages = languages.json()
            languages_dict = {language["code"]: language["name"]
                              for language in languages}
            languages_html = ""
            for index, detection in enumerate(detect_language_data['data']['detections'], start=1):
                languages_html += f"<p>{index}. {languages_dict[detection['language']]} with confidence: {detection['confidence']}</p>"

            response_html = f"""
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>API Service Results</title>
                    </head>
                    <body>
                        <h2>Results for word "{word}":</h2>
                        <h3>Language Error:</h3>
                        
                        <p>This word is most likely not an English word :(</p>
                        <h4>Most accurate possibilities:</h4>
                        {languages_html}
                    </body>
                </html>
                """
            return HTMLResponse(content=response_html)

    except requests.exceptions.RequestException as e:
        response_html = f"""
            <!DOCTYPE html>
            <html>
                <head>
                    <title>API Service Error</title>
                </head>
                <body>
                    <h2>Error</h2>
                    <p>An error occurred while processing your request:</p>
                    <p>{str(e)}</p>
                </body>
            </html>
            """
        return HTMLResponse(content=response_html, status_code=500)
