package com.noushad.kidsapp.fragment;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.noushad.kidsapp.R;
import com.noushad.kidsapp.activity.ReArrangeActivity;
import com.noushad.kidsapp.adapter.ReArrangeAdapter;
import com.noushad.kidsapp.data_provider.AbstractDataProvider;


public class ReArrangeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ReArrangeAdapter mAdapter;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    private CardView mCheckButton;
    private TextView mResultText;


    public ReArrangeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_re_arrange, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews();

        setupDragAndDrop();
        setupAdapter();
        setupList();
        completeSetup();
    }

    private void completeSetup() {
        mRecyclerViewDragDropManager.attachRecyclerView(mRecyclerView);
        mRecyclerViewDragDropManager.setItemMoveMode(RecyclerViewDragDropManager.ITEM_MOVE_MODE_SWAP);
        mAdapter.setItemMoveMode(RecyclerViewDragDropManager.ITEM_MOVE_MODE_SWAP);
    }

    private void setupList() {
        GeneralItemAnimator animator = new DraggableItemAnimator();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);
        mRecyclerView.setItemAnimator(animator);
    }

    private void setupAdapter() {
        mAdapter = new ReArrangeAdapter(getContext(), getDataProvider());
        mLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(mAdapter);
    }

    private void setupDragAndDrop() {

        mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();

        mRecyclerViewDragDropManager.setInitiateOnLongPress(true);
        mRecyclerViewDragDropManager.setInitiateOnMove(false);
        mRecyclerViewDragDropManager.setLongPressTimeout(1000);
        mRecyclerViewDragDropManager.setDragStartItemAnimationDuration(250);
        mRecyclerViewDragDropManager.setDraggingItemAlpha(0.8f);
        mRecyclerViewDragDropManager.setDraggingItemScale(1.3f);
        mRecyclerViewDragDropManager.setDraggingItemRotation(15.0f);
    }

    private void initializeViews() {
        mCheckButton = getView().findViewById(R.id.check_button);
        mResultText = getView().findViewById(R.id.result_text);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(v);
                if (mAdapter.isItemsInOrder()) {
                    mResultText.setText("CONGRATULATIONS");
                } else {
                    mResultText.setText("TRY AGAIN");
                }
            }
        });

        mRecyclerView = getView().findViewById(R.id.alphabet_list);
    }

    @Override
    public void onPause() {
        mRecyclerViewDragDropManager.cancelDrag();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (mRecyclerViewDragDropManager != null) {
            mRecyclerViewDragDropManager.release();
            mRecyclerViewDragDropManager = null;
        }

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
        mAdapter = null;
        mLayoutManager = null;

        super.onDestroyView();
    }

    public AbstractDataProvider getDataProvider() {
        return ((ReArrangeActivity) getActivity()).getDataProvider();
    }

    private void animate(View v) {

        Animator scaleFab = AnimatorInflater.loadAnimator(getContext(), R.animator.scale);
        scaleFab.setTarget(v);

        scaleFab.start();

    }

}
