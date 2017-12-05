package dragmanagements;

import visualprogrammer.Var;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class DeleteZone extends ImageView
    implements DropTarget
{
public DeleteZone (Context context) {
   super  (context);
}
public DeleteZone (Context context, AttributeSet attrs) {
	super (context, attrs);
}
public DeleteZone (Context context, AttributeSet attrs, int style) 
{
	super (context, attrs, style);
}
private DragController mDragController;
public DragController getDragController ()
{
   return mDragController;
}
public void setDragController (DragController newValue)
{
   mDragController = newValue;
   Log.d ("DeleteZone", "set drag controller");
}

public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo)
{
    Toast.makeText(getContext(), "Blok telah dihapus", Toast.LENGTH_SHORT).show();
}
    
public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo)
{
	//Var.onDelete=true;
    setImageLevel (2);
}
public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo)
{}
public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo)
{
	Var.onDelete=false;
    setImageLevel (1);
    
}
public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo)
{
	Var.onDelete=true;
    return isEnabled ();
}
public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo, Rect recycle)
{
	Log.d ("DeleteZone", "estimate drop location");
    return null;
}
public void setup (DragController controller)
{
    mDragController = controller;
    Log.d ("DeleteZone", "setup");
    if (controller != null) {
       controller.addDropTarget (this);
       Log.d ("DeleteZone", "setup.add drop target");
    }
}
}
