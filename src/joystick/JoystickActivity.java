package joystick;

import visualprogrammer.BuildUiActivity;
import visualprogrammer.TouchListener;
import visualprogrammer.Var;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.visualprogrammer.R;

import dragmanagements.DragController;

public class JoystickActivity extends Activity{
	
	ImageView up,left,right,down,upper,lefter,righter,downer;
	public static boolean upenable=false,
			downenable=false,
			leftenable=false,
			rightenable=false,
			upperenable=false,
			downerenable=false,
			lefterenable=false,
			righterenable=false;
	
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.joystick_layout);
		
		up=(ImageView) findViewById(R.id.joystickup);
		down=(ImageView) findViewById(R.id.joystickdown);
		left=(ImageView) findViewById(R.id.joystickleft);
		right=(ImageView) findViewById(R.id.joystickright);
		
		up.setAlpha((float)0);
		down.setAlpha((float)0);
		left.setAlpha((float)0);
		right.setAlpha((float)0);
		
		upper=(ImageView) findViewById(R.id.joystickupper);
		downer=(ImageView) findViewById(R.id.joystickdowner);
		lefter=(ImageView) findViewById(R.id.joysticklefter);
		righter=(ImageView) findViewById(R.id.joystickrighter);
		
		upper.setAlpha((float)0);
		downer.setAlpha((float)0);
		lefter.setAlpha((float)0);
		righter.setAlpha((float)0);
		
		up.setOnClickListener(new click());
		right.setOnClickListener(new click());
		left.setOnClickListener(new click());
		down.setOnClickListener(new click());
		
		upper.setOnClickListener(new click());
		righter.setOnClickListener(new click());
		lefter.setOnClickListener(new click());
		downer.setOnClickListener(new click());
		
		up.setOnTouchListener(new touch());
		down.setOnTouchListener(new touch());
		left.setOnTouchListener(new touch());
		right.setOnTouchListener(new touch());
		
		upper.setOnTouchListener(new touch());
		downer.setOnTouchListener(new touch());
		lefter.setOnTouchListener(new touch());
		righter.setOnTouchListener(new touch());
		
		for(int i=0; i<Var.activeBlocks.size(); i++){
			
			if(Var.activeBlocks.get(i).getId()==Var.FORWARD_ID){
				upenable=true;
				up.setAlpha((float)1);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.REVERSE_ID) {
		    	downenable=true;
		    	down.setAlpha((float)1);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.TLEFT_ID){
		    	left.setAlpha((float)1);
		    	leftenable=true;
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.TRIGHT_ID) {
		    	right.setAlpha((float)1);
		    	rightenable=true;
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.LCD_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.lcdteks3);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.DELAY_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.waktu);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.SOUND_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.suara);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.GRIPPER_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.gripper);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.LOOP_ID) {
		    	
		    	//Var.activeBlocks.get(i).setImageResource(R.drawable.loop_open);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.LFOLLOWER_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.linefollower);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.WFOLLOWER_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.wallfollowers);
		    }
			DragController.hideSoftKey();
		}

		
	}
	
	private class click implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.joystickup){}
			if(v.getId()==R.id.joystickdown){}
			if(v.getId()==R.id.joystickleft){}
			if(v.getId()==R.id.joystickright){}
			
			if(v.getId()==R.id.joystickupper){}
			if(v.getId()==R.id.joystickdowner){}
			if(v.getId()==R.id.joysticklefter){}
			if(v.getId()==R.id.joystickrighter){}
		}
		
	}
	
	private class touch implements OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v.getId()==R.id.joystickup && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.upenable==true)up.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickup && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.upenable==true)up.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joystickdown && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.downenable==true)down.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickdown && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.downenable==true)down.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joystickleft && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.leftenable==true)left.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickleft && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.leftenable==true)left.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joystickright&& event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.rightenable==true)right.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickright&& event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.rightenable==true)right.setAlpha((float)1);
			}
			
			
			if (v.getId()==R.id.joystickupper && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.upperenable==true)upper.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickupper && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.upperenable==true)upper.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joystickdowner && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.downenable==true)downer.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickdowner && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.downenable==true)downer.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joysticklefter && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.lefterenable==true)lefter.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joysticklefter && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.lefterenable==true)lefter.setAlpha((float)1);
			}
			
			if (v.getId()==R.id.joystickrighter && event.getAction() == MotionEvent.ACTION_DOWN) {
				if(JoystickActivity.righterenable==true)righter.setAlpha((float)0.5);
			}
			else if (v.getId()==R.id.joystickrighter && event.getAction() == MotionEvent.ACTION_UP) {
				if(JoystickActivity.righterenable==true)righter.setAlpha((float)1);
			}
			
			return false;
		}	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        upenable=false;
	    	downenable=false;
	    	leftenable=false;
	    	rightenable=false;
	    	upperenable=false;
	    	downerenable=false;
	    	lefterenable=false;
	    	righterenable=false;
	        finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
}
