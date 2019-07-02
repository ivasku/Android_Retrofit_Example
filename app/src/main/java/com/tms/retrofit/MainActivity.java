package com.tms.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.text_result);

        // we create retrifut object with static main URL
        // and we specify the JSON converter we use
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //now we can create out API where the Retrofit will implement the functions found in this interface
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // to execute our network request we use Call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();


        // we call enque to run on backround thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // if response is not 404
                // response should be something between 200 and 300
                // we check if the response is ok, if it is not, simply return
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                // we get our list of posts, every Json object
                List<Post> post = response.body();

                for (Post data: post) {

                    String content = "";
                    content += "ID: " + data.getId() + "\n";
                    content += "User ID: " + data.getUserId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Text: " + data.getText() + "\n\n";

                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "Error in call enque" + t.getLocalizedMessage());
                Log.d(TAG, "Error in call enque" + t.getMessage());
                Log.d(TAG, "Error in call enque" + t.getCause());
            }
        });
    }
}
