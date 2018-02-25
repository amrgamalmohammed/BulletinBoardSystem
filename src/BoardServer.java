import java.io.*;
import java.net.Socket;
import java.util.Random;

public class BoardServer implements Runnable{

    private Socket clientSocket;
    private BulletInBoard board;
    private Log log;

    public BoardServer(BulletInBoard board, Socket clientSocket, Log log) {

        this.clientSocket = clientSocket;
        this.board = board;
        this.log = log;
    }

    @Override
    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            //read message from client
            String msg = bufferedReader.readLine();
            String[] segments = msg.split("\t\t");
            String type = segments[0];
            String ID = segments[1];

            //assign received sequence number
            int rSeq = board.getrSeq();

            int rNum = -1;
            //if reader get and increment number of readers
            if (type.equalsIgnoreCase("read")) {
                rNum = board.getrNum();
            }

            //simulate delay
            Thread.sleep(getRandomSec());

            //assign served sequence number
            int sSeq = board.getsSeq();

            switch (type) {
                case "read":
                    board.decrNum();
                    break;
                case "write":
                    board.setoVal(Integer.parseInt(ID));
                    break;
                default:
                    //generate error
            }

            int oVal = board.getoVal();

            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));

            //respond to client
            bufferedWriter.write( String.valueOf(oVal) + "\t\t" +
                                    String.valueOf(sSeq) + "\t\t" +
                                    String.valueOf(rSeq) + "\n");
            bufferedWriter.flush();

            log.write(sSeq, oVal, rNum, ID, type);

            bufferedReader.close();
            bufferedWriter.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getRandomSec() {

        Random r = new Random();
        int Low = 0;
        int High = 10000;
        return r.nextInt(High - Low) + Low;
    }
}
