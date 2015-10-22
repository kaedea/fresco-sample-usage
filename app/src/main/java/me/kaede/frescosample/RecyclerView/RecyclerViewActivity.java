package me.kaede.frescosample.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import me.kaede.frescosample.R;

public class RecyclerViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyclerview);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		TabLayout tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
		ViewPager viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOffscreenPageLimit(3);
		tabLayout.setupWithViewPager(viewPager);
	}

	public class MyAdapter extends FragmentStatePagerAdapter {
		public String[] pagers = new String[]{"ヒトツ","フタツ","ミツ"};
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return RecyclerViewFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return pagers.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return pagers[position];
		}
	}
}
