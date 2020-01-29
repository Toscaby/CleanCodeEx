package Chapter10.organizationforedit.right;

import Chapter10.organizationforedit.Column;

/**
 * 为了修改而组织，重构后符合开闭原则（OCP），比如增加updateSql类不需要修改其他现有类
 * @author Tosca
 * @date 29/1/2020
 */
public abstract class Sql {
  public Sql(String table, Column[] columns) {}
  abstract public String generate();
}
