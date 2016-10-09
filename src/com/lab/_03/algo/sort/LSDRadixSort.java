/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.algo.sort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author rj
 */
public class LSDRadixSort extends RadixSort {
    private Map <Integer, Queue<Integer>> bucket;
    
    public LSDRadixSort () {
        super();
        initialize();
    }
    
    private void initialize() {
        bucket = new HashMap<Integer, Queue<Integer>>();
        for (int i = 0; i < DIGITS; i++) {
            bucket.put(i, new LinkedList<Integer>());
        }
    }

    @Override
    public void sort() {
        //PASSING part
        for (int max = getMax(), exp = 1; max / exp > 0; exp *= 10) {
            
            // assign to buckets
            while(!ar.isEmpty()) {
                int num = ar.remove(0);
                bucket.get((num / exp) % 10).add(num);
            }
            
            // remove first and add back to integer
            for (int i = 0; i < 10; i++) {
                while(!bucket.get(i).isEmpty()) {
                    ar.add(bucket.get(i).remove());
                }
            }
        }
    }
    
}
