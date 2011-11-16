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

import com.google.gwt.user.client.ui.HasWidgets;
import com.gwtmobile.ui.client.widgets.IsGwtMobileWidget;
import com.gwtmobile.ui.client.widgets.IsGwtMobileWidgetHelper;

public class FlowPanel extends com.google.gwt.user.client.ui.FlowPanel implements HasWidgets, IsGwtMobileWidget {
	    
	    private IsGwtMobileWidgetHelper widgetHelper = new IsGwtMobileWidgetHelper();
	    
	    public FlowPanel(){
	    	setStyleName("gtwm-FlowPanel");
	    }
	    
	    @Override
	    public void onLoad() {
	    	super.onLoad();
	    	widgetHelper.CheckInitialLoad(this);
	    }

	    @Override
		public void onInitialLoad() {
	    }
	    
	    //FIXME: shouldn't this method be on PageBase/PanelBase?
	    @Override
		public void onTransitionEnd() {    	
	    }
	    
	    @Override
		public void setSecondaryStyle(String style) {
	    	widgetHelper.setSecondaryStyle(this, style);
	    }

}
