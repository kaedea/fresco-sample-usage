package me.kaede.frescosample.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import me.kaede.frescosample.ImageApi;
import me.kaede.frescosample.R;

public class ListViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);

		ListView listView = (ListView) this.findViewById(R.id.list_main);
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);

		adapter.setDatas(ImageApi.jk.getUrls());
	}


}
