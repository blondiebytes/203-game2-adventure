/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

public class StartEndGame extends World {
    
    private final WorldImage background;
    private final Score score;
    private final boolean isANewGame;
    
    public StartEndGame() {
        this.background = new FromFileImage(new Posn(0,0), "art/background-stars.jpg");
        this.score = new Score();
        this.isANewGame = true;
                
    }
    
    public StartEndGame(String str, Score score, boolean isANewGame) {
        this.background = new FromFileImage(new Posn(0, 0), str);
        this.score = score;
        this.isANewGame = isANewGame;
    }
    
    public World onTick() {
        // the world doesn't change onTick
        return this;
    }
    
    public boolean bigBang() {
        
            //this.theCanvas = new WorldCanvas(500, 500, "Meteor Shower");
            //this.theCanvas.show();
        if (isANewGame) {
            // If we are starting anew, then we want to create something
        return this.bigBang(500, 500, .01);
        } else 
            return false;
    }
    
    public World onKeyEvent(String key) {
         if (key.equals(" ")){
             return new MeteorShowerRM();
         }
         else {
             return this;
         }
    }
    
    public WorldImage makeImage() {
        WorldImage playText;
        if (isANewGame) {
            // edit this to make prettier too
            playText = new TextImage(new Posn(235, 200), "Press the" , 40, new White());
          //  space bar to start playing!
            playText = new OverlayImages(playText, new TextImage(new Posn(235, 250), "space bar to ", 40, new White()));
            playText = new OverlayImages(playText, new TextImage(new Posn(235, 300), "start playing!", 40, new White()));
        } else {
            WorldImage gameOverText = new OverlayImages(new TextImage(new Posn(235, 200), "Game Over!", 40, new White()),
                    new TextImage(new Posn(235, 250), "Score: " + score.score, 40, new White()));
           playText = new OverlayImages(gameOverText, new TextImage(new Posn(235, 300), "Press the space bar to play again!", 20, new White()) );
        }
        
         WorldImage finalImage = new OverlayImages(background, playText);
         return finalImage;
    }
    
    
}
