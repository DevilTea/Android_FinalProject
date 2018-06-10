package com.kamijou.deviltea;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ExamActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private KanaFragment hiraganaFragment = new KanaFragment();
    private KanaFragment katakanaFragment = new KanaFragment();
    private KanaFragment allkanaFragment = new KanaFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        hiraganaFragment.setType(Constant.TYPE_HIRAGANA);
        hiraganaFragment.setExam(true);
        katakanaFragment.setType(Constant.TYPE_KATAKANA);
        katakanaFragment.setExam(true);
        allkanaFragment.setType(Constant.TYPE_ALLKANA);
        allkanaFragment.setExam(true);

        viewPager = (ViewPager) findViewById(R.id.examination_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.examination_tab_layout);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0) {
                    return hiraganaFragment;
                } else if(position == 1) {
                    return katakanaFragment;
                } else if(position == 2) {
                    return allkanaFragment;
                } else {
                    return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, true);
            }

            @Override
            public void onPageSelected(int position) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exam, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        KanaFragment kanaFragment = null;
        if(viewPager.getCurrentItem() == 0) {
            kanaFragment = hiraganaFragment;
        } else if(viewPager.getCurrentItem() == 1) {
            kanaFragment = katakanaFragment;
        } else if(viewPager.getCurrentItem() == 2) {
            kanaFragment = allkanaFragment;
        }

        if(item.getItemId() == R.id.start_exam) {
            ArrayList examPositions = kanaFragment.getAdapter().getExamPositions();
            Log.d("asd", "bb: " + examPositions.toString());
            if (examPositions.isEmpty()) {
                Toast.makeText(kanaFragment.getActivity(), "請選擇要測驗的列", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(ExamActivity.this, ExamActivity2.class);
                ArrayList<KanaData> examKanaDatas = KanaData.getKanaDatasByPositions(kanaFragment.getAdapter().getExamPositions());
                examKanaDatas.removeIf(new Predicate<KanaData>() {
                    @Override
                    public boolean test(KanaData kanaData) {
                        return kanaData.getPinyin().isEmpty();
                    }
                });
                intent.putExtra("kanaType", kanaFragment.getType());
                intent.putExtra("examKanaDatas", examKanaDatas);
                startActivity(intent);
            }
        } else if(item.getItemId() == R.id.check_all) {
            kanaFragment.getAdapter().setAllCheckboxes(true);
        } else if(item.getItemId() == R.id.clear_all) {
            kanaFragment.getAdapter().setAllCheckboxes(false);
        } else if(item.getItemId() == R.id.statistics) {
            Toast.makeText(this, "統計資料", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
