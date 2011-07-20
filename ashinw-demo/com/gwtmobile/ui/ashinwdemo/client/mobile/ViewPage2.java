package com.gwtmobile.ui.ashinwdemo.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.Button;
import com.gwtmobile.ui.client.widgets.CyclicSlidePanel;
import com.gwtmobile.ui.client.widgets.Slide;
import com.gwtmobile.ui.client.widgets.SlidePanel.SlideProvider;

public class ViewPage2 extends AbstractPage implements SlideProvider {
	private static ViewPage2UiBinder uiBinder = GWT
			.create(ViewPage2UiBinder.class);

	interface ViewPage2UiBinder extends UiBinder<Widget, ViewPage2> {
	}

	private QuoteSlide switcher[], nextSlide;
	
	@UiField CyclicSlidePanel panel;
	@UiField Button prevBtn, nextBtn;

	@Override
	protected void prepareView()  {
		super.prepareView();
		initWidget(uiBinder.createAndBindUi(this));
		prepareSlides();
	}
	
	@Override
	protected void initNavigationalState(String stateInfo) {
		Utils.Console("ViewPage2#_initNavigationalState:" + stateInfo);
		if (stateInfo == null)
			stateInfo = "10,0";
		String params[] = stateInfo.split(",");
		int count = Integer.parseInt(params[0]);
		int startFrom5 = Integer.parseInt(params[1]);
		panel.setSlideCount(count);
		panel.setCurrentSlideIndex(startFrom5 == 1 ? 5: 0);
	}
	
	@UiHandler({"prevBtn", "nextBtn"})
	public void onNavButtonClick(ClickEvent event) {
		Button src = (Button) event.getSource();
		if (src == prevBtn) 
			panel.previous();
		else
			panel.next();
	}
	
	@Override
	public Slide loadSlide(int index) {
		if (nextSlide == null) {
			nextSlide = switcher[index];
		} else if (nextSlide == switcher[0])
			nextSlide = switcher[1];
		else
			nextSlide = switcher[0];
		
		nextSlide.quote.setHTML("This is slide: " + index);
		Utils.Console("loading slide: " + index);
		return nextSlide;
	}
	
	private void prepareSlides() {
		switcher = new QuoteSlide[2];
		switcher[0] = new QuoteSlide();
		switcher[1] = new QuoteSlide();
		
		panel.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue())
					Utils.Console("moving next");
				else
					Utils.Console("moving previous");
			}
		});
		panel.setSlideProvider(this);
		panel.setSlideCount(2);
	}

	static private class QuoteSlide extends Slide {
		final public HTML quote = new HTML();
		
		public QuoteSlide() {
			add(quote);
			this.addStyleName(PageResources.INSTANCE.style().slideContent());
		}
	}
}