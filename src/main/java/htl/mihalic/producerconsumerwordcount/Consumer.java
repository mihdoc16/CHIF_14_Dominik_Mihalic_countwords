/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class Consumer implements Runnable{
    private MyQueue<Book> books;

    public Consumer(MyQueue<Book> books) {
        this.books = books;
    }
    
    @Override
    public void run() {
        while(true){
            Book b = null;
            synchronized(books){
                try {
                    b = books.get();
                    books.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    continue;
                }
            }
            String[] parts = b.getText().split(" ");
            for (int i = 0; i < parts.length; i++) {
                b.countWords(parts[i]);
            }
        }
    }
    
}
