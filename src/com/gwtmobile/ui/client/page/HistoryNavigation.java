package com.gwtmobile.ui.client.page;

/**
 * Represents a history navigation model that can be for moving between
 * pages.
 */
public interface HistoryNavigation {
	/**
	 * Light-weight page mapping interface that is used to retrieve page
	 * implementations as required. Note, implementations are required
	 * to accommodate caching strategies if required.
	 */
	public interface Mapper {
		public Page getPage(String pageName);
	}

	public abstract void add(Page page);
	
	public abstract void navigate(String token);

	public abstract Page current();

	public abstract Page from();

	public abstract Page back();
	
	public void goBack(Page fromPage, Object returnValue);

	public void setMapper(Mapper mapper);
	
	public void startUp(Object param);
}