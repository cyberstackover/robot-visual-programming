package visualprogrammer;

import interfaces.Image;
import interfaces.Modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.example.visualprogrammer.R;

import dragmanagements.DragLayer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class OpenFileActivity extends Activity{
	
	private ListView listview;
	final ArrayList<String> list = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		listview=(ListView)findViewById(R.id.list_item);
		
		try{
			File dir = new File(Var.dataPath);
			File[] filelist = dir.listFiles();
			
			if(filelist.length==0){
				finish();
				Toast.makeText(getApplicationContext(), "File kosong, silahkan buat file baru", Toast.LENGTH_SHORT).show();
			}
			
			String[] name = new String[filelist.length];
			for (int i = 0; i < name.length; i++) {
				name[i] = filelist[i].getName().replace(".txt", "");
			}
			
		    for (int i = 0; i < name.length; ++i) {
		      list.add(0,name[i]);
		    }
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.open_layout, R.id.textView_open, list);
		    
		    //final StableArrayAdapter adapter = new StableArrayAdapter(this,R.layout.open_layout,R.id.textView_open, list);
		    listview.setAdapter(adapter);
		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
					public void onItemClick(AdapterView<?> parent, final View view,
					          int position, long id) {
					        final String item = (String) parent.getItemAtPosition(position);
					        
					        String saveData = "";

					       
					        try{
					            FileReader fstream = new FileReader(Var.dataPath+item+".txt");
					            BufferedReader read = new BufferedReader(fstream);
					            saveData = read.readLine();
					            read.close();
					        } catch (Exception e) {}
					        

					        BuildUiActivity.hideProperties();
					        BuildUiActivity.worksheet.removeAllViews();
					        Var.activeBlocks.clear();
					        Var.links.clear();
					        
					        BuildUiActivity.mDragLayer.generateActiveBlocks(saveData);
					        Var.fileName=item;
					        finish();
					      }
		    });
		}
		catch(Exception e){
			finish();
			Toast.makeText(getApplicationContext(), "File kosong, silahkan buat file baru", Toast.LENGTH_SHORT).show();
		}
	  }
}
