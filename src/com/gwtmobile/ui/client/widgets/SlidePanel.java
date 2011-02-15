package com.gwtmobile.ui.client.widgets;

import java.util.Iterator;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.SwipeEvent;
import com.gwtmobile.ui.client.event.SwipeEventsHandler;
import com.gwtmobile.ui.client.page.Transition;

public class SlidePanel extends WidgetBase implements HasWidgets, SwipeEventsHandler {

    protected FlowPanel _panel = new FlowPanel();
    protected int _count = 0;
    protected int _current = 0;
    protected SlideProvider _slideProvider = null;


    public SlidePanel() {
        initWidget(_panel);
        setStyleName("SlidePanel");
    }

    public void setSlideCount(int count) {
    	_count = count;
    }
    
    public int getSlideCount() {
    	return _count;
    }
    
    public void setSlideProvider(SlideProvider provider) {
		_slideProvider = provider;
	}

	public SlideProvider getSlideProvider() {
		return _slideProvider;
	}

	@Override
    protected void onInitialLoad() {
    	super.onInitialLoad();
    	if (_slideProvider != null) {
    		_current = 0;
        	Slide slide = _slideProvider.loadSlide(_current);
        	_panel.add(slide);
    	}
    }
    
	@Override
	public void onLoad() {
		super.onLoad();		
        DragController.get().addSwipeEventHandler(this);
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
		if (_current >= _count - 1) {
			return;
		}
		_current++;
    	Slide to = _slideProvider.loadSlide(_current);
    	Slide from = (Slide) _panel.getWidget(0);
    	Transition transition = Transition.SLIDE;
    	transition.start(from, to, this, false);
	}

	public void previous() {
		if (_current <= 0) {
			return;
		}
		_current--;
		Slide to = _slideProvider.loadSlide(_current);
    	Slide from = (Slide) _panel.getWidget(0);
    	Transition transition = Transition.SLIDE;
    	transition.start(from, to, this, true);
	}

	@Override
	public void onTransitionEnd() {
		super.onTransitionEnd();
		_panel.remove(0);
	}
	
	public int getCurrentSlide() {
		return _current;
	}
	
	@Override
	public void add(Widget w) {
		_panel.add(w);
	}

	@Override
	public void clear() {
		_panel.clear();
		
	}

	@Override
	public Iterator<Widget> iterator() {
		return _panel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return _panel.remove(w);
	}

	/********************* sub class Slide *******************/
	
	public static class Slide extends WidgetBase implements HasWidgets {

		protected FlowPanel _panel = new FlowPanel();
		
		public Slide() {
			initWidget(_panel);
			setStyleName("Slide");
		}
		
		@Override
		public void add(Widget w) {
			_panel.add(w);
		}

		@Override
		public void clear() {
			_panel.clear();
			
		}

		@Override
		public Iterator<Widget> iterator() {
			return _panel.iterator();
		}

		@Override
		public boolean remove(Widget w) {
			return _panel.remove(w);
		}
		
	}
	
	/********************* interface SlideProvider *******************/

	public interface SlideProvider {
		Slide loadSlide(int index);
	}
}
