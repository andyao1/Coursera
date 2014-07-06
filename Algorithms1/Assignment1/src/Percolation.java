public class Percolation {
  private final WeightedQuickUnionUF ufForPerc;
  private final WeightedQuickUnionUF ufForFull;
  private final int topVE;
  private final int botVE;
  private final int dimension;
  private final boolean[] opens;

  /**
   * create N-by-N grid, with all sites blocked
   */
  public Percolation(int N) {
    dimension = N;
    int count = N * N;
    topVE = count;
    botVE = count + 1;
    opens = new boolean[count];
    ufForPerc = new WeightedQuickUnionUF(count + 2);
    ufForFull = new WeightedQuickUnionUF(count + 1);
  }

  private void checkInput(int i) {
    if (i < 1 || i > dimension) {
      throw new IndexOutOfBoundsException();
    }
  }

  private void check(int i, int j) {
    checkInput(i);
    checkInput(j);
  }

  private int getPosition(int i, int j) {
    return (i - 1) * dimension + j - 1;
  }

  private void union(int i, int j, boolean forFull) {
    ufForPerc.union(i, j);
    if (forFull) {
      ufForFull.union(i, j);
    }
  }

  /**
   * open site (row i, column j) if it is not already
   */
  public void open(int i, int j) {
    check(i, j);

    int curr = getPosition(i, j);
    if (!opens[curr]) {
      opens[curr] = true;
      if (i > 1) {
        if (opens[getPosition(i - 1, j)]) {
          union(curr, curr - dimension, true);
        }
      } else {
        union(topVE, curr, true);
      }
      if (j > 1 && opens[getPosition(i, j - 1)]) {
        union(curr, curr - 1, true);
      }

      if (j < dimension && opens[getPosition(i, j + 1)]) {
        union(curr, curr + 1, true);
      }

      if (i < dimension) {
        if (opens[getPosition(i + 1, j)]) {
          union(curr, curr + dimension, true);
        }
      } else {
        union(botVE, curr, false);
      }
    }
  }

  /**
   * is site (row i, column j) open?
   * 
   * @param i
   * @param j
   * @return
   */
  public boolean isOpen(int i, int j) {
    check(i, j);
    return opens[getPosition(i, j)];
  }

  /**
   * is site (row i, column j) full?
   * 
   * @param i
   * @param j
   * @return
   */
  public boolean isFull(int i, int j) {
    check(i, j);
    int pos = getPosition(i, j);
    boolean isOpened = opens[pos];
    boolean isConnected = ufForFull.connected(topVE, pos);
    return isOpened && isConnected;
  }

  /**
   * does the system percolate?
   * 
   * @return
   */
  public boolean percolates() {
    return ufForPerc.connected(topVE, botVE);
  }
}
