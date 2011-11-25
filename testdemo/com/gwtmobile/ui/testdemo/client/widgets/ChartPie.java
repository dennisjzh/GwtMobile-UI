package com.gwtmobile.ui.testdemo.client.widgets;

import java.util.Map;

public class ChartPie extends CanvasBase {

	private double total;
	private double subTotal;
	private double[] angles;
	private double[] data;
	
	private double centerx;
	private double centery;
	
	public ChartPie(double[] data){
		super();
		setStylePrimaryName("gwtm-ChartPie");

        // initialise properties
        Map<String, Object> properties = getProperties();
        properties.put("chart.colors", new String[]{"red", "#ddd", "#0f0", "blue", "pink", "yellow", "black", "cyan"});
        properties.put("chart.strokestyle","#999");
        properties.put("chart.linewidth", 1);
        properties.put("chart.labels", new String[]{});
        properties.put("chart.labels.sticks", false);
        properties.put("chart.labels.sticks.length", 7);
        properties.put("chart.labels.sticks.color", "#aaa");
        properties.put("chart.segments", new String[]{});
        properties.put("chart.gutter.left", 25);
        properties.put("chart.gutter.right", 25);
        properties.put("chart.gutter.top", 25);
        properties.put("chart.gutter.bottom", 25);
        properties.put("chart.title", "");
        properties.put("chart.title.background", null);
        properties.put("chart.title.hpos", null);
        properties.put("chart.title.vpos", 0.5);
        properties.put("chart.title.bold", true);
        properties.put("chart.title.font", null);
        properties.put("chart.shadow", false);
        properties.put("chart.shadow.color", "rgba(0,0,0,0.5)");
        properties.put("chart.shadow.offsetx", 3);
        properties.put("chart.shadow.offsety", 3);
        properties.put("chart.shadow.blur", 3);
        properties.put("chart.text.size", 10);
        properties.put("chart.text.color", "black");
        properties.put("chart.text.font", "Verdana");
        properties.put("chart.contextmenu", null);
        properties.put("chart.tooltips", new String[]{});
        properties.put("chart.tooltips.event", "onclick");
        properties.put("chart.tooltips.effect", "fade");
        properties.put("chart.tooltips.css.class", "chart_tooltip");
        properties.put("chart.tooltips.highlight", true);
        properties.put("chart.highlight.style", "3d");
        properties.put("chart.highlight.style.2d.fill", "rgba(255,255,255,0.5)");
        properties.put("chart.highlight.style.2d.stroke", "rgba(255,255,255,0)");
        properties.put("chart.centerx", null);
        properties.put("chart.centery", null);
        properties.put("chart.radius", null);
        properties.put("chart.border", false);
        properties.put("chart.border.color", "rgba(255,255,255,0.5)");
        properties.put("chart.key", null);
        properties.put("chart.key.background", "white");
        properties.put("chart.key.position", "graph");
        properties.put("chart.key.halign", "right");
        properties.put("chart.key.shadow", false);
        properties.put("chart.key.shadow.color", "#666");
        properties.put("chart.key.shadow.blur", 3);
        properties.put("chart.key.shadow.offsetx", 2);
        properties.put("chart.key.shadow.offsety", 2);
        properties.put("chart.key.position.gutter.boxed", true);
        properties.put("chart.key.position.x", null);
        properties.put("chart.key.position.y", null);
        properties.put("chart.key.color.shape", "square");
        properties.put("chart.key.rounded", true);
        properties.put("chart.key.linewidth", 1);
        properties.put("chart.annotatable", false);
        properties.put("chart.annotate.color", "black");
        properties.put("chart.align", "center");
        properties.put("chart.zoom.factor", 1.5);
        properties.put("chart.zoom.fade.in", true);
        properties.put("chart.zoom.fade.out", true);
        properties.put("chart.zoom.hdir", "right");
        properties.put("chart.zoom.vdir", "down");
        properties.put("chart.zoom.frames", 25);
        properties.put("chart.zoom.delay", 16.666);
        properties.put("chart.zoom.shadow", true);
        properties.put("chart.zoom.mode", "canvas");
        properties.put("chart.zoom.thumbnail.width", 75);
        properties.put("chart.zoom.thumbnail.height", 75);
        properties.put("chart.zoom.background", true);
        properties.put("chart.zoom.action", "zoom");
        properties.put("chart.resizable", false);
        properties.put("chart.resize.handle.adjust", new int[]{0,0});
        properties.put("chart.resize.handle.background", null);
        properties.put("chart.variant", "pie");
        properties.put("chart.variant.donut.color", "white");
        properties.put("chart.exploded", new String[]{});
        properties.put("chart.effect.roundrobin.multiplier", 1);
        
		// initialise attributes
		this.total = 0;
        this.subTotal = 0;
        this.angles = new double[]{};
        this.data = data;
        
        // calculate data values total
        for (int i=0; i<data.length; i++) {
            this.total += data[i];
        }
        
	}
	
	public void draw(){
		
	}
	
	public void drawSegment(double degrees, String color, int last, int index){
		degrees  = degrees * getPropertyInt("chart.effect.roundrobin.multiplier");
		getContext().beginPath();
		getContext().setFillStyle(color);
		getContext().setStrokeStyle(getPropertyString("chart.strokestyle"));
		getContext().setLineWidth(0);
		
		if (hasProperty("chart.shadow")){
			// RGraph.SetShadow
		}
		
		double x = 0, y = 0;
		
		// exploded segments
		if (hasProperty("chart.exploded")){
			double explosion = getPropertyDouble("chart.exploded");
			double t = ((this.subTotal + (degrees / 2)) / (360/6.2830)) - 1.57;
            x        = (Math.cos(t) * explosion);
            y        = (Math.sin(t) * explosion);
            getContext().moveTo(this.centerx + x, this.centery + y);
		}
		
		// calculate angles
		double startAngle = ((this.subTotal / 57.3)) - 1.57;
		double endAngle   = (((this.subTotal + degrees) / 57.3)) - 1.57;
		double radius = hasProperty("chart.radius")?getPropertyDouble("chart.radius"):getRadius();
		getContext().arc(this.centerx + x,
                this.centery + y,
                radius,
                startAngle,
                endAngle,
                true);
		getContext().lineTo(this.centerx + x, this.centery + y);
		this.angles = new double[]{subTotal - 90, subTotal + degrees - 90, this.centerx + x, this.centery + y};
		getContext().closePath();
		getContext().fill();
		setProperty("chart.segments", new double[]{subTotal, subTotal + degrees});
		this.subTotal += degrees;
	}
	
	public void drawLabels(){
		
	}
	
	public void drawSticks(){
		
	}
	
	public void drawBorders(){
		
	}
	
	public void explode(){
		
	}
	
	public void getSegment(){
		
	}
	
	public double getRadius(){
        // calculate frequent globals
        double gutterLeft   = getPropertyDouble("chart.gutter.left");
        double gutterRight  = getPropertyDouble("chart.gutter.right");
        double gutterTop    = getPropertyDouble("chart.gutter.top");
        double gutterBottom = getPropertyDouble("chart.gutter.bottom");
		return Math.min(getCanvas().getOffsetHeight() - gutterTop - gutterBottom, getCanvas().getOffsetWidth() - gutterLeft - gutterRight) / 2;
		
	}
	
	public void highlightSegment(){
		
	}
	
}
