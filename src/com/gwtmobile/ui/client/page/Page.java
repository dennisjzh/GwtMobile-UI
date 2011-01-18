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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.CSS;
import com.gwtmobile.ui.client.utils.Utils;

public abstract class Page extends SimplePanel implements EventListener {

    private boolean isInitialLoad = true;
    public Page() {
    	//TODO: fixed animation?
        setStyleName(CSS.Transitions.slide());
    }
    
    @Override
    public void onLoad() {
        if (isInitialLoad) {
            onInitialLoad();
            isInitialLoad = false;
        }
    }
    
	protected void onInitialLoad() {
	}
	
	@Override
	public void setWidget(Widget w) {
		super.setWidget(w);
		fillScreen(w);
	}
	
	private void fillScreen(Widget w) {
		w.setWidth("100%");
		w.setHeight("100%");
	}

	protected void registerTransitionEndEvent() {
	    Utils.Console("******** T event registered for " + this.getClass().toString());
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
	    if (PageHistory.current() == Page.this) {  //goto
	        from = PageHistory.from();
	        to = PageHistory.current();
	        RootLayoutPanel.get().remove(from);
	        Timer timer = new Timer() {                
                @Override
                public void run() {
                    to.onNavigateTo();
                }
            };
            timer.schedule(10);
	    }
	    else {             //goback
	        from = PageHistory.current();
	        PageHistory.back();
	        to = PageHistory.current();
	        RootLayoutPanel.get().remove(from);
            Timer timer = new Timer() {                
                @Override
                public void run() {
                    to.onNavigateBack(from, PageHistory.getParameter());
                }
            };
            timer.schedule(10);
	    }       
	}
		
    protected void onNavigateTo() {
    }

    protected void onNavigateBack(Page from, Object object) {
    }

    public void goTo(final Page toPage) {
	    final Page fromPage = this;
	    toPage.setTranslateX(toPage.getElement(), Window.getClientWidth());
		RootLayoutPanel.get().add(toPage);
		PageHistory.add(toPage);
        //toPage.registerTransitionEndEvent();
        Utils.Console(Window.getClientWidth() + " screen width.");
//		Timer timer = new Timer() {
//            @Override
//            public void run() {
                fromPage.setTranslateX(fromPage.getElement(), 0 - Window.getClientWidth());
                toPage.setTranslateX(toPage.getElement(), 0);
                toPage.onTransitionEnd();
//            }
//		};
//		timer.schedule(1);
	}

	public void goBack(Object parameter) {
        final Page fromPage = this;
        PageHistory.setParameter(parameter);
        final Page toPage = PageHistory.from();
		if (toPage == null) {
			// exit app here.
		    return;
		}
        toPage.setTranslateX(toPage.getElement(), 0 - Window.getClientWidth());
		RootLayoutPanel.get().add(toPage);
//        toPage.registerTransitionEndEvent();
//        Timer timer = new Timer() {
//            @Override
//            public void run() {
                fromPage.setTranslateX(fromPage.getElement(), Window.getClientWidth());
                toPage.setTranslateX(toPage.getElement(), 0);
                toPage.onTransitionEnd();
//            }
//        };
//        timer.schedule(1);
	}
	
	private native void setTranslateX(Element ele, double value) /*-{
       ele.style.webkitTransform = "translate3d(" + value + "px ,0px, 0px)";
    }-*/;

	
}
