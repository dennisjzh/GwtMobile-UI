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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;

public class HeaderPanel extends PanelBase {
   
	ClickHandler _leftButtonClickHandler;
	ClickHandler _rightButtonClickHandler;
	
    public HeaderPanel() {
    	super.add(new SimplePanel());	//left button placeholder
    	super.add(new FlowPanel());		//contents
    	super.add(new SimplePanel());	//right button placeholder
        setStyleName(Primary.HeaderPanel);
    }
    
    @Override
    public void add(Widget w) {
    	FlowPanel contents = (FlowPanel)getWidget(1);
    	contents.add(w);
    }
    
    public void setCaption(String caption) {
    	FlowPanel contents = (FlowPanel)getWidget(1);
    	contents.clear();
    	contents.add(new HTML(caption));
    }
    
    public String getCaption() {
    	FlowPanel contents = (FlowPanel)getWidget(1);
    	if (contents.getWidgetCount() > 0) {
        	HTML w = (HTML) contents.getWidget(0);
        	return w.getHTML();
    	}
    	return "";
    }
    
    public void setLeftButton(String buttonName) {
    	SimplePanel leftButton = (SimplePanel)getWidget(0);
    	ClickHandler clickHandler = new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				onLeftButtonClick(event);
			}
		};
    	if (buttonName.toUpperCase().equals("BACK")) {
    		leftButton.setWidget(new BackButton(buttonName, clickHandler));
    	}
    	else {
    		leftButton.setWidget(new Button(buttonName, clickHandler));
    	}
    }
    
    public void setRightButton(String buttonName) {
    	SimplePanel rightButton = (SimplePanel)getWidget(2);
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
    	SimplePanel leftButton = (SimplePanel)getWidget(0);
    	return (Button) leftButton.getWidget();
    }
    
    public Button getRightButton() {
    	SimplePanel rightButton = (SimplePanel)getWidget(2);
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
    
}
