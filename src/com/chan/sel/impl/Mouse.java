package com.chan.sel.impl;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import com.chan.sel.internal.IMouse;
import com.thoughtworks.selenium.Selenium;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class Mouse implements IMouse{
	
	@SuppressWarnings("unused")
	private Selenium environment;
	private int lastX = 0;
	private int lastY = 0;
	private Robot robotTest;
	static long lastClickTime = 0; //Double click protection
	
	private Speed speed;

	public Mouse()
	{
		 try
		 {
			 robotTest = new Robot();
		 }
		 catch (AWTException e) {
			// TODO: handle exception
		}
	}

	public Mouse(Selenium environment) 
	{
		 this.environment = environment;
		 try
		 {
			 robotTest = new Robot();
		 }
		 catch (AWTException e) {
			// TODO: handle exception	
		}
	}
	
	
	@Override
	public void click() 
	{
		//Protect normal click against a double click when it comes too fast;
		long delta = System.currentTimeMillis() - lastClickTime;
		if ( delta < 1000 )
		{
			try { Thread.sleep(1000 - delta); } catch (InterruptedException e) {}
		}
		robotTest.mousePress(InputEvent.BUTTON1_MASK);
		robotTest.mouseRelease(InputEvent.BUTTON1_MASK);
		lastClickTime = System.currentTimeMillis();
	}

	

	@Override
	public void doubleClick() {
		robotTest.mousePress(InputEvent.BUTTON1_MASK);
		robotTest.mouseRelease(InputEvent.BUTTON1_MASK);
		robotTest.mousePress(InputEvent.BUTTON1_MASK);
		robotTest.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void pressLeftButton() {
		robotTest.mousePress(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void releaseLeftButton() {
		robotTest.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void rightClick() {
		robotTest.mousePress(InputEvent.BUTTON3_MASK);
		robotTest.mouseRelease(InputEvent.BUTTON3_MASK);	
	}

	private List<Point> createMousePath(int xStart,int yStart,int xEnd,int yEnd)
	{
		List<Point> path = new ArrayList<Point>();
		Point start = new Point(xStart,yStart);
		Point end = new Point(xEnd, yEnd);
		path.add(start);
		
		List<Point> subPath;
		Point subEnd;
		Point subStart;
		
		int stepsOfOne = 5;
		int stepsOfFive = 3;
		int stepsRest = 5;
		
		if (start.distance(end) > stepsOfOne*2)
		{
			// move 10 times 1 pixel
			subEnd = getPointFromDistance(start,end,stepsOfOne);
			subPath = createMousePath(start,subEnd,stepsOfOne);
			path.addAll(subPath);
			
			if (start.distance(end) > (stepsOfOne*2)+(stepsOfFive*5*2))
			{
				// move 5 times 5 pixels
				subStart = path.get(path.size()-1);
				subEnd = getPointFromDistance(subStart,end,stepsOfOne+(stepsOfFive*5));
				subPath = createMousePath(subStart,subEnd,stepsOfFive);
				path.addAll(subPath);
				
				// (move distance-(One*2 + Five*2) pixels in 10 steps) !> distance
				subStart = path.get(path.size()-1);
				subEnd = getPointFromDistance(start,end,start.distance(end)-(stepsOfOne+(stepsOfFive*5)));
				subPath = createMousePath(subStart,subEnd,stepsRest);
				path.addAll(subPath);
				
				// move 5 times 5 pixels
				subStart = path.get(path.size()-1);
				subEnd = getPointFromDistance(start,end,start.distance(end)-stepsOfOne);
				subPath = createMousePath(subStart,subEnd,stepsOfFive);
				path.addAll(subPath);				
			}
			else
			{
				// (move X pixels in steps of 5) !> distance
				subStart = path.get(path.size()-1);
				subEnd = getPointFromDistance(start,end,start.distance(end)-stepsOfOne);
				subPath = createMousePath(subStart,subEnd,(int)Math.floor(subStart.distance(subEnd)/5));
				path.addAll(subPath);				
			}
			// move 10 times 1 pixel
			subStart = path.get(path.size()-1);
			subPath = createMousePath(subStart,end,stepsOfOne);
			path.addAll(subPath);
		}
		else
		{
			subPath = createMousePath( start, end, (int)Math.ceil(start.distance(end)));
			path.addAll(subPath);
		}
		
		return path;
	}
	
	private Point getPointFromDistance(Point start, Point end, double distance)
	{
		double x = ((end.x - start.x) * (distance / start.distance(end))) + start.x;
		double y = ((end.y - start.y) * (distance / start.distance(end))) + start.y;
		return new Point( (int)x, (int)y);
	}

	private List<Point> createMousePath(Point start, Point end, int steps)
	{
		List<Point> path = new ArrayList<Point>();
		
		double xStep = (start.x-end.x) / (double)steps;
		double yStep = (start.y-end.y) / (double)steps;

		for (int i=0;i<=steps;i++)
		{
			Point p = new Point((int)(start.x-i*xStep),(int)(start.y-i*yStep));
			// if I end up at or further then the actual end point:
			if (start.distance(p) > start.distance(end) || p.equals(end))
			{
				// break out of the loop
				break;
			}
			path.add(p);
		}
		
		// add the end point to make sure we always end correctly
		path.add(end);
		return path;
	}

	
	/**
	 * Moves the mouse to from (xStart,yStart) to (xEnd,yEnd).
	 * @param xStart
	 * @param yStart
	 * @param xEnd
	 * @param yEnd
	 * @param delay
	 */
	private void mouseMove(int xStart,int yStart,int xEnd,int yEnd,int delay)
	{
		//first Go to start
		robotTest.mouseMove(xStart,yStart);
		
		List<Point> path = createMousePath(xStart,yStart,xEnd,yEnd);
		for( Point point : path )
		{
			robotTest.delay(delay);
			robotTest.mouseMove(point.x, point.y);
		}
		
		// remember last location
		lastX = xEnd;
		lastY = yEnd;
	}
	
	

	@Override
	public void moveTo(int xEnd,int yEnd)
	{
		int xStart = lastX;
		int yStart = lastY;
		
		int delay = this.getSpeed().getDelay();
		
		mouseMove(xStart,yStart,xEnd,yEnd,delay);
	}
	
	public void mouseMove(int xEnd,int yEnd)
	{
		moveTo(xEnd, yEnd);
	}
	
	@Override
	public void moveRelative(int x, int y)
	{
		int xStart = lastX;
		int yStart = lastY;
		
		int delay = this.getSpeed().getDelay();
		mouseMove(xStart,yStart,xStart+x,yStart+y,delay);
	}

	@Override
	public Speed getSpeed()
	{
		if(this.speed == null)
		{
			this.setSpeed(Speed.MEDIUM);
		}
		return this.speed;
	}

	@Override
	public void setSpeed(Speed speed)
	{
			this.speed = speed;
	}
	@Override
	public void mouseWheel(int wheelAmt)
	{
		robotTest.mouseWheel(wheelAmt);
	}
	
}
