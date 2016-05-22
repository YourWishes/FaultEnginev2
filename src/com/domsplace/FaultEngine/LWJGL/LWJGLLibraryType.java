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

/**
 *
 * @author Dominic Masters
 */
public final class LWJGLLibraryType {
    public static final LWJGLLibraryType INPUT = new LWJGLLibraryType("Input");
    public static final LWJGLLibraryType INPUT_RAW = new LWJGLLibraryType("Raw Input");
    public static final LWJGLLibraryType LWJGL = new LWJGLLibraryType("LWJGL");
    public static final LWJGLLibraryType OPEN_AL = new LWJGLLibraryType("Open AL");
    
    //Instance
    private final String type;
    
    private LWJGLLibraryType (String type) {
        this.type = type;
    }
    
    public String getType() {return this.type;}
}
