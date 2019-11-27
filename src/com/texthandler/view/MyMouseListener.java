package com.texthandler.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.texthandler.model.FinalData;
import com.texthandler.model.SenseOfExistence;
import com.texthandler.model.TxtBuilder;
import com.texthandler.model.Uploader;

public class MyMouseListener implements MouseListener{
	
	private JPanel panel;
	private ChartJpanel cjp;
	private groupsJpanel gjp;
	private JScrollPane scrollPane;
	private String text;
	private JTextArea textArea;
	
	public MyMouseListener(JPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String btnName = ((JButton)e.getSource()).getText();
		if ("阅读".equals(btnName.trim())) {
			if(scrollPane!=null){
				scrollPane.setVisible(true);
			}else{
				try {
					loadText();
				} catch (IOException e1) {
					System.out.println("-----4-----");
				}
			}
		}else if("排名".equals(btnName.trim())) {
			if(cjp!=null) {
				cjp.setDoSort(false);
			}else {
				range();
			}
		}else if("上传".equals(btnName.trim())) {
			saveImg();
		}else if("排序".equals(btnName.trim())) {
			if(cjp!=null) {
				cjp.setDoSort(true);
			}
		}else if("展示".equals(btnName.trim())) {
			if(gjp!=null){
				gjp.setFlag(true);
			}else{
				showGroups(true);
			}
		}else if("关系图".equals(btnName.trim())){
			if(gjp!=null){
				gjp.setFlag(false);
			}else{
				showGroups(false);
			}
		}else if("想看李若彤".equals(btnName.trim())){
			if(scrollPane!=null){
				scrollPane.setVisible(false);
			}
		}
//		cjp.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.RAISED);// 设置边框凸显
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// 设置边框凹显
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(new Color(0x33, 0x66, 0xcc));// 设置字体颜色
		btn.setBorderPainted(true);// 显示边框
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// 设置边框凹显
		btn.setBorder(etchedBorder);
		btn.setRolloverEnabled(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(Color.black);// 设置字体颜色
		btn.setBorderPainted(false);// 隐藏边框
	}
	
	public void loadText() throws IOException {
			text = TxtBuilder.getBuilder().getText();
			textArea = new JTextArea();
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, panel.getWidth(), panel.getHeight()-50);
			panel.add(scrollPane, BorderLayout.CENTER);
			scrollPane.setViewportView(textArea);
			textArea.setText(text);
			textArea.setLineWrap(true);        //激活自动换行功能 
			textArea.setWrapStyleWord(true); 
			textArea.setEditable(false);
			textArea.setBounds(0, 0, panel.getWidth(), panel.getHeight()-50);
			textArea.setFont(new Font("楷体", 0, 22));
			textArea.setCaretPosition(0);
	}
	
	private void range() {
		
		cjp = new ChartJpanel(panel);
		cjp.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(cjp, BorderLayout.CENTER);
		cjp.setVisible(true);
		
	}
	
	private void saveImg() {
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight()-50, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		panel.paint(g2);
		try {
			String imgName = "img/"+getRandomString(5)+".jpg";
			ImageIO.write(image, "jpeg", new File(imgName));
			Uploader uploader = new Uploader(imgName);
			uploader.upload();
		} catch (IOException e) {
			System.out.println("-----4----");
			JOptionPane.showMessageDialog(null, "上传失败");
		}
		JOptionPane.showMessageDialog(null, "上传成功");
	}
	
	private String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random = new Random();
	     StringBuffer sb = new StringBuffer();
	     for(int i = 0; i < length; i++){
	       int number = random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
	private void showGroups(boolean flag) {
		gjp = new groupsJpanel(panel, flag);
		gjp.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		panel.add(gjp, BorderLayout.CENTER);
		gjp.setVisible(true);
	}

}
