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

import java.beans.Beans;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.utils.Utils;

public class RadioButtonGroup extends CheckBoxGroup {

    private String _name = null;
    
    public RadioButtonGroup() {
    	super();
    	setStyleName(Primary.RadioButtonGroup);
    	addStyleName(Secondary.Vertical);
    }

    @Override
    public void onInitialLoad() {
    	super.onInitialLoad();
// bypass assertions for gwtdesigner reasons
//    	assert name != null
//			: "Attribute 'name' must be set on RadioButtonGroup";
    }
    
    @Override
    public void onClick(ClickEvent e) {
        EventTarget target = e.getNativeEvent().getEventTarget();
        String targetTagName = ((Element)target.cast()).getTagName().toUpperCase();
        if (targetTagName.equals("SPAN")) {
        	int before = getCheckedIndex();
    		super.onClick(e);
        	int after = getCheckedIndex();
        	Utils.Console("before " + before + " after " + after);
        	if (after == -1) {
        		RadioButton radio = (RadioButton) getWidget(before);
        		if (!radio.getValue()) {	
        			// cannot un-select a radio button without selecting another one.
        			radio.setValue(true);
        		}
        	}
        	else if (before > -1) {
        		RadioButton radio = (RadioButton) getWidget(before);
        		radio.setValue(false);
        	}
        }
        else if (targetTagName.equals("INPUT")) {
    		super.onClick(e);
        	for (int i = 0; i < getWidgetCount(); i++) {
				RadioButton radio = (RadioButton) getWidget(i);
				radio.setValue(radio.getValue());
			}
        }
    }
    
    public int getCheckedIndex() {
        for (int i = 0; i < getWidgetCount(); i++) {
            RadioButton radio = (RadioButton) getWidget(i);
            if (radio.getValue()) {
            	return i;
            }
        }
        return -1;
    }

	public RadioButton getCheckedWidget() {
    	int i = getCheckedIndex();
    	if (i > -1) {
    		return (RadioButton)getWidget(i);
    	}
		return null;
    }
    
    @Override
    public void add(Widget w) {
// no assertions for gwtdesigner reasons
//    	assert w instanceof RadioButton 
//    		: "Can only contain RadioButton widgets in RadioButtonGroup";
    	if (Beans.isDesignTime() && !(w instanceof RadioButton))
    		return;
    	
    	RadioButton radio = (RadioButton) w;
    	if (_name != null) {
    		radio.setName(_name);
    	}
        super.addWidgetToPanel(radio);
		radio.addValueChangeHandler(this);
    }
    
    public void setName(String name) {
    	this._name = name;
    	for (int i = 0; i < getWidgetCount(); i++) {
    		RadioButton radio = (RadioButton) getWidget(i);
    		radio.setName(name);
    	}
    }
    
    public String getName() {
    	return _name;
    }
    
}
