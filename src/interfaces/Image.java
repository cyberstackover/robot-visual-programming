package interfaces;


import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Image extends ImageView
{
	public int order;
	
    public Image(Context context){
    	super(context);
    }
	public Image(Context context,int id, int resource, int x, int y, int ml, int mr, int mt, int mb) {
		super(context);
        super.setImageResource(resource);
        super.setId(id);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(x,y);
        params.setMargins(ml,mr,mt,mb);
        super.setLayoutParams(params);
	}
	
	public Image(Context context,int id, int resource, int ml, int mr, int mt, int mb) {
		super(context);
        super.setImageResource(resource);
        super.setId(id);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        params.setMargins(ml,mr,mt,mb);
        super.setLayoutParams(params);
	}
}