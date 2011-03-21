package com.gwtmobile.ui.client.widgets;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.TextBoxBase;

public class NumberTextBox extends TextBoxBase 
	implements FocusHandler, BlurHandler {

	public NumberTextBox() {
	    super(createNumberInputElement());
		setStyleName("NumberTextBox");
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

	private static native InputElement createNumberInputElement()  /*-{
		var e = $doc.createElement("INPUT");
		e.type = "number";
		return e;
	}-*/;
}
