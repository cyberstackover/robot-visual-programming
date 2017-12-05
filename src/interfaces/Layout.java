package interfaces;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

public class Layout extends LinearLayout{
	
	public Layout(Context context,int orientation,int gravity, int width, int height){
		
		super(context);
		super.setLayoutParams(new LayoutParams(width,height));
		super.setGravity(gravity);
		super.setOrientation(orientation);
		super.setBackgroundColor(Color.rgb(139, 206,255));
    
	}	
}
