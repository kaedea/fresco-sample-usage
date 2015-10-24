package me.kaede.util.fresco.gifimageview;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;

import me.kaede.frescosample.R;
import me.kaede.util.fresco.CustomProgressbarDrawable;
import me.kaede.util.fresco.ImageDownloadListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 06peng on 15/6/28.
 */
public class CustomGifImageView extends LinearLayout implements ImageDownloadListener {

    DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    ImageDownloadListener imageDownloadListener;
    private CloseableReference<PooledByteBuffer> bytes;
    private GifDrawable mGifDrawable;
    private GifImageView mGifView;

    public CustomGifImageView(Context context) {
        super(context);
        init(context);
    }

    public CustomGifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomGifImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_gifimageview, null);
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(layout, layoutParams);
        mGifView = (GifImageView) findViewById(R.id.gifview);

        if (mDraweeHolder == null) {
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                    .setProgressBarImage(new CustomProgressbarDrawable(this)).build();
            mDraweeHolder = DraweeHolder.create(hierarchy, getContext());
        }
    }

    public void setImageUrl(String url) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<PooledByteBuffer>> dataSource = imagePipeline.fetchEncodedImage(imageRequest, this);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mDraweeHolder.getController())
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String s, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                        try {
                            if (bytes == null) {
                                bytes = dataSource.getResult();
                            }
                            if (bytes != null) {
                                PooledByteBuffer pooledByteBuffer = bytes.get();
                                PooledByteBufferInputStream sourceIs = new PooledByteBufferInputStream(pooledByteBuffer);
                                BufferedInputStream bis = new BufferedInputStream(sourceIs);
                                mGifDrawable = new GifDrawable(bis);
                                mGifView.setImageDrawable(mGifDrawable);
                                mGifView.setVisibility(View.VISIBLE);

                                //mProgressTextView.setVisibility(View.INVISIBLE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            dataSource.close();
                            CloseableReference.closeSafely(bytes);
                        }
                    }
                })
                .setTapToRetryEnabled(true)
                .build();
        mDraweeHolder.setController(controller);
    }

    @Override
    protected void onDetachedFromWindow() {
        mDraweeHolder.onDetach();
        close();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        init(getContext());
        mDraweeHolder.onAttach();
        super.onAttachedToWindow();
    }

    @Override
    protected boolean verifyDrawable(Drawable dr) {
        if (dr == mDraweeHolder.getHierarchy().getTopLevelDrawable()) {
            return true;
        }
        return super.verifyDrawable(dr);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mDraweeHolder.onDetach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mDraweeHolder.onAttach();
    }

    public void setImageDownloadListener(ImageDownloadListener imageDownloadListener) {
        this.imageDownloadListener = imageDownloadListener;
    }

    @Override
    public void onUpdate(int progress) {
        if (imageDownloadListener !=null){
            imageDownloadListener.onUpdate(progress);
        }
    }

    private void close() {
        try {
            if (mGifDrawable != null && !mGifDrawable.isRecycled()) {
                mGifDrawable.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
