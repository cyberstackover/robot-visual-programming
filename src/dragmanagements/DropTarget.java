package dragmanagements;

import android.graphics.Rect;

public interface DropTarget {
    void onDrop(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo);
    void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo);
    void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo);
    void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo);
    boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo);
    Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset,DragView dragView, Object dragInfo, Rect recycle);
    void getHitRect(Rect outRect);
    void getLocationOnScreen(int[] loc);
    int getLeft();
    int getTop();
}
