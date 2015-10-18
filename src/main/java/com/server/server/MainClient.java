package com.server.server;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.server.utils.Args;

public class MainClient
{
    public static void main(String[] args)
    {
        ClientBootstrap bootstrap =
            new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory()
        {

            public ChannelPipeline getPipeline() throws Exception
            {
                return Channels.pipeline(new ClientHandler());
            }
        });

        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
    }

    private static class ClientHandler extends SimpleChannelHandler
    {
        private static Random random = new Random();

        @Override
        public void channelConnected(ChannelHandlerContext context, ChannelStateEvent e)
        {
            //System.out.println("hello world , is my client");

            String msg = "Hello,I'm a client";
            ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
            buffer.writeBytes(msg.getBytes());
            e.getChannel().write(buffer);

            sendMessageByFrame(e);
        }

        private void sendMessageByFrame(ChannelStateEvent e)
        {
            String[] demoArray = new String[] { "hello world , i`m the demo1 ", "my name is demo2 ", "here i`m , i`m demo3 " };
            for (int i = 0; i < 50; i++)
            {
                int randomIndex = random.nextInt(i + 10);
                String demo = demoArray[randomIndex % demoArray.length];
                ChannelBuffer buffer = stringToChannelBuffer(demo);
                if (buffer == null)
                    continue;

                e.getChannel().write(buffer);
            }
        }

        private ChannelBuffer stringToChannelBuffer(String msg)
        {
            if (Args.isEmpty(msg))
                return null;

            ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
            buffer.writeBytes(msg.getBytes());
            return buffer;
        }
    }
}
