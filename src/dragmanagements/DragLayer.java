
package dragmanagements;

import interfaces.Image;
import interfaces.Modules;
import visualprogrammer.BuildUiActivity;
import visualprogrammer.Var;

import com.example.visualprogrammer.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class DragLayer extends FrameLayout implements DragController.DragListener
{
    public static DragController mDragController;
    public static Modules dropzone;
    public static Image link;

    public DragLayer (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

	public void setDragController(DragController controller) {
        mDragController = controller;
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mDragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragController.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDragController.onTouchEvent(ev);
    }
 
    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return mDragController.dispatchUnhandledMove(focused, direction);
    }

public void onDragStart(DragSource source, Object info, int dragAction) 
{
    dropzone= new Modules(getContext(),Var.temp_id, R.drawable.dropzone1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
    link= new Image(getContext(),50, R.drawable.next1, Var.size.y/11, Var.size.y/7,10,0,10,0) ;
    link.setAlpha((float)0.7);
    dropzone.unsetActiveBlock();
    BuildUiActivity.hideProperties();
    dropzone.setListener(dropzone.getId());

	if(Var.fromModul==true){
		if(Var.activeBlocks.size()>0) BuildUiActivity.worksheet.addView(link);
		
		BuildUiActivity.worksheet.addView(dropzone);
		mDragController.addDropTarget (dropzone);
	}
	
    View v = findViewById (R.id.delete_zone_view);
    if (v != null) {
       DeleteZone dz = (DeleteZone) v;
       mDragController.addDropTarget (dz);
    }
    
    Var.indexLink=Var.links.size();
    Var.indexModule=Var.activeBlocks.size();
}
 
public void onDragEnd() 
{
    mDragController.removeAllDropTargets ();

	if(Var.fromWorksheet==true){
		if(Var.onSuccess==false){
    		BuildUiActivity.worksheet.removeView(link);
	    	BuildUiActivity.worksheet.removeView(dropzone);
	    	
	    	if(!Var.onDelete){
	    		Var.activeBlocks.get(Var.tempMovingOrder).setVisibility(View.VISIBLE);
	    		
	    		if(Var.tempMovingOrder==0){
	    	    	if(Var.links.size()>1)Var.links.get(Var.tempMovingOrder+1).setVisibility(View.VISIBLE);
	        	}else{
	    	    	Var.links.get(Var.tempMovingOrder).setVisibility(View.VISIBLE);
	        	}
	    	}
	    	Var.onDelete=false;
		    Var.fromWorksheet=false;
    }
    else {
    	
		if(Var.tempMovingOrder==0){
			Var.activeBlocks.get(Var.tempMovingOrder+1).setVisibility(View.VISIBLE);
	    	if(Var.links.size()>1)Var.links.get(Var.tempMovingOrder+1).setVisibility(View.VISIBLE);
    	}
    	
    	if(Var.activeBlocks.size()>=1){
    		Var.links.add(Var.indexLink,link);
    		link.order=Var.links.size()-1;
    	}

    	Var.sortOrder();
    	if(Var.tempMovingOrder==0){
    		BuildUiActivity.worksheet.removeView(Var.activeBlocks.get(0));
    		Var.activeBlocks.remove(0);
    		
    		BuildUiActivity.worksheet.removeView(Var.links.get(0+1));
    		Var.links.remove(0);
    	}
    	else{
	    	if(Var.tempMovingOrder >= Var.indexModule){
	    		BuildUiActivity.worksheet.removeView(Var.activeBlocks.get(Var.tempMovingOrder+1));
	    		BuildUiActivity.worksheet.removeView(Var.links.get(Var.tempMovingOrder+1));
	    		
	    		Var.activeBlocks.remove(Var.tempMovingOrder+1);
	    		Var.links.remove(Var.tempMovingOrder+1);
	    	}else {
	    		BuildUiActivity.worksheet.removeView(Var.activeBlocks.get(Var.tempMovingOrder));
	    		BuildUiActivity.worksheet.removeView(Var.links.get(Var.tempMovingOrder));
	    		
	    		Var.activeBlocks.remove(Var.tempMovingOrder);
	    		Var.links.remove(Var.tempMovingOrder);
	    	}
    	}
    	Var.sortOrder();
        Var.onDelay=false;
        Var.onGripper=false;
        Var.onLoop=false;
        Var.onFollower=false;
        Var.onWall=false;
        Var.onForward=false;
        Var.onReverse=false;
        Var.onSound=false;
        Var.onLCD=false;
        Var.onTleft=false;
        Var.onTright=false;
    }
    Var.fromWorksheet=false;
    Var.fromModul=false;
	Var.isSaved=false;
	}
	
	
    if(Var.fromModul==true){
    
	    if(Var.onSuccess==false){
	    		BuildUiActivity.worksheet.removeView(link);
		    	BuildUiActivity.worksheet.removeView(dropzone);
			    Var.fromModul=false;
	    }
	    else {
	    	
	    	if(Var.activeBlocks.size()>=1){
	    		Var.links.add(Var.indexLink,link);
	    		link.order=Var.links.size()-1;
	    	}

	    	Var.sortOrder();
			
	    	if(Var.onForward==true){
	    		dropzone.attachForwardProperties();
	    		Var.onForward=false;
	    	}
	    	else if(Var.onDelay==true){
	    		dropzone.attachDelayProperties();
	    		Var.onDelay=false;
	    	}
	    	else if(Var.onReverse==true){
	    		dropzone.attachReverseProperties();
	    		Var.onReverse=false;
	    	}
	    	else if(Var.onSound==true){
	    		dropzone.attachSoundProperties();
	    		Var.onSound=false;
	    	}
	    	else if(Var.onLCD==true){
	    		Var.onLCD=false;
		    	dropzone.attachLCDProperties();
	    	}
	    	else if(Var.onGripper==true){
	    		dropzone.attachGripperProperties();
	    		Var.onGripper=false;
	    	}
	    	else if(Var.onFollower==true){
	    		dropzone.attachFollowerProperties();
	    		Var.onFollower=false;
	    	}
	    	else if(Var.onLoop==true){
	    		dropzone.attachLoopProperties();
	    		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	    		dropzone.setLayoutParams(params);
	    		Var.onLoop=false;
	    	}
	    	else if(Var.onWall==true){
	    		dropzone.attachWallFollowerProperties();
	    		Var.onWall=false;
	    	}
	    	else if(Var.onTleft==true){
	    		Var.onTleft=false;
	    		dropzone.attachTurnLeftProperties();
	    	}
	    	else if(Var.onTright==true){
	    		Var.onTright=false;
	    		dropzone.attachTurnRightProperties();
	    	}

	    }
	    Var.fromWorksheet=false;
		Var.fromModul=false;
		Var.isSaved=false;
	}

	Log.i("******************", String.valueOf(Var.activeBlocks.size()));
	Log.i("##################", String.valueOf(Var.links.size()));
	
}


public void generateActiveBlocks(String data){

	String[] programData=data.split(";");
	
    for(int i=0; i<programData.length; i++){
    	String[] blockData;
    	blockData=programData[i].split(",");
    	
    	dropzone= new Modules(getContext(),0, R.drawable.dropzone, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
        link= new Image(getContext(),50, R.drawable.next1, Var.size.y/11, Var.size.y/7,10,0,10,0) ;
		link.setAlpha((float)0.7);
    	
    	if(blockData[0].equalsIgnoreCase(dropzone.fwd1) || blockData[0].equalsIgnoreCase(dropzone.fwd2)){

	        dropzone.setImageResource(R.drawable.maju);
	        dropzone.setId(Var.FORWARD_ID);
	        dropzone.setListener(Var.FORWARD_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.fvalue=blockData[1];
	        dropzone.fspeed=blockData[2];
	        //if(dropzone.tipeData.equalsIgnoreCase(dropzone.fwd1)) dropzone.setImageResource(R.drawable.maju_waktu);
	        //else dropzone.setImageResource(R.drawable.maju_jarak);
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.reverse1) || blockData[0].equalsIgnoreCase(dropzone.reverse2)){

	        dropzone.setImageResource(R.drawable.mundur);
	        dropzone.setId(Var.REVERSE_ID);
	        dropzone.setListener(Var.REVERSE_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.rvalue=blockData[1];
	        dropzone.rspeed=blockData[2];
	        
	        //if(dropzone.tipeData.equalsIgnoreCase(dropzone.reverse1)) dropzone.setImageResource(R.drawable.mundur_waktu);
	        //else dropzone.setImageResource(R.drawable.mundur_jarak);
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.tleft1) || blockData[0].equalsIgnoreCase(dropzone.tleft2)){
    		dropzone.setImageResource(R.drawable.belki);
	        dropzone.setId(Var.TLEFT_ID);
	        dropzone.setListener(Var.TLEFT_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.tlvalue=blockData[1];
	        dropzone.tlspeed=blockData[2];
	        
	        //if(dropzone.tipeData.equalsIgnoreCase(dropzone.tleft1)) dropzone.setImageResource(R.drawable.belki_waktu);
	        //else dropzone.setImageResource(R.drawable.belki_sudut);
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.tright1) || blockData[0].equalsIgnoreCase(dropzone.tright2)){
    		dropzone.setImageResource(R.drawable.belka);
	        dropzone.setId(Var.TRIGHT_ID);
	        dropzone.setListener(Var.TRIGHT_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.tlvalue=blockData[1];
	        dropzone.tlspeed=blockData[2];
	        
	        //if(dropzone.tipeData.equalsIgnoreCase(dropzone.tright1)) dropzone.setImageResource(R.drawable.belka_waktu);
	        //else dropzone.setImageResource(R.drawable.belka_sudut);
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.lcd)){
    		dropzone.setImageResource(R.drawable.lcdteks3);
	        dropzone.setId(Var.LCD_ID);
	        dropzone.setListener(Var.LCD_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.lcdChar=blockData[2];
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.sMonostable)){
    		dropzone.setImageResource(R.drawable.suara);
	        dropzone.setId(Var.SOUND_ID);
	        dropzone.setListener(Var.SOUND_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.monosOn=blockData[1];
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.sAstable)){
    		dropzone.setImageResource(R.drawable.suara);
	        dropzone.setId(Var.SOUND_ID);
	        dropzone.setListener(Var.SOUND_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.astaOn=blockData[1];
	        dropzone.astaOff=blockData[2];
	        dropzone.astaLoop=blockData[3];
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.sMario)){
    		dropzone.setImageResource(R.drawable.suara);
	        dropzone.setId(Var.SOUND_ID);
	        dropzone.setListener(Var.SOUND_ID);
	        dropzone.tipeData=blockData[0];
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.delay)){
    		dropzone.setImageResource(R.drawable.waktu);
	        dropzone.setId(Var.DELAY_ID);
	        dropzone.setListener(Var.DELAY_ID);
	        dropzone.tipeData=blockData[0];
	        dropzone.dvalue=blockData[1];
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.gripper1) || blockData[0].equalsIgnoreCase(dropzone.gripper2)){
    		dropzone.tipeData=blockData[0];
        	dropzone.setImageResource(R.drawable.gripper);
    	    dropzone.setId(Var.GRIPPER_ID);
    	    dropzone.setListener(Var.GRIPPER_ID);	
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.lfollower1) ||
    			blockData[0].equalsIgnoreCase(dropzone.lfollower2) ||
    			blockData[0].equalsIgnoreCase(dropzone.lfollower3)){
    		dropzone.setImageResource(R.drawable.linefollower);
    	    dropzone.tipeData=blockData[0];
        	dropzone.lfvalue=blockData[1];
    	    dropzone.lfspeed=blockData[2];
    	    dropzone.setId(Var.LFOLLOWER_ID);
    	    dropzone.setListener(Var.LFOLLOWER_ID);
    	}
    	else if(blockData[0].equalsIgnoreCase(dropzone.wfollower1) || blockData[0].equalsIgnoreCase(dropzone.wfollower2)){
    		dropzone.setImageResource(R.drawable.wallfollowers);
    	    dropzone.setId(Var.WFOLLOWER_ID);
    	    dropzone.setListener(Var.WFOLLOWER_ID);
    	    
    	    dropzone.tipeData=blockData[0];
    	    dropzone.wfpar2=blockData[1];
    	    dropzone.wfvalue=blockData[2];
    	    dropzone.wrange=blockData[3];
    	    dropzone.wfspeed=blockData[4];
    	}
    	
    	dropzone.mEmpty=false;
		Var.activeBlocks.add(dropzone);
		Var.links.add(link);
		Var.sortOrder();
		if(Var.links.size()>1)BuildUiActivity.worksheet.addView(link);
		BuildUiActivity.worksheet.addView(dropzone);
    }
}

}
