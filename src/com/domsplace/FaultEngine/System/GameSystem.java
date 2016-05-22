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

import com.domsplace.FaultEngine.LWJGL.LWJGLLibrary;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Masters
 */
public final class GameSystem {
    public static final List<GameSystem> SYSTEMS = new ArrayList<GameSystem>();
    
    public static GameSystem getSystem(Architecture architecture, OperatingSystem operatingSystem) {
        setupGameSystems();
        for(GameSystem gs : SYSTEMS) {
            if(gs.compare(architecture, operatingSystem)) return gs;
        }
        return null;
    }
    
    public static GameSystem getCurrentSystem() {
        return getSystem(Architecture.getCurrentSystemArchitecture(), OperatingSystem.getCurrentSystemOperatingSystem());
    }
    
    private static void setupGameSystems() {
        for(OperatingSystem system : OperatingSystem.getOperatingSystems()) {
            for(Architecture architecture : Architecture.getArchitectures()) {
                GameSystem syst = null;
                for(GameSystem sys : SYSTEMS) {
                    if(!sys.operatingSystem.equals(system)) continue;
                    if(!sys.architecture.equals(architecture)) continue;
                    syst = sys;
                }
                if(syst != null) continue;
                syst = new GameSystem(architecture, system);
            }
        }
    }
    
    //Instance
    private final Architecture architecture;
    private final OperatingSystem operatingSystem;
    
    private GameSystem(Architecture architecture, OperatingSystem operatingSystem) {
        this.architecture = architecture;
        this.operatingSystem = operatingSystem;
        SYSTEMS.add(this);
    }
    
    public Architecture getArchitecture() {return this.architecture;}
    public OperatingSystem getOperatingSystem() {return this.operatingSystem;}
    public List<LWJGLLibrary> getLWJGLLibraries() {return LWJGLLibrary.getLibraries(this);}
    
    public boolean isLWJGLCompatible() {return !(this.architecture.equals(Architecture.OTHER) || this.operatingSystem.equals(OperatingSystem.OTHER));}
    public boolean isGUICompatible() {return !GraphicsEnvironment.isHeadless();}
    
    public boolean compare(GameSystem system) {return compare(system.architecture, system.operatingSystem);}
    public boolean compare(Architecture a, OperatingSystem o) {return this.architecture.equals(a) && this.operatingSystem.equals(o);}
}
