package com.inqbarna.rxpagingsupport;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by david on 14/10/15.
 */
public abstract class RxPagedAdapter<T, VH extends RecyclerView.ViewHolder & RxPagedAdapter.LoadHolder> extends RecyclerView.Adapter<VH> {

    protected final static int RESERVED_LOADING_TYPE = R.layout.reserved_loading;

    public interface LoadHolder {
        void setLoadingState(boolean loading);
    }

    private PageManager<T> manager;

    public RxPagedAdapter(Settings settings, Bundle savedInstanceState) {
        manager = new PageManager<>(this, settings, savedInstanceState);
    }

    public void enableMovementDetection(RecyclerView view) {
        manager.enableMovementDetection(view);
    }

    public void disableMovementDetection(RecyclerView view) {
        manager.disableMovementDetection(view);
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RESERVED_LOADING_TYPE) {
            return createLoadingViewHolder(parent);
        } else {
            return doCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        if (isLastPosition(position)) {
            holder.setLoadingState(true);
        } else {
            doBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLastPosition(position)) {
            return RESERVED_LOADING_TYPE;
        }
        return View.NO_ID;
    }

    private boolean isLastPosition(int position) {
        // this is true, when we should do special loading operation...
        return !manager.isLastPageSeen() && position == manager.getTotalCount();
    }

    protected abstract VH createLoadingViewHolder(ViewGroup parent);

    protected abstract void doBindViewHolder(VH holder, int position);
    protected abstract VH doCreateViewHolder(ViewGroup parent, int viewType);

    public T getItem(int pos) {
        return manager.getItem(pos);
    }

    public PageManager<T>.ConnectionManager beginConnection() {
        return manager.beginConnection();
    }

    public int getTotalCount() {
        return manager.getTotalCount();
    }

    @Override
    public int getItemCount() {
        return !manager.isLastPageSeen() ? manager.getTotalCount() + 1 : manager.getTotalCount();
    }

    public void recycle() {
        manager.recycle();
    }

    public void onSaveInstanceState(Bundle outState) {
        manager.onSaveInstanceState(outState);
    }
}
