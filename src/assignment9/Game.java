package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private int score;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        score = 0;
    }

    public void play() {
        while (snake.isInbounds()) {
            int dir = getKeypress();
            if (dir > 0) {
                snake.changeDirection(dir);
            }
            snake.move();
            if (snake.eatFood(food)) {
                score++;
                food = new Food();
            }
            updateDrawing();
        }
        // Game Over screen
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.5, 0.6, "Game Over!");
        StdDraw.text(0.5, 0.4, "Score: " + score);
        StdDraw.show();
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();
        // Draw score in top-left corner
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.textLeft(0.02, 0.98, "Score: " + score);
        StdDraw.pause(90);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
