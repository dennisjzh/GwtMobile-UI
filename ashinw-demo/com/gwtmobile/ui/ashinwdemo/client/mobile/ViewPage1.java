package com.gwtmobile.ui.ashinwdemo.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.ashinwdemo.client.PageNames;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.widgets.HeaderPanel;

public class ViewPage1 extends AbstractPage {
	private static ViewPage1UiBinder uiBinder = GWT
			.create(ViewPage1UiBinder.class);

	interface ViewPage1UiBinder extends UiBinder<Widget, ViewPage1> {
	}
	
	@UiField HeaderPanel navHpl;

	@Override
	protected void prepareView()  {
		super.prepareView();
		initWidget(uiBinder.createAndBindUi(this));
		prepareStartButton();
	}

	private void prepareStartButton() {
		navHpl.setRightButtonClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				PageHistory.navigateTo(PageNames.PAGE2_NAME, null);
			}
		});
	}
}
