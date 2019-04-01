/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

/**
 *
 * @author Dominik
 */
public class Main {
    public static void main(String[] args) {
        MyQueue<Book> books = new MyQueue<>();
        Producer prod = new Producer(books);
        Consumer cons1 = new Consumer(books);
        Consumer cons2 = new Consumer(books);
        Consumer cons3 = new Consumer(books);
    
        new Thread(prod, "Producer").start();   
        new Thread(cons1, "Consumer 1").start();
        new Thread(cons2, "Consumer 2").start();
        new Thread(cons3, "Consumer 3").start();
    }
   
}
