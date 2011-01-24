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
	TransitionDemoPage demo = new TransitionDemoPage();
	
	private static TransitionPageUiBinder uiBinder = GWT
			.create(TransitionPageUiBinder.class);

	interface TransitionPageUiBinder extends UiBinder<Widget, TransitionsPage> {
	}

	public TransitionsPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @UiHandler("list")
    void onListSelectionChanged(SelectionChangedEvent e) {
    	switch (e.getSelection()) {
    	case 0:
    		demo.header.setCaption("Slide");
    		this.goTo(demo, Transition.SLIDE);
    		break;
    	case 1:
    		demo.header.setCaption("Slide Up");
    		this.goTo(demo, Transition.SLIDEUP);
    		break;
    	case 2:
    		demo.header.setCaption("Slide Down");
    		this.goTo(demo, Transition.SLIDEDOWN);
    		break;
    	case 3:
    		demo.header.setCaption("Fade");
    		this.goTo(demo, Transition.FADE);
    		break;
    	case 4:
    		demo.header.setCaption("Pop");
    		this.goTo(demo, Transition.POP);
    		break;
    	case 5:
    		demo.header.setCaption("Swap");
    		this.goTo(demo, Transition.SWAP);
    		break;
    	case 6:
    		demo.header.setCaption("Flip");
    		this.goTo(demo, Transition.FLIP);
    		break;
    	}
    }

}
