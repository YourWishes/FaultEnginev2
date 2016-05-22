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

package com.domsplace.FaultEngine.Display.Mesh;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Display.Camera.Camera;
import com.domsplace.FaultEngine.Display.Face.Face;
import com.domsplace.FaultEngine.Display.Render.IRenderTarget;
import com.domsplace.FaultEngine.Display.Render.IRenderable;
import com.domsplace.FaultEngine.Display.Render.RenderSettings;
import com.domsplace.FaultEngine.Display.VertexBuffer.VertexBufferObject;
import com.domsplace.FaultEngine.Dispose.IDisposeable;
import com.domsplace.FaultEngine.Location.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic
 */
public class Mesh implements IDisposeable, ICloneable<Mesh>, IRenderable {
    private List<Face> faces;
    private VertexBufferObject vbo;
    
    public Mesh() {
        this.faces = new ArrayList<Face>();
        this.vbo = new VertexBufferObject(this);
    }
    
    public Mesh(Mesh m) {
        this();
        this.faces = m.getFaces();
    }
    
    public List<Face> getFaces() {return new ArrayList<Face>(this.faces);}
    public VertexBufferObject getVertexBufferObject() {return this.vbo;}

    @Override public Mesh clone() {return new Mesh(this);}

    @Override
    public void dispose() {
        if(this.vbo != null) {
            this.vbo.mesh = null;
            this.vbo.dispose();
        }
        this.vbo = null;
    }

    @Override
    public void render(RenderSettings renderSettings, IRenderTarget target, Location offset, Camera camera) {
        //TODO: Finish
    }
}
