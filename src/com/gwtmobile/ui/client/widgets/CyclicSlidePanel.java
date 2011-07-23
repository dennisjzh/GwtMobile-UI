package com.gwtmobile.ui.client.widgets;


/**
 * An enhanced SlidePanel that has the ability to cycle backwards and forwards
 * so that there there is no end. Typical use-case would be a repetitive slide
 * show.
 */
public class CyclicSlidePanel extends SlidePanel {
	@Override
	public void next() {
		if (_current >= getSlideCount() - 1) 
			_current = 0;
		else
			_current++;
    	moveNext();
	}
	
	@Override
	public void previous() {
		cycleBackwards();
		movePrevious();
	}

	private void cycleBackwards() {
		if (_current <= 0) 
			_current = getSlideCount() - 1;
		else
			_current--;
	}
	
	public void setCurrentSlideIndex(int idx) {
		if (_current == idx)
			return;
		_current = idx;
		cycleBackwards(); // force a slide effect
		next();
	}
}
