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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;

public class ScrollPanel extends PanelBase 
implements HasWidgets, DragEventsHandler, SwipeEventsHandler {

//	Element _activeElement = null; 
	
    public ScrollPanel() {
        setStyleName("ScrollPanel");
    }
    
    @Override
	public void onLoad() {
        DragController.get().addDragEventsHandler(this);
        DragController.get().addSwipeEventHandler(this);
	}
	
	@Override
	public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
        DragController.get().removeSwipeEventHandler(this);
	}
	
	@Override
    public Widget getWidget() {
    	return _panel.getWidget(0);
    }
    
	public void reset() {
        setTransitionDuration(getWidget().getElement(), 0);
        setTranslateY(getWidget().getElement(), 0);
	}
	
	public void setPostionToTop() {
        setTransitionDuration(getWidget().getElement(), 0);
	    setTranslateY(getWidget().getElement(), 0);
	}
	
	public void setPositionToBottom() {
        setTransitionDuration(getWidget().getElement(), 0);
        setTranslateY(getWidget().getElement(), 
                this.getElement().getClientHeight() - this.getElement().getScrollHeight());
	}
	
	private native void setTranslateY(Element ele, double value) /*-{
		ele.style.webkitTransform = "translate3d(0px, " + value + "px ,0px)";
	}-*/;

	private native int getTranslateY(Element ele) /*-{
        var transform = ele.style.webkitTransform;
        var translateY = 0;        
        if (transform && transform !== "") {
            translateY = parseInt((/translate3d\(0px, (\-?.*)px, 0px\)/).exec(transform)[1]);
        }        
        return translateY;
    }-*/;

	private native int getMatrixY(Element ele) /*-{
		var matrix = new WebKitCSSMatrix(window.getComputedStyle(ele).webkitTransform);
		return matrix.f;
    }-*/;
	
	private native void setTransitionDuration(Element ele, double value) /*-{
		ele.style.webkitTransitionDuration = "" + value + "ms";
	}-*/;

	private native int getHeight(Element ele) /*-{
		return parseInt($doc.defaultView.getComputedStyle(ele, "").getPropertyValue("height"));
	}-*/;


	@Override
    public void onDragStart(DragEvent e) {
		Element element = getWidget().getElement();
		int matrix = getMatrixY(element);
		int current = getTranslateY(element);
		setTransitionDuration(element, 0);
		if (current != matrix) {  //scroll on going
			int diff = current - matrix;
			int offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			setTranslateY(element, matrix + offset);
			DragController.get().suppressNextClick();
		}
//		_activeElement = Utils.getActiveElement();
//		_activeElement.blur();
	}

	@Override
    public void onDragMove(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		int current = getTranslateY(widgetEle);
		if (current > 0) {//exceed top boundary
			if (e.OffsetY > 0) { 	//resist scroll down.
				current += e.OffsetY / 2;
			}
			else {				
				current += e.OffsetY * 2;				
			}
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			if (e.OffsetY < 0) { 	//resist scroll up.
				current += e.OffsetY / 2;
			}
			else {				
				current += e.OffsetY * 2;				
			}
		}
		else {
			current += e.OffsetY;
		}
		setTranslateY(widgetEle, current);		
	}

	@Override
    public void onDragEnd(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		int current = getTranslateY(widgetEle);
		if (current == 0) {
			return;
		}
		int panelHeight = getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		if (current > 0 //exceed top boundary
				|| panelHeight > widgetHeight) {
			setTransitionDuration(widgetEle, 500);
			setTranslateY(widgetEle, 0);
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			setTransitionDuration(widgetEle, 500);
			setTranslateY(widgetEle, panelHeight - widgetHeight);
		}
//		if (_activeElement != null) {
//			_activeElement.focus();
//		}
	}

	@Override
    public void onSwipeVertical(SwipeEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		long current = getTranslateY(widgetEle);
		if ((current >= 0) // exceed top boundary
			|| (-current + panelHeight >= widgetHeight)) { // exceed bottom boundary
			return;
		}
		
		double speed = e.getSpeed() * 2000;
		double dicstanceFactor = 0.5;
		double timeFactor = 2;
		long distance = (long) (speed / Math.abs(speed)) * Math.round(speed * speed * dicstanceFactor / 1000);
		long time =  Math.round(speed * speed * timeFactor / 2000);
		current += distance;
		if (current > 0) {//exceed top boundary
			double timeAdj = 1 - (double)current / distance;
			time = (long) (time * timeAdj * timeAdj);
			current = 0;
		}
		else if (-current + panelHeight > widgetHeight) { //exceed bottom boundary
			long bottom = panelHeight - widgetHeight;
			double timeAdj = 1 - (double)(current - bottom) / distance;
			time = (long) (time * timeAdj * timeAdj);
			current = bottom;
		}
		setTransitionDuration(widgetEle, time);
		setTranslateY(widgetEle, current);
	}

    @Override
    public void onSwipeHorizontal(SwipeEvent e) {
    }

	@Override
	public void add(Widget w) {
		assert _panel.getWidgetCount()  == 0 : "Can only add one widget to ScrollPanel.";
		super.add(w);
	}
}
