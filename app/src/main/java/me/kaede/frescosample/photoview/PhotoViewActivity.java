package me.kaede.frescosample.photoview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;
import me.kaede.util.fresco.photoview.HackyViewPager;
import me.kaede.util.fresco.photoview.PhotoView;

import java.util.List;

public class PhotoViewActivity extends AppCompatActivity {

	private static final String ISLOCKED_ARG = "isLocked";
	private ViewPager mViewPager;
	private int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_view);
		mViewPager = (HackyViewPager) findViewById(R.id.viewpager);

		mViewPager.setAdapter(new SamplePagerAdapter());
		if (getIntent() != null) {
			position = getIntent().getIntExtra("position", 0);
			mViewPager.setCurrentItem(position);
		}

		if (savedInstanceState != null) {
			boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false);
			((HackyViewPager) mViewPager).setLocked(isLocked);
		}

	}

	static class SamplePagerAdapter extends PagerAdapter {

		List<String> datas = ImageApi.jk.getUrls();

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			photoView.setImageUri(datas.get(position));

			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	private boolean isViewPagerActive() {
		return (mViewPager != null && mViewPager instanceof HackyViewPager);
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		if (isViewPagerActive()) {
			outState.putBoolean(ISLOCKED_ARG, ((HackyViewPager) mViewPager).isLocked());
		}
		super.onSaveInstanceState(outState);
	}
}
