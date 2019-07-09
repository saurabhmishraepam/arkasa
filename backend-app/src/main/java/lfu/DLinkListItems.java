package lfu;

public class DLinkListItems {

    Node start, end;
    private int size;


    public synchronized void add(Node node) {
        if (end == null && start == null) {

            start = node;
            end = node;
            size++;
        } else {
            end.setNext(node);
            node.setPrevious(end);
            size++;
        }
    }

    public synchronized void delete(Node node) {

        if (node.getPrevious() == null && start == node) {

            start = node.getNext();
            node.setPrevious(null);
            size--;

        } else {
            node.getPrevious().setNext(node.getNext());

            node.getNext().setPrevious(node.getPrevious());
            size--;
        }
    }


}
