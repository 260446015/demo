package com.example.rpc.version1;

import com.example.rpc.version1.api.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProccessHandler implements Runnable {
    private Socket socket;
    private Object service;

    public ProccessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object invoke = invoke(rpcRequest);
            System.out.println("服务端处理执行结果"+invoke);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws Exception {
        Class<?> clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        return method.invoke(service,rpcRequest.getArgs());
    }
}
