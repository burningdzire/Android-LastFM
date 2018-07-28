package io.github.burningdzire.lastfm.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import io.github.burningdzire.lastfm.R;
import io.github.burningdzire.lastfm.adapter.CategoryAdapter;

public class DetailActivity extends AppCompatActivity {

    private static String tag;
    private final String LOG_TAG = DetailActivity.class.getSimpleName();
    private ViewPager mViewPager;

    public static String getTag() {
        return tag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT) == true) {
            tag = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        } else {
            tag = "disco";
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
       // mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
