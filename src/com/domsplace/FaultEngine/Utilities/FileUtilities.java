/*
 * Copyright 2014 Dominic Masters.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domsplace.FaultEngine.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Masters
 */
public class FileUtilities {
    public static final List<InputStream> OPEN_STREAMS = new ArrayList<InputStream>();
    
    public static InputStream getResource(String resource) throws IOException {
        if(!resource.startsWith("/")) resource = "/" + resource;
        URL url = FileUtilities.class.getResource(resource);
        InputStream is = url.openStream();
        OPEN_STREAMS.add(is);
        return is;
    }
    
    public static String getResourceAsString(String resource) throws IOException {
        return getInputStreamAsString(getResource(resource));
    }
    
    public static String getInputStreamAsString(InputStream is) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer content = new StringBuffer();
        char[] buffer = new char[1024];
        int n;

        while ( ( n = reader.read(buffer)) != -1 ) {
            content.append(buffer,0,n);
        }

        return content.toString();
    }

    public static String loadFileToString(String modelobj) throws Exception {
        return loadFileToString(new File(modelobj));
    }
    
    public static String loadFileToString(File file) throws Exception {
        InputStream is = new FileInputStream(file);
        return getInputStreamAsString(is);
    }
    
    public static void saveResourceToFile(String resource, File file) throws Exception {
        file.getParentFile().mkdirs();
        if(file.exists()) return;
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        InputStream is = getResource(resource);
        
        int read = 0;
        byte[] buffer = new byte[1024];

        while ((read = is.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }
        fos.close();
        is.close();
    }
    
    public static void saveStringsToFile(List<String> strings, File file) throws Exception {
        try {file.getParentFile().mkdirs();} catch(Throwable t) {}
        //if(file.exists()) return;
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        
        for(String s : strings) {
            fos.write((s + "\n").getBytes());
        }
        
        fos.flush();
        fos.close();
    }

    public static void saveStringToFile(String t, File file) throws Exception {
        List<String> strings = new ArrayList<String>();
        for(String s : t.replaceAll("\r", "\n").split("\n")) {
            strings.add(s);
        }
        saveStringsToFile(strings, file);
    }
    
    public static void readStreamToFile(InputStream is, File file) throws Exception {
        file.getParentFile().mkdirs();
        if(file.exists()) return;
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);

        int read = 0;
        byte[] buffer = new byte[1024];

        while ((read = is.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }
        fos.close();
    }
}
