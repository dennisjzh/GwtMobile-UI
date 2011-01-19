package com.gwtmobile.ui.kitchensink.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtmobile.ui.client.page.PageHistory;

public class KitchenSink implements EntryPoint {

	MainPage mainPage = new MainPage();
	
	@Override
	public void onModuleLoad() {
        RootLayoutPanel.get().add(mainPage);
        PageHistory.add(mainPage);
	}

}
