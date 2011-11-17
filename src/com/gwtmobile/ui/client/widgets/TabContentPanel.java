package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;



public class TabContentPanel extends FlowPanel implements HasWidgets {

	public TabContentPanel(){
		setStyleName("gwtm-TabContentPanel");
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabContent) {
    		super.add(w);
    	} else if (Beans.isDesignTime() && w instanceof Label) {
    		// allow Label during designtime
    	} else {
    		assert false : "The TabContentPanel can only contain multiple TabContent elements";
    	}
    	
    }
	
}
