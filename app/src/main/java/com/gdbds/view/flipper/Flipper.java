package com.gdbds.view.flipper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class Flipper extends ViewFlipper {

	private OnViewChange onViewChange;

	public Flipper(Context context) {
		super(context);
	}

	public Flipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void showNext() {
		super.showNext();
		if (onViewChange != null) {
			onViewChange.onChange(getDisplayedChild());
		}
	}

	@Override
	public void showPrevious() {
		super.showPrevious();
		if (onViewChange != null) {
			onViewChange.onChange(getDisplayedChild());
		}
	}

	public interface OnViewChange {
		void onChange(int index);
	}

	public OnViewChange getOnViewChange() {
		return onViewChange;
	}

	public void setOnViewChange(OnViewChange onViewChange) {
		this.onViewChange = onViewChange;
	}

}
