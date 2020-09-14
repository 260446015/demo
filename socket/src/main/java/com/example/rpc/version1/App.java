package com.example.rpc.version1;

import com.example.rpc.version1.api.IOrderService;

public class App {
    public static void main(String[] args) {
        IOrderService orderService=RpcProxyClient.clientProxy(IOrderService.class,"localhost",8080);

        System.out.println(orderService.queryOrderList());
        System.out.println(orderService.orderById("Mic"));
    }
}
