package com.gwtmobile.ui.kitchensink.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.widgets.HeaderPanel;
import com.gwtmobile.ui.client.widgets.ListPanel;

public class TransitionDemoPage extends Page {

	@UiField HeaderPanel header;
	@UiField ListPanel list;
	
	private static TransitionPageUiBinder uiBinder = GWT
			.create(TransitionPageUiBinder.class);

	interface TransitionPageUiBinder extends UiBinder<Widget, TransitionDemoPage> {
	}

	public TransitionDemoPage(String caption) {
		initWidget(uiBinder.createAndBindUi(this));
		header.setCaption(caption);
	}
	
    @UiHandler("list")
	void onListSelectionChanged(SelectionChangedEvent e) {
    }

}
