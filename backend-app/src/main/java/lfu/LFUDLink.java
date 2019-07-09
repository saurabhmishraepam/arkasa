package lfu;

import java.util.HashMap;
import java.util.Map;

public class LFUDLink {

    private Map<Integer,ValuesNode> values=new HashMap<>();
    private DLinkListFreq frequencies=new DLinkListFreq();



    private void put(int key, int val){

        if(!values.containsKey(key)){
            if(frequencies.getStart().getFrequencyVal()==1){
                Node n=new Node();
                n.setVal(val);



            }


        }else{



        }


    }

    private int get(int key){

        return -1;
    }

    private void print(){




    }







}
