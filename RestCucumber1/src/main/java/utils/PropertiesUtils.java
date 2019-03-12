package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	Properties prop=new Properties();
	 
	public String getPropValue(String key)  throws IOException
	{
		prop.load(new FileInputStream("src/test/resources/apiConfig.properties"));
		return prop.getProperty(key);
		
	}

}
