package com.gwtmobile.ui.client.page;

import java.util.Stack;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtmobile.ui.client.utils.Utils;

/**
 * This is an abstraction of History navigation model that was originally
 * implemented in PageHistory. This has been abstracted out in a separate
 * class to enable alternate history navigational models.
 * 
 * This implementation works well for a native application. However, for 
 * a hyrbrid, native/webapp consider using the browser navigation model.
 * 
 * @see BrowserHistoryNavigationImpl
 */
public class SerialHistoryNavigationImpl implements HistoryNavigation {
	private Stack<Page> _history = new Stack<Page>();
	
	@Override
	public void add(Page page) {
		_history.push(page);
	}
	
	@Override
	public void navigate(String token) {
		// DO nothing
	}

	@Override
	public Page current() {
		if (_history.isEmpty()) {
			return null;
		}
		return _history.peek();
	}
	
	@Override
	public Page from() {
		int size =_history.size();
		if (size < 2) {
			return null;
		}
		return _history.elementAt(size - 2);
	}
    
	@Override
	public Page back() {
        if (_history.isEmpty()) {
            return null;
        }
        return _history.pop();
    }
	
	@Override
	public void goBack(Page fromPage, Object returnValue) {
		PageHistory.setReturnValue(returnValue);
		final Page toPage = PageHistory.from();
		if (toPage == null) {
			// exit app here.
			return;
		}
		Element focus = Utils.getActiveElement();
		focus.blur();
		final Transition transition = fromPage.getTransition();
		if (transition != null) {
			transition.start(fromPage, toPage, RootLayoutPanel.get(), true);
		}
		else {
			Transition.start(fromPage, toPage, RootLayoutPanel.get());
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		// Do nothing b/c mapping is not required serial navigation
	}
	
	@Override
	public void startUp(Object param) {
		Page initial = (Page) param;
		Page.load(initial);
	}
}