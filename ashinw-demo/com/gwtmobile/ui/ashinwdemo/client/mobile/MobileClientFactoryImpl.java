package com.gwtmobile.ui.ashinwdemo.client.mobile;

import java.util.HashMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.gwtmobile.ui.ashinwdemo.client.ClientFactory;
import com.gwtmobile.ui.ashinwdemo.client.PageNames;
import com.gwtmobile.ui.client.page.HistoryNavigation;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;

public class MobileClientFactoryImpl implements ClientFactory {
	private class AppMapper implements HistoryNavigation.Mapper {
		private HashMap<String, AbstractPage> pageCache = new HashMap<String, AbstractPage>();
		
		@Override
		public Page getPage(String pageName) {
			AbstractPage ret = pageCache.get(pageName);
			if (ret != null)
				return ret;
			
			if (PageNames.HOME_NAME.equals(pageName))
				ret = new ViewHome();
			else if (PageNames.PAGE1_NAME.equals(pageName))
				ret = new ViewPage1();
			else if (PageNames.PAGE2_NAME.equals(pageName))
				ret = new ViewPage2();
			if (ret != null) {
				pageCache.put(pageName, ret);
				ret.preparePage(MobileClientFactoryImpl.this);
			} else
				ret = pageCache.get(PageNames.HOME_NAME);
			return ret;
		}
	}
	
	@Override
	public void bootStrap() {
		final AppMapper appMapper = new AppMapper();
		PageHistory.setMapper(appMapper);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				PageHistory.startUp(null);
//				Page page = appMapper.getPage(PageNames.HOME_NAME);
//				Page.load(page);
			}
		});
	}
	
	// add your managers and controllers here ...
}
