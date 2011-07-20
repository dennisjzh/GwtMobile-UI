package com.gwtmobile.ui.ashinwdemo.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.ashinwdemo.client.PageNames;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.widgets.ButtonPanel;
import com.gwtmobile.ui.client.widgets.FlipSwitch;
import com.gwtmobile.ui.client.widgets.Slider;

public class ViewHome extends AbstractPage {
	private static ViewHomeUiBinder uiBinder = GWT
			.create(ViewHomeUiBinder.class);

	interface ViewHomeUiBinder extends UiBinder<Widget, ViewHome> {
	}
	
	@UiField ButtonPanel gotoPage1Btn, gotoPage2Btn;
	@UiField Label value1;
	@UiField Slider slider1;
	@UiField FlipSwitch switch1;
	
	private int slideCount;
	
	@Override
	protected void prepareView() {
		super.prepareView();
		initWidget(uiBinder.createAndBindUi(this));
		switch1.setEnabled(false);
		switch1.setValue(false);
	}

	@UiHandler({"gotoPage1Btn", "gotoPage2Btn"})
	public void onActionButtonClick(ClickEvent event) {
		ButtonPanel src = (ButtonPanel) event.getSource();
		String nextPage = null;
		String param = null;
		if (gotoPage1Btn == src) 
			nextPage = PageNames.PAGE1_NAME;
		else { 
			nextPage = PageNames.PAGE2_NAME;
			StringBuffer buff = new StringBuffer(slideCount + "");
			buff.append(',');
			buff.append((switch1.isEnabled() && switch1.getValue()) ? '1' : '0');
			param = buff.toString();
		}
		
		if (nextPage != null) 
			PageHistory.navigateTo(nextPage, param);
		else 
			GWT.log("not going anywhere");
	}
	
	@UiHandler("slider1")
	public void onValueChangeSlider1(ValueChangeEvent<Integer> e) {
		int val = e.getValue();
		slideCount = val / 5;
		if (slideCount < 2)
			slideCount = 2;
		value1.setText("No of slides: " + slideCount);
		switch1.setEnabled(slideCount > 5);
	}
}