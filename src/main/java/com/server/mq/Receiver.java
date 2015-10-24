package com.server.mq;

import static com.server.mq.QueueConfig.QUEUE_NAME;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.server.utils.Args;

public class Receiver 
{
	private QueueClient client = new QueueClient(QUEUE_NAME);
	
	public static void main(String[] args)
	{
		Receiver receiver = new Receiver();
	}
	
	public void init() throws IOException, TimeoutException, QueueInitException
	{
		client.init();
	}
	
	public void close() throws IOException, TimeoutException
	{
		client.close();
	}
}
