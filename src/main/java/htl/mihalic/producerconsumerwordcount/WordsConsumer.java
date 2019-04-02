/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class WordsConsumer implements Runnable{
    private MyQueue<Book> books;
    private HashMap<String, Integer> words = new HashMap<>();

    public WordsConsumer(MyQueue<Book> books) {
        this.books = books;
    }
    
    @Override
    public void run() {
        while(true){
            System.out.println("Im Consumer kommt rein");
            Book b = null;
            synchronized(books){
                try {
                    b = books.get();
                    books.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(WordsConsumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    continue;
                }
            }
            String[] parts = b.getText().split("\\s+");
            for (int i = 0; i < parts.length; i++) {
//                for (String countWord : b.countWords(parts[i]).keySet()) {
//                    String key = countWord;
//                    String value = String.valueOf(b.countWords(parts[i]).get(countWord));
//                    System.out.println(key + " " + value);
//                }
//                parts[i] = parts[i].replaceAll("^[A-Za-z]+", "");
                words.put(parts[i], b.countWords(parts[i]).get(parts[i]));
            }
            for (String string : words.keySet()) {
                String key = string;
                String value = String.valueOf(words.get(string));
                System.out.printf("%s: " + key + " " + value + "\n", Thread.currentThread().getName());
            }
        }
    }
    
}
