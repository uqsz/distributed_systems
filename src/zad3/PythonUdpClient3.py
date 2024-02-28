import socket

serverIP = "127.0.0.1"
serverPort = 9500
msg_bytes = (300).to_bytes(4, byteorder='little')

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(msg_bytes, (serverIP, serverPort))


buff, address = client.recvfrom(1024)
received = int.from_bytes(buff)
print("python udp server received msg: ", received)