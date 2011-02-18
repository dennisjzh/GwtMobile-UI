package com.gwtmobile.ui.client.widgets;

import java.util.Iterator;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class Slide extends WidgetBase implements HasWidgets {

	protected FlowPanel _panel = new FlowPanel();
	
	public Slide() {
		initWidget(_panel);
		setStyleName("Slide");
	}
	
	@Override
	public void add(Widget w) {
		_panel.add(w);
	}

	@Override
	public void clear() {
		_panel.clear();
		
	}

	@Override
	public Iterator<Widget> iterator() {
		return _panel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return _panel.remove(w);
	}
	
}
