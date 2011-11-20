package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;



public class TabHeaderPanel extends PanelBase {

	public TabHeaderPanel(){
		setStyleName(Primary.TabHeaderPanel);
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabHeader) {
    		super.add(w);
    	} else if (Beans.isDesignTime() && w instanceof Label) {
    		// allow Label during designtime
    	} else {
    		assert false : "The TabHeaderPanel can only contain multiple TabHeader elements";
    	}
    	
    }
	
}
