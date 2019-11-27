package com.texthandler.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.texthandler.model.DensityBuilder;
import com.texthandler.model.FinalData;

public class DenJpanel extends JPanel{

	private JPanel panel;
	private int[] density;
	private DensityBuilder densityBuilder;
	
	public DenJpanel(JPanel panel, String name) {
		this.panel = panel;
		densityBuilder = new DensityBuilder();
		density = densityBuilder.getDensity(name);
	}
	
	public void setDensity(String name) {
		density = densityBuilder.getDensity(name);
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		
		int width = getWidth();
		int height = getHeight();
		int topMargin = 100;
		int leftMargin = 20;
		int BottomMargin = 100;
		int ruler = width-2*leftMargin;
		int rulerStep = ruler/FinalData.chapterNum;
		
		Graphics2D g2 = (Graphics2D) g;
		
		//背景
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
//		ImageIcon icon = new ImageIcon(getClass().getResource("/img/sdxl.jpg"));
//		Image img = icon.getImage();
//		g2.drawImage(img, 0, 0, width, height, this);
		
		for(int i = 0; i < FinalData.chapterNum; i++){
//			int color = 255 - density[i];
//			if (color<0) {
//				color = 0;
//			}
			
			if(density[i]==0)
				g2.setColor(new Color(255, 255, 255));
			else if(density[i]<27)
				g2.setColor(new Color(235, 245, 251));
			else if(density[i]<54)
				g2.setColor(new Color(214, 234, 248));
			else if(density[i]<81)
				g2.setColor(new Color(174, 214, 241));
			else if(density[i]<108)
				g2.setColor(new Color(133, 193, 233));
			else if(density[i]<135)
				g2.setColor(new Color(93, 173, 226));
			else if(density[i]<162)
				g2.setColor(new Color(52, 152, 219));
			else if(density[i]<189)
				g2.setColor(new Color(46, 134, 193));
			else if(density[i]<216)
				g2.setColor(new Color(40, 116, 166));
			else if(density[i]<243)
				g2.setColor(new Color(33, 97, 140));
			else
				g2.setColor(new Color(27, 79, 114));
			
			g2.fillRect(leftMargin+i*rulerStep, topMargin, rulerStep, height/2);
		}
		
	}
	
}
