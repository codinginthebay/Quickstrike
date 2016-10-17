package codinginthebay.quickstrike;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Julian on 10/16/2016.
 */
public class Background {
    private Bitmap image;
    private int x, y;

    public Background(Bitmap res){
        image = res;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}
