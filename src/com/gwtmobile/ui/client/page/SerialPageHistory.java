/*
 * Copyright (c) 2011 Zhihua (Dennis) Jiang
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
 *
 */

package com.gwtmobile.ui.client.page;

import java.util.Stack;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtmobile.ui.client.utils.Utils;

/**
 * This implementation works well for a native application. However, for
 * a hyrbrid, native/webapp consider using the browser navigation model.
 *
 * @see BrowserPageHistory
 */
public class SerialPageHistory implements PageHistory {
	private Stack<Page> _history = new Stack<Page>();
	private NavigateInfo _navigateInfo;

	public void add(Page page) {
		_history.push(page);
	}

	@Override
	public void navigate(String token) {
		// DO nothing
	}

	@Override
	public void navigate(String pageName, String params) {
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

	public Page back() {
        if (_history.isEmpty()) {
            return null;
        }
        return _history.pop();
    }

	@Override
	public void goTo(Page toPage, Object params, Transition transition) {
		Element focus = Utils.getActiveElement();
		focus.blur();
		final Page fromPage = current();
		setNavigateInfo(fromPage, params, false);
		add(toPage);
		toPage.setTransition(transition);
		if (transition != null) {
			transition.start(fromPage, toPage, RootLayoutPanel.get(), false);
		} else {
			Transition.start(fromPage, toPage, RootLayoutPanel.get());
		}
	}

	@Override
	public void goBack(Object returnValue) {
		Page fromPage = back();
		setNavigateInfo(fromPage, returnValue, true);
		final Page toPage = current();
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
	public void startUp(Page startUpPage) {
		RootLayoutPanel rootPanel = RootLayoutPanel.get();
		rootPanel.clear();
		rootPanel.add(startUpPage);
		_history.clear();
		_navigateInfo = null;
		add(startUpPage);
	}

	@Override
	public NavigateInfo getNavigateInfo() {
		NavigateInfo temp = _navigateInfo;
        _navigateInfo = null;	//memory: release reference once retrieved.
        return temp;
    }

	private void setNavigateInfo(Page fromPage, Object value, boolean goBack) {
		_navigateInfo = new NavigateInfo();
        _navigateInfo.setFromPage(fromPage);
        _navigateInfo.setValue(value);
        _navigateInfo.setGoBack(goBack);
	}

  /**
   * Go home and clear the stack.  Just pass "this" from the calling page.
   * </br>
   * By Frank Mena 2012-04-11
   *
   * @param fromPage the from page
   */
  public void goHome(Page fromPage) {

    if (false == _history.empty()) {
      Page homePage = _history.firstElement();
      _history.clear();

      Element focus = Utils.getActiveElement();
      focus.blur();
      final Transition transition = Transition.SLIDE;
      transition.start(fromPage, homePage, RootLayoutPanel.get(), true);
    }
  }



  /**
   * Gets the home page and clears the stack.
   * </br>
   * By Frank Mena 2012-04-11
   *
   * @return the home page
   */
  public Page getHomePageAndClearStack() {

    if (false == _history.empty()) {
      Page page = _history.firstElement();
      _history.clear();
      return page;

    }
    else {
      return null;
    }
  }



  /**
   * Gets the home page.
   * </br>
   * By Frank Mena 2012-04-11
   *
   * @return the home page
   */
  public Page getHomePage() {

    if (false == _history.empty()) {
      Page page = _history.firstElement();
      _history.clear();
      return page;

    }
    else {
      return null;
    }
  }

}