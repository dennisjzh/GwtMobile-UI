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

package com.gwtmobile.ui.client.panels;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class ScrollPanel extends HTMLFlowPanel implements HasWidgets, DragEventsHandler, SwipeEventsHandler {

	private boolean withPadding = true;
	private boolean hasTextBox = false;
	private boolean fullHeight = false;
	private Element scrollbar;
	
    public ScrollPanel() {
        setStyleName("gwtm-ScrollPanel");
        setWithPadding(withPadding);
        this.initScrollbar();
    }
    
	public boolean isWithPadding() {
		return withPadding;
	}

	public void setWithPadding(boolean withPadding) {
		this.withPadding = withPadding;
		if (withPadding){
			addStyleName("WithPanning");
		} else {
			removeStyleName("WithPanning");
		}
		
	}
    
    private void initScrollbar(){
        scrollbar=DOM.createDiv();
        scrollbar.appendChild(DOM.createDiv());
        scrollbar.setClassName("gwtm-Scrollbar");
        this.getElement().insertFirst(scrollbar);
		this.hideScrollBar();
    }
    
    private void adjustScrollbar(double scrollPannerPos){
		Element widgetEle = getWidget().getElement();
		int scrollPannelHeight = widgetEle.getOffsetHeight();		
		double scrollHeight=Utils.getHeight(this.scrollbar);
		
    	double scrollbarHeight=(scrollHeight*scrollHeight)/scrollPannelHeight;    	
    	if(scrollbarHeight>=scrollHeight){
    		this.hideScrollBar();
    		return;
    	}else{
    		this.showScrollbar();    		
    	}

    	double scrollbarPos=-scrollHeight*scrollPannerPos/scrollPannelHeight;

    	if(scrollPannerPos>0) {
    		scrollbarHeight=(scrollbarHeight-scrollPannerPos);
        	scrollbarPos=0;   	
    		if(scrollbarHeight<8) scrollbarHeight=8;
    	}else if((-scrollPannerPos+scrollHeight)>(scrollPannelHeight)){
    		double outScroll=(-scrollPannerPos+scrollHeight)-scrollPannelHeight;
    		scrollbarHeight=scrollbarHeight-outScroll;
    		if(scrollbarHeight<8) {
    			scrollbarHeight=8;
    		}else{
    			scrollbarPos=scrollbarPos+outScroll;
    		}
    		scrollbarPos=(scrollbarPos+scrollHeight)>scrollHeight?scrollHeight-scrollbarHeight:scrollbarPos;

    	}
    	
		Utils.setHeight(this.scrollbar.getFirstChildElement(), scrollbarHeight);
    	Utils.setTranslateY(this.scrollbar.getFirstChildElement(),scrollbarPos);
    }
    
    private void showScrollbar(){
    	this.scrollbar.setAttribute("style", "opacity:1");
    }
    
    private void hideScrollBar(){
    	this.scrollbar.setAttribute("style", "opacity:0");
    }
    
    public void setHasTextBox(boolean hasTextBox) {    	
		hasTextBox = hasTextBox && Utils.isAndroid();
	}

	public boolean getHasTextBox() {
		return hasTextBox;
	}

	public boolean isFullHeight() {
		return fullHeight;
	}

	public void setFullHeight(boolean fullHeight) {
		this.fullHeight = fullHeight;
		if (fullHeight){
			addStyleName("fullHeight");
		} else { 
			removeStyleName("fullHeight");
		}
	}	
	
    @Override
	public void onLoad() {
        DragController.get().addDragEventsHandler(this);
        DragController.get().addSwipeEventsHandler(this);
	}
	
	@Override
	public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
        DragController.get().removeSwipeEventHandler(this);
	}
	
	
    public Widget getWidget() {
    	//if (Beans.isDesignTime() an
		return getIntPanel();
    	//return super.getWidget(0);
    }
    
	public void reset() {
        Utils.setTransitionDuration(getWidget().getElement(), 0);
        Utils.setTranslateY(getWidget().getElement(), 0);
	}
	
	public void setPostionToTop() {
        Utils.setTransitionDuration(getWidget().getElement(), 0);        
	    Utils.setTranslateY(getWidget().getElement(), 0);
	}
	
	public void setPositionToBottom() {
        Utils.setTransitionDuration(getWidget().getElement(), 0);

        Utils.setTranslateY(getWidget().getElement(), 
                this.getElement().getClientHeight() - this.getElement().getScrollHeight());
	}
	
	public void setScrollPosition(int pos) {
		if (hasTextBox) {
			setStyleTop(pos);
		}
		else {
			Element element = getWidget().getElement();
			Utils.setTranslateY(element, pos);
		}
		this.adjustScrollbar(pos);
	}

	public int getScrollPosition() {
		if (hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return Utils.getTranslateY(element);
		}
	}
	
	public int getScrollToPosition() {
		if (hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return Utils.getMatrixY(element);
		}
	}
	
	@Override
    public void onDragStart(DragEvent e) {
		int matrix = getScrollToPosition();
		int current = getScrollPosition();
		Utils.setTransitionDuration(getWidget().getElement(), 0);
		Utils.setTransitionDuration(this.scrollbar.getFirstChildElement(), 0);

		if (current != matrix) {  //scroll on going
			int diff = current - matrix;
			int offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			setScrollPosition(matrix + offset);
			DragController.get().suppressNextClick();
		}
	}

	@Override
    public void onDragMove(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		int current = getScrollPosition();
		if (current > 0) {//exceed top boundary
			if (e.OffsetY > 0) { 	//resist scroll down.
				current += (int)(e.OffsetY / 2);	// need the cast for production mode.
			}
			else {				
				current += e.OffsetY * 2;				
			}
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			if (e.OffsetY < 0) { 	//resist scroll up.
				current += (int)(e.OffsetY / 2);
			}
			else {				
				current += e.OffsetY * 2;				
			}
		}
		else {
			current += e.OffsetY;
		}
		
		setScrollPosition(current);	
	}

	@Override
    public void onDragEnd(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		int current = getScrollPosition();
		if (current == 0) {
			return;
		}
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		if (current > 0 //exceed top boundary
				|| panelHeight > widgetHeight) {
			Utils.setTransitionDuration(widgetEle, 500);
			Utils.setTransitionDuration(this.scrollbar.getFirstChildElement(), 500);
			setScrollPosition(0);
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			Utils.setTransitionDuration(widgetEle, 500);
			Utils.setTransitionDuration(this.scrollbar.getFirstChildElement(), 500);
			setScrollPosition(panelHeight - widgetHeight);
		}
		this.hideScrollBar();
		
	}

	@Override
    public void onSwipeVertical(SwipeEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		long current = getScrollPosition();
		if ((current >= 0) // exceed top boundary
			|| (-current + panelHeight >= widgetHeight)) { // exceed bottom boundary
			return;
		}
		
		double speed = e.getSpeed();		
		double timeFactor = 3000;
		long time =  (long) Math.abs(speed * timeFactor);
		double dicstanceFactor = 0.25;
		long distance = (long) (speed * time * dicstanceFactor);
		//Utils.Console("speed " + speed + " time " + time + " distance " + distance + " current " + current);
		current += distance;
		if (current > 0) {//exceed top boundary
			double timeAdj = 1 - (double)current / distance;
			time = (long) (time * timeAdj);
			current = 0;
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			long bottom = panelHeight - widgetHeight;
			double timeAdj = 1 - (double)(current - bottom) / distance;
			time = (long) (time * timeAdj);
			current = bottom;
		}
		Utils.setTransitionDuration(widgetEle, time);
		Utils.setTransitionDuration(this.scrollbar.getFirstChildElement(), time);
		Utils.addEventListenerOnce(this.scrollbar.getFirstChildElement(), 
				"webkitTransitionEnd", false, new EventListener() {
					@Override
					public void onBrowserEvent(Event event) {
				    	  hideScrollBar();
					}
				});
		setScrollPosition((int) current);
	}

    @Override
    public void onSwipeHorizontal(SwipeEvent e) {
    }

	@Override
	public void add(Widget w) {
		//assert _panel.getWidgetCount()  == 0 : "Can only add one widget to ScrollPanel.";
		super.add(w);
		if (Utils.isIOS()) {
			Utils.setTranslateY(w.getElement(), 0); //anti-flickering on iOS.
		}
	}
	
	private int getStyleTop() {
		Style style = getWidget().getElement().getStyle();
		String top = style.getTop();
		if (top.isEmpty()) {
			return 0;
		}
		else {
			return Integer.parseInt(top.replace("px", ""));
		}
	}

	private void setStyleTop(int top) {
		Style style = getWidget().getElement().getStyle();
		style.setTop(top, Unit.PX);
	}
	
}
