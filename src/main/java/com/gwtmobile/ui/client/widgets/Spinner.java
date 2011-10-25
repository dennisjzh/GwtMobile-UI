/*
 * Copyright (c) 2011  vilbig.alexander
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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Widget;



/**
 * Spinner that supports spinner wheels as input medhod. Used e.g for Date entry, see {@link DateTextBox}
 * 
 * @author vilbig.alexander
 * @author wiese.daniel <br>
 * 
 */
public class Spinner extends Widget {

	public interface SpinnerResultHandler {

		public void onAccept(String value);



		public void onCancel();

	}

	public enum Style {

		DEFAULT(""), RIGHT("right"), READONLY("readonly"), SHRINK("shrink");

		private String name;



		Style(String n) {
			name = n;
		}



		public String getName() {
			return name;
		}

	};

	class Slot {
		String[] values;
		Style[] styles;
		int index;



		Slot(String[] values, Style[] styles, int index) {
			this.values = values;
			this.styles = styles;
			this.index = index;
		}
	}

	private static Spinner INSTANCE;

	private String value;
	private boolean visible = false;
	private SpinnerResultHandler handler;
	private List<Slot> slots;



	Spinner() {
		slots = new ArrayList<Slot>();
		setSpinnerCancel(this);
		setSpinnerDone(this);
	}



	public static Spinner getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Spinner();
		}
		return INSTANCE;
	}



	private void setValue(String value) {
		this.value = value;
		visible = false;
		if (handler != null) {
			handler.onAccept(value);
		}
	}



	private void setCancelled() {
		visible = false;
		if (handler != null) {
			handler.onCancel();
		}
	}



	public String getValue() {
		return value;
	}



	public void show(SpinnerResultHandler handler) {
		this.handler = handler;
		if (!visible) {
			visible = true;
			createSlots();
			showSpinner();
		}
	}



	public void addSlot(String[] values) {
		slots.add(new Slot(values, null, -1));
	}



	public void addSlot(String[] values, Style[] styles) {
		slots.add(new Slot(values, styles, -1));
	}



	public void addSlot(String[] values, Style[] styles, int defaultIndex) {
		slots.add(new Slot(values, styles, defaultIndex));
	}



	public void clearSlots() {
		slots.clear();
	}



	private void createSlots() {
		for (Slot s : slots) {
			if (s.styles == null) {
				addSpinnerSlot(createArray(s.values));
			} else {
				String styleString = "";
				for (Style style : s.styles) {
					styleString += style.getName() + " ";
				}
				if (s.index < 0) {
					addSpinnerSlot(createArray(s.values), styleString);
				} else {
					addSpinnerSlot(createArray(s.values), styleString, s.index);
				}
			}
		}
	}



	private native void addSpinnerSlot(JavaScriptObject values) /*-{
																$wnd.SpinningWheel.addSlot(values);
																}-*/;



	private native void addSpinnerSlot(JavaScriptObject values, String styles) /*-{
																				$wnd.SpinningWheel.addSlot(values, styles);
																				}-*/;



	private native void addSpinnerSlot(JavaScriptObject values, String styles, int defaultIndex) /*-{
																									$wnd.SpinningWheel.addSlot(values, styles, defaultIndex);
																									}-*/;



	private native void setSpinnerDone(Spinner x) /*-{

													$wnd.spinnerDone = function () {

													var results = $wnd.SpinningWheel.getSelectedValues();
													x.@com.gwtmobile.ui.client.widgets.Spinner::setValue(Ljava/lang/String;)(results.values.join(' '));

													};

													}-*/;



	private native void setSpinnerCancel(Spinner x) /*-{

													$wnd.spinnerCancel = function () {

													x.@com.gwtmobile.ui.client.widgets.Spinner::setCancelled()();

													};

													}-*/;



	private native void showSpinner() /*-{
										
										$wnd.SpinningWheel.setDoneAction($wnd.spinnerDone);
										$wnd.SpinningWheel.setCancelAction($wnd.spinnerCancel);
										$wnd.SpinningWheel.open();
										
										}-*/;



	/**
	 * Creates a JavaScrtip Array of String object from a Java String array.
	 * 
	 * @param array
	 *            the Java array
	 * @return the JavaScript array corresponding to the given Java array
	 */
	static private JavaScriptObject createArray(String[] array) {
		JavaScriptObject jsArray = JavaScriptObject.createArray();
		for (int i = 0; i < array.length; i++) {
			jsArray = put(jsArray, i, array[i]);
		}
		return jsArray;
	}



	static private native JavaScriptObject put(JavaScriptObject array, int index, String value) /*-{
																								array[index] = value;
																								return array;
																								}-*/;

}
