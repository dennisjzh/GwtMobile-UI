package com.gwtmobile.ui.client.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.CSS.SubStyleNames;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;



/**
 * Slider with databinding support
 * 
 */
public class Slider extends WidgetBase implements DragEventsHandler, HasValueChangeHandlers<Integer>,
		IsEditor<LeafValueEditor<Integer>>, HasValue<Integer> {

	protected int _value = 0;
	protected FlowPanel _panel = new FlowPanel();
	protected HTML _label = new HTML(_value + "");
	protected HTML _slider = new HTML();
	private LeafValueEditor<Integer> editor;



	public Slider() {
		_panel.add(_label);
		_slider.setHTML("<div></div><div></div><div></div>");
		_panel.add(_slider);
		initWidget(_panel);
		setStyleName(StyleNames.Slider);
	}



	@Override
	public void onLoad() {
		super.onLoad();
		DragController.get().addDragEventsHandler(this);
	}



	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
	}



	@Override
	public void onDragStart(DragEvent e) {
		DragController.get().captureDragEvents(this);
		int value = computeNewValue(e);
		setValue(value);
	}



	@Override
	public void onDragMove(DragEvent e) {
		e.stopPropagation();
		int value = computeNewValue(e);
		setValue(value);
	}



	@Override
	public void onDragEnd(DragEvent e) {
		DragController.get().releaseDragCapture(this);
	}



	@Override
	public Integer getValue() {
		return _value;
	}



	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}



	public void setDisplayValue(boolean display) {
		if (display) {
			_label.removeStyleName(SubStyleNames.hide);
		} else {
			_label.addStyleName(SubStyleNames.hide);
		}
	}



	private int computeNewValue(DragEvent e) {
		Element ele = getElement();
		int offset = (int) e.X - ele.getAbsoluteLeft();
		int width = ele.getClientWidth();
		int value = offset * 100 / width;
		if (value > 100) {
			value = 100;
		} else if (value < 0) {
			value = 0;
		}
		return value;
	}



	private void updateSliderPosition() {
		_label.setHTML(_value + "");
		Element slider = getSliderElement();
		slider.getStyle().setWidth(_value, Unit.PCT);
	}



	private Element getSliderElement() {
		return (Element) _slider.getElement().getChild(1);
	}



	@Override
	public LeafValueEditor<Integer> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}



	public void setValue(Integer value) {
		if (value != null && _value != value) {
			_value = value;
			updateSliderPosition();
			ValueChangeEvent.fire(this, _value);
		}
	}



	@Override
	public void setValue(Integer value, boolean fireEvents) {
		setValue(value);
	}
}
