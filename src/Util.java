import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class Util {

    private String command, user, host, password;

    public Util(String command, String user, String host, String password) {

        this.command = command;
        this.user = user;
        this.host = host;
        this.password = password;
    }

    public void initSSH() {

        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(this.user, this.host, 22);
            session.setPassword(password);
            session.setConfig(config);

            session.connect();
            System.out.println("ssh to " + host + " ...");

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();

            while (true) {
                if (channel.isClosed()) {
                    System.out.println("SSH exit with status: " + channel.getExitStatus());
                    break;
                }
            }

            channel.disconnect();
            session.disconnect();
            System.out.println("ssh session to " + host + " has been terminated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
