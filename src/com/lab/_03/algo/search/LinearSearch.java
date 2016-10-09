/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.algo.search;

import java.util.ArrayList;

/**
 *
 * @author KurtJunsheanEspinosa
 */
public class LinearSearch implements Search{

    ArrayList ar = new ArrayList();
    
    @Override
    public boolean search(Object o) {
        for (int i = 0; i < ar.size(); i++) {
            if(ar.get(i).equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(Object o) {
        ar.add(o);
    }

    @Override
    public void clear() {
        ar.clear();
    }
    
}
