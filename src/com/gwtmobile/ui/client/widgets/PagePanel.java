package com.gwtmobile.ui.client.widgets;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.page.Transition.TransitionFlavor;

public class PagePanel extends HTMLPanel {

	private TransitionFlavor transitionFlavor = TransitionFlavor.SLIDE;
	
	public PagePanel(String html) {
		super(html);
		setStyleName(Primary.PagePanel);
	}

	public TransitionFlavor getTransitionFlavor() {
		return transitionFlavor;
	}

	public void setTransitionFlavor(TransitionFlavor transitionFlavor) {
		this.transitionFlavor = transitionFlavor;
	}
}
