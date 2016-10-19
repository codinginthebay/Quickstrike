package codinginthebay.quickstrike;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Julian on 10/16/2016.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    public static final int WIDTH = 480;
    public static final int HEIGHT = 318;

    private MainThread thread;
    private Background bg;

    private Player player;

    private Enemy enemy;

    private Random rand = new Random();

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.placeholderbg));

        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.placeholderplayer), 66, 51, 1);

        enemy = new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.placeholderenemy), 79, 79, 1);

        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        int counter = 0;
        while(retry){
            try{
                counter++;
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            player.setUp(true);
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            player.setUp(false);
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update(){
//        if(player.getPlaying()){
            bg.update();
            player.update();
            enemy.update();

//        }

    }

    public boolean collision(GameObject a, GameObject b){
        if(Rect.intersects(a.getRectangle(), b.getRectangle())){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas){
        final float scaleFactorX = getWidth()/(WIDTH*1f);
        final float scaleFactorY = getHeight()/(HEIGHT*1f);

        if(canvas != null){
            final int savedState = canvas.save();

            canvas.scale(scaleFactorX,scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            enemy.draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }
}
