package com.gwtmobile.ui.client.widgets;

import java.text.ParseException;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.shared.Parser;

public class DateTimeParser implements Parser<Date> {

	DateTimeFormat format;

	public DateTimeParser(String pattern) {
		format = DateTimeFormat.getFormat(pattern);
	}

	@Override
	public Date parse(CharSequence text) throws ParseException {
		if (text == null || text.length() == 0) {
			return null;
		}
		return format.parse(text.toString());
	}

}
