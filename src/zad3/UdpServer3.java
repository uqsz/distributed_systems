package zad3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class UdpServer3 {
    public static void main(String args[]) {
        System.out.println("UDP SERVER JAVA");
        DatagramSocket socket = null;
        int portNum = 9500;

        try{
            socket = new DatagramSocket(portNum);
            byte[] receiveBuffer = new byte[4];

            while(true) {
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                ByteBuffer byteBuffer = ByteBuffer.wrap(receiveBuffer).order(ByteOrder.LITTLE_ENDIAN);
                int receivedInt = byteBuffer.getInt();
                System.out.println("received msg: " + receivedInt);

                receivedInt+=1;

                byte[] sendBuffer = ByteBuffer.allocate(4).putInt(receivedInt).array();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());

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