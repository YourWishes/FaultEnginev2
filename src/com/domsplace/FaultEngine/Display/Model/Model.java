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

package com.domsplace.FaultEngine.Display.Model;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Display.Camera.Camera;
import com.domsplace.FaultEngine.Display.Mesh.Mesh;
import com.domsplace.FaultEngine.Display.Render.IRenderTarget;
import com.domsplace.FaultEngine.Display.Render.IRenderable;
import com.domsplace.FaultEngine.Display.Render.RenderSettings;
import com.domsplace.FaultEngine.Dispose.IDisposeable;
import com.domsplace.FaultEngine.Location.ILocateable;
import com.domsplace.FaultEngine.Location.Location;
import com.domsplace.FaultEngine.Material.Material;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic
 */
public final class Model implements ILocateable, IDisposeable, ICloneable<Model>, IRenderable {
    private Location location;
    private List<Mesh> meshes;
    private List<Model> children;
    private Model parent;
    private double scaleX;
    private double scaleY;
    private double scaleZ;
    private Material material;
    
    public Model() {
        this.location = new Location(this);
        this.meshes = new ArrayList<Mesh>();
        this.children = new ArrayList<Model>();
        this.parent = null;
        this.scaleX = 1;
        this.scaleY = 1;
        this.scaleZ = 1;
    }
    
    public Model(Model model) {
        this();
        this.location = model.location.clone();
        this.location.setParent(this);
        this.meshes = model.getMeshes();
        
        for(Model m : model.getChildren()) {
            this.addChild(m.clone());
        }
        
        if(model.parent != null) {
            model.parent.addChild(this);
        }
        
        this.scaleX = model.scaleX;
        this.scaleY = model.scaleY;
        this.scaleZ = model.scaleZ;
    }
    
    @Override public Location getLocation() {return this.location;}
    @Override public ILocateable getParent() {return this.getParentModel();}
    public List<Mesh> getMeshes() {return new ArrayList<Mesh>(this.meshes);}
    public List<Model> getChildren() {return new ArrayList<Model>(this.children);}
    public Material getMaterial() {return this.material;}
    public Model getParentModel() {return this.parent;}
    public double getScaleX() {return this.scaleX;}
    public double getScaleY() {return this.scaleY;}
    public double getScaleZ() {return this.scaleZ;}
    
    public Material setMaterial(Material m) {return this.material = m;}
    
    public void addChild(Model m) {
        if(!this.children.contains(m)) {
            this.children.add(m);
        }
        if(m.parent != null) m.parent.removeChild(m);
        m.parent = this;
    }
    
    public void removeChild(Model m) {this.children.remove(m); m.parent = null;}
    
    public Material cloneMaterial(Material m) {return this.setMaterial(this.getMaterial().clone());}
    
    @Override
    public void dispose() {
        if(this.parent != null) this.parent.removeChild(this);
        
        for(Model model : this.children) {
            model.dispose();
        }
        
        this.children = null;
        this.location.dispose();
        this.location = null;
        this.parent = null;
        this.meshes = null;
        this.material = null;
    }

    @Override
    public Model clone() {
        return new Model(this);
    }

    @Override
    public void render(RenderSettings renderSettings, IRenderTarget target, Location offset, Camera camera) {
        Location offset_plus_my_offset = offset.clone().add(this.location);
        for(Model child : this.getChildren()) {//Thread Safe
            child.render(renderSettings, target, offset_plus_my_offset, camera);
        }
        
        for(Mesh mesh : this.getMeshes()) {
            mesh.render(renderSettings, target, offset_plus_my_offset, camera);
        }
    }
}
