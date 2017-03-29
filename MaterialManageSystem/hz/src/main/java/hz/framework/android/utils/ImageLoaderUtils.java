package hz.framework.android.utils;

import android.content.Context;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class ImageLoaderUtils {
	private ImageLoaderUtils() {

	}

	static DisplayImageOptions roundOption;
	static DisplayImageOptions simpleOption;
	private ImageLoader loader;
	private static ImageLoaderUtils instance;
	private Context context;

	public static ImageLoaderUtils getInstance(Context context) {
		if (instance == null) {
			instance = new ImageLoaderUtils();
			instance.loader = ImageLoader.getInstance();
			instance.context = context;

			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					context).threadPriority(Thread.NORM_PRIORITY - 2)
					.denyCacheImageMultipleSizesInMemory()
					.threadPoolSize(50)
					.discCacheFileNameGenerator(new Md5FileNameGenerator())
					.tasksProcessingOrder(QueueProcessingType.LIFO).build();
			instance.loader.init(config);
			roundOption = new DisplayImageOptions.Builder()
			// .showStubImage(R.drawable.ic_stub) // 设置图片下载期间显示的图片
			// .showImageForEmptyUri(R.drawable.ic_empty) //
			// 设置图片Uri为空或是错误的时候显示的图片
			// .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
//					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
			.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
					.build(); // 创建配置过得DisplayImageOption对象
			instance.loader.init(ImageLoaderConfiguration
					.createDefault(context));
			simpleOption = new DisplayImageOptions.Builder()
					// .showStubImage(R.drawable.ic_stub) // 设置图片下载期间显示的图片
					// .showImageForEmptyUri(R.drawable.ic_empty) //
					// 设置图片Uri为空或是错误的时候显示的图片
					// .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
//					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.displayer(new SimpleBitmapDisplayer()) // 设置成圆角图片
					.build(); // 创建配置过得DisplayImageOption对象
			instance.loader.init(ImageLoaderConfiguration
					.createDefault(context));
		}
		return instance;
	}

	public void displayImageSimple(String uri, ImageView imageView) {
		instance.loader.displayImage(uri, imageView, simpleOption);
	}
	public void displayImageRound(String uri, ImageView imageView) {
		instance.loader.displayImage(uri, imageView, roundOption);
	}

	public void displayImageSimple(String uri, ImageView imageView,
			DisplayImageOptions options) {
		instance.loader.displayImage(uri, imageView, simpleOption);
	}
	public void displayImageRound(String uri, ImageView imageView,
							 DisplayImageOptions options) {
		instance.loader.displayImage(uri, imageView, roundOption);
	}
}
