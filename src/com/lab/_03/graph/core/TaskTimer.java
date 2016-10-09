/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.graph.core;


public abstract class TaskTimer
{

    public TaskTimer(Reporter ioutput) { output = ioutput; }

    private Reporter output;

    public void initialize(int i) { } // default is nothing

    public abstract void doTask(int i); // { } // override this method

    public void run(int start, int stop, int step)
    {
      for(int i = start; i <= stop; i += step) {
          initialize(i);
          System.gc();
          long starttime = System.currentTimeMillis();
          doTask(i);
          long stoptime = System.currentTimeMillis();
          int time = (int) (stoptime - starttime);
          output.addPoint(i, time);
      }
    }
}