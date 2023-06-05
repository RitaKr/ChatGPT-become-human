import java.awt.*;

public class Maze {
    static final int wallSize = 10;
    static final int cellSize = 100;
    static final int rows = 6;  // Number of rows in the maze
    static final int cols = 8; // Number of columns in the maze
    Cell[][] maze;


    Cell[][] mazeLv1 = {
            {   new Cell(1, 0, 1, 1, 0, 0),
                    new Cell(1, 0, 1, 0, 0, 1),
                    new Cell(1, 0, 1, 0, 0, 2),
                    new Cell(1, 0, 1, 0, 0, 3),
                    new Cell(1, 0, 1, 0, 0, 4),
                    new Cell(1, 0, 0, 0, 0, 5),
                    new Cell(1, 1, 0, 0, 0, 6),
                    new Cell(1, 1, 0, 1, 0, 7),
            },
            {   new Cell(1, 0, 0, 1, 1, 0),
                    new Cell(1, 0, 1, 0, 1, 1),
                    new Cell(1, 0, 1, 0, 1, 2),
                    new Cell(1, 0, 1, 0, 1, 3),
                    new Cell(1, 0, 1, 0, 1, 4),
                    new Cell(0, 1, 0, 0, 1, 5),
                    new Cell(0, 1, 0, 1, 1, 6),
                    new Cell(0, 1, 0, 1, 1, 7),
            },
            {   new Cell(0, 1, 0, 1, 2, 0),
                    new Cell(1, 0, 0, 1, 2, 1),
                    new Cell(1, 0, 1, 0, 2, 2),
                    new Cell(1, 0, 1, 0, 2, 3),
                    new Cell(1, 0, 1, 0, 2, 4),
                    new Cell(0, 1, 0, 0, 2, 5),
                    new Cell(0, 1, 0, 1, 2, 6),
                    new Cell(0, 1, 0, 1, 2, 7),
            },
            {   new Cell(0, 1, 1, 1, 3, 0),
                    new Cell(0, 0, 1, 1, 3, 1),
                    new Cell(1, 0, 0, 0, 3, 2),
                    new Cell(1, 0, 0, 0, 3, 3),
                    new Cell(1, 1, 0, 0, 3, 4),
                    new Cell(0, 1, 0, 1, 3, 5),
                    new Cell(0, 1, 1, 1, 3, 6),
                    new Cell(0, 1, 0, 1, 3, 7),
            },
            {   new Cell(1, 0, 1, 1, 4, 0),
                    new Cell(1, 0, 1, 0, 4, 1),
                    new Cell(0, 1, 1, 0, 4, 2),
                    new Cell(0, 0, 0, 1, 4, 3),
                    new Cell(0, 1, 0, 0, 4, 4),
                    new Cell(0, 0, 1, 1, 4, 5),
                    new Cell(1, 0, 1, 0, 4, 6),
                    new Cell(0, 1, 0, 0, 4, 7),
            },
            {   new Cell(1, 0, 1, 1, 5, 0),
                    new Cell(1, 0, 1, 0, 5, 1),
                    new Cell(1, 0, 1, 0, 5, 2),
                    new Cell(0, 0, 1, 0, 5, 3),
                    new Cell(0, 1, 1, 0, 5, 4),
                    new Cell(1, 0, 1, 1, 5, 5),
                    new Cell(1, 0, 1, 0, 5, 6),
                    new Cell(0, 1, 1, 0, 5, 7),
            }
    };
    Cell[][] mazeLv2 = {
            {   new Cell(1, 0, 1, 1, 0, 0),
                    new Cell(1, 0, 1, 0, 0, 1),
                    new Cell(1, 0, 1, 0, 0, 2),
                    new Cell(1, 0, 1, 0, 0, 3),
                    new Cell(1, 0, 1, 0, 0, 4),
                    new Cell(1, 0, 0, 0, 0, 5),
                    new Cell(1, 1, 0, 0, 0, 6),
                    new Cell(1, 1, 0, 1, 0, 7),
            },
            {   new Cell(1, 0, 0, 1, 1, 0),
                    new Cell(1, 0, 1, 0, 1, 1),
                    new Cell(1, 0, 1, 0, 1, 2),
                    new Cell(1, 0, 1, 0, 1, 3),
                    new Cell(1, 0, 1, 0, 1, 4),
                    new Cell(0, 1, 0, 0, 1, 5),
                    new Cell(0, 1, 0, 1, 1, 6),
                    new Cell(0, 1, 0, 1, 1, 7),
            },
            {   new Cell(0, 1, 0, 1, 2, 0),
                    new Cell(1, 0, 0, 1, 2, 1),
                    new Cell(1, 0, 1, 0, 2, 2),
                    new Cell(1, 0, 1, 0, 2, 3),
                    new Cell(1, 0, 1, 0, 2, 4),
                    new Cell(0, 1, 0, 0, 2, 5),
                    new Cell(0, 1, 0, 1, 2, 6),
                    new Cell(0, 1, 0, 1, 2, 7),
            },
            {   new Cell(0, 1, 1, 1, 3, 0),
                    new Cell(0, 0, 1, 1, 3, 1),
                    new Cell(1, 0, 0, 0, 3, 2),
                    new Cell(1, 0, 0, 0, 3, 3),
                    new Cell(1, 1, 0, 0, 3, 4),
                    new Cell(0, 1, 0, 1, 3, 5),
                    new Cell(0, 1, 1, 1, 3, 6),
                    new Cell(0, 1, 0, 1, 3, 7),
            },
            {   new Cell(1, 0, 1, 1, 4, 0),
                    new Cell(1, 0, 1, 0, 4, 1),
                    new Cell(0, 1, 1, 0, 4, 2),
                    new Cell(0, 0, 0, 1, 4, 3),
                    new Cell(0, 1, 0, 0, 4, 4),
                    new Cell(0, 0, 1, 1, 4, 5),
                    new Cell(1, 0, 1, 0, 4, 6),
                    new Cell(0, 1, 0, 0, 4, 7),
            },
            {   new Cell(1, 0, 1, 1, 5, 0),
                    new Cell(1, 0, 1, 0, 5, 1),
                    new Cell(1, 0, 1, 0, 5, 2),
                    new Cell(0, 0, 1, 0, 5, 3),
                    new Cell(0, 1, 1, 0, 5, 4),
                    new Cell(1, 0, 1, 1, 5, 5),
                    new Cell(1, 0, 1, 0, 5, 6),
                    new Cell(0, 1, 1, 0, 5, 7),
            }
    };
    Cell[][] mazeLv3 = {
            {   new Cell(1, 0, 0, 1, 0, 0),
                    new Cell(1, 0, 1, 0, 0, 1),
                    new Cell(1, 0, 0, 0, 0, 2),
                    new Cell(1, 0, 1, 0, 0, 3),
                    new Cell(1, 1, 0, 0, 0, 4),
                    new Cell(1, 0, 0, 1, 0, 5),
                    new Cell(1, 1, 0, 0, 0, 6),
                    new Cell(1, 1, 0, 1, 0, 7),
            },
            {   new Cell(0, 1, 0, 1, 1, 0),
                    new Cell(1, 1, 0, 1, 1, 1),
                    new Cell(0, 1, 0, 1, 1, 2),
                    new Cell(1, 1, 0, 1, 1, 3),
                    new Cell(0, 1, 0, 1, 1, 4),
                    new Cell(0, 1, 0, 1, 1, 5),
                    new Cell(0, 0, 1, 1, 1, 6),
                    new Cell(0, 1, 1, 0, 1, 7),
            },
            {   new Cell(0, 1, 0, 1, 2, 0),
                    new Cell(0, 1, 0, 1, 2, 1),
                    new Cell(0, 1, 0, 1, 2, 2),
                    new Cell(0, 0, 1, 1, 2, 3),
                    new Cell(0, 1, 1, 0, 2, 4),
                    new Cell(0, 0, 1, 1, 2, 5),
                    new Cell(1, 0, 1, 0, 2, 6),
                    new Cell(1, 1, 0, 0, 2, 7),
            },
            {   new Cell(0, 1, 0, 1, 3, 0),
                    new Cell(0, 1, 0, 1, 3, 1),
                    new Cell(0, 0, 0, 1, 3, 2),
                    new Cell(1, 0, 0, 0, 3, 3),
                    new Cell(1, 0, 1, 0, 3, 4),
                    new Cell(1, 0, 1, 0, 3, 5),
                    new Cell(1, 0, 1, 0, 3, 6),
                    new Cell(0, 1, 0, 0, 3, 7),
            },
            {   new Cell(0, 1, 0, 1, 4, 0),
                    new Cell(0, 0, 1, 1, 4, 1),
                    new Cell(0, 1, 1, 0, 4, 2),
                    new Cell(0, 1, 1, 1, 4, 3),
                    new Cell(1, 1, 0, 1, 4, 4),
                    new Cell(1, 0, 1, 1, 4, 5),
                    new Cell(1, 0, 1, 0, 4, 6),
                    new Cell(0, 1, 0, 0, 4, 7),
            },
            {   new Cell(0, 1, 1, 1, 5, 0),
                    new Cell(1, 0, 1, 1, 5, 1),
                    new Cell(1, 1, 1, 0, 5, 2),
                    new Cell(1, 0, 1, 1, 5, 3),
                    new Cell(0, 0, 1, 0, 5, 4),
                    new Cell(1, 0, 1, 0, 5, 5),
                    new Cell(1, 0, 1, 0, 5, 6),
                    new Cell(0, 1, 1, 0, 5, 7),
            }
    };
    public Maze(int level) {
        switch (level) {
            case 1:
                maze = mazeLv1;
                break;
            case 2:
                maze = mazeLv2;
                break;
            case 3:
                maze = mazeLv3;
                break;
        }
    }

    class Cell {
        private int T; //has top border?
        private int R; //has right border?
        private int B; //has bottom border?
        private int L; //has left border?
        private int x; //top left corner of border
        private int y; //top left corner of border
        private int row; //index of cell's row
        private int col; //index of cell's col

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getT() {
            return T;
        }

        public void setT(int t) {
            T = t;
        }

        public int getR() {
            return R;
        }

        public void setR(int r) {
            R = r;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }

        public int getL() {
            return L;
        }

        public void setL(int l) {
            L = l;
        }

        public void setCoordinates(int row, int col) {
            this.row = row;
            this.col = col;
            x = col * (wallSize + cellSize);
            y = row * (wallSize + cellSize);
        }
        public Cell(int t, int r, int b, int l, int row, int col) {
            T = t;
            R = r;
            B = b;
            L = l;
            this.row = row;
            this.col = col;
            x = col * (wallSize + cellSize);
            y = row * (wallSize + cellSize);
        }
        public Cell(int t, int r, int b, int l) {
            T = t;
            R = r;
            B = b;
            L = l;

        }
    }

}
