package me.kaede.frescosample.basicusage;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class BasicUsageActivity extends AppCompatActivity {

	Handler handler = new Handler(Looper.getMainLooper());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_usage);
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
		this.setSupportActionBar(toolbar);

		SimpleDraweeView draweeView;
		DraweeController controller;

		// basic
		draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_basic);
		draweeView.setImageURI(Uri.parse(ImageApi.jk.getUrl(0)));

		// ScaleType FocusCrop
		draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_basic_focus);
		draweeView.getHierarchy().setActualImageFocusPoint(new PointF(0, 0));
		draweeView.setImageURI(Uri.parse(ImageApi.jk.getUrl(0)));

		// ScaleType Tile
		Uri uri = new Uri.Builder()
				.scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
				.path(String.valueOf(R.drawable.pattern))
				.build();
		((SimpleDraweeView) findViewById(R.id.drawee_basic_tile)).setImageURI(uri);

		// Placeholder Image
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				((SimpleDraweeView) BasicUsageActivity.this.findViewById(R.id.drawee_basic_placeholder)).setImageURI(Uri.parse(ImageApi.jk.getUrl(1)));
			}
		}, 2000);

		// Fade Duration
		((SimpleDraweeView) findViewById(R.id.drawee_basic_fade)).setImageURI(Uri.parse(ImageApi.jk.getUrl(2)));

		// Corner Radius
		((SimpleDraweeView) findViewById(R.id.drawee_basic_radius)).setImageURI(Uri.parse(ImageApi.jk.getUrl(3)));

		// Circle
		((SimpleDraweeView) findViewById(R.id.drawee_basic_circle)).setImageURI(Uri.parse(ImageApi.jk.getUrl(4)));

		// Failure Image
		((SimpleDraweeView) findViewById(R.id.drawee_basic_failure)).setImageURI(Uri.parse(ImageApi.other.getUrlByName("does-not-have-this-url")));

		// Retry Image
		draweeView = ((SimpleDraweeView) findViewById(R.id.drawee_basic_retry));
		controller = Fresco.newDraweeControllerBuilder()
				.setUri(Uri.parse(ImageApi.other.getUrlByName("does-not-have-this-url")))
				.setTapToRetryEnabled(true)
				.build();
		draweeView.setController(controller);

		// ProgressBar Image
		draweeView = ((SimpleDraweeView) findViewById(R.id.drawee_basic_progressbar));
		GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
		GenericDraweeHierarchy hierarchy = builder
				.setProgressBarImage(new ProgressBarDrawable())
				.build();
		draweeView.setHierarchy(hierarchy);
		draweeView.setImageURI(Uri.parse(ImageApi.jk.getUrl(5)));

		// Background Image
		((SimpleDraweeView) findViewById(R.id.drawee_basic_backround)).setImageURI(Uri.parse(ImageApi.jk.getUrl(6)));

		// Overlay Image
		((SimpleDraweeView) findViewById(R.id.drawee_basic_overlay)).setImageURI(Uri.parse(ImageApi.jk.getUrl(7)));

		// PressedStateOverlay Image
		draweeView = ((SimpleDraweeView) findViewById(R.id.drawee_basic_press));
		draweeView.setImageURI(Uri.parse(ImageApi.jk.getUrl(8)));
		draweeView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

}
