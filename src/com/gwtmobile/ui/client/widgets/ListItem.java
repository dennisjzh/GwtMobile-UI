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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames.Secondary;
import com.gwtmobile.ui.client.widgets.ListPanel.Chevron;

public class ListItem extends FlowPanel{

	public enum ShowArrow { InheritFromParent, Visible, Hidden };
	private ShowArrow displayArrow = ShowArrow.InheritFromParent;
	private boolean enabled = true;

	public ListItem() {
		// there is no named style role for list item. 
    	//setStyleName("gwtm-ListItem");
    }

	// denis: Need this method to keep the setter consistent with ListPanel.
	// joao: this method is causing infinit loop, commenting for now
//	public void setDisplayArrow(boolean display) {
//		setDisplayArrow(display ? ShowArrow.Visible : ShowArrow.Hidden);
//	}

	public void setDisplayArrow(ShowArrow showA) {
		this.displayArrow = showA;
		if (ShowArrow.InheritFromParent.compareTo(showA) != -1)
			return;
		boolean show = (ShowArrow.Visible.compareTo(showA)!=-1);
		
		int last = getWidgetCount() - 1;
		if (last >=0) {
			Widget widget = getWidget(last);
			if (widget instanceof Chevron) {
				if (!show) {
					remove(last);
				}
			}
			else {
				if (show) {
					add(new ListPanel.Chevron());
				}
			}
		}		
	}
	
	public ShowArrow getDisplayArrow() {
		return this.displayArrow;
	}
	
	public void setEnabled(boolean disabled) {
		this.enabled = disabled;
		if (!isEnabled()) {
			addStyleName(Secondary.Disabled);
		} else {
			removeStyleName(Secondary.Disabled);
		}
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	void setDisplayArrowFromParent(ListPanel.ShowArrow show) {
		// Parent can only override if it has not been set.
		if (this.displayArrow == ShowArrow.InheritFromParent) {
			setDisplayArrow(ShowArrow.valueOf(show.toString()));
		}
	}

}
