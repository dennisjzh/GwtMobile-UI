/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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

package com.gwtmobile.ui.client.page;

import com.google.gwt.core.client.GWT;

public class PageHistory {
	final private static HistoryNavigation HN_IMPL = GWT.create(SerialHistoryNavigationImpl.class); 
	
	private static Object _returnValue; 
	
	public static void add(Page page) {
		HN_IMPL.add(page);
	}
	
	public static void navigateTo(String pageName, String params) {
		String token = pageName + (params == null? "" : ":" + params);
		HN_IMPL.navigate(token);
	}
	
	public static Page current() {
		return HN_IMPL.current();
	}
	
	public static Page from() {
		return HN_IMPL.from();
	}
    
	public static Page back(Object returnValue) {
        _returnValue = returnValue;
        return back();
    }
    
	public static Page back() {
		return HN_IMPL.back();
    }
	
	public static void goBack(Page fromPage, Object returnValue) {
		HN_IMPL.goBack(fromPage, returnValue);
	}
    
	public static void setReturnValue(Object returnValue) {
        _returnValue = returnValue;
    }
    
	public static Object getReturnValue() {
        return _returnValue;
    }
	
	public static void setMapper(HistoryNavigation.Mapper mapper) {
		HN_IMPL.setMapper(mapper);
	}
	
	public static void startUp(Object param) {
		HN_IMPL.startUp(param);
	}
}
