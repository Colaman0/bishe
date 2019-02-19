package com.kyle.takeaway.framgent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.item.ItemCartProductViewModel;
import com.kyle.takeaway.item.ItemCartViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Create by kyle on 2019/2/18
 * Function :
 */
public class CartFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    private FeaturesAdapter mAdapter;
    private Action mAction;
    private Consumer<ItemCartViewModel> mDeleteAction = new Consumer<ItemCartViewModel>() {
        @Override
        public void accept(ItemCartViewModel viewModel) throws Exception {
            mAdapter.remove(viewModel);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new FeaturesAdapter(getActivity())
                .bindRecyclerView(recyclerview)
                .addItemClickListener((position, itemView) -> Log.d("cola", "position = " + position));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(mAdapter);
        for (int i = 0; i < 10; i++) {
            mAdapter.add(new ItemCartViewModel(getAction()).setDeleteAction(mDeleteAction));
        }
    }

    private Action getAction() {
        if (mAction == null) {
            mAction = () -> {
                int account = 0;
                int size = mAdapter.getDatas().size();
                for (int i = 0; i < size; i++) {
                    account += ((ItemCartViewModel) mAdapter.getDatas().get(i)).getAccount();
                }
                tvTotal.setText(String.valueOf(account));
            };
        }
        return mAction;
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
    }
}
