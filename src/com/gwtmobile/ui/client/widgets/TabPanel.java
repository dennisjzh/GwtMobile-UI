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

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TabPanel extends WidgetBase implements HasWidgets {

    private FlowPanel _panel = new FlowPanel();
    private FlexTable _tabs = new FlexTable();
    
    public TabPanel() {
        initWidget(_panel);
        _panel.add(_tabs);
        _tabs.insertRow(0);
        _tabs.insertRow(1);
        _tabs.insertCell(1, 0);
        _tabs.addStyleName("Table");
        _tabs.getCellFormatter().setStyleName(1, 0, "Content");
        setStyleName("Tab");
    }
    
    @Override
    public void add(Widget w) {
        // TODO: Check widget is a tab.
        int index = _tabs.getCellCount(0);
        Tab tab = (Tab) w;
        tab.initTab(this, index);
        _tabs.setWidget(0, index, tab);
        _tabs.getCellFormatter().setStyleName(0, index, "Header");
        _tabs.getFlexCellFormatter().setColSpan(1, 0, _tabs.getCellCount(0));
    }
    
    @Override
    protected void onInitialLoad() {
        selectTab(0);
    }
    
    public void selectTab(int index) {
        Tab selected = getSelectedTab();
        if (selected != null) {
            unselectTab(selected.getIndex());
        }
        Tab tab = (Tab) _tabs.getWidget(0, index);
        _tabs.setWidget(1, 0, tab.getContent());
        _tabs.getCellFormatter().addStyleName(0, index, "Selected");
    }

    public void unselectTab(int index) {
        Tab tab = (Tab) _tabs.getWidget(0, index);
        _tabs.getCellFormatter().removeStyleName(0, tab.getIndex(), "Selected");
        _tabs.remove(tab.getContent());
    }

    public Tab getSelectedTab() {
        TabContent content = (TabContent) _tabs.getWidget(1, 0);
        if (content != null) {
            return content.getTab();
        }
        return null;
    }
    
    public void onClickHeader(TabHeader header) {
        selectTab(header.getTab().getIndex());
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<Widget> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Widget w) {
        // TODO Auto-generated method stub
        return false;
    }
}
