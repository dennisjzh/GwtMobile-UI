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
import java.util.ArrayList;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;
import com.gwtmobile.ui.client.page.Transition;
import com.gwtmobile.ui.client.utils.Utils;

public class SlidePanel extends PanelBase implements SwipeEventsHandler, HasValueChangeHandlers<Boolean> {

    protected int count = 0;
    private int currentSlide = 0;
    protected SlideProvider slideProvider = null;
    protected ArrayList<Widget> slides = new ArrayList<Widget>();
    protected boolean rotate = false;

    public SlidePanel() {
        setStyleName(Primary.SlidePanel);
        addDesignTimeMessage("Add Slides to the panel.");
        addDesignTimeMessage("Set the 'currentSlide' property to switch slide.");
    }

    public void setSlideCount(int count) {
    	this.count = count;
    }
    
    public int getSlideCount() {
    	return count > 0 ? count : slides.size();
    }
    
    public void setSlideProvider(SlideProvider provider) {
		slideProvider = provider;
	}

	public SlideProvider getSlideProvider() {
		return slideProvider;
	}

	@Override
	public void onInitialLoad() {
    	super.onInitialLoad();
    	Slide slide = getSlide(currentSlide);
		if (slide != null) {
			super.add(slide);
		}
    }

	public Slide getSlide(int index) {
		Slide slide = null;
    	if (slideProvider != null) {
    		slide = slideProvider.loadSlide(index);
    	}
		if (slide == null && index < slides.size() ) {
			slide = (Slide) slides.get(index);
		}
		return slide;
	}
    
	@Override
	public void onLoad() {
		super.onLoad();		
        DragController.get().addSwipeEventsHandler(this);
	}
	
	@Override
	protected void onUnload() {
        DragController.get().removeSwipeEventHandler(this);
		super.onUnload();
	}
	
	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
		if (e.getSpeed() < 0) { //swipe to next
			next();
		}
		else {					//swipe to previous
			previous();
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
	}
	
	public void next() {
		if (currentSlide >= getSlideCount() - 1) {
			if (!rotate) {
				return;
			} else {
				currentSlide = -1;
			}
		}
		currentSlide++;
    	moveNext();
	}

	protected void moveNext() {
		Slide to = getSlide(currentSlide);
    	Slide from = (Slide) super.getWidget(0);
    	Transition transition = Transition.SLIDE;
    	ValueChangeEvent.fire(this, true);
    	transition.start(from, to, this, false);
	}

	public void previous() {
		if (currentSlide <= 0) {
			if (!rotate) {
				return;
			} else {
				currentSlide = getSlideCount();
			}
		}
		currentSlide--;
		movePrevious();
	}

	protected void movePrevious() {
		Slide to = getSlide(currentSlide);
    	Slide from = (Slide) super.getWidget(0);
    	Transition transition = Transition.SLIDE;
    	ValueChangeEvent.fire(this, false);
    	transition.start(from, to, this, true);
	}

	@Override
	public void onTransitionEnd(TransitionDirection direction) {
		super.onTransitionEnd(direction);
		if (direction == TransitionDirection.To) {
			super.remove(0);
		}
	}
	
	public int getCurrentSlideIndex() {
		return currentSlide;
	}
	
	@Override
	public void add(Widget w) {
		//assert (w instanceof Slide) : "Can only add Slide widgets to SlidePanel.";
		// we can't assert because gwtdesign adds a Label by default
		if (Beans.isDesignTime()) {
			if (!(w instanceof Slide)) {
				addDesignTimeError("Can only add Slides to SlidePanel. Remove the " + 
						Utils.getSimpleName(w.getClass()) + " widget.");
				return;
			}
		}
		slides.add(w);
	}

	public void setRotate(boolean rotate)
	{
		this.rotate = rotate;
	}
	
	public boolean isRotate()
	{
	    return rotate;
	}

	public void setCurrentSlide(int currentSlide) {
		this.currentSlide = currentSlide;
	}

	public int getCurrentSlide() {
		return currentSlide;
	}

	/********************* interface SlideProvider *******************/

	public interface SlideProvider {
		Slide loadSlide(int index);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}
}