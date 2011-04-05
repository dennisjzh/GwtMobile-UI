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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class DropDownList extends PanelBase implements FocusHandler, BlurHandler {

	ListBox _listBox = new ListBox();
	
	public DropDownList() {
		super();
		_panel.add(_listBox);
		_panel.add(new HTMLPanel(""));
		_panel.add(new HTMLPanel(""));
		_panel.add(new HTMLPanel(""));
		setStyleName("DropDownList");
		_listBox.addFocusHandler(this);
		_listBox.addBlurHandler(this);
		//getElement().insertFirst(Document.get().createDivElement());
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		addStyleName("Focus");
	}

	@Override
	public void onBlur(BlurEvent event) {
		removeStyleName("Focus");
	}
	
	@Override
	public void add(Widget w) {
		assert w.getClass() == DropDownItem.class : "Can only contain DropDownItem in DropDownList.";
		DropDownItem item = (DropDownItem) w;
		_listBox.addItem(item.getText());
	}

}
