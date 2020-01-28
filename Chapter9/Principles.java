package Chapter9;

/**
 * F.I.R.S.T原则
 * @author Tosca
 * @date 28/1/2020
 */
public class Principles {
  // 1. Fast
  // 测试应该快

  // 2. Independent
  // 测试应该相互独立，某一个测试不应该为下一个测试设定条件，可以单独运行每个测试，以及任何顺序的测试

  // 3. Repeatable
  // 测试应该在任何环境中重复通过 （如 有网无网 不同机型 等等）

  // 4. Self-Validating
  // 测试应该有boolean输出 不论通过还是失败

  // 5. Timely
  // 测试应该及时 提前(生产代码前)编写
}
