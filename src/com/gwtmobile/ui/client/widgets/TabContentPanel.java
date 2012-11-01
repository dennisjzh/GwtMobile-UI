package com.gwtmobile.ui.client.widgets;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;



public class TabContentPanel extends PanelBase {

	ArrayList<TabContent> _contentArray = new ArrayList<TabContent>();
	
	public TabContentPanel(){
		setStyleName(Primary.TabContentPanel);
	}
	
    @Override
    public void add(Widget w) {
    
    	if (w instanceof TabContent) {
        	_contentArray.add((TabContent) w);
    	} 
    	else if (isDesignTimeEmptyLabel(w)) {
        	super.add(w);
    	} 
    	else {
    		assert false : "TabContentPanel can only contain TabContent widgets";
    	}
    	
    }
    
    public TabContent getSelectedTabContent() {
    	assert this.getWidgetCount() > 0 : "no selected tab content";
    	return (TabContent) getWidget(0);
    }

    public void selectTab(int fromIndex, int toIndex) {
    	if (toIndex < 0 || _contentArray.size() <= toIndex) {
    		return;
    	}
    	TabContent to = _contentArray.get(toIndex);
    	if (getWidgetCount() > 0 && fromIndex > -1) {
    		TabContent from = (TabContent) getWidget(0);
    		//FIXME: transition not working
//        	Transition transition = Transition.SLIDE;
//        	transition.start(from, to, this, fromIndex > toIndex);
        	super.remove(from);
        	super.add(to);
    	}
    	else {
        	super.add(to);
    	}		
    }
    
    @Override
    protected String getDesignTimeMessage() {
    	return "Add equal number of TabContent widgets as the TabHeader widgets in the TabHeaderPanel.";
    }
}
