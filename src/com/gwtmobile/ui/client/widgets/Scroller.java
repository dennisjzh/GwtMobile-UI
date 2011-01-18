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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.CSS;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;

//TODO: change base to WidgetBase?
public class Scroller extends FlowPanel 
implements  DragEventsHandler, SwipeEventsHandler {

    private boolean _isInitialLoad = true;
    
    @Override
	public void onLoad() {
	    if (_isInitialLoad) {
	        _isInitialLoad = false;
	        getWidget().addStyleName(CSS.Transitions.scroll());
	        setHeight("100%");
	        setWidth("100%");
	    }
        DragController.get().addDragEventsHandler(this);
        DragController.get().addSwipeEventHandler(this);
	}
	
    private Widget getWidget() {
    	return getWidget(0);
    }
	@Override
	public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
        DragController.get().removeSwipeEventHandler(this);
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


	@Override
    public void onDragStart(DragEvent e) {
		int matrix = getMatrixY(getWidget().getElement());
		int current = getTranslateY(getWidget().getElement());
		if (current != matrix) {  //scroll on going
			setTransitionDuration(getWidget().getElement(), 0);
			int diff = current - matrix;
			int offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			setTranslateY(getWidget().getElement(), matrix + offset);
			DragController.get().suppressClickEvent();
		}
		else {
            setTransitionDuration(getWidget().getElement(), 0);
		}
	}

	@Override
    public void onDragMove(DragEvent e) {
		int current = getTranslateY(getWidget().getElement());
		if (current > 0) {//exceed top boundary
			if (e.OffsetY > 0) { 	//resist scroll down.
				current += e.OffsetY / 2;
			}
			else {				
				current += e.OffsetY * 2;				
			}
		}
		else if (-current + this.getElement().getClientHeight() > this.getElement().getScrollHeight()) { //exceed bottom boundary
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
		setTranslateY(getWidget().getElement(), current);		
	}

	@Override
    public void onDragEnd(DragEvent e) {
		int current = getTranslateY(getWidget().getElement());
		if (current > 0) {//exceed top boundary
			setTransitionDuration(getWidget().getElement(), 500);
			setTranslateY(getWidget().getElement(), 0);
		}
		else if (-current + this.getElement().getClientHeight() > this.getElement().getScrollHeight()) { //exceed bottom boundary
			setTransitionDuration(getWidget().getElement(), 500);
			setTranslateY(getWidget().getElement(), this.getElement().getClientHeight() - this.getElement().getScrollHeight());
		}
	}

	@Override
    public void onSwipeVertical(SwipeEvent e) {
		long current = getTranslateY(getWidget().getElement());
		if ((current >= 0) // exceed top boundary
				|| (-current + this.getElement().getClientHeight() >= this
						.getElement().getScrollHeight())) { // exceed bottom
															// boundary
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
		else if (-current + this.getElement().getClientHeight() > this.getElement().getScrollHeight()) { //exceed bottom boundary
			long bottom = this.getElement().getClientHeight() - this.getElement().getScrollHeight();
			double timeAdj = 1 - (double)(current - bottom) / distance;
			time = (long) (time * timeAdj * timeAdj);
			current = bottom;
		}
		setTransitionDuration(getWidget().getElement(), time);
		setTranslateY(getWidget().getElement(), current);
	}

    @Override
    public void onSwipeHorizontal(SwipeEvent e) {
    }
	
//	private native void setupTransition(Element element) /*-{
//		element.style.webkitTransitionProperty = '-webkit-transform';
//		element.style.webkitTransitionTimingFunction = 'cubic-bezier(0,0,0.25,1)';
//		element.style.webkitTransitionDuration = '250ms';
//	}-*/;

//	@Override
//	public void onBrowserEvent(Event e) {
//		String type = e.getType();
//		if (type.equals("webkitTransitionEnd")) {
//		    e.preventDefault();
//		    e.stopPropagation();
//			onTransitionEnd(e);
//		}
//	}
//
//	private void onTransitionEnd(Event e) {
//	    DragController.get().resume();
//        setTransitionDuration(getWidget().getElement(), 0);
//		Utils.Console("transitionended" + _count++);
//	}
}
