package codinginthebay.quickstrike;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Julian on 10/17/2016.
 */

public class Enemy extends GameObject {
    private boolean up;
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    private long startTime;

    public Enemy(Bitmap res, int w, int h, int numFrames){
        x = 350;
        y = GamePanel.HEIGHT/2;
        dx = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b){
        up = b;
    }

    public void update(){
        long elapsed = (System.nanoTime()-startTime)/1000000;
        animation.update();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public void resetDX(){dx=0;}
}
