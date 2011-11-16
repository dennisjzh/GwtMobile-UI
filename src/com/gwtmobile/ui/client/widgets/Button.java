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

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.resources.MobileResources;
import com.gwtmobile.ui.client.resources.MobileResources.IconImages;

public class Button extends HTML implements DragEventsHandler, IsGwtMobileWidget {
	
	public enum IconPositions { None, Left, Right, MiddleTop, MiddleBottom };
	
	private boolean enabled = true;
	private boolean showCaption = true;
	
	private IconPositions iconPosition = IconPositions.None;
	private IconImages iconImage = IconImages.None;

	private String caption = "";
	private String iconClass = "";
	
	private String imageUrl = "";
	
	public Button() {
        setStyleName("gwtm-Button");
    }
    
    public Button(String caption, ClickHandler handler) {
        this();
        this.caption = caption;
        this.addClickHandler(handler);
        this.setHTML(caption);
    }

    private void updateUi(){
    	
    	addStyleName("Button" + getIconPosition().toString());
    	
    	String butIconClass = null;
    	if (getIconImage().compareTo(IconImages.Custom) == 0){
    		butIconClass = getIconClass();
    	} else if (getIconImage().compareTo(IconImages.None) != 0) {
    		butIconClass = "ButtonIcon" + getIconImage().toString();
    	}
    	
    	Image butIcon = null;
    	if (getIconImage().compareTo(IconImages.Custom) != 0){
    		butIcon = new Image(MobileResources.IMAGE_MAP.get(getIconImage().toString()).getSafeUri());
    		butIcon.setStyleName("ButtonIcon"+getIconPosition().toString());
    		if (butIconClass!=null) butIcon.addStyleName(butIconClass);
    		if (getIconImage().compareTo(IconImages.None) == 0){
    			butIcon.getElement().setAttribute("style", "width:0px;margin:0px;");
    		}
    	}
    	super.setHTML("<div class=\"ButtonCaption\"><div class=\"ButtonCaption"+(isShowCaption()?"Visible":"Hidden")+"\">"+getCaption()+"</div>"+butIcon.toString()+"</div>");
    }
    
    public String getHTML(){
    	if (Beans.isDesignTime())
    		return caption;
    	return super.getHTML();
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        DragController.get().addDragEventsHandler(this);
        _widgetHelper.CheckInitialLoad(this);
    }
    
    @Override
    public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    	if (isEnabled()) {
            addStyleName("Pressed");
    	}
        e.stopPropagation();
    }

    @Override
    public void onDragMove(DragEvent e) {
    	if (isEnabled()) {
    		removeStyleName("Pressed");       
    	}
        e.stopPropagation();
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	if (isEnabled()) {
    		removeStyleName("Pressed");
    	}
    	else {
    		DragController.get().suppressNextClick();
    	}
        e.stopPropagation();
    }
    
    public void setEnabled(boolean enabled){
    	this.enabled = enabled;
    	if (isEnabled()){
    		removeStyleName("Disabled");
    	} else {
    		addStyleName("Disabled");
    	}
    }
    
    public boolean isEnabled(){
    	return this.enabled;
    }
    
    public IconPositions getIconPosition() {
		return this.iconPosition;
	}

	public void setIconPosition(IconPositions iconPosition) {
		this.iconPosition = iconPosition;
		updateUi();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
		updateUi();
	}
    
	public boolean isShowCaption() {
		return showCaption;
	}

	public void setShowCaption(boolean showCaption) {
		this.showCaption = showCaption;
		updateUi();
	}

	public IconImages getIconImage() {
		return iconImage;
	}

	public void setIconImage(IconImages iconImage) {
		this.iconImage = iconImage;
		updateUi();
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
		updateUi();
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		updateUi();
	}

	@Override
	public void onInitialLoad() {
	}

	@Override
	public void onTransitionEnd() {
	}

	@Override
	public void setSecondaryStyle(String style) {
		_widgetHelper.setSecondaryStyle(this, style);
	}

}
