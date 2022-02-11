import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    private final int PORT = 6000;
    private final String HOST = "localhost";
    private String message;
    private byte[] messageByte = new byte[1024];
    private WeatherPojo weather;

    @Override
    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket(PORT);
            System.out.println("Servidor Conectado");

            while(true) {
                DatagramPacket dataR = new DatagramPacket(messageByte, messageByte.length);
                ds.receive(dataR);

                String str = new String(dataR.getData()).trim();
                System.out.println(str);
                weather = new Weather(str).getWeather();
                message = weather.toString();
                DatagramPacket data = new DatagramPacket(message.getBytes(), message.getBytes().length, dataR.getAddress(), dataR.getPort());
                ds.send(data);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
