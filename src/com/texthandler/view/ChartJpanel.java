package com.texthandler.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import com.texthandler.model.SenseOfExistence;

public class ChartJpanel extends JPanel{

	private JPanel panel;
	private Map<String, Integer> map;
	private boolean doSort;
	private ArrayList<Map.Entry<String,Integer>> list;
	
	public ChartJpanel(JPanel panel) {
		
		this.panel = panel;
		map = SenseOfExistence.getInstance().getMap();
		list = SenseOfExistence.getInstance().sort();
		doSort = false;
	}
	
	public void setDoSort(boolean flag) {
		doSort = flag;
	}
	
	public boolean getDoSort() {
		return doSort;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		
		int width = getWidth();
		int height = getHeight();
		int topMargin = 50;
		int leftMargin = 20;
		int BottomMargin = 100;
		int ruler = height-topMargin-BottomMargin;
		int rulerStep = ruler/10;
		
		Graphics2D g2 = (Graphics2D) g;
		
		//背景
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
//		ImageIcon icon = new ImageIcon(getClass().getResource("/img/sdxl.jpg"));
//		Image img = icon.getImage();
//		g.drawImage(img, 0, 0, Width, Height, this);
		
		g2.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i <= 10; i++){//绘制灰色横线和百分比
			g2.setFont(new Font("宋体", Font.PLAIN, 20));
			g2.drawString((100-10*i)+"%", leftMargin, topMargin+rulerStep*i);//写下百分比
			g2.drawLine(leftMargin, topMargin+rulerStep*i, width-leftMargin, topMargin+rulerStep*i);//绘制灰色横线
		}
		
		if(!doSort) {
			Set<String> nameSet = map.keySet();
			int i = 0;
			g2.setFont(new Font("宋体", Font.PLAIN, 22));
			for(String name : nameSet) {
				g2.setColor(Color.PINK);
				int value = ruler*map.get(name)/6000;
				int step = (i+1)*width/22;//设置间隔
				g2.fillRoundRect(step*2, height-value-BottomMargin, width/22, value, width/22, 10);
				g2.setColor(Color.black);
				g2.drawString(map.get(name)/60+"%", step*2, height-value-BottomMargin-5);
				g2.drawString(name, step*2, height-70);
				i++;
			}
		}else {
			int i = 0;
			g2.setFont(new Font("宋体", Font.PLAIN, 22));
			for(Map.Entry<String,Integer> m : list) {
				g2.setColor(Color.PINK);
				int value = ruler*m.getValue()/6000;
				int step = (i+1)*width/22;//设置间隔
				g2.fillRoundRect(step*2, height-value-BottomMargin, width/22, value, width/22, 10);
				g2.setColor(Color.black);
				g2.drawString(m.getValue()/60+"%", step*2, height-value-BottomMargin-5);
				g2.drawString(m.getKey(), step*2, height-70);
				i++;
			}
		}
	}
	
}
