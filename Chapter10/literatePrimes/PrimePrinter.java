package Chapter10.literatePrimes;

/**
 * @author Tosca
 * @date 28/1/2020
 */
public class PrimePrinter {
  public static void main(String[] args) {
    final int NUM_OF_PRIMES = 1000;
    int[] primes = PrimeGenerator.generate(NUM_OF_PRIMES);

    final int ROWS_PER_PAGE = 50;
    final int COLUMNS_PER_PAGE = 4;
    RowColumnPagePrinter printer = new RowColumnPagePrinter(
        ROWS_PER_PAGE, COLUMNS_PER_PAGE, "The First " + NUM_OF_PRIMES + " Prime Numbers");
    printer.print(primes);
  }
}
