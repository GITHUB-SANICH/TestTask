package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    public static String getURL() {
        return getProperty("url");
    }

    public static Browser getBrowser() {
        return Browser.valueOf(getProperty("browser"));
    }

    public static String getParamsItemsList(String propertyKeyName) {
        return getProperty(propertyKeyName);
    }

    public static void setParamsItemsList(String propertyKeyName, String propertyValue, boolean storageSystemProperty) {
        setProperty(propertyKeyName, propertyValue, storageSystemProperty);
    }

    public static void deleteParamsItemsList(String propertyKeyName) {
        clearProperty(propertyKeyName);
    }


    private static String getProperty(String propertyKeyName) {
        if (System.getProperty(propertyKeyName) == null) {
            return getPropertyFromFile(propertyKeyName);
        } else {
            return System.getProperty(propertyKeyName);
        }
    }

    private static void setProperty(String propertyKeyName, String propertyValue, boolean storageSystemProperty) {
        if (storageSystemProperty) {
            System.setProperty(propertyKeyName, propertyValue);
        } else {
            setPropertyFromFile(propertyKeyName, propertyValue);
        }
    }

    private static void clearProperty(String propertyKeyName) {
        if (System.getProperty(propertyKeyName) != null) {
            System.clearProperty(propertyKeyName);
        } else {
            clearPropertyFromFile(propertyKeyName);
        }
    }

    private static String getPropertyFromFile(String propertyKeyName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Files.newInputStream(Paths.get("src/test/resources/framework.properties"));
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("Cannot read property value for " + propertyKeyName);
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(propertyKeyName);
    }

    private static void setPropertyFromFile(String propertyKeyName, String propertyValue) {
        Properties prop = new Properties();
        FileInputStream in;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("src/test/resources/framework.properties");
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new FileOutputStream("src/test/resources/framework.properties");
            prop.setProperty(propertyKeyName, propertyValue);
            prop.store(out, null);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot create property value for: " + propertyKeyName);
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void clearPropertyFromFile(String propertyKeyName) {
        Properties prop = new Properties();
        FileInputStream in;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("src/test/resources/framework.properties");
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new FileOutputStream("src/test/resources/framework.properties");
            prop.remove(propertyKeyName);
            prop.store(out, null);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot create property value for: " + propertyKeyName);
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}