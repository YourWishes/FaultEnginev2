/*
 * Copyright 2014 Dominic.
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

package com.domsplace.FaultEngine.Display.Face;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Color.Color;
import com.domsplace.FaultEngine.Display.Normal.Normal;
import com.domsplace.FaultEngine.Display.Texture.TextureCoordinate;
import com.domsplace.FaultEngine.Display.Vertice.Vertice;
import com.domsplace.FaultEngine.Dispose.IDisposeable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic
 */
public class Face implements IDisposeable, ICloneable<Face> {
    private List<Vertice> vertices;
    private List<Normal> normals;
    private List<TextureCoordinate> textureCoordinates;
    
    public Face() {
        this.vertices = new ArrayList<Vertice>();
        this.normals = new ArrayList<Normal>();
        this.textureCoordinates = new ArrayList<TextureCoordinate>();
    }
    
    public Face(Face f) {
        this.vertices = new ArrayList<Vertice>(f.vertices);
        this.normals = new ArrayList<Normal>(f.normals);
        this.textureCoordinates = new ArrayList<TextureCoordinate>(f.textureCoordinates);
    }

    @Override
    public void dispose() {        
        //Nullify Values
        this.vertices = null;
        this.normals = null;
        this.textureCoordinates = null;
    }

    @Override
    public Face clone() {
        return new Face(this);
    }
}
