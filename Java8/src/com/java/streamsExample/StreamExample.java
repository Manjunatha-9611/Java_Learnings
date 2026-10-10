package com.java.streamsExample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		
		//Collection != Stream
		
		//Creation of Stream object
		//Approach 1
		Stream<List<Integer>> s1 = Stream.of(Arrays.asList(1,2,3,2,14,5,52,23,1,12));
		s1.forEach(System.out::print);
		
		System.out.println();
		System.out.println("=====================================");
		
		//Approach 2
		List<Integer> list = Arrays.asList(1,2,3,2,14,5,52,23,1,12,12,234,45,12);
		Stream<Integer> s2 = list.stream();
		s2.forEach(System.out::println);
		
	}

}
