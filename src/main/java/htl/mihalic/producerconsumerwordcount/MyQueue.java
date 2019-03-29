/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.mihalic.producerconsumerwordcount;

import java.util.LinkedList;

/**
 *
 * @author Dominik
 */
public class MyQueue<T> {
    private final LinkedList<T> books = new LinkedList<>();
    private final int maxSize = 3;

    public MyQueue() {
 
    }
    
    public void put(T book) throws FullException{
        if(books.size() >= maxSize){
            throw new FullException();
        }
        books.add(book);
    }
    
    public T get() throws EmptyException{
        if(books.isEmpty()){
            throw new EmptyException();
        }
        
        return books.poll();
    }
}
