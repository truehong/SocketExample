package basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

    private static final String SERVER_IP = "";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        Socket socket = null;
        // 1. TCP 서버의 IP 와 PORT를 상수로 할당

        try {
            // 2. 서버와 연결을 위한 소켓 생성
            socket = new Socket();
            // 3. 생성한 소켓을 서버의 소켓과 연결(connect)
            socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket != null & ! socket.isClosed()){
                    socket.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
