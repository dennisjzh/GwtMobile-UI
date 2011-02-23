/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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

import java.util.Iterator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;

public class TabPanel extends WidgetBase implements HasWidgets, ClickHandler {

    private FlowPanel _panel = new FlowPanel();
    private FlowPanel _tabHeaderPanel = new FlowPanel();
    private int _selectedTabIndex = -1;
    
    public TabPanel() {
        initWidget(_panel);
        setStyleName("TabPanel");
        _panel.add(_tabHeaderPanel);
        _tabHeaderPanel.addDomHandler(this, ClickEvent.getType());
    }
    
    @Override
    public void add(Widget w) {
        assert w instanceof Tab : "Can only place Tab widgets inside a Tab Panel.";
        _tabHeaderPanel.add(w);
    }
    
    @Override
    protected void onInitialLoad() {
    	if (_tabHeaderPanel.getWidgetCount() > 0) {
            selectTab(0);
    	}
    }
    
    public void selectTab(int index) {
        if (_selectedTabIndex == index) {
        	Utils.Console("same tab");
        	return;
        }
        unselectCurrentTab();
  		Tab tab = (Tab) _tabHeaderPanel.getWidget(index);
        tab.addStyleName("Selected");
        _panel.add(tab.getContent());
        _selectedTabIndex = index;
    }

    public void unselectCurrentTab() {
    	if (_selectedTabIndex == -1) {
    		return;
    	}
		Tab tab = (Tab) _tabHeaderPanel.getWidget(_selectedTabIndex);
    	tab.removeStyleName("Selected");
        tab.getContent().removeFromParent();
        _selectedTabIndex = -1;
    }

	@Override
	public void onClick(ClickEvent event) {
		int index = getClickedTabHeaderIndex(event);
		if (index != -1) {
            selectTab(index);
		}
	}
	
    private int getClickedTabHeaderIndex(ClickEvent e) {
        Element div = Element.as(e.getNativeEvent().getEventTarget());
        if (div == _tabHeaderPanel.getElement()) {
        	Utils.Console("Is click on tab header working? " + e.toString());
        	return -1;
        }
        while (div.getParentElement() != _tabHeaderPanel.getElement()) {
            div = div.getParentElement();
        }
        int index = DOM.getChildIndex(
        		(com.google.gwt.user.client.Element)_tabHeaderPanel.getElement(), 
        		(com.google.gwt.user.client.Element)div);
        return index;
    }


    @Override
    public void clear() {
    	_panel.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return _panel.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return _panel.remove(w);
    }

}
