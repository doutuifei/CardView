package com.muzi.cardview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */

public class Adapter extends BaseQuickAdapter<Bean, BaseViewHolder> {

    public Adapter(@LayoutRes int layoutResId, @Nullable List<Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {
        final CustomCardView customCardView = helper.getView(R.id.cardView);
        customCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCardView.toggle();
            }
        });

    }
}
