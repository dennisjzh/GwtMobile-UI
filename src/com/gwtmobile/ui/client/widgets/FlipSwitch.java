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
import com.google.gwt.user.client.ui.HTML;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;

public class FlipSwitch extends HTML implements DragEventsHandler, ClickHandler {

	protected boolean _value = false;
	
    public FlipSwitch() {
        setStyleName("FlipSwitch");
        addStyleName("Off");
        addClickHandler(this);
  	super.setHTML("<div></div><div></div><div><div><div>ON</div><div></div><div>OFF</div></div></div>");
    }
    
    @Override
    public void setHTML(String html) {
    	assert false : "HTML/Text cannot be set to a FlipSwith widget.";
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        DragController.get().addDragEventsHandler(this);
    }
    
    @Override
    public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    }

    @Override
    public void onDragMove(DragEvent e) {
    }

    @Override
    public void onDragEnd(DragEvent e) {
    }
    
    public void setValue(boolean value) {
    	_value = value;
    	updateStyle();    	
    }
    
    public boolean getValue() {
    	return _value;
    }

	@Override
	public void onClick(ClickEvent event) {
		_value = !_value;
		updateStyle();
	}
	
	private void updateStyle() {
		if (_value) {
			removeStyleName("Off");
		}
		else {
			addStyleName("Off");
		}
	}
}
