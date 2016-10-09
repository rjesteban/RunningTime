/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.algo.sort;

/**
 *
 * @author rj
 */
public class SortingTest {
    
    public static void main(String[] args) {
        RadixSort lrs = new LSDRadixSort();
        lrs.ar.add(0);
        lrs.ar.add(2);
        lrs.ar.add(5);
        lrs.ar.add(8);
        lrs.ar.add(5);
        lrs.ar.add(6);
        lrs.ar.add(6);
        lrs.ar.add(34827);
        lrs.ar.add(434343);
        lrs.ar.add(66443433);
        lrs.ar.add(6);
        lrs.ar.add(6);
        lrs.ar.add(6);
        lrs.sort();
        lrs.display();
    }
    
}
