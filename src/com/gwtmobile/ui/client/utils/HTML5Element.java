package com.gwtmobile.ui.client.utils;

import com.google.gwt.dom.client.Element;

public class HTML5Element extends Element {

	public enum ContentEditable {
		True, False, inherit
	};

	public enum Direction {
		tlr, rtl, auto
	};

	public enum Draggable {
		True, False, auto
	};

	public enum DropZone {
		copy, move, link
	};

	protected HTML5Element() {
	}

	// attrib ACCESSKEY
	public final String getAcccessKey() {
		return (hasAttribute("accesskey") ? getAttribute("accesskey") : "");
	}

	public final void setAcccessKey(String accesskey) {
		if (accesskey == null || accesskey.equals("")) {
			removeAttribute("accesskey");
		} else {
			setAttribute("accesskey", accesskey);
		}
	}

	// attrib CONTENTEDITABLE
	public final ContentEditable getContentEditable() {
		if (hasAttribute("contenteditable")) {
			if (getAttribute("contenteditable").equalsIgnoreCase(
					ContentEditable.True.toString())) {
				return ContentEditable.True;
			} else if (getAttribute("contenteditable").equalsIgnoreCase(
					ContentEditable.False.toString())) {
				return ContentEditable.False;
			}
		}
		return ContentEditable.inherit;
	}

	public final void setContentEditable(ContentEditable contentEditable) {
		if (contentEditable == null) {
			removeAttribute("contenteditable");
		} else {
			setAttribute("contenteditable", contentEditable.toString()
					.toLowerCase());
		}
	}

	// attrib CONTEXTMENU
	public final String getContextMenu() {
		return (hasAttribute("contextmenu") ? getAttribute("") : "");
	}

	public final void setContextMenu(String contextmenu) {
		if (contextmenu == null || contextmenu.equals("")) {
			removeAttribute("contextmenu");
		} else {
			setAttribute("contextmenu", contextmenu);
		}
	}

	// attrib DROPZONE
	public final DropZone DropZone() {
		return (hasAttribute("dropzone") ? DropZone
				.valueOf(getAttribute("dropzone")) : DropZone.move);
	}

	public final void setDropZone(DropZone dropzone) {
		if (dropzone == null) {
			removeAttribute("dropzone");
		} else {
			setAttribute("dropzone", dropzone.toString().toLowerCase());
		}
	}

	// attrib HIDDEN
	public final boolean isHidden() {
		return this.hasAttribute("hidden");
	}

	public final void setHidden(boolean hidden) {
		if (hidden) {
			this.setAttribute("hidden", "hidden");
		} else if (isHidden()) {
			this.removeAttribute("hidden");
		}
	}

	// attrib HIDDEN
	public final boolean isSpellChecker() {
		return this.hasAttribute("spellchecker");
	}

	public final void setSpellChecker(boolean spellchecker) {
		if (spellchecker) {
			this.setAttribute("spellchecker", String.valueOf(spellchecker));
		} else if (isSpellChecker()) {
			this.removeAttribute("spellchecker");
		}
	}

}
