package visualprogrammer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.util.Log;

public class Files {
	
	public boolean openDirectory(){
		return true;
	}

	public void deleteFile(String item){
		File file = new File(Var.dataPath+item+".txt");
    	if(file.exists())
    	  file.delete();
    	
    	File file1 = new File(Var.outputPath+item+".hex");
    	if(file1.exists())
    	  file1.delete();
    	
	}
	
	public boolean saveData(String data, String path){
        File f;
        f = new File(Var.dataPath);
        f.mkdirs();
        f = new File(Var.outputPath);
        f.mkdirs();
        f = new File(path);
        
        if (!f.exists()) {
        	Log.d("FileHandler", "File belum ada");
            try {
            	Log.d("FileHandler", "Membuat file baru");
                if (f.createNewFile()) {
                    Log.d("FileHandler", "File baru \"" + f.getName() + "\" dibuat di" + f.getPath());
                } else {
                	Log.d("FileHandler", "File baru GAGAL \"" + f.getName() + "\" dibuat di" + f.getPath());
                }
            } catch (IOException ex) {
            	Log.d("FileHandler", "masuk exception");
                return false;
            }
        }
        try {
        	Log.d("FileHandler","membuat filewriter");
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(data);
            out.close();

            return true;
        } catch (Exception e) {
        	Log.d("FileHandler","gagal membuat filewriter");
            return false;
        }
	}
	
}
