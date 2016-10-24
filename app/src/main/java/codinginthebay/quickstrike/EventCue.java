package codinginthebay.quickstrike;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Julian on 10/19/2016.
 */
public class EventCue extends GameObject {
    private boolean up;
    private Bitmap spritesheet;
    private Animation animation = new Animation();
    private long startTime;
    private long elapsed;
    private long timeCheck;
    public boolean timedEvent = false;

    public EventCue(Bitmap res, int w, int h, int numFrames){
        x = 200;
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
        elapsed = (System.nanoTime()-startTime)/1000000;
        animation.update();
        System.out.println("elapsed time: " + elapsed);
        timeCheck = (startTime)/100000000;  //sets when event happens
        System.out.println("time check: " + timeCheck);
        if(elapsed>=timeCheck){
            timedEvent=true;
        }
    }

    public void draw(Canvas canvas){
            canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public void resetDX(){dx=0;}
}
