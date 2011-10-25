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

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.CSS.SubStyleNames;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.page.PageHistory.HistoryChangedHandler;

public class HeaderPanel extends WidgetBase implements HasWidgets {
   
	ClickHandler _leftButtonClickHandler;
	ClickHandler _rightButtonClickHandler;
	
	private String leftButtonCaption;	
	
    public HeaderPanel() {
    	FlowPanel container = new FlowPanel();
    	container.add(new SimplePanel());	//left button placeholder
    	container.add(new FlowPanel());		//contents
    	container.add(new SimplePanel());	//right button placeholder
        initWidget(container);
        setStyleName(StyleNames.HeaderPanel);
        PageHistory.Instance.addHistoryChangedHandler(new HistoryChangedHandler() {
			
			@Override
			public void onHistoryChanged(Page from, Page to, boolean forward) {
				if ("BACK".equalsIgnoreCase(leftButtonCaption)) {
					updateLeftButton();
				}
			}
		});
    }
    
    @Override
    public void add(Widget w) {
    	FlowPanel contents = ((FlowPanel)((FlowPanel)getWidget()).getWidget(1));
    	contents.add(w);
    }
    
    public void setCaption(String caption) {
    	FlowPanel contents = ((FlowPanel)((FlowPanel)getWidget()).getWidget(1));
    	contents.clear();
    	contents.add(new HTML(caption));
    }
    
    public String getCaption() {
    	FlowPanel contents = ((FlowPanel)((FlowPanel)getWidget()).getWidget(1));
    	if (contents.getWidgetCount() > 0) {
        	HTML w = (HTML) contents.getWidget(0);
        	return w.getHTML();
    	}
    	return "";
    }
    
    public void setLeftButton(String buttonName) {
    	leftButtonCaption = buttonName;
    	updateLeftButton();
    }
    
    public void updateLeftButton() {
    	SimplePanel leftButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(0));
		Button leftButtonWidget = (Button) leftButton.getWidget();
		if (leftButtonWidget == null) { // create new button
	    	ClickHandler clickHandler = new ClickHandler() {				
				@Override
				public void onClick(ClickEvent event) {
					onLeftButtonClick(event);
				}
			};
	    	if ("BACK".equalsIgnoreCase(leftButtonCaption)) {
	    		if (PageHistory.Instance.from() != null) { // back history present
	    			leftButton.setWidget(new BackButton(leftButtonCaption, clickHandler));
	    		}
	    	} else {
	    		leftButton.setWidget(new Button(leftButtonCaption, clickHandler));
	    	}
		} else { // check for back button
	    	if ("BACK".equalsIgnoreCase(leftButtonCaption)) {
	    		if (PageHistory.Instance.from() == null) { // back history not present
	    			leftButtonWidget.addStyleName(SubStyleNames.hidden);
	    		} else {
	    			leftButtonWidget.removeStyleName(SubStyleNames.hidden);	    			
	    		}
	    	}
		}
    }
    
    public void setRightButton(String buttonName) {
    	SimplePanel rightButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(2));
    	ClickHandler clickHandler = new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				onRightButtonClick(event);
			}
		};
    	if (buttonName.toUpperCase().equals("NEXT")) {
    		rightButton.setWidget(new NextButton(buttonName, clickHandler));
    	}
    	else {
    		rightButton.setWidget(new Button(buttonName, clickHandler));
    	}
    }
    
    public Button getLeftButton() {
    	SimplePanel leftButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(0));
    	return (Button) leftButton.getWidget();
    }
    
    public Button getRightButton() {
    	SimplePanel rightButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(2));
    	return (Button) rightButton.getWidget();
    }
    
    void onLeftButtonClick(ClickEvent event) {
    	if (_leftButtonClickHandler != null) {
        	_leftButtonClickHandler.onClick(event);
    	}
    	else {
    		Button leftButton = getLeftButton();
    		if (leftButton != null && leftButton.getClass() == BackButton.class) {
    			((BackButton)leftButton).onClick(event);
    		}
    	}
    }
    
    void onRightButtonClick(ClickEvent event) {    	
    	if (_rightButtonClickHandler != null) {
        	_rightButtonClickHandler.onClick(event);
    	}
    }
    
    public void setLeftButtonClickHandler(ClickHandler handler) {
    	_leftButtonClickHandler = handler;
    }
    
    public void setRightButtonClickHandler(ClickHandler handler) {
    	_rightButtonClickHandler = handler;
    }
    
    @Override
    public void clear() {
        
    }

    @Override
    public Iterator<Widget> iterator() {
        return null;
    }

    @Override
    public boolean remove(Widget w) {
        return false;
    }
    
}
