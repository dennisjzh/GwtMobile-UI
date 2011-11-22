package com.gwtmobile.ui.client.widgets;

import java.beans.Beans;

import com.google.gwt.ajaxloader.client.Properties.TypeException;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart.PieOptions;
import com.gwtmobile.ui.client.widgets.HTMLPanel;

public class PieChart extends HTMLPanel {

	private com.google.gwt.visualization.client.visualizations.corechart.PieChart chart = null;
	private PieOptions options = PieOptions.create();
	private AbstractDataTable dataTable = null;
	
	public PieChart(){
		this("PieChart PlaceHolder");
	}
	
	public PieChart(String html){
		super(html);
		setStyleName("gwtm-ChartPie");
	}
	
	@Override
	public void onLoad(){
		super.onLoad();
		VisualizationUtils.loadVisualizationApi(new Runnable() {
            public void run() {
            	dataTable = createTable();
                chart = new com.google.gwt.visualization.client.visualizations.corechart.PieChart(dataTable, options);
                clear();
                add(chart);
              }
            }, com.google.gwt.visualization.client.visualizations.corechart.PieChart.PACKAGE);
		
	}
	
//    private PieOptions createOptions() {
//    	PieOptions options = PieOptions.create();
//    	options.setWidth(getChartWidth()==-1?400:getChartWidth());
//        options.setHeight(getChartHeight()==-1?240:getChartHeight());
//        options.set3D(isChartPie3D());
//        options.setTitle(getChartTitle()==null?"":getChartTitle());
//        return options;
//      }
    
    private AbstractDataTable createTable() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING, "Task");
        data.addColumn(ColumnType.NUMBER, "Hours per Day");
        data.addRows(2);
        data.setValue(0, 0, "Work");
        data.setValue(0, 1, 14);
        data.setValue(1, 0, "Sleep");
        data.setValue(1, 1, 10);
        return data;
      }

	public boolean isChartPie3D() {
		try {
			return options.getBoolean("is3D");
		} catch (TypeException e) {
			e.printStackTrace();
			setChartPie3D(false);
			return false;
		}
	}

	public void setChartPie3D(boolean chartPie3D) {
		options.set3D(chartPie3D);
	}

	public String getChartTitle() {
		try {
			return options.getString("title");
		} catch (TypeException e) {
			e.printStackTrace();
			return "";
		}	
	}

	public void setChartTitle(String chartTitle) {
		options.setTitle(chartTitle);
	}

	public int getChartWidth() {
		try {
			return Integer.parseInt(""+options.getNumber("width"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		} catch (TypeException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public void setChartWidth(int chartWidth) {
		options.setWidth(chartWidth);
		if (Beans.isDesignTime())
			this.setWidth(chartWidth+"px");
	}

	public int getChartHeight() {
		try {
			return Integer.parseInt(""+options.getNumber("height"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		} catch (TypeException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void setChartHeight(int chartHeight) {
		options.setHeight(chartHeight);
		if (Beans.isDesignTime())
			this.setHeight(chartHeight+"px");
	}

	public String getChartBackgroundColor() {
		try {
			return options.getString("backgroundColor");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		} catch (TypeException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setChartBackgroundColor(String chartBackgroundColor) {
		options.setBackgroundColor(chartBackgroundColor);
	}

	public PieOptions getOptions() {
		return options;
	}

	public void setOptions(PieOptions options) {
		this.options = options;
	}

}
