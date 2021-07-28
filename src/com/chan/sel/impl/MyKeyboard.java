package com.chan.sel.impl;


import static java.awt.event.KeyEvent.VK_BACK_QUOTE;
import static java.awt.event.KeyEvent.VK_BACK_SLASH;
import static java.awt.event.KeyEvent.VK_COMMA;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_EQUALS;
import static java.awt.event.KeyEvent.VK_MINUS;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_QUOTE;
import static java.awt.event.KeyEvent.VK_SEMICOLON;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_SLASH;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;

import com.chan.sel.internal.IKeyboard;
import com.thoughtworks.selenium.Selenium;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class MyKeyboard implements IKeyboard
{
	private Selenium sel;
	private WebDriver driver;
	private Keyboard keyboard;
	
	public MyKeyboard(Selenium sel)
	{
		this.sel = sel;
	}
	
	public MyKeyboard(WebDriver driver)
	{
		this.driver = driver;
		keyboard = ((HasInputDevices)driver).getKeyboard();
	}
	
	@Override
	public void keyPress(int keyCode)
	{
		sel.keyDownNative(String.valueOf(keyCode));	
	}
	
	@Override
	public void keyRelease(int keyCode)
	{
		sel.keyUpNative(String.valueOf(keyCode));
	}
	@Override
	public void keyPressAndRelease(int keyCode)
	{
		sel.keyPressNative(String.valueOf(keyCode));
	}
	
	@Override
	public void typeKey(char p_char)
	{
		int iCode = 0;
		if (p_char >= '0' && p_char <= '9')
		{
			iCode = Character.getNumericValue(p_char) + 48;
			keyPressAndRelease(iCode);
		}
		else if (p_char >= 'a' && p_char <= 'z')
		{
			iCode = Character.getNumericValue(p_char) + 55;
			keyPressAndRelease(iCode);
		}
		else if (p_char >= 'A' && p_char <= 'Z')
		{
			iCode = Character.getNumericValue(p_char) + 55;
			shiftPress(iCode);
		}
		else
		{
			switch(p_char)
			{
				case '-':
					keyPressAndRelease(VK_MINUS);
					break;
				case ',':
					keyPressAndRelease(VK_COMMA);
					break;
				case '/':
					keyPressAndRelease(VK_SLASH);
					break;
				case '.':
					keyPressAndRelease(VK_PERIOD);
					break;
				case '!':
					iCode = Character.getNumericValue('1') + 48;
					shiftPress(iCode);
					break;
				case '#':
					iCode = Character.getNumericValue('3') + 48;
					shiftPress(iCode);
					break;
				case '$':
					iCode = Character.getNumericValue('4') + 48;
					shiftPress(iCode);
					break;
				case '%':
					iCode = Character.getNumericValue('5') + 48;
					shiftPress(iCode);
					break;
				case '&':
					iCode = Character.getNumericValue('7') + 48;
					shiftPress(iCode);
					break;
				case '(':
					iCode = Character.getNumericValue('9') + 48;
					shiftPress(iCode);
					break;
				case ')':
					iCode = Character.getNumericValue('0') + 48;
					shiftPress(iCode);
					break;
				case '*':
					iCode = Character.getNumericValue('8') + 48;
					shiftPress(iCode);
					break;
				case ':':
					shiftPress(VK_SEMICOLON);
					break;
				case '<':
					shiftPress(VK_COMMA);
					break;
				case '>':
					shiftPress(VK_PERIOD);
					break;
				case '?':
					shiftPress(VK_SLASH);
					break;
				case '@':
					iCode = Character.getNumericValue('2') + 48;
					shiftPress(iCode);
					break;
				case '^':
					iCode = Character.getNumericValue('6') + 48;
					shiftPress(iCode);
					break;
				case '_':
					shiftPress(VK_MINUS);
					break;
				case '|':
					shiftPress(VK_BACK_SLASH);
					break;
				case '~':
					shiftPress(VK_BACK_QUOTE);
					break;
				case '`':
					keyPressAndRelease(VK_BACK_QUOTE);
					break;
				case '\'':
					keyPressAndRelease(VK_QUOTE);
					break;
				case '"':
					shiftPress(VK_QUOTE);
					break;
				case '+':
					shiftPress(VK_EQUALS);
					break;
				case '{':
					shiftPress('[');
					break;
				case '}':
					shiftPress(']');
					break;
				default : 
					iCode = Character.toString(p_char).hashCode();
					keyPressAndRelease(iCode);
			}
		}

	}
	
	@Override
	public void typeString(String text)
	{
		if(sel!=null)
		{
			for (char c : text.toCharArray())
			{
				this.typeKey(c);
			}
		}
	}
	
	@Override
	public void shiftPress(int keyCode)
	{
		sel.keyDownNative(String.valueOf(VK_SHIFT));
		keyPressAndRelease(keyCode);
		sel.keyUpNative(String.valueOf(VK_SHIFT));
	}
	
	/**
	 * Ctrl press + key + Ctrl release
	 * @param keyCode
	 */
	@Override
	public void ctrlPress(char key)
	{
		sel.keyDownNative(String.valueOf(VK_CONTROL));
		typeKey(key);
		sel.keyUpNative(String.valueOf(VK_CONTROL));
	}
	
	@Override
	public void ctrlPress(int keyCode)
	{
		sel.keyDownNative(String.valueOf(VK_CONTROL));
		keyPressAndRelease(keyCode);
		sel.keyUpNative(String.valueOf(VK_CONTROL));
	}
	
	@Override
	public void controlKeyDown() {
		sel.controlKeyDown();
	}

	@Override
	public void controlKeyUp() {
		sel.controlKeyUp();
	}

	@Override
	public void keyPress(Keys key) {
		keyboard.pressKey(key);
	}

	@Override
	public void keyRelease(Keys key) {
		keyboard.releaseKey(key);
	}

	@Override
	public void keyPressAndRelease(Keys key) {
		keyboard.pressKey(key);
		keyboard.releaseKey(key);
	}

	@Override
	public void shiftPress(Keys key) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.SHIFT).perform();
		keyPressAndRelease(key);
		actions.keyUp(Keys.SHIFT).perform();
	}

	@Override
	public void ctrlPress(Keys key) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).perform();
		keyPressAndRelease(key);
		actions.keyUp(Keys.CONTROL).perform();
	}
}
