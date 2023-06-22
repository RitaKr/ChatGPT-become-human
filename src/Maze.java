import java.awt.*;

public class Maze {
    public static final int wallSize = 10;
    public static final int cellSize = 100;
    public static final int rows = 6;  // Number of rows in the maze
    public static final int cols = 8; // Number of columns in the maze
    public Cell[][] maze;


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
    Cell[][] mazeLv3 = {
            {   new Cell(1, 1, 0, 1, 0, 0),
                    new Cell(1, 0, 0, 1, 0, 1),
                    new Cell(1, 0, 0, 0, 0, 2),
                    new Cell(1, 0, 1, 0, 0, 3),
                    new Cell(1, 0, 0, 0, 0, 4),
                    new Cell(1, 0, 0, 0, 0, 5),
                    new Cell(1, 0, 0, 0, 0, 6),
                    new Cell(1, 1, 1, 0, 0, 7),
            },
            {   new Cell(0, 1, 0, 1, 1, 0),
                    new Cell(0, 1, 1, 1, 1, 1),
                    new Cell(0, 1, 1, 1, 1, 2),
                    new Cell(1, 1, 0, 1, 1, 3),
                    new Cell(0, 1, 1, 1, 1, 4),
                    new Cell(0, 0, 0, 1, 1, 5),
                    new Cell(0, 0, 0, 1, 1, 6),
                    new Cell(1, 1, 0, 0, 1, 7),
            },
            {   new Cell(0, 0, 0, 1, 2, 0),
                    new Cell(1, 0, 1, 0, 2, 1),
                    new Cell(1, 0, 1, 0, 2, 2),
                    new Cell(0, 0, 1, 0, 2, 3),
                    new Cell(1, 0, 1, 0, 2, 4),
                    new Cell(0, 1, 1, 0, 2, 5),
                    new Cell(0, 0, 0, 1, 2, 6),
                    new Cell(0, 1, 1, 0, 2, 7),
            },
            {   new Cell(0, 1, 0, 1, 3, 0),
                    new Cell(1, 0, 1, 1, 3, 1),
                    new Cell(1, 0, 1, 0, 3, 2),
                    new Cell(1, 0, 0, 0, 3, 3),
                    new Cell(1, 0, 0, 0, 3, 4),
                    new Cell(1, 0, 0, 0, 3, 5),
                    new Cell(0, 1, 0, 0, 3, 6),
                    new Cell(1, 1, 0, 1, 3, 7),
            },
            {   new Cell(0, 0, 1, 1, 4, 0),
                    new Cell(1, 0, 1, 0, 4, 1),
                    new Cell(1, 1, 1, 0, 4, 2),
                    new Cell(0, 0, 1, 1, 4, 3),
                    new Cell(1, 1, 1, 0, 4, 4),
                    new Cell(0, 0, 0, 1, 4, 5),
                    new Cell(0, 1, 0, 0, 4, 6),
                    new Cell(0, 1, 1, 1, 4, 7),
            },
            {   new Cell(1, 0, 1, 1, 5, 0),
                    new Cell(1, 0, 1, 0, 5, 1),
                    new Cell(1, 0, 1, 0, 5, 2),
                    new Cell(1, 0, 1, 0, 5, 3),
                    new Cell(1, 0, 1, 0, 5, 4),
                    new Cell(0, 1, 1, 0, 5, 5),
                    new Cell(0, 0, 1, 1, 5, 6),
                    new Cell(1, 1, 1, 0, 5, 7),
            }
    };
    Color maze1col = new Color(194, 129, 189);
    Color maze2col = new Color(121, 145, 201);
    Color maze3col = new Color(161, 110, 243);
    public Color wallsColor;

    /**
     * Створює новий об'єкт лабіринту згідно з вказаним рівнем.
     * @param level рівень лабіринту
     */
    public Maze(int level) {
        switch (level) {
            case 1:
                maze = mazeLv1;
                wallsColor = maze1col;
                break;
            case 2:
                maze = mazeLv2;
                wallsColor = maze2col;
                break;
            case 3:
                maze = mazeLv3;
                wallsColor = maze3col;
                break;
        }
    }

    public class Cell {
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

        public int getY() {
            return y;
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

        /**
         * Створює новий об'єкт клітинки лабіринту з вказаними параметрами.
         *
         * @param t    верхня стінка клітинки (1, якщо є, 0 якщо немає)
         * @param r    права стінка клітинки (1, якщо є, 0 якщо немає)
         * @param b    нижня стінка клітинки (1, якщо є, 0 якщо немає)
         * @param l    ліва стінка клітинки (1, якщо є, 0 якщо немає)
         * @param row  рядок клітинки
         * @param col  стовпець клітинки
         */
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
    }

}
