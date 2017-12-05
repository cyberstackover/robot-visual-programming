package interfaces;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

public class Texts extends TextView{

	public Texts(Context context) {
		super(context);
	}

	public Texts(Context context, String s) {
		super(context);
		super.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
		super.setTextColor(Color.BLACK);
		super.setText(s);
	}

}
