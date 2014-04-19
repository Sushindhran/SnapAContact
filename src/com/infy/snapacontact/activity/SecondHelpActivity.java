package com.infy.snapacontact.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class SecondHelpActivity.
 */
public class SecondHelpActivity extends Activity {
	
	/** The previous button. */
	private ImageView previousButton;
	
	/** The context. */
	private Context context;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		context = this;
		setContentView(R.layout.activity_help_second);
		previousButton =  (ImageView) findViewById(R.id.previousActivity);
		previousButton.setOnClickListener(new PreviousActivityButtonhandler());
	}
	
	/**
	 * The Class PreviousActivityButtonhandler.
	 */
	public class PreviousActivityButtonhandler implements View.OnClickListener {
		
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View view) {
			context.startActivity(new Intent(context,FirstHelpActivity.class));
			finish();
		}
	}
}
