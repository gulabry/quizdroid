package edu.washington.gulabry.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AnswerOverview extends ActionBarActivity {

    private String topic;
    private int currentQuestion;
    private Bundle questionBundle;
    private int numberCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_overview);

        Intent launched = getIntent();
        topic = launched.getStringExtra("topic");
        String correctAnswer = launched.getStringExtra("correctAnswer");
        String userAnswer = launched.getStringExtra("selectedString");

        currentQuestion = launched.getIntExtra("currentQuestion", 0);
        numberCorrect = launched.getIntExtra("numberCorrect", 0);
        questionBundle = launched.getBundleExtra("questions");

        final ArrayList<Question> questions = (ArrayList) questionBundle.getSerializable("serializedQuestions");

        TextView correctView = (TextView) findViewById(R.id.correctAnswer);
        correctView.setText("The correct answer is " + correctAnswer);

        TextView selectedView = (TextView) findViewById(R.id.userAnswer);
        selectedView.setText("You answered " + userAnswer);

        if (userAnswer.equals(correctAnswer)) {
            numberCorrect++;
        }

        TextView correctNumberView = (TextView) findViewById(R.id.total);
        correctNumberView.setText("You've correctly answered " + numberCorrect + " questions out of " + questions.size()
        );

        Button next = (Button)findViewById(R.id.next);
        if (currentQuestion == questions.size()) {
            next.setText("Finish");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion == questions.size()) {
                    Intent backToHome = new Intent(AnswerOverview.this, MainActivity.class);
                    startActivity(backToHome);
                } else {
                    Intent backToQuiz = new Intent(AnswerOverview.this, Quiz.class);
                    backToQuiz.putExtra("topic", topic);
                    backToQuiz.putExtra("currentQuestion", currentQuestion);
                    backToQuiz.putExtra("questions", questionBundle);
                    backToQuiz.putExtra("numberCorrect", numberCorrect);
                    startActivity(backToQuiz);
                    finish();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_overview, menu);
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
