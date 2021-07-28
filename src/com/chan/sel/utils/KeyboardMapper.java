package com.chan.sel.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;

import com.chan.sel.internal.IKeyboard;

public class KeyboardMapper {

	private final Map<Integer, Keys> keysMap;

	public KeyboardMapper() {
		super();
		keysMap = new HashMap<Integer, Keys>();
		keysMap.put(IKeyboard.KEY_ALT, Keys.ALT);
		keysMap.put(IKeyboard.KEY_ALTGR, null);
		keysMap.put(IKeyboard.KEY_BACKSPACE, Keys.BACK_SPACE);
		keysMap.put(IKeyboard.KEY_CAPSLOCK, null);
		keysMap.put(IKeyboard.KEY_CONTROL, Keys.CONTROL);
		keysMap.put(IKeyboard.KEY_DELETE, Keys.DELETE);
		keysMap.put(IKeyboard.KEY_DOWNARROW, Keys.ARROW_DOWN);
		keysMap.put(IKeyboard.KEY_END, Keys.END);
		keysMap.put(IKeyboard.KEY_ENTER, Keys.ENTER);
		keysMap.put(IKeyboard.KEY_ESCAPE, Keys.ESCAPE);
		keysMap.put(IKeyboard.KEY_F1, Keys.F1);
		keysMap.put(IKeyboard.KEY_F10, Keys.F10);
		keysMap.put(IKeyboard.KEY_F11, Keys.F11);
		keysMap.put(IKeyboard.KEY_F12, Keys.F12);
		keysMap.put(IKeyboard.KEY_F2, Keys.F2);
		keysMap.put(IKeyboard.KEY_F3, Keys.F3);
		keysMap.put(IKeyboard.KEY_F4, Keys.F4);
		keysMap.put(IKeyboard.KEY_F5, Keys.F5);
		keysMap.put(IKeyboard.KEY_F6, Keys.F6);
		keysMap.put(IKeyboard.KEY_F7, Keys.F7);
		keysMap.put(IKeyboard.KEY_F8, Keys.F8);
		keysMap.put(IKeyboard.KEY_F9, Keys.F9);
		keysMap.put(IKeyboard.KEY_HOME, Keys.HOME);
		keysMap.put(IKeyboard.KEY_INSERT, Keys.INSERT);
		keysMap.put(IKeyboard.KEY_LEFTARROW, Keys.ARROW_LEFT);
		keysMap.put(IKeyboard.KEY_PAGEDOWN, Keys.PAGE_DOWN);
		keysMap.put(IKeyboard.KEY_PAGEUP, Keys.PAGE_UP);
		keysMap.put(IKeyboard.KEY_RIGHTARROW, Keys.ARROW_RIGHT);
		keysMap.put(IKeyboard.KEY_SHIFT, Keys.SHIFT);
		keysMap.put(IKeyboard.KEY_TAB, Keys.TAB);
		keysMap.put(IKeyboard.KEY_UPARROW, Keys.ARROW_UP);
	}

	public Keys getKey(final int KeyCode)
	{
		return keysMap.get(KeyCode);
	}
}
