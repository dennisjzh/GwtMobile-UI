package com.gwtmobile.ui.ashinwdemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class DemoApp implements EntryPoint {
	@Override
	public void onModuleLoad() {
	    ClientFactory clientFactory = GWT.create(ClientFactory.class);
	    clientFactory.bootStrap();
	}
}
