import java.util.Iterator;

public class Board {
  private final int[][] blocks;

  public Board(int[][] blocks) {
    this.blocks = copyBlocks(blocks);
  }

  public int dimension() {
    return blocks.length;
  }

  public int hamming() {
    int dim = dimension();
    int wrong = 0;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        int expected = (i * dim) + j + 1;
        int cell = blocks[i][j];
        if (cell != expected && cell != 0) {
          wrong++;
        }
      }
    }
    return wrong;
  }

  public int manhattan() {
    int dim = dimension();
    int distance = 0;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        int cell = blocks[i][j];
        if (cell != 0) {
          int expectedRow = (cell - 1) / dim;
          int expectedCol = (cell - 1) - expectedRow * dim;
          distance += Math.abs(expectedRow - i) + Math.abs(expectedCol - j);
        }
      }
    }
    return distance;
  }

  private int findBlankPosition() {
    int dim = dimension();
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        int cell = blocks[i][j];
        if (cell == 0) {
          return i * dim + j;
        }
      }
    }
    return -1;
  }

  public boolean isGoal() {
    return hamming() == 0;
  }

  public Board twin() {
    int dim = dimension();
    int blankPos = findBlankPosition();
    int blankRow = blankPos / dim;

    int[][] newBlocks = copyBlocks(blocks);
    for (int i = 0; i < dim; i++) {
      if (i != blankRow) {
        int tmp = newBlocks[i][0];
        newBlocks[i][0] = newBlocks[i][1];
        newBlocks[i][1] = tmp;
        break;
      }
    }
    return new Board(newBlocks);
  }

  public boolean equals(Object y) {
    if (y instanceof Board) {
      Board b = (Board) y;
      int dim = dimension();
      if (b.dimension() != dim) {
        return false;
      } else {
        for (int i = 0; i < dim; i++) {
          for (int j = 0; j < dim; j++) {
            if (this.blocks[i][j] != b.blocks[i][j]) {
              return false;
            }
          }
        }
        return true;
      }
    } else {
      return false;
    }
  }

  public Iterable<Board> neighbors() {
    return new Neighbors();
  }

  public String toString() {
    // string representation of the board (in the output format specified below)
    StringBuilder sb = new StringBuilder();
    int dim = dimension();
    sb.append(dim).append("\n");
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        sb.append(" ").append(this.blocks[i][j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  private class Neighbors implements Iterable<Board> {
    @Override
    public Iterator<Board> iterator() {
      Queue<Board> boards = new Queue<Board>();
      int dim = dimension();
      int blankPos = findBlankPosition();
      int blankRow = blankPos / dim;
      int blankCol = blankPos - blankRow * dim;
      if (blankRow > 0) {
        boards.enqueue(move(blankRow, blankCol, blankRow - 1, blankCol));
      }
      if (blankRow < dim - 1) {
        boards.enqueue(move(blankRow, blankCol, blankRow + 1, blankCol));
      }
      if (blankCol > 0) {
        boards.enqueue(move(blankRow, blankCol, blankRow, blankCol - 1));
      }
      if (blankCol < dim - 1) {
        boards.enqueue(move(blankRow, blankCol, blankRow, blankCol + 1));
      }
      return boards.iterator();
    }
  }

  private int[][] copyBlocks(int[][] oldBlocks) {
    int dim = oldBlocks.length;
    int[][] newBlocks = new int[dim][dim];
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        newBlocks[i][j] = oldBlocks[i][j];
      }
    }
    return newBlocks;
  }

  private Board move(int blankRow, int blankCol, int newRow, int newCol) {
    int[][] newBlocks = copyBlocks(blocks);
    newBlocks[blankRow][blankCol] = blocks[newRow][newCol];
    newBlocks[newRow][newCol] = 0;

    Board b = new Board(newBlocks);
    return b;
  }
}
