package me.kaede.frescosample.postprocessor;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class PostprocessorActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);

		SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_main);

		Postprocessor redMeshPostprocessor = new BasePostprocessor() {
			@Override
			public String getName() {
				return "redMeshPostprocessor";
			}

			@Override
			public void process(Bitmap bitmap) {
				for (int x = 0; x < bitmap.getWidth(); x+=2) {
					for (int y = 0; y < bitmap.getHeight(); y+=2) {
						bitmap.setPixel(x, y, Color.RED);
					}
				}
			}
		};

		Uri uri = Uri.parse(ImageApi.other.getUrlByName("lowres-small", ".jpg"));

		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
				.setAutoRotateEnabled(true)
				.setLocalThumbnailPreviewsEnabled(true)
				.setProgressiveRenderingEnabled(false)
				.setPostprocessor(redMeshPostprocessor)
				.build();

		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setImageRequest(request)
				.build();
		draweeView.setController(controller);

		GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
		GenericDraweeHierarchy hierarchy = builder
				.setProgressBarImage(new ProgressBarDrawable())
				.build();
		draweeView.setHierarchy(hierarchy);
	}
}
