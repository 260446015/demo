package com.example.socket;

import java.io.*;
import java.net.Socket;

public class SocketClientBootstrap {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("客户端端消息----\n");
            bufferedWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverStr = bufferedReader.readLine();
            System.out.println(serverStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
