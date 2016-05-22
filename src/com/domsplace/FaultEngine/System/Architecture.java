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
public final class Architecture {
    private static final List<Architecture> ARCHITECTURES = new ArrayList<Architecture>();
    public static final Architecture x64 = new Architecture("64 Bit");
    public static final Architecture x86 = new Architecture("32 Bit");
    public static final Architecture OTHER = new Architecture("Unknown");
    
    public static List<Architecture> getArchitectures() {return new ArrayList<Architecture>(ARCHITECTURES);}
    
    public static Architecture getCurrentSystemArchitecture() {
        //String arch = System.getProperty("os.arch", "other");
        String arch = System.getProperty("sun.arch.data.model", "other");
        if(arch == null) arch = "";
        arch = arch.toLowerCase();
        if(arch.contains("32") || arch.contains("86")) return Architecture.x86;
        if(arch.contains("64")) return Architecture.x64;
        return Architecture.OTHER;
    }
    
    //Instance
    private final String name;
    
    private Architecture(final String name) {
        this.name = name;
        ARCHITECTURES.add(this);
    }
    
    public String getName() {return this.name;}
}
