package com.gwtmobile.ui.testdemo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.event.SelectionChangedEvent;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.ListPanel;
import com.gwtmobile.ui.client.resources.MobileResources;
import com.gwtmobile.ui.testdemo.client.pages.PageButtons;
import com.gwtmobile.ui.testdemo.client.pages.PageCharts;
import com.gwtmobile.ui.testdemo.client.pages.PageForms;
import com.gwtmobile.ui.testdemo.client.pages.PageGrid;
import com.gwtmobile.ui.testdemo.client.pages.PageLabels;
import com.gwtmobile.ui.testdemo.client.pages.PageSlides;
import com.gwtmobile.ui.testdemo.client.pages.PageStacks;
import com.gwtmobile.ui.testdemo.client.pages.PageTabsBottom;
import com.gwtmobile.ui.testdemo.client.pages.PageTabsTop;
import com.gwtmobile.ui.testdemo.client.resources.AppResources;


public class MainPage extends Page {
	
	interface MainPageUiBinder extends UiBinder<Widget, MainPage> { }
	private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);
	
	@UiField ListPanel list;
	
	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
		
		MobileResources.IMAGE_MAP.put("bell", AppResources.INSTANCE.iconBell());
		
		Window.addResizeHandler(new ResizeHandler() {		
			@Override
			public void onResize(ResizeEvent event) {
				Utils.Console("gwtmobile-msg:window resized to " + event.getWidth() + " " + event.getHeight());
			}
		});
	}

	@UiHandler("list")
	void onListSelectionChanged(SelectionChangedEvent e) {
		switch (e.getSelection()) {
		case 0:
			PageButtons pageButtons = new PageButtons();
			this.goTo(pageButtons);
			break;
		case 1:
			PageLabels pageLabels = new PageLabels();
			this.goTo(pageLabels);
			break;
		case 2:
			PageTabsTop pageTabsTop = new PageTabsTop();
			this.goTo(pageTabsTop);
			break;
		case 3:
			PageTabsBottom pageTabsBottom = new PageTabsBottom();
			this.goTo(pageTabsBottom);
			break;
		case 4:
			PageStacks pageStacks = new PageStacks();
			this.goTo(pageStacks);
			break;
		case 5:
			PageForms pageForms = new PageForms();
			this.goTo(pageForms);
			break;
		case 6:
			PageSlides pageSlides = new PageSlides();
			this.goTo(pageSlides);
			break;
		case 7:
			PageGrid pageGrid = new PageGrid();
			this.goTo(pageGrid);
			break;
		case 8:
			PageCharts pageCharts = new PageCharts();
			this.goTo(pageCharts);
			break;
		}
	}

}
