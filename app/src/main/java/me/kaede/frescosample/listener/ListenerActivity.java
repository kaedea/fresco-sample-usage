package me.kaede.frescosample.listener;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class ListenerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);

		ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
			@Override
			public void onFinalImageSet(
					String id,
					@Nullable ImageInfo imageInfo,
					@Nullable Animatable anim) {
				if (imageInfo == null) {
					return;
				}
				QualityInfo qualityInfo = imageInfo.getQualityInfo();
				FLog.d("Final image received! " +
								"Size %d x %d",
						"Quality level %d, good enough: %s, full quality: %s",
						imageInfo.getWidth(),
						imageInfo.getHeight(),
						qualityInfo.getQuality(),
						qualityInfo.isOfGoodEnoughQuality(),
						qualityInfo.isOfFullQuality());
			}

			@Override
			public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
			}

			@Override
			public void onFailure(String id, Throwable throwable) {
				FLog.e(getClass(), throwable, "Error loading %s", id);
				Toast.makeText(ListenerActivity.this,"Error loading, id = "+id,Toast.LENGTH_LONG).show();
			}
		};

		SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_main);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(Uri.parse(ImageApi.other.getUrlByName("does-not-have-this-url")))
				.setControllerListener(controllerListener)
				.setTapToRetryEnabled(true)
				.build();
		draweeView.setController(controller);

		GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
		GenericDraweeHierarchy hierarchy = builder
				.setFailureImage(getResources().getDrawable(R.drawable.image_failure), ScalingUtils.ScaleType.CENTER)
				.setRetryImage(getResources().getDrawable(R.drawable.image_retry), ScalingUtils.ScaleType.CENTER)
				.build();
		draweeView.setHierarchy(hierarchy);
	}
}
