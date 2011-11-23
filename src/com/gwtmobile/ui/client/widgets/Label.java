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

import com.gwtmobile.ui.client.resources.MobileResources.StyleVariants;


public class Label extends com.google.gwt.user.client.ui.Label 
	implements IsGwtMobileWidget {
	
	public enum LabelVariants { Default, Header1, Header2, Emphasised, Small, SmallEmphasised, Legend};
	
	private LabelVariants labelTemplate = LabelVariants.Default;
	private StyleVariants labelBackgroundVariant = StyleVariants.None;
	private boolean withHPadding = false;
	private boolean withVPadding = false;
    private IsGwtMobileWidgetHelper widgetHelper = new IsGwtMobileWidgetHelper();
    
	public Label() {
		super();
		setStyleName("gwtm-Label");
		setLabelTemplate(labelTemplate);
		setLabelBackgroundVariant(labelBackgroundVariant);
		setWithHPadding(withHPadding);
		setWithVPadding(withVPadding);
	}
	
	public Label(String text) {
		this();
		setText(text);
	}
	
	public LabelVariants getLabelTemplate() {
		return labelTemplate;
	}

	public void setLabelTemplate(LabelVariants labelTemplate) {
		removeStyleName(this.labelTemplate.toString());
		this.labelTemplate = labelTemplate;
		addStyleName(labelTemplate.toString());
	}

	public StyleVariants getLabelBackgroundVariant() {
		return labelBackgroundVariant;
	}

	public void setLabelBackgroundVariant(StyleVariants labelBackgroundVariant) {
		removeStyleName(this.labelBackgroundVariant.toString());
		this.labelBackgroundVariant = labelBackgroundVariant;
		addStyleName(labelBackgroundVariant.toString());
	}

	public boolean isWithHPadding() {
		return withHPadding;
	}

	public void setWithHPadding(boolean withHPadding) {
		this.withHPadding = withHPadding;
		if (withHPadding){
			addStyleName("withHPadding");
		} else {
			removeStyleName("withHPadding");
		}
	}

	public boolean isWithVPadding() {
		return withVPadding;
	}

	public void setWithVPadding(boolean withVPadding) {
		this.withVPadding = withVPadding;
		if (withVPadding){
			addStyleName("withVPadding");
		} else {
			removeStyleName("withVPadding");
		}
	}
	
	@Override
	public void setWordWrap(boolean wrap){
		super.setWordWrap(wrap);
		if (!wrap){
			addStyleName("wordWrap");
		} else {
			removeStyleName("wordWrap");
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		widgetHelper.CheckInitialLoad(this);
	}

	@Override
	public void onInitialLoad() {
	}

	@Override
	public void setSecondaryStyle(String style) {
		widgetHelper.setSecondaryStyle(this, style);
	}

}
