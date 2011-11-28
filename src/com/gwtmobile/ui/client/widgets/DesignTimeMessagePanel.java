package com.gwtmobile.ui.client.widgets;

import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.utils.Utils;

//Internal class, not public
class DesignTimeMessagePanel extends HTMLPanel {

	private boolean error = false;
	public DesignTimeMessagePanel(Widget w) {		
		super("");
		addMessage("Empty " + Utils.getSimpleName(w.getClass()) + ".");
		setStyleName(Primary.DesignTimeMessagePanel);
	}	
		
	public Label addMessage(String message) {
		Label label = new Label(message);
		this.add(label);
		return label;
	}
	
	public void addErrorMessage(String message) {
		addMessage(message).addStyleName(Secondary.Error);
		error = true;
	}
	
	public boolean hasError() {
		return error;
	}
}
