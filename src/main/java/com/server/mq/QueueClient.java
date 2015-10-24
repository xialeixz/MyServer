package com.server.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.server.utils.Args;

public class QueueClient 
{
	private String queueName;
	
	private ConnectionFactory factory;
	
	private Channel channel;
	
	private Connection connection;
	
	public QueueClient(String queueName)
	{
		this.queueName = queueName;
	}
	
	public void init() throws IOException, TimeoutException, QueueInitException
	{
		if(Args.isEmpty(queueName))
			throw new QueueInitException();
		
		factory = new ConnectionFactory();
		factory.setHost("loaclhost");

		connection = factory.newConnection();
		channel = connection.createChannel();
		
		channel.queueDeclare(queueName, false, false, false, null);
	}
	
	public void sendMsg(String msg) throws IOException
	{
		if(Args.isNull(channel) || Args.isEmpty(msg))
			return ;
		
		channel.basicPublish("", queueName, null, msg.getBytes());
		return ;
	}
	
	public void close() throws IOException, TimeoutException
	{
		if(!Args.isNull(channel))
			channel.close();
		
		if(!Args.isNull(connection))
			connection.close();
	}
}
