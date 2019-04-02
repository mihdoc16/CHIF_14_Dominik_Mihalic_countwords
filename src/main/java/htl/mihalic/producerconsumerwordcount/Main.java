/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author Dominik
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
//        MyQueue<Book> books = new MyQueue<>();
//        WordsProducer prod = new WordsProducer(books);
//        WordsConsumer cons1 = new WordsConsumer(books);
//        WordsConsumer cons2 = new WordsConsumer(books);
//        WordsConsumer cons3 = new WordsConsumer(books);
//    
//        new Thread(prod, "Producer").start();   
//        new Thread(cons1, "Consumer 1").start();
//        new Thread(cons2, "Consumer 2").start();
//        new Thread(cons3, "Consumer 3").start();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files\\test.txt")));
        String helper, text = "";
        helper = br.readLine();
        while(helper != null){
            text += helper;
            helper = br.readLine();
        }
        
//        String[] parts = text.split("\\s+");
//        for (int i = 0; i < parts.length; i++) {
//            parts[i] = parts[i].substring(0, 1) + parts[i].substring(1, parts[i].length()-1).replaceAll("$[,;.:-_*+~#'`´^°!]", "");
//            System.out.println(parts[i]);
//        }

        HashMap<String, Integer> test = new HashMap<>();
        Book b = new Book("1jcfs10.txt", text);
        String[] parts = b.getText().split("[\\s+.,:;()-_*#+~^°]");
        for (int i = 0; i < parts.length; i++) {
//                for (String countWord : b.countWords(parts[i]).keySet()) {
//                    String key = countWord;
//                    String value = String.valueOf(b.countWords(parts[i]).get(countWord));
//                    System.out.println(key + " " + value);
//                }
//                parts[i] = parts[i].replaceAll("^[A-Za-z]+", "");
            test.put(parts[i], b.countWords(parts[i]).get(parts[i]));
        }
        for (String string : test.keySet()) {
            String key = string;
            String value = String.valueOf(test.get(string));
            System.out.printf("%s: " + key + " " + value + "\n", Thread.currentThread().getName());
        }


//        test.put("Test", 3);
//        
//        for (String string : test.keySet()) {
//            String key = string;
//            String value = String.valueOf(test.get(string));
//            System.out.println(key + " " + value);
//        }
        
//        List<String> f;
//
//        File[] files = new File("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files").listFiles();
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i].getName());
//        f = Files.readAllLines(Paths.get("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files\\1jcfs10.txt"));
//        System.out.println(f.toString());
//        }
    }
   
}
