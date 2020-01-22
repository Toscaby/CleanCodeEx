package Chapter4.wrongcases;

/**
 * @author Tosca
 * @date 2020/1/22
 */
public class GeneratePrimes {

  public static int[] generatePrimes(int maxValue) {
    if (maxValue >= 2) { // the only valid case
      // declarations
      int s = maxValue + 1;
      boolean[] f = new boolean[s];
      int i;

      // initialize array to true
      for (i = 0; i < s; ++i) {
        f[i] = true;
      }

      // get rid of know non-primes
      f[0] = f[1] = false;

      // sieve
      int j;
      for (i = 2; i < Math.sqrt(s) + 1; ++i) {
        if (f[i]) { // if i is uncrossed, cross its multiples.
          for (j = 2 * i; j < s; j += i) {
            f[j] = false;// mutiple is not prime.
          }
        }
      }

      // how many primes are there?
      int count = 0;
      for (i = 0; i < s; i++) {
        if (f[i]) {
          count++; // bump count
        }
      }

      int[] primes = new int[count];

      // move the primes into the result
      for (i = 0, j = 0; i < s; ++i) {
        if (f[i]) {
          primes[j++] = i;
        }
      }

      return primes;
    } else { // maxValue < 2
      return new int[0]; // return null array if bad input.
    }
  }
}
