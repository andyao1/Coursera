public class PercolationStats {
  private final double[] thresholds;
  private final double mean;
  private final double stddev;
  private final double stddevOverRootOfT;

  // perform T independent computational experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    if (N < 1 || T < 1) {
      throw new IllegalArgumentException();
    }
    thresholds = new double[T];
    for (int experiment = 0; experiment < T; experiment++) {
      Percolation p = new Percolation(N);
      int openedCount = 0;
      while (!p.percolates()) {
        int i = StdRandom.uniform(1, N + 1);
        int j = StdRandom.uniform(1, N + 1);
        if (!p.isOpen(i, j)) {
          p.open(i, j);
          openedCount++;
        }
      }
      thresholds[experiment] = openedCount * 1.0 / (N * N);
    }
    mean = StdStats.mean(thresholds);
    stddev = StdStats.stddev(thresholds);
    stddevOverRootOfT = 1.96 * stddev / Math.sqrt(T);
  }

  // perform T independent computational experiments on an N-by-N grid
  public double mean() {
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }

  // returns lower bound of the 95% confidence interval
  public double confidenceLo() {
    return mean - stddevOverRootOfT;
  }

  // returns upper bound of the 95% confidence interval
  public double confidenceHi() {
    return mean + stddevOverRootOfT;
  }

  // test client, described below
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(N, T);
    StdOut.printf("mean                    = %f", stats.mean());
    StdOut.println();
    StdOut.printf("stddev                  = %f", stats.stddev());
    StdOut.println();
    StdOut.printf("95%% confidence interval = %f, %f", stats.confidenceLo(),
        stats.confidenceHi());
    StdOut.println();
  }
}
