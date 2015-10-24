## Load An Image

### XML code
    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="match_parent"
        android:layout_height="150dp"
    />

### Java code
    SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
    draweeView.setImageURI(Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png"));


