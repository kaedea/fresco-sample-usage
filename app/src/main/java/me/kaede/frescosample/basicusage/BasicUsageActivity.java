package me.kaede.frescosample.basicusage;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
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
		draweeView.setImageURI(Uri.parse("http://duang.de/content/images/2015/10/12326342.jpg"));

		// Placeholder Image
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				((SimpleDraweeView)BasicUsageActivity.this.findViewById(R.id.drawee_basic_placeholder)).setImageURI(Uri.parse("http://duang.de/content/images/2015/10/22886092.jpg"));
			}
		},2000);
	}
}
