import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class TCPClient {

    private Socket socket;

    public TCPClient(String serverIP, int serverPort) {

        try {
            this.socket = new Socket(InetAddress.getByName(serverIP), serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String data) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                                                new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(data);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String receive() {

        String data = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(
                                                new InputStreamReader(socket.getInputStream()));
            data = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public synchronized long getRandomSec() {

        Random r = new Random();
        int Low = 0;
        int High = 10000;
        return r.nextInt(High - Low) + Low;
    }
}
