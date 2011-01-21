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

package com.gwtmobile.ui.client.page;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;

public abstract class Page extends Composite implements EventListener {

    private boolean _isInitialLoad = true;
    private Transition _transition;
    
    @Override
    protected void initWidget(Widget widget) {
    	super.initWidget(widget);    	
		setStyleName("Page");    
    }
    
    @Override
    public void onLoad() {
        if (_isInitialLoad) {
            onInitialLoad();
            _isInitialLoad = false;
        }
    }
    
	protected void onInitialLoad() {
	}
	
	protected void registerTransitionEndEvent() {
		Utils.addEventListenerOnce(this.getElement(), "webkitTransitionEnd", false, this);
	}
	
	@Override
	public void onBrowserEvent(Event e) {
		String type = e.getType();
		if (type.equals("webkitTransitionEnd")) {
		    onTransitionEnd();
		}		
	}
	
	protected void onTransitionEnd() {
	    final Page to, from;
	    if (PageHistory.current() != Page.this) {  //goto
	        from = PageHistory.current();
	        to = this;
	        PageHistory.add(to);
	        RootLayoutPanel.get().remove(from);
	        to.getTransition().remove(from, to);
	        //TODO: change to use scheduler deferred command.
	        Timer timer = new Timer() {                
                @Override
                public void run() {
                    to.onNavigateTo();
                }
            };
            timer.schedule(1);
	    }
	    else {             //goback
	        from = PageHistory.current();
	        PageHistory.back();
	        to = PageHistory.current();
	        RootLayoutPanel.get().remove(from);
	        from.getTransition().remove(from, to);
            Timer timer = new Timer() {                
                @Override
                public void run() {
                    to.onNavigateBack(from, PageHistory.getReturnValue());
                }
            };
            timer.schedule(1);
	    }       
	}
		
    protected void onNavigateTo() {
    }

    protected void onNavigateBack(Page from, Object object) {
    }

    public void goTo(final Page toPage, final Transition transition) {
	    final Page fromPage = this;
    	toPage.setTransition(transition);
    	transition.prepare(fromPage, toPage, false);
		RootLayoutPanel.get().add(toPage);
		//PageHistory.add(toPage);
        toPage.registerTransitionEndEvent();
		new Timer() {
            @Override
            public void run() {
            	transition.start(fromPage, toPage);
            }
		}.schedule(10);	//10ms instead of 1ms, to give iOS enough time to set the starting state.
	}

	public void goBack(Object returnValue) {
        final Page fromPage = this;
        PageHistory.setReturnValue(returnValue);
        final Page toPage = PageHistory.from();
		if (toPage == null) {
			// exit app here.
		    return;
		}
		final Transition transition = fromPage.getTransition();
    	transition.prepare(fromPage, toPage, true);
		RootLayoutPanel.get().add(toPage);
        fromPage.registerTransitionEndEvent();
        new Timer() {
            @Override
            public void run() {
            	transition.start(fromPage, toPage);
            }
        }.schedule(10);
	}
	
	void setTransition(Transition transition) {
		_transition = transition;
	}
	
	Transition getTransition() {
		return _transition;
	}
	
}
