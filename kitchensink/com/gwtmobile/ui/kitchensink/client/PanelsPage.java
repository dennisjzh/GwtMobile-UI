package com.gwtmobile.ui.kitchensink.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.widgets.ListPanel;

public class PanelsPage extends Page {

	@UiField ListPanel list;
	
	private static PanelsPageUiBinder uiBinder = GWT
			.create(PanelsPageUiBinder.class);

	interface PanelsPageUiBinder extends UiBinder<Widget, PanelsPage> {
	}

	public PanelsPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @UiHandler("list")
    void onListSelectionChanged(SelectionChangedEvent e) {
    	switch (e.getSelection()) {
    	case 0:
    	}
    }

}
