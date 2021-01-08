package com.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.Solution;

public class SolutionTest {
	
	@Test
	public void testDetectMachinesWithMinWaste_Waste0() {
		Integer[] arr = { 1, 2, 4, 5, 6, 10 };
		int target = 11;
		Solution solution = new Solution();
		
		for (List<Integer> i: solution.detectMachinesWithMinWaste(new ArrayList<Integer>(Arrays.asList(arr)), target)) {
			int innerCount = 0;
			for (Integer j: i) {
				innerCount += j;
			}
			assertEquals(target, innerCount);
		}
	}
	
	/**
	 * the minimal waste combination are 5 5 6 and 5 5 6.
	 * The sum is - 16
	 * The waste is - 3, because (minimalCount - target) = 3 
	 */
	@Test
	public void testDetectMachinesWithMinWaste_Waste3() {
		Integer[] arr = { 5, 6, 5, 6 };
		int target = 13;
		Solution solution = new Solution();
		
		for (List<Integer> i: solution.detectMachinesWithMinWaste(new ArrayList<Integer>(Arrays.asList(arr)), target)) {
			int innerCount = 0;
			for (Integer j: i) {
				System.out.println("j:"+j);
				innerCount += j;
			}
			System.out.println("innerCount"+innerCount);
			assertEquals(innerCount, 16);
		}
	}
}
