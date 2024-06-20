# Distributed systems

This repository contains implementations and resources related to distributed systems. A distributed system is a collection of independent computers that communicate with each other over a network and work together to achieve a common goal.

## Contents

1. **[Sockets](#Sockets)**
2. **[API](#API)**
3. **[Ray](#Ray)**
4. **[Middleware](#Middleware)**
5. **[RabbitMQ](#RabbitMQ)**
6. **[Zookeeper](#Zookeeper)**

### Sockets

A chat application where clients connect to the server via the TCP and UDP protocol. The server accepts messages from each client and broadcasts them to the others along with the client's ID/nickname. The server is multithreaded, ensuring each client connection has its own thread for proper concurrency handling.

### API

A basic web service leveraging open REST APIs to execute a complex functionality. Simple English dictionary.

### Ray

Notebook with lab exercises about Actor Model with ray framework.

### Middleware

Customer subscription service using gRPC and testing dynamic invocation and dispatch in Ice

### RabbitMQ

System for operating the orthopedic department at the hospital.

### Zookeeper

Zookeeper's server event handling in simple programme.
