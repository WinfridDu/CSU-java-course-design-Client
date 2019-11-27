package com.texthandler.view;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.texthandler.model.DensityBuilder;

public class MyItemListener implements ItemListener{

	private DenJpanel djp;
	private JComboBox jComboBox;
	private DensityBuilder densityBuilder;
	private JPanel panel;
	
	public MyItemListener(JPanel panel, JComboBox jComboBox) {
		this.jComboBox = jComboBox;
		this.panel = panel;
		densityBuilder = new DensityBuilder();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == e.SELECTED){
			if(djp!=null) {
				djp.setDensity((String)jComboBox.getSelectedItem());
			}else{
				djp = new DenJpanel(panel, (String)jComboBox.getSelectedItem());
				djp.setBounds(0, 0, panel.getWidth(), panel.getHeight());
				panel.add(djp, BorderLayout.CENTER);
				djp.setVisible(true);
			}
			djp.repaint();
		}		
	}

}
