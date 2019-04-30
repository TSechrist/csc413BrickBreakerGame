package dev.game.gameobject.moveableobject;

import dev.game.Handler;
import dev.game.gameobject.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Ball extends MoveableObject {

    private BufferedImage img;
    private int angle;
    private int dmgAmt = 25;
    private int SPEED = 9;

    public Ball(Handler handler, BufferedImage img, float x, float y, int angle, int height, int width) {
        super(handler, x + 32, y + 32, height, width);
        this.img = img;
        this.angle = angle;
    }

    @Override
    public void tick() {

        vx = (float) Math.round((SPEED) * Math.cos(Math.toRadians(angle)));
        vy = (float) Math.round((SPEED) * Math.sin(Math.toRadians(angle)));

        if(!checkObjectCollisions(vx, vy))
        {
            this.x += vx;
            this.y += vy;
        }

        if(this.checkObjectCollisions(vx, vy)){

            if(getObjectCollide(vx, vy) instanceof BreakableWall)
            {
                getObjectCollide(vx, vy).damage(dmgAmt);
                if(this.angle == 135)
                {
                    this.angle = 45;
                }
                else if(this.angle == 45)
                {
                    this.angle = 135;
                }
                else if(this.angle == 225)
                {
                    this.angle = 315;
                }
                else
                {
                    this.angle = 225;
                }

            }
            else if (getObjectCollide(vx, vy) instanceof Tank)
            {
                if(this.angle == 135)
                {
                    this.angle = 225;
                }
                else
                {
                    this.angle = 315;
                }
            }
            else if (getObjectCollide(vx, vy) instanceof SolidWall)
            {
                if(this.angle == 315)
                {
                    this.angle = 45;
                }
                else
                {
                    this.angle = 135;
                }
            }
//            else if (getObjectCollide(vx, vy) instanceof SideWall)
//            {
//                if(this.angle == 135)
//                {
//                    this.angle = 45;
//                }
//                else if(this.angle == 45)
//                {
//                    this.angle = 135;
//                }
//                else if(this.angle == 225)
//                {
//                    this.angle = 315;
//                }
//                else
//                {
//                    this.angle = 225;
//                }
//            }

            else if(getObjectCollide(vx, vy) instanceof PowerUp || getObjectCollide(vx, vy) instanceof Ball)
            {
                this.x += vx;
                this.y += vy;
            }
        }
    }

    @Override
    public void die(){


    }

    @Override
    public void render(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(this.x - handler.getCamera().getxOff(), this.y - handler.getCamera().getyOff());
        rotation.rotate(Math.toRadians(this.angle), img.getWidth() / 2.0, img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, rotation, null);
    }


}