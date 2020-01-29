package Chapter10.organizationforedit.right;

import Chapter10.organizationforedit.Column;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class InsertSql extends Sql {
  public InsertSql(String table, Column[] columns) {
    super(table, columns);
  }

  @Override
  public String generate() {
    return null;
  }
  // 只与插入有关的行为变为对应类的私有方法
  private String valuesList(Object[] fields, final Column[] columns) {return null;}
}
