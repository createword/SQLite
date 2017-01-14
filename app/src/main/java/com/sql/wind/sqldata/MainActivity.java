package com.sql.wind.sqldata;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.sql.wind.sqldata.general.L;
import com.sql.wind.sqldata.model.ArticleModel;
import com.sql.wind.sqldata.sqlLite.AbsDBAPI;
import com.sql.wind.sqldata.sqlLite.DbFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AbsDBAPI<ArticleModel> mArticleModel = DbFactory.createArticleModel();
    ArrayList<ArticleModel> modelsList = new ArrayList<ArticleModel>();

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
                for (int i = 0; i < 10; i++) {
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.setPid(1);
                    articleModel.setUserName("userName");
                    articleModel.setTitle("DdddddAF");
                    articleModel.setContent("FADSFDssssssssssssAS");
                    ArticleModel articleModel2 = new ArticleModel();
                    articleModel2.setPid(2);
                    articleModel2.setUserName("admin");
                    articleModel2.setTitle("ee");
                    articleModel2.setContent("dddd");
                    modelsList.add(articleModel);
                    modelsList.add(articleModel2);
                }

                mArticleModel.saveItems(modelsList);

            }
        });
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
