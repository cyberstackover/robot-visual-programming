package webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;

import visualprogrammer.TouchListener;
import visualprogrammer.Var;

import com.example.visualprogrammer.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class SyncActivity extends Activity{
	
	public static ImageView upload, download;
	UserFunctions userfunctions;
	ProgressDialog progressDialog;
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync_layout);
		upload=(ImageView) findViewById(R.id.upload);
		download=(ImageView) findViewById(R.id.download);
		
		userfunctions= new UserFunctions();
		upload.setOnTouchListener(new TouchListener());
		download.setOnTouchListener(new TouchListener());
		
		upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UploadAsync AsyncProcess = new UploadAsync(SyncActivity.this);
				AsyncProcess.execute("");
			}});
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadAsync AsyncProcess = new DownloadAsync(SyncActivity.this);
				AsyncProcess.execute("");
			}});
		}
	

	private class UploadAsync extends AsyncTask<String, Void, String>{
    	Context mContext = null;
		
		Exception exception = null;
		Boolean isSuccess = false;
    	
		UploadAsync(Context context){
			mContext = context;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setMessage("Mengunggah file...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

				try{
					File dir = new File(Var.dataPath);
					File[] filelist = dir.listFiles();
					JSONObject jsonObject = null;
					
					if(filelist.length==0){
						finish();
						Toast.makeText(getApplicationContext(), "File kosong, silahkan buat file baru", Toast.LENGTH_SHORT).show();
					}
					
					String[] name = new String[filelist.length];
					for (int i = 0; i < name.length; i++) {
						name[i] = filelist[i].getName().replace(".txt", "");
					}
					
				    for (int i = 0; i < name.length; ++i) {
				    	
				        String saveData = "";
				        FileReader fstream = new FileReader(Var.dataPath+name[i]+".txt");
				        BufferedReader read = new BufferedReader(fstream);
				        saveData = read.readLine();
				        read.close();
				        jsonObject = userfunctions.kirimData(CloudActivity.id_user, name[i], saveData);
				    }
					
					try { 
		                if (jsonObject.getString("success") != null) { 
		                    String res = jsonObject.getString("success"); 
		                    if(Integer.parseInt(res) == 1){ 
		                        isSuccess = true;
		                    } 
		                } 
		            } catch (JSONException ex) { 
		                ex.printStackTrace(); 
		            } 
					
				}catch (Exception e){
					Log.e("ACS", "Error:", e);
					exception = e;
				}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			if(exception != null){
				progressDialog.dismiss();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Gagal menyambung ke server", Toast.LENGTH_LONG).show();
				
			}
			else if(isSuccess == true){
				progressDialog.dismiss();
				setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Data telah diunggah", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	private class DownloadAsync extends AsyncTask<String, Void, String>{
    	Context mContext = null;
		
		Exception exception = null;
		Boolean isSuccess = false;
    	
		DownloadAsync(Context context){
			mContext = context;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setMessage("Mengunduh file...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
					userfunctions.DownloadData(String.valueOf(CloudActivity.id_user));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		  
		  return null;
		}

		@Override
		protected void onPostExecute(String result) {

			if(exception != null){
				progressDialog.dismiss();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Gagal menyambung ke server", Toast.LENGTH_LONG).show();
				
			}
			else {
				progressDialog.dismiss();
				setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Data telah diunduh", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}


}


