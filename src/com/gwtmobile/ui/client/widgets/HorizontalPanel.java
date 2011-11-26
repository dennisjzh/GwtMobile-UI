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

import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.resources.MobileResources.StyleVariants;


public class HorizontalPanel extends PanelBase {

	public enum BoxPack {start, end, center, baseline, stretch, justify};
	private BoxPack boxPack = BoxPack.baseline;
	private StyleVariants styleVariants = StyleVariants.None;

    // 6 attributes very common to change and tryout within gwtdesigner
    // especially to accomodate widgets into panels for multiple layouts
    // on some panels, we might want to block users from playing with this, but 
    // this brings more flexibility and if we don't have
    private boolean withHPadding = false;
	private boolean withVPadding = false;
    private boolean withHMargin = false;
	private boolean withVMargin = false;
	private boolean forceFullWidth = false;
	private boolean forceFullHeight = false;
	
	public HorizontalPanel() {
		setStyleName(StyleNames.Primary.HorizontalPanel);
		setBoxPack(boxPack);
		setWithHPadding(withHPadding);
		setWithVPadding(withVPadding);
		setStyleVariants(styleVariants);
		updateUi();
	}
	
	public BoxPack getBoxPack() {
		return boxPack;
	}

	public void setBoxPack(BoxPack boxPack) {
		this.boxPack = boxPack;
		updateUi();
	}

	public StyleVariants getStyleVariants() {
		return styleVariants;
	}

	public void setStyleVariants(StyleVariants styleVariants) {
		removeStyleName(this.styleVariants.toString());
		this.styleVariants = styleVariants;
		addStyleName(this.styleVariants.toString());
	}

	public boolean isWithHPadding() {
		return withHPadding;
	}

	public void setWithHPadding(boolean withHPadding) {
		this.withHPadding = withHPadding;
		this.updateUi();
	}

	public boolean isWithVPadding() {
		return withVPadding;
	}

	public void setWithVPadding(boolean withVPadding) {
		this.withVPadding = withVPadding;
		this.updateUi();
	}
	
	public boolean isForceFullWidth() {
		return forceFullWidth;
	}

	public void setForceFullWidth(boolean forceFullWidth) {
		this.forceFullWidth = forceFullWidth;
		this.updateUi();
	}
	
	public boolean isWithHMargin() {
		return withHMargin;
	}

	public void setWithHMargin(boolean withHMargin) {
		this.withHMargin = withHMargin;
		this.updateUi();
	}

	public boolean isWithVMargin() {
		return withVMargin;
	}

	public void setWithVMargin(boolean withVMargin) {
		this.withVMargin = withVMargin;
		this.updateUi();
	}

	public boolean isForceFullHeight() {
		return forceFullHeight;
	}

	public void setForceFullHeight(boolean forceFullHeight) {
		this.forceFullHeight = forceFullHeight;
		this.updateUi();
	}	
	
	private void updateUi(){
		getElement().setAttribute("style", "-webkit-box-pack: "+boxPack.toString()+";"
				+ (isWithHPadding()?"padding-top: .5em; padding-bottom: .5em;":"")
				+ (isWithVPadding()?"padding-left: .5em; padding-right: .5em;":"")
				+ (isWithHMargin()?"margin-top: .5em; margin-bottom: .5em;":"")
				+ (isWithVMargin()?"margin-left: .5em; margin-right: .5em;":"")
				+ (isForceFullHeight()?"height: 100%;":"")
				+ (isForceFullWidth()?"width: 100%;":"")
			);
	}
	
}
