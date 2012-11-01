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
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;


public class StackHeader extends PanelBase implements ClickHandler, DragEventsHandler {

	private StackArrow _stackArrow = new StackArrow();
	private boolean _showArrow = true;
	private boolean _enabled = true;
	
	public StackHeader() {
	    this.addDomHandler(this, ClickEvent.getType());    
        this.add(_stackArrow);

	}
	
	public boolean isShowArrow() {
		return _showArrow;
	}

	public void setShowArrow(boolean showArrow) {
		if (isShowArrow() != showArrow){
			if (showArrow) {
				this.add(_stackArrow);
			} else {
				this.remove(_stackArrow);
			}
			this._showArrow = showArrow;
		}
	}


	public boolean isEnabled() {
		return _enabled;
	}

	//TODO: fix me
	public void setEnabled(boolean enabled) {
		removeStyleName(""+this._enabled);
		this._enabled = enabled;
		addStyleName(""+this._enabled);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
        DragController.get().addDragEventsHandler(this);
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();
        DragController.get().removeDragEventsHandler(this);
	}
	
    @Override
    public void onClick(ClickEvent event) {
    	if (isEnabled()) {
    		Stack parent = (Stack) this.getParent();
    		parent.toggle();
    	}
    }
    
	@Override
	public void onDragEnd(DragEvent e) {
		if (isEnabled()) removeStyleName(Secondary.Pressed);
	}

	@Override
	public void onDragMove(DragEvent e) {
		if (isEnabled()) removeStyleName(Secondary.Pressed);
	}

	@Override
	public void onDragStart(DragEvent e) {
		if (isEnabled()) addStyleName(Secondary.Pressed);		
	}

	
    static class StackArrow extends HTML {    	
    	public StackArrow() {
    		setStyleName(Primary.StackArrow);
    	}
    }


}
