## SimpleDraweeView's Attributes

### XML code
    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="match_parent"
        android:layout_height="150dp"

        fresco:fadeDuration="300"
        fresco:actualImageScaleType="focusCrop"
        fresco:placeholderImage="@color/wait_color"
        fresco:placeholderImageScaleType="fitCenter"
        fresco:failureImage="@drawable/error"
        fresco:failureImageScaleType="centerInside"
        fresco:retryImage="@drawable/retrying"
        fresco:retryImageScaleType="centerCrop"
        fresco:progressBarImage="@drawable/progress_bar"
        fresco:progressBarImageScaleType="centerInside"
        fresco:progressBarAutoRotateInterval="1000"
        fresco:backgroundImage="@color/blue"
        fresco:overlayImage="@drawable/watermark"
        fresco:pressedStateOverlayImage="@color/red"
        fresco:roundAsCircle="false"
        fresco:roundedCornerRadius="1dp"
        fresco:roundTopLeft="true"
        fresco:roundTopRight="false"
        fresco:roundBottomLeft="false"
        fresco:roundBottomRight="true"
        fresco:roundWithOverlayColor="@color/corner_color"
        fresco:roundingBorderWidth="2dp"
        fresco:roundingBorderColor="@color/border_color"
    />



