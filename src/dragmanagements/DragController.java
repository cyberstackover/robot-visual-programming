
package dragmanagements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import java.util.ArrayList;

import com.example.visualprogrammer.R;

import visualprogrammer.BuildUiActivity;
import visualprogrammer.Var;


public class DragController {
    private static final String TAG = "DragController";
    public static int DRAG_ACTION_MOVE = 0;
    public static int DRAG_ACTION_COPY = 1;
    public static int moduleID;
    private static final int VIBRATE_DURATION = 75;
    private static Context mContext;
    private Vibrator mVibrator;
    private Rect mRectTemp = new Rect();
    private final int[] mCoordinatesTemp = new int[2];
    private boolean mDragging;
    private float mMotionDownX;
    private float mMotionDownY;
    private DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private View mOriginator;
    private float mTouchOffsetX;
    private float mTouchOffsetY;
    private DragSource mDragSource;
    private Object mDragInfo;
    private DragView mDragView;
    private ArrayList<DropTarget> mDropTargets = new ArrayList<DropTarget>();
    private DragListener mListener;
    private static IBinder mWindowToken;
    private View mMoveTarget;
    private DropTarget mLastDropTarget;
    private static InputMethodManager mInputMethodManager;
    
    interface DragListener {

        public void onDragStart(DragSource source, Object info, int dragAction);
        public void onDragEnd();
    }
    
    public DragController(Context context) {
        mContext = context;
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }
    public void startDrag(View v, DragSource source, Object dragInfo, int dragAction) {
        // Start dragging, but only if the source has something to drag.
        boolean doDrag = source.allowDrag ();
        if (!doDrag) return;

        mOriginator = v;

        Bitmap b = getViewBitmap(v);

        if (b == null) {
            // out of memory?
            return;
        }

        int[] loc = mCoordinatesTemp;
        v.getLocationOnScreen(loc);
        int screenX = loc[0];
        int screenY = loc[1];

        startDrag(b, screenX, screenY, 0, 0, b.getWidth(), b.getHeight(),
                source, dragInfo, dragAction);

        b.recycle();

        if (dragAction == DRAG_ACTION_MOVE) {
           // if(Var.fromWorksheet==true)((ImageView) mOriginator).setImageResource(R.drawable.dropzone1);
        }
    }
    public void startDrag(Bitmap b, int screenX, int screenY,
            int textureLeft, int textureTop, int textureWidth, int textureHeight,
            DragSource source, Object dragInfo, int dragAction) {
        if (mListener != null) {
            mListener.onDragStart(source, dragInfo, dragAction);
        }

        int registrationX = ((int)mMotionDownX) - screenX;
        int registrationY = ((int)mMotionDownY) - screenY;

        mTouchOffsetX = mMotionDownX - screenX;
        mTouchOffsetY = mMotionDownY - screenY;

        mDragging = true;
        mDragSource = source;
        mDragInfo = dragInfo;

        mVibrator.vibrate(VIBRATE_DURATION);

        DragView dragView = mDragView = new DragView(mContext, b, registrationX, registrationY,textureLeft, textureTop, textureWidth, textureHeight);
        dragView.show(mWindowToken, (int)mMotionDownX, (int)mMotionDownY);
    }
    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e(TAG, "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mDragging;
    }
    public void cancelDrag() {
        endDrag();
    }

    private void endDrag() {
        if (mDragging) {
            mDragging = false;
            if (mListener != null) {
                mListener.onDragEnd();
            }
            if (mDragView != null) {
                mDragView.remove();
                mDragView = null;
            }
        }
	    BuildUiActivity.del.setVisibility(View.GONE);
    }
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            recordScreenSize();
        }

        final int screenX = clamp((int)ev.getRawX(), 0, mDisplayMetrics.widthPixels);
        final int screenY = clamp((int)ev.getRawY(), 0, mDisplayMetrics.heightPixels);

        switch (action) {
            case MotionEvent.ACTION_MOVE:
            	Log.d ("DragControl", "action move intercept touch");
                break;

            case MotionEvent.ACTION_DOWN:
                // Remember location of down touch
                mMotionDownX = screenX;
                mMotionDownY = screenY;
                mLastDropTarget = null;
                Log.d ("DragControl", "action down intercept touch");
                break;

            case MotionEvent.ACTION_CANCEL:
            	Log.d ("DragControl", "action cancel intercept touch");
            	break;
            case MotionEvent.ACTION_UP:
                if (mDragging) {
                    drop(screenX, screenY);
                    Log.d ("DragControl", "drop action up");
                }
                endDrag();
                Log.d ("DragControl", "enddrag action up");
                break;
        }

        return mDragging;
    }
    void setMoveTarget(View view) {
        mMoveTarget = view;
    }
    
    void shiftingWsheet(MotionEvent ev){
    	addDropTarget(DragLayer.dropzone);
    	if(Var.tempMovingOrder==0){
    		Var.activeBlocks.get(Var.tempMovingOrder).setVisibility(View.GONE);
	    	Var.links.get(Var.tempMovingOrder+1).setVisibility(View.GONE);
	    	
	    	for(int i=0; i<Var.activeBlocks.size(); i++){
	    		
	    		Log.d("SHIFTING",String.valueOf(i));
	    		
	    		Rect loc2 = new Rect();
	            int[] location2 = new int[2];
	            Var.activeBlocks.get(i).getLocationOnScreen(location2);
	            
	            loc2.left = location2[0];
	            loc2.right = loc2.left + Var.activeBlocks.get(i).getWidth();
	            int center= (loc2.left+loc2.right)/2;
	            
	            if(ev.getX()<center){
	            	if(i>1){
	            	
		            	Var.indexLink=Var.activeBlocks.get(i).order;
						Var.indexModule=Var.activeBlocks.get(i).order;
						
		            	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
		            	BuildUiActivity.worksheet.removeView(DragLayer.link);
		
						BuildUiActivity.worksheet.addView(DragLayer.link,(Var.activeBlocks.get(i).order*2)-1);
						BuildUiActivity.worksheet.addView(DragLayer.dropzone,(Var.activeBlocks.get(i).order*2));
	            	}
					break;
	        	}
	    	}

    	}else{
	    	Var.activeBlocks.get(Var.tempMovingOrder).setVisibility(View.GONE);
	    	Var.links.get(Var.tempMovingOrder).setVisibility(View.GONE);
	    	
	    	for(int i=0; i<Var.activeBlocks.size(); i++){
	    		
	    		Log.d("SHIFTING",String.valueOf(i));
	    		
	    		Rect loc2 = new Rect();
	            int[] location2 = new int[2];
	            Var.activeBlocks.get(i).getLocationOnScreen(location2);
	            
	            loc2.left = location2[0];
	            loc2.right = loc2.left + Var.activeBlocks.get(i).getWidth();
	            int center= (loc2.left+loc2.right)/2;
	            
	            if(ev.getX()<center){
		            	Var.indexLink=Var.activeBlocks.get(i).order;
						Var.indexModule=Var.activeBlocks.get(i).order;
						
		            	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
		            	BuildUiActivity.worksheet.removeView(DragLayer.link);
		
						BuildUiActivity.worksheet.addView(DragLayer.link,(Var.activeBlocks.get(i).order*2)-1);
						BuildUiActivity.worksheet.addView(DragLayer.dropzone,(Var.activeBlocks.get(i).order*2));
					break;
	        	}
	    	}

    	}
    	
    	
    	
    	Rect loc1 = new Rect();
        int[] location1 = new int[2];
        Var.activeBlocks.get(0).getLocationOnScreen(location1);
        
        loc1.left = location1[0];
        loc1.right = loc1.left + Var.activeBlocks.get(0).getWidth();
        
        Rect loc = new Rect();
        int[] location = new int[2];
        Var.activeBlocks.get(Var.activeBlocks.size()-1).getLocationOnScreen(location);
        loc.left = location[0];
        
        if(ev.getX()<loc1.right){
        	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
        	BuildUiActivity.worksheet.removeView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.dropzone,0);
        	BuildUiActivity.worksheet.addView(DragLayer.link,1);
    		
        	Var.indexLink=1;
    		Var.indexModule=0;
    	}

        else if(ev.getX()>loc.left){
        	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
        	BuildUiActivity.worksheet.removeView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.dropzone);
    		
    		Var.indexModule=Var.activeBlocks.size();
    		Var.indexLink=Var.activeBlocks.size();
    	}

    }
    
    void shifting(MotionEvent ev){
    	for(int i=0; i<Var.activeBlocks.size(); i++){
    		
    		Log.d("SHIFTING",String.valueOf(i));
    		
    		Rect loc2 = new Rect();
            int[] location2 = new int[2];
            Var.activeBlocks.get(i).getLocationOnScreen(location2);
            
            loc2.left = location2[0];
            loc2.right = loc2.left + Var.activeBlocks.get(i).getWidth();
            
            if(ev.getX()<loc2.right){

            	Var.indexLink=Var.activeBlocks.get(i).order;
				Var.indexModule=Var.activeBlocks.get(i).order;
				
            	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
            	BuildUiActivity.worksheet.removeView(DragLayer.link);

				BuildUiActivity.worksheet.addView(DragLayer.link,(Var.activeBlocks.get(i).order*2)-1);
				BuildUiActivity.worksheet.addView(DragLayer.dropzone,(Var.activeBlocks.get(i).order*2));
				
				break;
        	}
    	}

    	Rect loc1 = new Rect();
        int[] location1 = new int[2];
        Var.activeBlocks.get(0).getLocationOnScreen(location1);
        
        loc1.left = location1[0];
        loc1.right = loc1.left + Var.activeBlocks.get(0).getWidth();
        
        Rect loc = new Rect();
        int[] location = new int[2];
        Var.activeBlocks.get(Var.activeBlocks.size()-1).getLocationOnScreen(location);
        loc.left = location[0];
        
        if(ev.getX()<loc1.right){
        	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
        	BuildUiActivity.worksheet.removeView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.dropzone,0);
        	BuildUiActivity.worksheet.addView(DragLayer.link,1);
    		
        	Var.indexLink=1;
    		Var.indexModule=0;
    	}

        else if(ev.getX()>loc.left){
        	BuildUiActivity.worksheet.removeView(DragLayer.dropzone);
        	BuildUiActivity.worksheet.removeView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.link);
        	BuildUiActivity.worksheet.addView(DragLayer.dropzone);
    		
    		Var.indexModule=Var.activeBlocks.size();
    		Var.indexLink=Var.activeBlocks.size();
    	}

    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        return mMoveTarget != null && mMoveTarget.dispatchUnhandledMove(focused, direction);
    }
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mDragging) {
            return false;
        }

        final int action = ev.getAction();
        final int screenX = clamp((int)ev.getRawX(), 0, mDisplayMetrics.widthPixels);
        final int screenY = clamp((int)ev.getRawY(), 0, mDisplayMetrics.heightPixels);

        switch (action) {
        case MotionEvent.ACTION_DOWN:
            mMotionDownX = screenX; mMotionDownY = screenY;
            break;
            
        case MotionEvent.ACTION_MOVE:
        	
            if(ev.getX()<=100)BuildUiActivity.scrollWorksheet.scrollBy(-30,0);
            if(ev.getX()>Var.size.x-100)BuildUiActivity.scrollWorksheet.scrollBy(+30,0);
            Log.d("SUMBU Y",String.valueOf(ev.getY()));
            if(Var.activeBlocks.size()>=1 && Var.fromModul==true && ev.getY()>(Var.size.y/3) ) shifting(ev);
            if(Var.activeBlocks.size()>1 && Var.fromWorksheet==true && ev.getY()<3*(Var.size.y/4)) shiftingWsheet(ev);
            	
            mDragView.move((int)ev.getRawX(), (int)ev.getRawY());
            final int[] coordinates = mCoordinatesTemp;
            DropTarget dropTarget = findDropTarget(screenX, screenY, coordinates);
            if (dropTarget != null) {
                if (mLastDropTarget == dropTarget) {
                    dropTarget.onDragOver(mDragSource, coordinates[0], coordinates[1],
                        (int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
                } else {
                    if (mLastDropTarget != null) {
                        mLastDropTarget.onDragExit(mDragSource, coordinates[0], coordinates[1],
                            (int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
                    }
                    dropTarget.onDragEnter(mDragSource, coordinates[0], coordinates[1],
                        (int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
                }
            } else {
                if (mLastDropTarget != null) {
                    mLastDropTarget.onDragExit(mDragSource, coordinates[0], coordinates[1],
                        (int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
                }
            }
            mLastDropTarget = dropTarget;
            break;
        case MotionEvent.ACTION_UP:
            if (mDragging) {
                drop(screenX, screenY);
            }
            endDrag();

            break;
        case MotionEvent.ACTION_CANCEL:
            cancelDrag();
        }
        return true;
    }

    
    private boolean drop(float x, float y) {

        final int[] coordinates = mCoordinatesTemp;
        DropTarget dropTarget = findDropTarget((int) x, (int) y, coordinates);

        if (dropTarget != null) {
            dropTarget.onDragExit(mDragSource, coordinates[0], coordinates[1],
                    (int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
            
            if (dropTarget.acceptDrop(mDragSource, coordinates[0], coordinates[1],(int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo)) 
            {
                dropTarget.onDrop(mDragSource, coordinates[0], coordinates[1],(int) mTouchOffsetX, (int) mTouchOffsetY, mDragView, mDragInfo);
                mDragSource.onDropCompleted((View) dropTarget, true);
                return true;
            } else {
                mDragSource.onDropCompleted((View) dropTarget, false);
                return true;
            }
        }
        else {
	        Var.onSuccess=false;
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
        return false;
    }


    public static void hideSoftKey(){
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(mWindowToken, 0);
    }
    
    
    private DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        final Rect r = mRectTemp;

        final ArrayList<DropTarget> dropTargets = mDropTargets;
        final int count = dropTargets.size();
        for (int i=count-1; i>=0; i--) {
            final DropTarget target = dropTargets.get(i);
            target.getHitRect(r);
            target.getLocationOnScreen(dropCoordinates);
            r.offset(dropCoordinates[0] - target.getLeft(), dropCoordinates[1] - target.getTop());
            if (r.contains(x, y)) {
                dropCoordinates[0] = x - dropCoordinates[0];
                dropCoordinates[1] = y - dropCoordinates[1];
                Log.d ("DeleteZone", "finddroptarget return target");
                return target;
            }
        }
        return null;
    }
    private void recordScreenSize() {
        ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(mDisplayMetrics);
    }
    private static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        } else if (val >= max) {
            return max - 1;
        } else {
            return val;
        }
    }

    public void setWindowToken(IBinder token) {
        mWindowToken = token;
    }
    public void setDragListener(DragListener l) {
        mListener = l;
    }
    public void removeDragListener(DragListener l) {
        mListener = null;
    }
    public void addDropTarget(DropTarget target) {
        mDropTargets.add(target);
    }
    public void removeDropTarget(DropTarget target) {
        mDropTargets.remove(target);
    }
    public void removeAllDropTargets () {
        mDropTargets = new ArrayList<DropTarget> ();
    }

}
