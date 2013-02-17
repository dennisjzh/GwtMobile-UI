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
package com.gwtmobile.ui.client.widgets;

import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragControllerMobile;




/**
 * A touch panel that allows propagation of touch events to child widgets.
 * Use for displaying maps that allow the user to pan and zoom.
 * 
 * @author Frank Mena
 *
 */
public class TouchPanel extends PanelBase {

    @Override
    public void onLoad() {
        super.onLoad();
        startTouch();
    }
  
    @Override
    public void onUnload() {
      stopTouch();
    }
 
  
    /**
     * Start touch. Useful when you have more than one touch target on 
     * different tabs of the same page.
     */
    public void startTouch() {
      DragController drag = DragController.get();
      if (drag instanceof DragControllerMobile)
          ((DragControllerMobile) drag).setStartPropagation();
    }
    
    /**
     * Stop touch.
     */
    public void stopTouch() {
      DragController drag = DragController.get();
      if (drag instanceof DragControllerMobile)
          ((DragControllerMobile) drag).setStopPropagation();
    }
    
}
