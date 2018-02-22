package com.noushad.kidsapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.noushad.kidsapp.R;
import com.noushad.kidsapp.data_provider.AbstractDataProvider;
import com.noushad.kidsapp.model.Alphabet;
import com.noushad.kidsapp.utils.DrawableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 2/22/18.
 */

public class ReArrangeAdapter
        extends RecyclerView.Adapter<ReArrangeAdapter.MyViewHolder>
        implements DraggableItemAdapter<ReArrangeAdapter.MyViewHolder> {


    private static final String TAG = "MyDraggableItemAdapter";
    private int mItemMoveMode;

    private interface Draggable extends DraggableItemConstants {
    }

    private static Context mContext;
    private AbstractDataProvider mProvider;

    public ReArrangeAdapter(Context context, AbstractDataProvider dataProvider) {
        mContext = context;
        mProvider = dataProvider;
        setHasStableIds(true);
    }

    public void setItemMoveMode(int itemMoveMode) {
        mItemMoveMode = itemMoveMode;
    }

    public static class MyViewHolder extends AbstractDraggableItemViewHolder implements View.OnClickListener {
        public CardView mContainer;
        public View mDragHandle;
        public TextView mTextView;
        private Alphabet mItem;

        public MyViewHolder(View v) {
            super(v);
            mContainer = v.findViewById(R.id.item_container);
            mDragHandle = v.findViewById(R.id.item_drag_handle);
            mTextView = v.findViewById(R.id.item_alphabet);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //
            Toast.makeText(mContext, String.valueOf(mItem.getId()), Toast.LENGTH_SHORT).show();
        }

        public void bind(Alphabet item) {
            mItem = item;
            mTextView.setText(item.getText());

            final int dragState = getDragStateFlags();

            if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {
                int bgResId;

                if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                    bgResId = R.color.colorRed;
                    mTextView.setTextColor(mContext.getResources().getColor(R.color.coloWhite));
                    // need to clear drawable state here to get correct appearance of the dragging item.
                    DrawableUtils.clearState(mContainer.getForeground());


                } else if ((dragState & Draggable.STATE_FLAG_DRAGGING) != 0) {
                    bgResId = R.color.colorAccent;
                } else {
                    bgResId = R.color.colorGreen;
                    mTextView.setTextColor(mContext.getResources().getColor(R.color.coloWhite));
                }

                mContainer.setCardBackgroundColor(mContext.getResources().getColor(bgResId));
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.alphabet_grid_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public long getItemId(int position) {
        return mProvider.getItem(position).getId();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Alphabet item = (Alphabet) mProvider.getItem(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mProvider.getCount();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Log.d(TAG, "onMoveItem(fromPosition = " + fromPosition + ", toPosition = " + toPosition + ")");

        if (mItemMoveMode == RecyclerViewDragDropManager.ITEM_MOVE_MODE_DEFAULT) {
            mProvider.moveItem(fromPosition, toPosition);
        } else {
            mProvider.swapItem(fromPosition, toPosition);
        }
    }


    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }


    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    @Override
    public void onItemDragStarted(int position) {
        notifyDataSetChanged();
    }

    @Override
    public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {
        notifyDataSetChanged();

    }

    public boolean isItemsInOrder() {

        List<Alphabet> elements = new ArrayList<>();
        for (int i = 0; i < mProvider.getCount(); i++) {
            elements.add((Alphabet) mProvider.getItem(i));
        }

        Alphabet prev = null;
        for (Alphabet alphabet : elements) {
            if (prev != null && prev.compareTo(alphabet) > 0) {
                return false;
            }
            prev = alphabet;
        }
        return true;

    }
}
