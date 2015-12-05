package edu.uchicago.cs.java.finalproject.mvc.controller;

import edu.uchicago.cs.java.finalproject.mvc.model.Cc;
import edu.uchicago.cs.java.finalproject.mvc.model.CollisionOp;
import edu.uchicago.cs.java.finalproject.mvc.model.Player;
import edu.uchicago.cs.java.finalproject.mvc.model.Projectile;
import edu.uchicago.cs.java.finalproject.mvc.view.GamePanel;
import edu.uchicago.cs.java.finalproject.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Junyuan on 11/27/2015.
 */
public class ActionListener implements KeyListener, MouseListener, MouseMotionListener {
    private final int PAUSE = 27, // esc key
            QUIT = 81, // q key
            LEFT = 65, // a
            RIGHT = 68, // d
            UP = 87, //w
            DOWN = 83, // s
            START = 32, // space
            MUTE = 77, // m-key mute
            ONE = 49,
            TWO = 50,
            THREE = 51;
            //SPECIAL = 70; 					// fire special weapon;  F key


    private Game game;
    private GamePanel gmpPanel;

    public ActionListener(Game game){
        this.game = game;
        this.gmpPanel = game.getGmpPanel();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case START:
                if (Cc.getInstance().gameState == Cc.GameState.Start ||
                        Cc.getInstance().gameState == Cc.GameState.GameOver ||
                        Cc.getInstance().gameState == Cc.GameState.Died ||
                        Cc.getInstance().gameState == Cc.GameState.Level) {
                    game.bStart = true;
                }
                break;
            case PAUSE:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().gameState = Cc.GameState.Pause;
                } else if (Cc.getInstance().gameState == Cc.GameState.Pause) {
                    Cc.getInstance().gameState = Cc.GameState.InGame;
                }
                break;
            case QUIT:
                if (Cc.getInstance().gameState == Cc.GameState.Pause||
                        Cc.getInstance().gameState == Cc.GameState.GameOver ||
                        Cc.getInstance().gameState == Cc.GameState.Died)
                    System.exit(0);
                break;
            case UP:
                if (Cc.getInstance().gameState == Cc.GameState.InGame)
                    Cc.getInstance().getPlayer().pressUp();
                break;
            case DOWN:
                if (Cc.getInstance().gameState == Cc.GameState.InGame)
                    Cc.getInstance().getPlayer().pressDown();
                break;
            case LEFT:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().pressLeft();
                    game.bLeft = true;
                }
                break;
            case RIGHT:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().pressRight();
                    game.bRight = true;
                }
                break;
            case ONE:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().currentWeapon =1;
                }
                break;
            case TWO:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().currentWeapon =2;
                }
                break;
            case THREE:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().currentWeapon =3;
                }
                break;

            default:
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case LEFT:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().releaseLeft();
                    game.bLeft = false;
                }
                break;
            case RIGHT:
                if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                    Cc.getInstance().getPlayer().releaseRight();
                    game.bRight = false;
                }
                break;
            case UP:
                if (Cc.getInstance().gameState == Cc.GameState.InGame)
                    Cc.getInstance().getPlayer().releaseUp();
                break;
            case DOWN:
                if (Cc.getInstance().gameState == Cc.GameState.InGame)
                    Cc.getInstance().getPlayer().releaseDown();
                break;
            case MUTE:
                if (Sound.isMuted)
                    game.sound.play();
                else
                    game.sound.close();
                Sound.isMuted = !Sound.isMuted;
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Cc.getInstance().gameState == Cc.GameState.InGame || Cc.getInstance().getPlayer() != null)
            Cc.getInstance().getPlayer().bPressed = true;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (Cc.getInstance().gameState == Cc.GameState.InGame && Cc.getInstance().getPlayer() != null)
            Cc.getInstance().getPlayer().bPressed = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Cc.getInstance().gameState == Cc.GameState.InGame && Cc.getInstance().getPlayer() != null)
            Cc.getInstance().getPlayer().pntMousePoint = e.getPoint();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if (Cc.getInstance().gameState == Cc.GameState.InGame && Cc.getInstance().getPlayer() != null)
            Cc.getInstance().getPlayer().pntMousePoint = e.getPoint();
    }
}
