package npi.multitouchclose;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.graphics.PointF;

public class MainActivity extends ActionBarActivity {
	private Set<Integer> activePointers;
	private Map<Integer, PointF> pointersPositions;
	private enum GestureDetectionState {
		CAPTURING, DETECTED, IDLE
	}
	
	private GestureDetectionState gds;
	
	MainActivity() {
		super();
		this.activePointers = new TreeSet<Integer>();
		this.pointersPositions = new HashMap<Integer, PointF>();
		this.gds = GestureDetectionState.IDLE;
	}

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
	        	final int pointerId = ev.getPointerId(0);
	        	this.pointersPositions.put(pointerId, new PointF(ev.getX(), ev.getY()));
	        	this.activePointers.add(pointerId);
	        	break;
	        }
	        case MotionEvent.ACTION_POINTER_DOWN: {
	            final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
	                    >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                this.pointersPositions.put(pointerId, new PointF(ev.getX(), ev.getY()));
                
	        	if (this.pointersPositions.size() <= 3) {
	        		this.activePointers.add(pointerId);
	        		
	        		if (this.pointersPositions.size() == 3) {
	        			this.gds = GestureDetectionState.CAPTURING;
	        		}
	        	} else {
	        		this.gds = GestureDetectionState.IDLE;
	        	}
	        	break;
	        }
	        case MotionEvent.ACTION_UP: {
	            final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
	                    >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
	        	final int pointerId = ev.getPointerId(pointerIndex);
	        	this.pointersPositions.remove(pointerId);
	        	this.activePointers.remove(pointerId);
	        	break;
	        }
	        case MotionEvent.ACTION_POINTER_UP: {
	            final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
	                    >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
	        	final int pointerId = ev.getPointerId(pointerIndex);
	        	this.pointersPositions.remove(pointerId);
	        	this.activePointers.remove(pointerId);
	        	
	        	if (this.activePointers.contains(pointerId)) {
	        		if (this.pointersPositions.size() < 3)
	        		{
	        			this.gds = GestureDetectionState.IDLE;
	        		} else {
	        			for (Integer i : this.pointersPositions.keySet()) {
	        				if (!this.activePointers.contains(i)) {
	        					this.activePointers.add(i);
	        					break;
	        				}
	        			}
	        		}
	        	}
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
