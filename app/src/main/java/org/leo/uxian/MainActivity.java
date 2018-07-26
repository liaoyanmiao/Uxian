package org.leo.uxian;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import org.leo.uxian.adpter.ViewPagerAdapter;
import org.leo.uxian.fragment.MainFragment;
import org.leo.uxian.presenter.impl.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements View.OnClickListener,ViewPager.OnPageChangeListener{

    @BindView(R.id.id_viewPage)
    ViewPager idViewPage;
    @BindView(R.id.id_tvMain)
    TextView idTvMain;
    @BindView(R.id.id_tvDiscover)
    TextView idTvDiscover;
    @BindView(R.id.id_tvCart)
    TextView idTvCart;
    @BindView(R.id.id_tvMine)
    TextView idTvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idTvMain.setOnClickListener(this);
        idTvDiscover.setOnClickListener(this);
        idTvCart.setOnClickListener(this);
        idTvMine.setOnClickListener(this);

        List<Fragment> fragmentList = new ArrayList<>();
        MainFragment mainFragment = new MainFragment();
        fragmentList.add(mainFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentList(fragmentList);
        idViewPage.setAdapter(adapter);
        idViewPage.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void onClick(View v) {
        tabSwitch(v.getId());
    }

    private void setDrawable(TextView textView, int id) {
        Drawable drawable = context.getResources().getDrawable(id);
        int h = drawable.getMinimumHeight();
        int w = drawable.getMinimumWidth();
        drawable.setBounds(0, 0, w, h);//必须设置图片大小，否则不显示
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 切换页面
     * @param viewId 页面ID
     */
    private void tabSwitch(int viewId) {
        switch (viewId) {
            case R.id.id_tvMain:
                idViewPage.setCurrentItem(0,true);
                idTvMain.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                setDrawable(idTvMain,R.drawable.tab_home_sel);

                idTvDiscover.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvDiscover,R.drawable.tab_discover_nor);

                idTvCart.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvCart,R.drawable.tab_cart_nor);

                idTvMine.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMine,R.drawable.tab_mine_nor);
                break;
            case R.id.id_tvDiscover:
                idTvMain.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMain,R.drawable.tab_home_nor);

                idTvDiscover.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                setDrawable(idTvDiscover,R.drawable.tab_discover_sel);

                idTvCart.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvCart,R.drawable.tab_cart_nor);

                idTvMine.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMine,R.drawable.tab_mine_nor);
                break;
            case R.id.id_tvCart:
                idTvMain.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMain,R.drawable.tab_home_nor);

                idTvDiscover.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvDiscover,R.drawable.tab_discover_nor);

                idTvCart.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                setDrawable(idTvCart,R.drawable.tab_cart_sel);

                idTvMine.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMine,R.drawable.tab_mine_nor);
                break;
            case R.id.id_tvMine:
                idTvMain.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvMain,R.drawable.tab_home_nor);

                idTvDiscover.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvDiscover,R.drawable.tab_discover_nor);

                idTvCart.setTextColor(ContextCompat.getColor(context,R.color.darkgray));
                setDrawable(idTvCart,R.drawable.tab_cart_nor);

                idTvMine.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
                setDrawable(idTvMine,R.drawable.tab_mine_sel);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            tabSwitch(R.id.id_tvMain);
        } else if (position == 1) {
            tabSwitch(R.id.id_tvDiscover);
        } else if (position == 2) {
            tabSwitch(R.id.id_tvCart);
        } else if (position == 3) {
            tabSwitch(R.id.id_tvMine);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
