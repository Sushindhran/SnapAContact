package com.infy.snapacontact.activity;

import com.infy.snapacontact.util.SoundLoadClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class StartActivity.
 */
public class StartActivity extends Activity {

	/** The tag. */
	private String TAG = StartActivity.class.getSimpleName();
	
	/** The snap shot view. */
	private ImageView snapShotView;
	
	/** The help view. */
	private ImageView helpView;
	
	/** The slide show. */
	private static ImageView slideShow;
	
	/** The images. */
	private static int images[] = {R.drawable.welcome,R.drawable.camera,R.drawable.contact,R.drawable.mobile};
	
	/** The image count. */
	private static int imageCount = 0;
	
	/** The context. */
	private static Context context;
	
	/** The refresh handler. */
	private static RefreshHandler refreshHandler=new RefreshHandler();
	
	/** The sound. */
	private static SoundLoadClass sound;
	
	/** The play flag. */
	private static boolean playFlag = true;
	
	/** The activity flag. */
	private static boolean activityFlag = true;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		context = this;
		sound = new SoundLoadClass(context);
		snapShotView = (ImageView)findViewById(R.id.snapBegin);
		helpView = (ImageView)findViewById(R.id.help);
		slideShow = (ImageView) findViewById(R.id.welcome);

		snapShotView.setOnClickListener(new OpenOcrActivity());
		helpView.setOnClickListener(new OpenHelpActivity());

		updateUI();
	}

	/**
	 * The Class OpenOcrActivity.
	 */
	public class OpenOcrActivity implements View.OnClickListener {
		
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View view) {
			Log.v(TAG, "Starting Main App");
			context.startActivity(new Intent(context,OCRActivity.class));
			activityFlag = false;
		}
	}

	/**
	 * The Class OpenHelpActivity.
	 */
	public class OpenHelpActivity implements View.OnClickListener {
		
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View view) {
			Log.v(TAG, "Starting Help Activity");
			context.startActivity(new Intent(context,FirstHelpActivity.class));
			activityFlag = false;
		}
	}

	/**
	 * The Class RefreshHandler.
	 */
	static class RefreshHandler extends Handler{
		
		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			StartActivity.updateUI();
		}
		
		/**
		 * Sleep.
		 *
		 * @param delayMillis the delay millis
		 */
		public void sleep(long delayMillis){
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	/**
	 * Update ui.
	 */
	public static void updateUI(){
		refreshHandler.sleep(2000);
		if(imageCount<images.length){

			slideShow.setImageResource(images[imageCount]);

			if((imageCount == 1 || imageCount == 2 || imageCount == 3) && (playFlag) && (activityFlag)) {
				sound.playSound(context, imageCount);
			}

			imageCount++;

			if(imageCount==4) {
				playFlag = false;
				imageCount=0;
			}
		}
	}
	
	/**
	 * Initialize.
	 */
	public static void initialize() {
		imageCount = 0;
		context = null;
		sound = null;
		playFlag = true;
		activityFlag = true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		refreshHandler.removeMessages(0);
		initialize();
		finish();
	}
}
