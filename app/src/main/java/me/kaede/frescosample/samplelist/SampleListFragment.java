package me.kaede.frescosample.samplelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.kaede.frescosample.R;
import me.kaede.frescosample.basicusage.BasicUsageActivity;
import me.kaede.frescosample.gif.GifActivity;
import me.kaede.frescosample.gifimageview.GifImageViewActivity;
import me.kaede.frescosample.listener.ListenerActivity;
import me.kaede.frescosample.listview.ListViewActivity;
import me.kaede.frescosample.lowres.LowResActivity;
import me.kaede.frescosample.photoview.PhotoViewActivity;
import me.kaede.frescosample.postprocessor.PostprocessorActivity;
import me.kaede.frescosample.progressivejpg.ProgressiveJPGActivity;
import me.kaede.frescosample.recyclerview.RecyclerViewActivity;
import me.kaede.frescosample.resize.ResizeActivity;
import me.kaede.frescosample.subsampling.SubsamplingActvity;

/**
 * Created by kaede on 2015/10/23.
 */
public class SampleListFragment extends Fragment{
	private static final String BUNDLE_INDEX = "BUNDLE_INDEX";

	private int index;
	ActivityHolder activityHolder;


	public static SampleListFragment newInstance(int index) {
		SampleListFragment fragment = new SampleListFragment();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_INDEX, index);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			index = getArguments().getInt(BUNDLE_INDEX);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);


		//find view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);


		activityHolder = new ActivityHolder();
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);
		MyAdapter adapter = new MyAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

		switch (index) {
			case 0:
			default:
				activityHolder.addActivity("Basic Usage", BasicUsageActivity.class);
				activityHolder.addActivity("Gif/WebP Animation Image", GifActivity.class);
				activityHolder.addActivity("Low Resolution Image", LowResActivity.class);
				activityHolder.addActivity("Add Loading Listener", ListenerActivity.class);
				activityHolder.addActivity("Progressive JPG Streaming", ProgressiveJPGActivity.class);
				activityHolder.addActivity("Resize Image", ResizeActivity.class);
				activityHolder.addActivity("Use Postprocessor", PostprocessorActivity.class);
				activityHolder.addActivity("Usage with ListView", ListViewActivity.class);
				activityHolder.addActivity("Usage with RecyclerView", RecyclerViewActivity.class);

				break;
			case 1:
				activityHolder.addActivity("PhotoView", PhotoViewActivity.class);
				activityHolder.addActivity("SubsamplingScaleImageView", SubsamplingActvity.class);
				activityHolder.addActivity("GifImageView & GifDrawable", GifImageViewActivity.class);
				break;
			case 2:
				break;
		}

		adapter.notifyDataSetChanged();


		return view;
	}

	public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_samplelist_recycleview, parent, false);
			ViewHolder vh = new ViewHolder(v);
			return vh;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, final int position) {
			holder.textView.setText(activityHolder.getNameList().get(position));
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					activityHolder.startActivity(v.getContext(),position);
				}
			});
		}

		@Override
		public int getItemCount() {
			return activityHolder.getNameList().size();
		}
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		public TextView textView;
		public ViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.tv_item_samplelist_recyclerview);
		}
	}
}
