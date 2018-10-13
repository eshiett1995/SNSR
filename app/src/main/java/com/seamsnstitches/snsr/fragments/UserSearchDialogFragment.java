package com.seamsnstitches.snsr.fragments;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.Rest.ApiClient;
import com.seamsnstitches.snsr.Rest.ApiInterface;
import com.seamsnstitches.snsr.databinding.FragmentUserSearchBinding;
import com.seamsnstitches.snsr.models.User;
import com.seamsnstitches.snsr.models.api.response.UserModel;
import com.seamsnstitches.snsr.utility.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchDialogFragment extends DialogFragment {
    private FragmentUserSearchBinding mUserSearchBinding;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: Listen to when search button is clicked call searchUser()
        mUserSearchBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_user_search, container, false);
        progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        mUserSearchBinding.searchButton.setEnabled(true);
        mUserSearchBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    progressDialog.show();
                    mUserSearchBinding.searchButton.setEnabled(false);
                    searchUser(mUserSearchBinding
                            .searchUsername.getText().toString());
                }
            }
        });
        return mUserSearchBinding.getRoot();
    }

    /**
     * This helper method hels to check if the email inputted in the editText
     * is a valid email
     *
     * @return true if valid else false if not valid
     */
    public boolean validate() {
        boolean valid = true;

        String email = mUserSearchBinding.searchUsername.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mUserSearchBinding.searchUsername.setError("enter a valid email address");
            valid = false;
        } else {
            mUserSearchBinding.searchUsername.setError(null);
        }
        return valid;
    }

    private void searchUser(String email) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserModel> call = apiService.getUserBySearch(email);

        if (call == null) return;
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                progressDialog.dismiss();
                mUserSearchBinding.searchButton.setEnabled(true);
                UserModel userModel = response.body();
                if (userModel == null) return;
                User user = userModel.getUser();
                if (user == null) return;
                openUserDetailsBottomSheet(user);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressDialog.dismiss();
                mUserSearchBinding.searchButton.setEnabled(true);
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This helper method helps in opening a UserDetailsBottomSheetFragments
     *
     * @param user containing the details of the user, put into a bundle to be passed to the
     *             fragment
     */
    private void openUserDetailsBottomSheet(User user) {
        UserDetailsBottomSheetFragment userDetailsBottomSheetFragment =
                UserDetailsBottomSheetFragment.newInstance(getBundle(user));
        userDetailsBottomSheetFragment.show(getActivity()
                .getSupportFragmentManager(), AppConstants.TRANSACTION_DETAIL_TAG);
    }

    /**
     * Creates a new bundle puts a boolean to indicate that d call to open UserDetailsBottomSheet
     * is from search made for a particular user and an User model that contains user's details
     *
     * @param user user object containing details gotten from the Api
     * @return bundle
     */
    private Bundle getBundle(User user) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(AppConstants.IS_ORDER_LIST, false);
        bundle.putParcelable(AppConstants.SEARCH_USER_DETAIL_TAG, user);
        return bundle;
    }
}
