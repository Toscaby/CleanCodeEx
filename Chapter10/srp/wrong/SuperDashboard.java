package Chapter10.srp.wrong;

import Chapter10.srp.Component;
import Chapter10.srp.JFrame;

/**
 * 违反SRP
 * @author Tosca
 * @date 28/1/2020
 */
public class SuperDashboard extends JFrame implements MetaDataUser {
  // 这个类有两个引起修改的原因 1）Swing更新会影响此类，可能还会影响版本号 2) 新功能发版影响版本号，但不一定影响Component

  // 提供对有焦点类的访问能力
  public Component getLastFocusedComponent() {return new Component();}
  public void setLastFocused(Component lastFocused) {}

  // 莫名其妙的提供版本号
  @Override
  public int getMajorVersionNumber() {
    return 0;
  }

  @Override
  public int getMinorVersionNumber() {
    return 0;
  }

  @Override
  public int getBuildNumber() {
    return 0;
  }
}
