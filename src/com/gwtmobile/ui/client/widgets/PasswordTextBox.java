package com.gwtmobile.ui.client.widgets;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

public class PasswordTextBox extends com.google.gwt.user.client.ui.PasswordTextBox 
	implements FocusHandler, BlurHandler {

	public PasswordTextBox() {
		setStyleName("PasswordTextBox");
		addFocusHandler(this);
		addBlurHandler(this);
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		this.addStyleName("Focus");
	}

	@Override
	public void onBlur(BlurEvent event) {
		this.removeStyleName("Focus");
	}

}
