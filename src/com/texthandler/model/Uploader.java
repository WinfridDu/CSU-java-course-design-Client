package com.texthandler.model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Uploader {
	
	private Socket client;
	private String fileName;
	private DataOutputStream dos;
	private FileInputStream fis;
	
	public Uploader(String fileName) {
		client = TxtBuilder.getBuilder().getSocket();
		this.fileName = fileName;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			fis = new FileInputStream(new File(fileName));
		} catch (IOException e) {
			System.out.println("-----5-----");
		}
		try {
			dos.writeUTF("upload");
		} catch (IOException e) {
			System.out.println("-----6-----");
		}
	}
	
	public void upload() {
		byte[] imgData = new byte[1024*10];
		int length = 0;
        try {
			while ((length = fis.read(imgData, 0, imgData.length)) > 0) {
			    try {
					dos.write(imgData, 0, length);
					dos.flush();
				} catch (IOException e) {
					System.out.println("-----7-----");
				}
			}
		} catch (IOException e) {
			System.out.println("-----6-----");
		}
	}
	
}
