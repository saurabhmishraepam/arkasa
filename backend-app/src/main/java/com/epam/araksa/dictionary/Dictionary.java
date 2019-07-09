package com.epam.araksa.dictionary;

import java.util.List;

public interface Dictionary {

	void insert(String word);
	boolean search(String word);
	void delete(String word);
	boolean isEmpty();
	int size();
	
	List<String> printAll() ;
}
