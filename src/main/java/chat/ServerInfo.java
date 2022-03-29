package chat;

public class ServerInfo {

    public ServerInfo() {
    }

    public static final int PORT = 5000;
    public static final String SERVER_IP = "";
    public static void consoleLog(String log) {
        System.out.println("[server " + Thread.currentThread().getName() + "]" + log);
    }
}
