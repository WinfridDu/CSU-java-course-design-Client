package com.texthandler.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TxtBuilder {

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String text;
	private static final TxtBuilder INSTANCE = new TxtBuilder();
	
	private TxtBuilder() {
		
		try {
			client = new Socket("localhost",8888);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("-----1-----");
		} catch (IOException e) {
			System.out.println("-----2-----");
		}
		
		try {
			dos.writeUTF("load");
			StringBuffer sb = new StringBuffer();
			String temp = new String();
			while((temp = dis.readUTF().trim())!=null) {
				sb.append(temp);
				if(temp.length() < 10000)
					break;
			}
			text = sb.toString();
			
		} catch (IOException e) {
			System.out.println("-----3-----");
		}
	}
	
	public Socket getSocket() {
		return client;
	}
	
	public static TxtBuilder getBuilder() {
		return INSTANCE;
	}
	
	public String getText() {
		return text;
	}
	
}
