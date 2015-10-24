package me.kaede.frescosample.snippet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.kaede.frescosample.R;
import me.kaede.widget.markdownview.MarkdownView;

public class SnippetActivity extends AppCompatActivity {

    public static final String EXTRA_FILE = "EXTRA_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snippet);
        String file = getIntent().getStringExtra(EXTRA_FILE);
        if (file!=null&&file.length()>0){
            MarkdownView markdownView = (MarkdownView) this.findViewById(R.id.markdownview);
            markdownView.loadMarkdownFile("file:///android_asset/"+file);
        }
    }
}
