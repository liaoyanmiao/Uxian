package org.leo.uxian.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import org.leo.uxian.R;
import org.leo.uxian.entity.PropagandaQueryPageEntity;
import org.leo.uxian.presenter.impl.MainFragmentPresenter;
import org.leo.uxian.utils.GlideImageLoader;
import org.leo.uxian.view.IMainFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter> implements IMainFragment{
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setListener() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mPresenter.invokePropagandaQueryPage();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showProgressDialog();
        mPresenter.invokePropagandaQueryPage();
    }

    @Override
    protected void initView() {
        super.initView();
        refreshLayout.setEnableLoadmore(false);
        SinaRefreshView headerView = new SinaRefreshView(context);
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainFragmentPresenter createPresenter() {
        return new MainFragmentPresenter(this);
    }

    @Override
    public void showPropagandaQueryPage(List<PropagandaQueryPageEntity> list) {
        List<File> images = new ArrayList<>();
        for (PropagandaQueryPageEntity entity : list) {
            String base64Str = entity.getImgUrl().split(",")[1];
            byte[] decode = Base64.decode(base64Str,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            saveBitmap(bitmap);
        }
        dismiss();
        refreshLayout.finishRefreshing();
        String path = Environment.getExternalStorageDirectory().getPath()
                +"/decodeImage.jpg";
        File file = new File(path);
        images.add(file);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        banner.start();
    }

    private void saveBitmap(Bitmap bitmap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        try {
            String path = Environment.getExternalStorageDirectory().getPath()
                    +"/decodeImage.jpg";
            Log.d("linc","path is "+path);
            OutputStream stream = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            stream.close();
            Log.e("linc","jpg okay!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("linc","failed: "+e.getMessage());
        }
    }
}
