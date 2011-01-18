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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.event.SelectionChangedHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class ListPanel extends FlowPanel implements ClickHandler{

    public ListPanel() { 
        addDomHandler(this, ClickEvent.getType());
    }

    public HandlerRegistration addSelectionChangedHandler(SelectionChangedHandler handler) {
        return this.addHandler(handler, SelectionChangedEvent.TYPE);
    }


    @Override
    //TODO: click event needs rework, base class changed.
    public void onClick(ClickEvent e) {
        EventTarget target = e.getNativeEvent().getEventTarget();
        Element tr = Element.as(target);
        Utils.Console(tr.getNodeName());
        while (!tr.getNodeName().toUpperCase().equals("TR") || 
                tr.getParentElement().getParentElement() != this.getElement()) {
            tr = tr.getParentElement();
            if (tr == null) {
                Utils.Console("ListPanel onClick: tr not found");
                return;
            }
        }
        Element tbody = tr.getParentElement();
        int index = DOM.getChildIndex((com.google.gwt.user.client.Element)tbody, (com.google.gwt.user.client.Element)tr);
        Utils.Console(index + "");
        SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(index, target);
        this.fireEvent(selectionChangedEvent);
    }

}
