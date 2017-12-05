package visualprogrammer;

import java.util.ArrayList;

import interfaces.Image;
import interfaces.Modules;
import android.bluetooth.BluetoothAdapter;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;

public class Var {
	
	public static String dataPath="/sdcard/RoboViper/data/";
	public static String outputPath="/sdcard/RoboViper/output/";
	public static String fileName="";
	public static boolean isExiting=false;
	public static boolean isNew=false;
	public static boolean isDeploy=false;
	public static boolean isSaved=false;
	public static boolean forwardActive=false,
			   reverseActive=false,
			   tleftActive=false,
			   trightActive=false,
			   lcdActive=false,
			   delayActive=false,
			   detectActive=false,
			   soundActive=false,
			   gripperActive=false,
			   freeActive=false,
			   loopActive=false,
			   lfollowerActive=false,
			   wfollowerActive=false;
	
	public static Display display;
	public static Point size;
	public static int indexLink;
	public static int indexModule;
	public static int temp_id;
	public static int tempMovingOrder;
	
	public static ArrayList<Image> links= new ArrayList<Image>();
	public static ArrayList<Modules> activeBlocks= new ArrayList<Modules>();
	
	public static boolean onSuccess=false;
	public static boolean fromModul=false;
	public static boolean fromWorksheet=false;
	
	
	public static boolean onDelete=false;
	public static boolean onForward=false;
	public static boolean onReverse=false;
	public static boolean onTright=false;
	public static boolean onTleft=false;
	public static boolean onLCD=false;
	public static boolean onDelay=false;
	public static boolean onSound=false;
	public static boolean onGripper=false;
	public static boolean onLoop=false;
	public static boolean onFollower=false;
	public static boolean onWall=false;

	public static final int HOME_ID = -1;
	public static final int MENU_ID = -2;
	public static final int MODUL_ID = -3;
	public static final int EXIT_ID = -4;
	public static final int ABOUT_ID = -55;
	
	public static final int NEW_ID = -5;
	public static final int OPEN_ID = -6;
	public static final int SAVE_ID = -7;
	public static final int DELETE_ID = -8;
	public static final int INFO_ID = -9;
	public static final int CONNECT_ID = -10;
	public static final int DEPLOY_ID = -11;
	public static final int SYNC_ID = -111;
	
	public static final int LEFT_ID=-12;
	public static final int RIGHT_ID=-13;
	
	public static final int FORWARD_ID=-14;
	public static final int TRIGHT_ID=-15;
	public static final int TLEFT_ID=-16;
	public static final int REVERSE_ID=-17;
	public static final int LCD_ID=-18;
	public static final int DELAY_ID=-19;
	public static final int SOUND_ID=-20;
	public static final int GRIPPER_ID=-22;
	public static final int LFOLLOWER_ID=-23;
	public static final int WFOLLOWER_ID=-24;
	public static final int LOOP_ID=-25;

	public static final int LEFT1_ID=24;
	public static final int RIGHT1_ID=25;
	
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;    
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    public static final int REQUEST_CONNECT_DEVICE = 1;
    public static final int REQUEST_ENABLE_BT = 2;  
    public static String namadevice_connect = null;
    public static StringBuffer output_string;
    public static BluetoothAdapter bluetooth_adapter = null;
    public static ServiceBluetooth service_data_io = null;
    
    public static void sortOrder(){
    	for(int i=0; i<Var.activeBlocks.size(); i++) Var.activeBlocks.get(i).order=i;
		for(int i=0; i<Var.links.size(); i++) Var.links.get(i).order=i;
		BuildUiActivity.blockno.setText("Jumlah blok aktif : "+Var.activeBlocks.size()+"/64");
		if(activeBlocks.size()>64) BuildUiActivity.blockno.setTextColor(Color.RED);
		else BuildUiActivity.blockno.setTextColor(Color.rgb(70, 70, 70));
    }

}
