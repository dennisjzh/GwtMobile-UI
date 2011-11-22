package com.gwtmobile.ui.testdemo.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart.PieOptions;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;
import com.gwtmobile.ui.client.widgets.Button;

public class PageCharts  extends Page {

	interface Page1UiBinder extends UiBinder<Widget, PageCharts> { }
	private static Page1UiBinder uiBinder = GWT.create(Page1UiBinder.class);
	@UiField Button backButton;
	
	public PageCharts(){
		initWidget(uiBinder.createAndBindUi(this));
          
//		VisualizationUtils.loadVisualizationApi(new Runnable() {
//            public void run() {
//          
//                // Create a pie chart visualization.
//                PieChart pie = new PieChart(createTable(), createOptions());
//                HTMLPanel d = new HTMLPanel("");
//                d.add(pie);
//                mainPanel.add(d);
//              }
//            }, PieChart.PACKAGE);
     
	}

	
    @UiHandler("backButton")
    public void onClick(ClickEvent event) {
        PageHistory.Instance.current().goBack(null);
    }

    private PieOptions createOptions() {
    	PieOptions options = PieOptions.create();
        options.setWidth(400);
        options.setHeight(240);
        options.set3D(true);
        options.setTitle("My Daily Activities");
        return options;
      }
    
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
    
    private SelectHandler createSelectHandler(final PieChart chart) {
        return new SelectHandler() {
          @Override
          public void onSelect(SelectEvent event) {
            String message = "";
            
            // May be multiple selections.
            JsArray<Selection> selections = chart.getSelections();

            for (int i = 0; i < selections.length(); i++) {
              // add a new line for each selection
              message += i == 0 ? "" : "\n";
              
              Selection selection = selections.get(i);

              if (selection.isCell()) {
                // isCell() returns true if a cell has been selected.
                
                // getRow() returns the row number of the selected cell.
                int row = selection.getRow();
                // getColumn() returns the column number of the selected cell.
                int column = selection.getColumn();
                message += "cell " + row + ":" + column + " selected";
              } else if (selection.isRow()) {
                // isRow() returns true if an entire row has been selected.
                
                // getRow() returns the row number of the selected row.
                int row = selection.getRow();
                message += "row " + row + " selected";
              } else {
                // unreachable
                message += "Pie chart selections should be either row selections or cell selections.";
                message += "  Other visualizations support column selections as well.";
              }
            }
            
            Window.alert(message);
          }
        };
      }
    
}
