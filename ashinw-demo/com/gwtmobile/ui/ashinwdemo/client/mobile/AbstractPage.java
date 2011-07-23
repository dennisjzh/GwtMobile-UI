package com.gwtmobile.ui.ashinwdemo.client.mobile;

import com.gwtmobile.ui.ashinwdemo.client.ClientFactory;
import com.gwtmobile.ui.client.page.Page;

public abstract class AbstractPage extends Page {
	protected ClientFactory clientFactory;
	
	public void preparePage(ClientFactory cf) {
		clientFactory = cf;
		prepareView();
	}
	
	protected void prepareView() {
		// consider ensuring all page resources which are common to all
		// pages are injected here
		PageResources.INSTANCE.style().ensureInjected();
	}
}
