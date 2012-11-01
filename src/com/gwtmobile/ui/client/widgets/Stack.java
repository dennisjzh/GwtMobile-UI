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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.utils.Utils;

public class Stack extends PanelBase {

    StackHeader _header;
    StackContent _content;    
    public enum StackInitialState { Default, Collapsed, Expanded };
    private StackInitialState _initialState = StackInitialState.Default;
    private boolean _freezeState = false;
    
    @Override
    protected String getDesignTimeMessage() {
    	return "Add a StackHeader widget and a StackConent widget.";
    }

	@Override
	public void onInitialLoad() {
		switch (_initialState) {
			case Collapsed:
				collapse();
				break;
			case Expanded:
				expand();
				break;
			default:
				if (Beans.isDesignTime()) {
					expand();
				}
				else {
					collapse();
				}
		}
	}
	
    @Override
    public void add(Widget w) {
        if (w instanceof StackHeader) {
        	if (_header == null) {
            	_header = (StackHeader) w;
        	}
        	else {
        		assert false: "Stack widget can only contain one StackHeader widget.";
        	}
        }
        else if (w instanceof StackContent) {
        	if (_content == null) {
            	_content = (StackContent) w;
        	}
        	else {
        		assert false: "Stack widget can only contain one StackContent widget.";
        	}
        }
        else if (!isDesignTimeEmptyLabel(w)) {
    		assert false: "Stack widget can only contain a StackHeader widget a StackContent widget.";
        }
        super.add(w);
    }

    public void collapse() {
        this.addStyleName(Secondary.Collapsed);
		this.removeStyleName(Secondary.Expanded);
		Element focus = Utils.getActiveElement();
		focus.blur();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {			
			@Override
			public void execute() {
				if (_content != null) 
					_content.setHeight("0px");
			}
		});
    }

    public void expand() {
    	if (isExpended()) {
    		return;
    	}
        Stack.this.addStyleName(Secondary.Expanded);
        Stack.this.removeStyleName(Secondary.Collapsed);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {			
			@Override
			public void execute() {
		        _content.setHeight(_content.getElement().getScrollHeight() - Utils.getPaddingHeight(_content.getElement()) + "px");
			}
		});
    }
    
    public void toggle() {
        if (isFreezeState()) return;
    	if (!isExpended()) {
        	expand();
        } else {
        	collapse();
        }        
    }
    
    public boolean isExpended() {
    	return this.getStyleName().indexOf(Secondary.Expanded) > -1;
    }
    
    public StackHeader getHeader() {
        return _header;
    }
    
    public StackContent getContent() {
        return _content;
    }

	public StackInitialState getInitialState() {
		return _initialState;
	}

	public void setInitialState(StackInitialState initialState) {
		this._initialState = initialState;
	}

	public boolean isFreezeState() {
		return _freezeState;
	}

	public void setFreezeState(boolean freezeState) {
		this._freezeState = freezeState;
	}

    
}
