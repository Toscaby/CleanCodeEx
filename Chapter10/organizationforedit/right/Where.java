package Chapter10.organizationforedit.right;

import Chapter10.organizationforedit.Column;

/**
 * 根据原本select拆分的公共行为
 * @author Tosca
 * @date 29/1/2020
 */
public class Where extends Sql {
  public Where(String table, Column[] columns) {
    super(table, columns);
  }

  @Override
  public String generate() {
    return null;
  }
}
