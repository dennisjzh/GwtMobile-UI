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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.utils.Utils;

//FIXEME: extends WidgetBase?
public class FlipSwitch extends HTML 
	implements IsGwtMobileWidget, DragEventsHandler, ClickHandler, HasValueChangeHandlers<Boolean> {

	private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();

	private final static String HTML_CONTENT = "<div></div><div></div><div><div><div>ON</div><div></div><div>OFF</div></div></div>";
	protected boolean enabled = true;
	protected boolean value = true;
	protected boolean showText = true;

	public FlipSwitch() {
    	super(HTML_CONTENT);
        addClickHandler(this);
        setStyleName(StyleNames.Primary.FlipSwitch);
    }
    
    @Override
	public void onInitialLoad() {
    	if (!value) {
        	updateFlipPosition(0);
    	}
    }
    
    @Override
    public String getHTML(){
    	if (Beans.isDesignTime()) return "DO NOT EDIT";
    	return super.getHTML();
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
    	if (!enabled) {
    		return;
    	}
    	DragController.get().captureDragEvents(this);
    	Utils.setTransitionDuration(getFilpElement(), 0);
    }

    @Override
    public void onDragMove(DragEvent e) {
    	if (!enabled) {
    		return;
    	}
    	e.stopPropagation();
    	Element flip = getFilpElement();
    	int x = Utils.getTranslateX(flip);
    	int newX = (int) (x + e.OffsetX);
    	int onPosition = getOnPosition();
    	int offPosition = getOffPosition();
    	if (newX > onPosition) {
    		newX = onPosition;
    	}
    	else if (newX < offPosition) {
    		newX = offPosition;
    	}
    	if (newX != x) {
        	Utils.setTranslateX(flip, newX);
    	}
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	if (!enabled) {
    		return;
    	}
    	DragController.get().releaseDragCapture(this);
    	Element flip = getFilpElement();
    	int x = Utils.getTranslateX(flip);
    	int onPosition = getOnPosition();
    	int offPosition = getOffPosition();
    	if (x == onPosition) {
    		setValue(true, false, 0, true);
    	}
    	else if (x == offPosition) {
    		setValue(false, false, 0, true);
    	}
    	else {
    		float ratio = (float)x / (float)(offPosition - onPosition);
	    	boolean newValue = ratio < 0.5;
	    	int duration = (int) ((0.5 - Math.abs(ratio - 0.5)) * 200);
	    	Utils.Console("ratio " + ratio + " duration " + duration);
	    	setValue(newValue, true,  duration, true);
    	}
    }

    public void setValue(boolean value) {
    	setValue(value, false, 200, true);
    }
    
    public void setValue(boolean value, boolean fireEvent) {
    	setValue(value, false, 200, fireEvent);
    }
    
    private void setValue(boolean value, boolean forceUpdateFlipPosition, int duration, boolean fireEvent) {
    	if (this.value != value) {
        	this.value = value;
        	updateFlipPosition(duration);
        	if (fireEvent) {
            	ValueChangeEvent.fire(this, value);
        	}
    	}
    	else if (forceUpdateFlipPosition || Beans.isDesignTime()) {
        	updateFlipPosition(duration);    	
    	}
    }
    
    public boolean getValue() {
    	return value;
    }

    public boolean isEnabled() {
    	return enabled;
    }
    
    public void setEnabled(boolean enabled) {
    	if (this.enabled == enabled) {
    		return;
    	}
		if (enabled) { 
			removeStyleName("Disabled");
		}
		else { 
			addStyleName("Disabled");
		}
		this.enabled = enabled;
	}
    
	@Override
	public void onClick(ClickEvent event) {
		if (enabled) {
			setValue(!value);
		}
	}
	
	private void updateFlipPosition(int duration) {
    	Utils.setTransitionDuration(getFilpElement(), duration);
		Element flip = getFilpElement();

		// make the value be visible during design time
		if (Beans.isDesignTime()){
			Element flipDiv = (Element)flip.getChild(1);
			if (value) {
				flipDiv.removeAttribute("style");
			} else {
				flipDiv.setAttribute("style", "position:absolute; left:0; height:2em; margin-left:.5px;");
			}
		} else {
			if (value) {
				Utils.setTranslateX(flip, getOnPosition());
			} else {
				Utils.setTranslateX(flip, getOffPosition());
			}
		}
	}
	
	private Element getFilpElement() {
    	return (Element) getElement().getChild(2).getChild(0);
	}

	private int getOnPosition() {
		return 0;
	}
	
	private int getOffPosition() {
		Element flip = getFilpElement();
		Element parent = flip.getParentElement();
		int flipWidth = flip.getScrollWidth();
		int parentWidth = parent.getClientWidth();
		return parentWidth - flipWidth;
	}

    public boolean isShowText() {
		return showText;
	}

	public void setShowText(boolean showText) {
		
		if (this.showText != showText){
			setHTML(HTML_CONTENT.replaceAll("ON", "").replaceAll("OFF", ""));
		}
		
		this.showText = showText;
		
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setSecondaryStyle(String style) {
		_widgetHelper.setSecondaryStyle(this, style);
	}

}
