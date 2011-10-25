package com.gwtmobile.ui.client.widgets;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarElement extends WidgetBase implements HasWidgets {
    
    private ToolbarHeader header;
    private ToolbarContent content;

    public ToolbarElement() {
    }
    
    public ToolbarHeader getHeader() {
        return header;
    }    
    public ToolbarContent getContent() {
        return content;
    } 
    
    @Override
    public void add(Widget w) {    	
        if (this.getWidget() == null) {
        	assert w instanceof ToolbarHeader : "Expected a ToolbarHeader widget in a ToolbarElement";
            header = (ToolbarHeader)w;
            initWidget(header);
            header.selectImage(false);
        }
        else {
        	assert w instanceof ToolbarContent : "Expected a ToolbarContent widget in a ToolbarElement";
            content = (ToolbarContent) w;
        }
    }

    @Override
    public void clear() {
    }

    @Override
    public Iterator<Widget> iterator() {
        return null;
    }

    @Override
    public boolean remove(Widget w) {
        return false;
    }

}
