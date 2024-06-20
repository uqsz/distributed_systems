# Distributed systems

This repository contains implementations and resources related to distributed systems. A distributed system is a collection of independent computers that communicate with each other over a network and work together to achieve a common goal.

## Contents

1. **[Sockets](#Sockets)**
2. **[Simple API](#getting-started)**
3. **[Ray](#getting-started)**
4. **[Middleware](#getting-started)**
5. **[RabbitMQ](#getting-started)**
6. **[Zookeeper](#getting-started)**

### Sockets

A chat application where clients connect to the server via the TCP and UDP protocol. The server accepts messages from each client and broadcasts them to the others along with the client's ID/nickname. The server is multithreaded, ensuring each client connection has its own thread for proper concurrency handling.

### Simple API

A basic web service leveraging open REST APIs to execute a complex functionality. Simple English dictionary.
