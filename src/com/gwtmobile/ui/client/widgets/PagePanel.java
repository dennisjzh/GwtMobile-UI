package com.gwtmobile.ui.client.widgets;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtmobile.ui.client.CSS.StyleNames.Primary;

public class PagePanel extends HTMLPanel {

	public PagePanel(String html) {
		super(html);
		setStyleName(Primary.PagePanel);
	}
}
