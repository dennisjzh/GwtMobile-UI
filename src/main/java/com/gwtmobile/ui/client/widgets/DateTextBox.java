/*
 * Copyright (c) 2011 Vilbig Alexander
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

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.uibinder.client.UiConstructor;
import com.gwtmobile.ui.client.widgets.Spinner.SpinnerResultHandler;

/**
 * Defines a Date selection box.
 * @author vilbig.alexander
 * <br>copyright (C) 2011, SWM Services GmbH
 *
 */
public class DateTextBox extends BoxBase<Date> {
	
	public enum DateStyle {
		
		DATE("dd. MMMM yyyy", "d. MMMM yyyy"), 
		TIME("HH:mm", "HH : mm"), 
		DATETIME("dd.MM.yyyy HH:mm", "d. M. yyyy HH : mm");
		
		private String pattern;
		private String parse;
		
		private DateStyle(String pattern, String parse) {
			this.pattern = pattern;
			this.parse = parse;
		}
		
		public String getPattern() {
			return pattern;
		}
		
		public String getParse() {
			return parse;
		}
		
	}
	
	private static final int YEARSPAN = 20;
	private static final int MINUTESTEP = 5;
	
	private DateStyle style;
	private Spinner spinner;
	private int yearspan = YEARSPAN;
	private int startYear = -1;
	private int endYear = -1;
	private int minutestep = MINUTESTEP;
	
	public DateTextBox() {
		this(DateStyle.DATETIME);
	}

	public @UiConstructor DateTextBox(DateStyle dateStyle) {
		super(dateStyle.toString().toLowerCase(), new DateTimeFormatRenderer(DateTimeFormat.getFormat(dateStyle.getPattern())), new DateTimeParser(dateStyle.getPattern()));
		style = dateStyle;
		setReadOnly(true); // prevent opening of default keyboard on mobile devices
		updateYears();
		spinner = Spinner.getInstance();
		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				initSpinner();
				spinner.show(new SpinnerResultHandler() {
					
					@Override
					public void onCancel() {
						// do nothing
					}
					
					@Override
					public void onAccept(String value) {
						DateTimeFormat format = DateTimeFormat.getFormat(style.getParse());
						setValue(format.parse(value));
					}
				});
			}
		});
	}
	
	public void setStartYear(int startYear) {
		this.startYear = startYear;
		updateYears();
	}
	
	public void setEndYear(int endYear) {
		this.endYear = endYear;
		updateYears();
	}
	
	public void setYearspan(int yearspan) {
		this.yearspan = yearspan;
		updateYears();
	}
	
	public void setMinutestep(int minutestep) {
		this.minutestep = minutestep;
	}
	
	@SuppressWarnings("deprecation")
	private void updateYears() {
		Date value = getValue();
		if (value == null) {
			value = new Date();
		}
		int year = value.getYear() + 1900;

		if ((startYear < 0) && (endYear < 0)) {
			// both dates unset
			startYear = year - (yearspan / 2);
			endYear = startYear + yearspan;
			return;
		}
		if ((startYear >= 0) && (endYear < 0)) {
			// start date set -> try to accomodate current value
			if (getValue() != null) {
				if (startYear + yearspan < year) {
					yearspan = year - startYear + 1;
				} else if (year < startYear) {
					startYear = year;
				}
			}
			endYear = startYear + yearspan;
			return;
		}
		if ((startYear < 0) && (endYear >= 0)) {
			// end date set -> try to accomodate current value
			if (getValue() != null) {
				if (endYear - yearspan > year) {
					yearspan = endYear - year + 1;
				} else if (year > endYear) {
					endYear = year;
				}
			}
			startYear = endYear - yearspan;
			return;
		}
		if ((startYear >= 0) && (endYear >= 0)) {
			// both dates set -> try to accomodate current value
			if (year < startYear) {
				startYear = year - (yearspan / 2);
				endYear = startYear + yearspan;
			} else if (endYear < year) {
				endYear = year + (yearspan / 2);
				startYear = endYear - yearspan;
			} else {
				endYear = startYear + yearspan;				
			}
		}
	}
	
	@Override
	public void setValue(Date value) {
		super.setValue(value);
		updateYears();
	}

	@SuppressWarnings("deprecation")
	private void initSpinner() {
		Date value = getValue();
		if (value == null) {
			value = new Date();
		}
		int year = value.getYear() + 1900;
		
		String[] days;
		String[] months;
		String[] years;
		String[] hours;
		String[] minutes;
		spinner.clearSlots();
		switch (style) {
			case DATE:
				days = new String[31];
				months = LocaleInfo.getCurrentLocale().getDateTimeFormatInfo().monthsFull();
				years = new String[yearspan];
				for (int i = 0; i < 31; i++) {
					days[i] = (i + 1) + ".";
				}
				for (int i = 0; i < yearspan; i++) {
					years[i] = (startYear + i) + "";
				}
				spinner.addSlot(days, new Spinner.Style[] {Spinner.Style.SHRINK, Spinner.Style.RIGHT}, value.getDate() - 1);
				spinner.addSlot(months, new Spinner.Style[] {Spinner.Style.DEFAULT}, value.getMonth());
				spinner.addSlot(years, new Spinner.Style[] {Spinner.Style.SHRINK}, year - startYear);				
				break;
			case TIME:
				hours = new String[24];
				minutes = new String[60 / minutestep];
				for (int i = 0; i < 24; i++) {
					hours[i] = NumberFormat.getFormat("00").format(i);
				}
				for (int i = 0; i < (60 / minutestep); i++) {
					minutes[i] = NumberFormat.getFormat("00").format(i*minutestep);
				}
				spinner.addSlot(hours, new Spinner.Style[] {Spinner.Style.DEFAULT, Spinner.Style.RIGHT}, value.getHours());
				spinner.addSlot(new String[] {":"}, new Spinner.Style[] {Spinner.Style.SHRINK, Spinner.Style.READONLY});
				spinner.addSlot(minutes, new Spinner.Style[] {Spinner.Style.DEFAULT}, value.getMinutes() / minutestep);		
				break;
			case DATETIME:
				days = new String[31];
				months = new String[12];				
				years = new String[yearspan];
				hours = new String[24];
				minutes = new String[60 / minutestep];
				for (int i = 0; i < 31; i++) {
					days[i] = (i + 1) + ".";
				}
				for (int i = 0; i < 12; i++) {
					months[i] = (i + 1) + ".";
				}
				for (int i = 0; i < yearspan; i++) {
					years[i] = (startYear + i) + "";
				}
				for (int i = 0; i < 24; i++) {
					hours[i] = NumberFormat.getFormat("00").format(i);
				}
				for (int i = 0; i < (60 / minutestep); i++) {
					minutes[i] = NumberFormat.getFormat("00").format(i*minutestep);
				}
				spinner.addSlot(days, new Spinner.Style[] {Spinner.Style.SHRINK}, value.getDate() - 1);
				spinner.addSlot(months, new Spinner.Style[] {Spinner.Style.SHRINK}, value.getMonth());
				spinner.addSlot(years, new Spinner.Style[] {Spinner.Style.DEFAULT}, year - startYear);				
				spinner.addSlot(hours, new Spinner.Style[] {Spinner.Style.SHRINK, Spinner.Style.RIGHT}, value.getHours());
				spinner.addSlot(new String[] {":"}, new Spinner.Style[] {Spinner.Style.SHRINK, Spinner.Style.READONLY});
				spinner.addSlot(minutes, new Spinner.Style[] {Spinner.Style.SHRINK}, value.getMinutes() / minutestep);		
			default:
				break;
		}				
	}
	
}
