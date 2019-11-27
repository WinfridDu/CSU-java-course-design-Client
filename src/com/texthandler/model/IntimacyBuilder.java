package com.texthandler.model;

import java.util.*;
import java.util.stream.Collectors;

public class IntimacyBuilder {

	private String text;
	private String[] chapters;
	private List<Set<String>> groups;//用于第一种表示
	private Map<String,Double> weights;
	private Map<String,Integer> degree;
	private List<ArrayList<String>> degreeGroups;
	
	private static final IntimacyBuilder INSTANCE = new IntimacyBuilder();
	
	private IntimacyBuilder() {
		text = TxtBuilder.getBuilder().getText();
		String delimiter = "\\|";  // 指定分割字符
		chapters = text.split(delimiter);
		weights = new HashMap<>();
		initGroups();
	}

	public Map<String,Double> getWeights(){
		return weights;
	}

	public Map<String,Integer> getDegree(){
		return degree;
	}
	
	public List<Set<String>> getGroups() {
		return groups;
	}

	public List<ArrayList<String>> getDegreeGroups(){
		return degreeGroups;
	}
	
	private void initGroups() {
		int[][] intimacy = new int[10][10];
		int[][] unIntimacy = new int[10][10];
		String[] names = FinalData.names;
		for (int i = 0; i < 10; i++) {
			String name1 = names[i];
			for(int j = 0; j < 10; j++) {
				String name2 = names[j];
				intimacy[i][j] = 0;
				unIntimacy[i][j] = 0;
				for (String chapter : chapters) {
					int index1 = 0;
					while((index1 = chapter.indexOf(name1, index1)) != -1) {
						int index2 = 0;
						if((index2 = chapter.indexOf(name2, index1)) != -1 
								&& index2-index1 < 5000) {
							intimacy[i][j]++;
						}else {
							unIntimacy[i][j]++;
						}
						index1 += name1.length();
					}
				}
			}
		}

		groups = new ArrayList<>();

		degree = new HashMap<>();
		for (String name:names) {
			degree.put(name,0);
		}

		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < 10; j++) {
				double appear = intimacy[i][j] + intimacy[j][i];
				double notAppear = unIntimacy[i][j] + unIntimacy[j][i];
				if(appear / notAppear > 1.0) {
					degree.put(names[i],degree.get(names[i])+1);
					degree.put(names[j],degree.get(names[j])+1);
					weights.put(names[i]+"|"+names[j], appear/(appear+notAppear));

					if(!groups.isEmpty()){
						boolean contain = false;
						for (Set<String> set : groups) {
							if(set.contains(names[i])||set.contains(names[j])){
								set.add(names[i]);
								set.add(names[j]);
								contain = true;
								break;
							}
						}
						if(!contain){
							Set<String> temp = new HashSet<>();
							temp.add(names[i]);
							temp.add(names[j]);
							groups.add(temp);
						}
					}else{
						groups.add(new HashSet<>());
						groups.get(0).add(names[i]);
						groups.get(0).add(names[j]);
					}
				}
			}
		}

		Map<Integer, List<Map.Entry<String,Integer>>> result = degree.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue()));
		degreeGroups = new ArrayList<>();
		for (Map.Entry<Integer,List<Map.Entry<String,Integer>>> temp: result.entrySet()) {
			ArrayList<String> set = new ArrayList<>();
			List<Map.Entry<String,Integer>> list = temp.getValue();
			for(Map.Entry<String,Integer> entry:list){
				set.add(entry.getKey());
			}
			degreeGroups.add(set);
		}
		Collections.reverse(degreeGroups);

		for(String name : names){
			if(degree.get(name)==0){
				Set<String> temp = new HashSet<>();
				temp.add(name);
				groups.add(temp);
			}
		}
	}
	
	public static IntimacyBuilder getInstance() {
		return INSTANCE;
	}


}
