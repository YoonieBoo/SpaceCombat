import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class ResultPanel {
    
    boolean[] activated = new boolean[2];
    Point[] point = new Point[2];
    BufferedImage image;
    File imageF = new File("button.jpg");
    int button_w = 150;
    int button_h = 50;
    GamePanel game;
    Rectangle[] rectangle = new Rectangle[2];
    boolean clicked;
    boolean won = false;
    boolean painted = false;
    Random random;
    Integer score;
    Integer highScore;
    LocalStorage localStorage = new LocalStorage("data.properties");

    public ResultPanel(GamePanel game) {
        for (int i = 0; i < 2; i++) {
            activated[i] = false;
        }
        for (int i = 0; i < 2; i++) {
            point[i] = new Point(465, 200 + 81*i);
            rectangle[i] = new Rectangle(point[i], new Dimension(button_w,
                    button_h));
        }

        try {
            image = ImageIO.read(imageF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.game = game;

        random = new Random(10000);
        //score = ;
        //highScore = localStorage.loadData("Score");
    }

    // Method to load the high score from a file
    private int loadHighScore() {
        int highScore = 0;
        try {
            File file = new File("highscore.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                highScore = Integer.parseInt(reader.readLine());
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }

    public void draw(Graphics2D g2d,Integer currentScore) {
        g2d.drawImage(game.backGround, 0, 0, null);
        g2d.drawImage(game.opacity, 0, 0, null);
        
        if (won) {
            g2d.setColor(Color.GREEN);
            g2d.setFont(game.font);
            g2d.drawString("You Have WON!", 425, 100);
            g2d.setColor(Color.WHITE);
            g2d.setFont(game.font);
            
            //if (!painted) score = random.nextInt(10000);  // Random score assignment for demo
            g2d.drawString("Score: " + currentScore,425, 140);  // Draw the current score
            
            // Draw the high score
            g2d.drawString("High Score: " + localStorage.loadData("Score"), 425, 180); // X and Y coordinates
            
        } else {
            g2d.setColor(Color.RED);
            g2d.setFont(game.font);
            g2d.drawString("You Have LOST!", 425, 100);
            g2d.setColor(Color.WHITE);
            g2d.setFont(game.font);
            g2d.drawString("Score: " + Integer.toString(0), 500, 135);  // Lost game score display
        }

        for (int i = 0; i < 2; i++) {
            if (activated[i]) {
                g2d.drawImage(image, point[i].x, point[i].y, null);
            }
        }

        g2d.setColor(Color.white);
        g2d.drawString("Menu", 500, 235);
        g2d.drawString("Exit", 500, 316);
    }

    public Rectangle getBounds(int i) {
        return rectangle[i];
    }
}
