/*
 * Copyright (C) 2009 The Android Open Source Project
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

package com.infy.snapacontact.util;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The interface of all images used in gallery.
 */
public interface IImage {
    
    /** The Constant THUMBNAIL_TARGET_SIZE. */
    static final int THUMBNAIL_TARGET_SIZE = 320;
    
    /** The Constant MINI_THUMB_TARGET_SIZE. */
    static final int MINI_THUMB_TARGET_SIZE = 96;
    
    /** The Constant UNCONSTRAINED. */
    static final int UNCONSTRAINED = -1;

    /**
     * Get the image list which contains this image.
     *
     * @return the container
     */
    public abstract IImageList getContainer();

    /**
     * Get the bitmap for the full size image.
     *
     * @param minSideLength the min side length
     * @param maxNumberOfPixels the max number of pixels
     * @return the bitmap
     */
    public abstract Bitmap fullSizeBitmap(int minSideLength,
            int maxNumberOfPixels);
    
    /**
     * Full size bitmap.
     *
     * @param minSideLength the min side length
     * @param maxNumberOfPixels the max number of pixels
     * @param rotateAsNeeded the rotate as needed
     * @return the bitmap
     */
    public abstract Bitmap fullSizeBitmap(int minSideLength,
            int maxNumberOfPixels, boolean rotateAsNeeded);
    
    /**
     * Full size bitmap.
     *
     * @param minSideLength the min side length
     * @param maxNumberOfPixels the max number of pixels
     * @param rotateAsNeeded the rotate as needed
     * @param useNative the use native
     * @return the bitmap
     */
    public abstract Bitmap fullSizeBitmap(int minSideLength,
            int maxNumberOfPixels, boolean rotateAsNeeded, boolean useNative);
    
    /**
     * Gets the degrees rotated.
     *
     * @return the degrees rotated
     */
    public abstract int getDegreesRotated();
    
    /** The Constant ROTATE_AS_NEEDED. */
    public static final boolean ROTATE_AS_NEEDED = true;
    
    /** The Constant NO_ROTATE. */
    public static final boolean NO_ROTATE = false;
    
    /** The Constant USE_NATIVE. */
    public static final boolean USE_NATIVE = true;
    
    /** The Constant NO_NATIVE. */
    public static final boolean NO_NATIVE = false;

    /**
     * Get the input stream associated with a given full size image.
     *
     * @return the input stream
     */
    public abstract InputStream fullSizeImageData();
    
    /**
     * Full size image id.
     *
     * @return the long
     */
    public abstract long fullSizeImageId();
    
    /**
     * Full size image uri.
     *
     * @return the uri
     */
    public abstract Uri fullSizeImageUri();

    /**
     * Get the path of the (full size) image data.
     *
     * @return the data path
     */
    public abstract String getDataPath();

    // Get/Set the title of the image
    /**
     * Sets the title.
     *
     * @param name the new title
     */
    public abstract void setTitle(String name);
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public abstract String getTitle();

    // Get metadata of the image
    /**
     * Gets the date taken.
     *
     * @return the date taken
     */
    public abstract long getDateTaken();

    /**
     * Gets the mime type.
     *
     * @return the mime type
     */
    public abstract String getMimeType();

    /**
     * Gets the width.
     *
     * @return the width
     */
    public abstract int getWidth();

    /**
     * Gets the height.
     *
     * @return the height
     */
    public abstract int getHeight();

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public abstract String getDisplayName();

    // Get property of the image
    /**
     * Checks if is readonly.
     *
     * @return true, if is readonly
     */
    public abstract boolean isReadonly();
    
    /**
     * Checks if is drm.
     *
     * @return true, if is drm
     */
    public abstract boolean isDrm();

    // Get the bitmap/uri of the medium thumbnail
    /**
     * Thumb bitmap.
     *
     * @param rotateAsNeeded the rotate as needed
     * @return the bitmap
     */
    public abstract Bitmap thumbBitmap(boolean rotateAsNeeded);
    
    /**
     * Thumb uri.
     *
     * @return the uri
     */
    public abstract Uri thumbUri();

    // Get the bitmap of the mini thumbnail.
    /**
     * Mini thumb bitmap.
     *
     * @return the bitmap
     */
    public abstract Bitmap miniThumbBitmap();

    // Rotate the image
    /**
     * Rotate image by.
     *
     * @param degrees the degrees
     * @return true, if successful
     */
    public abstract boolean rotateImageBy(int degrees);

}
