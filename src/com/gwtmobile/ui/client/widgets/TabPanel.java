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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;

public class TabPanel extends PanelBase 
	implements HasSelectionHandlers<Integer>, ClickHandler {


	public enum TabPosition {Top, Bottom}; // todo: LeftTabs, RightTabs
    private TabHeaderPanel tabHeaderPanel;
    private TabContentPanel tabContentPanel;
    private int selectedTabIndex = -1;
    private int defaultTabIndex = 0;
    private boolean fullHeight = false;
    private TabPosition tabPosition = TabPosition.Top;

	public TabPanel() {
        setStyleName(Primary.TabPanel);
        addStyleName(tabPosition.toString());
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
    	if (tabHeaderPanel != null && tabHeaderPanel.getWidgetCount() > 0) {
            selectTab(defaultTabIndex);
    	}
    }
    
    public void selectTab(int index) {

    	if (selectedTabIndex == index) {
        	return;
        }
    	if (selectedTabIndex != -1) {
        	tabHeaderPanel.unSelectHeader(selectedTabIndex);
    	}
        tabHeaderPanel.selectHeader(index);
        tabContentPanel.selectTab(selectedTabIndex, index);
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
    	return tabContentPanel.getSelectedTabContent();
    }
    
	@Override
	public void onClick(ClickEvent event) {
		int index = tabHeaderPanel.getClickedTabHeaderIndex(event);
		if (index != -1) {
            selectTab(index);
		}
	}
    
	@Override
	public HandlerRegistration addSelectionHandler(
			SelectionHandler<Integer> handler) {
		return this.addHandler(handler, SelectionEvent.getType());
	}
	
	public TabPosition getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(TabPosition tabsPosition) {
		this.tabPosition = tabsPosition;
		if (tabsPosition == TabPosition.Bottom && getWidget(0) == tabHeaderPanel) {
			super.clear();
			super.add(tabContentPanel);
			super.add(tabHeaderPanel);
			addStyleName(Secondary.Bottom);
			removeStyleName(Secondary.Top);
		} else if(tabsPosition == TabPosition.Top && getWidget(0) == tabContentPanel){
			super.clear();
			super.add(tabHeaderPanel);
			super.add(tabContentPanel);
			addStyleName(Secondary.Top);
			removeStyleName(Secondary.Bottom);
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
			addStyleName(Secondary.FullHeight);
		} else { 
			removeStyleName(Secondary.FullHeight);
		}
	}

	public void setTabBarPanel(boolean isTabBarPanel) {
		if (isTabBarPanel) {
			addStyleName(Primary.TabBarPanel);
		}
		else {
			removeStyleName(Primary.TabBarPanel);
		}
	}
}
