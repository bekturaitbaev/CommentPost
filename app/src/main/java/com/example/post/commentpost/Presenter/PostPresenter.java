package com.example.post.commentpost.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.post.commentpost.MainActivity;
import com.example.post.commentpost.Model.PostAction;
import com.example.post.commentpost.Model.PostModel;
import com.example.post.commentpost.View.PostActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostPresenter {
    private static Context mContext;
    private static Activity activity;
    private static PostActivity activityPost;

    private ArrayList<PostModel> postList = new ArrayList<>();
    private MainActivity mMain;

    public PostPresenter(){

    }

    public void getPostList(PostActivity postActivity) {
        activityPost = postActivity;
        PostAction mPostAction = new PostAction();
        mPostAction.execute();
    }

    public void goPostPage(MainActivity mMain, Context mContext, Activity activity){
        this.mMain = mMain;
        this.mContext = mContext;
        this.activity = activity;
        Intent intent = new Intent(mContext, PostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);


    }
    public void workWithJsonString(String jsonString){
        try {
            JSONArray jsonPostArray = new JSONArray(jsonString);
            if(jsonPostArray.length()<30)
            for(int i=0; i<jsonPostArray.length(); i++){
                JSONObject jsonPostObject = (JSONObject) jsonPostArray.get(i);
                postList.add(new PostModel(jsonPostObject.get("title").toString(),
                        jsonPostObject.get("body").toString(),
                        jsonPostObject.get("userId").toString(),
                        jsonPostObject.get("id").toString()));
            }
            else{
                for(int i=0; i<30; i++){
                    JSONObject jsonPostObject = (JSONObject) jsonPostArray.get(i);
                    postList.add(new PostModel(jsonPostObject.get("title").toString(),
                            jsonPostObject.get("body").toString(),
                            jsonPostObject.get("userId").toString(),
                            jsonPostObject.get("id").toString()));
                }
            }
            activityPost.showPostList(postList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
