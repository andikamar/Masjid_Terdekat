package net.dika.masjid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dika.masjid.R;
import net.dika.masjid.adapter.TabPagerAdapter;

public class HomeTabFragment extends Fragment {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_tab, container, false);

        tabLayout = v.findViewById(R.id.tabs);
        viewPager = v.findViewById(R.id.viewpager);

        viewPager.setAdapter(new TabPagerAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        //toolbar.setTitle("Recipes");
                        break;
                    case 1:
                        //toolbar.setTitle("Categories");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }

    public HomeTabFragment() {
    }
}
