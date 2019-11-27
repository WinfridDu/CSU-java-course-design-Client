package com.texthandler.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import com.texthandler.componentUI.MyButtonUI;
import com.texthandler.componentUI.MyComboBoxUI;
import com.texthandler.model.FinalData;

public class MyJpanel extends JPanel{

	private MyMouseListener mml;

	public MyJpanel(String text){
		
		mml = new MyMouseListener(this);

		this.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel controlPanel = new JPanel();
		panel.add(controlPanel);
		
		if(!"分布".equals(text)) {
			JButton button1 = createBtn(text);
			controlPanel.add(button1);
		}else {
			String[] names = FinalData.names;
			JComboBox comboBox = createCbb(names);
			controlPanel.add(comboBox);
		}

		if("排名".equals(text)) {
			JButton button3 = createBtn("排序");
			controlPanel.add(button3);
		}

		if("展示".equals(text)){
			controlPanel.add(createBtn("关系图"));
		}

		if(!"阅读".equals(text)) {
			controlPanel.add(createBtn("上传"));
		}else{
			controlPanel.add(createBtn("想看李若彤"));
        }
//		else {
//			String[] bookNames = {"神雕侠侣","射雕英雄传","倚天屠龙记"};
//			JComboBox comboBox = createCbb(bookNames);
//			controlPanel.add(comboBox);
//		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		
//		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("img/lrt.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);

	}

	private JButton createBtn(String text) {
		JButton btn = new JButton(text);
		btn.setUI(new MyButtonUI());// 恢复基本视觉效果
		btn.setPreferredSize(new Dimension(120, 40));// 设置按钮大小
		btn.setContentAreaFilled(false);// 设置按钮透明
		btn.setFont(new Font("宋体", Font.PLAIN, 22));// 按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		btn.addMouseListener(mml);
		btn.setBorderPainted(true);
		return btn;
	}

	private JComboBox createCbb(String[] temps) {
		JComboBox jComboBox = new JComboBox<>(temps);
		//		jComboBox.setEditable(true);
		jComboBox.setUI(new MyComboBoxUI());
		jComboBox.setPreferredSize(new Dimension(140, 40));
		jComboBox.setFont(new Font("宋体", Font.PLAIN, 22));
		jComboBox.addItemListener(new MyItemListener(this, jComboBox));
		return jComboBox;
	}

}
