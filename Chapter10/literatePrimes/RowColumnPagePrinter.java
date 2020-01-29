package Chapter10.literatePrimes;

import java.io.PrintStream;

/**
 * @author Tosca
 * @date 28/1/2020
 */
public class RowColumnPagePrinter {
  private int rowPerPage;
  private int columnPerPage;
  private int numberPerPage;
  private String pageHeader;
  private PrintStream printStream;

  public RowColumnPagePrinter(int rowPerPage, int columnPerPage, String pageHeader) {
    this.rowPerPage = rowPerPage;
    this.columnPerPage = columnPerPage;
    this.pageHeader = pageHeader;
    numberPerPage = rowPerPage * columnPerPage;
    printStream = System.out;
  }

  public void print(int[] primes) {
    int pageNum = 1;
    for (int firstIndexOnPage = 0;
         firstIndexOnPage < primes.length;
         firstIndexOnPage += numberPerPage) {
      int lastIndexOnPage = Math.min(
          firstIndexOnPage + numberPerPage - 1,
          primes.length - 1
      );
      printPageHeader(pageNum);
      printPage(firstIndexOnPage, lastIndexOnPage, primes);
      pageNum ++;
    }
  }

  private void printPageHeader(int pageNum) {
    printStream.println(pageHeader + " --- Page " + pageNum);
    printStream.println();
  }

  private void printPage(int firstIndexOnPage, int lastIndexOnPage, int[] primes) {
    int firstIndexOfLastRowOnPage = firstIndexOnPage + rowPerPage - 1;
    for (int firstIndexInRow = firstIndexOnPage; firstIndexInRow <= firstIndexOfLastRowOnPage; firstIndexInRow ++) {
      printRow(firstIndexInRow, lastIndexOnPage, primes);
      printStream.println();
    }
  }

  private void printRow(int firstIndexInRow, int lastIndexOnPage, int[] primes) {
    for (int column = 0; column < columnPerPage; column++) {
      int index = firstIndexInRow + column * rowPerPage;
      if (index <= lastIndexOnPage) {
        printStream.format("%10d", primes[index]);
      }
    }
  }
}
