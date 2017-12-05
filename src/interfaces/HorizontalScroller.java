package interfaces;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

public class HorizontalScroller extends HorizontalScrollView{

	public HorizontalScroller(Context context, int width) {
		super(context);
        super.setBackgroundColor(Color.rgb(139, 206,255));
        super.setHorizontalScrollBarEnabled(false);
        super.setLayoutParams(new LayoutParams(width, LayoutParams.WRAP_CONTENT));
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	
}
