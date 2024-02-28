import socket

serverIP = "127.0.0.1"
serverPort = 9500
msg_bytes = "PYTHON PING REQUEST"

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(bytes(msg_bytes, 'cp1250'), (serverIP, serverPort))


buff, address = client.recvfrom(1024)
print("python udp server received msg: ", buff.decode('utf-8'))