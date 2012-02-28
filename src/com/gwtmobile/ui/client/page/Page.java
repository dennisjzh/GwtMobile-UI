/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtmobile.ui.client.page;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;
import com.gwtmobile.ui.client.page.PageHistory.NavigateInfo;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.IsGwtMobilePanel;
import com.gwtmobile.ui.client.widgets.PagePanel;
import com.gwtmobile.ui.client.widgets.WidgetBase;

public abstract class Page extends WidgetBase implements IsGwtMobilePanel {
	final static private String CONSUMED_TOKEN = "#?#";
	private Transition transition = Transition.SLIDE; // assume SLIDE as default transition
	protected String tokenStateInfo = CONSUMED_TOKEN;

	static {
		if (!Utils.isDesktop() && !Utils.hasPhoneGap()) {
			hideAddressBar();
			Window.addResizeHandler(new ResizeHandler() { 
				@Override 
				public void onResize(ResizeEvent event) { 
					hideAddressBar();
					scrollToHideAddressBar();
				} 
			}); 
		} 
	}
	
	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);
		setStyleName(Primary.PagePanel);
		// we might need to assert that the given widget has to be a PagePanel
		if (widget.getClass().getName().endsWith("PagePanel"))
			setTransition(((PagePanel)widget).getTransitionFlavor().getTransition());

		// TODO: use permutation instead?
		if (Utils.isAndroid()) {
			addStyleName("Android");
		} else if (Utils.isIOS()) {
			addStyleName("iOS");
		} else {
			addStyleName("Desktop");
		}
	}

	private static void hideAddressBar() {
		int barHeight = Utils.isIOS() ? 60 : Utils.isAndroid() ? 1 : 0;
		if (barHeight > 0) {
			RootLayoutPanel.get().setHeight(Window.getClientHeight() + barHeight + "px");
		}
	}
	
	private static void scrollToHideAddressBar() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				Window.scrollTo(0, 1);
			}
		});
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (!Utils.isDesktop() && !Utils.hasPhoneGap()) {
			scrollToHideAddressBar();
		}
	}
	
	/**
	 * Gives the page an opportunity to load state that was sent as part of the
	 * history token prior to display.
	 * 
	 * @see PageHistory#navigateTo(String, String)
	 * @see BrowserPageHistory#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)
	 * 
	 * @param stateInfo
	 */
	protected void initNavigationalState(String stateInfo) {
	}

	@Override
	public void onTransitionEnd(TransitionDirection direction) {
		if (direction != TransitionDirection.To) {
			return;
		}
		final Page to;
		final PageHistory pageHistory = PageHistory.Instance;
		if (pageHistory.from() == null
				|| pageHistory.from() != Page.this) { // goto
			to = this;
			// TODO: change to use scheduler deferred command.
			Timer timer = new Timer() {
				@Override
				public void run() {
					NavigateInfo info = pageHistory.getNavigateInfo();
					to.onNavigateTo(info.getFromPage(), info.getValue());
					to.initNavigationIfRequired();
				}
			};
			timer.schedule(1);
		} else { // goback
			to = pageHistory.current();
			Timer timer = new Timer() {
				@Override
				public void run() {
					NavigateInfo info = pageHistory.getNavigateInfo();
					to.onNavigateBack(info.getFromPage(), info.getValue());
					to.initNavigationIfRequired();
				}
			};
			timer.schedule(1);
		}
	}

	protected void initNavigationIfRequired() {
		if (!CONSUMED_TOKEN.equals(tokenStateInfo)) {
			initNavigationalState(tokenStateInfo);
			tokenStateInfo = CONSUMED_TOKEN;
		}
	}

	protected void onNavigateTo(Page from, Object object) {
	}

	protected void onNavigateBack(Page from, Object object) {
	}

	public void goTo(final Page toPage) {
		goTo(toPage, toPage.getTransition());
	}

	public void goTo(final Page toPage, Object params) {
		goTo(toPage, params, toPage.getTransition());
	}

	public void goTo(final Page toPage, final Transition transition) {
		goTo(toPage, null, transition);
	}

	public void goTo(final Page toPage, Object params, final Transition transition) {
		PageHistory.Instance.goTo(toPage, params, transition);
	}

	public void goBack(Object returnValue) {
		PageHistory.Instance.goBack(returnValue);
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public Transition getTransition() {
		return transition;
	}

	public static void load(Page mainPage) {
		setPageResolution();
		PageHistory.Instance.startUp(mainPage);
	}

//	public static void setDefaultTransition(Transition transition) {
//		_defaultTransition = transition;
//	}

	@Override
	public Widget getWidget() { // make getWidget() public
		return super.getWidget();
	}

	private static void setPageResolution() {
		double ratio = getDevicePixelRatio();
		if (ratio == 2) { // iphone 4. screen size on iphone does not change
							// despite the dp ratio.
			Document.get().getDocumentElement().setClassName("HVGA");
		} else if (ratio == 1.5) {
			Document.get().getDocumentElement().setClassName("WVGA");
		} else if (ratio == 0.75) {
			Document.get().getDocumentElement().setClassName("QVGA");
		} else {
			Document.get().getDocumentElement().setClassName("HVGA");
		}
	}

	public static native double getDevicePixelRatio() /*-{
		return $wnd.devicePixelRatio;
	}-*/;

}
