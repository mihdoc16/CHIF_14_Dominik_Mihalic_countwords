/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class WordsProducer implements Runnable{
    private MyQueue<Book> books;
    private List<String> f;
    private BufferedReader br;
    private String text;
    private String helper;

    public WordsProducer(MyQueue<Book> books) {
        this.books = books;
    }
    
    @Override
    public void run() {
        while(true){
            File[] files = new File("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files").listFiles();
            Book b;
            synchronized(books){
                for (int i = 0; i < files.length; i++) {
//                    try {
//                        f = Files.readAllLines(Paths.get("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files\\"+files[i].getName()));
//                    } catch (IOException ex) {
//                        Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files\\"+files[i].getName())));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        helper = br.readLine();
                        while(helper != null){
                            text += helper;
                            helper = br.readLine();
                            //System.out.println(helper);
                        }
                        System.out.println("Producer hat Text gegeben");
                    } catch (IOException ex) {
                        Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    b = new Book(files[i].getName(), text);
                    try {
                        books.put(b);
                        System.out.println("Buch in die Queue gepackt");
                        books.notifyAll();
                    } catch (FullException ex) {
                        try {
                            System.out.println("Producer wartet. Queue ist voll");
                            books.wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
//            try {
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("something.txt")));
//                
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
}
