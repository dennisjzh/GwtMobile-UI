package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;
import java.util.ArrayList;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.page.Transition;



public class TabContentPanel extends PanelBase {

	ArrayList<TabContent> contentArray = new ArrayList<TabContent>();
	public TabContentPanel(){
		setStyleName(Primary.TabContentPanel);
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabContent) {
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

    public void selectTab(int fromIndex, int toIndex) {
    	if (toIndex < 0 || contentArray.size() <= toIndex) {
    		return;
    	}
    	TabContent to = contentArray.get(toIndex);    	
    	if (getWidgetCount() > 0) {
    		TabContent from = (TabContent) getWidget(0);
        	Transition transition = Transition.SLIDE;
        	transition.start(from, to, this, fromIndex > toIndex);
    	}
    	else {
        	super.add(to);
    	}
    }
}
