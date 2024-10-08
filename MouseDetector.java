

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MouseDetector extends MouseAdapter implements MouseMotionListener {

    int x = 0;
    int y = 0;
    Point pClicked = new Point(0, 0);
    Point pMoved = new Point(0, 0);
    GamePanel game;

    public MouseDetector(GamePanel game) {
        game.addMouseMotionListener(this);
        game.addMouseListener(this);
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pMoved = e.getPoint();
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!game.play) {
            if (SwingUtilities.isLeftMouseButton(e))
                pClicked = e.getPoint();
        } else {
            if (SwingUtilities.isLeftMouseButton(e)) {
                game.addBullet(1);
                bulletSound();
            }
//            if (SwingUtilities.isRightMouseButton(e))
//                game.addBullet(2);
        }
    }

    // Updated audio method
    public void bulletSound() {
        try {
            File soundFile = new File("fire.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}