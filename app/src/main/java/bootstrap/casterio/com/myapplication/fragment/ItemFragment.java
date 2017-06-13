package bootstrap.casterio.com.myapplication.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;

import java.util.List;

import bootstrap.casterio.com.myapplication.R;
import bootstrap.casterio.com.myapplication.fragment.dummy.DummyContent;
import bootstrap.casterio.com.myapplication.fragment.dummy.DummyContent.DummyItem;
import bootstrap.casterio.com.myapplication.ui.touchHelper.OnStartDragListener;
import bootstrap.casterio.com.myapplication.ui.touchHelper.SimpleItemTouchHelperCallback;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment implements OnStartDragListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mFragType = 1;
    private OnListFragmentInteractionListener mListener;

    public static final int FRAG_LINEAR = 1;
    public static final int FRAG_GRID = 2;
    public static final int FRAG_STAG_GRID = 3;

    private static final int NUM_COLUMNS = 3;

    private RecyclerView recyclerView;
    private Drawable smallDivider;
    private GridDividerItemDecoration gridDecoration;

    private ItemTouchHelper itemTouchHelper;

    private MyItemRecyclerViewAdapter adapter;
    private List<DummyItem> items;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFragType = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            Context context = view.getContext();

            smallDivider = context.getDrawable(R.drawable.divider_drawable);
            gridDecoration = new GridDividerItemDecoration(smallDivider, smallDivider, NUM_COLUMNS);

            if (mFragType <= FRAG_LINEAR) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

                recyclerView.addItemDecoration(
                        new DividerItemDecoration(recyclerView.getContext(),
                                DividerItemDecoration.VERTICAL));
            } else if (mFragType == FRAG_GRID) {
                recyclerView.setLayoutManager(new GridLayoutManager(context, NUM_COLUMNS));
                //Use the custom Item Decoration class
//                recyclerView.addItemDecoration(new GridDividerDecoration(recyclerView.getContext()));

//                //Large divider on bottom
//                Drawable lgDivider = context.getDrawable(R.drawable.divider_lg);
//                recyclerView.addItemDecoration(
//                        new GridDividerItemDecoration(lgDivider, lgDivider, NUM_COLUMNS));
//
//                //Medium Divider in middle
//                Drawable medDivider = context.getDrawable(R.drawable.divider_med);
//                recyclerView.addItemDecoration(
//                        new GridDividerItemDecoration(medDivider, medDivider, NUM_COLUMNS));
//
//                //Small divider on top
//                Drawable smallDivider = context.getDrawable(R.drawable.divider_sm);
//                recyclerView.addItemDecoration(
//                        new GridDividerItemDecoration(smallDivider, smallDivider, NUM_COLUMNS));
            } else if (mFragType == FRAG_STAG_GRID) {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(NUM_COLUMNS, StaggeredGridLayoutManager.VERTICAL));

            }
            items = DummyContent.ITEMS;
            adapter = new MyItemRecyclerViewAdapter(items, mListener);
            recyclerView.setAdapter(adapter);

            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
            itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_changes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        int LIST_INDEX = 1;

        switch (id) {
            case R.id.action_additem:
                Log.d("MSW", "---- ONE PRESSED ---");

                List<DummyItem> newItems = items;
                newItems.add(LIST_INDEX, DummyContent.createDummyItemY(newItems.size()));
                newItems.add(LIST_INDEX + 1, DummyContent.createDummyItemY(newItems.size()));

                items.add(LIST_INDEX, DummyContent.createDummyItemY(items.size()));
                items.add(LIST_INDEX + 1, DummyContent.createDummyItemY(items.size()));
//
                adapter.notifyItemRangeInserted(LIST_INDEX, 2);
////            adapter.notifyItemInserted(index);
////            adapter.notifyDataSetChanged();

//                adapter.swapItems(newItems);

                Log.d("MSW", "Size of orig list");
//                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DummyItemDiffCallback(items, newItems));
//                diffResult.dispatchUpdatesTo(adapter);
//
                return true;
            case R.id.action_deleteitem:
                items.remove(LIST_INDEX);
                items.remove(LIST_INDEX + 1);
                adapter.notifyItemRangeRemoved(LIST_INDEX, 2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }


    /**
     *
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }

    private boolean dividersVisible = false;

    public void toggleDividerVisibility() {
        if (dividersVisible) {
            recyclerView.removeItemDecoration(gridDecoration);
            dividersVisible = false;
        } else {
            recyclerView.addItemDecoration(gridDecoration);
            dividersVisible = true;
        }
    }

}
