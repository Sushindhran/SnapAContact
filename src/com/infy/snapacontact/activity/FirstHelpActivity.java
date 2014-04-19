package com.infy.snapacontact.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * Description : This is the Activity Class corresponding to the first Help page.
 *
 * @author Kartik_Kumar02
 */
public class FirstHelpActivity extends Activity {
	
	/** The next button. */
	private ImageView nextButton;
	
	/** The context. */
	private Context context;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		context = this;
		setContentView(R.layout.activity_help_first);
		nextButton =  (ImageView) findViewById(R.id.nextActivity);
		nextButton.setOnClickListener(new NextActivityButtonhandler());
	}
	
	/**
	 * Description : This class handles the n click event of the button present.
	 *
	 * @author Kartik_Kumar02
	 */
	public class NextActivityButtonhandler implements View.OnClickListener {
		
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(View view) {
			context.startActivity(new Intent(context,SecondHelpActivity.class));
			finish();
		}
	}
}
