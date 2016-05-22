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

package com.domsplace.FaultEngine.Color;

import com.domsplace.FaultEngine.Clone.ICloneable;
import java.util.Random;

/**
 *
 * @author Dominic
 */
public final class Color implements ICloneable<Color> {
    public static Color BLACK = Color.fromHex("#000000");
    public static Color BLUE = Color.fromHex("#0000FF");
    public static Color GREEN = Color.fromHex("#00FF00");
    public static Color WHITE = Color.fromHex("#FFFFFF");
    public static Color PINK = Color.fromHex("#FF00FF");
    public static Color GRAY = Color.fromHex("#CCCCCC");
    public static Color RED = Color.fromHex("#FF0000");
    public static Color CORNFLOWER_BLUE = Color.fromHex("#6495ED");
    public static Color YELLOW = Color.fromHex("#FFFF00");
    public static Color ORANGE = Color.fromHex("#E8A84F");
    public static Color DARK_GRAY = Color.fromHex("#333333");
    public static Color DARK_GREEN = Color.fromHex("#00750C");
    public static Color CYAN = Color.fromHex("#00FFFF");
    public static Color GOLD = Color.fromHex("#FFD700");
    public static Color SKY_BLUE = Color.fromHex("#87CEEB");
    public static Color TREE_TRUNK_BROWN = Color.fromHex("#53350A");
    public static Color SAND_YELLOW = Color.fromHex("#EFDD6F");
    public static Color DARK_BROWN = Color.fromHex("#4D3100");
    public static Color DARK_BLUE = Color.fromHex("#000080");
                
    public static Color fromHex(String hex) {
        if(!hex.startsWith("#")) hex = "#" + hex;
        hex = hex.toUpperCase();
        int r = Integer.valueOf(hex.substring( 1, 3 ), 16 );
        int g = Integer.valueOf(hex.substring( 3, 5 ), 16 );
        int b = Integer.valueOf(hex.substring( 5, 7 ), 16 );
        return new Color(r,g,b);
    }

    public static Color randomColor() {
        int r = new Random().nextInt() * 255;
        int g = new Random().nextInt() * 255;
        int b = new Random().nextInt() * 255;
        return new Color(r,g,b);
    }
    
    //Instance
    private double red;
    private double green;
    private double blue;
    private double alpha;
    
    public Color(double r, double g, double b, double a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }
    
    public Color(int r, int g, int b, int a) {
        this(
                ((double) r) / 255.0d,
                ((double) g) / 255.0d,
                ((double) b) / 255.0d,
                ((double) a) / 100.0d
        );
    }
    
    public Color(double r, double g, double b) {
        this(r,g,b,1);
    }
    
    public Color(int r, int g, int b) {
        this(r, g, b, 100);
    }
    
    public Color(int hex) {
        this((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
    }
    
    public Color(Color color) {
        this(color.red, color.green, color.blue, color.alpha);
    }
    
    public double getRed() {return this.red;}
    public double getGreen() {return this.green;}
    public double getBlue() {return this.blue;}
    public double getAlpha() {return this.alpha;}
    
    public Color setRed(double r) {this.red = r; return this;}
    public Color setGreen(double g) {this.green = g; return this;}
    public Color setBlue(double b) {this.blue = b; return this;}
    public Color setAlpha(double a) {this.red = a; return this;}
    
    public boolean isTransparent() {return alpha <= 0;}
    public boolean isOpaque() {return alpha < 1;}
    
    @Override
    public Color clone() {
        return new Color(this);
    }
}
