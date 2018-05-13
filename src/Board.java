import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Board extends JPanel
        implements Runnable {

    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int INITIAL_X = 250;
    private final int INITIAL_Y = 250;
    //Lower delay gives higher FPS, but this changes exponentially as you go lower
    //15 Delay means 60 fps
    private final int DELAY = 15;

    private Thread animator;
    private int x, y;
    private Player player;
    String fps;
    private FPScounter fpsCounter;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        x = INITIAL_X;
        y = INITIAL_Y;
        player = new Player(x, y);
        setupKeyBindings();
    }

    private void setupKeyBindings() {
        //get the "focus is in the window" input map for the center panel
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        int[] keys = {KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
        for (int k : keys) {
            getInputMap(mapName).put(KeyStroke.getKeyStroke(k, 0, false),
                    "" + k + " Press");
            getActionMap().put("" + k + " Press",
                    new KeyBoardControl(player, k, true));

            getInputMap(mapName).put(KeyStroke.getKeyStroke(k, 0, true),
                    "" + k + " Release");
            getActionMap().put("" + k + " Release",
                    new KeyBoardControl(player, k, false));
        }
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        fpsCounter = new FPScounter();
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawKeyPress(g);
        drawPlayer(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawKeyPress(Graphics g) {
        g.setColor(Color.WHITE);
        if (fps != null) {
            g.drawString(fps, 400, 70);
        }
        g.drawString("RIGHT: " + player.isRight(), 400, 110);
        g.drawString("LEFT: " + player.isLeft(), 400, 130);
        g.drawString("UP: " + player.isUp(), 400, 150);
        g.drawString("DOWN: " + player.isDown(), 400, 170);
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(player.getSprite(), player.getX(), player.getY(), this);
    }

    private void cycle() {

        player.move();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            fpsCounter.StartCounter();
            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
            String newFPS = fpsCounter.StopAndPost();
            if (!newFPS.equals("wait")) {
                fps = newFPS;
            }

        }
    }
}