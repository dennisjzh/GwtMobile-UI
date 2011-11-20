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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.utils.Utils;

public class Stack extends PanelBase {

    StackHeader header;
    StackContent content;
    
    public enum StackInitialState {Collapsed, Expanded};
    
    private StackInitialState initialState = StackInitialState.Collapsed;
    private boolean freezeState = false;
    
	@Override
	public void onInitialLoad() {
		//addStyleName("gwtm-Stack");
		if (initialState == StackInitialState.Collapsed){
			collapse();
		} else {
			expand();
		}
	}
	
    @Override
    public void add(Widget w) {
    	
//    	if (w instanceof com.google.gwt.user.client.ui.Label) return;
    	
//    	if ( !(w instanceof AccordionHeader) && !(w instanceof AccordionContent)) 
//    		assert false : "Can only contain a header and a content. " + w.getClass().getName() + " - " + super.getWidgetCount();
    	
//    	//if (w instanceof Label){
//    		// allow Label to temporary build on GWT designer
//    	} else {
//	        if (header == null) {
//	            header = (AccordionHeader) w;
//	        }
//	        else if (content != null) {
//	            assert false : "Can only contain a header and a content.";
//	        }
//	        else {
//	            content = (AccordionContent) w;
//	        }
//    	}
        if (w instanceof StackHeader) {
        	header = (StackHeader) w;
        }
        else if (w instanceof StackContent) {
        	content = (StackContent) w;
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
				if (content != null) 
					content.setHeight("0px");
			}
		});
    }

    public void expand() {
    	if (isExpended()) {
    		return;
    	}
        this.addStyleName(Secondary.Expanded);
        this.removeStyleName(Secondary.Collapsed);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {			
			@Override
			public void execute() {
				Utils.Console(content.getElement().getScrollHeight() - content.getElement().getOffsetHeight() + "px");
		        content.setHeight(content.getElement().getScrollHeight() - content.getElement().getOffsetHeight()+ "px");
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
        return header;
    }
    
    public StackContent getContent() {
        return content;
    }

	public StackInitialState getInitialState() {
		return initialState;
	}

	public void setInitialState(StackInitialState initialState) {
		this.initialState = initialState;
	}

	public boolean isFreezeState() {
		return freezeState;
	}

	public void setFreezeState(boolean freezeState) {
		this.freezeState = freezeState;
	}

    
}
