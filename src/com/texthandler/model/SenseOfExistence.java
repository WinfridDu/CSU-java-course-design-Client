package com.texthandler.model;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SenseOfExistence {
	
	private String text;
	private Map<String, Integer> map;
	private static final SenseOfExistence INSTANCE = new SenseOfExistence();
	private Map<String,Integer> esGroups;//存在感分组
	
	private SenseOfExistence() {
		text = TxtBuilder.getBuilder().getText();
		map = getCounts();
		devide();
	}
	
	private Map<String, Integer> getCounts() {
		String temp = "";
		String[] names = FinalData.names;
		Map<String, Integer> map = new HashMap<>();
		for(String name : names) { 
			Pattern pattern = Pattern.compile(name);
			Matcher matcher = pattern.matcher(text);
			int count = 0;
			while(matcher.find())
				count++;
			map.put(name, count);
		}
		
		return map;
	}
	
	public static SenseOfExistence getInstance() {
		return INSTANCE;
	}
	
	public Map<String, Integer> getMap(){
		return map;
	}
	
	public ArrayList<Map.Entry<String,Integer>> sort() {
		ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());//升序，前边加负号变为降序
            }
        }); 
        return list;
	}

	private void devide(){
		int length = map.size();
		Collection<Integer> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		int max = (int)obj[length-1];
		esGroups = new HashMap<>();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue()> (max/3.0)){
				esGroups.put(entry.getKey(),3);
			}else if(entry.getValue()> (max/10.0)){
				esGroups.put(entry.getKey(),2);
			}else{
				esGroups.put(entry.getKey(),1);
			}
		}
	}

	public Map<String,Integer> getNameGroups(){
		return esGroups;
	}
	
}
