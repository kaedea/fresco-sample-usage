package me.kaede.frescosample;

import android.app.Application;
import android.os.Environment;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * Created by kaede on 2015/10/20.
 */
public class FrescoApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		FLog.setMinimumLoggingLevel(FLog.VERBOSE);
		Fresco.initialize(this);
		/*DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
				.setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"Moe Studio"))
				.setBaseDirectoryName("fresco_sample")
				.setMaxCacheSize(20*1024)/data/data/me.kaede.frescosample/cache/image_cache
				.build();
		ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)//
				.setMainDiskCacheConfig(diskCacheConfig)
				.build();
		Fresco.initialize(this, imagePipelineConfig);*/
	}
}
