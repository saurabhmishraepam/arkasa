package lfu;

public class ValuesNode {

    private int val;
    private Node itemNode;
    private FrequencyNode frequencyNode;
    private int maxCap;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getItemNode() {
        return itemNode;
    }

    public void setItemNode(Node itemNode) {
        this.itemNode = itemNode;
    }

    public FrequencyNode getFrequencyNode() {
        return frequencyNode;
    }

    public void setFrequencyNode(FrequencyNode frequencyNode) {
        this.frequencyNode = frequencyNode;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }
}
