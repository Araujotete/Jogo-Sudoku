import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku {
    private Board board;
    private JFrame frame;
    private JTextField[][] textFields;

    public Sudoku() {
        board = new Board();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout()); // Mantido BorderLayout

        JPanel gridPanel = new JPanel(new GridLayout(4, 4)); // Alterado para 4x4
        textFields = new JTextField[4][4]; // Alterado para 4x4

        for (int i = 0; i < 4; i++) { // Alterado para 4
            for (int j = 0; j < 4; j++) { // Alterado para 4
                textFields[i][j] = new JTextField();
                textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                if (board.getGrid()[i][j].isFixed()) {
                    textFields[i][j].setEditable(false);
                    textFields[i][j].setText(String.valueOf(board.getGrid()[i][j].getValue()));
                }
                final int row = i;
                final int col = j;
                textFields[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int value = Integer.parseInt(textFields[row][col].getText());
                            board.setCell(row, col, value);
                        } catch (NumberFormatException ex) {
                            textFields[row][col].setText("");
                        }
                    }
                });
                gridPanel.add(textFields[i][j]);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER); // Adiciona o grid ao centro

        JButton solveButton = new JButton("Resolver");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para resolver o Sudoku
                // Exibir solução no tabuleiro
            }
        });
        frame.add(solveButton, BorderLayout.SOUTH); // Adiciona o botão ao sul
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Sudoku();
        });
    }
}