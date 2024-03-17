import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("---CHAT-SERVER---");
        int portNumber = 12345;
        ServerSocket serverSocket = null;
        DatagramSocket udpSocket = null;

        Map<Integer, Socket> socketsMap = new HashMap<>();

        int id = 0;

        try {
            serverSocket = new ServerSocket(portNumber);
            udpSocket = new DatagramSocket(portNumber);

            ClientUDP udpClient = new ClientUDP(udpSocket, socketsMap);
            Thread udpClientThread = new Thread(udpClient);
            udpClientThread.start();

            while (true) {
                Socket tcpClientSocket = serverSocket.accept();
                System.out.print("New client connected! ");

                socketsMap.put(id, tcpClientSocket);
                ClientTCP tcpClient = new ClientTCP(id, socketsMap);

                Thread clientThread = new Thread(tcpClient);
                clientThread.start();

                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                serverSocket.close();
            if (udpSocket != null)
                udpSocket.close();

        }
    }

    public static class ClientTCP implements Runnable {
        private int id;
        private Map<Integer, Socket> socketsMap;

        public ClientTCP(int id, Map<Integer, Socket> socketsMap) {
            this.id = id;
            this.socketsMap = socketsMap;
        }

        @Override
        public void run() {
            System.out.println(
                    "Running new thread for client with ID: " + this.id + " with port: "
                            + socketsMap.get(this.id).getPort());

            try {
                PrintWriter out = new PrintWriter(socketsMap.get(this.id).getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socketsMap.get(this.id).getInputStream()));

                String msg;

                out.println("[SERVER] Your ID -> " + this.id);

                while ((msg = in.readLine()) != null) {
                    System.out.println("[TCP] Message received from ID: " + this.id + " -> \"" + msg + "\"");

                    out.println("[SERVER] Your message has been successfully sent!");

                    for (Socket socket : socketsMap.values()) {
                        if (socket != socketsMap.get(this.id)) {
                            PrintWriter out_rest = new PrintWriter(socket.getOutputStream(), true);
                            out_rest.println("[SERVER/ID->" + this.id + "] " + msg);
                        }
                    }
                }

            } catch (IOException e) {
                // if ()
            } finally {
                if (socketsMap.get(this.id) != null) {
                    try {
                        socketsMap.get(this.id).close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    socketsMap.remove(this.id);
                }
            }
        }
    }

    public static class ClientUDP implements Runnable {
        private DatagramSocket socket;
        private Map<Integer, Socket> socketsMap;

        public ClientUDP(DatagramSocket socket, Map<Integer, Socket> socketsMap) {
            this.socket = socket;
            this.socketsMap = socketsMap;
        }

        @Override
        public void run() {
            String msg = null;
            try {
                byte[] receiveBuffer = new byte[1024];
                while (true) {
                    Arrays.fill(receiveBuffer, (byte) 0);
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    socket.receive(receivePacket);

                    msg = new String(receivePacket.getData());

                    System.out.println("[UDP] Message received from " + receivePacket.getAddress() + ":"
                            + receivePacket.getPort() + " -> \"" + msg + "\"");

                    byte[] sendBuffer = ("[SERVER UDP] " + msg).getBytes();

                    for (Socket client : socketsMap.values()) {
                        if (!client.getInetAddress().equals(receivePacket.getAddress())
                                || client.getPort() != receivePacket.getPort()) {
                            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                                    client.getLocalAddress(), client.getPort());
                            socket.send(sendPacket);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
