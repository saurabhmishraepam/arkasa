package lfu;

public class FrequencyNode{

    private int frequencyVal;
    private int frequencySize;
    private DLinkListItems items;
    private FrequencyNode nextFreq, prevFreq;


    public FrequencyNode getNextFreq() {
        return nextFreq;
    }

    public void setNextFreq(FrequencyNode nextFreq) {
        this.nextFreq = nextFreq;
    }

    public FrequencyNode getPrevFreq() {
        return prevFreq;
    }

    public void setPrevFreq(FrequencyNode prevFreq) {
        this.prevFreq = prevFreq;
    }

    public int getFrequencyVal() {
        return frequencyVal;

    }

    public void setFrequencyVal(int frequencyVal) {
        this.frequencyVal = frequencyVal;
    }

    public int getFrequencySize() {
        return frequencySize;
    }

    public void setFrequencySize(int frequencySize) {
        this.frequencySize = frequencySize;
    }

    public DLinkListItems getItems() {
        return items;
    }

    public void setItems(DLinkListItems items) {
        this.items = items;
    }
}
