package com.example.rpc.version1;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    public static  <T> T clientProxy(final Class<T> clazz, String host, int port) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RemoteInvocationHandler(host, port));
    }
}
