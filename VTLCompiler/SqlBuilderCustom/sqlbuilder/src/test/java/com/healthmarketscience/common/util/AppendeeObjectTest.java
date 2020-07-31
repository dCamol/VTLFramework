/*
Copyright (c) 2007 Health Market Science, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.healthmarketscience.common.util;

import junit.framework.TestCase;

/**
 * @author James Ahlborn
 */
public class AppendeeObjectTest extends TestCase {

  public AppendeeObjectTest(String name) {
    super(name);
  }

  public void test() throws Exception {
    AppendeeObject obj = new AppendeeObject();
    StringAppendableExt app = new StringAppendableExt();
    obj.appendTo(app);
    assertEquals(obj.toString(), app.toString());
    assertEquals(obj.toString(), obj.toString(5));
  }
}
