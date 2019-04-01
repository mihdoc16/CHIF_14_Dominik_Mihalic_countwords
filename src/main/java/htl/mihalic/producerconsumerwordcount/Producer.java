/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class Producer implements Runnable{
    private MyQueue<Book> books;
    private List<String> f;

    public Producer(MyQueue<Book> books) {
        this.books = books;
    }
    
    @Override
    public void run() {
        while(true){
            File[] files = new File("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files").listFiles();
            Book b;
            synchronized(books){
                for (int i = 0; i < files.length; i++) {
                    try {
                        f = Files.readAllLines(Paths.get("D:\\Schulordner\\POS Stuff\\ProducerConsumerWordCount\\files\\"+files[i].getName()));
                    } catch (IOException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   b = new Book(files[i].getName(), f.toString());
                    try {
                        books.put(b);
                        books.notifyAll();
                    } catch (FullException ex) {
                        try {
                            books.wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
        }
    }
    
}
