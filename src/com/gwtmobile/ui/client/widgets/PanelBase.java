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



public class PanelBase extends com.google.gwt.user.client.ui.FlowPanel implements IsGwtMobilePanel {
	    
	    private IsGwtMobileWidgetHelper widgetHelper = new IsGwtMobileWidgetHelper();
	    
	    public PanelBase(){
			if (Beans.isDesignTime()) {
				super.add(new DesignTimeMessagePanel(this));
			}
	    }
	    
	    @Override
	    public void add(Widget w) {
			if (Beans.isDesignTime()) {
				if (getWidgetCount() == 1 && 
						getWidget(0) instanceof DesignTimeMessagePanel) {
					DesignTimeMessagePanel designTimePanel = (DesignTimeMessagePanel) getWidget(0);
					if (!designTimePanel.hasError()) {
						super.clear();
					}
				}
			}
			super.add(w);
	    }
	    
	    public void addDesignTimeMessage(String message) {
			if (Beans.isDesignTime()) {
				if (getWidgetCount() == 1 && 
						getWidget(0) instanceof DesignTimeMessagePanel) {
					DesignTimeMessagePanel designTimePanel = (DesignTimeMessagePanel) getWidget(0);
					designTimePanel.addMessage(message);
				}
			}
	    }
	    
	    public void addDesignTimeError(String error) {
			if (Beans.isDesignTime()) {
				if (getWidgetCount() == 1 && 
						getWidget(0) instanceof DesignTimeMessagePanel) {
					DesignTimeMessagePanel designTimePanel = (DesignTimeMessagePanel) getWidget(0);
					designTimePanel.addErrorMessage(error);
				}
			}
	    }
	    
	    @Override
	    public void onLoad() {
	    	super.onLoad();
	    	widgetHelper.CheckInitialLoad(this);
	    }

	    @Override
		public void onInitialLoad() {
	    }
	    
	    @Override
		public void onTransitionEnd(TransitionDirection direction) {
//	    	for (Widget widget : this.getChildren()) {
//				if (widget instanceof IsGwtMobilePanel) {
//					((IsGwtMobilePanel) widget).onTransitionEnd();
//				}
//			}
	    }
	    
	    @Override
		public void setSecondaryStyle(String style) {
	    	widgetHelper.setSecondaryStyle(this, style);
	    }

		@Override
		public void addToPanel(Widget w) {
			super.add(w);
		}

}
