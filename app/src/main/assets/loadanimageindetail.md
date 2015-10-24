## Load An Image In Detail

### XML code
    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view"
        android:layout_width="match_parent"
        android:layout_height="150dp"
    />

### Java code
    SimpleDraweeView draweeView = (SimpleDraweeView) this.findViewById(R.id.my_image_view);

    Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    Uri lowResUri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo-low-quality.png");

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        		.setAutoRotateEnabled(true)
        		.setFirstAvailableImageRequests(requests)
        		.setLocalThumbnailPreviewsEnabled(true)
        		.setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
        		.setResizeOptions(newResizeOptions(width,height))
        		.setProgressiveRenderingEnabled(false)
        		.setPostprocessor(postprocessor)
        		.build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        		.setImageRequest(request)
        		.setTapToRetryEnabled(true)
                .setAutoPlayAnimations(true)
                .setLowResImageRequest(ImageRequest.fromUri(lowResUri))
                .setOldController(mSimpleDraweeView.getController())
                .setControllerListener(controllerListener)
        		.build();
    draweeView.setController(controller);

    GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
    GenericDraweeHierarchy hierarchy = builder
        		.setProgressBarImage(new ProgressBarDrawable())
        		.build();
    draweeView.setHierarchy(hierarchy);





