package lfu;

public class DLinkListFreq {

    private int size;

    private FrequencyNode start, end;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FrequencyNode getStart() {
        return start;
    }

    public void setStart(FrequencyNode start) {
        this.start = start;
    }

    public FrequencyNode getEnd() {
        return end;
    }

    public void setEnd(FrequencyNode end) {
        this.end = end;
    }

    public synchronized void add(FrequencyNode f) {

        if (start == null && end == null) {


            start = f;
            end = f;
            size++;

        } else {

            end.setNextFreq(f);
            f.setPrevFreq(f);
            size++;
        }


    }

    public synchronized void delete(FrequencyNode f) {

        if (f.getPrevFreq() == null && start == f) {

            start = f.getNextFreq();
            f.setPrevFreq(null);
            size--;

        } else {
            f.getPrevFreq().setNextFreq(f.getNextFreq());

            f.getNextFreq().setPrevFreq(f.getPrevFreq());
            size--;
        }


    }


}
