package me.kaede.frescosample.recyclerview;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import me.kaede.frescosample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaede on 2015/10/22.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	int index;

	List<String> datas = new ArrayList<>();
	Map<String,Integer> heightMap = new HashMap<>();
	static Map<String,Integer> widthMap = new HashMap<>();

	public MyAdapter(int index) {
		this.index = index;
	}

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
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		final String url = getDatas().get(position);
		if (heightMap.containsKey(url)){
			int height = heightMap.get(url);
			FLog.i("kaede", url+ "'s height = " + height);
			if (height>0){
				updateItemtHeight(height,holder.itemView);
				holder.draweeView.setImageURI(Uri.parse(url));
				return;
			}

		}
		ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
			@Override
			public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
				if (imageInfo == null) {
					return;
				}
				QualityInfo qualityInfo = imageInfo.getQualityInfo();
				if (qualityInfo.isOfGoodEnoughQuality()){
					int heightTarget = (int) getTargetHeight(imageInfo.getWidth(),imageInfo.getHeight(),holder.itemView,url);
					FLog.i("kaede", "heightTarget = " + heightTarget);
					if (heightTarget<=0)return;
					heightMap.put(url,heightTarget);
					updateItemtHeight(heightTarget, holder.itemView);
				}
			}

			@Override
			public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
			}

			@Override
			public void onFailure(String id, Throwable throwable) {
			}
		};
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(Uri.parse(url))
				.setControllerListener(controllerListener)
				.setTapToRetryEnabled(true)
				.build();
		holder.draweeView.setController(controller);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	private float getTargetHeight(float width,float height,View view, String url){
		View child = view.findViewById(R.id.draweeview);
		float widthTarget;
		if (widthMap.containsKey(url)) widthTarget = widthMap.get(url);
		else {
			widthTarget = child.getMeasuredWidth();
			if (widthTarget>0){
				widthMap.put(url, (int) widthTarget);
			}
		}

		FLog.i("kaede","child.getMeasuredWidth() = " + widthTarget);
		/*int getWidth = child.getWidth();
		int getMeasuredWidth = child.getMeasuredWidth();
		int getLayoutParamsWidth = child.getLayoutParams().width;
		if (getWidth==0||getMeasuredWidth==0||getLayoutParamsWidth==0){
			FLog.i("kaede","child.getWidth() = " + getWidth);
			FLog.i("kaede","child.getMeasuredWidth() = " + getMeasuredWidth);
			FLog.i("kaede","child.getLayoutParams().width = " + getLayoutParamsWidth);
		}*/
		return height * (widthTarget /width);
	}

	private void updateItemtHeight(int height, View view) {
		CardView cardView = (CardView) view.findViewById(R.id.cardview);
		View child = view.findViewById(R.id.draweeview);
		CardView.LayoutParams layoutParams = (CardView.LayoutParams) child.getLayoutParams();
		layoutParams.height = height;
		cardView.updateViewLayout(child,layoutParams);
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		SimpleDraweeView draweeView;

		public ViewHolder(View itemView) {
			super(itemView);
			draweeView = (SimpleDraweeView) itemView.findViewById(R.id.draweeview);
		}
	}
}
