/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.graph.core;

import java.awt.*;
import java.lang.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class GraphMaker extends Canvas implements Observer {

    private Vector reps = new Vector();
    private int repcount = 0;
    private int xmax, ymax; // the real value, not the x,y coord on screen
    public void setYMax(int newval) {ymax = newval;}
    private int xm, ym;
    private int w, h; // iternal value of width and length. without margin
    private int tmargin, bmargin, lmargin, rmargin;
    private boolean hasGrid;


    // x and y coefficients used to match the function curves
    // need to consider make a new class for it.
    // support classes: XCoeffListener and YCoeffListener
    private int xcoeff=1, ycoeff=1;
    public void setXCoeff(int newval) { xcoeff = newval; }
    public void setYCoeff(int newval) { ycoeff = newval; }
    private int[] lines = new int[5];

    public GraphMaker(int thexmax, int theymax) {
	super();
	setSize(400, 400); // 20 is for margin
	w = 300;
	h = 300;
	tmargin = 20;
	rmargin = 40;
	lmargin = 50;
	bmargin = 30;
	hasGrid = false;
	xm = thexmax;
	ym = theymax;
	//xm.getObservable().addObserver(this);
	//ym.getObservable().addObserver(this);
	xmax = thexmax;
	ymax = theymax;
	setBackground(Color.white);
    }

    private void setw() {w = getSize().width - lmargin - rmargin;}
    private void seth() {h = getSize().height - bmargin - tmargin;}
    private void setwh() { setw(); seth(); }

    /*
    class graphCompListener extends ComponentAdapter {
	public graphCompListener() { super(); }
	public void componentResized(ComponentEvent e) {
	    w = getSize().width - margin;
	    h = getSize().height - margin;
	}
    }
    */

    public void update(Observable o, Object arg) {
	// the problem is there are multiple observable out there!!!
	//clear();
	ymax = ym;
	//Graphics g = getGraphics();
	//paint(g);
	repaint();
    }

    public void drawAxis(Color c) {
	Graphics g = getGraphics();
	g.setColor(c);
	setwh();
	// draw x axis
	g.drawLine( lmargin/2, h+tmargin, getSize().width-rmargin, h+tmargin);
	g.drawString("Size", getSize().width-rmargin+3, h+tmargin+2);
	//g.drawLine( getSize().width-rmargin-5, h+tmargin-2, getSize().width-rmargin, h+tmargin);
	//g.drawLine( getSize().width-rmargin-5, h+tmargin+2, getSize().width-rmargin, h+tmargin);
	for(int i=1; i<11; i++) {
	    g.drawLine( i*w/10+lmargin, h-5+tmargin, 
			i*w/10+lmargin, h+tmargin);
	    Integer xval = new Integer(i*xmax/10);
	    g.drawString(xval.toString(), i*w/10+lmargin-15, h+10+tmargin);
	}
 
	// draw y axis
	g.drawLine( lmargin, tmargin, lmargin, h+bmargin/2+tmargin);
	g.drawString("Time(ms)", lmargin-8, tmargin-3);
	for(int i=1; i<11; i++) {
	    g.drawLine( lmargin, h-i*h/10+tmargin, 
			lmargin+5, h-i*h/10+tmargin);
	    Integer yval = new Integer(i*ymax/10);
	    g.drawString(yval.toString(), 12, h-i*h/10+tmargin+5);
	}	
    }

    public void addGrid() {
	hasGrid = true;
    }
    public void drawGrid(Color c) {
	if(!hasGrid) return;
	Graphics g = getGraphics();
	if (g == null) return;
	g.setColor(c);
	for(int i=1; i<11; i++) {
	    g.drawLine( lmargin, h-i*h/10+tmargin, 
			lmargin+w, h-i*h/10+tmargin);
	    g.drawLine( i*w/10+lmargin, tmargin, 
			i*w/10+lmargin, h+tmargin);
	}
    }

    public void plotData(int x, int y) {
	Graphics g = getGraphics();
	if (g == null) return;
	if(y/ymax < h)
	    g.fillRect(x*w/xmax-2+lmargin, h-y/ymax-2+tmargin, 2, 2);
    }
    public void clearData(int x, int y) {
	Graphics g = getGraphics();
	Color c = g.getColor();
        g.setColor(getBackground());
	if(y/ymax < h)
	    g.fillRect(x*w/xmax-2+lmargin, h-y/ymax-2+tmargin, 2, 2);
	g.setColor(c);
    }

    public void plotData(int x[], int y[]) {
	Graphics g = getGraphics();
	for(int i=0; i<300; i++) { // change 300 later? 
	    if(x[i] != -1)
		if(y[i]/ymax<h) {
		    g.fillRect(x[i]*w/xmax-2+lmargin, 
			       h-y[i]/ymax-2+tmargin, 2, 2);
		    
		}
	}
    }

    public void plotData(Color c, int x[], int y[]) {
	Graphics g = getGraphics();
	g.setColor(c);
	for(int i=0; i<300; i++) { // change 300 later? 
	    if(x[i] != -1)
		if(y[i]/ymax<h) {
		    g.fillRect((x[i]*w)/xmax-2+lmargin, 
			       h-(y[i]*h)/ymax-2+tmargin, 2, 2);
		    
		}
	}
    }
    /*
    public void repaint() {
	clear();
	setwh();
	Graphics g = getGraphics();
	paint(g);
    }
    */

    public void clear() {
	Graphics g = getGraphics();
	Color c = g.getColor();
        g.setColor(getBackground());

	drawGrid(Color.white);
	drawAxis(Color.white);

	for(int i=0; i<repcount; i++) {
		Reporter rep = (Reporter) reps.elementAt(i);
		plotData(Color.white, rep.x, rep.y);
		if( rep.getSize() > 0)
		    g.drawString( rep.getName(), 
				  (rep.getLastX())*w/xmax-10+lmargin,
				  h-(rep.getLastY()*h)/ymax-10+tmargin);
	}
	for(int i=0; i<5; i++)
	    if(lines[i]!=0) drawLine(i, Color.white);	

	g.setColor(c);
    }

    public void paint(Graphics g) {
	setwh();
	drawGrid(Color.lightGray);
	drawAxis(Color.black);

	g.drawRect(2,2,getSize().width-3,getSize().height-3);
	for(int i=0; i<repcount; i++) {
		Reporter rep = (Reporter) reps.elementAt(i);
		Color c = Color.black;
		if (i == 0) c = Color.red;
		else if(i == 1) c = Color.blue;
		else if(i == 2) c = Color.green;
		else if(i == 3) c = Color.cyan;
		else if(i == 4) c = Color.pink;
		//else g.setColor(Color.black);
		plotData(c, rep.x, rep.y);
		if( rep.getSize() > 0)
		    g.drawString( rep.getName(), 
				  (rep.getLastX())*w/xmax-10+lmargin,
				  h-(rep.getLastY())*h/ymax-10+tmargin);
	}
	for(int i=0; i<5; i++)
	    if(lines[i]!=0) drawLine(i, Color.black);
    }

    public Reporter getReporter(String name) {
	Reporter newrep = new Reporter(this, name);
	reps.addElement(newrep);
	repcount++;
	// error handle? if repcount > 10?
	return newrep;
    }

    public void makeTable() {
	File tablefile = new File("data.html");
	try {
	FileWriter out = new FileWriter(tablefile);
	//} catch(IOException e) {}
	//if(tablefile.exists());

	String tmp;

	tmp = "<html>";
	out.write(tmp, 0, tmp.length());

	tmp = "<center><h3>Output Table</h3>";
	out.write(tmp, 0, tmp.length());
	tmp = "<table border=1>";
	out.write(tmp, 0, tmp.length());
	tmp = "<tr><td>Size";
	out.write(tmp, 0, tmp.length());
	
	// write first row
	//for(int j=0; j<300; j++) {
	    //if(reps[0].x[j] != -1) {
		//tmp = new String("<td>"+reps[0].x[j]);
		//out.write(tmp, 0, tmp.length());
	    //}
	//}
	tmp = "\n";
	out.write(tmp, 0, tmp.length());
	
	for(int i=0; i<repcount; i++) {
		Reporter rep = (Reporter) reps.elementAt(i);
		tmp = "<tr><td>"+rep.getName();
		out.write(tmp, 0, tmp.length());
		for(int j=0; j<300; j++) {
		    if(rep.y[j] != -1) {
			// write data
			tmp = "<td>"+rep.y[j];
			out.write(tmp, 0, tmp.length());
		    }
		}
		tmp = "\n";
		out.write(tmp, 0, tmp.length());
	}
	tmp = "</table></center>";
	out.write(tmp, 0, tmp.length());
	tmp = "</html>";
	out.write(tmp, 0, tmp.length());	
	out.close();
	} catch(IOException e) {}
    }

    // draw function lines
    public final static int logx = 1;
    public final static int x = 2;
    public final static int xlogx = 3;
    public final static int xx = 4;

    public void addLine(int i) {
	lines[i]++;
	drawLine(i, Color.black);
    }

    public void drawLine(int i, Color c) {
	Graphics g = getGraphics();
	g.setColor(c);

	// the real function value of x and y
	// ymax can change
	// x : 0..10, y: 0..ymax
	double x, y;
	double xpre=0, ypre=0;
	double xmin = 0.05, ymin =0;

	// the x and y on the screen
	// x: 0..xscrmax, y:0..yscrmax
	int xscr, yscr;
	// hard coded for now......
	double xscrmax = w, yscrmax = h; // min is 0 as default
	int xscrpre=0, yscrpre=(int)yscrmax;// start from left bottom

	// try this .............
	if (xcoeff < xmax)
	    xmax = xmax/xcoeff;

	// scale the picture
	double xstep = ((double)xmax - xmin)/xscrmax; // (max-min)/number of points
	double xscale = xscrmax/xmax;
	double yscale = yscrmax/ymax;

	for(x=xmin; x<xmax; x+=xstep) {
	    //x = x*xcoeff;
	    switch(i) {
	    case 1:
		//g.setColor(Color.red);
		y = Math.log(x)/ycoeff/5;
		break;
	    case 2:
		//g.setColor(Color.green);
		y = x/ycoeff/5;
		break;
	    case 3:
		//g.setColor(Color.cyan);
		y = x*Math.log(x)/ycoeff/5;
		break;
	    case 4: 
		//g.setColor(Color.blue);
		y = x*x/ycoeff/5;
		break;
	    default:
		y = x/ycoeff/5;
	    }
	    // convert from real to on screen unit
	    xscr = (int)Math.round(x*xscale);
	    yscr = (int)Math.round(yscrmax-y*yscale);
	    if(0<yscr&& yscr<h)
		g.drawLine(xscrpre+lmargin, yscrpre+tmargin, 
			   xscr+lmargin, yscr+tmargin);
	    xscrpre = xscr;
	    yscrpre = yscr;
	    xpre = x;
	    ypre = y;
	}

	// a hack to restore the old xmax
	if (xcoeff < xmax)
	    xmax = xmax*xcoeff;
    }
}
