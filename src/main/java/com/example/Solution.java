package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	
	//structure to store waste and combinations
	private Map<Integer, List<List<Integer>>> wasteToMachines = new HashMap<>();
	//to find the minimal waste
	int min = Integer.MAX_VALUE;
	
	/**
	 * recursively create all possible combinations, and count it.
	 * if count >= than target, we store it in the wasteToMachines structure.
	 * @param current
	 * @param target
	 * @param temporary
	 */
	private void calcSum(List<Integer> current, int target, List<Integer> temporary) {
		int count = 0;
		// let's count amount of produced chipsets
		for (Integer i : temporary) {
			count += i;
		}
		// if amount more than expected target
		if (count >= target) {
			// we try to find the minimal amount of waste
			if (min >= count) {
				min = count;
				//if we have already calculated that amount of waste 
				if (wasteToMachines.containsKey(count)) {
					List<List<Integer>> tempList = wasteToMachines.get(min);
					// we add another possible combination as List
					tempList.add(temporary);
					wasteToMachines.put(count, tempList);
				} else {
					//otherwise we define it
					List<List<Integer>> list = new ArrayList<List<Integer>>();
					list.add(temporary);
					wasteToMachines.put(count, list);
				}
			}
		}
		//create another combination recursively
		for (int i = 0; i < current.size(); i++) {
			List<Integer> remaining = new ArrayList<Integer>();
			int n = current.get(i);
			for (int j = i + 1; j < current.size(); j++) {
				remaining.add(current.get(j));
			}
			List<Integer> chunks = new ArrayList<Integer>(temporary);
			chunks.add(n);
			calcSum(remaining, target, chunks);
		}
	}

	/**
	 * main function where I find the solution
	 * @param current - chipset/minute for every machine 
	 * @param target - number of chipsets to be produced
	 * @return the minimal waste combination
	 */
	public List<List<Integer>> detectMachinesWithMinWaste(ArrayList<Integer> current, int target) {
		calcSum(current, target, new ArrayList<Integer>());
		print(target);
		return wasteToMachines.get(min);
	}
	
	/**
	 * function to display final result
	 * @param target
	 */
	private void print(int target) {
		System.out.println("Nr solutions=" + wasteToMachines.get(min).size());
		for (List<Integer> i: wasteToMachines.get(min)) {
			for (Integer j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("Waste="+(min-target));
	}
}
