package me.kaede.util;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaede on 2015/10/21.
 * sample url : http://gh.kaedea.com/fresco-sample-usage/assets/image/girly/jk-00.jpg
 */
public class ImageHolder {
	String domain;
	String baseDir;
	String categroy;
	String prefix;
	String extension = ".jpg";
	int maxIndex = Integer.MAX_VALUE;
	String indexFormat = "00";

	DecimalFormat formatter;

	public ImageHolder(String domain) {
		this.domain = domain;
		init();

	}

	public ImageHolder(String domain, String baseDir, String categroy, String prefix, String extension, int maxIndex, String indexFormat) {
		this.domain = domain;
		this.baseDir = baseDir;
		this.categroy = categroy;
		this.prefix = prefix;
		this.extension = extension;
		this.maxIndex = maxIndex;
		this.indexFormat = indexFormat;
		init();
	}

	private void init(){
		formatter = new DecimalFormat(indexFormat);
	}

	public String getUrl(int index){
		if (index>maxIndex)return null;
		if (domain==null)return null;
		StringBuilder sb = new StringBuilder(domain);
		if (baseDir!=null) sb.append("/").append(baseDir);
		if (categroy!=null) sb.append("/").append(categroy);
		if (prefix!=null) sb.append("/").append(prefix);else sb.append("/");
		String url = sb.append(formatter.format(index)).append(extension).toString();
		Log.i("ImageHolder","[getUrl] url = "+url);
		return url;
	}

	public List<String> getUrls(){
		List<String> urls = new ArrayList<>();
		if (maxIndex == Integer.MAX_VALUE) return urls;
		for (int i = 0; i<= maxIndex; i++){
			urls.add(getUrl(i));
		}
		return urls;
	}

	public String getUrlByName(String name){
		if (name==null||name.length()<=0)return null;
		if (domain==null)return null;
		StringBuilder sb = new StringBuilder(domain);
		if (baseDir!=null) sb.append("/").append(baseDir);
		if (categroy!=null) sb.append("/").append(categroy);
		if (prefix!=null) sb.append("/").append(prefix);else sb.append("/");
		String url = sb.append(name).append(extension).toString();
		Log.i("ImageHolder", "[getUrlByName] url = " + url);
		return url;
	}

	public String getUrlByName(String name,String extension){
		if (name==null||name.length()<=0)return null;
		if (extension==null||extension.length()<=0)return null;
		if (domain==null)return null;
		StringBuilder sb = new StringBuilder(domain);
		if (baseDir!=null) sb.append("/").append(baseDir);
		if (categroy!=null) sb.append("/").append(categroy);
		if (prefix!=null) sb.append("/").append(prefix);else sb.append("/");
		String url = sb.append(name).append(extension).toString();
		Log.i("ImageHolder","[getUrlByName] url = "+url);
		return url;
	}
}
