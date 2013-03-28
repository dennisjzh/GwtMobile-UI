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

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

class TextBoxBase extends com.google.gwt.user.client.ui.TextBoxBase
  implements FocusHandler, BlurHandler, IsGwtMobileWidget {

    private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();

  TextBoxBase(String type) {
      super(createNumberInputElement(type));
    setStyleName("TextBox " + capitalize(type));
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
    this.addStyleName("Focus");
  }

  @Override
  public void onBlur(BlurEvent event) {
    this.removeStyleName("Focus");
  }

  private static native InputElement createNumberInputElement(String type)  /*-{
    var e = $doc.createElement("INPUT");
    e.type = type;
    return e;
  }-*/;

  private String capitalize(String input) {
    return input.substring(0, 1).toUpperCase() +
        input.substring(1).toLowerCase();

  }

  @Override
  public void onInitialLoad() {
  }

  @Override
  public void setSecondaryStyle(String style) {
    _widgetHelper.setSecondaryStyle(this, style);
  }

  /**
   * Sets the type and displays the corresponding keyboard.
   * Possible HTML5 types are:<br/>
   * date, datetime, datetime-local, email, number, range, search, tel
   *
   * @param type the new type
   */
  public void setType(String type) {
    setElement(createNumberInputElement(type));
  }

  /**
   * Gets the maximum allowable length of the text box.
   *
   * @return the maximum length, in characters
   */
  public int getMaxLength() {
    return getInputElement().getMaxLength();
  }

  /**
   * Gets the number of visible characters in the text box.
   *
   * @return the number of visible characters
   */
  public int getVisibleLength() {
    return getInputElement().getSize();
  }

  /**
   * Sets the maximum allowable length of the text box.
   *
   * @param length the maximum length, in characters
   */
  public void setMaxLength(int length) {
    getInputElement().setMaxLength(length);
  }

  /**
   * Sets the number of visible characters in the text box.
   *
   * @param length the number of visible characters
   */
  public void setVisibleLength(int length) {
    getInputElement().setSize(length);
  }

  /**
   * Sets the placeholder.
   *
   * @param placeholder the new placeholder
   */
  public void setPlaceholder(String placeholder) {
    getElement().setAttribute("placeholder", placeholder);
  }

  private InputElement getInputElement() {
    return getElement().cast();
  }

}