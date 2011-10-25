/*
 * Copyright (c) 2011 Vilbig.Alexander
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.CSS.StyleNames;
import com.gwtmobile.ui.client.CSS.SubStyleNames;
import com.gwtmobile.ui.client.page.Transition;


public class ToolbarPanel extends WidgetBase implements HasWidgets, ClickHandler {
	
	public interface ToolbarSelectionHandler {
		void toolSelected(int index, ToolbarElement te);
	}

    private FlowPanel mainPanel = new FlowPanel();
    private FlowPanel toolbarPanel = new FlowPanel();
    private FlowPanel contentPanel = new FlowPanel();
    private int selectedToolIndex = -1;
    private List<ToolbarSelectionHandler> selectionHandlers = new ArrayList<ToolbarSelectionHandler>();
    
    public ToolbarPanel() {
        initWidget(mainPanel);
        setStyleName(StyleNames.ToolbarPanel);
        mainPanel.add(contentPanel);
        mainPanel.add(toolbarPanel);
        toolbarPanel.addDomHandler(this, ClickEvent.getType());
    }
    
    @Override
    public void add(Widget w) {
        assert w instanceof ToolbarElement : "Can only place ToolbarElement widgets inside a Toolbar Panel.";
        toolbarPanel.add(w);
    }
    
    @Override
	public void onInitialLoad() {
    	if (toolbarPanel.getWidgetCount() > 0) {
    		//FIXME:allow a different default tab to be set?
            selectTool(0);
    	}
    }
    
    public void addSelectionHandler(ToolbarSelectionHandler handler) {
    	selectionHandlers.add(handler);
    }
    
    public void removeSelectionHandler(ToolbarSelectionHandler handler) {
    	selectionHandlers.remove(handler);
    }
    
    public ToolbarContent getContent() {
    	Widget child = null;
    	try {
    		child = contentPanel.getWidget(0);
    	} catch (IndexOutOfBoundsException ex) {
    		return null;
    	}
    	return (ToolbarContent) child;
    }
    
    public HasWidgets getContentArea() {
    	return contentPanel;
    }
    
    public void selectTool(int index) {
        if (selectedToolIndex == index) {
        	return;
        }
        ToolbarElement from = unselectCurrentTool();
        ToolbarElement to = (ToolbarElement) toolbarPanel.getWidget(index);
        to.addStyleName(SubStyleNames.selected);
        to.getHeader().selectImage(true);
        
        if (from == null) {
        	contentPanel.add(to.getContent());
        }
        else {
        	Transition.start(from.getContent(), to.getContent(), contentPanel);
        }
        selectedToolIndex = index;
        for (ToolbarSelectionHandler handler : selectionHandlers) {
        	handler.toolSelected(selectedToolIndex, to);
        }
    }

    public int getSelectedToolIndex() {
        return selectedToolIndex;
    }
    
    public void highlightTool(int index, boolean isHighlighted) {
        ToolbarElement te = (ToolbarElement) toolbarPanel.getWidget(index);
        if (te != null) {
        	te.getHeader().selectImage(isHighlighted);
        	if (isHighlighted) {
                te.addStyleName(SubStyleNames.selected);        		
        	} else {
                te.removeStyleName(SubStyleNames.selected);        		        		
        	}
        }
    }

	@Override
	public void onClick(ClickEvent event) {
		int index = getClickedToolHeaderIndex(event);
		if (index != -1) {
            selectTool(index);
		}
	}
	
    @Override
    public void clear() {
    	mainPanel.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return mainPanel.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return mainPanel.remove(w);
    }
    
    private ToolbarElement unselectCurrentTool() {
    	if (selectedToolIndex == -1) {
    		return null;
    	}
    	ToolbarElement te = (ToolbarElement) toolbarPanel.getWidget(selectedToolIndex);
    	te.removeStyleName("Selected");
    	te.getHeader().selectImage(false);
        return te;
    }

    private int getClickedToolHeaderIndex(ClickEvent e) {
        Element div = Element.as(e.getNativeEvent().getEventTarget());
        if (div == toolbarPanel.getElement()) {
        	return -1;
        }
        while (div.getParentElement() != toolbarPanel.getElement()) {
            div = div.getParentElement();
        }
        int index = DOM.getChildIndex(
        		(com.google.gwt.user.client.Element)toolbarPanel.getElement(), 
        		(com.google.gwt.user.client.Element)div);
        return index;
    }

}
