package com.gwtmobile.ui.testdemo.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface AppResources extends ClientBundle {

	public static final AppResources INSTANCE =  GWT.create(AppResources.class);
	

	@Source("images/ic_menu_bell.png") ImageResource iconBell();
	
}
