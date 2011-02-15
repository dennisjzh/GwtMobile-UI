package com.gwtmobile.ui.kitchensink.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.widgets.HeaderPanel;
import com.gwtmobile.ui.client.widgets.SlidePanel;
import com.gwtmobile.ui.client.widgets.SlidePanel.Slide;
import com.gwtmobile.ui.client.widgets.SlidePanel.SlideProvider;

public class SlidePanelPage extends Page implements SlideProvider{

	@UiField HeaderPanel header;
	@UiField SlidePanel slider;
	
	private static SlidePanelPageUiBinder uiBinder = GWT
			.create(SlidePanelPageUiBinder.class);

	interface SlidePanelPageUiBinder extends UiBinder<Widget, SlidePanelPage> {
	}

	public SlidePanelPage() {
		initWidget(uiBinder.createAndBindUi(this));		
		
		slider.setSlideCount(10);
		slider.setSlideProvider(this);

		header.setLeftButtonClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (slider.getCurrentSlide() > 0) {
					slider.previous();
				}
				else {
					goBack(null);
				}
			}
		});

		header.setRightButtonClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				slider.next();
			}
		});
	}

	@Override
	public Slide loadSlide(int index) {
		Slide slide = new SlidePanel.Slide();
		slide.add(new HTML("Slide " + index));
		return slide;		
	}

}
