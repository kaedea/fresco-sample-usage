package me.kaede.frescosample.snippet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.kaede.frescosample.R;
import us.feras.mdv.MarkdownView;

public class SnippetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snippet);

        MarkdownView markdownView = (MarkdownView) this.findViewById(R.id.markdownview);
        markdownView.loadMarkdownFile("file:///android_asset/loadanimage.md");
    }
}
