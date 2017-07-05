package org.liquidengine.legui.color;

import org.joml.Vector4f;
import org.joml.Vector4fc;

/**
 * Contains predefined color values constants and constructors.
 */
public final class ColorConstants {

    //@formatter:off
    /** Red immutable color vector */
    public static final Vector4fc RED         = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);
    /** Red immutable color vector */
    public static final Vector4fc red         = RED;
    /** Green immutable color vector */
    public static final Vector4fc GREEN       = new Vector4f(0.0f, 1.0f, 0.0f, 1.0f);
    /** Green immutable color vector */
    public static final Vector4fc green       = GREEN;
    /** Blue immutable color vector */
    public static final Vector4fc BLUE        = new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
    /** Blue immutable color vector */
    public static final Vector4fc blue        = BLUE;
    /** Dark-red immutable color vector */
    public static final Vector4fc DARK_RED    = new Vector4f(0.6f, 0.0f, 0.0f, 1.0f);
    /** Dark-red immutable color vector */
    public static final Vector4fc darkRed     = DARK_RED;
    /** Dark-green immutable color vector */
    public static final Vector4fc DARK_GREEN  = new Vector4f(0.0f, 0.6f, 0.0f, 1.0f);
    /** Dark-green immutable color vector */
    public static final Vector4fc darkGreen   = DARK_GREEN;
    /** Dark-blue immutable color vector */
    public static final Vector4fc DARK_BLUE   = new Vector4f(0.0f, 0.0f, 0.6f, 1.0f);
    /** Dark-blue immutable color vector */
    public static final Vector4fc darkBlue    = DARK_BLUE;
    /** Light-red immutable color vector */
    public static final Vector4fc LIGHT_RED   = new Vector4f(1.0f, 0.3f, 0.3f, 1.0f);
    /** Light-red immutable color vector */
    public static final Vector4fc lightRed    = LIGHT_RED;
    /** Light-green immutable color vector */
    public static final Vector4fc LIGHT_GREEN = new Vector4f(0.3f, 1.0f, 0.3f, 1.0f);
    /** Light-green immutable color vector */
    public static final Vector4fc lightGreen  = LIGHT_GREEN;
    /** Light-blue immutable color vector */
    public static final Vector4fc LIGHT_BLUE  = new Vector4f(0.6f, 0.7f, 1.0f, 1.0f);
    /** Light-blue immutable color vector */
    public static final Vector4fc lightBlue   = LIGHT_BLUE;
    /** Black immutable color vector */
    public static final Vector4fc BLACK       = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    /** Black immutable color vector */
    public static final Vector4fc black       = BLACK;
    /** Light-black immutable color vector */
    public static final Vector4fc LIGHT_BLACK = new Vector4f(0.1f, 0.1f, 0.1f, 1.0f);
    /** Light-black immutable color vector */
    public static final Vector4fc lightBlack  = LIGHT_BLACK;
    /** White immutable color vector */
    public static final Vector4fc WHITE       = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    /** White immutable color vector */
    public static final Vector4fc white       = WHITE;
    /** Transparent immutable color vector */
    public static final Vector4fc TRANSPARENT = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
    /** Transparent immutable color vector */
    public static final Vector4fc transparent = TRANSPARENT;
    /** Light-gray immutable color vector */
    public static final Vector4fc LIGHT_GRAY  = new Vector4f(0.8f, 0.8f, 0.8f, 1.0f);
    /** Light-gray immutable color vector */
    public static final Vector4fc lightGray   = LIGHT_GRAY;
    /** Dark-gray immutable color vector */
    public static final Vector4fc DARK_GRAY   = new Vector4f(0.2f, 0.2f, 0.2f, 1.0f);
    /** Dark-gray immutable color vector */
    public static final Vector4fc darkGray    = DARK_GRAY;
    /** Gray immutable color vector */
    public static final Vector4fc GRAY        = new Vector4f(0.5f, 0.5f, 0.5f, 1.0f);
    /** Gray immutable color vector */
    public static final Vector4fc gray        = GRAY;

    private                 ColorConstants() {  }
    /** Red color vector */
    public  static Vector4f red           () { return new Vector4f(red        ); }
    /** Green color vector */
    public  static Vector4f green         () { return new Vector4f(green      ); }
    /** Blue color vector */
    public  static Vector4f blue          () { return new Vector4f(blue       ); }
    /** Dark-red color vector */
    public  static Vector4f darkRed       () { return new Vector4f(darkRed    ); }
    /** Dark-green color vector */
    public  static Vector4f darkGreen     () { return new Vector4f(darkGreen  ); }
    /** Dark-blue color vector */
    public  static Vector4f darkBlue      () { return new Vector4f(darkBlue   ); }
    /** Light-red color vector */
    public  static Vector4f lightRed      () { return new Vector4f(lightRed   ); }
    /** Light-green color vector */
    public  static Vector4f lightGreen    () { return new Vector4f(lightGreen ); }
    /** Light-blue color vector */
    public  static Vector4f lightBlue     () { return new Vector4f(lightBlue  ); }
    /** Black color vector */
    public  static Vector4f black         () { return new Vector4f(black      ); }
    /** Light-black color vector */
    public  static Vector4f lightBlack    () { return new Vector4f(lightBlack ); }
    /** White color vector */
    public  static Vector4f white         () { return new Vector4f(white      ); }
    /** Transparent color vector */
    public  static Vector4f transparent   () { return new Vector4f(transparent); }
    /** Light-gray color vector */
    public  static Vector4f lightGray     () { return new Vector4f(lightGray  ); }
    /** Dark-gray color vector */
    public  static Vector4f darkGray      () { return new Vector4f(darkGray   ); }
    /** Gray color vector */
    public  static Vector4f gray          () { return new Vector4f(gray       ); }
    //@formatter:on
}
