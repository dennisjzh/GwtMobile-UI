package com.gwtmobile.ui.client.page;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.gwtmobile.ui.client.utils.Utils;

/**
 * This is a web app oriented history navigation model. This navigational model
 * used the browser's forward/back/direct url reference scheme.
 * 
 * If your app needs to run as a web application as well as a native 
 * application then add a reference the following to your GWT module:
 *
 * <pre>
 * &lt;replace-with
 *     class="com.gwtmobile.ui.client.page.BrowserHistoryNavigationImpl"&gt;
 *         &lt;when-type-is
 *         class="com.gwtmobile.ui.client.page.SerialHistoryNavigationImpl" /&gt;
 * &lt;/replace-with&gt;
 * </pre>
 * 
 * @see PageHistory#navigateTo(String, String)
 * @see HistoryNavigation.Mapper
 */
public class BrowserHistoryNavigationImpl extends SerialHistoryNavigationImpl implements ValueChangeHandler<String> {
	private Mapper _mapper;

	public BrowserHistoryNavigationImpl() {
		History.addValueChangeHandler(this);
	}
	
	@Override
	public void navigate(String token) {
		History.newItem(token, true);
	}
	
	@Override
	public void goBack(Page fromPage, Object returnValue) {
		History.back();
	}

	@Override
	public void setMapper(Mapper mapper) {
		_mapper = mapper;		
	}
	
	@Override
	public void startUp(Object param) {
		String token = History.getToken();
		loadPage(token);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
		loadPage(token);
	}

	protected void loadPage(String token) {
		final String[] tokenRef = parseParams(token);
		final Page page = _mapper.getPage(tokenRef[0]);
		if (page == null) // FIXME: Maybe throw an IllegalArgumentException?
			Utils.Console("No page registered for history token:" + token);
		else {
			page.tokenStateInfo = tokenRef[1];
			Page current = current();
			if (current == null) {
				Page.load(page);
				page.initNavigationIfRequired();
			} else
				current.goTo(page);
		}
	}
	
	private String[] parseParams(String token) {
		String[] ret = {token, null};
		int pos = token.indexOf(':');
		if (pos > -1) {
			ret[0] = token.substring(0, pos);
			ret[1] = token.substring(pos+1);
		}
		return ret;
	}
}