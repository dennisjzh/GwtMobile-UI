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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.event.SelectionChangedHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class ListPanel extends PanelBase implements ClickHandler, DragEventsHandler{

	public enum ShowArrow { Visible, Hidden };
	private ShowArrow showArrow;
	private int selected = -1;
	private boolean selectable = true;
	
    public ListPanel() { 
        addDomHandler(this, ClickEvent.getType());
        setStyleName(Primary.ListPanel);
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
    	if (w.getClass() == ListItem.class) {
    		super.add(w);
    	}
    	else {
    		//TODO: check with joao if this is causing problem with gwt designer.
    		// yes, this asserts are causing some issues, for inntance, an emply panel on gwt designer
    		// will have a child Label saying that the panel is empty. With this assert we cant use gwtd
//    		assert false : "Only ListItem can be added to ListPanel.";
        	ListItem listItem = new ListItem();
        	super.add(listItem);    	
        	listItem.add(w);
        	if (showArrow == ShowArrow.Visible) {
	        	Chevron chevron = new Chevron();
	        	listItem.add(chevron);
        	}
    	}
    }

    @Override
    public void onClick(ClickEvent e) {
        if (selected >= 0) {
    		ListItem item = (ListItem) getWidget(selected);
    		if (item.isEnabled()) {
	            SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(selected, 
	            	e.getNativeEvent().getEventTarget());
	            this.fireEvent(selectionChangedEvent);
	        	item.removeStyleName(Secondary.Pressed);
    		}
    		selected = -1;
        }
    }
    
    public void setDisplayArrow(ShowArrow show) {
    	showArrow = show;
    	for (int i = 0; i < getWidgetCount(); i++) {
    		ListItem listItem = (ListItem) getWidget(i);
			listItem.setDisplayArrowFromParent(show);
		}
    }
    
    public ShowArrow getDisplayArrow() {
    	return showArrow;
    }
    
    public void isSelectable(boolean selectable) {
    	this.selectable  = selectable;
    }
    
    public void setSelectable(boolean selectable) {
    	this.selectable  = selectable;
    }
    
    public boolean getSelectable() {
    	return selectable;
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    	if (selectable) {
	    	selected = Utils.getTargetItemIndex(getElement(), e.getNativeEvent().getEventTarget());
	    	if (selected >= 0) {
	    		new Timer() {
					@Override
					public void run() {
				    	if (selected >= 0) {
				    		ListItem item = (ListItem) getWidget(selected);
				    		if (item.isEnabled()) {
					        	getWidget(selected).addStyleName(Secondary.Pressed);
				    		}
				    	}
					}
				}.schedule(75);
	    	}
    	}
    }

    @Override
    public void onDragMove(DragEvent e) {
    	if (selected >= 0) {
        	getWidget(selected).removeStyleName(Secondary.Pressed);
    		selected = -1;
    	}
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	if (selected >= 0) {
        	getWidget(selected).removeStyleName(Secondary.Pressed);
    		//_selected = -1; need to keep the selected value for click event.
    	}
    }
    
    public ListItem getItem(int index) {
    	return (ListItem) getWidget(index);
    }
    
    static class Chevron extends HTML {    	
    	public Chevron() {
    		super("<div class=\"Chevron\"><span></span><span></span></div>");
    	}
    }

}
