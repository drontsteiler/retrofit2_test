package com.dormitory.retrofit_test.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dormitory.retrofit_test.api.ApiService;
import com.dormitory.retrofit_test.utils.InternetConnection;
import com.dormitory.retrofit_test.model.Post;
import com.dormitory.retrofit_test.adapter.PostAdapter;
import com.dormitory.retrofit_test.model.PostList;
import com.dormitory.retrofit_test.R;
import com.dormitory.retrofit_test.api.RetroClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private View parentView;
    private ArrayList<Post> postList;
    private PostAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InternetConnection.checkConnection(getApplicationContext())){
                    final ProgressDialog dialog;

                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    ApiService api = RetroClient.getApiService();
                    Call<PostList> call=api.getMyJSON();
                    call.enqueue(new Callback<PostList>() {
                        @Override
                        public void onResponse(Call<PostList> call, Response<PostList> response) {
                            dialog.dismiss();
                            if(response.isSuccessful()){
                                postList=response.body().getPosts();
                                adapter = new PostAdapter(MainActivity.this, postList);
                                listView.setAdapter(adapter);
                            }else{
                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostList> call, Throwable t) {
                            dialog.dismiss();
                            Snackbar.make(parentView, R.string.string_failed, Snackbar.LENGTH_LONG).show();



                        }
                    });
                }else {
                    Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });


        postList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(parentView, postList.get(i).getTitle() + postList.get(i).getBody(), Snackbar.LENGTH_LONG).show();
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();

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
}
