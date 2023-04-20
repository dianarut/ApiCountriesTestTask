package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

    public static Properties readPropertiesFile(String fileName){
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(fileName);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


}
