package npi.multitouchclose;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    	final int action = ev.getAction();
    	
        switch (action & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_DOWN: {
	        	break;
	        }
	        case MotionEvent.ACTION_POINTER_DOWN: {
	        	break;
	        }
	        case MotionEvent.ACTION_UP: {
	        	break;
	        }
	        case MotionEvent.ACTION_POINTER_UP: {
	        	break;
	        }
	        case MotionEvent.ACTION_MOVE: {
	            break;
	        }
	        case MotionEvent.ACTION_CANCEL: {
	        	break;
	        }
        }
        return true;
    }	
}
