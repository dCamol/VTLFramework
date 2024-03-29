/*
Copyright (c) 2008 Health Market Science, Inc.

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

package com.healthmarketscience.sqlbuilder;

import com.healthmarketscience.common.util.AppendableExt;
import com.healthmarketscience.sqlbuilder.dbspec.Table;

import java.io.IOException;
import java.io.Serializable;



/**
 * Outputs the "simple" name of a table.
 *
 * @author James Ahlborn
 */
class TableObject extends SqlObject implements Serializable
{
  protected Table _table;
    
  protected TableObject(Table table) {
    _table = table;
  }

  @Override
  protected void collectSchemaObjects(ValidationContext vContext) {
    vContext.addTable(_table);
  }
    
  @Override
  public void appendTo(AppendableExt app) throws IOException {
    app.append(_table.getTableNameSQL());
  }
}
