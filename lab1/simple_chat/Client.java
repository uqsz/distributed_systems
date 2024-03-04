import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws IOException {
        String asciiArt = "\n _   _      _ _        __        __         _     _ _ \n" +
                "| | | | ___| | | ___   \\ \\      / /__  _ __| | __| | |\n" +
                "| |_| |/ _ \\ | |/ _ \\   \\ \\ /\\ / / _ \\| '__| |/ _` | |\n" +
                "|  _  |  __/ | | (_) |   \\ V  V / (_) | |  | | (_| |_|\n" +
                "|_| |_|\\___|_|_|\\___/     \\_/\\_/ \\___/|_|  |_|\\__,_(_)";

        System.out.println("---CHATCLIENT");
        String hostName = "localhost";
        int serverPortNumber = 12345;
        int multicastPortNumber = 9500;

        Socket tcpSocket = null;
        DatagramSocket udpSocket = null;
        MulticastSocket mulSocket = null;

        ReceiverTCP tcpRec = null;
        Thread tcpRecThread = null;

        ReceiverUDP udpRec = null;
        Thread udpRecThread = null;

        ReceiverUDP mulRec = null;
        Thread mulRecThread = null;

        DatagramPacket sendPacket = null;

        byte[] sendBuffer = null;

        try {

            InetAddress ServerAddress = InetAddress.getByName("localhost");
            InetAddress multicastGroup = InetAddress.getByName("239.0.0.1");

            tcpSocket = new Socket(hostName, serverPortNumber);
            udpSocket = new DatagramSocket(tcpSocket.getLocalPort());
            mulSocket = new MulticastSocket(multicastPortNumber);

            mulSocket.joinGroup(multicastGroup);

            PrintWriter out = new PrintWriter(tcpSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));

            tcpRec = new ReceiverTCP(tcpSocket, in);
            tcpRecThread = new Thread(tcpRec);
            tcpRecThread.start();

            udpRec = new ReceiverUDP(udpSocket);
            udpRecThread = new Thread(udpRec);
            udpRecThread.start();

            mulRec = new ReceiverUDP(mulSocket);
            mulRecThread = new Thread(mulRec);
            mulRecThread.start();

            sendBuffer = asciiArt.getBytes();

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print(">>>: ");
                    String command = scanner.nextLine();
                    if (!command.isEmpty()) {
                        if (command.charAt(0) == 'U') {
                            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ServerAddress,
                                    serverPortNumber);
                            udpSocket.send(sendPacket);

                        } else if (command.charAt(0) == 'M') {
                            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, multicastGroup,
                                    multicastPortNumber);
                            udpSocket.send(sendPacket);

                        } else
                            out.println(command);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tcpSocket != null)
                tcpSocket.close();
            if (udpSocket != null)
                udpSocket.close();
            if (mulSocket != null)
                mulSocket.close();
            if (tcpRecThread.isAlive())
                tcpRecThread.interrupt();
            if (udpRecThread.isAlive())
                udpRecThread.interrupt();
            if (mulRecThread.isAlive())
                mulRecThread.interrupt();

        }
    }

    public static class ReceiverTCP implements Runnable {
        private Socket socket;
        private BufferedReader in;

        public ReceiverTCP(Socket socket, BufferedReader in) {
            this.socket = socket;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String response;
                while (true) {
                    if (!socket.isClosed() && (response = in.readLine()) != null)
                        System.out.print("\n" + response + "\n" + ">>>: ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ReceiverUDP<T extends DatagramSocket> implements Runnable {
        private T socket;

        public ReceiverUDP(T socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] receiveBuffer = new byte[1024];
            try {
                while (true) {
                    Arrays.fill(receiveBuffer, (byte) 0);
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    socket.receive(receivePacket);

                    String msg = new String(receivePacket.getData());
                    System.out.print("\n" + msg.trim() + "\n" + ">>>: ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
