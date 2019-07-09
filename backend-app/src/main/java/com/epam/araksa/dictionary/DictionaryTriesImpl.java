package com.epam.araksa.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryTriesImpl implements Dictionary {

	private CharNode start = new CharNode('#');
	private int wordCount;

	@Override
	public boolean isEmpty() {
		return wordCount == 0;
	}

	@Override
	public int size() {
		return wordCount;
	}

	@Override
	public void insert(String word) {
		word = word.toLowerCase();
		char[] charSet = word.toCharArray();
		if (insert(start, charSet, 0)) {
			wordCount++;
		}

	}

	public boolean insert(CharNode node, char[] charSet, int currCounter) {

		boolean isInserted = false;
		if (currCounter < charSet.length) {

			char toInsert = charSet[currCounter];
			int index = toInsert - 97;
			boolean isLeaf = currCounter == charSet.length - 1;
			if (!indexBoundCheck(toInsert)) {
				return false;
			}
			if (node.getChilds()[index] == null) {
				isInserted = true;
				node.getChilds()[index] = new CharNode(toInsert, isLeaf);
			}
			node = node.getChilds()[index];
			currCounter++;

			isInserted = insert(node, charSet, currCounter) || isInserted;

		}
		return isInserted;

	}

	private boolean indexBoundCheck(char c) {
		int index = c - 97;
		return index >= 0 && index <= 26;
	}

	@Override
	public boolean search(String word) {
		word = word.toLowerCase();
		char[] charSet = word.toCharArray();
		return search(start, charSet, 0);
	}

	private boolean search(CharNode node, char[] charSet, int counter) {
		boolean isFound = true;
		if (counter < charSet.length) {
			if (node != null) {

				char toSearch = charSet[counter];
				int index = toSearch - 97;
				boolean isLeaf = counter == charSet.length - 1;
				if (!indexBoundCheck(toSearch)) {
					return false;
				}
				if (node.getChilds()[index] != null && node.getChilds()[index].getC() == toSearch) {
					node = node.getChilds()[index];
					isFound = search(node, charSet, counter + 1) && isFound;
				} else {
					isFound = false;
				}

			} else {
				isFound = false;
			}
		}

		return isFound;

	}

	@Override
	public void delete(String word) {

		delete(start, word.toLowerCase().toCharArray(), 0);
	}

	private void delete(CharNode node, char[] charSet, int counter) {

		boolean isFound = true;
		if (counter < charSet.length) {
			if (node != null) {

				char toSearch = charSet[counter];
				int index = toSearch - 97;
				boolean isLeaf = counter == charSet.length - 1;
				if (!indexBoundCheck(toSearch)) {
					return;
				}
				if (node.getChilds()[index] != null && node.getChilds()[index].getC() == toSearch) {

					if (singleChildCheck(node)) {
						node.getChilds()[index] = null;
					} else {
						node = node.getChilds()[index];
						delete(node, charSet, counter + 1);
					}
				}

			}
		}
	}

	private boolean singleChildCheck(CharNode node) {
		int childCount = 0;
		if (node != null) {
			for (CharNode c : node.getChilds()) {
				if (c != null)
					childCount++;
			}
		}
		return childCount <= 1;
	}

	@Override
	public List<String> printAll() {
		List<String> listOfWords = new ArrayList<>();
		print(start, listOfWords, new StringBuilder());
		return listOfWords;
	}

	private void print(CharNode node, List<String> listOfWords, StringBuilder s) {
		if (node != null) {
			s.append(node.getC());
			CharNode[] childs = node.getChilds();

			if (node.isLeafNode()) {
				listOfWords.add(s.toString());

			} else {

				for (CharNode d : childs) {
					print(d, listOfWords, new StringBuilder(s));
				}

			}

		}

	}

}
