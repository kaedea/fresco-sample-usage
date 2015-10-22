package me.kaede.frescosample.listview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import me.kaede.frescosample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaede on 2015/10/22.
 */
public class MyAdapter extends BaseAdapter {
	List<String> datas = new ArrayList<>();

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		if (datas!=null&&datas.size()>0){
			this.datas.clear();
			this.datas.addAll(datas);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public String getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
			convertView.setTag(new ViewHolder((SimpleDraweeView) convertView.findViewById(R.id.draweeview)));
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.draweeView.setImageURI(Uri.parse(getItem(position)));
		return convertView;
	}

	public class ViewHolder{
		SimpleDraweeView draweeView;

		public ViewHolder(SimpleDraweeView draweeView) {
			this.draweeView = draweeView;
		}
	}
}