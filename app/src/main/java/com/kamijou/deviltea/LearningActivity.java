package com.kamijou.deviltea;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LearningActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private KanaFragment hiraganaFragment = new KanaFragment();
    private KanaFragment katakanaFragment = new KanaFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        hiraganaFragment.setType(Constant.TYPE_HIRAGANA);
        hiraganaFragment.setExam(false);
        katakanaFragment.setType(Constant.TYPE_KATAKANA);
        katakanaFragment.setExam(false);

        viewPager = (ViewPager) findViewById(R.id.learning_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.learning_tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0) {
                    return hiraganaFragment;
                } else if(position == 1) {
                    return katakanaFragment;
                } else {
                    return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, true);
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
