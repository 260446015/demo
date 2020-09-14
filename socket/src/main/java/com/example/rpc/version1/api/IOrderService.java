package com.example.rpc.version1.api;

public interface IOrderService {

    String queryOrderList();

    String orderById(String id);
}
