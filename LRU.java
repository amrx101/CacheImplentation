/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
import java.util.*;
public class LRU {
    private HashMap<Integer, DoubleNode> myMap;
    private Integer size;
    private DoubleNode head;
    private DoubleNode tail;
    private int count;
    
    public LRU(int size){
        myMap = new HashMap();
        this.size = size;
        count = 0;
    }
    
    public int getPage(int key){
        int retVal = -1;
        // check if the page is in cache
        if(myMap.containsKey(key)){
            DoubleNode node = myMap.get(key);
            retVal = node.value;
            remove(node);
            setHead(node);
            
        }
        return retVal;
    }
    private void setHead(DoubleNode node){
        if(head == null){
            head = node;
            tail = head;
        }
        else{
            node.next = head;
            head.previous = node;
            head = node;
        }
    }
    public void insertPage(int key, int value){
        DoubleNode page = new DoubleNode(key,value); // create a page Node
        /*
         * we are going to insert a page into the cache
         * so we must first check if there is space in the cache
         * if not remove the last page of the cache
         */
        // removal of page
        if(count==size){
            tail = tail.previous;
            count -= 1;
        }
        /*
         * now, lets add page 
         */
        //first  add page into hashTable
        myMap.put(key, page);
        // second , add page at the Head 
        setHead (page);
        // increment count 
        count += 1;
        
    }
    
    private void remove(DoubleNode node){
        DoubleNode prev = node.previous;
        DoubleNode next = node.previous;
        // if the node to be deleted is the head itself
        if(prev == null){
            // if there were other elements in the cache
            if(head.next != null){
                head = head.next;
            }
                    // there was only one element in the cache
            else{
                head = null;
            }
        }
        // if the page to be deleted was the end
        if(next == null){
            tail = tail.previous;
            
           // tail = tail.previous;
        }
        prev.next = next;
        next.previous = prev;
    }
    
    
    // inner class
    private class DoubleNode{
        private int key;
        private int value;
        private DoubleNode next;
        private DoubleNode previous;
        
        public DoubleNode(int key,int value){
            this.key = key;
            this.value = value;
            next = null;
            previous = null;
        }    
    }
    
}

