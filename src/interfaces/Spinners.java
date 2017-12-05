package interfaces;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinners extends Spinner{

	public Spinners(Context context, String [] a) {
		super(context);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		super.setAdapter(spinnerArrayAdapter);
		
	}

}
