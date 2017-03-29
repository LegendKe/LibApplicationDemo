package hz.framework.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import hz.framework.android.R;
import hz.framework.android.View.CirclePageIndicator;
import hz.framework.android.utils.SharedPreferences;

/**
 * Created by houzheng on 16/5/9.
 */
public abstract class BaseSplashActivity extends FragmentActivity {

    private Button btnHome;
    private CirclePageIndicator indicator;
    private ViewPager pager;
    private GalleryPagerAdapter adapter;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean firstTimeUse = SharedPreferences.getInstance().getBoolean(getApplicationContext(),"first-time-use", true);
        if(firstTimeUse&&enableMultiPage() && getImages()!=null &&getImages().length>0) {
            initGuideGallery();
        } else {
            initLaunchLogo();
        }
    }

    private void initLaunchLogo() {
        ImageView guideImage = (ImageView) findViewById(R.id.guideImage);
        guideImage.setImageResource(getSpLaunchImage());
        guideImage.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), getNextActivity());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                finish();
            }
        }, 1000);
    }

    protected abstract int getSpLaunchImage();

    private void initGuideGallery() {
        final Animation fadeIn= AnimationUtils.loadAnimation(this, R.anim.fadein);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.getInstance().putBoolean(getApplicationContext(),"first-time-use", false);

                Intent intent = new Intent(getApplicationContext(), getNextActivity());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                finish();
            }
        });

        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setVisibility(View.VISIBLE);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setVisibility(View.VISIBLE);

        adapter = new GalleryPagerAdapter();
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == getImages().length - 1) {
                    btnHome.setVisibility(View.VISIBLE);
                    btnHome.startAnimation(fadeIn);
                } else {
                    btnHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    protected abstract Class<?> getNextActivity();

    protected abstract boolean enableMultiPage();

    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return getImages().length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(BaseSplashActivity.this);
            item.setScaleType(ImageView.ScaleType.CENTER_CROP);
            item.setImageResource(getImages()[position]);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
    }

    protected abstract int[] getImages();

}
