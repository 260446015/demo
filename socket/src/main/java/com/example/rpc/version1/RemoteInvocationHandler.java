package com.example.rpc.version1;

import com.example.rpc.version1.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setClassName(method.getDeclaringClass().getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setTypes(method.getParameterTypes());
            rpcRequest.setArgs(args);
            return rpcNetTransport.send(rpcRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
