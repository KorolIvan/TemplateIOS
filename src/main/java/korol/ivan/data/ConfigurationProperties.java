package korol.ivan.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author by Ivan Korol on 6/20/2017.
 */
public class ConfigurationProperties {

    public static String getProperty(String fileName, String key) {

        FileInputStream fileInputStream = null;
        Properties property = new Properties();
        String value = null;
        try {
            fileInputStream = new FileInputStream("src/main/resources/"+fileName+".properties");
            property.load(fileInputStream);
            value = property.getProperty(key);
        } catch (IOException e) {
            System.out.println("Error: file is not found!");
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (IOException e){
                    System.out.println("Error: cannot close the file stream");
                }
            }

        }
        return value;
    }
}
