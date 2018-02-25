import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Log {

    private PrintWriter readers;
    private PrintWriter writers;

    public Log () {

        try {
            readers = new PrintWriter("Readers.txt", "UTF-8");
            readers.println("sSeq"+"\t\t"+"oVal"+"\t\t"+"rID"+"\t\t"+"rNum");
            readers.flush();

            writers = new PrintWriter("Writers.txt", "UTF-8");
            writers.println("sSeq"+"\t\t"+"oVal"+"\t\t"+"wID");
            writers.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public synchronized void write(int sSeq, int oVal, int rNum, String ID, String type) {

        if (type.equalsIgnoreCase("read")){

            readers.println(String.valueOf(sSeq) + "\t\t" +
                                String.valueOf(oVal) + "\t\t" +
                                String.valueOf(ID) + "\t\t" +
                                String.valueOf(rNum));
            readers.flush();
        }
        else {

            writers.println(String.valueOf(sSeq) + "\t\t" +
                    String.valueOf(oVal) + "\t\t" +
                    String.valueOf(ID));
            writers.flush();
        }
    }

    public synchronized void close() {
        writers.close();
        readers.close();
    }
}
