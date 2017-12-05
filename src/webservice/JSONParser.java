package webservice;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.UnsupportedEncodingException; 
import java.util.List;

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException; 
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

    static InputStream is = null; 
    static JSONObject jObj = null; 
    static String json = "";

    // constructor 
    public JSONParser() {

    } 
    
    public JSONObject getJSONListfromURL(String url,List<NameValuePair> params) throws Exception{

        //initialize 
        InputStream is = null; 
        String result = ""; 
        JSONObject jArray = null;

        //http post 
        try{ 
        	//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts = 15000ms
			httpParameters.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
			HttpConnectionParams.setSoTimeout(httpParameters, 10000);				
			
            DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters); 
            HttpPost httppost = new HttpPost(url); 
            httppost.setEntity(new UrlEncodedFormEntity(params)); 
            HttpResponse response = httpclient.execute(httppost); 
            HttpEntity entity = response.getEntity(); 
            is = entity.getContent();

        }catch(Exception e){ 
            Log.e("log_tag", "Error in http connection "+e.toString()); 
            throw e;
        }

        //convert response to string 
        try{ 
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8); 
            StringBuilder sb = new StringBuilder(); 
            String line = null; 
            while ((line = reader.readLine()) != null) { 
                sb.append(line + "\n"); 
            } 
            is.close(); 
            result=sb.toString(); 
        }catch(Exception e){ 
            Log.e("log_tag", "Error converting result "+e.toString()); 
            throw e;
        }

        //try parse the string to a JSON object 
        try{ 
                jArray = new JSONObject(result); 
        }catch(JSONException e){ 
            Log.e("log_tag", "Error parsing data "+e.toString()); 
            throw e;
        }

        return jArray; 
    }

    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) throws Exception {

        // Making HTTP request 
        try { 
        	//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			httpParameters.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			//Setup timeouts = 15000ms
			//HttpConnectionParams.setTcpNoDelay(httpParameters, true);
			HttpConnectionParams.setConnectionTimeout(httpParameters, 20000);
			HttpConnectionParams.setSoTimeout(httpParameters, 20000);				
			
        	// defaultHttpClient 
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters); 
            HttpPost httpPost = new HttpPost(url); 
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost); 
            HttpEntity httpEntity = httpResponse.getEntity(); 
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
            throw e;
        } catch (ClientProtocolException e) { 
            e.printStackTrace(); 
            throw e;
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw e;
        }

        try { 
            BufferedReader reader = new BufferedReader(new InputStreamReader( 
                    is, "iso-8859-1"), 8); 
            StringBuilder sb = new StringBuilder(); 
            String line = null; 
            while ((line = reader.readLine()) != null) { 
                sb.append(line + "\n"); 
            } 
            is.close(); 
            json = sb.toString(); 
            Log.e("JSON", json); 
        } catch (Exception e) { 
            Log.e("Buffer Error", "Error converting result " + e.toString()); 
            throw e;
        }

        // try parse the string to a JSON object 
        try { 
            jObj = new JSONObject(json);            
        } catch (JSONException e) { 
            Log.e("JSON Parser", "Error parsing data " + e.toString()); 
            throw e;
        }

        // return JSON String 
        return jObj;
    } 
    
    public void SendJSONToURL(String url,List<NameValuePair> params) throws Exception{

        //http post 
        try{ 
            DefaultHttpClient httpclient = new DefaultHttpClient(); 
            HttpPost httppost = new HttpPost(url); 
            httppost.setEntity(new UrlEncodedFormEntity(params)); 
            httpclient.execute(httppost);

        }catch(Exception e){ 
            Log.e("log_tag", "Error in http connection "+e.toString()); 
            throw e;
        } 
    } 
}