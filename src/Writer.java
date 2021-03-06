import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {

    public static void main(String[] args) {

        String serverIP = args[0];
        String serverPort = args[1];
        String ID = args[2];
        String maxNumAcc = args[3];

        write(serverIP, serverPort, ID, maxNumAcc);

        return;
    }

    private static void write(String serverIP, String serverPort, String ID, String maxNumAcc) {

        int repeats = Integer.parseInt(maxNumAcc);
        PrintWriter log = null;
        String rSeq, sSeq;

        try {
            log = new PrintWriter("log"+ID+".txt", "UTF-8");
            log.println("Client type: Writer");
            log.println("Client Name: " + ID);
            log.println("rSeq"+"\t\t"+"sSeq");
            log.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        TCPClient client = null;

        for (int i = 0; i < repeats; i++) {

            System.out.println("Writer ...");
            //send write request
            System.out.println("Sending write request ...");
            client = new TCPClient(serverIP, Integer.parseInt(serverPort));
            client.send("write"+"\t\t"+ID+"\n");

            //receive read data
            String msg = client.receive();

            String[] segments = msg.split("\t\t");

            sSeq = segments[1];
            rSeq = segments[2];

            //writing logs
            log.println(rSeq+"\t\t"+sSeq);
            log.flush();

            //sleep between operations
            try {
                Thread.sleep(client.getRandomSec());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished writer " + ID + " ...");
        log.close();
    }
}
