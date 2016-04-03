/*
 *    Copyright 2015-2016 InQBarna Kenkyuu Jo SL
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */
package com.inqbarna.rxpagingsupport.multiselection;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by albodelu on 3/04/16.
 */
public abstract class ClickableViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private ChoiceCapableAdapter adapter;
    private View row = null;

    public ClickableViewHolder(ChoiceCapableAdapter adapter, View row) {
        super(row);
        this.row = row;
        this.adapter = adapter;
        row.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isCheckedNow = adapter.isChecked(getAdapterPosition());

        adapter.onChecked(getAdapterPosition(), !isCheckedNow);
        row.setActivated(!isCheckedNow);
    }

    public void setRowActivatedOnBind() {
        row.setActivated(adapter.isChecked(getAdapterPosition()));
    }
}
