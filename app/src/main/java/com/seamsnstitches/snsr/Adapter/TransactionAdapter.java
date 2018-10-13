package com.seamsnstitches.snsr.Adapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.FragmentOrdersBinding;
import com.seamsnstitches.snsr.databinding.ItemClothModelBinding;
import com.seamsnstitches.snsr.databinding.RowOrderBinding;
import com.seamsnstitches.snsr.fragments.PurchaseConfirmationFragment;
import com.seamsnstitches.snsr.fragments.UserDetailsBottomSheetFragment;
import com.seamsnstitches.snsr.models.ClothModel;
import com.seamsnstitches.snsr.models.Transaction;
import com.seamsnstitches.snsr.utility.AppConstants;
import com.seamsnstitches.snsr.utility.GetLetterImageResource;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList = new ArrayList<>();
    private RowOrderBinding mRowOrderBinding;
    private AppCompatActivity mActivity;
    private FragmentOrdersBinding mFragmentOrdersBinding;
    private boolean isLoadingAdded = false;

    public TransactionAdapter(AppCompatActivity activity, FragmentOrdersBinding mFragmentOrdersBinding) {
        mActivity = activity;
        this.mFragmentOrdersBinding = mFragmentOrdersBinding;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mRowOrderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_order, parent, false);
        return new TransactionViewHolder(mRowOrderBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(holder.getAdapterPosition());
        String name = transaction.getUser().getFirstName() + " " +
                transaction.getUser().getLastName();
        holder.mUserName.setText(name);
        holder.mUserLocation.setText(transaction.getUser().getAddress());
        setFirstLetterOnImageView(holder.mFirstLetterCircleView, name);
    }

    @Override
    public int getItemCount() {
        if (transactionList == null) return 0;
        return transactionList.size();
    }

    /**
     * When this method gets called it clears existing transaction list
     */
    public void clearList() {
        isLoadingAdded = false;

        while (getItemCount() > 0) {
            removeItem(getItem(0));
        }
    }

    public boolean isListEmpty() {
        return transactionList.size() == 0;
    }

    /**
     * A method that initializes the list of transactions
     *
     * @param transactions a parameter for Datat type Movie list
     */
    public void addItemList(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            add(transactions.get(i));
        }
    }

    public void updateIsLoadingTrue() {
        isLoadingAdded = true;
    }

    public void updateIsLoadingFalse() {
        isLoadingAdded = false;
    }

    /**
     * This method returns a transaction item in the list specified by pos
     *
     * @param position position of item required in the list
     * @return Transaction
     */
    public Transaction getItem(int position) {
        return transactionList.get(position);
    }

    /**
     * This method adds item to the list
     *
     * @param transaction
     */
    public void add(Transaction transaction) {
        transactionList.add(transaction);
        notifyItemInserted(transactionList.size() - 1);
    }

    public void removeItem(Transaction transaction) {
        int pos = transactionList.indexOf(transaction);
        if (pos > -1) {
            transactionList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    /**
     * This method helps check the name of the company or user passes into it and gets the first letter
     * of the string and upload the item ImageView accordingly
     *
     * @param imageView the image view where the letter will be updated in form of an image
     * @param text      the name of user or fashion brand
     */
    private void setFirstLetterOnImageView(CircleImageView imageView, String text) {
        imageView.setImageResource(mActivity.getResources().getIdentifier(GetLetterImageResource
                        .getLetterImageResource(text.toLowerCase().charAt(0)), "drawable",
                "com.seamsnstitches.snsr"));
    }

    /**
     * Helper method to get first character of the text and then returns it
     *
     * @param text the string to get the first character from
     * @returns the first character of the text
     */
    private char getFirstLetterFromText(String text) {
        return text.charAt(0);
    }

    public static class ClotheModelAdapter extends RecyclerView.Adapter<ClotheModelAdapter.ClotheModelViewHolder> {
        private List<ClothModel> clothModels;
        private FragmentManager mFragmentManager;
        private ItemClothModelBinding itemClothModelBinding;

        public ClotheModelAdapter(List<ClothModel> clothModels, FragmentManager fragmentManager) {
            this.clothModels = clothModels;
            mFragmentManager = fragmentManager;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @NonNull
        @Override
        public ClotheModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            itemClothModelBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cloth_model,
                            parent, false);
            return new ClotheModelViewHolder(itemClothModelBinding.getRoot());
        }

        @Override
        public void onBindViewHolder(@NonNull final ClotheModelViewHolder holder, int position) {
            String clothModel = clothModels
                    .get(holder.getAdapterPosition())
                    .getClothingType().getName() +
                    "( Order " + clothModels.indexOf(clothModels.get(holder.getAdapterPosition()))
                    + ")";
            holder.clothModelName.setText(clothModel);
            Picasso
                    .get()
                    .load(clothModels.get(holder.getAdapterPosition()).getClothingType().getImageURL())
                    .into(holder.clothModelImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    openPurchaseConfirmationFrag(holder);
                }
            });
        }

        /**
         * This method takes care of showing the dialog fragment
         *
         * @param holder the ViewHolder to get position of items to pass data to the fragment
         */
        private void openPurchaseConfirmationFrag(ClotheModelViewHolder holder) {
            PurchaseConfirmationFragment purchaseFragment
                    = PurchaseConfirmationFragment.newInstance(
                    getConfirmationBundle(holder.getAdapterPosition()));
            purchaseFragment.show(mFragmentManager, AppConstants.PURCHASE_CONFIRMATION_FRAG_TAG);
        }

        /**
         * Inputs data in a bundle and returns it.. so as to be passed to the PurchaseConfirmationFrag
         * to be used to initialize views
         *
         * @param position
         * @return
         */
        public Bundle getConfirmationBundle(int position) {
            Bundle confirmationBundle = new Bundle();
            confirmationBundle.putString(AppConstants.PURCHASE_CONFIRMATION_FRAG_QUANTITY,
                    String.valueOf(clothModels.get(position).getQuantity()));
            return confirmationBundle;
        }

        @Override
        public int getItemCount() {
            return clothModels.size();
        }

        public void addItem(ClothModel clothModel) {
            clothModels.add(clothModel);
        }

        public void addAllItems(List<ClothModel> clothModels) {
            for (ClothModel clothModel : clothModels) {
                addItem(clothModel);
            }
        }

        public class ClotheModelViewHolder extends RecyclerView.ViewHolder {
            private CircleImageView clothModelImage;
            private TextView clothModelName;

            public ClotheModelViewHolder(View itemView) {
                super(itemView);
                initViews();
            }

            private void initViews() {
                clothModelImage = itemClothModelBinding.clothModelImage;
                clothModelName = itemClothModelBinding.clothModelText;
            }
        }
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mFirstLetterCircleView;
        private TextView mUserName, mUserLocation;
        private UserDetailsBottomSheetFragment bottomSheetFragment;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            mFirstLetterCircleView = mRowOrderBinding.rowOrderFirstLetterCircleImage;
            mUserName = mRowOrderBinding.rowOrderUserName;
            mUserLocation = mRowOrderBinding.rowOrderLocationAddress;
            mFragmentOrdersBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bottomSheetFragment != null) {
                        bottomSheetFragment.dismiss();
                    }
                }
            });

            mRowOrderBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClicked();
                }
            });
        }

        /**
         * This method is called when item is clicked to perform neccessary actions
         */
        private void onItemClicked() {
            Bundle bundle = bottomSheetsArgs();
            if (bundle != null) {
                bottomSheetFragment = UserDetailsBottomSheetFragment
                        .newInstance(bundle);
                bottomSheetFragment.show(mActivity.getSupportFragmentManager(),
                        AppConstants.TRANSACTION_DETAIL_TAG);
            }
        }

        /**
         * This method initializes a new Bundle object and put all neccessary details to be transferred
         * to the bottom sheet
         *
         * @return Bundle containing details
         */
        private Bundle bottomSheetsArgs() {
            if (getAdapterPosition() != -1) {
                Transaction transaction = transactionList.get(getAdapterPosition());
                Bundle args = new Bundle();
                args.putParcelable(AppConstants.TRANSACTION_DETAIL_TAG, transaction);
                args.putBoolean(AppConstants.IS_ORDER_LIST, true);
                return args;
            }
            return null;
        }
    }
}
