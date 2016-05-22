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

package com.domsplace.FaultEngine.System;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Masters
 */
public final class OperatingSystem {
    private static final List<OperatingSystem> OPERATING_SYSTEMS = new ArrayList<OperatingSystem>();
    public static final OperatingSystem WINDOWS = new OperatingSystem("Windows");
    public static final OperatingSystem LINUX = new OperatingSystem("Linux");
    public static final OperatingSystem MAC_OSX = new OperatingSystem("Mac OS X");
    public static final OperatingSystem OTHER = new OperatingSystem("Other");
    
    public static List<OperatingSystem> getOperatingSystems() {return new ArrayList<OperatingSystem>(OPERATING_SYSTEMS);}
    
    public static OperatingSystem getCurrentSystemOperatingSystem() {
        String os = System.getProperty("os.name", "other");
        if(os == null) os = "";
        os = os.toLowerCase();
        if(os.contains("window")) return OperatingSystem.WINDOWS;
        if(os.contains("os x")) return OperatingSystem.MAC_OSX;
        if(os.contains("linux")) return OperatingSystem.LINUX;
        return OperatingSystem.OTHER;
    }
    
    //Instance
    private final String name;
    
    private OperatingSystem(final String name) {
        this.name = name;
        OPERATING_SYSTEMS.add(this);
    }
    
    public String getName() {return this.name;}

    public String getLWJGLDirectory() {
        return this.name.toLowerCase().replaceAll(" ", "");
    }
}
