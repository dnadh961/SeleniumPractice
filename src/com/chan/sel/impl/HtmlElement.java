package com.chan.sel.impl;

import com.chan.sel.internal.IHtmlElement;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class HtmlElement implements IHtmlElement{

	private String id;
	
	public HtmlElement(String id)
	{
		this.id = id;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDomPointer() {
		return "selenium.browserbot.getCurrentWindow().document.getElementById('"+id+"')";
	}
}
