package me.kaede.frescosample.gifimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.kaede.frescosample.ImageApi;
import me.kaede.util.fresco.ImageDownloadListener;
import me.kaede.util.fresco.gifimageview.CustomGifImageView;

public class GifImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomGifImageView gifView = new CustomGifImageView(this);
        gifView.setImageDownloadListener(new ImageDownloadListener() {
            @Override
            public void onUpdate(int progress) {
                Log.d("GifImageViewActivity","[onCreate][onUpdate] progress = " + progress );
            }
        });
        setContentView(gifView);
        gifView.setImageUrl(ImageApi.other.getUrlByName("animation2", ".gif"));
    }
}
