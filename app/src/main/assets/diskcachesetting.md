## Load An Image

### Java code
    DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
    		.setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"Moe Studio"))
    		.setBaseDirectoryName("fresco_sample")
    		.setMaxCacheSize(200*1024*1024)//200MB
    		.build();
    ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
    		.setMainDiskCacheConfig(diskCacheConfig)
    		.build();
    Fresco.initialize(this, imagePipelineConfig);


