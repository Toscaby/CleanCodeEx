package Chapter16;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public enum DateInterval {
  CLOSED {
    public boolean isIn(int d, int left, int right) {
      return d >= left && d <= right;
    }
  },
  CLOSED_LEFT {
    public boolean isIn(int d, int left, int right) {
      return d >= left && d < right;
    }
  },
  CLOSED_RIGHT {
    public boolean isIn(int d, int left, int right) {
      return d > left && d <= right;
    }
  },
  OPEN {
    public boolean isIn(int d, int left, int right) {
      return d > left && d < right;
    }
  };

  public abstract boolean isIn(int d, int left, int right);
}
