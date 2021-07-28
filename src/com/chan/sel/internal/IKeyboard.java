package com.chan.sel.internal;

import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;

/**
 * 
 * @author Chanu Vempati
 *
 */
public interface IKeyboard {
	
	/*** Key event constants ***/
	int KEY_SHIFT = KeyEvent.VK_SHIFT;
	int KEY_CONTROL = KeyEvent.VK_CONTROL;
	int KEY_ALT = KeyEvent.VK_ALT;
	int KEY_ALTGR = KeyEvent.VK_ALT_GRAPH;
	int KEY_INSERT = KeyEvent.VK_INSERT;
	int KEY_HOME = KeyEvent.VK_HOME;
	int KEY_PAGEUP = KeyEvent.VK_PAGE_UP;
	int KEY_PAGEDOWN = KeyEvent.VK_PAGE_DOWN;
	int KEY_END = KeyEvent.VK_END;
	int KEY_DELETE = KeyEvent.VK_DELETE;
	int KEY_BACKSPACE = KeyEvent.VK_BACK_SPACE;
	int KEY_ESCAPE = KeyEvent.VK_ESCAPE;
	int KEY_ENTER = KeyEvent.VK_ENTER;
	int KEY_TAB = KeyEvent.VK_TAB;
	int KEY_CAPSLOCK = KeyEvent.VK_CAPS_LOCK;
	
	//arrow keys
	int KEY_LEFTARROW = KeyEvent.VK_KP_LEFT;
	int KEY_RIGHTARROW = KeyEvent.VK_KP_RIGHT;
	int KEY_DOWNARROW = KeyEvent.VK_KP_DOWN;
	int KEY_UPARROW = KeyEvent.VK_KP_UP;
	
	//function keys
	int KEY_F1 = KeyEvent.VK_F1;
	int KEY_F2 = KeyEvent.VK_F2;
	int KEY_F3 = KeyEvent.VK_F3;
	int KEY_F4 = KeyEvent.VK_F4;
	int KEY_F5 = KeyEvent.VK_F5;
	int KEY_F6 = KeyEvent.VK_F6;
	int KEY_F7 = KeyEvent.VK_F7;
	int KEY_F8 = KeyEvent.VK_F8;
	int KEY_F9 = KeyEvent.VK_F9;
	int KEY_F10 = KeyEvent.VK_F10;
	int KEY_F11 = KeyEvent.VK_F11;
	int KEY_F12 = KeyEvent.VK_F12;

	void keyPress(int keyCode);
	
	void keyPress(Keys key);

	void keyRelease(int keyCode);
	
	void keyRelease(Keys key);
	
	void keyPressAndRelease(int keyCode);
	
	void keyPressAndRelease(Keys keye);

	void typeKey(char p_char);
	
	void typeString(String text);
	
	void shiftPress(int keyCode);
	
	void shiftPress(Keys key);
	
	void ctrlPress(char key);
	
	void ctrlPress(int keyCode);
	
	void ctrlPress(Keys key);
	
	void controlKeyDown();
	
	void controlKeyUp();

}
