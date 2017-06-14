package bootstrap.casterio.com.myapplication.fragment;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import bootstrap.casterio.com.myapplication.fragment.dummy.DummyContent;

/**
 * Created by mwolfson on 6/9/17.
 */

public class DummyItemDiffCallback extends DiffUtil.Callback {
    private List<DummyContent.DummyItem> mOldList;
    private List<DummyContent.DummyItem> mNewList;

    public DummyItemDiffCallback(List<DummyContent.DummyItem> oldList, List<DummyContent.DummyItem> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList != null ? mOldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).id.equals(mOldList.get(oldItemPosition).id);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}