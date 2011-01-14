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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;

public class TabHeader extends FlowPanel implements HasClickHandlers, ClickHandler {

    private Tab _tab;
    
    public TabHeader() {
        addClickHandler(this);
    }
    
    protected void initHeader(Tab tab) {
        _tab = tab;
    }
    
    public Tab getTab() {
        return _tab;
    }
    
    @Override
    public void onClick(ClickEvent event) {
        _tab.getTabMenu().onClickHeader(this);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return this.addDomHandler(handler, ClickEvent.getType());
    }
}
