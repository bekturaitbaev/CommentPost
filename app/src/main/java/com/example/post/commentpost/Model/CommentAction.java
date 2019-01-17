package com.example.post.commentpost.Model;

import android.os.AsyncTask;

import com.example.post.commentpost.Presenter.CommentPresenter;
import com.example.post.commentpost.Presenter.PostPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CommentAction extends AsyncTask<Void, Void, Void>{
    private String jsonString="";
    private String stringUrl;


    public CommentAction(String postId){
        this.stringUrl = "http://jsonplaceholder.typicode.com/comments?postId="+postId;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL jsonUrl = new URL(this.stringUrl);
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
        CommentPresenter commentPresenter = new CommentPresenter();
        commentPresenter.workWithJsonString(jsonString);
    }
}
