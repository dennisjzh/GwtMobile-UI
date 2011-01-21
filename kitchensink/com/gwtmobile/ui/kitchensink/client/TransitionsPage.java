package com.gwtmobile.ui.kitchensink.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.Transition;
import com.gwtmobile.ui.client.widgets.ListPanel;

public class TransitionsPage extends Page {

	@UiField ListPanel list;
	
	private static TransitionPageUiBinder uiBinder = GWT
			.create(TransitionPageUiBinder.class);

	interface TransitionPageUiBinder extends UiBinder<Widget, TransitionsPage> {
	}

	public TransitionsPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @UiHandler("list")
    void onListSelectionChanged(SelectionChangedEvent e) {
    	TransitionDemoPage demo;
    	switch (e.getSelection()) {
    	case 0:
    		demo = new TransitionDemoPage("Slide");
    		this.goTo(demo, Transition.SLIDE);
    		break;
    	case 1:
    		demo = new TransitionDemoPage("Slide Up");
    		this.goTo(demo, Transition.SLIDEUP);
    		break;
    	case 2:
    		demo = new TransitionDemoPage("Slide Down");
    		this.goTo(demo, Transition.SLIDEDOWN);
    		break;
    	case 3:
    		demo = new TransitionDemoPage("Fade");
    		this.goTo(demo, Transition.FADE);
    		break;
    	}
    }

}
