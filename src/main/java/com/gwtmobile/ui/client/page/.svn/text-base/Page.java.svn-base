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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.WidgetBase;



/**
 * A <code>Page</code> describes the current screen content.
 */
public abstract class Page extends WidgetBase implements IPage {
	final static private String CONSUMED_TOKEN = "#?#";
	private Transition _transition;
	private static Transition _defaultTransition = Transition.SLIDE;
	protected String tokenStateInfo = CONSUMED_TOKEN;
	private HasWidgets parent = RootLayoutPanel.get(); 

	public void setParent(HasWidgets p) {
		parent = p;
	}

	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);
		setStyleName(StyleNames.Page);
		// TODO: use permutation instead?
		if (Utils.isAndroid()) {
			addStyleName(StyleNames.Android);
		} else if (Utils.isIOS()) {
			addStyleName(StyleNames.iOS);
		} else {
			addStyleName(StyleNames.Desktop);
		}
	}



	/**
	 * Gives the page an opportunity to load state that was sent as part of the history token prior to display.
	 * 
	 * @see PageHistory#navigateTo(String, String)
	 * @see BrowserPageHistory#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)
	 * 
	 * @param stateInfo
	 */
	protected void initNavigationalState(String stateInfo) {
	}



	@Override
	public void onTransitionEnd() {
		final Page to, from;
		final PageHistory pageHistory = PageHistory.Instance;
		if (pageHistory.from() == null || pageHistory.from() != Page.this) { // goto
			Utils.console("goto");
			from = pageHistory.current();
			to = this;
			pageHistory.add(to);
			// TODO: change to use scheduler deferred command.
			Timer timer = new Timer() {
				@Override
				public void run() {
					to.onNavigateTo();
					to.initNavigationIfRequired();
				}
			};
			timer.schedule(1);
		} else { // goback
			Utils.console("goback");
			from = pageHistory.current();
			pageHistory.back();
			to = pageHistory.current();
			Timer timer = new Timer() {
				@Override
				public void run() {
					to.onNavigateBack(from, pageHistory.getReturnValue());
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



	protected void onNavigateTo() {
	}



	protected void onNavigateBack(Page from, Object object) {
	}



	/**
	 * Vollzieht einen Uebergang von der aktuellen Seit zur
	 * 
	 * @param toPage
	 */
	public void goTo(final IPage toPage) {
		goTo(toPage, _defaultTransition);
	}



	public void goTo(final IPage toPage, final Transition transition) {
		if (toPage instanceof Page) {
			final Page toPageC = (Page) toPage;
			Element focus = Utils.getActiveElement();
			focus.blur();
			final Page fromPage = this;
			toPageC.setTransition(transition);
			this.beforeLeaving();
			toPage.beforeEnter();
			if (transition != null) {
				transition.start(fromPage, toPageC, parent, false);
			} else {
				Transition.start(fromPage, toPageC, parent);
			}
		}
	}



	public void goBack(Object returnValue) {
		this.beforeLeaving();
		PageHistory.Instance.goBack(this, returnValue);
	}



	void setTransition(Transition transition) {
		_transition = transition;
	}



	Transition getTransition() {
		return _transition;
	}



	public static void load(Page mainPage) {
		setPageResolution();
		mainPage.beforeEnter();
		RootLayoutPanel.get().add(mainPage);
		PageHistory.Instance.add(mainPage);
	}

	// don't add page to history
	public static void loadAsRoot(Page mainPage) {
		setPageResolution();
		mainPage.beforeEnter();
		RootLayoutPanel.get().add(mainPage);
	}


	public static void setDefaultTransition(Transition transition) {
		_defaultTransition = transition;
	}



	@Override
	public Widget getWidget() { // make getWidget() public
		return super.getWidget();
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see com.gwtmobile.ui.client.page.IPage#beforeEnter()
	 */
	@Override
	public void beforeEnter() {
		// Intetially left empty

	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see com.gwtmobile.ui.client.page.IPage#beforeLeaving()
	 */
	@Override
	public void beforeLeaving() {
		// Intetially left empty
	}



	private static void setPageResolution() {
		int ratio = getDevicePixelRatio();
		if (ratio == 2) { // iphone 4. screen size on iphone does not change
							// despite the dp ratio.
			Document.get().getDocumentElement().setClassName(StyleNames.HVGA);
		} else if (ratio == 1.5) {
			Document.get().getDocumentElement().setClassName(StyleNames.WVGA);
		} else if (ratio == 0.75) {
			Document.get().getDocumentElement().setClassName(StyleNames.QVGA);
		} else {
			Document.get().getDocumentElement().setClassName(StyleNames.HVGA);
		}
	}



	public static native int getDevicePixelRatio() /*-{
													return $wnd.devicePixelRatio;
													}-*/;

}
