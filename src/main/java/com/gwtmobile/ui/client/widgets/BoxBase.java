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
package com.gwtmobile.ui.client.widgets;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.ValueBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.CSS.SubStyleNames;



/**
 * Creates an HTML5 Element und use the right GWT renderer and parser to support data bindimng. Has to inherit form
 * {@link ValueBoxBase} gwt's {@link ValueBox} has an assertion for Text content (not valid using e.g. numbers).
 * 
 * @param <T>
 *            der typ of the box.
 */
public class BoxBase<T> extends com.google.gwt.user.client.ui.ValueBoxBase<T> implements FocusHandler, BlurHandler,
		IsGwtMobileWidget {

	private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();



	public BoxBase(String html5Type, Renderer<T> renderer, Parser<T> parser) {

		super(createNumberInputElement(html5Type), renderer, parser);
		// BiDi input is not expected - disable direction estimation.
		setDirectionEstimator(false);
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			setDirection(Direction.LTR);
		}
		setStyleName(StyleNames.TextBox + capitalize(html5Type));
		addFocusHandler(this);
		addBlurHandler(this);
	}



	@Override
	protected void onLoad() {
		super.onLoad();
		_widgetHelper.CheckInitialLoad(this);

	}



	@Override
	public void onFocus(FocusEvent event) {
		this.addStyleName(SubStyleNames.focus);
	}



	@Override
	public void onBlur(BlurEvent event) {
		this.removeStyleName(SubStyleNames.focus);
	}



	protected static native InputElement createNumberInputElement(String type) /*-{
																				var e = $doc.createElement("INPUT");
																				e.type = type;
																				return e;
																				}-*/;



	private String capitalize(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

	}



	@Override
	public void onInitialLoad() {
	}



	@Override
	public void onTransitionEnd() {
	}



	@Override
	public void setSecondaryStyle(String style) {
		_widgetHelper.setSecondaryStyle(this, style);
	}



	/**
	 * Gets the maximum allowable length.
	 * 
	 * @return the maximum length, in characters
	 */
	public int getMaxLength() {
		return getInputElement().getMaxLength();
	}



	/**
	 * Gets the number of visible characters.
	 * 
	 * @return the number of visible characters
	 */
	public int getVisibleLength() {
		return getInputElement().getSize();
	}



	/**
	 * Sets the maximum allowable length.
	 * 
	 * @param length
	 *            the maximum length, in characters
	 */
	public void setMaxLength(int length) {
		getInputElement().setMaxLength(length);
	}



	/**
	 * Sets the number of visible characters.
	 * 
	 * @param length
	 *            the number of visible characters
	 */
	public void setVisibleLength(int length) {
		getInputElement().setSize(length);
	}



	private InputElement getInputElement() {
		return getElement().cast();
	}

}
