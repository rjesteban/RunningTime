/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.graph.core;

public class Reporter {
    GraphMaker gm;
    private String name;
    private final int MAX = 3000;
    int[] x = new int[MAX];
    int[] y = new int[MAX];
    private int size = 0;

    public Reporter(GraphMaker thegm, String thename) {
	gm = thegm;
	name = thename;
	for(int i=0; i<MAX; i++) {
	    x[i] = -1;
	    y[i] = -1;
	}
    }

    public String getName() { return name; }

    public void addPoint(int thex, int they) {
	x[size] = thex;
	y[size] = they;
	size++;
        //System.out.println(x[size - 1] + ", " + y[size - 1]);
	gm.repaint();
    }
    public void addPoint(int thex[], int they[]) {
	for(int i=0; i<size; i++) {
	    x[i] = thex[i];
	    y[i] = they[i];
	}
	gm.plotData(x, y);
    }
    public void clear() {
	for(int i=0; i<size; i++) {
	    x[i] = -1;
	    y[i] = -1;
	}
	size = 0;
	gm.repaint();
    }
    public int getSize() { return size; }
    public int getLastX() { return x[size-1]; }
    public int getLastY() { return y[size-1]; }

}