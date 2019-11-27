package com.texthandler.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DensityBuilder {
	
	private String text;
	
	public DensityBuilder() {
		text = TxtBuilder.getBuilder().getText();
	}
	
	public int[] getDensity(String name) {
		int[] density = new int[FinalData.chapterNum];
		String[] temp;
		String delimiter = "\\|";  // 指定分割字符
		temp = text.split(delimiter);
		for(int i = 0; i < FinalData.chapterNum ; i++){
			Pattern pattern = Pattern.compile(name);
			Matcher matcher = pattern.matcher(temp[i]);
			int count = 0;
			while(matcher.find())
				count++;
			density[i] = count;
		}
		return density;
	}
	
}
