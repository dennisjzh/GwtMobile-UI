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
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.event.SelectionChangedHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class RadioButtonGroup extends WidgetBase implements HasWidgets, ClickHandler {

    private FlowPanel _panel = new FlowPanel();
    
    public RadioButtonGroup() {
        initWidget(_panel);
        addDomHandler(this, ClickEvent.getType());
        setStyleName("RadioButtonGroup");
    }

    public HandlerRegistration addSelectionChangedHandler(SelectionChangedHandler handler) {
        return this.addHandler(handler, SelectionChangedEvent.TYPE);
    }
    
    @Override
    public void onClick(ClickEvent e) {
        EventTarget target = e.getNativeEvent().getEventTarget();
        Element div = Element.as(target);
        while (!div.getNodeName().toUpperCase().equals("DIV") || 
                div.getParentElement() != this.getElement()) {
            div = div.getParentElement();
            if (div == null) {
                Utils.Console("RadioButtonGroup onClick: div not found");
                return;
            }
        }
        int index = DOM.getChildIndex(this.getElement(), (com.google.gwt.user.client.Element)div);
        for (int i = 0; i < _panel.getWidgetCount(); i++) {
            RadioButton radio = (RadioButton) _panel.getWidget(i);
            if (radio.isSelected() && i != index) {
                SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(index, target);
                this.fireEvent(selectionChangedEvent);
            }
            radio.setSelected(i == index);
        }
    }
    
    public RadioButton getRadioButton(int index) {
        return (RadioButton) _panel.getWidget(index);
    }
    @Override
    public void add(Widget w) {
        if (w.getClass() != RadioButton.class) {
            assert false : "Can only contain RadioButton widgets in RadioButtonGroup"; 
        }
        _panel.add(w);
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
