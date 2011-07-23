package com.gwtmobile.ui.ashinwdemo.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface PageResources extends ClientBundle {
	PageResources INSTANCE = GWT.create(PageResources.class);

	ImageResource sym_keyboard_return();

	ImageResource sym_keyboard_shift_locked();
	
	@Source("PageResources.css")
	Style style();

	public interface Style extends CssResource {
		String sym_keyboard_return();
		String sym_keyboard_shift_locked();
		
		String normalText();
		String smallerText();
		
		String catalogueItem();
		String slideContent();
	}
}
