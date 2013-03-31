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
 */

package com.gwtmobile.ui.client.widgets;

/*
 * Add the following to master.css to fix Chrome browser issue:
 *
 *   input::-webkit-outer-spin-button,
 *   input::-webkit-inner-spin-button {
 *     -webkit-appearance: none;
 *     margin: 0;
 *   }
 *
 *   Frank Mena
 */
public class NumericTextBox extends TextBoxBase {

  NumericTextBox() {

    super("numeric");
    getElement().setAttribute("pattern", "[0-9]*");
  }

}
