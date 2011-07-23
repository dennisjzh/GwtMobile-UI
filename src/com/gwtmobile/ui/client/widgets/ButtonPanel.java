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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;

/**
 * A copy of a {@link Button} but we added to nested html/widgets like images.
 * This was motivated by gwtmobile event system invalidating standard GWT 
 * widgets.
 */
public class ButtonPanel extends HTMLPanel implements DragEventsHandler, IsGwtMobileWidget {

	private boolean _isDisabled = false;
	private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();
	
    public ButtonPanel(String html) {
		super(html);
        setStyleName("Button");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        DragController.get().addDragEventsHandler(this);
        _widgetHelper.CheckInitialLoad(this);
    }
    
    @Override
    public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    	if (!_isDisabled) {
            addStyleName("Pressed");
    	}
        e.stopPropagation();
    }

    @Override
    public void onDragMove(DragEvent e) {
    	if (!_isDisabled) {
    		removeStyleName("Pressed");       
    	}
        e.stopPropagation();
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	if (!_isDisabled) {
    		removeStyleName("Pressed");
    	}
    	else {
    		DragController.get().suppressNextClick();
    	}
        e.stopPropagation();
    }
    
    public void setDisabled(boolean disabled) {
    	_isDisabled = disabled;
    	if (disabled) {
    		addStyleName("Disabled");
    	}
    	else {
    		removeStyleName("Disabled");
    	}
    }
    
    public boolean isDisabled() {
    	return _isDisabled;
    }

	@Override
	public void onInitialLoad() {
	}

	@Override
	public void onTransitionEnd() {
	}

	@Override
	public void setSecondaryStyle(String style) {
		_widgetHelper.setSecondaryStyle(this, style);
	}
	
	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
}
