package com.texthandler.view;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.texthandler.model.IntimacyBuilder;
import com.texthandler.model.SenseOfExistence;

public class groupsJpanel extends JPanel{
	
	private JPanel panel;
	private List<Set<String>> groups;
	private Map<String, Integer> map;
	private boolean flag;
	private Map<String,Integer> esGroups;
	private Map<String,Double> lines;
	private List<ArrayList<String>> degreeGroups;
	
	public groupsJpanel(JPanel panel,boolean flag) {
		
		this.panel = panel;
		groups = IntimacyBuilder.getInstance().getGroups();
		lines = IntimacyBuilder.getInstance().getWeights();
		map = SenseOfExistence.getInstance().getMap();
		esGroups = SenseOfExistence.getInstance().getNameGroups();
		degreeGroups = IntimacyBuilder.getInstance().getDegreeGroups();
		this.flag = flag;

	}

	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		
		int paneWidth = getWidth();
		int paneHeight = getHeight();

		int maxSize=0;
		for (Set<String> set : groups) {
			if(set.size()>maxSize){
				maxSize = set.size();
			}
		}
		int rulerStep1 = paneWidth/groups.size();
		int rulerStep2 = (paneHeight-50)/maxSize;
		int width = rulerStep1/2;
		int height = rulerStep2*2/3;
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(flag){
			ImageIcon icon = new ImageIcon("img/yxs.jpg");
			Image img = icon.getImage();
			g2.drawImage(img, rulerStep1*2, rulerStep2*4, rulerStep1, rulerStep2*2, this);

			g2.setFont(new Font("宋体", Font.PLAIN, 22));
			int j = 0;
			for (Set<String> set : groups) {
				int i = 0;
				for (String string : set) {
					g2.setColor(new Color(244, 255, 129));
					g2.fillRoundRect(width/2+rulerStep1*j, rulerStep2/6+rulerStep2*i, width, height, 20, 20);
					g2.setColor(Color.black);
					g2.drawString(string, width/2+rulerStep1*j+width/4, rulerStep2/6+rulerStep2*i+height/2);
					i++;
				}
				j++;
				if(j<=2) {
					g2.setColor(Color.LIGHT_GRAY);
					g2.drawLine(rulerStep1*j, rulerStep2/6, rulerStep1*j, paneHeight-60);
				}
			}
		}
		else{
			int R = (paneHeight-100)/(degreeGroups.size()*2);
			int centerX = paneWidth/2;
			int centerY = (paneHeight-50)/2;
			int r = R/2;

//			List<Color> colors = new ArrayList<>();
//			colors.add(new Color(245, 183, 177));
//			colors.add(new Color(171, 235, 198));
//			colors.add(new Color(174, 214, 241));


			Map<String, Map<String, Integer>> coordinate = new HashMap<>();
			//画点
			for(int i=0;i<degreeGroups.size();i++){
				g2.setColor(Color.black);
				int r1 = (int)(r*(degreeGroups.size()-i)/degreeGroups.size()*1.5);
				ArrayList<String> temp = degreeGroups.get(i);
				int count = temp.size();
				for(int j=0;j<count;j++){
					double angle = 2*Math.PI/count*j+Math.PI/3*(i+1);
					int x = (int) (centerX-R*(i+1)*Math.cos(angle));
					int y = (int) (centerY-R*(i+1)*Math.sin(angle));
					coordinate.put(temp.get(j),new HashMap<String,Integer>(){{
						put("x",x);
						put("y",y);
					}});

					g2.setFont(new Font("宋体", Font.PLAIN, esGroups.get(temp.get(j))*20));
					g2.drawString(temp.get(j),x-r1*2,y+r1/2);
//					g2.fillOval(x-r1/2,y-r1/2,r1,r1);
				}
			}

			g2.setColor(new Color(215, 189, 226));
			for (Map.Entry<String,Double> entry : lines.entrySet()) {
				String[] names = entry.getKey().split("\\|");
				double weight = entry.getValue();
				g2.setStroke(new BasicStroke((float) (weight*5.0)));
				int x1 = coordinate.get(names[0]).get("x");
				int y1 = coordinate.get(names[0]).get("y");
				int x2 = coordinate.get(names[1]).get("x");
				int y2 = coordinate.get(names[1]).get("y");
				int recWidth = Math.abs(x1-x2);
				int recHeight = Math.abs(y1-y2);
				if(x1>x2 && y1>y2){
					int beginX = x2-recWidth;
					int beginY = y2;
					g2.drawArc(beginX,beginY,recWidth*2,recHeight*2,0,90);
//					g2.drawArc(beginX+recWidth,beginY-recHeight,recWidth*2,recHeight*2,180,90);
				}else if(x1>x2 && y1<y2){
					int beginX = x1-recWidth;
					int beginY = y1;
					g2.drawArc(beginX,beginY,recWidth*2,recHeight*2,90,90);
//					g2.drawArc(beginX-recWidth,beginY-recHeight,recWidth*2,recHeight*2,0,-90);
				}else if(x1<x2 && y1<y2){
					int beginX = x1;
					int beginY = y1-recHeight;
					g2.drawArc(beginX,beginY,recWidth*2,recHeight*2,180,90);
//					g2.drawArc(beginX-recWidth,beginY+recHeight,recWidth*2,recHeight*2,0,90);
				}else if(x1<x2 && y1>y2){
					int beginX = x1-recWidth;
					int beginY = y2-recHeight;
					g2.drawArc(beginX,beginY,recWidth*2,recHeight*2,0,-90);
//					g2.drawArc(beginX+recWidth,beginY+recHeight,recWidth*2,recHeight*2,90,90);
				}else{
					if(x1==x2){
						int tempRecWidth = recHeight/4;
						int beginX = x1-tempRecWidth;
						int beginY = y1>y2?y2:y1;
						g2.drawArc(beginX,beginY,tempRecWidth*2,recHeight,90,-180);
					}else{
						int tempRecHeight = recWidth/4;
						int beginY = y1-tempRecHeight;
						int beginX = x1>x2?x2:x1;
						g2.drawArc(beginX,beginY,recWidth,tempRecHeight*2,0,180);
					}
				}
			}
		}
	}
}
