package com.example.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NewIoClientBootstrap {
    static Selector selector;
    public static void main(String[] args) {
        SocketChannel socketChannel;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",8080));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if(next.isConnectable()){
                        handleConnectable(next);
                    }else if(next.isReadable()){
                        handleReadable(next);
                    }else if(next.isWritable()){
                        handleWritable(next);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleWritable(SelectionKey next) {

    }

    private static void handleReadable(SelectionKey next) throws IOException {
        SocketChannel socketChannel = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        System.out.println("client receive msg:"+new String(byteBuffer.array()));
    }

    private static void handleConnectable(SelectionKey next) throws IOException {
        SocketChannel socketChannel = (SocketChannel) next.channel();
        if(socketChannel.isConnectionPending()){
            socketChannel.finishConnect();
        }
        socketChannel.write(ByteBuffer.wrap("Hello Server,I'm NIo Client".getBytes()));
        socketChannel.register(selector,SelectionKey.OP_READ);
    }
}
