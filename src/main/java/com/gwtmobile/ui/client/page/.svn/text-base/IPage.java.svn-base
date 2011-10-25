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
package com.gwtmobile.ui.client.page;

/**
 * A <code>IPage</code> describes the current screen content.
 * 
 */
public interface IPage {

	/**
	 * Starts a transition form one page to another.
	 * 
	 * @param toPage
	 *            the target page which will be displayed next
	 */
	void goTo(final IPage toPage);



	/**
	 * Will be called before the page gets rendered on the screen.
	 */
	void beforeEnter();



	/**
	 * Will be called leaving the current page before the next page will be rendered on the screen.
	 */
	void beforeLeaving();

}
