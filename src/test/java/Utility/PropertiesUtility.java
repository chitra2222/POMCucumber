package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	
		private FileInputStream file = null;
		private Properties ob = null;

	public Properties loadFile(String filename) {
		// TODO Auto-generated method stub
		ob = new Properties();
		String PropertyFilePath = null;
		switch(filename) {
		case "TestDataProperties":
			PropertyFilePath = Constants.TESTDATA_PROPERTIES;
			break;
			
		}	
			try {
				file = new FileInputStream(PropertyFilePath);
				ob.load(file);
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  return ob;

	}
	
	public String getPropertyValue(String key) {
		String value= ob.getProperty(key);
		System.out.println("Property from the file is : "+value);
		
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
