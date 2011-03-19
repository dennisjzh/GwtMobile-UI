package com.gwtmobile.ui.client.widgets;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

public class TextBox extends com.google.gwt.user.client.ui.TextBox 
	implements FocusHandler, BlurHandler {

	public TextBox() {
		setStyleName("TextBox");
		addFocusHandler(this);
		addBlurHandler(this);
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		this.addStyleName("Focus");
		//TODO: need to scroll to view
		//this.getElement().scrollIntoView();
	}

	@Override
	public void onBlur(BlurEvent event) {
		this.removeStyleName("Focus");
	}

}
