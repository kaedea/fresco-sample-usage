package me.kaede.frescosample.gifimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.kaede.frescosample.ImageApi;
import me.kaede.util.fresco.gifdrawableview.CustomGifView;

public class GifImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomGifView gifView = new CustomGifView(this);
        setContentView(gifView);
        gifView.setImageUrl(ImageApi.other.getUrlByName("animation2", ".gif"));
    }
}
