package com.server.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class MainServer
{
    private static int receiveMsgTimes = 0;

    public static void main(String[] args)
    {
        ServerBootstrap bootstrap =
            new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory()
        {

            public ChannelPipeline getPipeline() throws Exception
            {
                return Channels.pipeline(new HelloWorldServerHandler());
            }
        });

        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class HelloWorldServerHandler extends SimpleChannelHandler
    {
        @Override
        public void channelConnected(ChannelHandlerContext context, ChannelStateEvent e) throws Exception
        {
            super.channelConnected(context, e);
        }

        @Override
        public void messageReceived(ChannelHandlerContext context, MessageEvent e)
        {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            receiveMsgTimes += 1;
            System.out.println("第" + receiveMsgTimes + "次接收信息，信息内容：" + new String(buffer.toString(Charset.defaultCharset())));
        }
    }
}
