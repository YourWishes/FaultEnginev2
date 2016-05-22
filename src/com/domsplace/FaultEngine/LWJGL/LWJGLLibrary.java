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

package com.domsplace.FaultEngine.LWJGL;

import com.domsplace.FaultEngine.Game.Game;
import com.domsplace.FaultEngine.System.Architecture;
import com.domsplace.FaultEngine.System.GameSystem;
import com.domsplace.FaultEngine.System.OperatingSystem;
import com.domsplace.FaultEngine.Utilities.FileUtilities;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Masters
 */
public final class LWJGLLibrary {
    private static final List<LWJGLLibrary> LIBRARIES = new ArrayList<LWJGLLibrary>();
    
    public static List<LWJGLLibrary> getLibraries(GameSystem system) {
        loadLibraries();
        List<LWJGLLibrary> libs = new ArrayList<LWJGLLibrary>();
        for(LWJGLLibrary lib : LIBRARIES) {
            if(!lib.system.compare(system)) continue;
            libs.add(lib);
        }
        return libs;
    }
    
    private static void loadLibraries() {
        if(LIBRARIES.size() != 0) return;
        
        //Linux
        //32 Bit
        new LWJGLLibrary("libjinput-linux.so", GameSystem.getSystem(Architecture.x86, OperatingSystem.LINUX), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("liblwjgl.so", GameSystem.getSystem(Architecture.x86, OperatingSystem.LINUX), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("libopenal.so", GameSystem.getSystem(Architecture.x86, OperatingSystem.LINUX), LWJGLLibraryType.OPEN_AL);
        //64 Bit
        new LWJGLLibrary("libjinput-linux64.so", GameSystem.getSystem(Architecture.x64, OperatingSystem.LINUX), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("liblwjgl64.so", GameSystem.getSystem(Architecture.x64, OperatingSystem.LINUX), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("libopenal64.so", GameSystem.getSystem(Architecture.x64, OperatingSystem.LINUX), LWJGLLibraryType.OPEN_AL);
        
        //Mac OS X
        //32 Bit
        new LWJGLLibrary("libjinput-osx.jnlib", GameSystem.getSystem(Architecture.x86, OperatingSystem.MAC_OSX), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("liblwjgl.jnlib", GameSystem.getSystem(Architecture.x86, OperatingSystem.MAC_OSX), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("libopenal.dylib", GameSystem.getSystem(Architecture.x86, OperatingSystem.MAC_OSX), LWJGLLibraryType.OPEN_AL);
        //64 Bit
        new LWJGLLibrary("libjinput-osx.jnlib", GameSystem.getSystem(Architecture.x64, OperatingSystem.MAC_OSX), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("liblwjgl.jnlib", GameSystem.getSystem(Architecture.x64, OperatingSystem.MAC_OSX), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("libopenal.dylib", GameSystem.getSystem(Architecture.x64, OperatingSystem.MAC_OSX), LWJGLLibraryType.OPEN_AL);
        
        //Windows
        //32 Bit
        new LWJGLLibrary("jinput-dx8.dll", GameSystem.getSystem(Architecture.x86, OperatingSystem.WINDOWS), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("jinput-raw.dll", GameSystem.getSystem(Architecture.x86, OperatingSystem.WINDOWS), LWJGLLibraryType.INPUT_RAW);
        new LWJGLLibrary("lwjgl.dll", GameSystem.getSystem(Architecture.x86, OperatingSystem.WINDOWS), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("OpenAL32.dll", GameSystem.getSystem(Architecture.x86, OperatingSystem.WINDOWS), LWJGLLibraryType.OPEN_AL);
        //64 Bit
        new LWJGLLibrary("jinput-dx8_64.dll", GameSystem.getSystem(Architecture.x64, OperatingSystem.WINDOWS), LWJGLLibraryType.INPUT);
        new LWJGLLibrary("jinput-raw_64.dll", GameSystem.getSystem(Architecture.x64, OperatingSystem.WINDOWS), LWJGLLibraryType.INPUT_RAW);
        new LWJGLLibrary("lwjgl64.dll", GameSystem.getSystem(Architecture.x64, OperatingSystem.WINDOWS), LWJGLLibraryType.LWJGL);
        new LWJGLLibrary("OpenAL64.dll", GameSystem.getSystem(Architecture.x64, OperatingSystem.WINDOWS), LWJGLLibraryType.OPEN_AL);
    }
    
    public static File getLibraryDirectory() {return getLibraryDirectory(GameSystem.getCurrentSystem());}
    public static File getLibraryDirectory(GameSystem system) {
        return new File(Game.getGame().getInstallationDirectory(), "natives/" + system.getOperatingSystem().getLWJGLDirectory());
    }
    
    //Instance
    private final String filename;
    private final GameSystem system;
    private final LWJGLLibraryType type;
    
    private LWJGLLibrary(String filename, GameSystem system, LWJGLLibraryType type) {
        this.filename = filename;
        this.system = system;
        this.type = type;
        LIBRARIES.add(this);
    }
    
    public String getFileName() {return this.filename;}
    public GameSystem getSystem() {return this.system;}
    public LWJGLLibraryType getType() {return this.type;}
    
    public String getResource() throws Exception {
        return "natives/" + this.system.getOperatingSystem().getLWJGLDirectory() + "/" + this.filename;
    }
    
    public File getFile() {
        return new File(getLibraryDirectory(this.system), this.filename);
    }
    
    public void saveToFile() throws Exception {FileUtilities.saveResourceToFile(this.getResource(), this.getFile());}
}
