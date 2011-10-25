/*
 * Copyright (c) 2011 Daniel Wiese
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

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.gwtmobile.ui.client.CSS.StyleNames;

/**
 * A ListItem with an Image. 
 * @author wiese.daniel
 * <br>copyright (C) 2011, SWM Services GmbH
 *
 */
public class DecoratedListItem extends ListItem {
	
	private Image img;
	private Label titleLabel;
	private Label subtitleLabel;
	
	public @UiConstructor DecoratedListItem(ImageResource image, String title, String subtitle) {
		HorizontalPanel panel = new HorizontalPanel();
		img = new Image(image);
		panel.add(img);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.addStyleName(StyleNames.decoratedListItemPanel);
		titleLabel = new Label(title);
		titleLabel.addStyleName(StyleNames.decoratedListItemTitle);
		vPanel.add(titleLabel);
		subtitleLabel = new Label(subtitle);
		subtitleLabel.addStyleName(StyleNames.decoratedListItemSubtitle);
		vPanel.add(subtitleLabel);
		panel.add(vPanel);
		add(panel);
	}
	
	public void setImage(ImageResource image) {
		img.setResource(image);
	}
	
	public void setTitle(String title) {
		titleLabel.setText(title);
	}
	
	public void setSubtitle(String subtitle) {
		subtitleLabel.setText(subtitle);
	}
	
}
