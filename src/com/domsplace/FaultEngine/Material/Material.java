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

package com.domsplace.FaultEngine.Material;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Color.Color;

/**
 *
 * @author Dominic
 */
public class Material implements ICloneable<Material> {
    private Color color;
    
    public Material() {
        
    }
    
    public Material(Material material) {
        
    }
    
    public Color getColor() {return this.color;}
    
    public void setColor(Color c) {color = c;}
    
    @Override
    public Material clone() {
        return new Material(this);
    }
}
