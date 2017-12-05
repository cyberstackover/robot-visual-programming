package webservice;


import org.json.JSONException;
import org.json.JSONObject;

import visualprogrammer.BuildUiActivity;

import com.example.visualprogrammer.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CloudActivity extends Activity {

	String pwd, id;
	EditText usernamelogin,passwordlogin;
	EditText usernameregister,passwordregister,namaregister;
	Button login,register;
	ProgressDialog progressDialog;
	public static int id_user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_register_layout);
		
		usernameregister=(EditText) findViewById(R.id.register_username);
        passwordregister=(EditText) findViewById(R.id.register_password);
        namaregister=(EditText) findViewById(R.id.register_nama);
        register=(Button) findViewById(R.id.register);
        register.setOnTouchListener(new ButtonHighlights(register));
        
		usernamelogin=(EditText) findViewById(R.id.login_username);
        passwordlogin=(EditText) findViewById(R.id.login_password);
        login=(Button) findViewById(R.id.login);
        
        login.setOnTouchListener(new ButtonHighlights(login));
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
				LoginAsync loginAsyncProcess = new LoginAsync(CloudActivity.this, usernamelogin.getText().toString(), passwordlogin.getText().toString());
    			loginAsyncProcess.execute("");
    			login.setEnabled(false);
			}
		});
        
        register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
				RegisterAsync registerAsyncProcess = new RegisterAsync(CloudActivity.this, namaregister.getText().toString(),usernameregister.getText().toString(), passwordregister.getText().toString());
    			registerAsyncProcess.execute("");
    			register.setEnabled(false);
			}
		});
	}
	
	public static int getScreenOrientation(Activity activity){
    	Display getOrient = activity.getWindowManager().getDefaultDisplay();
    	int orientation = Configuration.ORIENTATION_UNDEFINED;
    	if(getOrient.getWidth() == getOrient.getHeight()){
    		orientation = Configuration.ORIENTATION_SQUARE;
    	}
    	else if(getOrient.getWidth() < getOrient.getHeight()){
    		orientation = Configuration.ORIENTATION_PORTRAIT;
    	}
    	else{
    		orientation = Configuration.ORIENTATION_LANDSCAPE;
    	}
    	return orientation;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	
	public class ButtonHighlights implements OnTouchListener {

		private Button button;
		

		public ButtonHighlights(final Button button) {
		  super();
		  this.button= button;
		}
		
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					button.setBackgroundColor(Color.argb(155, 185, 185, 185));
			    } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			    	button.setBackgroundColor(Color.argb(255, 42, 152, 219)); 
			    }
			    return false;
			  }
		}
	
    private class RegisterAsync extends AsyncTask<String, Void, String>{
    	Context mContext = null;
    	String nama;
		String dataId = "";
		String dataPassword = "";
		
		Exception exception = null;
		Boolean isSuccess = false;
    	
		RegisterAsync(Context context, String name, String stringId, String stringPassword){
			mContext = context;
			nama=name;
			dataId = stringId;
			dataPassword = stringPassword;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setMessage("Register new user...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try{
				UserFunctions userFunctions = new UserFunctions();
				JSONObject jsonObject = userFunctions.registerUser(nama, dataId, dataPassword);
				try { 
	                if (jsonObject.getString("success") != null) {
	                    int res = jsonObject.getInt("success"); 
	                    if(res == 1){ 
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
			login.setEnabled(true);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
			if(exception != null){
				progressDialog.dismiss();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Gagal menyambung ke server", Toast.LENGTH_LONG).show();
			}
			else if(isSuccess == true){
				//Intent i = new Intent(getApplicationContext(), SyncActivity.class);
				//startActivity(i);
				Toast.makeText(mContext, "Registrasi berhasil silahkan login terlebih dahulu", Toast.LENGTH_LONG).show();
				finish();
			}
			else if(isSuccess == false){
				progressDialog.dismiss();
				setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Registrasi gagal", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	
    private class LoginAsync extends AsyncTask<String, Void, String>{
    	Context mContext = null;
		String dataId = "";
		int ids;
		String dataPassword = "";
		
		Exception exception = null;
		Boolean isSuccess = false;
    	
		LoginAsync(Context context, String stringId, String stringPassword){
			mContext = context;
			dataId = stringId;
			dataPassword = stringPassword;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setMessage("Logging in...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try{
				UserFunctions userFunctions = new UserFunctions();
				JSONObject jsonObject = userFunctions.loginUser(dataId, dataPassword);
				try { 
	                if (jsonObject.getString("success") != null) {
	                    int res = jsonObject.getInt("success"); 
	                    CloudActivity.id_user= jsonObject.getInt("id_user");
	                    if(res == 1){ 
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
			login.setEnabled(true);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
			if(exception != null){
				progressDialog.dismiss();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "Gagal menyambung ke server", Toast.LENGTH_LONG).show();
			}
			else if(isSuccess == true){
				Intent i = new Intent(getApplicationContext(), SyncActivity.class);
				startActivity(i);
				Toast.makeText(mContext, "Login berhasil", Toast.LENGTH_LONG).show();
				finish();
			}
			else if(isSuccess == false){
				progressDialog.dismiss();
				setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Toast.makeText(mContext, "username atau Password yang Anda masukkan salah.", Toast.LENGTH_LONG).show();
			}
		}
	}

}
