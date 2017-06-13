package bootstrap.casterio.com.myapplication.fragment;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import bootstrap.casterio.com.myapplication.R;
import bootstrap.casterio.com.myapplication.fragment.ItemFragment.OnListFragmentInteractionListener;
import bootstrap.casterio.com.myapplication.fragment.dummy.DummyContent.DummyItem;
import bootstrap.casterio.com.myapplication.ui.touchHelper.ItemTouchHelperAdapter;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mView.setBackgroundColor(mValues.get(position).content.contains("Y") ? 0xFFE0E0E0 : 0xFFFAFAFA);

        if (mValues.get(position).content.contains("Y")) {
            Log.d("MSW", "---- IS Y IN ADAPTER");


        } else {
            Log.d("MSW", "   - is X IIN ADAPTER");
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 25;
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
//        //Use Collections to modify the dataset
        Collections.swap(mValues, fromPosition, toPosition);
//        //Notify the items were moved
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        //Remove Item from dataset
        mValues.remove(position);
        //Notify that the item was removed
        notifyItemRemoved(position);
    }

    public void swapItems(List<DummyItem> items) {
        final DummyItemDiffCallback diffCallback = new DummyItemDiffCallback(this.mValues, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mValues.clear();
        this.mValues.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
