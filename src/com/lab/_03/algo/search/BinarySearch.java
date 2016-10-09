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
public class BinarySearch implements Search{
    ArrayList ar = new ArrayList();
    
    @Override
    public boolean search(Object o) {
        int low = 0; 
        int high = ar.size()-1;
        int i = new Integer(o.toString());
        
        while(low<=high){
            int mid = (low+high)/2;
            int k = new Integer(ar.get(mid).toString());
            if(i<k){
                high = mid-1;
            }
            else if(i>k){
                low = mid+1;
            }
            else
                return true;
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
