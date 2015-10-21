package me.kaede.frescosample.basicusage;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.facebook.drawee.view.DraweeView;
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

		// SimpleDraweeView
		SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.drawee_basic);
		draweeView.setImageURI(Uri.parse(ImageApi.jk.getUrl(0)));

		// SimpleDraweeView
		SimpleDraweeView draweeViewFocus = (SimpleDraweeView) this.findViewById(R.id.drawee_basic_focus);
		draweeViewFocus.getHierarchy().setActualImageFocusPoint(new PointF(0,0));
		draweeViewFocus.setImageURI(Uri.parse(ImageApi.jk.getUrl(0)));

		// Placeholder Image
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				((SimpleDraweeView) BasicUsageActivity.this.findViewById(R.id.drawee_basic_placeholder)).setImageURI(Uri.parse(ImageApi.jk.getUrl(1)));
			}
		}, 2000);

		// Fade Duration
		((SimpleDraweeView)findViewById(R.id.drawee_basic_fade)).setImageURI(Uri.parse(ImageApi.jk.getUrl(2)));

		// Corner Radius
		((SimpleDraweeView)findViewById(R.id.drawee_basic_radius)).setImageURI(Uri.parse(ImageApi.jk.getUrl(3)));

		// Circle
		((SimpleDraweeView)findViewById(R.id.drawee_basic_circle)).setImageURI(Uri.parse(ImageApi.jk.getUrl(4)));
	}
}
