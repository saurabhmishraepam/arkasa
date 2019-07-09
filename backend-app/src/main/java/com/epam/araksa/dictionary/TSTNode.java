package com.epam.araksa.dictionary;

public class TSTNode {

	
	public TSTNode() {
	}
	public TSTNode(char c) {
		this.val=c;
	}
	public TSTNode(char c, boolean isLeaf) {
		this.val=c;
		this.isEndOfStr=isLeaf;
	}
	private char val;
	private TSTNode left, middle, right;
	boolean isEndOfStr;
	public boolean isEndOfStr() {
		return isEndOfStr;
	}
	public void setEndOfStr(boolean isEndOfStr) {
		this.isEndOfStr = isEndOfStr;
	}
	public char getVal() {
		return val;
	}
	public void setVal(char val) {
		this.val = val;
	}
	public TSTNode getLeft() {
		return left;
	}
	public void setLeft(TSTNode left) {
		this.left = left;
	}
	public TSTNode getMiddle() {
		return middle;
	}
	public void setMiddle(TSTNode middle) {
		this.middle = middle;
	}
	public TSTNode getRight() {
		return right;
	}
	public void setRight(TSTNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "TSTNode [val=" + val + "]";
	}
	
	
	
}
