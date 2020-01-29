package Chapter10.organizationforedit.right;

import Chapter10.organizationforedit.Column;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class CreateSql extends Sql {
  public CreateSql(String table, Column[] columns) {
    super(table, columns);
  }

  @Override
  public String generate() {
    return null;
  }
}
