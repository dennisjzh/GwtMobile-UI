package com.gwtmobile.ui.testdemo.client.widgets;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.gwtmobile.ui.client.widgets.HTMLPanel;
import com.gwtmobile.ui.client.widgets.Label;

public class CanvasBase extends HTMLPanel {

	// canvas and context
	private Canvas canvas = null;
	private Context2d context = null;
	private Map<String, Object> properties;
	// canvas attributes
	
	// context attributes
	
	public CanvasBase(){
		super("");
		this.canvas = com.google.gwt.canvas.client.Canvas.createIfSupported();
		this.setProperties(new HashMap<String, Object>());
		
		if (canvas == null) {
              add(new Label("Sorry, your system doesn't support Canvas"));
        } else {
        	context = canvas.getContext2d();
        	canvas.setStyleName("mainCanvas");
        	add(canvas);
        }
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public Context2d getContext() {
		return context;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public boolean hasProperty(String key){
		return this.properties.containsKey(key);
	}
	
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public void setProperty(String key, Object value){
		this.properties.put(key, value);
	}
	
	public Object getProperty(String key){
		return this.properties.get(key);
	}
	
	public int getPropertyInt(String key){
		return 0;
	}
	
	public double getPropertyDouble(String key){
		return 0;
	}
	
	public String getPropertyString(String key){
		return null;
	}
	
	public String[] getPropertyStringArray(String key){
		return null;
	}
	
	public int[] getPropertyIntArray(String key){
		return null;
	}
	
}
