package Chapter10.cohesion;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * Cohesion example
 * @author Tosca
 * @date 28/1/2020
 */
public class Stack {
  // Cohesion: 一个类只有少量实体变量，尽量让多个方法操作这些变量
  private int top = 0;
  List<Integer> elements = new LinkedList<>();

  // 只有size没有用到两个变量
  public int size() {
    return top;
  }

  public void push(int element) {
    top ++;
    elements.add(element);
  }

  public int pop() throws EmptyStackException {
    if (top == 0) {
      throw new EmptyStackException();
    }
    int element = elements.get(--top);
    elements.remove(top);
    return element;
  }
}