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
import java.util.Iterator;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;
import com.gwtmobile.ui.client.page.Transition;

public class SlidePanel extends PanelBase implements SwipeEventsHandler, HasValueChangeHandlers<Boolean> {

    protected int _count = 0;
    private int _currentSlideIndex = 0;
    protected SlideProvider _slideProvider = null;
    protected ArrayList<Widget> _slides = new ArrayList<Widget>();
    protected boolean _rotate = false;
    private int _selectedSlideIndex = 0; //used for design time slide selection.
    HasWidgetsImpl _hasWidgetsImpl;

    public SlidePanel() {
        setStyleName(Primary.SlidePanel);
        _hasWidgetsImpl = new HasWidgetsImpl();
    }

    // expose parent HasWidgets logic for transition
    private class HasWidgetsImpl implements HasWidgets {

		@Override
		public void add(Widget w) {
			SlidePanel.super.add(w);
		}

		@Override
		public void clear() {
			SlidePanel.super.clear();
		}

		@Override
		public Iterator<Widget> iterator() {
			return SlidePanel.super.iterator();
		}

		@Override
		public boolean remove(Widget w) {
			return SlidePanel.super.remove(w);
		}
    	
    }
    
    @Override
    protected String getDesignTimeMessage() {
    	return "Add Slide widgets to the panel. Use the 'selectedSlideIndex' property to switch slide at design time.";
    }
    
    public void setSlideCount(int count) {
    	this._count = count;
    }
    
    public int getSlideCount() {
    	return _count > 0 ? _count : _slides.size();
    }
    
    public void setSlideProvider(SlideProvider provider) {
		_slideProvider = provider;
	}

	public SlideProvider getSlideProvider() {
		return _slideProvider;
	}

	@Override
	public void onInitialLoad() {
    	super.onInitialLoad();
		// Use currentSlide as design time slide selector.
    	if (Beans.isDesignTime()) {
    		_currentSlideIndex = _selectedSlideIndex;
    	}
    	Slide slide = getSlide(_currentSlideIndex);
		if (slide != null) {
			super.add(slide);
		}
    }

	public Slide getSlide(int index) {
		Slide slide = null;
    	if (_slideProvider != null) {
    		slide = _slideProvider.loadSlide(index);
    	}
		if (slide == null && index < _slides.size() ) {
			slide = (Slide) _slides.get(index);
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
		if (_currentSlideIndex >= getSlideCount() - 1) {
			if (!_rotate) {
				return;
			} else {
				_currentSlideIndex = -1;
			}
		}
		moveToSlide(_currentSlideIndex + 1);
	}

	public void previous() {
		if (_currentSlideIndex <= 0) {
			if (!_rotate) {
				return;
			} else {
				_currentSlideIndex = getSlideCount();
			}
		}
		moveToSlide(_currentSlideIndex - 1);
	}

	protected void moveToSlide(int slide) {
		boolean reverse = _currentSlideIndex > slide; 
		_currentSlideIndex = slide;
		Slide to = getSlide(slide);
    	Slide from = (Slide) super.getWidget(0);
    	Transition transition = Transition.SLIDE;
    	ValueChangeEvent.fire(this, false);
    	transition.start(from, to, _hasWidgetsImpl, reverse);
	}

	public void goTo(int to) {
		if (to > 0 && to < getSlideCount()) {
			moveToSlide(to);
		}
	}
	
	@Override
	public void add(Widget w) {
		if (w instanceof Slide) {
			_slides.add(w);
		}
		else if (isDesignTimeEmptyLabel(w)) {
			super.add(w);			
		}
		else {
			assert (w instanceof Slide) : "Can only add Slide widgets to SlidePanel.";
		}
	}	

	public void setRotate(boolean rotate)
	{
		this._rotate = rotate;
	}
	
	public boolean isRotate()
	{
	    return _rotate;
	}

	public void setCurrentSlideIndex(int currentSlideIndex) {
		this._currentSlideIndex = currentSlideIndex;
	}

	public int getCurrentSlideIndex() {
		return _currentSlideIndex;
	}

	public void setSelectedSlideIndex(int selectedSlideIndex) {
		this._selectedSlideIndex = selectedSlideIndex;
	}

	public int getSelectedSlideIndex() {
		return _selectedSlideIndex;
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