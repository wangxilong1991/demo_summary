package com.wxl.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    private String serverHost;
    private int intPort;
    private String name ="client";

    private Channel channel ;

    public NettyClient(String host,int port) throws InterruptedException {
        this.serverHost = host;
        this.intPort = port;
        start();
    }
    public NettyClient(String host,int port,String name) throws InterruptedException {
        this.serverHost = host;
        this.intPort = port;
        this.name = String.format("[%s]",name);
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,false)
                    .remoteAddress(serverHost,intPort)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyClientHandler(name));
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(serverHost, intPort);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess()) {
                        System.out.println("链接服务端失败!");
                    } else {
                        System.out.println("链接服务端成功！");
                        channel = channelFuture.channel();
                    }
                }
            });
            channelFuture.sync();
            // wait until close
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void send(String msg){
        ByteBuf byteBuf = Unpooled.buffer().writeBytes((name+msg).getBytes());
        channel.writeAndFlush(byteBuf);
    }

    public void closeConnect() {
        this.send(" 挥了挥手 !");
        channel.close();
    }
    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient("localhost",10010,"cc");
        new Thread(){
            @Override
            public void run() {
                try {

                    nettyClient.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Thread.sleep(1000);
        nettyClient.send("hello j er");
        Thread.sleep(1000);
        nettyClient.closeConnect();

    }
}

class NettyClientHandler extends ChannelInboundHandlerAdapter{
    private String name;

    public NettyClientHandler(String name) {
        this.name = name;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = null;
        try{
            byteBuf = (ByteBuf)msg;

            byte[] bys = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes(byteBuf.readerIndex(),bys);

            System.out.println(new String(bys));

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] first = (name+" 来了").getBytes();
        ByteBuf firstMessage = Unpooled.buffer();
        firstMessage.writeBytes(first);
        ctx.writeAndFlush(firstMessage);
        System.err.println("客户端发了第一条消息!");
    }
}