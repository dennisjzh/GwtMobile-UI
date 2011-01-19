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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.event.SelectionChangedHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class ListPanel extends FlowPanel implements ClickHandler, DragEventsHandler{

	private boolean _showArrow;
	private int _selected = -1;
	
    public ListPanel() { 
        addDomHandler(this, ClickEvent.getType());
        setStyleName("List");
    }

    public HandlerRegistration addSelectionChangedHandler(SelectionChangedHandler handler) {
        return this.addHandler(handler, SelectionChangedEvent.TYPE);
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
    public void add(Widget w) {
    	Utils.Console("adding " + w.getClass().toString());
    	if (w.getClass().toString().endsWith(".ListItem")) {
    		super.add(w);
    	}
    	else {
        	ListItem listItem = new ListItem();
        	super.add(listItem);    	
        	listItem.add(w);
        	if (_showArrow) {
	        	Chevron chevron = new Chevron();
	        	listItem.add(chevron);
        	}
    	}
    }

    @Override
    public void onClick(ClickEvent e) {
        if (_selected >= 0) {
            SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(_selected, 
            	e.getNativeEvent().getEventTarget());
            this.fireEvent(selectionChangedEvent);
        	getWidget(_selected).removeStyleName("Pressed");
    		_selected = -1;
        }
    }
    
    public void setShowArrow(boolean show) {
    	Utils.Console("ListPanel setShowArrow");
    	_showArrow = show;
    	for (Widget listItem : this.getChildren()) {
			((ListItem)listItem).setShowArrowFromParent(show);
		}
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    	_selected = getTargetItemIndex(e.getNativeEvent().getEventTarget());
    	if (_selected >= 0) {
        	getWidget(_selected).addStyleName("Pressed");
    	}
    }

    @Override
    public void onDragMove(DragEvent e) {
    	if (_selected >= 0) {
        	getWidget(_selected).removeStyleName("Pressed");
    		_selected = -1;
    	}
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	if (_selected >= 0) {
        	getWidget(_selected).removeStyleName("Pressed");
    		//_selected = -1; need to keep the selected value for click event.
    	}
    }
    
    private int getTargetItemIndex(EventTarget target) {
        Element div = Element.as(target);
        if (div == this.getElement()) {
        	Utils.Console("Is click on list working? " + target.toString());
        	return -1;
        }
        while (div.getParentElement() != this.getElement()) {
            div = div.getParentElement();
        }
        int index = DOM.getChildIndex(
        		(com.google.gwt.user.client.Element)this.getElement(), 
        		(com.google.gwt.user.client.Element)div);
        return index;
    }
    
    static class Chevron extends HTML {    	
    	public Chevron() {
    		super("<div class=\"Chevron\"><span></span><span></span></div>");
    	}
    }

}
