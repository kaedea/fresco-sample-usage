package me.kaede.frescosample.lowres;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class LowResActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gif);

		SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_gif);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(Uri.parse(ImageApi.other.getUrlByName("lowres-big", ".jpg")))
				.setLowResImageRequest(ImageRequest.fromUri(Uri.parse(ImageApi.other.getUrlByName("lowres-small", ".jpg"))))
				.setTapToRetryEnabled(true)
				.build();
		draweeView.setController(controller);
	}
}
