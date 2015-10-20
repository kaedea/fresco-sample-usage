package me.kaede.frescosample;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by kaede on 2015/10/20.
 */
public class FrescoAppliction extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
	}
}
