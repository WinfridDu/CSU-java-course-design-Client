package com.texthandler;

import javax.swing.JOptionPane;


import com.texthandler.model.TxtBuilder;
import com.texthandler.view.TabbedPaneFrame;

public class HandlerMain {

	public static void main(String[] args) {
		
		String name = JOptionPane.showInputDialog("用户名：");
		JOptionPane.showMessageDialog(null, "连接成功");
		TabbedPaneFrame frame = new TabbedPaneFrame(name);

	}
}

