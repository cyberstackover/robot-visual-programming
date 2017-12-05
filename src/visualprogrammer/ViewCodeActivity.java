package visualprogrammer;

import com.example.visualprogrammer.R;

import android.app.Activity;
import android.os.*;
import android.view.WindowManager;
import android.widget.TextView;

public class ViewCodeActivity extends Activity{
	
	private String blockdata;
	private TextView teks;
	private String [] data;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_code_layout);
		teks=(TextView)findViewById(R.id.textView1);
		
		blockdata=getIntent().getStringExtra("datablock");
		data=blockdata.split(";");
		
		if(!blockdata.equalsIgnoreCase("")){
			teks.setText(data[0]+";\n");
			for(int i=1; i<data.length; i++){
				teks.append(data[i]+";\n");
			}
		}
	}
}
