package com.chan.sel.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TestMouseActions {

	/**
	 * @param args
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot mouse = new Robot();
		Thread.sleep(4000);
		mouse.mouseMove(400, 400);
		mouse.keyPress(KeyEvent.VK_SHIFT);
		mouse.keyPress(KeyEvent.VK_A);
		mouse.keyRelease(KeyEvent.VK_A);
		mouse.keyRelease(KeyEvent.VK_SHIFT);
	}

}
