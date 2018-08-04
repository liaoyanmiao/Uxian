package org.leo.uxian.view;

import org.leo.uxian.entity.PropagandaQueryPageEntity;

import java.util.List;

public interface IMainFragment extends IView {
    //炫染宣传接口数据
    void showPropagandaQueryPage(List<PropagandaQueryPageEntity> list);
}
