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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.event.SelectionChangedHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class RadioButtonGroup extends PanelBase 
	implements HasWidgets, ClickHandler, ValueChangeHandler<Boolean> {

    private String _name = null;
    
    public RadioButtonGroup() {
    	super();
        addDomHandler(this, ClickEvent.getType());
        setStyleName("RadioButtonGroup");
    }

    public HandlerRegistration addSelectionChangedHandler(SelectionChangedHandler handler) {
        return this.addHandler(handler, SelectionChangedEvent.TYPE);
    }
    
    @Override
    public void onLoad() {
    	super.onLoad();
    	assert _name != null
			: "Attribute 'name' must be set on RadioButtonGroup";
    }
    
    @Override
    public void onClick(ClickEvent e) {
        EventTarget target = e.getNativeEvent().getEventTarget();
        Element div = Element.as(target);
        while (!div.getNodeName().toUpperCase().equals("SPAN") || 
                div.getParentElement() != this.getElement()) {
            div = div.getParentElement();
            if (div == null) {
                Utils.Console("RadioButtonGroup onClick: span not found");
                return;
            }
        }
        int index = DOM.getChildIndex(this.getElement(), (com.google.gwt.user.client.Element)div);        
        RadioButton radio = (RadioButton) _panel.getWidget(index);
        if (!radio.getValue()) {
    		RadioButton current = getCheckedRadioButton();
    		if (current != null) {
    			current.setValue(false);
    		}
        	radio.setValue(true);
			SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(index, target);
			fireEvent(selectionChangedEvent);
        }
    }
    
    public RadioButton getRadioButton(int index) {
        return (RadioButton) _panel.getWidget(index);
    }
    
    public int getCheckedRadioButtonIndex() {
        for (int i = 0; i < _panel.getWidgetCount(); i++) {
            RadioButton radio = (RadioButton) _panel.getWidget(i);
            if (radio.getValue()) {
            	return i;
            }
        }
        return -1;
    }

    public RadioButton getCheckedRadioButton() {
    	int i = getCheckedRadioButtonIndex();
    	if (i > -1) {
    		return (RadioButton) _panel.getWidget(i);
    	}
		return null;
    }
    
    @Override
    public void add(Widget w) {
    	assert w.getClass() == RadioButton.class 
    		: "Can only contain RadioButton widgets in RadioButtonGroup";
    	RadioButton radio = (RadioButton) w;
        _panel.add(radio);
		radio.addValueChangeHandler(this);
    }
    
    public void setName(String name) {
    	_name = name;
    	for (int i = 0; i < _panel.getWidgetCount(); i++) {
    		RadioButton radio = (RadioButton) _panel.getWidget(i);
    		radio.setName(_name);
    	}
    }
    
    public String getName() {
    	return _name;
    }
    
    public void setShowIndicator(boolean show) {
    	if (show) {
    		removeStyleName("HideIndicator");
    	}
    	else {
    		addStyleName("HideIndicator");
    	}
    }
    
    public void setVertical(boolean vertical) {
    	if (vertical) {
    		addStyleName("Vertical");
    	}
    	else {
    		removeStyleName("Vertical");
    	}
    }
    
	@Override
	public void onValueChange(ValueChangeEvent<Boolean> event) {
		// if user touches the radio button indicator, 
		// a value change event will be fired instead of a click event.
		// when this occurs, need to make sure style on each radio button is correct.
        for (int i = 0; i < _panel.getWidgetCount(); i++) {
            RadioButton radio = (RadioButton) _panel.getWidget(i);
            radio.setValue(radio.getValue());
        }
	}

}
