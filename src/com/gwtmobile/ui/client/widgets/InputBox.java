/*
 * Copyright (c) 2011 Zhihua (Dennis) Jiang
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

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.text.shared.testing.PassthroughParser;
import com.google.gwt.text.shared.testing.PassthroughRenderer;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.utils.HTML5InputElement;
import com.gwtmobile.ui.client.utils.HTML5InputElement.InputTypes;


public class InputBox extends com.google.gwt.user.client.ui.TextBoxBase 
	implements FocusHandler, BlurHandler, IsGwtMobileWidget {

	private IsGwtMobileWidgetHelper widgetHelper = new IsGwtMobileWidgetHelper();

	public enum BoxTypes { TextBox, PasswordBox, EmailBox, NumberTextBox, PasswordTextBox, TelephoneTextBox, UrlTextBox};
	
	private InputTypes type = InputTypes.text;
	private BoxTypes boxTypes = BoxTypes.TextBox; // allows to have more flexible styles and introduce validators
	
	private HTML5InputElement html5Element = null;
	
	public InputBox() {
		this(InputTypes.text, BoxTypes.TextBox, ""); // by default, lets user text type
	}
	
	public InputBox(InputTypes inputType, BoxTypes boxType, String valueText){
		super(createInputElement(InputTypes.text.toString()));
		html5Element = HTML5InputElement.as(getElement());
	    setStyleName(StyleNames.Primary.InputBox);
	    setBoxTypes(boxType);
	    setType(inputType);
	    setText(valueText);
	}

	public InputTypes getType() {
		return type;
	}

	public void setType(InputTypes textBoxType) {
		this.removeStyleName(this.type.toString());
		this.type = textBoxType;
		this.getElement().setAttribute("type", this.type.toString());
		this.addStyleName(this.type.toString());
	}

	public BoxTypes getBoxTypes() {
		return boxTypes;
	}

	public void setBoxTypes(BoxTypes boxTypes) {
		this.removeStyleName(this.boxTypes.toString());
		this.boxTypes = boxTypes;
		this.addStyleName(this.boxTypes.toString());
	}

	public String getPlaceHolder(){
		return getHTML5Element().getPlaceholder();
	}
	
	public void setPlaceHolder(String pHolder){
		getHTML5Element().setPlaceholder(pHolder);
	}
	
	public String getPattern(){
		return getHTML5Element().getPattern();
	}
	
	public void setPattern(String pattern){
		getHTML5Element().setPattern(pattern);
	}
	
	private HTML5InputElement getHTML5Element(){
		return html5Element;
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		widgetHelper.CheckInitialLoad(this);
		
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		this.addStyleName("Focus");
	}

	@Override
	public void onBlur(BlurEvent event) {
		this.removeStyleName("Focus");
	}

	private static native HTML5InputElement createInputElement(String type)  /*-{
		var e = $doc.createElement("INPUT");
		e.type = type;
		return e;
	}-*/;
	
	@Override
	public void onInitialLoad() {
	}

	@Override
	public void setSecondaryStyle(String style) {
		widgetHelper.setSecondaryStyle(this, style);
	}
	
}
