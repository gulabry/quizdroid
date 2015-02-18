package edu.washington.gulabry.quizapp;

import java.io.Serializable;

/**
 * Created by Gula on 2/12/15.
 */
public class Question implements Serializable {

    String question;
    String[] options;
    int answer;

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

}
