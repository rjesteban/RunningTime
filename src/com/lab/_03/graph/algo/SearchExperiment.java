/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.graph.algo;


import com.lab._03.algo.search.BinarySearch;
import com.lab._03.algo.search.LinearSearch;
import com.lab._03.graph.core.GraphMaker;
import javax.swing.JFrame;

/**
 *
 * @author KurtJunsheanEspinosa
 */
public class SearchExperiment extends JFrame {
     private GraphMaker graph = new GraphMaker(1000000, 100);
  
  
  public static void main(String[] args) {
    SearchExperiment ex = new SearchExperiment();
    ex.show();
    ex.run();
    ex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public SearchExperiment() {
    setTitle("Search Implementation Comparison");
    setSize(900,700);
    add("Center", graph);
  }
  
  public void run() {
    SearchTimer sTime = new SearchTimer(graph.getReporter("LinearSearch"), new LinearSearch());
    sTime.run(0,1000000, 10000);
    SearchTimer rTime = new SearchTimer(graph.getReporter("BinarySearch"), new BinarySearch());
    rTime.run(0,1000000, 10000);
    graph.makeTable();
  }
}
