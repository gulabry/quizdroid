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


public class quizOverview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_overview);

        Intent topicSelected = getIntent(); //get the intent that got me here
        final String topic = topicSelected.getStringExtra("topic"); //get the data I need from that

        TextView title = (TextView)findViewById(R.id.title);
        TextView description = (TextView)findViewById(R.id.Description);
        Button start = (Button) findViewById(R.id.button);
        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);

        Question math1 = new Question("What's 5 times 5", new String[]{"5", "10", "15", "25"}, 4);
        Question math2 = new Question("What's 2 to the Power of 4", new String[]{"15", "16", "25", "40"}, 2);
        Question math3 = new Question("What's 4 + 5 + 6", new String[]{"5", "10", "15", "25"}, 3);
        Question math4 = new Question("6 + 5 - 4", new String[]{"7", "10", "15", "25"}, 1);

        final ArrayList<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(math1);
        mathQuestions.add(math2);
        mathQuestions.add(math3);
        mathQuestions.add(math4);

        final ArrayList<Question> physicsQuestions = new ArrayList<>();
        Question phy1 = new Question("If you throw something where does it go?", new String[]{"Up", "Down", "Up THEN Down", "Down THEN Up"}, 1);
        Question phy2 = new Question("E = MC ^ 2 was posited by..?", new String[]{"You", "Albert Einstein", "Obama", "Down THEN Up"}, 2);
        Question phy3 = new Question("Was Y2K real?", new String[]{"Yes", "Maybe", "No", "Down THEN Up"}, 3);

        physicsQuestions.add(phy1);
        physicsQuestions.add(phy2);
        physicsQuestions.add(phy3);

        final ArrayList<Question> mshQs = new ArrayList<>();
        Question m1 = new Question("Who is Batman?", new String[]{"Bruce Almighty", "Bruce Wayne", "Bruce Mayne", "Bruce Jenner"}, 2);
        Question m2 = new Question("Who is Spiderman", new String[]{"Peter Parker", "Peter Travers", "Peter Piper", "Peter Jennings"}, 1);
        Question m3 = new Question("Who is Superman", new String[]{"Clark Gable", "Clark Fable", "Clark Shark", "Clark Kent"}, 4);

        mshQs.add(m1);
        mshQs.add(m2);
        mshQs.add(m3);

        if (topic.equals("Math")) {
            title.setText("Math!");
            description.setText("The Best Math Quiz Around, Guy!");
            questionNumber.setText("There are " + String.valueOf(mathQuestions.size() + " Questions in this quiz."));
        } else if (topic.equals("Physics")) {
            title.setText("Physics!");
            description.setText("The Best Physics Quiz Around, Buddy!");
            questionNumber.setText("There are " + String.valueOf(physicsQuestions.size() + " Questions in this quiz."));
        } else if (topic.equals("Marvel Super Heros")) {
            title.setText("Marvel Super Heros!");
            description.setText("The Best Marvel Quiz Around, Pal!");
            questionNumber.setText("There are " + String.valueOf(mshQs.size() + " Questions in this quiz."));
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent quiz = new Intent(quizOverview.this, Quiz.class);

                Bundle questionBundle = new Bundle(); //put all questions in container

                if (topic.equals("Math")) {
                    questionBundle.putSerializable("serializedQuestions", mathQuestions); //

                } else if (topic.equals("Physics")){
                    questionBundle.putSerializable("serializedQuestions", physicsQuestions);

                } else {
                    questionBundle.putSerializable("serializedQuestions", mshQs);
                }

                quiz.putExtra("questions", questionBundle);
                quiz.putExtra("count", 0);
                quiz.putExtra("topic", topic);
                startActivity(quiz);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_overview, menu);
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
