import java.net.InetSocketAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread{
    private final int PORT = 6000;
    private final String HOST = "localhost";
    private String message;
    private InetSocketAddress addr = new InetSocketAddress(HOST, PORT);
    private byte[] messageByte = new byte[1024];

    public Client(String message){
        this.message = message;
        try {
            this.start();
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket();

            DatagramPacket dataSend = new DatagramPacket(message.getBytes(), message.getBytes().length, addr);
            ds.send(dataSend);

            DatagramPacket dataRecive = new DatagramPacket(messageByte, messageByte.length);
            ds.receive(dataRecive);

            String sever = new String(dataRecive.getData());
            System.out.println(sever);

            ds.close();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
