/*
 * Copyright (c) 2010-2011 Zhihua (Dennis) Jiang
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;

public class TabPanel extends FlowPanel implements HasWidgets, HasSelectionHandlers<Integer>, ClickHandler {


	public enum TabsPosition {Normal, Reverse}; // todo: LeftTabs, RightTabs
    private TabHeaderPanel tabHeaderPanel;
    private TabContentPanel tabContentPanel;
    private int selectedTabIndex = -1;
    private int defaultTabIndex = 0;
    private boolean fullHeight = false;
    private TabsPosition tabsPosition = TabsPosition.Normal;

	public TabPanel() {
        setStyleName("gwtm-TabPanel");
        addStyleName(tabsPosition.toString());
        tabHeaderPanel = null;
        tabContentPanel = null;
    }
    
    @Override
    public void add(Widget w) {
    	
    	if (w instanceof TabHeaderPanel && tabHeaderPanel == null) {
    		tabHeaderPanel = (TabHeaderPanel)w;
    		tabHeaderPanel.addDomHandler(this, ClickEvent.getType());
    		super.add(tabHeaderPanel);
    		return;
    	} else if (w instanceof TabHeaderPanel && tabHeaderPanel == null) {
    		assert false : "The TabPanel can only contain one TabHeaderPanel";
    	}
    	
    	if (w instanceof TabContentPanel && tabContentPanel == null) {
    		tabContentPanel = (TabContentPanel)w;
    		super.add(tabContentPanel);
    		return;
    	} else if (w instanceof TabHeaderPanel && tabHeaderPanel == null) {
    		assert false : "The TabPanel can only contain one TabContentPanel";
    	}
    	
    	if (Beans.isDesignTime() && w instanceof Label) {
    		// bypass for designtime compliance
    		return;
    	}
    	assert false : "The TabPanel can only contains one TabHeaderPAnel and one TabContentPanel. ("+w.getClass().getName()+")";
    	
    }
    
    @Override
	public void onInitialLoad() {
    
	    	if (tabHeaderPanel.getWidgetCount() > 0) {
	            selectTab(defaultTabIndex);
	    	}
    }
    
    public void selectTab(int index) {

    	if (selectedTabIndex == index) {
        	return;
        }
        TabHeader from = unselectCurrentTab();
        TabContent fromContent = unSelectCurrentTabContent();
        TabHeader to = (TabHeader) tabHeaderPanel.getWidget(index);
        TabContent toContent = (TabContent) tabContentPanel.getWidget(index);
        
        to.addStyleName("Selected");
        toContent.addStyleName("Selected");
        
        if (from == null || fromContent == null) {
        	//tabContentPanel.add(toContent);
        } else {
//        	Transition transition = Transition.SLIDE;
//        	transition.start(fromContent, toContent, tabContentPanel, 
//        			index < selectedTabIndex);
        }
        selectedTabIndex = index;
        SelectionEvent.fire(this, selectedTabIndex);
    }

    public int getSelectedTabIndex() {
        return selectedTabIndex;
    }

    public TabHeader getSelectedTab() {
    	return (TabHeader) tabHeaderPanel.getWidget(selectedTabIndex);
    }
    
    public TabContent getSelectedTabContent() {
    	return (TabContent) tabContentPanel.getWidget(selectedTabIndex);
    }
    
	@Override
	public void onClick(ClickEvent event) {
		int index = getClickedTabHeaderIndex(event);
		if (index != -1) {
            selectTab(index);
		}
	}
    
    private TabHeader unselectCurrentTab() {
    	if (selectedTabIndex == -1) {
    		return null;
    	}
    	TabHeader tab = getSelectedTab();
    	tab.removeStyleName("Selected");
        return tab;
    }
    
    private TabContent unSelectCurrentTabContent(){
    	if (selectedTabIndex == -1) {
    		return null;
    	}
    	TabContent tabC = getSelectedTabContent();
    	tabC.removeStyleName("Selected");
        return tabC;
    }

    private int getClickedTabHeaderIndex(ClickEvent e) {
        Element div = Element.as(e.getNativeEvent().getEventTarget());
        if (div == tabHeaderPanel.getElement()) {
        	Utils.Console("Is click on tab header working? " + e.toString());
        	return -1;
        }
        while (div.getParentElement() != tabHeaderPanel.getElement()) {
            div = div.getParentElement();
        }
        int index = DOM.getChildIndex(
        		(com.google.gwt.user.client.Element)tabHeaderPanel.getElement(), 
        		(com.google.gwt.user.client.Element)div);
        return index;
    }

	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<Integer> handler) {
		return this.addHandler(handler, SelectionEvent.getType());
	}
	
//	public void setTabsOnBottom(boolean tabsOnBottom) {
//		this.tabsOnBottom = tabsOnBottom;
//		if (tabsOnBottom && getWidget(0) == tabHeaderPanel) {
//			super.clear();
//			super.add(tabContentPanel);
//			super.add(tabHeaderPanel);
//			addStyleName("Reverse");
//			removeStyleName("Normal");
//		} else if(!tabsOnBottom && getWidget(0) == tabContentPanel){
//			super.clear();
//			super.add(tabHeaderPanel);
//			super.add(tabContentPanel);
//			addStyleName("Normal");
//			removeStyleName("Reverse");
//		}
//	}

	public TabsPosition getTabsPosition() {
		return tabsPosition;
	}

	public void setTabsOnBottom(boolean tabsOnBottom) {
		//TODO: this is just to make the kitchensink to compile.
	}
	
	public void setTabsPosition(TabsPosition tabsPosition) {
		this.tabsPosition = tabsPosition;
		if (tabsPosition == TabsPosition.Reverse && getWidget(0) == tabHeaderPanel) {
			super.clear();
			super.add(tabContentPanel);
			super.add(tabHeaderPanel);
			addStyleName(tabsPosition.toString());
			removeStyleName("Normal");
		} else if(tabsPosition == TabsPosition.Normal && getWidget(0) == tabContentPanel){
			super.clear();
			super.add(tabHeaderPanel);
			super.add(tabContentPanel);
			addStyleName(tabsPosition.toString());
			removeStyleName("Reverse");
		}
	}
	
	public int getDefaultTabIndex() {
		return defaultTabIndex;
	}

	public void setDefaultTabIndex(int defaultTabIndex) {
		this.defaultTabIndex = defaultTabIndex;
	}

	public boolean isFullHeight() {
		return fullHeight;
	}

	public void setFullHeight(boolean fullHeight) {
		this.fullHeight = fullHeight;
		if (fullHeight){
			addStyleName("fullHeight");
		} else { 
			removeStyleName("fullHeight");
		}
	}

	public void setTabBarPanel(boolean isNavBarPanel) {
		if (isNavBarPanel) {
			addStyleName("TabBarPanel");
		}
		else {
			removeStyleName("TabBarPanel");
		}
	}
}
