package com.example.netty_tomcat;

import com.example.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author yindwe@yonyou.com
 * @Date 2021/4/2
 */
public class NettyRequest implements Request {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public NettyRequest(ChannelHandlerContext ctx, HttpRequest req){
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl(){
        return this.req.uri();
    }

    public String getMethod(){
        return this.req.method().name();
    }

    public Map<String, List<String>>getParams(){
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name){
        Map<String, List<String>> params = getParams();
        List<String> param = params.get(name);
        //TODO 暂时只处理一个参数
        if(null == param){
            return null;
        }else {
            return param.get(0);
        }
    }
}
