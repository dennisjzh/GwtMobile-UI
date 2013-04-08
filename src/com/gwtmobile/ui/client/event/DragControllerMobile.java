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

package com.gwtmobile.ui.client.event;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.user.client.Event;
import com.gwtmobile.ui.client.utils.Point;
import com.gwtmobile.ui.client.utils.Utils;

public class DragControllerMobile extends DragController {

    protected boolean _touchMoving = false;
    static protected boolean _stopPropagation = true;
    protected Element _touchTarget = null;
    
    DragControllerMobile() {
    }
    
    @Override
    protected void registerEvents() {
        super.registerEvents();
        if (_dragStartListener == null) {
            _dragStartListener = Utils.addEventListener(_source.getElement(), "touchstart", true, this);
            _dragMoveListener = Utils.addEventListener(_source.getElement(), "touchmove", true, this);
            _dragEndListener = Utils.addEventListener(_source.getElement(), "touchend", true, this);
        }
    }
    
    @Override
    protected void unregisterEvents() {
        super.unregisterEvents();
        if (_dragStartListener != null) {
            Utils.removeEventListener(_source.getElement(), "touchstart", true, _dragStartListener);
            Utils.removeEventListener(_source.getElement(), "touchmove", true, _dragMoveListener);
            Utils.removeEventListener(_source.getElement(), "touchend", true, _dragEndListener);
            _dragStartListener = _dragMoveListener = _dragEndListener = null;
        }
    }

	public void onTouchStart(TouchEvent e) {
        EventTarget target = e.getEventTarget();
        boolean preventDefault = true;
        if (Element.is(target)) {
            Element ele = Element.as(target);
            //INPUT element will not get focus if default action is prevented.
            if (Utils.isHtmlFormControl(ele)) {
                ele.focus();
                preventDefault = false;
            }
            _touchTarget = ele;
        }
        else {
        	_touchTarget = null;
        }
        if (preventDefault && _stopPropagation) {
            e.preventDefault();   //prevent default action of selecting text            
            e.stopPropagation();
        }
        //FIXME: for multi-touch platforms.
		onStart(e, new Point(e.touches().get(0).getClientX(), e.touches().get(0).getClientY()));
	}

	public void onTouchMove(TouchEvent e) {
	  if (_stopPropagation) {
	    e.preventDefault();
	    e.stopPropagation();
	  }
        _touchMoving = true;
		onMove(e, new Point(e.touches().get(0).getClientX(), e.touches().get(0).getClientY()));
	}

	public void onTouchEnd(TouchEvent e) {
    if (_stopPropagation) {
      e.preventDefault();
      e.stopPropagation();
    }
        if (!_touchMoving) {            
            Utils.Console("fireclick ");
            if (_touchTarget != null) {
            	fireClick(_touchTarget);
            }
        }
        _touchMoving = false;
		onEnd(e, new Point(e.changedTouches().get(0).getClientX(), e.changedTouches().get(0).getClientY()));
	}

	@Override
    public void onBrowserEvent(Event e) {
		String type = e.getType();
		if (type.equals("touchstart")) {
			onTouchStart((TouchEvent)e);
		}
		else if (type.equals("touchmove")) {
			onTouchMove((TouchEvent)e);
		}
		else if (type.equals("touchend")) {
			onTouchEnd((TouchEvent)e);
		}
		else {
		    super.onBrowserEvent(e);
		}
	}

	/**
	 * Sets the stop propagation. For widgets such as pan and zoom maps.
	 * 
	 * @author Frank Mena
	 */
	public void setStopPropagation() {
	  _stopPropagation = true;
	}
	
  public void setStartPropagation() {
    _stopPropagation = false;
  }
  
	protected native void fireClick(Element theTarget) /*-{
		
		// http://stackoverflow.com/questions/7184573/pick-up-the-android-version-in-the-browser-by-javascript
		var ua = $wnd.navigator.userAgent;
		if( ua.indexOf("Android") >= 0 ) {
  			var androidversion = parseFloat(ua.slice(ua.indexOf("Android")+8));
  			if (androidversion >= 4.1) {
  				// http://code.google.com/p/android/issues/detail?id=38808
  				return;
  			}
		}
		
	    if (theTarget.nodeType == 3) theTarget = theTarget.parentNode;
	
	    var theEvent = $doc.createEvent('MouseEvents');
	    theEvent.initEvent('click', true, true);
	    theTarget.dispatchEvent(theEvent);
	}-*/;

}
