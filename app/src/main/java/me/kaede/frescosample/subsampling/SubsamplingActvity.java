package me.kaede.frescosample.subsampling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import me.kaede.frescosample.R;
import me.kaede.util.fresco.subscaleview.SubsamplingScaleImageView;

public class SubsamplingActvity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subsampling_actvity);

		SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) this.findViewById(R.id.scaleimageview);
		subsamplingScaleImageView.setImageUri("http://ww3.sinaimg.cn/bmiddle/a20a9b80jw1etmbognz7rj20cs3jiqa6.jpg");
	}
}
