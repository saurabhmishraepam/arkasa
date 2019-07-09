package com.epam.araksa.dictionary;

import java.util.Arrays;
import java.util.List;

public class DictionaryTest {
	
	public static void main(String[] args) {
		
		List<String> values=Arrays.asList(new String [] {"saurabh", "check", "all","values", "saurabh", "Saurabh"});
		
		Dictionary dc=new DictionaryTriesImpl();
		
		values.forEach(word->dc.insert(word));
		
		System.out.println(dc.printAll());
		
		values=Arrays.asList(new String [] {"saurabhuuu", "check", "all","values", "saurabh", "Saurabh"});
		
		values.forEach(word->System.out.println(dc.search(word)));
		
		dc.delete("saurabh");
		System.out.println(dc.printAll());
	}
	
	

}
