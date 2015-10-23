package me.kaede.util.fresco;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import com.facebook.drawee.drawable.ProgressBarDrawable;

/**
 * Created by kaede on 2015/10/23.
 */
public class CustomProgressbarDrawable extends ProgressBarDrawable {

	private ImageDownloadListener mListener;

	public CustomProgressbarDrawable(ImageDownloadListener listener) {
		mListener = listener;
	}

	@Override
	protected boolean onLevelChange(int level) {
		int progress = (int) ((level / 10000.0) * 100);
		if (mListener != null) {
			mListener.onUpdate(progress);
		}
		return super.onLevelChange(level);
	}
}
