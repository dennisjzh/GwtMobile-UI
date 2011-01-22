package com.gwtmobile.ui.client.page;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtmobile.ui.client.CSS.CSS;
import com.gwtmobile.ui.client.utils.Utils;

public class Transition implements EventListener {
	
	String _transitionStyleName;
	Page _from, _to;
	boolean _reverse;
	
	public static Transition SLIDE = new Transition(CSS.T.slide());
	public static Transition SLIDEUP = new Transition(CSS.T.slideup());
	public static Transition SLIDEDOWN = new Transition(CSS.T.slidedown());
	public static Transition FADE = new Transition(CSS.T.fade());
	public static Transition FLIP = new FlipTransition();
	
	Transition(String transitionStyleName) {
		_transitionStyleName = transitionStyleName;
	}
	
	public void start(Page fromPage, Page toPage, boolean reverse) {
		_from = fromPage;
		_to = toPage;
		_reverse = reverse;
		prepare();
		start();
	}
	
	protected void prepare() {
		_from.addStyleName(_transitionStyleName + " " + CSS.T.out());
		_to.addStyleName(_transitionStyleName + " " + CSS.T.in());
		if (_reverse) {
			_from.addStyleName(CSS.T.reverse());
			_to.addStyleName(CSS.T.reverse());
		}
		RootLayoutPanel.get().add(_to);
	}

	protected void start() {
		registerTransitionEndEvent();
		new Timer() {
            @Override
            public void run() {
        		_from.addStyleName(CSS.T.start());
        		_to.addStyleName(CSS.T.start());
            }
		}.schedule(10);	//10ms instead of 1ms, to give iOS enough time to set the starting state.

	}
	
	protected void removeTransitionStyles() {
		_from.removeStyleName(_transitionStyleName);
		_from.removeStyleName(CSS.T.start());
		_from.removeStyleName(CSS.T.out());
		_from.removeStyleName(CSS.T.reverse());
		_to.removeStyleName(_transitionStyleName);
		_to.removeStyleName(CSS.T.in());
		_to.removeStyleName(CSS.T.start());
		_to.removeStyleName(CSS.T.reverse());
	}
	
	@Override
	public void onBrowserEvent(Event e) {
		String type = e.getType();
		if (type.equals("webkitTransitionEnd")) {
		    onTransitionEnd();
		}		
	}
	
	protected void onTransitionEnd() {
		if (_from != null && _to != null) {
			RootLayoutPanel.get().remove(_from);
			removeTransitionStyles();
			_to.onTransitionEnd();
			_from = null;
			_to = null;			
		}
	}

	protected void registerTransitionEndEvent() {
		Utils.addEventListenerOnce(_from.getElement(), "webkitTransitionEnd", false, this);
		Utils.addEventListenerOnce(_to.getElement(), "webkitTransitionEnd", false, this);
	}

	private static class FlipTransition extends Transition {

		private int _phase = 0;
		
		FlipTransition() {
			super(CSS.T.flip0());
		}
		
		@Override
		protected void registerTransitionEndEvent() {
			if (_phase == 0) {
				Utils.addEventListenerOnce(_from.getElement(), "webkitTransitionEnd", false, this);
			}
			else {
				Utils.addEventListenerOnce(_to.getElement(), "webkitTransitionEnd", false, this);
			}
		}
		
		@Override
		protected void onTransitionEnd() {
			removeTransitionStyles();
			if (_phase == 0) {
				RootLayoutPanel.get().remove(_from);
				RootLayoutPanel.get().add(_to);
				_phase++;
				_transitionStyleName = CSS.T.flip1();
				prepare();
				start();
			}
			else {
				_to.onTransitionEnd();
				_from = null;
				_to = null;
				_phase = 0;
				_transitionStyleName = CSS.T.flip0();
			}
		}
		
		@Override
		protected void prepare() {
			_from.addStyleName(_transitionStyleName + " " + CSS.T.out());
			_to.addStyleName(_transitionStyleName + " " + CSS.T.in());
			if (_reverse) {
				_from.addStyleName(CSS.T.reverse());
				_to.addStyleName(CSS.T.reverse());
			}
		}
		
	}

}
