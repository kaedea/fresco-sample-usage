package me.kaede.frescosample;

import me.kaede.util.ImageHolder;

/**
 * Created by kaede on 2015/10/21.
 * sample : http://dn-assets-gitcafe-com.qbox.me/Kaedea/Kaede-Assets/raw/gitcafe-pages/image/girly/jk-01.jpg
 */
public class ImageApi {
	public static final String DOMAIN = "http://dn-assets-gitcafe-com.qbox.me";
	private static final String BASE_DIR = "Kaedea/Kaede-Assets/raw/gitcafe-pages/image";
	public static ImageHolder girly = new ImageHolder(DOMAIN, BASE_DIR,"girly","jk-",".jpg",17,"00");
	public static ImageHolder jk = new ImageHolder(DOMAIN, BASE_DIR,"jk","jk-",".jpg",30,"00");
	public static ImageHolder legs = new ImageHolder(DOMAIN, BASE_DIR,"legs","legs-",".jpg",29,"00");
	public static ImageHolder other = new ImageHolder(DOMAIN, BASE_DIR,"other","",".jpg",Integer.MAX_VALUE,"00");
}
