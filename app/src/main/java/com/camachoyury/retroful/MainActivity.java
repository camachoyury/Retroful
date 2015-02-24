package com.camachoyury.retroful;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.camachoyury.retroful.rest.RestClient;
import com.camachoyury.retroful.rest.api.UserApi;
import com.camachoyury.retroful.rest.model.User;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private TextView response;
    private EditText edtUsername;
    private EditText edtPassword;
    private ProgressBar progressBar;
    private Button login;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        response = (TextView) findViewById(R.id.response);
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        login.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Boolean cancel = false;

        // Reset errors.
        edtUsername.setError(null);
        edtPassword.setError(null);

        // Store values at the time of the login attempt.
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.error_field_required));
            cancel = true;

        }
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError(getString(R.string.error_field_required));
            cancel = true;
        }

        if (!cancel) {

            String array []= {username, password};
            LoginTask task = new LoginTask();

            task.execute(array);
        }


    }


    public class LoginTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            //textView.setText("Hello !!!");

            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(String... params) {

            String result = "ok";
            Log.i("PARAMS",params[0]+" "+params[1]);
            RestClient client = new RestClient();
            try {
                UserApi api = client.getConnection().create(UserApi.class);
                user = api.login(params[0], params[1]);
            } catch (Exception e) {
                e.printStackTrace();

                user = null;
                result = "error";
            }
            return result;

        }


        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            if (result.equalsIgnoreCase("ok")) {

                response.setText(user.toString());
            } else {

                response.setText("User Not Found");
            }

        }
    }


}
