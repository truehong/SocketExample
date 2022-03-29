package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClientApp {
    public static void main(String[] args) {
        String name = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("대화명을 입력해 주세요");
            System.out.println(">>>");
            name = scanner.nextLine();

            if(!name.isEmpty()) {
                break;
            }
            System.out.println("대화명은 한글자 이상 입력해야 합니다.");
        }
        scanner.close();
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ServerInfo.SERVER_IP, ServerInfo.PORT));
            ServerInfo.consoleLog("채팅방에 입장하였습니다.");
            new ChatWindow(name, socket);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "join" + name + "\r\n";
            pw.println(request);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
