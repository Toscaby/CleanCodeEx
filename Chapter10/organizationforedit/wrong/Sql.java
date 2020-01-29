package Chapter10.organizationforedit.wrong;

import Chapter10.organizationforedit.Column;
import Chapter10.organizationforedit.Criteria;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class Sql {
  public Sql(String table, Column[] columns) {}
  public String create() {return "";}
  public String insert(Object[] fields) {return "";}
  public String selectAll() {return "";}
  public String findByKey(String keyColumn, String keyValue) {return "";}
  public String select(Column column, String pattern) {return "";}
  public String select(Criteria criteria) {return "";}
  public String preparedInsert() {return "";}
  // 公共私有行为
  private String columnList(Column[] columns) {return "";}
  private String valuesList(Object[] fields, final Column[] columns) {return "";}
  // 违反SRP 此方法仅与select有关
  // 出现只与类的一小部分有关的私有方法行为-->考虑拆分
  // 不过重构的基本动因是系统可能存在的变动
  private String selectWithCriteria(String criteria) {return "";}
  private String placeholderList(Column[] columns) {return "";}
}
