package edu.washington.gulabry.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Quiz extends ActionBarActivity {

    private Bundle questionBundle;
    private int currentQuestion;
    private int correctAnswer;
    private int numberCorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent currentIntent = getIntent();
        questionBundle = currentIntent.getBundleExtra("questions");
        currentQuestion = currentIntent.getIntExtra("currentQuestion", 0);
        correctAnswer = currentIntent.getIntExtra("correctAnswer", 0);
        numberCorrect = currentIntent.getIntExtra("numberCorrect", 0);

        ArrayList questions = (ArrayList)questionBundle.getSerializable("serializedQuestions");

        final String topic = currentIntent.getStringExtra("topic");

        System.out.println(currentQuestion);

        final Question currentQ = (Question) questions.get(currentQuestion);

        TextView questionBody = (TextView) findViewById(R.id.question);
        questionBody.setText(currentQ.question);


        TextView answer0 = (TextView) findViewById(R.id.answer1);
        answer0.setText(currentQ.options[0]);

        TextView answer1 = (TextView) findViewById(R.id.answer2);
        answer1.setText(currentQ.options[1]);

        TextView answer2 = (TextView) findViewById(R.id.answer3);
        answer2.setText(currentQ.options[2]);

        TextView answer3 = (TextView) findViewById(R.id.answer4);
        answer3.setText(currentQ.options[3]);

        final Button submitAnswer = (Button)findViewById(R.id.Submit);

        final RadioGroup answerGroup = (RadioGroup)findViewById(R.id.answerGroup);

        answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submitAnswer.setVisibility(View.VISIBLE);
            }
        });

        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedAnswer = (RadioButton) findViewById(answerGroup.getCheckedRadioButtonId());
                String selectedString = selectedAnswer.getText().toString();
                Intent summary = new Intent(Quiz.this, AnswerOverview.class);
                summary.putExtra("topic", topic);
                summary.putExtra("selectedString", selectedString); //to string of option selected
                summary.putExtra("correctAnswer", currentQ.options[currentQ.answer - 1]); //answer
                currentQuestion++;
                summary.putExtra("currentQuestion", currentQuestion); //moving forward the question
                summary.putExtra("numberCorrect", numberCorrect); //number of Questions correctly answered
                summary.putExtra("questions", questionBundle); //bundle
                startActivity(summary);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
