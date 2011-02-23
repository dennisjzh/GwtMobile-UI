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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TabPanel extends WidgetBase implements HasWidgets {

    private FlowPanel _panel = new FlowPanel();
    private FlowPanel _tabHeaderPanel = new FlowPanel();
    
    public TabPanel() {
        initWidget(_panel);
        setStyleName("TabPanel");
        _panel.add(_tabHeaderPanel);
    }
    
    @Override
    public void add(Widget w) {
        assert w instanceof Tab : "Can only place Tab widgets inside a Tab Panel.";
        Tab tab = (Tab) w;
        tab.initTab(this);
        _tabHeaderPanel.add(w);
    }
    
    @Override
    protected void onInitialLoad() {
    	if (_tabHeaderPanel.getWidgetCount() > 0) {
    		Tab tab = (Tab) _tabHeaderPanel.getWidget(0);
            selectTab(tab);
    	}
    }
    
    public void selectTab(Tab tab) {
        Tab selected = getSelectedTab();
        if (selected == tab) {
        	return;
        }
        if (selected != null) {
            unselectTab(selected);
        }
        tab.addStyleName("Selected");
        _panel.add(tab.getContent());
    }

    public void unselectTab(Tab tab) {
        tab.removeStyleName("Selected");
        tab.getContent().removeFromParent();
    }

    public Tab getSelectedTab() {
    	if (_panel.getWidgetCount() != 2) {
    		return null;
    	}
    	TabContent tabContent = (TabContent) _panel.getWidget(1);
        return tabContent.getTab();
    }
    
    public void onClickHeader(TabHeader header) {
        selectTab(header.getTab());
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
