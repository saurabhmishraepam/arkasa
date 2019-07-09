package com.epam.araksa.dictionary;

import java.util.List;

public class DictionaryTernarySearchTreeImpl implements Dictionary {

	private TSTNode root;
	private int wordCount;

	@Override
	public void insert(String word) {

		char[] charSet = word.toLowerCase().toCharArray();
		root=insert(root,charSet,0);

	}

	private TSTNode insert(TSTNode node, char[] charSet, int counter) {
		if (counter < charSet.length) {
			char charToInsert = charSet[counter];
			System.out.println(charToInsert);
			boolean isLeaf = charSet.length - 1 == counter;
			if (node == null) {
				node = new TSTNode(charToInsert, isLeaf);
				insert(node,charSet,counter);
			} else {
				if (node.getVal() == charToInsert) {
					if (node.getMiddle() == null)
						node.setMiddle(insert(node.getMiddle(), charSet, counter + 1));
					else if (charToInsert < node.getVal()) {
						node.setLeft(insert(node.getLeft(), charSet, counter + 1));
					} else {
						node.setRight(insert(node.getRight(), charSet, counter + 1));
					}
				}else {
					
					if(charToInsert<node.getVal()) {
						node.setLeft(insert(node.getLeft(), charSet, counter));
					} else {
						node.setRight(insert(node.getRight(), charSet, counter ));
					}
					
				}

			}
			

		}

		return node;
	}

	@Override
	public boolean search(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String word) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> printAll() {
		
		print(root,0,null);
		
		
		return null;
	}
	
	private void print(TSTNode node, int child, TSTNode parent) {
		if(node!=null) {
			String s="middle";
			switch (child) {
			case 0:
				s="left";
				break;
			case 1:
				s="middle";
				break;
			case 2:
				s="right";
				break;
			}
			System.out.println(s+"  "+node.getVal()+" parent "+parent);
			print(node.getLeft(), 0, node);
			print(node.getMiddle(),1, node);
			print(node.getRight(),2, node);
			
		}
		
	}

}
