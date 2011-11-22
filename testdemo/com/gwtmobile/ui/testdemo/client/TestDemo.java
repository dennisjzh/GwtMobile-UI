package com.gwtmobile.ui.testdemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.utils.Utils;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDemo implements EntryPoint {
	MainPage mainPage;

	@Override
	public void onModuleLoad() {

		new Timer() {
			@Override
			public void run() {
				if (mainPage == null) {
					Utils.Console("Loading main ui...");
					mainPage = new MainPage();
					Page.load(mainPage);
				}
				else {
					this.cancel();
				}
			}
		}.scheduleRepeating(50);
	}
}
