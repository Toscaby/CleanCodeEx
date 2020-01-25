package Chapter4.rightcases;

/**
 * @author Tosca
 * @date 2020/1/22
 */
public class PrimeGenerator {

  private static boolean[] crossedOut;
  private static int[] result;

  public static int[] generatePrimes(int maxValue) {
    if (maxValue < 2) {
      return new int[0];
    } else {
      uncrossIntegersUpTo(maxValue);
      crossOutMultiples();
      putUncrossedIntegersIntoResult();
      return result;
    }
  }

  private static void uncrossIntegersUpTo(int maxValue) {
    crossedOut = new boolean[maxValue + 1];
    for (int i = 2; i < crossedOut.length; ++i) {
      crossedOut[i] = true;
    }
  }

  private static void crossOutMultiples() {
    int limit = determineIterationLimit();
    for (int i = 0; i < limit; ++i) {
      if (crossedOut[i]) {
        crossOutMultiplesOf(i);
      }
    }
  }

  private static int determineIterationLimit() {
    // Every multiple in the array has a prime factor that
    // is less than or equal to the root of the array size,
    // so we don't have to cross out multiples of numbers
    // larger than that root.
    return (int)Math.sqrt(crossedOut.length) + 1;
  }

  private static void crossOutMultiplesOf(int i) {
    for (int multiple = 2 * i; multiple < crossedOut.length; multiple += i) {
      crossedOut[multiple] = true;
    }
  }

  private static boolean notCrossed(int i) {
    return !crossedOut[i];
  }

  private static void putUncrossedIntegersIntoResult() {
    result = new int[numberOfUncrossedIntegers()];
    for (int j = 0, i = 2; i < crossedOut.length; ++i) {
      if (notCrossed(i)) {
        result[j++] = i;
      }
    }
  }

  private static int numberOfUncrossedIntegers() {
    int count = 0;
    for (int i = 2; i < crossedOut.length; i++) {
      if (notCrossed(i)) {
        count++;
      }
    }
    return count;
  }
}
