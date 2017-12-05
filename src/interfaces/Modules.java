package interfaces;

import visualprogrammer.BuildUiActivity;
import visualprogrammer.Var;

import com.example.visualprogrammer.R;

import dragmanagements.DragController;
import dragmanagements.DragSource;
import dragmanagements.DragView;
import dragmanagements.DropTarget;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Modules extends ImageView implements DragSource, DropTarget
{
	public boolean mEmpty = true;
    public int order;
    
	Spinners spinner;
	Spinners spinner1 ;
	 
    public Texts type,limit;
    public EditText values;
    
    private String dataSave="";
    public String tipeData="";
    public static String temptipeData="";
    
    public String blockHeader="ff";
    
    public String looping="ulang";
    public String lvalue="3";
    public static String templvalue;
    
    public String fwd1="maju_waktu";
    public String fwd2="maju_jarak";
    public String fvalue="10", fspeed="10";
    public static String tempfvalue="", tempfspeed="";
    
    public String reverse1="mundur_waktu";  
    public String reverse2="mundur_jarak"; 
    public String rvalue="10", rspeed="10";
    public static String temprvalue="", temprspeed="";
    
    public String tleft1="belok_kiri_waktu";
    public String tleft2="belok_kiri_derajat";
    public String tlvalue="90", tlspeed="10";
    public static String temptlvalue="", temptlspeed="";
    
    public String tright1="belok_kanan_waktu";
    public String tright2="belok_kanan_derajat";
    public String trvalue="90", trspeed="10";
    public static String temptrvalue="", temptrspeed="";
    
    public String lcd="lcd",lcdChar="Hello World";
    public static String templcdChar="";
    
    public String sMonostable="buzzer_nyala";
    public String monosOn="3";
    public static String tempmonosOn="";
    public String sAstable="buzzer_nyala_mati";
    public String sMario="buzzer_mario";
    public String astaOn="1",astaOff="1",astaLoop="4";
    public static String tempastaOn="",tempastaOff="",tempastaLoop="";
    
    public String delay="delay",dvalue="3"; //jeda waktu
    public static String tempdvalue="";
    
    public String gripper1="gripper_ambil",
    			gripper2="gripper_taruh";
    public static String tempgvalue="";
    
    public String lfollower1="line_follower_perempatan",
    		lfollower2="line_follower_pertigaan_kanan",
    		lfollower3="line_follower_pertigaan_kiri";
    public String lfvalue="02",lfspeed="10";
    public static String templfvalue="",templfspeed="";
    
    public String wfollower1="wall_follower_kanan",
    		wfollower2="wall_follower_kiri";
    public String wfpar2="waktu",wfspeed="10",wrange="5",wfvalue="5";
    public String wfwaktu="waktu",wfgaris="garis";
    public static String tempwfspeed="",tempwrange="",tempwfpar="",tempwfvalue="";
    
    public Modules(Context context){
    	super(context);
    }
	public Modules(Context context,int id, int resource, int x, int y, int ml, int mr, int mt, int mb) {
		super(context);
        super.setImageResource(resource);
        super.setId(id);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(x,y);
        params.setMargins(ml,mr,mt,mb);
        super.setLayoutParams(params);   
	}
	
	public void unsetActiveBlock(){	
		for(int i=0; i<Var.activeBlocks.size(); i++){
			
			if(Var.activeBlocks.get(i).getId()==Var.FORWARD_ID){
				Var.activeBlocks.get(i).setImageResource(R.drawable.maju);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.REVERSE_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.mundur);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.TLEFT_ID){
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.belki);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.TRIGHT_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.belka);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.LCD_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.lcdteks3);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.DELAY_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.waktu);
		    }
		    else if(Var.activeBlocks.get(i).getId()==Var.SOUND_ID) {
		    	
		    	Var.activeBlocks.get(i).setImageResource(R.drawable.suara);
		    	/*if(Var.activeBlocks.get(i).tipeData.equals(sMonostable))
					Var.activeBlocks.get(i).setImageResource(R.drawable.suara_mode1);
				if(Var.activeBlocks.get(i).tipeData.equals(sAstable))
					Var.activeBlocks.get(i).setImageResource(R.drawable.suara_mode2);*/
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

public void checkActiveBlock(){
		
		if(Var.forwardActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.maju_highlights);
			Var.forwardActive=false;
		}
		if(Var.reverseActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.mundur_highlights);
			Var.reverseActive=false;
		}
		if(Var.tleftActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.belki_highlights);
			Var.tleftActive=false;
		}
		if(Var.trightActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.belka_highlights);
			Var.trightActive=false;
		}
		if(Var.lcdActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.lcdteks_highlights);
			Var.lcdActive=false;
		}
		if(Var.soundActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.suara_highlights);
			Var.soundActive=false;
		}
		if(Var.delayActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.waktu_highlights);
			Var.delayActive=false;
		}
		if(Var.gripperActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.gripper_highlights);
			Var.gripperActive=false;
		}
		if(Var.loopActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.loop_open);
			Var.loopActive=false;
		}
		if(Var.lfollowerActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.linefollower_highlights);
			Var.lfollowerActive=false;
		}
		if(Var.wfollowerActive==true){
			unsetActiveBlock();
			this.setImageResource(R.drawable.wallfollower_highlights);
			Var.wfollowerActive=false;
		}
	}
	
public void setListener(int id){
	
	if(id==Var.FORWARD_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.FORWARD_ID;
				Var.onForward=true;
				Var.fromWorksheet=true;
				Var.tempMovingOrder=order;
				
				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachForwardProperties();
				Var.forwardActive=true;
				
				temptipeData=tipeData;
				tempfvalue=fvalue;
				tempfspeed=fspeed;

				checkActiveBlock();
				
				return false;
			}
		});
	}
	if(id==Var.REVERSE_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.REVERSE_ID;
				Var.onReverse=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachReverseProperties();
				Var.reverseActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				temprspeed=rspeed;
				temprvalue=rvalue;
				
				return false;
			}
		});
    }
	if(id==Var.TLEFT_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.TLEFT_ID;
				Var.onTleft=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachTurnLeftProperties();
				Var.tleftActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				temptlvalue=tlvalue;
				temptlspeed=tlspeed;
				
				return false;
			}
		});
    }
	if(id==Var.TRIGHT_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.TRIGHT_ID;
				Var.onTright=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachTurnRightProperties();
				Var.trightActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				temptrvalue=trvalue;
				temptrspeed=trspeed;
				
				return false;
			}
		});
    }
	if(id==Var.LCD_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.LCD_ID;
				Var.onLCD=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachLCDProperties();
				Var.lcdActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				templcdChar=lcdChar;
				
				return false;
			}
		});
    }
	if(id==Var.DELAY_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Var.temp_id=Var.DELAY_ID;
				Var.onDelay=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachDelayProperties();
				Var.delayActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				tempdvalue=dvalue;
				
				return false;
			}
		});
    }
	if(id==Var.SOUND_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Log.i("BLOCK ORDER", String.valueOf(order));
				Var.temp_id=Var.SOUND_ID;
				Var.onSound=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachSoundProperties();
				Var.soundActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				if(temptipeData.equals(sMonostable)){
					tempmonosOn=monosOn;
				}
				else{
					tempastaOn=astaOn;
					tempastaOff=astaOff;
					tempastaLoop=astaLoop;
				}
				//tempsvalue=svalue;
				
				return false;
			}
		});
    }
	if(id==Var.GRIPPER_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Log.i("BLOCK ORDER", String.valueOf(order));
				Var.temp_id=Var.GRIPPER_ID;
				Var.onGripper=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachGripperProperties();
				Var.gripperActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				//tempgvalue=gvalue;
				
				return false;
			}
		});
    }
	
	if(id==Var.LOOP_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Log.i("BLOCK ORDER", String.valueOf(order));
				Var.temp_id=Var.LOOP_ID;
				Var.onLoop=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachLoopProperties();
				Var.loopActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				templvalue=lvalue;
				
				return false;
			}
		});
    }

	
	if(id==Var.LFOLLOWER_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Log.i("BLOCK ORDER", String.valueOf(order));
				Var.temp_id=Var.LFOLLOWER_ID;
				Var.onFollower=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachFollowerProperties();
				Var.lfollowerActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				templfspeed=lfspeed;
				templfvalue=lfvalue;
				//tempgvalue=gvalue;
				
				return false;
			}
		});
    }
	
	if(id==Var.WFOLLOWER_ID){
        super.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {

				DragSource dragSource = (DragSource) v;
				
				Log.i("BLOCK ORDER", String.valueOf(order));
				Var.temp_id=Var.WFOLLOWER_ID;
				Var.onWall=true;
				Var.tempMovingOrder=order;
				Var.fromWorksheet=true;
				Var.indexLink=order;
				Var.indexModule=order;

				BuildUiActivity.mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
				BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size());
				BuildUiActivity.openTrash();
				BuildUiActivity.hideProperties();
				attachWallFollowerProperties();
				Var.wfollowerActive=true;
				checkActiveBlock();
				
				temptipeData=tipeData;
				tempwrange=wrange;
				tempwfpar=wfpar2;
				tempwfspeed=wfspeed;
				tempwfvalue=wfvalue;
				
				return false;
			}
		});
    }
	
}


public void deleteBlockById(int id){
	Log.d("INISIALISASI ID MASUK FUNGSI DELETEBLOCK",String.valueOf(id));
	Log.d("INISIALISASI LINK MASUK FUNGSI DELETEBLOCK",String.valueOf(Var.links.size()));
	
	
	if(id==0){
		BuildUiActivity.worksheet.removeView(Var.activeBlocks.get(id));
		
		if(Var.links.size()>1){
			Log.d("MASUK IF LINK LEBIH DARI 0",String.valueOf(Var.links.size()));
			BuildUiActivity.worksheet.removeView(Var.links.get(id+1));
			Var.links.remove(id);
		}
		Var.activeBlocks.remove(id);
		
		Log.d("ACTIVEBLOCKS",String.valueOf(Var.activeBlocks.size()));
		Log.d("LINKS",String.valueOf(Var.links.size()));
	} 
	else {
		BuildUiActivity.worksheet.removeView(Var.activeBlocks.get(id));
		BuildUiActivity.worksheet.removeView(Var.links.get(id));
		Var.links.remove(id);
		Var.activeBlocks.remove(id);
		
		Log.d("ACTIVEBLOCKS",String.valueOf(Var.activeBlocks.size()));
		Log.d("LINKS",String.valueOf(Var.links.size()));
	}
	
	if(Var.activeBlocks.size()==0){
		for(int i=0; i<Var.links.size(); i++) Var.links.remove(i);
	}
	Var.sortOrder();
	unsetActiveBlock();

	BuildUiActivity.bottom_properties.removeAllViews();
	BuildUiActivity.bottom_properties1.removeAllViews();
	
}

public boolean allowDrag () {
    return !mEmpty;
}

public void setDragController (DragController dragger){}


public void onDropCompleted (View target, boolean success)
{
    if (success) {
    	if(Var.onDelete==true){ 
    		deleteBlockById(order);
	    	Var.onSuccess=false;
	        Var.onDelay=false;
	        Var.onForward=false;
	        Var.onReverse=false;
	        Var.onSound=false;
	        Var.onLCD=false;
	        Var.onGripper=false;
	        Var.onLoop=false;
	        Var.onFollower=false;
	        Var.onWall=false;
	        Var.onTleft=false;
	        Var.onTright=false;

    	}
    }
}

public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset,
        DragView dragView, Object dragInfo)
{
    mEmpty = false;
    
    if(Var.fromWorksheet){
    	if(Var.onDelay==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.DELAY_ID);
        	setListener(Var.DELAY_ID);
        	Var.delayActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	dvalue=tempdvalue;
        }
        else if(Var.onGripper==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.GRIPPER_ID);
        	setListener(Var.GRIPPER_ID);
        	Var.gripperActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	//gvalue=tempgvalue;
        }
        else if(Var.onLoop==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.LOOP_ID);
        	setListener(Var.LOOP_ID);
        	Var.loopActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    		this.setLayoutParams(params);
        	
        	tipeData=temptipeData;
        	lvalue=templvalue;
        }
        else if(Var.onFollower==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.LFOLLOWER_ID);
        	setListener(Var.LFOLLOWER_ID);
        	Var.lfollowerActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	lfvalue=templfvalue;
        	lfspeed=templfspeed;
        }
        else if(Var.onWall==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.WFOLLOWER_ID);
        	setListener(Var.WFOLLOWER_ID);
        	Var.wfollowerActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	wrange=tempwrange;
        	wfpar2=tempwfpar;
        	wfspeed=tempwfspeed;
        	wfvalue=tempwfvalue;
        }
        else if(Var.onForward==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.FORWARD_ID);
        	setListener(Var.FORWARD_ID);
        	Var.forwardActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	fspeed=tempfspeed;
        	fvalue=tempfvalue;
        }
        else if(Var.onReverse==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.REVERSE_ID);
        	setListener(Var.REVERSE_ID);
        	Var.reverseActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	rspeed=temprspeed;
        	rvalue=temprvalue;
        }
        else if(Var.onSound==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.SOUND_ID);
        	setListener(Var.SOUND_ID);
        	Var.soundActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	//svalue=tempsvalue;
        	if(tipeData.equals(sMonostable)){
				monosOn=tempmonosOn;
			}
			else{
				astaOn=tempastaOn;
				astaOff=tempastaOff;
				astaLoop=tempastaLoop;
			}
        }
        else if(Var.onLCD==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.LCD_ID);
        	setListener(Var.LCD_ID);
        	Var.lcdActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	lcdChar=templcdChar;
        }
        else if(Var.onTleft==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.TLEFT_ID);
        	setListener(Var.TLEFT_ID);
        	Var.tleftActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	tlspeed=temptlspeed;
        	tlvalue=temptlvalue;
        }
        else if(Var.onTright==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	setId(Var.TRIGHT_ID);
        	setListener(Var.TRIGHT_ID);
        	Var.trightActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        	tipeData=temptipeData;
        	trspeed=temptrspeed;
        	trvalue=temptrvalue;
        }

    }
    
    if(Var.fromModul){
    	if(Var.onDelay==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.DELAY_ID);
        	Var.delayActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        	
        }
        else if(Var.onForward==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.FORWARD_ID);
        	Var.forwardActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onReverse==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.REVERSE_ID);
        	Var.reverseActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onSound==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.SOUND_ID);
        	Var.soundActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onLCD==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.LCD_ID);
        	Var.lcdActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        } 
        else if(Var.onLoop==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this .setId(Var.TAKE_ID);
        	
        	Modules closeloop= new Modules(getContext(),Var.temp_id, R.drawable.loop_close, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0,0,0,0) ;
    	    Image link= new Image(getContext(),50, R.drawable.next1, Var.size.y/11, Var.size.y/7,10,0,10,0) ;
    	    BuildUiActivity.worksheet.addView(link);
    	    closeloop.mEmpty=false;
    	    BuildUiActivity.worksheet.addView(closeloop);
        	Var.activeBlocks.add(Var.indexModule+1,closeloop);
        	//closeloop.setId(Var.LOOP_ID);
        	//closeloop.setListener(Var.LOOP_ID);
        	Var.links.add(Var.indexLink,link);
    		link.order=Var.links.size();
        	
        	Var.loopActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onFollower==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.TAKE_ID);
        	Var.lfollowerActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onWall==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.TAKE_ID);
        	Var.wfollowerActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onGripper==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.TAKE_ID);
        	Var.gripperActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onTleft==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.TLEFT_ID);
        	Var.tleftActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
        else if(Var.onTright==true) {
        	Var.activeBlocks.add(Var.indexModule,this);
        	//this.setId(Var.TRIGHT_ID);
        	Var.trightActive=true;
        	checkActiveBlock();
        	Var.onSuccess=true;
        }
    }
}
public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset,
        DragView dragView, Object dragInfo)
{
    setBackgroundResource (R.drawable.dropzone1);

}
public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset,
        DragView dragView, Object dragInfo)
{
}
public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset,
        DragView dragView, Object dragInfo)
{
    setBackgroundResource (Color.TRANSPARENT);
}
public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset,
        DragView dragView, Object dragInfo)
{
    return isEnabled();//mEmpty  && (mCellNumber >= 0);
}
public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset,
            DragView dragView, Object dragInfo, Rect recycle)
{
    return null;
}
public boolean isEmpty ()
{
    return mEmpty;
}
public boolean performClick ()
{
    if (!mEmpty) return super.performClick ();
    return false;
}
public boolean performLongClick ()
{
    if (!mEmpty) return super.performLongClick ();
    return false;
}
public void attachFollowerProperties(){
	
	
	Texts t= new Texts(getContext(),"Mengikuti garis hingga menemukan : ");
	BuildUiActivity.bottom_properties.addView(t);
	
	String par[]={"Perempatan","Pertigaan Kanan","Pertigaan Kiri"};
	spinner= new Spinners(getContext(),par);

	String par1[]={"Berhenti","Belok Kanan","Belok Kiri"};
	spinner1= new Spinners(getContext(),par1);
	

    if(tipeData.equalsIgnoreCase(lfollower1)){
    	spinner.setSelection(0);
    	//setImageResource(R.drawable.linefollower_perempatan);
    }
    if(tipeData.equalsIgnoreCase(lfollower2)){
    	spinner.setSelection(1);
    	//setImageResource(R.drawable.linefollower_pertigaan_kanan);
    }
    if(tipeData.equalsIgnoreCase(lfollower3)){
    	spinner.setSelection(2);
    	//setImageResource(R.drawable.linefollower_pertigaan_kiri);
    }

	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Perempatan")){
				tipeData=lfollower1;
				//setImageResource(R.drawable.linefollower_perempatan);
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Pertigaan Kanan")) {
				tipeData=lfollower2;
		    	//setImageResource(R.drawable.linefollower_pertigaan_kanan);
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Pertigaan Kiri")) {
				tipeData=lfollower3;
		    	//setImageResource(R.drawable.linefollower_pertigaan_kiri);
			}
		}@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});
	BuildUiActivity.bottom_properties.addView(spinner);
	
	Texts tt= new Texts(getContext()," Kemudian : ");
	BuildUiActivity.bottom_properties.addView(tt);
	
	if(lfvalue.equalsIgnoreCase("02"))spinner1.setSelection(0);
    if(lfvalue.equalsIgnoreCase("00"))spinner1.setSelection(1);
    if(lfvalue.equalsIgnoreCase("01"))spinner1.setSelection(2);
	
	spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Berhenti")){
				lfvalue="02";
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Belok Kanan")) {
				lfvalue="00";
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Belok Kiri")) {
				lfvalue="01";
			}
		}@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});
	
	BuildUiActivity.bottom_properties.addView(spinner1);
	
	Texts spd= new Texts(getContext()," Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    seek_min.setTextColor(Color.RED);
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			lfspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(lfspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    seek_max.setTextColor(Color.RED);
    BuildUiActivity.bottom_properties1.addView(seek_max);

}

public void attachWallFollowerProperties(){
	
	Texts t= new Texts(getContext(),"Menggunakan : ");
	BuildUiActivity.bottom_properties.addView(t);
	
	String par[]={"Sensor Kanan","Sensor Kiri"};
	spinner= new Spinners(getContext(),par);
	

    if(tipeData.equalsIgnoreCase(wfollower1)){
    	spinner.setSelection(0);
    }
    if(tipeData.equalsIgnoreCase(wfollower2)){
    	spinner.setSelection(1);
    }

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Sensor Kanan")){
				tipeData=wfollower1;
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Sensor Kiri")) {
				tipeData=wfollower2;
			}
		}@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});
	BuildUiActivity.bottom_properties.addView(spinner);
	
	Texts tt= new Texts(getContext(),"  jarak : ");
	BuildUiActivity.bottom_properties.addView(tt);

	final EditText wval=new EditText(getContext());
	final EditText range=new EditText(getContext());
	
	range.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	range.setInputType(InputType.TYPE_CLASS_NUMBER);
	range.setText(wrange);
	range.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	wrange=String.valueOf(range.getText());
	        	wfvalue=String.valueOf(wval.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties.addView(range);
	
	Texts typ=new Texts(getContext()," cm, berdasarkan : ");
	
	Texts lim=new Texts(getContext()," (5-100)");
	lim.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	lim.setTextColor(Color.RED);

	LinearLayout vals2= new LinearLayout(getContext());
	vals2.setGravity(Gravity.BOTTOM);
	vals2.setOrientation(LinearLayout.VERTICAL);
	vals2.addView(typ);
	vals2.addView(lim);
	
	BuildUiActivity.bottom_properties.addView(vals2);

	
	String par1[]={"Lama waktu","Garis dilewati"};
	spinner1= new Spinners(getContext(),par1);

	type=new Texts(getContext()," detik");

	limit=new Texts(getContext()," (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);

	
	if(wfpar2.equalsIgnoreCase(wfwaktu)){
		spinner1.setSelection(0);
		type.setText(" detik");
		limit.setText(" (0-100)");
	}
    if(wfpar2.equalsIgnoreCase(wfgaris)){
    	spinner1.setSelection(1);
    	type.setText(" garis");
    	limit.setText(" (0-50)");
    }
	
	spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lama waktu")){
				wfpar2=wfwaktu;
				type.setText(" detik");
				limit.setText(" (0-100)");
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Garis dilewati")) {
				wfpar2=wfgaris;
				type.setText(" garis");
				limit.setText(" (0-50)");
			}
		}@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});
	
	BuildUiActivity.bottom_properties.addView(spinner1);
	
	wval.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	wval.setInputType(InputType.TYPE_CLASS_NUMBER);
	wval.setText(wfvalue);
	wval.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	wfvalue=String.valueOf(wval.getText());
	        	wrange=String.valueOf(range.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties.addView(wval);
	
	BuildUiActivity.bottom_properties.addView(vals);
	
	Texts spd= new Texts(getContext(),"Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			wfspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(wfspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    BuildUiActivity.bottom_properties1.addView(seek_max);

}


public void attachGripperProperties(){
	Texts t= new Texts(getContext(),"Kontrol gripper untuk mengambil atau menaruh objek ");
	BuildUiActivity.bottom_properties.addView(t);
	
	String par[]={"Ambil objek","Taruh Objek"};
	
	
	spinner= new Spinners(getContext(),par);
	if(tipeData.equals(gripper1))spinner.setSelection(0);
	if(tipeData.equals(gripper2))spinner.setSelection(1);
	
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Ambil Objek")) tipeData=gripper1;
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Taruh Objek"))tipeData=gripper2;
		}@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});
	BuildUiActivity.bottom_properties1.addView(spinner);

}

public void attachDelayProperties(){
	Texts t= new Texts(getContext(),"Robot akan berhenti melakukan apapun selama jeda waktu : ");
	BuildUiActivity.bottom_properties1.addView(t);

	tipeData=delay;
	
	final EditText val=new EditText(getContext());
	val.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	val.setInputType(InputType.TYPE_CLASS_NUMBER);
	val.setText(dvalue);
	val.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	dvalue=String.valueOf(val.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(val);
	type=new Texts(getContext()," detik");
	limit=new Texts(getContext(),"   (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);;
	
	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties1.addView(vals);
}

private void soundMono(){
	tipeData=sMonostable;
	Texts tt= new Texts(getContext(),"Buzzer menyala selama : ");
	BuildUiActivity.bottom_properties1.addView(tt);
	
	final EditText e1=new EditText(getContext());
	e1.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	e1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
	e1.setInputType(InputType.TYPE_CLASS_NUMBER);
	e1.setText(monosOn);
	e1.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	monosOn=String.valueOf(e1.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(e1);

	Texts dtk= new Texts(getContext()," detik");
	limit=new Texts(getContext(),"  (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(dtk);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties1.addView(vals);
	
}

private void soundAstable(){
	
	tipeData=sAstable;
	Texts tt= new Texts(getContext(),"Buzzer menyala selama : ");
	BuildUiActivity.bottom_properties1.addView(tt);
	
	final EditText e1=new EditText(getContext());
	final EditText e2=new EditText(getContext());
	final EditText e3=new EditText(getContext());
	
	e1.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	e1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
	e1.setInputType(InputType.TYPE_CLASS_NUMBER);
	e1.setText(astaOn);
	e1.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	astaOn=String.valueOf(e1.getText());
	        	astaOff=String.valueOf(e2.getText());
	        	astaLoop=String.valueOf(e3.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(e1);
	
	Texts dtk= new Texts(getContext()," detik , mati ");
	
	limit=new Texts(getContext(),"  (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(dtk);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties1.addView(vals);
	
	e2.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	e2.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
	e2.setInputType(InputType.TYPE_CLASS_NUMBER);
	e2.setText(astaOff);
	e2.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	astaOn=String.valueOf(e1.getText());
	        	astaOff=String.valueOf(e2.getText());
	        	astaLoop=String.valueOf(e3.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(e2);
	
	Texts dtk1= new Texts(getContext()," detik , dan diulang ");

	limit=new Texts(getContext(),"  (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

	LinearLayout vals1= new LinearLayout(getContext());
	vals1.setGravity(Gravity.BOTTOM);
	vals1.setOrientation(LinearLayout.VERTICAL);
	vals1.addView(dtk1);
	vals1.addView(limit);
	
	BuildUiActivity.bottom_properties1.addView(vals1);

	
	e3.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	e3.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
	e3.setInputType(InputType.TYPE_CLASS_NUMBER);
	e3.setText(astaLoop);
	e3.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	astaOn=String.valueOf(e1.getText());
	        	astaOff=String.valueOf(e2.getText());
	        	astaLoop=String.valueOf(e3.getText());
	            return true;
	        }
	        return false;
	    }
	}); 
	BuildUiActivity.bottom_properties1.addView(e3);
	
	Texts dtk2= new Texts(getContext()," kali");

	limit=new Texts(getContext(),"  (0-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

	LinearLayout vals2= new LinearLayout(getContext());
	vals2.setGravity(Gravity.BOTTOM);
	vals2.setOrientation(LinearLayout.VERTICAL);
	vals2.addView(dtk2);
	vals2.addView(limit);
	
	BuildUiActivity.bottom_properties1.addView(vals2);
} 

public void attachSoundProperties(){
	
	Texts t= new Texts(getContext(),"Pilih mode : ");
	BuildUiActivity.bottom_properties.addView(t);
	String par[]={"Mode 1","Mode 2","Super Mario"};
	
	spinner= new Spinners(getContext(),par);
	if(tipeData.equals(sMonostable)){
		spinner.setSelection(0);
		soundMono();
	}
	if(tipeData.equals(sAstable)){
		spinner.setSelection(1);
		soundAstable();
	}
	if(tipeData.equals(sMario)){
		spinner.setSelection(2);
		tipeData=sMario;
	}
	
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Mode 1")){
				BuildUiActivity.bottom_properties1.removeAllViews();
				soundMono();
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Mode 2")){
				BuildUiActivity.bottom_properties1.removeAllViews();
				soundAstable();
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Super Mario")){
				BuildUiActivity.bottom_properties1.removeAllViews();
				tipeData=sMario;
			}
		}@Override public void onNothingSelected(AdapterView<?> arg0) {}});
	
	BuildUiActivity.bottom_properties.addView(spinner);
	
}

public void attachLCDProperties(){
	
	tipeData=lcd;
	
	Texts t= new Texts(getContext(),"Menampilkan teks pada LCD. Maksimal 16 karakter");
	BuildUiActivity.bottom_properties.addView(t);

	Texts t1= new Texts(getContext(),"Input teks : ");
	BuildUiActivity.bottom_properties1.addView(t1);

	final EditText e1=new EditText(getContext());
	e1.setLayoutParams(new LayoutParams(Var.size.x/5,LayoutParams.WRAP_CONTENT));
	e1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(16)});
	e1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
	e1.setText(lcdChar);
	e1.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	lcdChar=String.valueOf(e1.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(e1);
}

public void attachTurnRightProperties(){

	String par[] = {"Derajat","Lama Waktu"};

	spinner= new Spinners(getContext(), par);

	Texts mode=new Texts(getContext(),"Robot berbelok ke kanan berdasarkan : ");
	BuildUiActivity.bottom_properties.addView(mode);
	
	BuildUiActivity.bottom_properties.addView(spinner);

	type=new Texts(getContext()," derajat");
	limit=new Texts(getContext()," (0-180)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

    if(tipeData.equalsIgnoreCase(tright2)){
    	spinner.setSelection(0);
    	limit.setText("   (0-180)");
    	type.setText(" derajat");
    }
    if(tipeData.equalsIgnoreCase(tright1)){
    	spinner.setSelection(1);
    	limit.setText("   (0-100)");
    	type.setText(" detik");
    }
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Derajat")){
				tipeData=tright2;
				limit.setText("   (0-180)");
				type.setText(" derajat");
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lama Waktu")){
				tipeData=tright1;
				limit.setText("   (0-100)");
				type.setText(" detik");
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});	
	
	final EditText val=new EditText(getContext());
	val.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	val.setInputType(InputType.TYPE_CLASS_NUMBER);
	val.setText(trvalue);
	val.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	trvalue=String.valueOf(val.getText());
	            return true;
	        }
	        return false;
	    }
	});	
	
	BuildUiActivity.bottom_properties.addView(val);
	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties.addView(vals);
		
	Texts spd= new Texts(getContext()," Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			trspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(trspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    BuildUiActivity.bottom_properties1.addView(seek_max);
}


public void attachTurnLeftProperties(){
	String par[] = {"Derajat","Lama Waktu"};

	spinner= new Spinners(getContext(), par);

	Texts mode=new Texts(getContext(),"Robot berbelok ke kiri berdasarkan : ");
	BuildUiActivity.bottom_properties.addView(mode);
	
	BuildUiActivity.bottom_properties.addView(spinner);

	type=new Texts(getContext()," derajat");
	limit=new Texts(getContext()," (0-180)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

    if(tipeData.equalsIgnoreCase(tleft2)){
    	spinner.setSelection(0);
    	limit.setText("     (0-180)");
    	type.setText(" derajat");
    }
    if(tipeData.equalsIgnoreCase(tleft1)){
    	spinner.setSelection(1);
    	limit.setText("   (0-100)");
    	type.setText(" detik");
    }
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Derajat")){
				tipeData=tleft2;
				limit.setText("   (0-180)");
				type.setText(" derajat");
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lama Waktu")){
				tipeData=tleft1;
				limit.setText("   (0-100)");
				type.setText(" detik");
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});	
	
	final EditText val=new EditText(getContext());
	val.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	val.setInputType(InputType.TYPE_CLASS_NUMBER);
	val.setText(tlvalue);
	val.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	tlvalue=String.valueOf(val.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties.addView(val);
	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties.addView(vals);
		
	Texts spd= new Texts(getContext()," Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			tlspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(tlspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    BuildUiActivity.bottom_properties1.addView(seek_max);
}

public void attachReverseProperties(){
	String par[] = {"Jarak Tempuh","Lama Waktu"};

	spinner= new Spinners(getContext(), par);

	Texts mode=new Texts(getContext(),"Berdasarkan : ");
	BuildUiActivity.bottom_properties.addView(mode);
	
	BuildUiActivity.bottom_properties.addView(spinner);

	type=new Texts(getContext()," cm");
	limit=new Texts(getContext()," (5-100)");
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);


    if(tipeData.equalsIgnoreCase(reverse1)){
    	spinner.setSelection(1);
    	type.setText(" detik");
    	limit.setText("    (0-100)");
    }
    if(tipeData.equalsIgnoreCase(reverse2)){
    	spinner.setSelection(0);
    	type.setText(" cm");
    	limit.setText(" (5-100)");
    }
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lama Waktu")){
				tipeData=reverse1;
				type.setText(" detik");
				limit.setText("    (0-100)");
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Jarak Tempuh")){
				tipeData=reverse2;
				type.setText(" cm");
				limit.setText(" (5-100)");
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});	
	
	final EditText val=new EditText(getContext());
	val.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	val.setInputType(InputType.TYPE_CLASS_NUMBER);
	val.setText(rvalue);
	val.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	rvalue=String.valueOf(val.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties.addView(val);
	
	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties.addView(vals);
	
		
	Texts spd= new Texts(getContext()," Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			rspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(rspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    BuildUiActivity.bottom_properties1.addView(seek_max);
}

public void attachLoopProperties(){

	tipeData=looping;
	
	Texts t= new Texts(getContext(),"Melakukan perulangan blok yang berada di antara kurung kurawal");
	BuildUiActivity.bottom_properties.addView(t);

	Texts t1= new Texts(getContext(),"Jumlah perulangan : ");
	BuildUiActivity.bottom_properties1.addView(t1);

	final EditText e1=new EditText(getContext());
	e1.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	e1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(16)});
	e1.setInputType(InputType.TYPE_CLASS_NUMBER);
	e1.setText(lvalue);
	e1.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	lvalue=String.valueOf(e1.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties1.addView(e1);

}

public void attachForwardProperties(){
	String par[] = {"Jarak Tempuh","Lama Waktu"};

	spinner= new Spinners(getContext(), par);

	Texts mode=new Texts(getContext(),"Berdasarkan : ");
	BuildUiActivity.bottom_properties.addView(mode);
	
	BuildUiActivity.bottom_properties.addView(spinner);

	type=new Texts(getContext()," cm");
	
	limit=new Texts(getContext()," (5-100)");
	//v.setTextAppearance(getContext(), android.R.style.TextAppearance_DeviceDefault_Small);
	limit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
	limit.setTextColor(Color.RED);

    if(tipeData.equalsIgnoreCase(fwd1)){
    	spinner.setSelection(1);
    	type.setText(" detik");
    	limit.setText("    (0-100)");
    	
    	//setImageResource(R.drawable.maju_waktu);
    }
    if(tipeData.equalsIgnoreCase(fwd2)){
    	spinner.setSelection(0);
    	type.setText(" cm");
    	limit.setText(" (5-100)");
    	
    	//setImageResource(R.drawable.maju_jarak);
    }
	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long arg3) {
			if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lama Waktu")){
				tipeData=fwd1;
			//	setImageResource(R.drawable.maju_waktu);
				type.setText(" detik");
				limit.setText("    (0-100)");
			}
			else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Jarak Tempuh")){
				tipeData=fwd2;
				//setImageResource(R.drawable.maju_jarak);
				type.setText(" cm");
				limit.setText(" (5-100)");
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}});	
	
	final EditText val=new EditText(getContext());
	val.setLayoutParams(new LayoutParams(Var.size.x/18,LayoutParams.WRAP_CONTENT));
	val.setInputType(InputType.TYPE_CLASS_NUMBER);
	val.setText(fvalue);
	val.setOnKeyListener(new OnKeyListener() {
		@Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) 
	        {
	        	fvalue=String.valueOf(val.getText());
	            return true;
	        }
	        return false;
	    }
	});
	BuildUiActivity.bottom_properties.addView(val);
	
	LinearLayout vals= new LinearLayout(getContext());
	vals.setGravity(Gravity.BOTTOM);
	vals.setOrientation(LinearLayout.VERTICAL);
	vals.addView(type);
	vals.addView(limit);
	
	BuildUiActivity.bottom_properties.addView(vals);
	
	Texts spd= new Texts(getContext()," Kecepatan : ");
	BuildUiActivity.bottom_properties1.addView(spd);

    Texts seek_min= new Texts(getContext()," min");
    BuildUiActivity.bottom_properties1.addView(seek_min);
    
    SeekBars sBar= new SeekBars(getContext());
    sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			fspeed=String.valueOf(arg1);
		}
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {}
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {}});
    
    sBar.setProgress(Integer.parseInt(fspeed));
    BuildUiActivity.bottom_properties1.addView(sBar);
    
    Texts seek_max= new Texts(getContext(),"max ");
    BuildUiActivity.bottom_properties1.addView(seek_max);
    }	

public String extractDataSave(Modules a){
	if(a.getId()==Var.FORWARD_ID){
		dataSave=a.tipeData+","+a.fvalue+","+a.fspeed+","+a.fspeed;
	}
	if(a.getId()==Var.REVERSE_ID){
		dataSave=a.tipeData+","+a.rvalue+","+a.rspeed+","+a.rspeed;
    }
	if(a.getId()==Var.TLEFT_ID){
		dataSave=a.tipeData+","+a.tlvalue+","+a.tlspeed+","+a.tlspeed;
    }
	if(a.getId()==Var.TRIGHT_ID){
		dataSave=a.tipeData+","+a.trvalue+","+a.trspeed+","+a.trspeed;
    }
	if(a.getId()==Var.LCD_ID){
		dataSave=a.tipeData+","+String.valueOf(a.lcdChar.length())+","+a.lcdChar;
    }
	if(a.getId()==Var.DELAY_ID){
		dataSave=a.tipeData+","+a.dvalue;
    }
	if(a.getId()==Var.SOUND_ID){
		if(a.tipeData.equals(sMonostable)){
			dataSave=a.tipeData+","+a.monosOn;
		}
		else if(a.tipeData.equals(sAstable)){
			dataSave=a.tipeData+","+a.astaOn+","+a.astaOff+","+a.astaLoop;
		}
		else if(a.tipeData.equals(sMario)) dataSave=a.tipeData;
    }
	if(a.getId()==Var.GRIPPER_ID){
		dataSave=a.tipeData;
    }
	if(a.getId()==Var.LFOLLOWER_ID){
		dataSave=a.tipeData+","+a.lfvalue+","+a.lfspeed;
    }
	if(a.getId()==Var.LOOP_ID){
		dataSave=a.tipeData+","+a.lvalue;
    }
	if(a.getId()==Var.WFOLLOWER_ID){
		dataSave=a.tipeData+","+a.wfpar2+","+a.wfvalue+","+a.wrange+","+a.wfspeed;
    }
	return dataSave;
}


}
