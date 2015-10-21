package me.kaede.frescosample.gif;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class GifActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gif);

		SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_gif);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(Uri.parse(ImageApi.other.getUrlByName("animation",".gif")))
				.setTapToRetryEnabled(true)
				.setAutoPlayAnimations(true)
				.build();
		draweeView.setController(controller);
	}
}
