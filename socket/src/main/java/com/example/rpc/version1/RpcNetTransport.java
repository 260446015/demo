package com.example.rpc.version1;

import com.example.rpc.version1.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Socket socket;
        try {
            socket = newSocket(host, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        return null;

    }
}
