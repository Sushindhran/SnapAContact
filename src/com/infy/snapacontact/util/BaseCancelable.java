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

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

// TODO: Auto-generated Javadoc
/**
 * An abstract class for the interface <code>Cancelable</code>. Subclass can
 * simply override the <code>execute()</code> function to provide an
 * implementation of <code>Cancelable</code>.
 *
 * @param <T> the generic type
 */
public abstract class BaseCancelable<T> implements Cancelable<T> {

    /** The state of the task, possible transitions are: <pre> INITIAL -> CANCELED EXECUTING -> COMPLETE, CANCELING, ERROR, CANCELED CANCELING -> CANCELED </pre> When the task stop, it must be end with one of the following states: COMPLETE, CANCELED, or ERROR;. */
    private static final int STATE_INITIAL = (1 << 0);
    
    /** The Constant STATE_EXECUTING. */
    private static final int STATE_EXECUTING = (1 << 1);
    
    /** The Constant STATE_CANCELING. */
    private static final int STATE_CANCELING = (1 << 2);
    
    /** The Constant STATE_CANCELED. */
    private static final int STATE_CANCELED = (1 << 3);
    
    /** The Constant STATE_ERROR. */
    private static final int STATE_ERROR = (1 << 4);
    
    /** The Constant STATE_COMPLETE. */
    private static final int STATE_COMPLETE = (1 << 5);

    /** The m state. */
    private int mState = STATE_INITIAL;

    /** The m error. */
    private Throwable mError;
    
    /** The m result. */
    private T mResult;
    
    /** The m current task. */
    private Cancelable<?> mCurrentTask;
    
    /** The m thread. */
    private Thread mThread;

    /**
     * Execute.
     *
     * @return the t
     * @throws Exception the exception
     */
    protected abstract T execute() throws Exception;

    /**
     * Interrupt now.
     */
    protected synchronized void interruptNow() {
        if (isInStates(STATE_CANCELING | STATE_EXECUTING)) {
            mThread.interrupt();
        }
    }

    /**
     * Frees the result (which is not null) when the task has been canceled.
     *
     * @param result the result
     */
    protected void freeCanceledResult(T result) {
        // Do nothing by default;
    }

    /**
     * Checks if is in states.
     *
     * @param states the states
     * @return true, if is in states
     */
    private boolean isInStates(int states) {
        return (states & mState) != 0;
    }

    /**
     * Handle terminal states.
     *
     * @return the t
     * @throws ExecutionException the execution exception
     */
    private T handleTerminalStates() throws ExecutionException {
        if (mState == STATE_CANCELED) {
            throw new CancellationException();
        }
        if (mState == STATE_ERROR) {
            throw new ExecutionException(mError);
        }
        if (mState == STATE_COMPLETE) return mResult;
        throw new IllegalStateException();
    }

    /* (non-Javadoc)
     * @see com.infy.snapacontact.util.Cancelable#await()
     */
    public synchronized void await() throws InterruptedException {
        while (!isInStates(STATE_COMPLETE | STATE_CANCELED | STATE_ERROR)) {
            wait();
        }
    }

    /* (non-Javadoc)
     * @see com.infy.snapacontact.util.Cancelable#get()
     */
    public final T get() throws InterruptedException, ExecutionException {
        synchronized (this) {
            if (mState != STATE_INITIAL) {
                await();
                return handleTerminalStates();
            }
            mThread = Thread.currentThread();
            mState = STATE_EXECUTING;
        }
        try {
            mResult = execute();
        } catch (CancellationException e) {
            mState = STATE_CANCELED;
        } catch (InterruptedException e) {
            mState = STATE_CANCELED;
        } catch (Throwable error) {
            synchronized (this) {
                if (mState != STATE_CANCELING) {
                    mError = error;
                    mState = STATE_ERROR;
                }
            }
        }
        synchronized (this) {
            if (mState == STATE_CANCELING) mState = STATE_CANCELED;
            if (mState == STATE_EXECUTING) mState = STATE_COMPLETE;
            notifyAll();
            if (mState == STATE_CANCELED && mResult != null) {
                freeCanceledResult(mResult);
            }
            return handleTerminalStates();
        }
    }

    /**
     * Requests the task to be canceled.
     *
     * @return true if the task is running and has not been canceled; false
     *     otherwise
     */

    public synchronized boolean requestCancel() {
        if (mState == STATE_INITIAL) {
            mState = STATE_CANCELED;
            notifyAll();
            return false;
        }
        if (mState == STATE_EXECUTING) {
            if (mCurrentTask != null) mCurrentTask.requestCancel();
            mState = STATE_CANCELING;
            return true;
        }
        return false;
    }

    /**
     * Whether the task's has been requested for cancel.
     *
     * @return true, if is canceling
     */
    protected synchronized boolean isCanceling() {
        return mState == STATE_CANCELING;
    }

    /**
     * Runs a <code>Cancelable</code> subtask. This method is helpful, if the
     * task can be composed of several cancelable tasks. By using this function,
     * it will pass <code>requestCancel</code> message to those subtasks.
     *
     * @param <T> the return type of the sub task
     * @param cancelable the sub task
     * @return the result of the subtask
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    protected <T> T runSubTask(Cancelable<T> cancelable)
            throws InterruptedException, ExecutionException {
        synchronized (this) {
            if (mCurrentTask != null) {
                throw new IllegalStateException(
                        "cannot two subtasks at the same time");
            }
            if (mState == STATE_CANCELING) throw new CancellationException();
            mCurrentTask = cancelable;
        }
        try {
            return cancelable.get();
        } finally {
            synchronized (this) {
                mCurrentTask = null;
            }
        }
    }

}
