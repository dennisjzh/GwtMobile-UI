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

import java.beans.Beans;

import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;
import com.google.gwt.user.client.ui.Label;

public class PanelBase extends com.google.gwt.user.client.ui.FlowPanel implements IsGwtMobilePanel {
	    
	    private IsGwtMobileWidgetHelper _widgetHelper = new IsGwtMobileWidgetHelper();
	    
	    @Override
	    public void onLoad() {
	    	super.onLoad();
	    	_widgetHelper.CheckInitialLoad(this);
	    }

	    @Override
		public void onInitialLoad() {
	    }
	    
	    @Override
		public void onTransitionEnd(TransitionDirection direction) {
	    }
	    
	    @Override
		public void setSecondaryStyle(String style) {
	    	_widgetHelper.setSecondaryStyle(this, style);
	    }

	    @Override
	    public void add(Widget w) {
	    	if (isDesignTimeEmptyLabel(w)) {
	    		Label label = (Label)w;
	    		String designTimeMessage = getDesignTimeMessage();
	    		if (designTimeMessage == null) {
		    		label.setText("Empty " + Utils.getSimpleName(this.getClass()));
	    		} else {
		    		label.setText("Empty " + Utils.getSimpleName(this.getClass()) + 
		    				". " + designTimeMessage);
	    		}
	    	}
		    super.add(w);
	    }
	    
	    public boolean isDesignTimeEmptyLabel(Widget w) {
	    	return Beans.isDesignTime() && 
	    			(w instanceof Label) && 
	    			((Label)w).getText().startsWith("Empty ");
	    }
	    
	    protected String getDesignTimeMessage() {
	    	return "Add widgets to the panel.";
	    }; 
}
