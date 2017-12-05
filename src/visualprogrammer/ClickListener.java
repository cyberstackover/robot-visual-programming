package visualprogrammer;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;

public class ClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		
		if(v.getId()==Var.HOME_ID){
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	            	BuildUiActivity.menuBar.setVisibility(View.GONE);
	            	BuildUiActivity.modulParent.setVisibility(View.GONE);
	            	BuildUiActivity.homeParent.setVisibility(View.VISIBLE);
	            }
	        }, 100L);
		}
		else if(v.getId()==Var.MODUL_ID){
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	            	BuildUiActivity.menuBar.setVisibility(View.GONE);
	            	BuildUiActivity.homeParent.setVisibility(View.GONE);
	            	BuildUiActivity.modulParent.setVisibility(View.VISIBLE);
	            }
	        }, 100L);
		}
		else if(v.getId()==Var.MENU_ID){
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	            	BuildUiActivity.modulParent.setVisibility(View.GONE);
	            	BuildUiActivity.homeParent.setVisibility(View.GONE);
	            	BuildUiActivity.menuBar.setVisibility(View.VISIBLE);
	            }
	        }, 100L);
		}
		else if(v.getId()==Var.EXIT_ID){}
		else if(v.getId()==Var.LEFT1_ID){
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	            	BuildUiActivity.scrollModule.fullScroll(HorizontalScrollView.FOCUS_LEFT);
	            }
	        }, 100L);
		}
		else if(v.getId()==Var.RIGHT1_ID){
			Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	            	BuildUiActivity.scrollModule.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
	            }
	        }, 100L);
		}
	}

}
