package com.interview.javabasic.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        // 客户端发数据报给服务端
        DatagramSocket socket = new DatagramSocket();
        // 要发送给服务端的数据
        byte[] buf = "Hello World".getBytes();
        // 将IP地址封装成InetAddress对象
        InetAddress address = InetAddress.getByName("127.0.0.1");
        // 将要发送给服务端的数据封装成DatagramPacket对象 需要填写上ip地址与端口号
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
                65001);
        // 发送数据给服务端
        socket.send(packet);

        // 客户端接受服务端发送过来的数据报
        byte[] data = new byte[100];
        // 创建DatagramPacket对象用来存储服务端发送过来的数据
        DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
        // 将接受到的数据存储到DatagramPacket对象中
        socket.receive(receivedPacket);
        // 将服务器端发送过来的数据取出来并打印到控制台
        String content = new String(receivedPacket.getData(), 0,
                receivedPacket.getLength());
        System.out.println(content);

    }

}
