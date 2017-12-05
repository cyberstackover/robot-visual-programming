package interfaces;

import visualprogrammer.Var;

import com.example.visualprogrammer.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver;
import android.view.ViewGroup.LayoutParams;
import android.widget.SeekBar;

public class SeekBars extends SeekBar{

	public SeekBars(Context context) {
		super(context);
	    super.setProgressDrawable(getResources().getDrawable(R.drawable.styled_progress));
	    ViewTreeObserver vto = super.getViewTreeObserver();
	    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
	        public boolean onPreDraw() {
	            Drawable thumb = getResources().getDrawable(R.drawable.thumb);
	            int h = (int) (SeekBars.this.getMeasuredHeight() * 1); // 8 * 1.5 = 12
	            int w = h;
	            Bitmap bmpOrg = ((BitmapDrawable)thumb).getBitmap();
	            Bitmap bmpScaled = Bitmap.createScaledBitmap(bmpOrg, w, h, true);
	            Drawable newThumb = new BitmapDrawable(getResources(), bmpScaled);
	            newThumb.setBounds(0, 0, newThumb.getIntrinsicWidth(), newThumb.getIntrinsicHeight());
	            SeekBars.this.setThumb(newThumb);
	            SeekBars.this.getViewTreeObserver().removeOnPreDrawListener(this);

	            return true;
	            }
	        });
	    super.setIndeterminate(false);
	    super.setMax(20);
	    LayoutParams sBarLayParams=new LayoutParams(Var.size.x/2, LayoutParams.WRAP_CONTENT);
	    //sBarLayParams.topMargin=10;
	    super.setPadding(20, 0, 20, 0);
	    super.setLayoutParams(sBarLayParams);

	}
	public void setWidth(int proportions){
		LayoutParams sBarLayParams=new LayoutParams(Var.size.x/proportions, LayoutParams.WRAP_CONTENT);
		super.setLayoutParams(sBarLayParams);
	}
	
}
