import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int serverPort = 5454;
        Car car = new Car(49);
        try {
            //socket.setSoTimeout(10000);
            while(true) {
                try {
                    Socket socket = new Socket("localhost", serverPort);
                    ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());;

                    toServer.writeObject(car);
                    toServer.close();
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
