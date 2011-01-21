package com.gwtmobile.ui.client.page;

import com.gwtmobile.ui.client.CSS.CSS;

public class Transition {
	
	String _transitionStyleName;
	public static Transition SLIDE = new Transition(CSS.T.slide());
	public static Transition SLIDEUP = new Transition(CSS.T.slideup());
	public static Transition SLIDEDOWN = new Transition(CSS.T.slidedown());
	public static Transition FADE = new Transition(CSS.T.fade());
	
	Transition(String transitionStyleName) {
		_transitionStyleName = transitionStyleName;
	}
	
	void prepare(Page fromPage, Page toPage, boolean reverse) {
		fromPage.addStyleName(_transitionStyleName + " " + CSS.T.out());
		toPage.addStyleName(_transitionStyleName + " " + CSS.T.in());
		if (reverse) {
			fromPage.addStyleName(CSS.T.reverse());
			toPage.addStyleName(CSS.T.reverse());
		}
	}

	void start(Page fromPage, Page toPage) {
		fromPage.addStyleName(CSS.T.start());
		toPage.addStyleName(CSS.T.start());
	}
	
	void remove(Page fromPage, Page toPage) {
		fromPage.removeStyleName(_transitionStyleName);
		fromPage.removeStyleName(CSS.T.start());
		fromPage.removeStyleName(CSS.T.out());
		fromPage.removeStyleName(CSS.T.reverse());
		toPage.removeStyleName(_transitionStyleName);
		toPage.removeStyleName(CSS.T.in());
		toPage.removeStyleName(CSS.T.start());
		toPage.removeStyleName(CSS.T.reverse());
	}	
}
