package com.texthandler.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.texthandler.componentUI.TabbedPaneUI;
import com.texthandler.model.FinalData;

public class TabbedPaneFrame extends JFrame{

	private JTabbedPane tabbedPane;
	private int count = 0;

	public TabbedPaneFrame(String name) {

		// 添加选项卡
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("载入小说", null);
		tabbedPane.addTab("存在感", null);
		tabbedPane.addTab("密度图", null);
		tabbedPane.addTab("亲密图", null);
		
		tabbedPane.setUI(new TabbedPaneUI());
		tabbedPane.setFont(new Font("粗体", Font.PLAIN, 22));
		
		init();

		// 添加选项卡面板
		add(tabbedPane, "Center");
		
		//绘制窗口
		this.setTitle(name);
//		this.setSize(1024, 850);
		this.setSize((int) (905*1), (int) (630*1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); // 获得显示器大小对象  
		Dimension frameSize = this.getSize();             // 获得窗口大小对象 
		this.setLocationRelativeTo(null);
//		this.setLocation((displaySize.width - frameSize.width) / 2,  
//				(displaySize.height - frameSize.height) / 2); // 设置窗口居中显示器显示
		this.setVisible(true);
		
		// 添加监听器
//		tabbedPane.addChangeListener(new ChangeListener() {
//
//			@Override
//			public void stateChanged(ChangeEvent e) {
//
//				int n = tabbedPane.getSelectedIndex();
//				loadTab(n);
//
//			}
//
//		});

//		loadTab(0);
	}
	
	private void init() {
		tabbedPane.setComponentAt(0, new MyJpanel("阅读"));
		tabbedPane.setComponentAt(1, new MyJpanel("排名"));
		tabbedPane.setComponentAt(2, new MyJpanel("分布"));
		tabbedPane.setComponentAt(3, new MyJpanel("展示"));
	}

//	private void loadTab(int n) {
//
//		String title = tabbedPane.getTitleAt(n);
//		String countString = String.valueOf(count ++);
//		String msg = "this is " +n+ title + ", load at " + countString + " times";
//		tabbedPane.setComponentAt(n, new JLabel(msg));
//
//	}

	

}
