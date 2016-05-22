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

package com.domsplace.FaultEngine.Display.Vertice;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Color.Color;
import com.domsplace.FaultEngine.Display.Normal.Normal;
import com.domsplace.FaultEngine.Display.Texture.TextureCoordinate;
import com.domsplace.FaultEngine.Location.Location;
import com.domsplace.FaultEngine.Vector.Vector;

/**
 *
 * @author Dominic
 */
public final class Vertice implements ICloneable<Vertice> {
    private double x;
    private double y;
    private double z;
    
    public Vertice(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vertice(float x, float y, float z) {
        this((double) x, (double) y, (double) z);
    }
    
    public Vertice() {
        this(0, 0, 0);
    }
    
    public Vertice(Normal n) {
        this(n.getX(), n.getY(), n.getZ());
    }
    
    public Vertice(Vertice v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    
    public Vertice(Location l) {
        this(l.getX(), l.getY(), l.getZ());
    }
    
    public Vertice(TextureCoordinate coordinate) {
        this(coordinate.getX(), coordinate.getY(), 0);
    }
    
    public Vertice(Vector v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public double getZ() {return this.z;}
    
    public Vertice setX(double x) {this.x = x; return this;}
    public Vertice setY(double y) {this.y = y; return this;}
    public Vertice setZ(double z) {this.z = z; return this;}
    
    public Vertice set(double x, double y, double z) {return this.setX(x).setY(y).setZ(z);}
    
    public Location toLocation() {
        return new Location(this);
    }
    
    public TextureCoordinate toTextureCoordinate() {
        return new TextureCoordinate(this);
    }
    
    public Normal toNormal() {
        return new Normal(this);
    }
    
    public Vector toVector() {
        return new Vector(this);
    }
    
    @Override
    public String toString () {
        return this.getX() + "," + this.getY() + "," + this.getZ ();
    }
    
    @Override
    public Vertice clone() {
        return new Vertice(this);
    }
}
