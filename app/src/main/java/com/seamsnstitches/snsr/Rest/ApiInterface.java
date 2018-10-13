package com.seamsnstitches.snsr.Rest;

import com.seamsnstitches.snsr.models.api.request.LoginModel;
import com.seamsnstitches.snsr.models.api.request.TransactionsSearchParams;
import com.seamsnstitches.snsr.models.api.response.EmployeeModel;
import com.seamsnstitches.snsr.models.api.response.GenericPageResponseModel;
import com.seamsnstitches.snsr.models.api.response.UserModel;
import com.seamsnstitches.snsr.utility.ApiConstant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET(ApiConstant.SEARCH_USER_URL)
    Call<UserModel> getUserBySearch(@Path("email") String usersEmail);

    @POST(ApiConstant.LOGIN_BASE_URL)
    Call<EmployeeModel> loginRequest(@Body LoginModel loginModel);

    @POST(ApiConstant.TRANSACTIONS_SEARCH_BASE_URL)
    Call<GenericPageResponseModel> searchTransactions(@Path("brand-id") long brandId,
                                                      @Path("page-number") int pageNumber,
                                                      @Body TransactionsSearchParams transactionsSearchParams);


}
