package com.gwtmobile.ui.testdemo.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.Button;
import com.gwtmobile.ui.client.widgets.RadioButtonGroup;

public class PageThemes  extends Page {

	interface Page1UiBinder extends UiBinder<Widget, PageThemes> { }
	private static Page1UiBinder uiBinder = GWT.create(Page1UiBinder.class);
	@UiField Button backButton;
	@UiField Button themeLight;
	@UiField Button themeDark;
	
	public PageThemes(){
		initWidget(uiBinder.createAndBindUi(this));
		String current_theme = Utils.getCssHref();
		if (current_theme.indexOf("light")!=-1) {
			themeLight.setEnabled(false);
		} else {
			themeDark.setEnabled(false);
		}

	}
	
    @UiHandler("backButton")
    public void onClick(ClickEvent event) {
        PageHistory.Instance.current().goBack(null);
    }
    
    @UiHandler("themeLight")
    public void onClick1(ClickEvent event) {
    	themeLight.setEnabled(false);
    	themeDark.setEnabled(true);
    	switchTheme();
    }
    
    @UiHandler("themeDark")
    public void onClick2(ClickEvent event) {
    	themeDark.setEnabled(false);
    	themeLight.setEnabled(true);
    	switchTheme();
    }
    
    private void switchTheme(){
		String current_theme = Utils.getCssHref();
		if (current_theme.indexOf("light")!=-1) {
			current_theme = current_theme.replaceAll("light", "dark");
		} else {
			current_theme = current_theme.replaceAll("dark", "light");
		}
		Utils.setCssHref(current_theme);
    }
    
}
