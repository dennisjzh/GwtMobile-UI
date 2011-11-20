package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;
import java.util.ArrayList;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;



public class TabContentPanel extends FlowPanel {

	ArrayList<TabContent> contentArray = new ArrayList<TabContent>();
	public TabContentPanel(){
		setStyleName(Primary.TabContentPanel);
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabContent) {
    		//super.add(w);
    		contentArray.add((TabContent) w);
    	} else if (Beans.isDesignTime() && w instanceof Label) {
    		// allow Label during designtime
    	} else {
    		assert false : "The TabContentPanel can only contain multiple TabContent elements";
    	}
    	
    }
    
    public TabContent getSelectedTabContent() {
    	assert this.getWidgetCount() > 0 : "no selected tab content";
    	return (TabContent) getWidget(0);
    }

    public TabContent selectTab(int index) {
    	if (index < 0 || contentArray.size() <= index) {
    		return null;
    	}
    	TabContent tabContent = contentArray.get(index);
    	
    	//TODO: transition goes here.
    	this.clear();
    	super.add(tabContent);
        return tabContent;
    }

	
}
