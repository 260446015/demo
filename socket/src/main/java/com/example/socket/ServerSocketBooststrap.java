package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketBooststrap {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket accept = serverSocket.accept();
            int localPort = accept.getLocalPort();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String clientStr = bufferedReader.readLine();
            System.out.println(clientStr);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            bufferedWriter.write("服务器端消息----\n");
            bufferedWriter.flush();
        }catch (Exception e){

        }finally {

        }
    }
}
