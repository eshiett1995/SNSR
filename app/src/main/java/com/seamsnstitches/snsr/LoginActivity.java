package com.seamsnstitches.snsr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seamsnstitches.snsr.Rest.ApiClient;
import com.seamsnstitches.snsr.Rest.ApiInterface;
import com.seamsnstitches.snsr.models.FashionBrand;
import com.seamsnstitches.snsr.models.api.request.LoginModel;
import com.seamsnstitches.snsr.models.api.response.EmployeeModel;
import com.seamsnstitches.snsr.utility.AppConstants;
import com.seamsnstitches.snsr.utility.SNSR_SharedPreferences.LoginStatusPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oto-obong on 14/01/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginStatusPreference.initLoginStatusPreference(getApplicationContext());
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed("Login failed, input correct data and try again");
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        onLoginTask(_emailText.getText().toString(), _passwordText.getText().toString());

        /**UserModel userModel = new UserModel();

         userModel.setEmail(_emailText.getText().toString());

         userModel.setPassword(_passwordText.getText().toString());

         new Apihelper().Login(userModel, new Apihelper.RetrofitCallback<LoginModel>() {

        @Override public void onCompleted(Throwable t, LoginModel result, boolean status) {

        if(status){

        // ToDo add save here.................

        }else{

        SweetAlertDialog dialog = Dialogs.getSweetAlertErrorDialog(LoginActivity.this,"Failed",result.getResponseMessage());

        dialog.show();

        }

        }
        });  **/

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                        startActivity(intent);
//
//                    }
//                }, 3000);
    }

    private void onLoginTask(String email, String password) {
        final LoginModel loginModel = new LoginModel();
        loginModel.setEmail(email);
        loginModel.setPassword(password);
        //loginModel.setDateCreated();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<EmployeeModel> call = apiService.loginRequest(loginModel);

        if (call == null) return;
        call.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                EmployeeModel employeeModel = response.body();
                Log.e(TAG, call.request().url().toString());
                if (employeeModel == null) {
                    Log.e(TAG, "Employee Model is null ooooooooooooooh");
                    //Toast.makeText(LoginActivity.this, , Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                Log.e(TAG, "IsLogin Successful: - " + employeeModel.getResponseModel().getIsSuccessful());
                Log.e(TAG, "Response: " + employeeModel.getResponseModel().getResponseMessage());

                if (employeeModel.getResponseModel().getIsSuccessful()) {
                    // On complete call either onLoginSuccess or onLoginFailed
                    onLoginSuccess();
                    progressDialog.dismiss();
//                    Log.e(TAG, " +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                    Log.e(TAG, " FashionBrand ----- " + employeeModel.getEmployee().getFashionBrand().getId());
//                    Log.e(TAG, " +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra(AppConstants.EMPLOYEE_DETAILS, employeeModel);
                    intent.setExtrasClassLoader(FashionBrand.class.getClassLoader());
                    intent.putExtra(AppConstants.FASHION_BRAND, employeeModel.getEmployee().getFashionBrand());
                    startActivity(intent);
                } else {
                    onLoginFailed(employeeModel.getResponseModel().getResponseMessage());
                    progressDialog.dismiss();
                    Log.e(TAG, "Login Failed and is called in onResponse block");

                }
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {
                Log.e(TAG, call.request().url().toString());
                progressDialog.dismiss();
                Log.e(TAG, "Login Failed and is called in onFailure block");
                String msg = t.getMessage();
                if (t.getMessage() == null) msg = "Message Is Null";
                t.printStackTrace();
                Log.e(TAG, msg);
                onLoginFailed(msg);
//                Toast.makeText(LoginActivity.this, "Login Not Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        LoginStatusPreference.setIsLoggedIn(true);
        finish();
    }

    public void onLoginFailed(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
