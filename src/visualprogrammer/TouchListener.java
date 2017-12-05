package visualprogrammer;

import joystick.JoystickActivity;
import webservice.SyncActivity;

import com.example.visualprogrammer.R;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchListener implements OnTouchListener{

	@Override
	public boolean onTouch(View v, MotionEvent arg1) {
		if (v.getId()==Var.NEW_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.newfile.setImageResource(R.drawable.ic_new_file_pressed);
	    } 
		else if (v.getId()==Var.NEW_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.newfile.setImageResource(R.drawable.ic_new_file);
	    }
		if (v.getId()==Var.OPEN_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.openfile.setImageResource(R.drawable.ic_open_file_pressed);
	    } 
		else if (v.getId()==Var.OPEN_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.openfile.setImageResource(R.drawable.ic_open_file);
	    }
		if (v.getId()==Var.SAVE_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.save.setImageResource(R.drawable.ic_save_file_pressed);
	    } 
		else if (v.getId()==Var.SAVE_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.save.setImageResource(R.drawable.ic_save_file);
	    }
		if (v.getId()==Var.DELETE_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.delete.setImageResource(R.drawable.ic_delete_file_pressed);
	    } 
		else if (v.getId()==Var.DELETE_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.delete.setImageResource(R.drawable.ic_delete_file);
	    }
		if (v.getId()==Var.SYNC_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.sync.setImageResource(R.drawable.ic_sync_pressed);
	    } 
		else if (v.getId()==Var.SYNC_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.sync.setImageResource(R.drawable.ic_sync);
	    }
		if (v.getId()==Var.INFO_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.info.setImageResource(R.drawable.ic_view_code_pressed);
	    } 
		else if (v.getId()==Var.INFO_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.info.setImageResource(R.drawable.ic_view_code);
	    }
		if (v.getId()==Var.CONNECT_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.connectifity.setImageResource(R.drawable.ic_bluetooth_pressed);
	    } 
		else if (v.getId()==Var.CONNECT_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.connectifity.setImageResource(R.drawable.ic_bluetooth);
	    }
		if (v.getId()==Var.DEPLOY_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.deploy.setImageResource(R.drawable.ic_deploy_pressed);
	    } 
		else if (v.getId()==Var.DEPLOY_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.deploy.setImageResource(R.drawable.ic_deploy);
	    }
		/////////////////////////////////////////////////////////////////////////////////////////
		if (v.getId()==Var.HOME_ID&& arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.home.setImageResource(R.drawable.ic_home_highlights);
			BuildUiActivity.blockno.setText("");
			BuildUiActivity.bottom_properties.removeAllViews();
			BuildUiActivity.bottom_properties1.removeAllViews();
	    } 
		else if (v.getId()==Var.HOME_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.home.setImageResource(R.drawable.ic_home);
	    }
		if (v.getId()==Var.MENU_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.menu.setImageResource(R.drawable.ic_menu_highlights);
	    } 
		else if (v.getId()==Var.MENU_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.menu.setImageResource(R.drawable.ic_menu);
	    }
		if (v.getId()==Var.MODUL_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.modul.setImageResource(R.drawable.ic_block_highlights);
	    } 
		else if (v.getId()==Var.MODUL_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.modul.setImageResource(R.drawable.ic_block);
	    }
		if (v.getId()==Var.ABOUT_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.about.setImageResource(R.drawable.ic_pad_pressed);
	    } 
		else if (v.getId()==Var.ABOUT_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.about.setImageResource(R.drawable.ic_pad);
	    }
		if (v.getId()==Var.EXIT_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.exit.setImageResource(R.drawable.exit1);
	    } 
		else if (v.getId()==Var.EXIT_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.exit.setImageResource(R.drawable.exit);
	    }
		/////////////////////////////////////////////////////////////////////////////////////
		if (v.getId()==R.id.download && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			SyncActivity.download.setImageResource(R.drawable.ic_download_file_pressed);
	    } 
		else if (v.getId()==R.id.download && arg1.getAction() == MotionEvent.ACTION_UP) {
			SyncActivity.download.setImageResource(R.drawable.ic_download_file);
	    }
		if (v.getId()==R.id.upload && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			SyncActivity.upload.setImageResource(R.drawable.ic_upload_file_pressed);
	    } 
		else if (v.getId()==R.id.upload && arg1.getAction() == MotionEvent.ACTION_UP) {
			SyncActivity.upload.setImageResource(R.drawable.ic_upload_file);
	    }
		/////////////////////////////////////////////////////////////////////////////////////
		if (v.getId()==Var.LEFT1_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.left1.setImageResource(R.drawable.left1);
	    } 
		else if (v.getId()==Var.LEFT1_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.left1.setImageResource(R.drawable.left);
	    }
		if (v.getId()==Var.RIGHT1_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.right1.setImageResource(R.drawable.right1);
	    } 
		else if (v.getId()==Var.RIGHT1_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.right1.setImageResource(R.drawable.right);
	    }
		///////////////////////////////////////////////////////////////////////////////////////
		
		if (v.getId()==Var.FORWARD_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.forward.setImageResource(R.drawable.maju2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.FORWARD_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.forward.setImageResource(R.drawable.maju1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.REVERSE_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.reverse.setImageResource(R.drawable.mundur2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.REVERSE_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.reverse.setImageResource(R.drawable.mundur1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.TLEFT_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.turnleft.setImageResource(R.drawable.belki2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.TLEFT_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.turnleft.setImageResource(R.drawable.belki1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.TRIGHT_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.turnright.setImageResource(R.drawable.belka2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.TRIGHT_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.turnright.setImageResource(R.drawable.belka1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.LCD_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.lcd.setImageResource(R.drawable.lcdteks1);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.LCD_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.lcd.setImageResource(R.drawable.lcdteks);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.DELAY_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.delay.setImageResource(R.drawable.waktu2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.DELAY_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.delay.setImageResource(R.drawable.waktu1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.SOUND_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.sound.setImageResource(R.drawable.suara2);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.SOUND_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.sound.setImageResource(R.drawable.suara1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		if (v.getId()==Var.GRIPPER_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.gripper.setImageResource(R.drawable.gripper1);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.GRIPPER_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.gripper.setImageResource(R.drawable.gripper_pressed);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		
		if (v.getId()==Var.LFOLLOWER_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.lfollower.setImageResource(R.drawable.linefollower_pressed);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.LFOLLOWER_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.lfollower.setImageResource(R.drawable.linefollower1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		
		if (v.getId()==Var.LOOP_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.loop.setImageResource(R.drawable.loop_pressed);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.LOOP_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.loop.setImageResource(R.drawable.loop1);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		
		if (v.getId()==Var.WFOLLOWER_ID && arg1.getAction() == MotionEvent.ACTION_DOWN) {
			BuildUiActivity.wfollower.setImageResource(R.drawable.wallfollower_pressed);
			v.getParent().requestDisallowInterceptTouchEvent(true);
	    } 
		else if (v.getId()==Var.WFOLLOWER_ID && arg1.getAction() == MotionEvent.ACTION_UP) {
			BuildUiActivity.wfollower.setImageResource(R.drawable.wallfollower);
			v.getParent().requestDisallowInterceptTouchEvent(false);
	    }
		/////////////////////////////////////////////////////////////////////////////////
		return false;
	}

}
