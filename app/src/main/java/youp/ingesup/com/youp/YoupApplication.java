package youp.ingesup.com.youp;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class YoupApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);
    }
}
