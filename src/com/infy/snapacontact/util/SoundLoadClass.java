package com.infy.snapacontact.util;

import com.infy.snapacontact.activity.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

// TODO: Auto-generated Javadoc
/**
 * The Class SoundLoadClass.
 */
public class SoundLoadClass {
	
	/** The sound pool. */
	private SoundPool soundPool;
	
	/** The sound id. */
	private int soundId;
	
	/** The sound loaded. */
	private boolean soundLoaded = false;
	
	/** The camera sound. */
	private int cameraSound;
	
	/** The contact sound. */
	private int contactSound;
	
	/** The phone sound. */
	private int phoneSound;
	
	/** The actual volume. */
	private float actualVolume;
	
	/** The max volume. */
	private float maxVolume;
	
	/** The volume. */
	private float volume;
	
	/**
	 * Instantiates a new sound load class.
	 *
	 * @param context the context
	 */
	public SoundLoadClass(Context context) {
		
		AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = actualVolume / maxVolume;
		
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				// TODO Auto-generated method stub
				soundLoaded = true;
			}
		});
		//soundId = soundPool.load(context, resId, 1);
		
		cameraSound = soundPool.load(context, R.raw.camera_sound, 1);
		contactSound = soundPool.load(context, R.raw.contact_sound, 1);
		phoneSound = soundPool.load(context, R.raw.phone_sound, 1);
	}
	
	/**
	 * Play sound.
	 *
	 * @param context the context
	 * @param imageCount the image count
	 */
	public void playSound(Context context, int imageCount) {
		
		// Is the sound loaded already?
		if (soundLoaded) {
			if (imageCount == 1) {
				soundPool.play(cameraSound, volume, volume, 1, 0, 1f);
			}
			else if (imageCount == 2) {
				soundPool.play(contactSound, volume, volume, 1, 0, 1f);
			}
			else {
				soundPool.play(phoneSound, volume, volume, 1, 0, 1f);
			}
		}
	}
	
	/**
	 * Gets the sound pool.
	 *
	 * @return the sound pool
	 */
	public SoundPool getSoundPool() {
		return soundPool;
	}
	
	/**
	 * Sets the sound pool.
	 *
	 * @param soundPool the new sound pool
	 */
	public void setSoundPool(SoundPool soundPool) {
		this.soundPool = soundPool;
	}
	
	/**
	 * Gets the sound id.
	 *
	 * @return the sound id
	 */
	public int getSoundId() {
		return soundId;
	}
	
	/**
	 * Sets the sound id.
	 *
	 * @param soundId the new sound id
	 */
	public void setSoundId(int soundId) {
		this.soundId = soundId;
	}
	
	/**
	 * Checks if is sound loaded.
	 *
	 * @return true, if is sound loaded
	 */
	public boolean isSoundLoaded() {
		return soundLoaded;
	}
	
	/**
	 * Sets the sound loaded.
	 *
	 * @param soundLoaded the new sound loaded
	 */
	public void setSoundLoaded(boolean soundLoaded) {
		this.soundLoaded = soundLoaded;
	}
	
	/**
	 * Gets the camera sound.
	 *
	 * @return the camera sound
	 */
	public int getCameraSound() {
		return cameraSound;
	}
	
	/**
	 * Sets the camera sound.
	 *
	 * @param cameraSound the new camera sound
	 */
	public void setCameraSound(int cameraSound) {
		this.cameraSound = cameraSound;
	}
	
	/**
	 * Gets the contact sound.
	 *
	 * @return the contact sound
	 */
	public int getContactSound() {
		return contactSound;
	}
	
	/**
	 * Sets the contact sound.
	 *
	 * @param contactSound the new contact sound
	 */
	public void setContactSound(int contactSound) {
		this.contactSound = contactSound;
	}
	
	/**
	 * Gets the phone sound.
	 *
	 * @return the phone sound
	 */
	public int getPhoneSound() {
		return phoneSound;
	}
	
	/**
	 * Sets the phone sound.
	 *
	 * @param phoneSound the new phone sound
	 */
	public void setPhoneSound(int phoneSound) {
		this.phoneSound = phoneSound;
	}
	
	/**
	 * Gets the actual volume.
	 *
	 * @return the actual volume
	 */
	public float getActualVolume() {
		return actualVolume;
	}
	
	/**
	 * Sets the actual volume.
	 *
	 * @param actualVolume the new actual volume
	 */
	public void setActualVolume(float actualVolume) {
		this.actualVolume = actualVolume;
	}
	
	/**
	 * Gets the max volume.
	 *
	 * @return the max volume
	 */
	public float getMaxVolume() {
		return maxVolume;
	}
	
	/**
	 * Sets the max volume.
	 *
	 * @param maxVolume the new max volume
	 */
	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}
	
	/**
	 * Gets the volume.
	 *
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}
	
	/**
	 * Sets the volume.
	 *
	 * @param volume the new volume
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
}
