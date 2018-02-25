import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Start {

    public static void main(String[] args) {

        propertiesReader pReader = null;
        try {
            //parsing properties file
            pReader = new propertiesReader("system.properties");
            String ServerFilePath = pReader.getServerFilePath();
            String serverIP = pReader.getServerIP();
            String serverPort = pReader.getServerPort();
            String ServerUserName = pReader.getServerUserName();
            String ServerPassword = pReader.getServerUserPassword();
            int numOfReaders = pReader.getNumOfReaders() ;
            int numOfWriters = pReader.getNumOfWriters();
            int maxAcc = pReader.getMaxAcc();
            int numOfClients =  numOfReaders + numOfWriters;
            ArrayList<String> clientsHosts = pReader.getClientsHosts();
            ArrayList<String> clientsUserNames= pReader.getClientsUserNames();
            ArrayList<String> clientsPasswords = pReader.getClientsPasswords();
            ArrayList<String> clientsFilePath = pReader.getClientsFilePath();

            //initiate server
            System.out.println("Initializing server ...");
            new Thread(new ServerInit(serverPort, serverIP,
                            ServerUserName,ServerPassword, ServerFilePath,
                            String.valueOf(numOfClients*maxAcc))).start();

            //sleep to make sure server is running
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int i = 0;

            //initiate readers
            System.out.println("Initializing readers ...");
            for (; i < numOfReaders; i++) {
                new Thread(new ReaderInit(serverIP, serverPort, i + 1, maxAcc,
                        clientsHosts.get(i), clientsUserNames.get(i),
                        clientsPasswords.get(i), clientsFilePath.get(i))).start();
            }


            //initiate writers
            System.out.println("Initializing writers ...");
            for (; i < numOfClients; i++) {
                new Thread(new WriterInit(serverIP, serverPort, i + 1, maxAcc,
                        clientsHosts.get(i), clientsUserNames.get(i),
                        clientsPasswords.get(i), clientsFilePath.get(i))).start();
            }

            System.out.println("Finished ...");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class ServerInit implements Runnable{

        private String serverPort, host, user, password, serverFilePath, maxItr;

        ServerInit(String serverPort, String host, String user, String password, String serverFilePath, String maxItr) {
            this.serverPort = serverPort;
            this.host = host;
            this.user = user;
            this.password = password;
            this.serverFilePath = serverFilePath;
            this.maxItr = maxItr;
        }

        @Override
        public void run() {

            int index = serverFilePath.lastIndexOf("/");
            String fileName = serverFilePath.substring(index + 1);
            String fileDirectory = serverFilePath.substring(0, index);
            String command = " cd " + fileDirectory + " ; javac " +
                    fileName  + ".java ; java " + fileName + " " + serverPort + " " + maxItr;
            System.out.println(command);

            Util util = new Util(command, user, host, password);
            util.initSSH();
        }
    }

    private static class ReaderInit implements Runnable{

        String serverIP, serverPort, id, maxAcc, host, user, password, clientFilePath;

        ReaderInit(String serverIP, String serverPort, int id, int maxAcc,
                   String host, String user, String password, String clientFilePath) {
            this.serverIP = serverIP;
            this.serverPort = serverPort;
            this.id = String.valueOf(id);
            this.maxAcc = String.valueOf(maxAcc);
            this.host = host;
            this.user = user;
            this.password = password;
            this.clientFilePath = clientFilePath;
        }

        @Override
        public void run() {

            int index = clientFilePath.lastIndexOf("/");
            String fileName = clientFilePath.substring(index + 1);
            String fileDirectory = clientFilePath.substring(0, index);
            String command = " cd " + fileDirectory + " ; javac " + fileName  + ".java ; java " + fileName +
                    " "+ serverIP + " " + serverPort + " "
                    + id + " " + maxAcc;
            System.out.println(command);

            Util util = new Util(command, user, host, password);
            util.initSSH();
        }
    }

    private static class WriterInit implements Runnable{

        String serverIP, serverPort, id, maxAcc, host, user, password, clientFilePath;

        WriterInit(String serverIP, String serverPort, int id, int maxAcc,
                   String host, String user, String password, String clientFilePath) {
            this.serverIP = serverIP;
            this.serverPort = serverPort;
            this.id = String.valueOf(id);
            this.maxAcc = String.valueOf(maxAcc);
            this.host = host;
            this.user = user;
            this.password = password;
            this.clientFilePath = clientFilePath;
        }

        @Override
        public void run() {

            int index = clientFilePath.lastIndexOf("/");
            String fileName = clientFilePath.substring(index + 1);
            String fileDirectory = clientFilePath.substring(0, index);
            String command = " cd " + fileDirectory + " ; javac " + fileName  + ".java ; java " + fileName +
                    " "+ serverIP + " " + serverPort + " "
                    + id + " " + maxAcc;
            System.out.println(command);

            Util util = new Util(command, user, host, password);
            util.initSSH();
        }
    }
}
