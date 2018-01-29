package IteratorTest;

import java.util.HashMap;
import java.util.Iterator;
//import java.util.Map;

public class Iterate {

    private HashMap<String,String> hash = new HashMap<>();
    private Iterator iterator = hash.entrySet().iterator();


    public static void main(String[] args) {
        Iterate iterate = new Iterate();
        iterate.makeHash();
    }

    private void makeHash(){
        hash.put("one","red");
        hash.put("two","blue");
        System.out.println("Just before while loop");
        for(String color : hash.values()){
            System.out.println(color);
        }
    }
}
