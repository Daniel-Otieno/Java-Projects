import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimplePacmanGame extends JPanel implements ActionListener, KeyListener {
    private static final int TILE_SIZE = 20;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 15;
    
    private int pacmanX = 1, pacmanY = 1;
    private int score = 0;
    
    private final boolean[][] dots;

    private Timer timer;

    public SimplePacmanGame() {
        setPreferredSize(new Dimension(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        dots = new boolean[GRID_WIDTH][GRID_HEIGHT];
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                dots[x][y] = true;  // All tiles start with dots
            }
        }

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw dots
        g.setColor(Color.WHITE);
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if (dots[x][y]) {
                    g.fillOval(x * TILE_SIZE + TILE_SIZE / 4, y * TILE_SIZE + TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2);
                }
            }
        }

        // Draw Pac-Man
        g.setColor(Color.YELLOW);
        g.fillArc(pacmanX * TILE_SIZE, pacmanY * TILE_SIZE, TILE_SIZE, TILE_SIZE, 30, 300);
        
        // Display score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int newX = pacmanX, newY = pacmanY;

        if (e.getKeyCode() == KeyEvent.VK_UP) newY--;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) newY++;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) newX--;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) newX++;

        if (newX >= 0 && newX < GRID_WIDTH && newY >= 0 && newY < GRID_HEIGHT) {
            pacmanX = newX;
            pacmanY = newY;

            if (dots[pacmanX][pacmanY]) {
                dots[pacmanX][pacmanY] = false;
                score += 10;
            }
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Pac-Man");
        SimplePacmanGame game = new SimplePacmanGame();
        
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
