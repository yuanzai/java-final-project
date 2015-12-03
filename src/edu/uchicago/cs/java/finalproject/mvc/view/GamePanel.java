package edu.uchicago.cs.java.finalproject.mvc.view;

import edu.uchicago.cs.java.finalproject.mvc.*;
import edu.uchicago.cs.java.finalproject.mvc.controller.Game;
import edu.uchicago.cs.java.finalproject.mvc.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class GamePanel extends Panel {
	
	// ==============================================================
	// FIELDS 
	// ============================================================== 
	 
	// The following "off" vars are used for the off-screen double-bufferred image. 
	private Dimension dimOff;
	private Image imgOff;
	private Graphics grpOff;
	
	private GameFrame gmf;
	private Font fnt;

	private FontMetrics fmt; 
	private int nFontWidth;
	private int nFontHeight;
	private BufferedImage cursorImg;
	private Cursor blankCursor;

    public int xSpriteOffset;
    public int xMapOffset;

	// ==============================================================
	// CONSTRUCTOR 
	// ==============================================================
	
	public GamePanel(Dimension dim){
	    gmf = new GameFrame();
        gmf.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        Game.DIM = screen;

        gmf.setUndecorated(true);
        gmf.getContentPane().add(this);
		gmf.pack();
		initView();
		
		//gmf.setSize(dim);


        gmf.setTitle("Game Base");
		gmf.setResizable(false);
		gmf.setVisible(true);
		this.setFocusable(true);

		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
        fnt = new Font("SansSerif", Font.PLAIN, 20);
	}


	// ==============================================================
	// METHODS 
	// ==============================================================
	
	private void drawScore(Graphics g) {
        for (int i = 0; i < Cc.getInstance().getPlayer().lives ; i++)
            HeartSVG.paint((Graphics2D) g, 20 + i* 45, 20);
        if (Cc.getInstance().getPlayer().currentWeapon == 1)
            GunSVG.paint((Graphics2D) grpOff, 20, 61, 50, Color.green);
        else
            GunSVG.paint((Graphics2D) grpOff, 20, 61, 50, Color.white);
        if (Cc.getInstance().getPlayer().currentWeapon == 2)
            LaserSVG.paint((Graphics2D) grpOff, 80, 61, 50, Color.green);
        else
            LaserSVG.paint((Graphics2D) grpOff, 80, 61, 50, Color.white);
        if (Cc.getInstance().getPlayer().currentWeapon == 3)
            ShotgunSVG.paint((Graphics2D) grpOff, 140, 61, 50, Color.green);
        else
            ShotgunSVG.paint((Graphics2D) grpOff, 140, 61, 50, Color.white);

        grpOff.setColor(Color.white);
        grpOff.drawString("Enemies left: " + Cc.getInstance().getEnemyCount(),20,135);

        if (Cc.getInstance().getPlayer().hitCounter>0) {
            Color myColour = new Color(200, Cc.getInstance().getPlayer().hitCounter / 2 * 5, Cc.getInstance().getPlayer().hitCounter / 2 * 5, 255 - Cc.getInstance().getPlayer().hitCounter / 2 * 50);
            g.setColor(myColour);
            g.fillRect(10, 10, 250, 45);
        }
	}
	
	@SuppressWarnings("unchecked")



    public void textWrapper(String s, int offset, Graphics grpOff) {
        grpOff.drawString(s,
                (Game.DIM.width - fmt.stringWidth(s)) / 2, Game.DIM.height / 4
                        + nFontHeight + offset);
    }

	public void update(Graphics g) {

        renderUpdate();


        if (Cc.getInstance().gameState == Cc.GameState.Start) {
            updateStart();
        }

        if (Cc.getInstance().gameState == Cc.GameState.Level) {
            updateLevel(Cc.getInstance().getLevel());
        }

        if (Cc.getInstance().gameState == Cc.GameState.Pause) {
            updatePause();
        }

        if (Cc.getInstance().gameState == Cc.GameState.GameOver) {
            updateGameOver(true);
        }

        if (Cc.getInstance().gameState == Cc.GameState.Died) {
            updateGameOver(false);
        }

        if (Cc.getInstance().gameState == Cc.GameState.InGame) {

            // draw lives
            drawScore(grpOff);

            // draw map
            drawMap(grpOff, Cc.getInstance().getMap(), xMapOffset);

            //draw them in decreasing level of importance
            //friends will be on top layer and debris on the bottom
            iterateMovables(grpOff, xSpriteOffset,
                    (ArrayList<Movable>) Cc.getInstance().getMovFriends(),
                    (ArrayList<Movable>) Cc.getInstance().getMovFoes(),
                    (ArrayList<Movable>) Cc.getInstance().getMovFloaters(),
                    (ArrayList<Movable>) Cc.getInstance().getMovFriendlyFire(),
                    (ArrayList<Movable>) Cc.getInstance().getMovEnemyFire(),
                    (ArrayList<Movable>) Cc.getInstance().getMovPlayers());

        }

		//draw the double-Buffered Image to the graphics context of the panel
		g.drawImage(imgOff, 0, 0, this);
        xSpriteOffset = 0;
	} 

    public void renderUpdate() {


        setCursor(blankCursor);
        if (grpOff == null || Game.DIM.width != dimOff.width
                || Game.DIM.height != dimOff.height) {
            dimOff = Game.DIM;
            imgOff = createImage(Game.DIM.width, Game.DIM.height);
            grpOff = imgOff.getGraphics();
        }
        grpOff.setFont(fnt);
        fmt = grpOff.getFontMetrics();
        nFontWidth = fmt.getMaxAdvance();
        nFontHeight = fmt.getHeight();
        // Fill in background with black.
        grpOff.setColor(Color.black);
        grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);
    }

    public void updateStart() {

        grpOff.setColor(Color.CYAN);
        textWrapper("PRESS SPACE TO START",0,grpOff);

        grpOff.setColor(Color.white);
        textWrapper("W A S D controls",40,grpOff);
        textWrapper("Left Mouse Click to fire",80,grpOff);
        textWrapper("Esc to Pause/Quit",120,grpOff);
        textWrapper("Kill all enemies to win",160,grpOff);
    }

    public void updateLevel( int i) {
        grpOff.setColor(Color.white);
        textWrapper("Level " + i,0,grpOff);
        textWrapper("Press space to begin", 100, grpOff);
    }


    public void updatePause() {

        grpOff.setColor(Color.white);
        textWrapper("Game Paused",0,grpOff);
        textWrapper("Esc to unpause",40,grpOff);
        textWrapper("Q to Quit",80,grpOff);

    }


    public void updateGameOver(boolean isWin) {
        grpOff.setColor(Color.white);
        if (isWin)
            textWrapper("So you won.",0,grpOff);
        else
            textWrapper("So you died.",0,grpOff);
        textWrapper("Space to restart",40,grpOff);
        textWrapper("Q to Quit",80,grpOff);
    }


    private void drawMap(Graphics g, Map map, int xOffset) {
		map.draw(g, xOffset,Game.DIM.height);
	}
	
	//for each movable array, process it.
	private void iterateMovables(Graphics g, int offset, ArrayList<Movable>...movMovz){
		
		for (ArrayList<Movable> movMovs : movMovz) {
			for (Movable mov : movMovs) {

				mov.move();
                mov.setCenter(new Point(mov.getCenter().x - offset, mov.getCenter().y));
				mov.draw(g);

			}
		}
		
	}
	
	private void initView() {
		Graphics g = getGraphics();			// get the graphics context for the panel
		g.setFont(fnt);						// take care of some simple font stuff
		fmt = g.getFontMetrics();
		nFontWidth = fmt.getMaxAdvance();
		nFontHeight = fmt.getHeight();

		//g.setFont(fntBig);					// set font info
	}



	public GameFrame getFrm() {return this.gmf;}
	public void setFrm(GameFrame frm) {this.gmf = frm;}	
}