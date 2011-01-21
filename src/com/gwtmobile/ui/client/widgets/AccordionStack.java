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

import java.util.Iterator;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.CSS;


public class AccordionStack extends WidgetBase implements HasWidgets {

    //TODO: make sure listPanel contains only two widget, a header and a content.
    private AccordionHeader _header;
    private AccordionContent _content;
    private FlowPanel _panel = new FlowPanel();
    
	public AccordionStack() {
	    initWidget(_panel);
	}
	
	@Override
    protected void onInitialLoad( ) {
        if (_header.getStyleName().indexOf(CSS.S.Close()) > -1) {
            setHeight(_header.getOffsetHeight() + "px");
        }
        else {
            setHeight(_header.getOffsetHeight() + _content.getOffsetHeight() + "px");
        }
        Timer timer = new Timer() {
            @Override
            public void run() {
                setStyleName("Stack");  //set style after setting height to 
                //prevent transition event from firing prematurely.
            }
        };
        timer.schedule(1);
	}
	
    @Override
    public void add(Widget w) {
        if (_header == null) {
            _header = (AccordionHeader) w;
        }
        else if (_content != null) {
            assert false : "Can only contain a header and a content.";
        }
        else {
            _content = (AccordionContent) w;
        }
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
    
    public void close() {
        setHeight(_header.getOffsetHeight() + "px");
        _header.removeStyleName(CSS.S.Open());
        _header.addStyleName(CSS.S.Close());
    }
    
//    public void open() {
//        //TODO: why content height is 2 times actual when the widget is loaded?
//        setHeight(_header.getOffsetHeight() + _content.getOffsetHeight() / 2 + "px");
//        _header.removeStyleName(CSS.Styles.Close());
//        _header.addStyleName(CSS.Styles.Open());
//    }

    public void toggle() {
        if (_header.getStyleName().indexOf(CSS.S.Close()) > -1) {
            setHeight(_header.getOffsetHeight() + _content.getOffsetHeight() + "px");
            _header.removeStyleName(CSS.S.Close());
            _header.addStyleName(CSS.S.Open());
        }
        else {
            setHeight(_header.getOffsetHeight() + "px");
            _header.removeStyleName(CSS.S.Open());
            _header.addStyleName(CSS.S.Close());
        }        
    }
    
    public AccordionHeader getHeader() {
        return _header;
    }
    
    public AccordionContent getContent() {
        return _content;
    }
}
