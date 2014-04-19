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

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class MonitoredActivity.
 */
public class MonitoredActivity extends Activity {

    /** The m listeners. */
    private final ArrayList<LifeCycleListener> mListeners =
            new ArrayList<LifeCycleListener>();

    /**
     * The listener interface for receiving lifeCycle events.
     * The class that is interested in processing a lifeCycle
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addLifeCycleListener<code> method. When
     * the lifeCycle event occurs, that object's appropriate
     * method is invoked.
     *
     * @see LifeCycleEvent
     */
    public static interface LifeCycleListener {
        
        /**
         * Invoked when on activity is created.
         *
         * @param activity the activity
         */
        public void onActivityCreated(MonitoredActivity activity);
        
        /**
         * On activity destroyed.
         *
         * @param activity the activity
         */
        public void onActivityDestroyed(MonitoredActivity activity);
        
        /**
         * On activity paused.
         *
         * @param activity the activity
         */
        public void onActivityPaused(MonitoredActivity activity);
        
        /**
         * On activity resumed.
         *
         * @param activity the activity
         */
        public void onActivityResumed(MonitoredActivity activity);
        
        /**
         * On activity started.
         *
         * @param activity the activity
         */
        public void onActivityStarted(MonitoredActivity activity);
        
        /**
         * On activity stopped.
         *
         * @param activity the activity
         */
        public void onActivityStopped(MonitoredActivity activity);
    }

    /**
     * The Class LifeCycleAdapter.
     */
    public static class LifeCycleAdapter implements LifeCycleListener {
        
        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityCreated(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityCreated(MonitoredActivity activity) {
        }

        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityDestroyed(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityDestroyed(MonitoredActivity activity) {
        }

        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityPaused(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityPaused(MonitoredActivity activity) {
        }

        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityResumed(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityResumed(MonitoredActivity activity) {
        }

        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityStarted(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityStarted(MonitoredActivity activity) {
        }

        /* (non-Javadoc)
         * @see com.infy.snapacontact.util.MonitoredActivity.LifeCycleListener#onActivityStopped(com.infy.snapacontact.util.MonitoredActivity)
         */
        public void onActivityStopped(MonitoredActivity activity) {
        }
    }

    /**
     * Adds the life cycle listener.
     *
     * @param listener the listener
     */
    public void addLifeCycleListener(LifeCycleListener listener) {
        if (mListeners.contains(listener)) return;
        mListeners.add(listener);
    }

    /**
     * Removes the life cycle listener.
     *
     * @param listener the listener
     */
    public void removeLifeCycleListener(LifeCycleListener listener) {
        mListeners.remove(listener);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityCreated(this);
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityDestroyed(this);
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityStarted(this);
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityStopped(this);
        }
    }
}
