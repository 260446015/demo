package com.example.rpc.version1;

import com.example.rpc.version1.api.IOrderService;

public class ServerBootstrap {
    public static void main(String[] args) {
        IOrderService orderService = new IOrderService() {
            @Override
            public String queryOrderList() {
                return "queryOrderList";
            }

            @Override
            public String orderById(String id) {
                return "orderById";
            }
        };
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(orderService,8080);
    }
}
