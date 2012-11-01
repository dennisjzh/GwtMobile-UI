package com.gwtmobile.ui.client.widgets;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.page.Transition.TransitionFlavor;

//FIXME: why pagepanel does not extends PanelBase?
public class PagePanel extends HTMLPanel {

	private TransitionFlavor _transitionFlavor = TransitionFlavor.SLIDE;
	
	public PagePanel(String html) {
		super(html);
		setStyleName(Primary.PagePanel);
	}

	public TransitionFlavor getTransitionFlavor() {
		return _transitionFlavor;
	}

	public void setTransitionFlavor(TransitionFlavor transitionFlavor) {
		this._transitionFlavor = transitionFlavor;
	}
}
