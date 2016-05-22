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

package com.domsplace.FaultEngine.Location;

import com.domsplace.FaultEngine.Clone.ICloneable;
import com.domsplace.FaultEngine.Display.Normal.Normal;
import com.domsplace.FaultEngine.Display.Texture.TextureCoordinate;
import com.domsplace.FaultEngine.Display.Vertice.Vertice;
import com.domsplace.FaultEngine.Dispose.IDisposeable;
import com.domsplace.FaultEngine.Utilities.MathUtils;
import com.domsplace.FaultEngine.Vector.Vector;

/**
 *
 * @author Dominic
 */
public final class Location implements ICloneable<Location>, ILocateable, IDisposeable {
    private static int LOCATION_INSTANCES = 0;//Used for Memory Watching
    public static int getRegisteredLocations() {
        return LOCATION_INSTANCES;
    }
    
    
    //Instance
    private double x;
    private double y;
    private double z;
    private double pitch;
    private double yaw;
    private double roll;
    
    private ILocateable parent;
    
    public Location(double x, double y, double z, double pitch, double yaw, double roll) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        Location.LOCATION_INSTANCES++;
    }
    
    public Location(Location location) {
        this(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw(), location.getRoll());
    }
    
    public Location(double x, double y, double z) {
        this(x, y, z, 0, 0, 0);
    }
    
    public Location(double x, double y) {
        this(x,y,0);
    }
    
    public Location() {
        this(0,0);
    }
    
    public Location(ILocateable l) {
        this();
        this.setParent(l);
    }
    
    public Location(Normal n) {
        this(n.getX(), n.getY(), n.getZ());
    }
    
    public Location(Vertice v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    
    public Location(TextureCoordinate coordinate) {
        this(coordinate.getX(), coordinate.getY());
    }
    
    public Location(Vector v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public double getZ() {return this.z;}
    public double getPitch() {return this.pitch;}
    public double getYaw() {return this.yaw;}
    public double getRoll() {return this.roll;}
    public boolean hasParent() {return this.getParent() != null;}
    @Override public Location getLocation() {return this;}
    @Override public ILocateable getParent() {return this.parent;}
    
    public Location setX(double x) {this.x = x; return this;}
    public Location setY(double y) {this.y = y; return this;}
    public Location setZ(double z) {this.z = z; return this;}
    public Location setPitch(double pitch) {this.pitch = pitch; return this;}
    public Location setYaw(double yaw) {this.yaw = yaw; return this;}
    public Location setRoll(double roll) {this.roll = roll; return this;}
    public Location setParent(ILocateable parent) {this.parent = parent; return this;}
		
    public Location addX(double x) {this.x += x; return this;}
    public Location addY(double y) {this.y += y; return this;}
    public Location addZ(double z) {this.z += z; return this;}
    public Location addYaw(double yaw) {this.yaw += yaw; return this;}
    public Location addPitch(double pitch) {this.pitch += pitch; return this;}
    public Location addRoll(double roll) {this.roll += roll; return this;}
    
    //Advaned GET Methods
    public Location getAbsoluteLocation () {
        if(this.getParent() != null && this.getParent().getParent() != null) {
            Location parentAbs = this.getParent().getParent().getLocation().getAbsoluteLocation();
            Location l = this.clone().add(parentAbs);
            parentAbs.dispose();
            //if(!this.Equals(this.getOwner().getLocation())) l.add (this);
            return l;
        }
        return this.clone();
    }
    
    public double getDistance(Location between) {
        Location abs = this.getAbsoluteLocation();
        Location bAbs = between.getAbsoluteLocation();
        double dist = Math.sqrt(
            Math.pow(abs.x - bAbs.x, 2) +
            Math.pow(abs.y - bAbs.y, 2) +
            Math.pow(abs.z - bAbs.z, 2)
        );

        abs.dispose();
        bAbs.dispose();

        return dist;
    }

    public double getDistanceX(Location between) {
        Location abs = this.getAbsoluteLocation();
        Location bAbs = between.getAbsoluteLocation();
        double dist = bAbs.x - abs.x;
        abs.dispose();
        bAbs.dispose();
        return dist;
    }
    
    public double getDistanceY(Location between) {
        Location abs = this.getAbsoluteLocation();
        Location bAbs = between.getAbsoluteLocation();
        double dist = bAbs.y - abs.y;
        abs.dispose();
        bAbs.dispose();
        return dist;
    }
    
    public double getDistanceZ(Location between) {
        Location abs = this.getAbsoluteLocation();
        Location bAbs = between.getAbsoluteLocation();
        double dist = bAbs.z - abs.z;
        abs.dispose();
        bAbs.dispose();
        return dist;
    }

    public Location getRelative(double x, double y, double z) {
        return this.getAbsoluteLocation().add(x, y, z);
    }

    public Location getRelativeInFacingDirection(double distance) {
            double pitch = this.getPitch();
            double yaw = this.getYaw();
            while(pitch > 360) pitch -= 360;
            while(pitch < 0) pitch += 360;
            while(yaw > 360) yaw -= 360;
            while(yaw < 0) yaw += 360;

            pitch = MathUtils.toRadians(pitch + 0.00001);
            yaw = MathUtils.toRadians(yaw + 0.00001);

            double adjacent = Math.cos(pitch) *  distance;
            double opposite = Math.tan(pitch) * adjacent;

            double z = (Math.cos(yaw) * adjacent);
            double x = Math.tan(yaw) * z;

            Location t = this.getAbsoluteLocation();
            t.addX(x);
            t.addY(-opposite);
            t.addZ(z);
            return t;
    }
    
    public double getLookAtPitch(Location target) {return this.getLookAtPitch(target, 90);}
    public double getLookAtPitch(Location target, double offset) {
        double dX = this.getDistanceX(target);
        double dY = this.getDistanceY(target);
        double dZ = this.getDistanceZ(target);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        pitch = MathUtils.toDegrees(pitch) + offset;
        if(this.getParent() != null && this.getParent().getParent() != null) {
            Location pAbs = this.getParent().getParent().getLocation().getAbsoluteLocation();
            pitch -= pAbs.getPitch();
            pAbs.dispose();
        }
        return pitch;
    }

    public double getLookAtYaw(Location target) {return this.getLookAtYaw(target, 90);}
    public double getLookAtYaw(Location target, double offset) {
        double dX = this.getDistanceX(target);
        double dZ = this.getDistanceZ(target);
        double yaw = Math.atan2(dZ, dX);
        yaw = (-MathUtils.toDegrees(yaw)) + offset;
        if(this.getParent() != null && this.getParent().getParent() != null) {
            Location pAbs = this.getParent().getParent().getLocation().getAbsoluteLocation();
            yaw -= pAbs.getYaw();
            pAbs.dispose();
        }
        return yaw;
    }
    
    //Advanced SET Methods
    public Location set(double x, double y, double z, double pitch, double yaw, double roll) {
        return this.setX(x).setY(y).setZ(z).setPitch(pitch).setYaw(yaw).setRoll(roll);
    }
    
    public Location set(double x, double y, double z) {
        return this.setX(x).setY(y).setZ(z);
    }
    
    public Location set(Location l) {this.x = l.x; this.y = l.y; this.z = l.z; this.pitch = l.pitch; this.yaw = l.yaw; this.roll = l.roll; return this;}
    public Location set(ILocateable l) {return set (l.getLocation());}
    
    //Advanced ADD Methods
    public Location add(Location l) {if(l == null) {return this;} this.x += l.x; this.y += l.y; this.z += l.z; this.pitch += l.pitch; this.yaw += l.yaw; this.roll += l.roll; return this;}
    public Location add(double x, double y, double z) {this.x += x; this.y += y; this.z += z; return this;}
    
    //Advanced SUB Methods
    public Location sub(Location l) {if(l == null) {return this;} this.x -= l.x; this.y -= l.y; this.z -= l.z; this.pitch -= l.pitch; this.yaw -= l.yaw; this.roll -= l.roll; return this;}
    
    
    //Other Methods
    public Location lookAt(Location l) {
        return this.lookAt(l, 1.0);
    }

    public Location lookAt(Location l, double multiplier) {
        return this.yawLookAt(l, multiplier).pitchLookAt(l, multiplier);
    }

    public Location pitchLookAt(Location l) {return this.pitchLookAt(l, 1.0);}
    public Location pitchLookAt(Location l, double multiplier) {
        double pitch = this.getLookAtPitch(l);
        pitch *= multiplier;
        this.setPitch(pitch);
        return this;
    }
    
    public Location yawLookAt(Location l) {return this.yawLookAt(l, 1.0);}
    public Location yawLookAt(Location l, double multiplier) {
        double yaw = this.getLookAtYaw(l);
        yaw *= multiplier;
        this.setYaw(yaw);
        return this;
    }
    
    public Location limit(double amt) {
        if(this.x > amt) this.x = amt;
        if(this.y > amt) this.y = amt;
        if(this.z > amt) this.z = amt;
        if(this.x < -amt) this.x = -amt;
        if(this.y < -amt) this.y = -amt;
        if(this.z < -amt) this.z = -amt;
        return this;
    }
    
    public Vertice toVertice() {
        return new Vertice(this);
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
        return this.getX() + "," + this.getY() + "," + this.getZ () + "," + this.getYaw() + "," + 
            this.getPitch() + "," + this.getRoll();
    }
    
    @Override
    public Location clone() {
        return new Location(this);
    }
    
    @Override
    public void dispose() {
        this.parent = null;
        this.set(0,0,0,0,0,0);
        Location.LOCATION_INSTANCES--;
    }
}
