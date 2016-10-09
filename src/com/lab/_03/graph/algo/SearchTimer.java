/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.graph.algo;

import com.lab._03.algo.search.Search;
import com.lab._03.graph.core.Reporter;
import com.lab._03.graph.core.TaskTimer;


/**
 *
 * @author KurtJunsheanEspinosa
 */




class SearchTimer extends TaskTimer {
  Search algo;
//  private int[] keys;
  
  public SearchTimer(Reporter out, Search algo) {
    super(out);
    this.algo = algo;
  }
  
  @Override
  public void initialize(int n) {
    algo.clear();
    for (int i = 0; i < n; i++) {
        algo.insert(i);
    }
  }
  
  @Override
  public void doTask(int n) {
    initialize(n);
    
    algo.search(n);
  }
}
