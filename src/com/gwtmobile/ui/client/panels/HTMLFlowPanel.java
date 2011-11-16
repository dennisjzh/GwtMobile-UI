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

package com.gwtmobile.ui.client.panels;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.widgets.IsGwtMobileWidgetHelper;

public class HTMLFlowPanel extends FlowPanel {

	protected HTMLPanel intPanel = new HTMLPanel("");
		
		public HTMLFlowPanel() {
			super.add(intPanel);
			setStyleName("gwtm-HTMLFlowPanel");
		}
		
		public Widget getIntPanel(){
			return intPanel;
		}
		
		@Override
		public void add(Widget w) {
			intPanel.add(w);
		}

		@Override
		public void clear() {
			intPanel.clear();
		}

		@Override
		public Iterator<Widget> iterator() {
			return intPanel.iterator();
		}

		@Override
		public boolean remove(Widget w) {
			return intPanel.remove(w);
		}

		public Widget getWidget(int index) {
			return intPanel.getWidget(index);
		}
		
		public int getWidgetCount() {
			return intPanel.getWidgetCount();
		}
		
//	    public void insert(Widget w, int beforeIndex) {
//	    	//extPanel.insert(w, beforeIndex);
//	    	intPanel.add(w);
//	    }
	    
	    private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();
	    
	    @Override
	    public void onLoad() {
	    	super.onLoad();
	    	_widgetHelper.CheckInitialLoad(this);
	    }

}
