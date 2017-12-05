package webservice; 
    
import java.util.ArrayList; 
import java.util.List;

import org.apache.http.NameValuePair; 
import org.apache.http.message.BasicNameValuePair; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import visualprogrammer.Files;
import visualprogrammer.Var;

public class UserFunctions { 
    
    private JSONParser jsonParser; 
    
    //private static String URL ="http://192.168.173.1/roboviper/index.php";
    private static String URL ="http://roboviper.besaba.com/json/index.php";
    
    public UserFunctions(){ 
        jsonParser = new JSONParser(); 
    } 
    
    public JSONObject loginUser(String uname, String password) throws Exception{ 
        // Building Parameters 
        List<NameValuePair> params = new ArrayList<NameValuePair>(); 
        try{
        	params.add(new BasicNameValuePair("tag", "login")); 
        	params.add(new BasicNameValuePair("username", uname)); 
        	params.add(new BasicNameValuePair("password", password)); 
        	JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        	return json;
        } catch(Exception e){
        	throw e;
        }
    }
    
    public JSONObject registerUser(String name, String uname, String password) throws Exception{ 
        // Building Parameters 
        List<NameValuePair> params = new ArrayList<NameValuePair>(); 
        try{
        	params.add(new BasicNameValuePair("tag", "register"));
        	params.add(new BasicNameValuePair("nama", name));
        	params.add(new BasicNameValuePair("username", uname)); 
        	params.add(new BasicNameValuePair("password", password)); 
        	JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        	return json;
        } catch(Exception e){
        	throw e;
        }
    }
    
    public JSONObject kirimData(int id,String namafile,String kodeprogram) throws Exception{
        List<NameValuePair> params = new ArrayList<NameValuePair>(); 
        try{
        	params.add(new BasicNameValuePair("tag", "backup"));
        	params.add(new BasicNameValuePair("id_user", String.valueOf(id)));
        	params.add(new BasicNameValuePair("nama_file", namafile));
        	params.add(new BasicNameValuePair("kode_program", kodeprogram));        	
        	JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
        	return json;
        	
        } catch(Exception e){
        	throw e;
        }
    }
    
    public void DownloadData(String id) throws Exception{
 		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
 		String namafile="",datafile="";
 		Files f;
         try{ 
	         params.add(new BasicNameValuePair("tag","restore"));
	         params.add(new BasicNameValuePair("id_user",id));
	         JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	         JSONArray  data = json.getJSONArray("data");
             for(int i=0;i < data.length();i++){                        
                 JSONObject e = data.getJSONObject(i);
                 f=new Files();
                 namafile="";
                 datafile="";
                 f.saveData(String.valueOf(e.getString("kode_program")), 
                		 Var.dataPath+String.valueOf(e.getString("nama_file"))+".txt");
             }
         }
         catch(JSONException e){ 
             e.printStackTrace(); 
         } 
 	}
 }
