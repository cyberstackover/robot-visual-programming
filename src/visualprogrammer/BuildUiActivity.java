package visualprogrammer;


import joystick.JoystickActivity;
import webservice.CloudActivity;
import interfaces.Layout;
import interfaces.Modules;
import interfaces.Image;
import interfaces.HorizontalScroller;
import interfaces.Texts;

import com.example.visualprogrammer.R;

import dragmanagements.DragController;
import dragmanagements.DragLayer;
import dragmanagements.DragSource;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class BuildUiActivity extends Activity 
implements View.OnLongClickListener, View.OnClickListener,View.OnTouchListener {
	
	
	TextView t;
	public Texts stat;
	
	public static Spinner spinner, spinner1;
	public static boolean isProperties=false;
	
	public static ImageView exit;
	public static Layout optionBar, menuBar, modulBar, modulParent, homeParent, appName;
	public static HorizontalScroller scrollModule;
	public static HorizontalScrollView scrollWorksheet,scrollbp;
	public static LinearLayout parent, worksheet, bottom_properties1, bottom_properties;
	public static Modules forward,reverse,turnleft,turnright,lcd,delay,sound,gripper,wfollower,loop,lfollower;
	public static Image left1, right1;
	public static Image home, menu, modul, about;
	public static Image newfile, openfile, save,delete,info,connectifity,deploy,sync;
	public static Display display;
	public static Point size;
	
	public static DragController mDragController;   
	public static DragLayer mDragLayer;             
	private boolean mLongClickStartsDrag = false;
	public static View del; 
	public static Modules logo;
	public static LinearLayout layout;
	public static Context context;
	public static Texts blockno;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inisialisasi();
		makeLayouts();
		attachOption();
		attachMenu();
		attachModul();
		attachHome();
		
		bottom_properties.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bottom_properties.removeAllViews();
				bottom_properties1.removeAllViews();
				Modules m=new Modules(getApplicationContext());
				m.unsetActiveBlock();
			}
		});
		bottom_properties1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bottom_properties.removeAllViews();
				bottom_properties1.removeAllViews();
				Modules m=new Modules(getApplicationContext());
				m.unsetActiveBlock();
			}
		});
     }
	
	public static void openTrash(){
		BuildUiActivity.del.setScaleX((float) 1);
		BuildUiActivity.del.setScaleY((float) 1);
	    BuildUiActivity.del.setVisibility(View.VISIBLE);
	}
	
	public static void hideProperties(){
	    BuildUiActivity.bottom_properties.removeAllViews();
		BuildUiActivity.bottom_properties1.removeAllViews();
	}
	
	@SuppressLint("NewApi")
	void inisialisasi(){
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main_layout); 
        
        Var.display = getWindowManager().getDefaultDisplay();
		Var.size = new Point();
		Var.display.getSize(Var.size);
		
		context=getApplicationContext();
		
		del = findViewById(R.id.delete_zone_view);
	    del.setVisibility(View.GONE);
		mDragController = new DragController(this);
	    mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
	    mDragLayer.setDragController (mDragController);

	    mDragController.setDragListener (mDragLayer);
        parent= (LinearLayout) findViewById(R.id.parentlayout);
        parent.setGravity(Gravity.CENTER_HORIZONTAL);
       
        Var.bluetooth_adapter = BluetoothAdapter.getDefaultAdapter();
        if (Var.bluetooth_adapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        connect();

	}
	
	public void makeLayouts(){
		
		optionBar = new Layout(getApplicationContext(),LinearLayout.HORIZONTAL, Gravity.CENTER,LayoutParams.MATCH_PARENT,Var.size.y/8);
		menuBar = new Layout(getApplicationContext(),LinearLayout.HORIZONTAL, Gravity.CENTER,LayoutParams.MATCH_PARENT,Var.size.y/6);
		modulBar = new Layout(getApplicationContext(),LinearLayout.HORIZONTAL, Gravity.CENTER_VERTICAL,LayoutParams.MATCH_PARENT,Var.size.y/6);
		modulParent = new Layout(getApplicationContext(),LinearLayout.HORIZONTAL, Gravity.CENTER_HORIZONTAL,LayoutParams.MATCH_PARENT,Var.size.y/6);
		scrollModule=new HorizontalScroller(getApplicationContext(), (Var.size.y/7)*7);
		scrollWorksheet=new HorizontalScroller(getApplicationContext(), LayoutParams.WRAP_CONTENT);
        
		parent.addView(optionBar);
        parent.addView(menuBar);

        left1= new Image(getApplicationContext(),Var.LEFT1_ID, R.drawable.left, Var.size.y/17, Var.size.y/14,Var.size.y/6,Var.size.y/25,Var.size.y/35,Var.size.y/25) ;
        right1= new Image(getApplicationContext(),Var.RIGHT1_ID, R.drawable.right, Var.size.y/17, Var.size.y/14,Var.size.y/35,Var.size.y/25,Var.size.y/6,Var.size.y/25) ;
        left1.setOnClickListener(new ClickListener());
        right1.setOnClickListener(new ClickListener());
        left1.setOnTouchListener(new TouchListener());
        right1.setOnTouchListener(new TouchListener());
        
        scrollModule.addView(modulBar);
        
        modulParent.addView(left1);
        modulParent.addView(scrollModule);
        modulParent.addView(right1);
        
        parent.addView(modulParent);
        
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,Var.size.y/6);
        //layoutParams.setMargins(25, 0, 25, 0);
		worksheet= new LinearLayout(getApplicationContext());
		worksheet.setGravity(Gravity.CENTER_VERTICAL);
		worksheet.setLayoutParams(layoutParams);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		modulParent.setVisibility(View.GONE);
        menuBar.setVisibility(View.GONE);
        
	}
	public void attachHome(){
		homeParent=new Layout(getApplicationContext(),LinearLayout.HORIZONTAL,Gravity.CENTER_VERTICAL,LayoutParams.MATCH_PARENT,Var.size.y/6);
		Image logoPens= new Image(getApplicationContext(),999, R.drawable.roboviper1, Var.size.y/8, Var.size.y/8, 15,0,0,0) ;
		Image name= new Image(getApplicationContext(),999, R.drawable.ic_title, 10,0,15,0) ;
		name.setScaleType(ScaleType.FIT_START);
		homeParent.addView(logoPens);
		name.setScaleType(ScaleType.FIT_XY);
		
		appName= new Layout(getApplicationContext(),LinearLayout.VERTICAL,Gravity.CENTER_VERTICAL,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		appName.addView(name);
		
		stat= new Texts(getApplicationContext(),"Tidak ada perangkat tersambung");
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    llp.setMargins(10, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
	    stat.setLayoutParams(llp);
		stat.setTextColor(Color.WHITE);
		stat.setTypeface(Typeface.DEFAULT_BOLD);
		stat.setGravity(Gravity.TOP);
		appName.addView(stat);
		homeParent.addView(appName);
		
		parent.addView(homeParent);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(25, 0, 25, 0);
		scrollWorksheet=new HorizontalScrollView(getApplicationContext());
		scrollWorksheet.setBackgroundColor(Color.rgb(255, 255,255));
		scrollWorksheet.setHorizontalScrollBarEnabled(false);
		scrollWorksheet.setLayoutParams(layoutParams);
		
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,Var.size.y/9);
		layoutParams1.setMargins(25, 0, 25, 0);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////		
		LinearLayout space= new LinearLayout(getApplicationContext());
		space.setBackgroundColor(Color.rgb(255, 255, 255));
		space.setLayoutParams(layoutParams1);
		
		bottom_properties1 = new LinearLayout(getApplicationContext());
		bottom_properties1.setBackgroundColor(Color.rgb(255, 255, 255));
		bottom_properties1.setGravity(Gravity.CENTER);
		bottom_properties1.setLayoutParams(layoutParams1);
		
		bottom_properties= new LinearLayout(getApplicationContext());
		bottom_properties.setGravity(Gravity.CENTER);
		bottom_properties.setLayoutParams(layoutParams1);
		
		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,Var.size.y/6);
		LinearLayout temp= new LinearLayout(this);
		temp.setLayoutParams(lp1);
		
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		LinearLayout temp1= new LinearLayout(this);
		temp1.setOrientation(LinearLayout.VERTICAL);
		temp1.setLayoutParams(lp2);
		
		LinearLayout.LayoutParams parms= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		parms.setMargins(25, 15, 15, 15);
		blockno= new Texts(getApplicationContext(),"");
		blockno.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Large);
		blockno.setTextColor(Color.rgb(70, 70, 70));
		temp.addView(blockno,parms);
		
		parent.addView(temp);
		temp1.addView(worksheet);
		temp1.addView(space);

 
		scrollWorksheet.addView(temp1);
		parent.addView(scrollWorksheet);
		parent.addView(bottom_properties);
	    parent.addView(bottom_properties1);
	    
	}
	
	public void attachOption(){
		
		home= new Image(getApplicationContext(),Var.HOME_ID, R.drawable.ic_home, Var.size.y/6, LayoutParams.WRAP_CONTENT, 5,5,5,5) ;
		menu= new Image(getApplicationContext(),Var.MENU_ID, R.drawable.ic_menu, Var.size.y/6, LayoutParams.WRAP_CONTENT, 5,5,5,5) ;
		modul= new Image(getApplicationContext(),Var.MODUL_ID, R.drawable.ic_block, Var.size.y/6, LayoutParams.WRAP_CONTENT, 5,5,5,5) ;
		about= new Image(getApplicationContext(),Var.ABOUT_ID, R.drawable.ic_pad, Var.size.y/6, LayoutParams.WRAP_CONTENT, 5,5,5,5) ;
		exit=(ImageView)findViewById(R.id.exit);
		exit.setId(Var.EXIT_ID);
		
		home.setOnClickListener(new ClickListener());
		menu.setOnClickListener(new ClickListener());
		modul.setOnClickListener(new ClickListener());
		about.setOnClickListener(new ClickListener());
		home.setOnTouchListener(new TouchListener());
		menu.setOnTouchListener(new TouchListener());
		modul.setOnTouchListener(new TouchListener());
		about.setOnTouchListener(new TouchListener());
		about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://roboviper.besaba.com"));
				//startActivity(browserIntent);
				Intent intent = new Intent(getApplicationContext(), JoystickActivity.class);
				startActivity(intent);
			}
		});
		exit.setOnTouchListener(new TouchListener());
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(Var.fileName.equalsIgnoreCase("") && Var.activeBlocks.size()==0)
					createDialogExit();
				else {
					if(Var.isSaved==false){
						createSaveDialog();
						
					}
					else{
						createDialogExit();
					}
				}
			}
		});
		
		optionBar.addView(home);
		optionBar.addView(menu);
		optionBar.addView(modul);
		optionBar.addView(about);
		
	}
	private String decimalToHex(int a) {
        String h = Integer.toHexString(a);
        if (h.length() < 2) {
            h = "0" + h;
        }
        return h;
    }
	private String asciiData(char c){
        int a=(int)c;
        if(a>=0 && (a+40) < 255){
            return decimalToHex(a);
        }
        return "";
    }
	public static byte[] hexToByte(String s) throws Exception {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}
		Log.d("MonitorActivity",s);
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
				Log.d("MonitorActivity",baKeyword[i]+",");
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return baKeyword;
	}
	
	public String compileData(String data){

		String[] programData=data.split(";");
    	String hexData="";
		
	    for(int i=0; i<programData.length; i++){
	    	String[] blockData;
	    	blockData=programData[i].split(",");

	    	Modules m=new Modules(getApplicationContext());
	    	
	    	if(blockData[0].equalsIgnoreCase(m.fwd1)){
	    		hexData+="ff"+"000000"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.fwd2)){
	    		hexData+="ff"+"000001"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.wfollower1)){
	    		if(blockData[1].equals(m.wfgaris))
	    			hexData+="ff"+"040100"+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[3]))+decimalToHex(Integer.parseInt(blockData[4]));
	    		else 
	    			hexData+="ff"+"040101"+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[3]))+decimalToHex(Integer.parseInt(blockData[4]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.wfollower2)){
	    		if(blockData[1].equals(m.wfgaris))
	    			hexData+="ff"+"040000"+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[3]))+decimalToHex(Integer.parseInt(blockData[4]));
	    		else 
	    			hexData+="ff"+"040001"+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[3]))+decimalToHex(Integer.parseInt(blockData[4]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.lfollower1)){
	    		hexData+="ff"+"0502"+blockData[1]+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.lfollower2)){
	    		hexData+="ff"+"0500"+blockData[1]+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.lfollower3)){
	    		hexData+="ff"+"0501"+blockData[1]+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.reverse1)){
	    		hexData+="ff"+"000100"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.reverse2)){
	    		hexData+="ff"+"000101"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.tright1)){
	    		hexData+="ff"+"000200"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.tright2)){
	    		hexData+="ff"+"000201"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.tleft1)){
	    		hexData+="ff"+"000300"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.tleft2)){
	    		hexData+="ff"+"000301"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[2]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.lcd)){
	    		int num=Integer.parseInt(blockData[1]);
	    		String text=blockData[2];
	    		
	    		hexData+="ff"+"08"+decimalToHex(Integer.parseInt(blockData[1]));
	    		for (int x = 0; x < num; x++) {
                    hexData+= asciiData(text.charAt(x));
                }
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.sMonostable)){
	    		hexData+="ff"+"07"+"00"+decimalToHex(Integer.parseInt(blockData[1]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.sAstable)){
	    		hexData+="ff"+"07"+"01"+decimalToHex(Integer.parseInt(blockData[1]))+decimalToHex(Integer.parseInt(blockData[2]))+decimalToHex(Integer.parseInt(blockData[3]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.sMario)){
	    		hexData+="ff"+"07"+"02";
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.delay)){
	    		hexData+="ff"+"09"+decimalToHex(Integer.parseInt(blockData[1]));
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.gripper1)){
	    		hexData+="ff"+"06"+"02"+"00";
	    	}
	    	else if(blockData[0].equalsIgnoreCase(m.gripper2)){
	    		hexData+="ff"+"06"+"02"+"01";
	    	}
	    }
	    return hexData;
	}
	
	public void attachMenu(){
		newfile= new Image(getApplicationContext(),Var.NEW_ID, R.drawable.ic_new_file, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		openfile= new Image(getApplicationContext(),Var.OPEN_ID, R.drawable.ic_open_file, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		save= new Image(getApplicationContext(),Var.SAVE_ID, R.drawable.ic_save_file, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		delete= new Image(getApplicationContext(),Var.DELETE_ID, R.drawable.ic_delete_file, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		info= new Image(getApplicationContext(),Var.INFO_ID, R.drawable.ic_view_code, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		connectifity= new Image(getApplicationContext(),Var.CONNECT_ID, R.drawable.ic_bluetooth, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		deploy= new Image(getApplicationContext(),Var.DEPLOY_ID, R.drawable.ic_deploy, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		sync= new Image(getApplicationContext(),Var.SYNC_ID, R.drawable.ic_sync, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		
		sync.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), CloudActivity.class);
				startActivity(intent);
			}
		});
		sync.setOnTouchListener(new TouchListener());
		connectifity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connect();
			}
		});
		connectifity.setOnTouchListener(new TouchListener());
		newfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Var.isNew=true;
				Intent intent = new Intent(getApplicationContext(), SaveDialogActivity.class);
				startActivity(intent);
				
			}
		});
		newfile.setOnTouchListener(new TouchListener());
		openfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), OpenFileActivity.class);
				startActivity(intent);
			}
		});
		openfile.setOnTouchListener(new TouchListener());
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(!Var.fileName.equalsIgnoreCase("")){
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
				}
				else {
					Intent intent = new Intent(getApplicationContext(), SaveDialogActivity.class);
					startActivity(intent);
					//Toast.makeText(getApplicationContext(), "Silahkan membuat file baru", Toast.LENGTH_SHORT).show(); 
				}
			}
		});
		save.setOnTouchListener(new TouchListener());
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), DeleteFileActivity.class);
				startActivity(intent);
			}
		});
		delete.setOnTouchListener(new TouchListener());
		info.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Modules m=new Modules(getApplicationContext());
				String temp = "";
				for(int i=0; i<Var.activeBlocks.size(); i++){
					temp+=m.extractDataSave(Var.activeBlocks.get(i))+";";
				}
				
				Intent intent = new Intent(getApplicationContext(), ViewCodeActivity.class);
				intent.putExtra("datablock", temp);
				startActivity(intent);
			}
		});
		info.setOnTouchListener(new TouchListener());
		deploy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				/*String hex="ff0805";
				byte bytes[] = hex.getBytes();
				
				//String a= Integer.toHexString(255)+Integer.toHexString(7)+Integer.toHexString(7)+Integer.toHexString(1)+Integer.toHexString(3)+Integer.toHexString(3)+Integer.toHexString(3);//stringToHex("255")+stringToHex("7")+stringToHex("0")+stringToHex("5");
				try {
					sendMessage1(hexToByte(hex));
					sendMessage("world");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Toast.makeText(getApplicationContext(), String.valueOf(hexToByte(hex)), Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//if(Var.fileName.equalsIgnoreCase("")){
					//Intent intent = new Intent(getApplicationContext(), SaveDialog.class);
					//startActivity(intent);
				//}
				//else{
				Files f= new Files();
				Modules m=new Modules(getApplicationContext());
				String temp = "";
				for(int i=0; i<Var.activeBlocks.size(); i++){
					temp+=m.extractDataSave(Var.activeBlocks.get(i))+";";
				}
				String data=compileData(temp);
				try {
					Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
					sendMessage1(hexToByte(data));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		deploy.setOnTouchListener(new TouchListener());
		
		menuBar.addView(newfile);
		menuBar.addView(openfile);
		menuBar.addView(save);
		menuBar.addView(delete);
		menuBar.addView(sync);
		menuBar.addView(info);
		menuBar.addView(connectifity);
		menuBar.addView(deploy);
	}
	
	public void attachModul(){
		forward= new Modules(getApplicationContext(),Var.FORWARD_ID, R.drawable.maju1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		reverse= new Modules(getApplicationContext(),Var.REVERSE_ID, R.drawable.mundur1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		turnleft= new Modules(getApplicationContext(),Var.TLEFT_ID, R.drawable.belki1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		turnright= new Modules(getApplicationContext(),Var.TRIGHT_ID, R.drawable.belka1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		lcd= new Modules(getApplicationContext(),Var.LCD_ID, R.drawable.lcdteks, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		delay= new Modules(getApplicationContext(),Var.DELAY_ID, R.drawable.waktu1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		sound= new Modules(getApplicationContext(),Var.SOUND_ID, R.drawable.suara1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		gripper= new Modules(getApplicationContext(),Var.GRIPPER_ID, R.drawable.gripper1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		loop= new Modules(getApplicationContext(),Var.LOOP_ID, R.drawable.loop1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		lfollower= new Modules(getApplicationContext(),Var.LFOLLOWER_ID, R.drawable.linefollower1, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
		wfollower= new Modules(getApplicationContext(),Var.WFOLLOWER_ID, R.drawable.wallfollower, Var.size.y/7, Var.size.y/7,0,0,0,0) ;
	
		forward.setOnTouchListener(this);
		reverse.setOnTouchListener(this);
		turnleft.setOnTouchListener(this);
		turnright.setOnTouchListener(this);
		lcd.setOnTouchListener(this);
		delay.setOnTouchListener(this);
		sound.setOnTouchListener(this);
		gripper.setOnTouchListener(this);
		loop.setOnTouchListener(this);
		lfollower.setOnTouchListener(this);
		wfollower.setOnTouchListener(this);
		
		forward.mEmpty = false;
		reverse.mEmpty = false;
		turnleft.mEmpty = false;
		turnright.mEmpty = false;
		lcd.mEmpty = false;
		delay.mEmpty = false;
		sound.mEmpty = false;
		gripper.mEmpty = false;
		loop.mEmpty=false;
		lfollower.mEmpty=false;
		wfollower.mEmpty=false;
		
		modulBar.addView(forward);
		modulBar.addView(reverse);
		modulBar.addView(turnleft);
		modulBar.addView(turnright);
		modulBar.addView(delay);
		modulBar.addView(gripper);
		modulBar.addView(sound);
		modulBar.addView(lcd);
		modulBar.addView(lfollower);
		modulBar.addView(wfollower);
		//modulBar.addView(loop);
		
	}

	public void onStart() {
        super.onStart();
        
        Log.d("BUILDUI", "onStart()");
  	  	if (!Var.bluetooth_adapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, Var.REQUEST_ENABLE_BT);

        } else {
            if (Var.service_data_io == null) setup();
        }
    }
    @Override
    public synchronized void onResume() {
        super.onResume();
        
        if(Var.isExiting==true){
        	Var.isExiting=false;
        	finish();

        	int pid = android.os.Process.myPid();
			android.os.Process.killProcess(pid);
        }
        
        Log.d("BUILDUI", "onResume()");
        if (Var.service_data_io != null) {
          if (Var.service_data_io.getState() == ServiceBluetooth.STATE_NONE) {
        	  Var.service_data_io.start();
            }
        }
    }
	private void setup() {
		Var.service_data_io = new ServiceBluetooth(this, mHandler);
		Var.output_string = new StringBuffer("");
    }
	public void connect(){
	    Intent serverIntent = new Intent(this, DeviceListActivity.class);
	    startActivityForResult(serverIntent, Var.REQUEST_CONNECT_DEVICE);
	}
	public void onDestroy() {
        super.onDestroy();
        if (Var.service_data_io != null) Var.service_data_io.stop();
    }
    private void sendMessage1(byte[] message) {
        if (Var.service_data_io.getState() != ServiceBluetooth.STATE_CONNECTED) {
            Toast.makeText(this, "Pengiriman data gagal", Toast.LENGTH_SHORT).show();
            return;
        }
        Var.service_data_io.write(message);
        Var.output_string.setLength(0);
    }
    private void sendMessage(String message) {
        if (Var.service_data_io.getState() != ServiceBluetooth.STATE_CONNECTED) {
            Toast.makeText(this, "Pengiriman data gagal", Toast.LENGTH_SHORT).show();
            return;
        }

        if (message.length() > 0) {
            byte[] send = message.getBytes();
            Var.service_data_io.write(send);
            Var.output_string.setLength(0);
        }
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case Var.MESSAGE_STATE_CHANGE:
                switch (msg.arg1) {
                case ServiceBluetooth.STATE_CONNECTED:
                	stat.setText("Tersambung ke : "+Var.namadevice_connect);
                    break;
                case ServiceBluetooth.STATE_CONNECTING:
                	stat.setText(R.string.title_connecting);
                    break;
                case ServiceBluetooth.STATE_LISTEN:
                case ServiceBluetooth.STATE_NONE:
                    stat.setText(R.string.title_not_connected);
                    break;
                }
                break;
            case Var.MESSAGE_DEVICE_NAME:
               
            	Var.namadevice_connect = msg.getData().getString(Var.DEVICE_NAME);
                Toast.makeText(getApplicationContext(), "Tersambung ke "
                               + Var.namadevice_connect, Toast.LENGTH_SHORT).show();
                break;
                
            case Var.MESSAGE_READ:
            	
                break;
                
            case Var.MESSAGE_TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString(Var.TOAST),
                               Toast.LENGTH_SHORT).show();
                break;
            }            
        }
    };
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case Var.REQUEST_CONNECT_DEVICE:
           
            if (resultCode == Activity.RESULT_OK) {
                
                String address = data.getExtras()
                                     .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                BluetoothDevice device = Var.bluetooth_adapter.getRemoteDevice(address);
                Var.service_data_io.connect(device);
            }
            break;
        case Var.REQUEST_ENABLE_BT:
             if (resultCode == Activity.RESULT_OK) {
                setup();
            } else {
                Toast.makeText(this, R.string.bt_not_enabled, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	     return true;
	     }
	     return super.onKeyDown(keyCode, event);    
	}
	
	public void createSaveDialog(){
		new AlertDialog.Builder(this)
	    .setTitle("Konfirmasi")
	    .setMessage("Apakah anda ingin menyimpan program terlebih dahulu?")
	    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	if(!Var.fileName.equals("")){
		        	Files f= new Files();
					Modules m=new Modules(getApplicationContext());
					String temp = "";
					for(int i=0; i<Var.activeBlocks.size(); i++){
						temp+=m.extractDataSave(Var.activeBlocks.get(i))+";";
					}
					f.saveData(temp, Var.dataPath+Var.fileName+".txt");
					f.saveData(temp, Var.outputPath+Var.fileName+".hex");
					Toast.makeText(getApplicationContext(), "Data telah tersimpan", Toast.LENGTH_SHORT).show();
					int pid = android.os.Process.myPid();
					android.os.Process.killProcess(pid);
					
	        	}else {
	        		Var.isExiting=true;
	        		Intent intent = new Intent(getApplicationContext(), SaveDialogActivity.class);
					startActivity(intent);
	        	}
	        }
	     }) 
	    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	int pid = android.os.Process.myPid();
				android.os.Process.killProcess(pid);
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}
	
	public void createDialogExit(){
		new AlertDialog.Builder(this)
	    .setTitle("Konfirmasi")
	    .setMessage("Apakah anda ingin keluar dari aplikasi ?")
	    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	
				int pid = android.os.Process.myPid();
				android.os.Process.killProcess(pid);
	        }
	     }) 
	    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}


	public boolean startDrag (View v)
	{
		hideProperties();
		
	    DragSource dragSource = (DragSource) v;
	    View del = findViewById (R.id.delete_zone_view);
	    del.setScaleX((float) 0.001);
	    del.setScaleY((float) 0.001);

	    Var.fromModul=true;
	    
	    if(v.getId()==Var.FORWARD_ID) {
	    	Var.onForward=true;
	    	Var.temp_id=Var.FORWARD_ID;
	    }
	    else if(v.getId()==Var.REVERSE_ID) {
	    	Var.onReverse=true;
	    	Var.temp_id=Var.REVERSE_ID;
	    }
	    else if(v.getId()==Var.TLEFT_ID) {
	    	Var.onTleft=true;
	    	Var.temp_id=Var.TLEFT_ID;
	    }
	    else if(v.getId()==Var.TRIGHT_ID) {
	    	Var.onTright=true;
	    	Var.temp_id=Var.TRIGHT_ID;
	    }
	    else if(v.getId()==Var.LCD_ID) {
	    	Var.onLCD=true;
	    	Var.temp_id=Var.LCD_ID;
	    }
	    else if(v.getId()==Var.DELAY_ID) {
	    	Var.onDelay=true;
	    	Var.temp_id=Var.DELAY_ID;
	    }
	    else if(v.getId()==Var.SOUND_ID) {
	    	Var.onSound=true;
	    	Var.temp_id=Var.SOUND_ID;
	    }
	    else if(v.getId()==Var.GRIPPER_ID) {
	    	Var.onGripper=true;
	    	Var.temp_id=Var.GRIPPER_ID;
	    }
	    else if(v.getId()==Var.LOOP_ID) {
	    	Var.onLoop=true;
	    	Var.temp_id=Var.LOOP_ID;
	    }
	    else if(v.getId()==Var.LFOLLOWER_ID) {
	    	Var.onFollower=true;
	    	Var.temp_id=Var.LFOLLOWER_ID;
	    }
	    else if(v.getId()==Var.WFOLLOWER_ID) {
	    	Var.onWall=true;
	    	Var.temp_id=Var.WFOLLOWER_ID;
	    }
	    mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
	    return true;
	}
	@Override
	public boolean onTouch(View v, MotionEvent ev) {
	    if (mLongClickStartsDrag) return false;

	    boolean handledHere = false;

	    final int action = ev.getAction();

        Handler handler=new Handler();
		handler.postDelayed(new Runnable() {
            public void run() {
                scrollWorksheet.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
	    
	    if (action == MotionEvent.ACTION_DOWN) {
	       handledHere = startDrag (v);
	    }
	    	    
	    
	    return handledHere;	
	}
		
	@Override
	public void onClick(View v) {
		if (mLongClickStartsDrag) {}
	}

	@Override
	public boolean onLongClick(View v) {
	    if (mLongClickStartsDrag) {
	    	if (!v.isInTouchMode()) {
	           return false;
	        }
	        Handler handler=new Handler();
			handler.postDelayed(new Runnable() {
	            public void run() {
	                scrollWorksheet.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
	            }
	        }, 100L);
	        return startDrag (v);
	    }
	    return false;
	}
}
