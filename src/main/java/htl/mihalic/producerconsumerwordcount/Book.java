/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.util.HashMap;

/**
 *
 * @author Dominik
 */
public class Book {
    private String inputFileName;
    private String text;

    public Book(String inputFileName, String text) {
        this.inputFileName = inputFileName;
        this.text = text;
    }
    
    public HashMap<String, Integer> countWords(String word){
        HashMap<String, Integer> something = new HashMap<>();
        
//        if(something.containsKey(word)){
//            int x = something.get(word);
//            something.replace(word, x, x++);
//        } else {
//            something.put(word, 1);
//        }
        for (String string : something.keySet()) {
            if(string.equals(word)){
                int x = something.get(string);
                something.replace(string, x, x++);
            }
        }
        if(!something.containsKey(word)){
            something.put(word, 1);
            int counter = 0;
            System.out.println(counter);
            counter++;
        }
        return something;
    }

    public String getText() {
        return text;
    }
    
    
}