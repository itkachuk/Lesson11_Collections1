package com.lohika.itkachuk.javatc.lesson11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestMyArrayList {
	public static void main(String[] args) {
		MyArrayList<Integer> list = new MyArrayList<Integer>();
		//ArrayList<Integer> list = new ArrayList<Integer>();

        // TestCase1: element insertion to the end of the list
		// + test data preparation
		list.add(10);
		list.add(11);
		list.add(23);
		list.add(17);
		list.add(100);
		list.add(12);
		list.add(45);
		list.add(63);
		list.add(17);
		list.add(200);
				
		// TestCase2: element insertion by index
		list.add(9, 35);
		if (!list.get(9).equals(35)) System.out.println("TestCase2: failed");

        // TestCase3: containsAll method test
        Set<Integer> set = new HashSet<Integer>();
        set.add(23);
        set.add(100);
        if (!list.containsAll(set)) System.out.println("TestCase3: failed");

		System.out.println("All tests completed");
	}
}
