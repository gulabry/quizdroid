package edu.washington.gulabry.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class QuizFragments extends ActionBarActivity {

    private String topic; //when it's called you want to know the topic first


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_fragments);

        //SET ALL YOUR FIELDS
        Intent launched = getIntent();
        topic = launched.getStringExtra("topic");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new QuizOverview())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_fragments, menu);
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

    public static class QuizOverview extends Fragment {

        private String topic;

        public QuizOverview() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            Bundle launched = getArguments();

            //topic = launched.getString("topic");



            TextView title = (TextView) rootView.findViewById(R.id.title);
            title.setText("This is working!");


            return rootView;
        }
    }


    public static class QuizQuestion extends Fragment {

        public QuizQuestion() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.activity_quiz_fragments, container, false);
            return rootView;
        }

    }

    public static class QuizAnswer extends Fragment {

        public QuizAnswer() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_quiz_fragments, container, false);
            return rootView;
        }

    }
}
