
import grpc
from concurrent import futures
import time
import sys
import random

sys.path.append('./generated')

import subscribe_pb2 as pb
import subscribe_pb2_grpc as pbg


celcius_sign="°C"

currencies_sign=["EUR","USD","GBP"]

currencies ={pb.EUR:4.33, pb.USD:4.02, pb.GBP:5.04}


def get_weather(acceptable, city):
    reply = pb.WeatherReply()
    degrees = random.randint(-35,35)
    if acceptable<=degrees:
        reply.is_weather_ok = True
        reply.response_message = f"Temperature in {city}: {degrees}{celcius_sign} is greater than {acceptable}{celcius_sign}"
    else:
        reply.is_weather_ok = False
        reply.response_message = f"Temperature in {city}: {degrees}{celcius_sign} is lesser than {acceptable}{celcius_sign}"
    return reply

def get_currency(acceptable, curr_type):
    reply = pb.CurrencyReply()
    currency = round(currencies[curr_type] + random.uniform(-1,1),2)
    if currency>=acceptable:
        reply.is_currency_ok = True
    else:
        reply.is_currency_ok = False
    reply.current_exchange = currency
    reply.response_message = f"Currency exchange to {currencies_sign[curr_type]}"
    return reply

def get_discounts(shop_type):
    reply = pb.DiscountsReply()
    num_of_discounts = random.randint(2,4)
    reply.how_many_discounts = num_of_discounts
    for i in range(num_of_discounts):
        disc = pb.Discount()
        disc.description =f"Product {random.randint(1,100)}"
        disc.discount_value = random.randint(10,50)
        disc.current_price = round(random.uniform(100,900),2)
        reply.discounts.append(disc)
    return reply

class SubscriberServicer(pbg.SubscriberServicer):
    def GetCityWeather(self, request, context):
        print("[REQUEST]: GetCityWeatherRequest")
        for i in range(5):
            yield get_weather(request.acceptable_degrees, request.city)
            time.sleep(3)
    
    def GetCurrencyExchange(self, request, context):
        print("[REQUEST]: GetCurrencyRequest")
        for i in range(5):
            yield get_currency(request.acceptable_amount,request.currency_type)
            time.sleep(3)
        
    
    def GetDiscounts(self, request, context):
        print("[REQUEST]: GetDiscountsRequest")
        for i in range(5):
            yield get_discounts(request.shop)
            time.sleep(3)



def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    pbg.add_SubscriberServicer_to_server(
        SubscriberServicer(), server)
    server.add_insecure_port("localhost:50051")
    server.start()
    server.wait_for_termination()


if __name__ == "__main__":
    serve()
