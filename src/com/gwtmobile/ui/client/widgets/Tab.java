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

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class Tab extends WidgetBase implements HasWidgets {
    
    private int _index;
    private TabPanel _tabMenu;
    private TabHeader _header;
    private TabContent _content;

    public Tab() {
    }
    
    public int getIndex() {
        return _index;
    }    
    public TabPanel getTabMenu() {
        return _tabMenu;
    }
    public TabHeader getHeader() {
        return _header;
    }    
    public TabContent getContent() {
        return _content;
    } 
    
    protected void initTab(TabPanel tabMenu, int index) {
        _tabMenu = tabMenu;
        _index = index;
    }
    
    @Override
    public void add(Widget w) {
        // TODO: Check widget is a TabHeader or a TabContent.
        if (this.getWidget() == null) {
            _header = (TabHeader)w;
            initWidget(_header);
            _header.initHeader(this);
        }
        else {
            _content = (TabContent) w;
            _content.initContent(this);
        }
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
