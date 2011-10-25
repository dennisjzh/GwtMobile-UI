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
package com.gwtmobile.ui.client.CSS;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


/**
 * Defines  transitions as CSS ClientBudle and relevant images.
 *
 */
public interface CSS extends ClientBundle {

	public static final CSS Instance = GWT.create(CSS.class);
	public static final CssTransitions T = Instance.transitions();
	
	
	//Ensure styles are injected
	public static boolean injected = T.ensureInjected();
	

	@Source("transitions.css")
	CssTransitions transitions();

	
	@Source("arrowdown.png")
	ImageResource arrowdown();

	@Source("arrowup.png")
	ImageResource arrowup();

	@Source("info.png")
	ImageResource info();

}
