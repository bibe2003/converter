/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;
import java.util.*;
import java.io.*;
import flexjson.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author Biljana
 */
public class CCCodes {
    // attributes
    private static String cwd;
    private static final String path = "config.json";
    private static String configFile;
    
    private class CountryCodes {
        String Country;
        String Units;
    }
    
    private static void readFile (String path) {
        byte[] rawFile;
        
        try {
            cwd = new File (".").getCanonicalPath(); // grab current working dir
            rawFile = Files.readAllBytes(Paths.get(path));
            configFile = new String(rawFile);
            JSONDeserializer<List<CountryCodes>> deserializer = new JSONDeserializer<List<CountryCodes>> ();
            List stdMap = deserializer.deserialize(configFile);
            System.out.println(stdMap);
            System.out.println(stdMap);
            System.out.println(stdMap.get(0));
            System.out.println(); // fill these in
            System.out.println(); // fill these 
            // list of country codes and just returns it
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Something went wrong! Missing file: "+cwd+"/"+path+"?");
        }
    }
}
