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

package com.domsplace.FaultEngine.Display.Texture;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Display.Normal.Normal;
import com.domsplace.FaultEngine.Display.Vertice.Vertice;
import com.domsplace.FaultEngine.Location.Location;
import com.domsplace.FaultEngine.Vector.Vector;

/**
 *
 * @author Dominic
 */
public final class TextureCoordinate implements ICloneable<TextureCoordinate> {
    private double x;
    private double y;
    
    public TextureCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public TextureCoordinate(float x, float y) {
        this((double) x, (double) y);
    }
    
    public TextureCoordinate() {
        this(0, 0);
    }
    
    public TextureCoordinate(Normal n) {
        this(n.getX(), n.getY());
    }
    
    public TextureCoordinate(Vertice v) {
        this(v.getX(), v.getY());
    }
    
    public TextureCoordinate(TextureCoordinate coordinate) {
        this(coordinate.getX(), coordinate.getY());
    }
    
    public TextureCoordinate(Location l) {
        this(l.getX(), l.getY());
    }
    
    public TextureCoordinate(Vector v) {
        this(v.getX(), v.getY());
    }
    
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    
    public TextureCoordinate setX(double x) {this.x = x; return this;}
    public TextureCoordinate setY(double y) {this.y = y; return this;}
    
    public TextureCoordinate set(double x, double y) {return this.setX(x).setY(y);}
    
    public Location toLocation() {
        return new Location(this);
    }
    
    public Vertice toVertice() {
        return new Vertice(this);
    }
    
    public Normal toNormal() {
        return new Normal(this);
    }
    
    public Vector toVector() {
        return new Vector(this);
    }
    
    @Override
    public String toString () {
        return this.getX() + "," + this.getY();
    }
    
    @Override
    public TextureCoordinate clone() {
        return new TextureCoordinate(this);
    }
    
}
