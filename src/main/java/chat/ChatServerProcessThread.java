package chat;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * listWriter : 채팅 서버에 연결된 모든 클라이언트들을 저장하고 있는 List입니다. ( join시 추가됨 )
 * boardcast() : 서버에 연결된 모든 클라이언트들에게 메시지를 전달하기 위한 메서드
 * */
public class ChatServerProcessThread extends Thread{
    private String nickname = null; 
    private Socket socket = null; 
    List<PrintWriter> listWriter = null;
    public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriter) {
        this.socket = socket;
        this.listWriter = listWriter; 
    }

    @Override
    public void run() {
        try{
            BufferedReader bufferedReader = 
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)); 
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            while (true) {
                String request = bufferedReader.readLine();
                if(request == null) {
                    consoleLog("클라이언트로 부터 연결 끊김");
                    doQuit(printWriter); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            consoleLog(this.nickname + " 님이 채팅방을 나갔습니다.");
        }
    }

    private void doQuit(PrintWriter writer) {
        removeWriter(writer);

        String data = this.nickname + "님이 퇴장했습니다.";
        broadcast(data);
    }

    private void removeWriter(PrintWriter writer) {
        synchronized (writer) {
            listWriter.remove(writer);
        }
    }

    private void broadcast(String data) {
        synchronized (listWriter) {
            for(PrintWriter writer : listWriter) {
                writer.println(data);
                writer.flush();
            }
        }
    }

    private void consoleLog(String log) {
        System.out.println(log);
    }


}
