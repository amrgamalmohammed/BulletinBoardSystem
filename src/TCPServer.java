import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable{

    private int serverPort, maxItrs;
    private ServerSocket serverSocket;
    private boolean isStopped;
    private Thread runningThread;

    private TCPServer(int serverPort, int maxItrs) {

        this.serverPort = serverPort;
        this.maxItrs = maxItrs;
    }

    public static void main(String[] args) {

        //parsing arguments
        int serverPort = Integer.parseInt(args[0]);
        int maxItrs = Integer.parseInt(args[1]);

        TCPServer tcpServer = new TCPServer(serverPort, maxItrs);

        System.out.println("Starting server ...");

        tcpServer.run();
    }

    @Override
    public void run() {

        //create main thread
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }

        BulletInBoard board = new BulletInBoard();
        Log log = new Log();

        //create server socket
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //start main loop
        while (!isStopped()) {

            //accept client socket
            Socket client = null;
            try {
                client = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            new Thread(new BoardServer(board, client, log)).start();

            if (--maxItrs == 0) {
                stop();
            }
        }

        System.out.println("Server closed ...");

    }

    public synchronized void stop(){

        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean isStopped() {

        return this.isStopped;
    }
}
