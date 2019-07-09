package com.epam.araksa.dictionary;

public class CharNode {

	public CharNode(){}
	public CharNode(char c){
		this.c=c;
	}
	
	public CharNode(char c, boolean isLeaf){
		this.c=c;
		this.isLeafNode=isLeaf;
	}
	
	private int MAX_CHAR=26;
	private CharNode [] childs=new CharNode[MAX_CHAR];
	
	private char c;
	
	private boolean isLeafNode=false;

	public boolean isLeafNode() {
		return isLeafNode;
	}

	public void setLeafNode(boolean isLeafNode) {
		this.isLeafNode = isLeafNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharNode other = (CharNode) obj;
		if (c != other.c)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CharNode [c=" + c + "]";
	}

	public CharNode[] getChilds() {
		return childs;
	}

	public void setChilds(CharNode[] childs) {
		this.childs = childs;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
	
	
	
	
	
}
