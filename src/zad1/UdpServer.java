package zad1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
public class UdpServer {

    public static void main(String args[]) {
        System.out.println("UDP SERVER");
        DatagramSocket socket = null;
        int portNum = 9500;

        try{
            socket = new DatagramSocket(portNum);
            byte[] receiveBuffer = new byte[1024];

            while(true){
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivePacket);

                String msg = new String(receivePacket.getData());
                System.out.println("NEW msg: " + msg);

                byte[] sendBuffer = "PING REPLY udp".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());

                System.out.println("Sending PING REPLY...");
                socket.send(sendPacket);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (socket !=null) socket.close();
        }
    }

}
