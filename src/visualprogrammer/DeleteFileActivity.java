package visualprogrammer;

import java.io.File;
import java.util.ArrayList;
import com.example.visualprogrammer.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DeleteFileActivity extends Activity{
	
	private ListView listview;
	private ArrayAdapter<String> adapter;
	final ArrayList<String> list = new ArrayList<String>();
	
	//@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	//@SuppressLint("NewApi")
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
		    adapter = new ArrayAdapter<String>(this,R.layout.delete_layout, R.id.textView_delete, list);
		    
		    listview.setAdapter(adapter);
		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
					public void onItemClick(AdapterView<?> parent, final View view,
					          int position, long id) {
					        final String item = (String) parent.getItemAtPosition(position);
					        
					        Files f=new Files();
					    	f.deleteFile(item);
					    	
					    	list.remove(item);
			                adapter.notifyDataSetChanged();
					    	/*
					        AlertDialog.Builder newDialog = new AlertDialog.Builder(getApplicationContext());
							newDialog.setTitle("Hapus file");
							newDialog.setMessage("Apakah anda akan menghapus "+item+" ?");
							newDialog.setPositiveButton("Iya", new DialogInterface.OnClickListener(){
							    public void onClick(DialogInterface dialog, int which){
							    	Files f=new Files();
							    	f.deleteFile(item);
							    	
							    	list.remove(item);
					                adapter.notifyDataSetChanged();
							    	
							        dialog.dismiss();
							    }
							});
							newDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener(){
							    public void onClick(DialogInterface dialog, int which){
							        dialog.cancel();
							    }
							});
							newDialog.show();*/
					        
					      }
		    });
		}
		catch(Exception e){
			finish();
			Toast.makeText(getApplicationContext(), "File kosong, silahkan buat file baru", Toast.LENGTH_SHORT).show();
		}
	}
}
