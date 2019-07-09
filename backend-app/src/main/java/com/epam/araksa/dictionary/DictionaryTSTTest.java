package com.epam.araksa.dictionary;

import java.util.Arrays;
import java.util.List;

public class DictionaryTSTTest {

	
	public static void main(String[] args) {

		List<String> values=Arrays.asList(new String [] {"saurabh","test", "saurabh"});
		
		Dictionary dc=new DictionaryTernarySearchTreeImpl();
		
		values.forEach(word->dc.insert(word));
		System.out.println("----------");
		dc.printAll();
		
	}
	
}
