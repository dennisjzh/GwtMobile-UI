/*
 * Copyright (c) 2011 Zhihua (Dennis) Jiang
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class Slider extends WidgetBase 
	implements DragEventsHandler, HasValueChangeHandlers<Boolean> {

	protected int _value = 0;
	protected HTML _html = new HTML();
	
    public Slider() {
    	initWidget(_html);
        setStyleName("Slider");
        _html.setHTML("<div></div><div></div><div></div>");
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
    	DragController.get().captureDragEvents(this);
    	Utils.setTransitionDuration(getSliderElement(), 0);
    }

    @Override
    public void onDragMove(DragEvent e) {
    	e.stopPropagation();
    	Element flip = getSliderElement();
    	int x = Utils.getTranslateX(flip);
    	int newX = (int) (x + e.OffsetX);
    	int leftMostPosition = 0;
    	int rightMostPosition = 100;	//TODO: fix me.
    	if (newX > rightMostPosition) {
    		newX = rightMostPosition;
    	}
    	else if (newX < leftMostPosition) {
    		newX = leftMostPosition;
    	}
    	if (newX != x) {
        	Utils.setTranslateX(flip, newX);
    	}
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	DragController.get().releaseCapture(this);
    }

    public void setValue(int value) {
    	_value = value;
    }
    
    public int getValue() {
    	return _value;
    }

	private Element getSliderElement() {
    	return (Element) getElement().getChild(2);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}
}
