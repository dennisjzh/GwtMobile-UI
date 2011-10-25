package com.gwtmobile.ui.client.CSS;
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
import com.google.gwt.resources.client.CssResource;
import com.gwtmobile.ui.client.widgets.DecoratedListItem;
import com.gwtmobile.ui.client.widgets.DropDownList;
import com.gwtmobile.ui.client.widgets.HorizontalPanel;
import com.gwtmobile.ui.client.widgets.RadioButton;
import com.gwtmobile.ui.client.widgets.Slider;
import com.gwtmobile.ui.client.widgets.VerticalPanel;



/**
 * Defines used CSS Resources as constants. We will not use 
 * {@link CssResource} here to enable better customization by external agencies.
 * 
 */
public interface StyleNames {

	/** Style forAufloesung. **/
	String HVGA = "HVGA";

	/** Style forAufloesung. **/
	String WVGA = "WVGA";

	/** Style forAufloesung. **/
	String QVGA = "QVGA";

	/** Styles for different types of devices */
	String Android = "Android";
	String iOS = "iOS";
	String Desktop = "Desktop";

	/** Style for {@link com.gwtmobile.ui.client.widgets.HeaderPanel}. */
	String HeaderPanel = "HeaderPanel";

	/** Style for {@link com.gwtmobile.ui.client.page.Page}. */
	String Page = "Page";

	/** Style for {@link com.gwtmobile.ui.client.widgets.Button}. */
	String Button = "Button";

	/** Style for {@link com.gwtmobile.ui.client.widgets.Button}. */
	String Pointer = "Pointer";

	/** Style for {@link com.gwtmobile.ui.client.widgets.BackButton}. */
	String BackButton = "BackButton";

	/** Style for {@link com.gwtmobile.ui.client.widgets.NextButton}. */
	String NextButton = "NextButton";

	/** Style for {@link com.gwtmobile.ui.client.widgets.ScrollPanel}. */
	String ScrollPanel = "ScrollPanel";

	/** Style for {@link com.gwtmobile.ui.client.widgets.ListPanel}. */
	String ListPanel = "ListPanel";

	/** Style for {@link com.gwtmobile.ui.client.widgets.TabPanel}. */
	String TabPanel = "TabPanel";
	
	/** Style for {@link HorizontalPanel}. **/
	String  HorizontalPanel ="HorizontalPanel";
	
	/** Style for an {@link VerticalPanel}. **/
	String VerticalPanel = "VerticalPanel";
	
	/** Style for {@link com.gwtmobile.ui.client.widgets.SlidePanel}. */
	String SlidePanel = "SlidePanel";
	

	/**
	 * Style for {@link com.gwtmobile.ui.client.widgets.Slide}s (element inside of
	 * {@link com.gwtmobile.ui.client.widgets.SlidePanel}.
	 */
	String Slide = "Slide";
	
	/** Sytle for a {@link Slider}. **/ 
	String Slider = "Slider";
	
	/** Style used inside a {@link com.gwtmobile.ui.client.widgets.ListPanel} **/
	String Chevron = "Chevron";


	/** Style for {@link com.gwtmobile.ui.client.widgets.AccordionPanel}. */
	String Accordion = "Accordion";

	/** Style for {@link com.gwtmobile.ui.client.widgets.AccordionHeader}. */
	String AccordionArrow = "AccordionArrow";

	/** Style for {@link com.gwtmobile.ui.client.widgets.TextBox}. */
	String TextBox = "TextBox";

	/** Style for {@link com.gwtmobile.ui.client.widgets.TextArea}. */
	String TextArea = "TextArea";

	/** Style for {@link com.gwtmobile.ui.client.widgets.CheckBoxGroup}. */
	String CheckBoxGroup = "CheckBoxGroup";
	
	/** Style for {@link com.gwtmobile.ui.client.widgets.CheckBox}. */
	String CheckBoxIndicator = "CheckBoxIndicator";
	
	/** Style for {@link RadioButton}. */
	String RadioButton ="RadioButton";

	/** Style for {@link com.gwtmobile.ui.client.widgets.RadioButtonGroup}. */
	String RadioButtonGroup = "RadioButtonGroup";

	/** Style for {@link com.gwtmobile.ui.client.widgets.FlipSwitch}. */
	String FlipSwitch = "FlipSwitch";

	/** Style for  {@link com.gwtmobile.ui.client.widgets.ToolbarPanel}. */
	String ToolbarPanel = "ToolbarPanel";
	
	/** Style for {@link DropDownList} **/
	String DropDownList = "DropDownList";

	/** Style for {@link DecoratedListItem} **/
	String decoratedListItemPanel = "DecoratedListItemPanel";
	
	/** Style for {@link DecoratedListItem} **/
	
	String decoratedListItemTitle = "DecoratedListItemTitle";
	
	/** Style for {@link DecoratedListItem} **/
	String decoratedListItemSubtitle = "DecoratedListItemSubtitle";
	

}
