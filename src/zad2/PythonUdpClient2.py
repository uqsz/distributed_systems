import socket

serverIP = "127.0.0.1"
serverPort = 9500
msg = "żółta gęś"

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(msg.encode('utf-8'), (serverIP, serverPort))
