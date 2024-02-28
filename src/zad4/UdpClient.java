package zad4;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpClient {
    public static void main(String args[]){
        System.out.println("UDP CLIENT\n");
        DatagramSocket socket = null;
        int portNum = 9600;

        try {
            socket = new DatagramSocket();

            InetAddress ServerAddress = InetAddress.getByName("localhost");
            byte[] sendBuffer = "JAVA PING REQUEST".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ServerAddress, 9500);

            byte[] receiveBuffer = new byte[1024];
            Arrays.fill(receiveBuffer, (byte)0);
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            System.out.println("Sending PING REQUEST...");
            socket.send(sendPacket);

            System.out.println("Waiting for PING REPLY...");
            socket.receive(receivePacket);

            String msg = new String(receivePacket.getData());
            System.out.println("NEW msg: " + msg);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
