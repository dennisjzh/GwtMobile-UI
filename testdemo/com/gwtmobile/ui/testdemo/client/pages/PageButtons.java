package com.gwtmobile.ui.testdemo.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.widgets.Button;

public class PageButtons  extends Page {

	interface Page1UiBinder extends UiBinder<Widget, PageButtons> { }
	private static Page1UiBinder uiBinder = GWT.create(Page1UiBinder.class);
	@UiField Button backButton;
	
	public PageButtons(){
		initWidget(uiBinder.createAndBindUi(this));
	}
	
    @UiHandler("backButton")
    public void onClick(ClickEvent event) {
        PageHistory.Instance.current().goBack(null);
    }
	
}
