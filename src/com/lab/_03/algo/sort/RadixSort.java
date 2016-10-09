/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.algo.sort;

import java.util.ArrayList;

/**
 *
 * @author rj
 */
public abstract class RadixSort {
    protected ArrayList<Integer> ar;
    protected final int DIGITS = 10;
    
    public RadixSort() {
        ar = new ArrayList<Integer>();
    }
    
    public abstract void sort();
    
    public void insert (int i) {
        ar.add(i);
    }
    
    public void clear() {
        ar.clear();
    }
    
    
    public int getMax() {
        int max = ar.get(0);
        for (int i = 1, n = ar.size(); i < n; i++) {
            if (max < ar.get(i))
                max = ar.get(i);
        }
        return max;
    }
    
    public void display() {
        for (int i = 0, n = ar.size(); i < n; i++) {
            System.out.print(ar.get(i) + " ");
        }
        System.out.println("");
    }
}
