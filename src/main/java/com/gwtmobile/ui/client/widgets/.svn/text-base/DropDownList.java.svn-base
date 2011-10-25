/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.CSS.SubStyleNames;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.itf.IProvidesKeyAndValue;



/**
 * Listbox that supports data binding and internationalization
 * 
 * @param <T>
 *            the list box type
 */
public class DropDownList<T> extends PanelBase implements FocusHandler, BlurHandler, ChangeHandler,
		HasValueChangeHandlers<T>, IsEditor<TakesValueEditor<T>>, HasConstrainedValue<T> {

	ListBox _listBox = new ListBox();
	private final Map<Integer, String> indexKeyMap = new HashMap<Integer, String>();
	private final Map<String, Integer> keyIndexMap = new HashMap<String, Integer>();

	private TakesValueEditor<T> editor;
	private IProvidesKeyAndValue<T> keyValueProvider;
	private Renderer<T> renderer;
	private int lastIndex = 0;
	private T currentValue;



	public DropDownList() {
		super();
		_panel.add(_listBox);
		if (!Utils.isIOS()) {
			_panel.add(new HTMLPanel(""));
			_panel.add(new HTMLPanel(""));
			_panel.add(new HTMLPanel(""));
		}
		setStyleName(StyleNames.DropDownList);
		_listBox.addFocusHandler(this);
		_listBox.addBlurHandler(this);
		_listBox.addChangeHandler(this);
	}



	@Override
	protected void onUnload() {
		removeStyleName(SubStyleNames.focus);
		super.onUnload();
	}



	@Override
	public void onFocus(FocusEvent event) {
		addStyleName(SubStyleNames.focus);
	}



	@Override
	public void onBlur(BlurEvent event) {
		removeStyleName(SubStyleNames.focus);
	}



	@Override
	public void onChange(ChangeEvent event) {
		ValueChangeEvent.fire(this, getValue());
	}



	/**
	 * keyValueProvider the keyValueProvider to set.
	 * 
	 * @param keyValueProvider
	 *            the keyValueProvider to set
	 */
	public void setKeyValueProvider(IProvidesKeyAndValue<T> keyValueProvider) {
		this.keyValueProvider = keyValueProvider;
	}



	/**
	 * renderer the renderer to set.
	 * 
	 * @param renderer
	 *            the renderer to set
	 */
	public void setRenderer(Renderer<T> renderer) {
		this.renderer = renderer;
	}



	@Override
	public void add(Widget w) {
		assert w instanceof DropDownItem : "Can only contain DropDownItem in DropDownList.";
		DropDownItem item = (DropDownItem) w;
		if (keyValueProvider != null) {
			// die idee ist, dass nur key's hinzgefuegt werden > das sind die auswahlbaren enum werte
			if (this.keyValueProvider.getValue(item.getValue()) != null) {
				T enumValue = this.keyValueProvider.getValue(item.getValue());
				this.keyIndexMap.put(item.getValue(), lastIndex);
				this.indexKeyMap.put(lastIndex, item.getValue());
				_listBox.addItem(this.renderer.render(enumValue), item.getValue());
				lastIndex++;
			}
		} else {
			// support String as Type T (shortcut)
			_listBox.addItem(item.getText(), item.getValue());
		}
	}



	public ListBox getListBox() {
		return _listBox;
	}



	/**
	 * Returns a {@link TakesValueEditor} backed by the ValueListBox.
	 */
	public TakesValueEditor<T> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}



	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		int index = _listBox.getSelectedIndex();
		if (index >= 0) {
			if (keyValueProvider != null) {
				return this.keyValueProvider.getValue(this.indexKeyMap.get(index));
			} else {
				// support String as Type T (shortcut)
				return (T) _listBox.getValue(index);
			}
		}
		return null;
	}



	@Override
	public void setValue(T value) {
		Integer indexToSelect = 0;
		if (keyValueProvider != null) {
			indexToSelect = this.keyIndexMap.get(this.keyValueProvider.getKey(value));
		} else {
			// support String as Type T (shortcut)
			String stringValue = (String) value;
			for (int i = 0; i < _listBox.getItemCount(); i++) {
				if (_listBox.getValue(i).equals(stringValue)) {
					indexToSelect = i;
					break;
				}
			}
		}
		_listBox.setSelectedIndex(indexToSelect);
		this.currentValue = value;

	}



	@Override
	public void setValue(T value, boolean fireEvents) {
		final T oldValue = currentValue;
		setValue(value);
		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}

	}



	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}



	@Override
	public void setAcceptableValues(Collection<T> values) {
		// intentionally left empty

	}

}
