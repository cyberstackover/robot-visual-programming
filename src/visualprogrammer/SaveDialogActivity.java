package visualprogrammer;

import interfaces.Modules;

import com.example.visualprogrammer.R;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.*;

public class SaveDialogActivity extends Activity{
	EditText input;
	Button cancel, save;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.name_layout);
		
		input = (EditText)findViewById(R.id.name);
		cancel = (Button)findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		save = (Button)findViewById(R.id.save);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Var.fileName=String.valueOf(input.getText());

				if(Var.isNew==true){
					Var.activeBlocks.clear();
					Var.links.clear();
					BuildUiActivity.bottom_properties.removeAllViews();
					BuildUiActivity.bottom_properties1.removeAllViews();
					BuildUiActivity.worksheet.removeAllViews();
					Var.isNew=false;
				}
				
				Files f= new Files();
				Modules m=new Modules(getApplicationContext());
				String temp = "";
				for(int i=0; i<Var.activeBlocks.size(); i++){
					temp+=m.extractDataSave(Var.activeBlocks.get(i))+";";
				}
				f.saveData(temp, Var.dataPath+Var.fileName+".txt");
				f.saveData(temp, Var.outputPath+Var.fileName+".hex");
				Toast.makeText(getApplicationContext(), "Data telah tersimpan", Toast.LENGTH_SHORT).show();
				Var.isSaved=true;
				
				if(Var.isExiting==true){
					finish();
				}
				
				Handler handler=new Handler();
				handler.postDelayed(new Runnable() {
		            public void run() {
		            	BuildUiActivity.menuBar.setVisibility(View.GONE);
		            	BuildUiActivity.homeParent.setVisibility(View.GONE);
		            	BuildUiActivity.modulParent.setVisibility(View.VISIBLE);
		            }
		        }, 100L);
				finish();

			}
		});
	}

}
