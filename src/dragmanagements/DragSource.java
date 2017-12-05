package dragmanagements;

import android.view.View;

public interface DragSource {

    boolean allowDrag ();
    void setDragController(DragController dragger);
    void onDropCompleted (View target, boolean success);
}
