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

package com.gwtmobile.ui.kitchensink.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.panels.HeaderPanel;
import com.gwtmobile.ui.client.panels.TabPanel;

public class TabBarPanelPage extends Page {

	@UiField HeaderPanel header;
	@UiField TabPanel tab;
	
	private static TabBARPanelPageUiBinder uiBinder = GWT
			.create(TabBARPanelPageUiBinder.class);

	interface TabBARPanelPageUiBinder extends UiBinder<Widget, TabBarPanelPage> {
	}

	public TabBarPanelPage() {
		initWidget(uiBinder.createAndBindUi(this));				
		
		tab.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				Utils.Console("Tab selection " + event.getSelectedItem() );
			}
		});
	}
}
