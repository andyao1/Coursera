import java.util.Comparator;

public class Solver {
  private static class Node {
    private final Board board;
    private final Node previous;
    private final int moves;

    public Node(Board board, int moves, Node previous) {
      this.board = board;
      this.moves = moves;
      this.previous = previous;
    }
  }

  private static class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
      return o1.moves + o1.board.manhattan() - o2.moves - o2.board.manhattan();
    }
  }

  private MinPQ<Node> realQueue = new MinPQ<Node>(new NodeComparator());
  private MinPQ<Node> antiQueue = new MinPQ<Node>(new NodeComparator());
  private Node realNode;
  private Node antiNode;
  private boolean isSolveable = false;

  public Solver(Board initial) {
    realQueue.insert(new Node(initial, 0, null));
    antiQueue.insert(new Node(initial.twin(), 0, null));

    // find a solution to the initial board (using the A* algorithm)
    while (true) {
      realNode = realQueue.delMin();
      antiNode = antiQueue.delMin();

      // System.out.println(realNode.board);
      
      if (realNode.board.isGoal()) {
        isSolveable = true;
        break;
      } else if (antiNode.board.isGoal()) {
        isSolveable = false;
        break;
      } else {
        for (Board neighbor : realNode.board.neighbors()) {
          if (realNode.previous == null
              || !realNode.previous.board.equals(neighbor)) {
            realQueue.insert(new Node(neighbor, realNode.moves + 1, realNode));
          }
        }
        for (Board neighbor : antiNode.board.neighbors()) {
          if (antiNode.previous == null
              || !antiNode.previous.board.equals(neighbor)) {
            antiQueue.insert(new Node(neighbor, antiNode.moves + 1, antiNode));
          }
        }
      }
    }
  }

  public boolean isSolvable() {
    return isSolveable;
  }

  public int moves() {
    if (isSolveable) {
      return realNode.moves;
    } else {
      return -1;
    }
  }

  public Iterable<Board> solution() {
    if (isSolveable) {
      Stack<Board> boards = new Stack<Board>();
      Node node = realNode;
      while (node != null) {
        boards.push(node.board);
        node = node.previous;
      }
      return boards;
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        blocks[i][j] = in.readInt();
      }
    }
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}