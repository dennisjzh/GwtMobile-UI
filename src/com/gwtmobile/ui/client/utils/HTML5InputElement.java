package com.gwtmobile.ui.client.utils;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(HTML5InputElement.TAG)
public class HTML5InputElement extends HTML5Element {

	static final String TAG = "input";

	public static enum InputTypes {
		button, checkbox, color, date, datetime, datetimeLocal, email, file, hidden, image, month, number, password, radio, range, reset, search, submit, tel, text, time, url, week
	};

	public static enum FormEncTypes {
		none, urlencoded, formdata, textplain
	};

	public static enum FormMethod {
		get, post
	};

	protected HTML5InputElement() {
	}

	public static HTML5InputElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (HTML5InputElement) elem;
	}

	// attrib ACCEPT
	public final String getAccept() {
		return (hasAttribute("accept") ? getAttribute("accept") : "");
	}

	public final void setAccept(String accept) {
		if (accept == null || accept.equals("")) {
			removeAttribute("accept");
		} else {
			setAttribute("accept", accept);
		}
	}

	// attrib ALT
	public final String getAlt() {
		return (hasAttribute("alt") ? getAttribute("alt") : "");
	}

	public final void setAlt(String alt) {
		if (alt == null || alt.equals("")) {
			removeAttribute("alt");
		} else {
			setAttribute("alt", alt);
		}
	}

	// attrib TYPE - but using INPUTTYPE instead
	public final InputTypes getType() {
		return (getAttribute("type").equals("datetime-local") ? InputTypes.datetimeLocal
				: InputTypes.valueOf(getAttribute("type")));
	}

	public final void setType(InputTypes inputType) {
		this.setAttribute("type",
				inputType == InputTypes.datetimeLocal ? "datetime-local"
						: inputType.toString());
	}

	// attrib AUTOCOMPLETE
	public final boolean isAutoComplete() {
		return (hasAttribute("autocomplete") && getAttribute("autocomplete")
				.equals("yes"));
	}

	public final void setAutoComplete(boolean accept) {
		setAttribute("autocomplete", (accept ? "yes" : "no"));
	}

	// attrib AUTOFOCUS
	public final boolean isAutoFocus() {
		return this.hasAttribute("autofocus");
	}

	public final void setAutoFocus(boolean autoFocus) {
		if (autoFocus) {
			this.setAttribute("autofocus", "autofocus");
		} else if (isAutoFocus()) {
			this.removeAttribute("autofocus");
		}
	}

	// attrib CHECKED
	public final boolean isChecked() {
		return this.hasAttribute("checked");
	}

	public final void setChecked(boolean checked) {
		if (checked) {
			this.setAttribute("checked", "checked");
		} else if (isChecked()) {
			this.removeAttribute("checked");
		}
	}

	// attrib DISABLED
	public final boolean isDisabled() {
		return this.hasAttribute("disabled");
	}

	public final void setDisabled(boolean disabled) {
		if (disabled) {
			this.setAttribute("disabled", "disabled");
		} else if (isDisabled()) {
			this.removeAttribute("disabled");
		}
	}

	// attrib FORM
	public final String getForm() {
		return this.hasAttribute("form") ? getAttribute("form") : "";
	}

	public final void setForm(String form_id) {
		if (form_id == null || form_id.equals("")) {
			this.removeAttribute("form");
		} else {
			this.setAttribute("form", form_id);
		}
	}

	// attrib FORMACTION
	public final String getFormAction() {
		return this.hasAttribute("formaction") ? getAttribute("formaction")
				: "";
	}

	public final void setFormAction(String form_action) {
		if (form_action == null || form_action.equals("")) {
			this.removeAttribute("formaction");
		} else {
			this.setAttribute("formaction", form_action);
		}
	}

	// attrib FORM ENC TYPE
	public final FormEncTypes getFormEncType() {
		if (!this.hasAttribute("formenctype"))
			return FormEncTypes.none;

		return FormEncTypes.valueOf(getAttribute("formenctype"));
	}

	public final void setFormEndType(FormEncTypes formenctype) {
		if (formenctype == FormEncTypes.none) {
			this.removeAttribute("formenctype");
		} else if (formenctype == FormEncTypes.urlencoded) {
			this.setAttribute("formenctype",
					"application/x-www-form-urlencoded");
		} else if (formenctype == FormEncTypes.formdata) {
			this.setAttribute("formenctype", "multipart/form-data");
		} else if (formenctype == FormEncTypes.textplain) {
			this.setAttribute("formenctype", "text/plain");
		}
	}

	// attrib FORM METHOD
	public final FormMethod getFormMethod() {
		if (!this.hasAttribute("formmethod"))
			return FormMethod.get;
		return FormMethod.valueOf(getAttribute("formmethod"));
	}

	public final void setFormMethod(FormMethod formmethod) {
		this.setAttribute("formmethod", formmethod.toString());
	}

	// attrib FORM NO VALIDATE
	public final boolean isFormNoValidate() {
		return this.hasAttribute("formnovalidate");
	}

	public final void setFormNoValidate(boolean novalidate) {
		if (novalidate) {
			this.setAttribute("formnovalidate", "formnovalidate");
		} else if (isFormNoValidate()) {
			this.removeAttribute("formnovalidate");
		}
	}

	// attrib FORM TARGET
	public final String getFormTarget() {
		return this.hasAttribute("formtarget") ? getAttribute("formtarget")
				: "";
	}

	public final void setFormTarget(String form_target) {
		if (form_target == null || form_target.equals("")) {
			this.removeAttribute("formtarget");
		} else {
			this.setAttribute("formtarget", form_target);
		}
	}

	// attrib FORM TARGET
	public final String getName() {
		return this.hasAttribute("name") ? getAttribute("name") : "";
	}

	public final void setName(String name) {
		if (name == null || name.equals("")) {
			this.removeAttribute("name");
		} else {
			this.setAttribute("name", name);
		}
	}

	// attrib HEIGHT
	public final String getHeight() {
		return this.hasAttribute("height") ? getAttribute("height") : "";
	}

	public final void setHeight(String height) {
		if (height == null || height.equals("")) {
			this.removeAttribute("height");
		} else {
			this.setAttribute("height", height);
		}
	}

	// attrib MAX
	public final String getMax() {
		return this.hasAttribute("max") ? getAttribute("max") : "";
	}

	public final void setMax(String max) {
		if (max == null || max.equals("")) {
			this.removeAttribute("max");
		} else {
			this.setAttribute("max", max);
		}
	}

	// attrib MIN
	public final String getMin() {
		return this.hasAttribute("min") ? getAttribute("min") : "";
	}

	public final void setMin(String min) {
		if (min == null || min.equals("")) {
			this.removeAttribute("min");
		} else {
			this.setAttribute("min", min);
		}
	}

	// attrib MAXLENGTH
	public final int getMaxLength() {
		return this.hasAttribute("maxlength") ? Integer
				.parseInt(getAttribute("maxlength")) : -1;
	}

	public final void setMaxLength(int maxlength) {
		if (maxlength > -1) {
			setAttribute("maxlength", String.valueOf(maxlength));
		} else {
			removeAttribute("maxlength");
		}
	}

	// attrib MULTIPLE
	public final boolean isMultiple() {
		return this.hasAttribute("multiple");
	}

	public final void setMultiple(boolean novalidate) {
		if (novalidate) {
			this.setAttribute("multiple", "multiple");
		} else if (isMultiple()) {
			this.removeAttribute("multiple");
		}
	}

	// attrib PATTERN
	public final String getPattern() {
		return this.hasAttribute("pattern") ? getAttribute("pattern") : "";
	}

	public final void setPattern(String pattern) {
		if (pattern == null || pattern.equals("")) {
			this.removeAttribute("pattern");
		} else {
			this.setAttribute("pattern", pattern);
		}
	}

	// attrib PLACEHOLDER
	public final String getPlaceholder() {
		return this.hasAttribute("placeholder") ? getAttribute("placeholder")
				: "";
	}

	public final void setPlaceholder(String placeholder) {
		if (placeholder == null || placeholder.equals("")) {
			this.removeAttribute("placeholder");
		} else {
			this.setAttribute("placeholder", placeholder);
		}
	}

	// attrib REQUIRED
	public final boolean isRequired() {
		return this.hasAttribute("required");
	}

	public final void setRequired(boolean required) {
		if (required) {
			this.setAttribute("required", "required");
		} else if (isRequired()) {
			this.removeAttribute("required");
		}
	}

	// attrib READONLY
	public final boolean isReadOnly() {
		return this.hasAttribute("readonly");
	}

	public final void setReadOnly(boolean required) {
		if (required) {
			this.setAttribute("readonly", "readonly");
		} else if (isRequired()) {
			this.removeAttribute("readonly");
		}
	}

	// attrib SIZE
	public final int getSize() {
		return this.hasAttribute("size") ? Integer
				.parseInt(getAttribute("size")) : -1;
	}

	public final void setSize(int size) {
		if (size > -1) {
			setAttribute("size", String.valueOf(size));
		} else {
			removeAttribute("size");
		}
	}

	// attrib SRC
	public final String getSrc() {
		return this.hasAttribute("src") ? getAttribute("src") : "";
	}

	public final void setSrc(String src) {
		if (src == null || src.equals("")) {
			this.removeAttribute("src");
		} else {
			this.setAttribute("src", src);
		}
	}

	// attrib PLACEHOLDER
	public final String getStep() {
		return this.hasAttribute("step") ? getAttribute("step") : "";
	}

	public final void setStep(String step) {
		if (step == null || step.equals("")) {
			this.removeAttribute("step");
		} else {
			this.setAttribute("step", step);
		}
	}

	// attrib VALUE
	public final String getValue() {
		return this.hasAttribute("value") ? getAttribute("value") : "";
	}

	public final void setValue(String value) {
		if (value == null || value.equals("")) {
			this.removeAttribute("value");
		} else {
			this.setAttribute("value", value);
		}
	}

	// attrib WIDTH
	public final String getWidth() {
		return this.hasAttribute("width") ? getAttribute("width") : "";
	}

	public final void setWidth(String width) {
		if (width == null || width.equals("")) {
			this.removeAttribute("width");
		} else {
			this.setAttribute("width", width);
		}
	}

	// event click
	public final native void click() /*-{
		this.click();
	}-*/;

	public final native void select() /*-{
	    this.select();
	  }-*/;
}
