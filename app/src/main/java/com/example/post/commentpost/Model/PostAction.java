package com.example.post.commentpost.Model;

import android.os.AsyncTask;

import com.example.post.commentpost.Presenter.PostPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PostAction extends AsyncTask<Void, Void, Void>{
    private String jsonString="";


    public PostAction(){

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL jsonUrl = new URL("http://jsonplaceholder.typicode.com/posts");
            HttpURLConnection httpURLConnection = (HttpURLConnection) jsonUrl.openConnection();
            InputStream inputStream =httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                jsonString = jsonString + line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
        PostPresenter postPresenter = new PostPresenter();
        postPresenter.workWithJsonString(jsonString);
    }
}
