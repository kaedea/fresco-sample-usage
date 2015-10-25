package me.kaede.frescosample.samplelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.kaede.frescosample.R;
import me.kaede.frescosample.snippet.SnippetActivity;

/**
 * Created by kaede on 2015/10/23.
 */
public class SnippetFragment extends Fragment{

	List<CodeSnippet> datas = new ArrayList<>();

	public static SnippetFragment newInstance() {
		SnippetFragment fragment = new SnippetFragment();
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);


		//find view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);
		MyAdapter adapter = new MyAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
		datas.add(new CodeSnippet("Load an image simply", "loadanimage.md"));
		datas.add(new CodeSnippet("Load an image in detail", "loadanimageindetail.md"));
		datas.add(new CodeSnippet("XML Attributes","xmlattributes.md"));
		datas.add(new CodeSnippet("DickCache Setting","diskcachesetting.md"));
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
			holder.textView.setText(datas.get(position).name);
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getContext(),SnippetActivity.class);
					intent.putExtra(SnippetActivity.EXTRA_FILE,datas.get(position).file);
					getContext().startActivity(intent);
				}
			});
		}

		@Override
		public int getItemCount() {
			return datas.size();
		}
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		public TextView textView;
		public ViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.tv_item_samplelist_recyclerview);
		}
	}

	public class CodeSnippet{
		String name;
		String file;

		public CodeSnippet(String name, String file) {
			this.name = name;
			this.file = file;
		}
	}
}
