package com.gwtmobile.ui.client.widgets;

import java.util.Iterator;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.utils.Utils;

public class ToolbarContent extends WidgetBase implements HasWidgets {

	protected FlowPanel panel = new FlowPanel();
	
	public ToolbarContent() {
		initWidget(panel);
	}
	
	@Override
	public void add(Widget w) {
		panel.add(w);
	}

	@Override
	public void clear() {
		panel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return panel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return panel.remove(w);
	}
	
	@Override
	public void onTransitionEnd() {
		for (int i = 0; i < panel.getWidgetCount(); i++) {
			Widget w = panel.getWidget(i);
			if (Utils.isSubclassOf(w.getClass(), WidgetBase.class)) {
				((WidgetBase) w).onTransitionEnd();
			}
		}
	}
	
	public Widget getWidget() {
		return panel.getWidget(0);
	}

}
