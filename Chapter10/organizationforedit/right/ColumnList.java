package Chapter10.organizationforedit.right;

import Chapter10.organizationforedit.Column;

/**
 * 公共行为
 * @author Tosca
 * @date 29/1/2020
 */
public class ColumnList extends Sql {
  public ColumnList(String table, Column[] columns) {
    super(table, columns);
  }

  @Override
  public String generate() {
    return null;
  }
}
