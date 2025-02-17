import java.util.Random;

public class Board {
    private Cell[][] grid;
    private static final int SIZE = 9;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        generateBoard();
    }

    private void generateBoard() {
        // Preencher o tabuleiro com zeros
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell(0, false);
            }
        }
        // Gerar um tabuleiro válido
        fillBoard();
        // Remover algumas células para criar o quebra-cabeça
        removeCells();
    }

    private boolean fillBoard() {
        // Algoritmo de backtracking para preencher o tabuleiro
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col].getValue() == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidMove(row, col, num)) {
                            grid[row][col].setValue(num);
                            if (fillBoard()) {
                                return true;
                            }
                            grid[row][col].setValue(0); // Backtrack
                        }
                    }
                    return false; // Se nenhum número é válido
                }
            }
        }
        return true; // Tabuleiro preenchido
    }

    private void removeCells() {
        Random rand = new Random();
        int cellsToRemove = 36; // Número de células a serem removidas
        while (cellsToRemove > 0) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (grid[row][col].getValue() != 0) {
                grid[row][col].setValue(0);
                cellsToRemove--;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col, int value) {
        // Verifica linha
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i].getValue() == value) {
                return false;
            }
        }
        // Verifica coluna
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col].getValue() == value) {
                return false;
            }
        }
        // Verifica subgrade 3x3
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + boxRowStart][j + boxColStart].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean isCellFixed(int row, int col) {
        return grid[row][col].isFixed();
    }

    public void setCell(int row, int col, int value) {
        if (isValidMove(row, col, value)) {
            grid[row][col].setValue(value);
        }
    }
}