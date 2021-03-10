package rahman.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rahman.trivia.data.AnswerListAsyncResponse;
import rahman.trivia.data.QuestionBank;
import rahman.trivia.databinding.ActivityMainBinding;
import rahman.trivia.model.Question;
import rahman.trivia.model.Score;
import rahman.trivia.util.Prefs;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private int currentindex = 0;
    private List<Question> questionsListis;
    private int scoreCounter=0;
    private final Score scorescore=new Score();
    private  Prefs prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        prefs=new Prefs(MainActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();

       TimerStart();
        mainBinding.highScoreTextView.setText("Highest Score: "+prefs.GetHighScore()+"");

        questionsListis = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questions) {

                Random random = new Random();
                currentindex = random.nextInt(questions.size());

                mainBinding.questionTextView.setText(questions.get(currentindex).getAnswer());
                mainBinding.counterTextView.setText(currentindex + " " + "/" + questionsListis.size());

            }
        });

        True();
        False();
//        Next();
//        Previous();

    }

    private void Next() {

//        mainBinding.imageNextButton.setOnClickListener(v -> {

            Random randomNext = new Random();
            currentindex = randomNext.nextInt(questionsListis.size());

            currentindex = (currentindex + 1) % questionsListis.size();
            UpdateQuestion();

//        });
    }

//    private void Previous() {
//
//        mainBinding.imagPreviousButton.setOnClickListener(v -> {
//
//            if (currentindex > 0) {
//
//                Random randomNext = new Random();
//                currentindex = randomNext.nextInt(questionsListis.size());
//                currentindex = (currentindex - 1) % questionsListis.size();
//                UpdateQuestion();
//            }
//
//        });
//
//    }

    private void False() {

        mainBinding.buttonFalse.setOnClickListener(v -> {
            CheckAnswer(false);
        });

    }


    private void True() {

        mainBinding.buttonTrue.setOnClickListener(v -> {
            CheckAnswer(true);

        });
    }

    private void UpdateQuestion() {
        String que = questionsListis.get(currentindex).getAnswer();
        mainBinding.questionTextView.setText(que);
        mainBinding.counterTextView.setText(currentindex + " " + "/" + questionsListis.size());
    }

    private void CheckAnswer(boolean b) {

        boolean b1 = questionsListis.get(currentindex).isAnswerTrue();
        if (b == b1) {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();

            AddPoint();

        } else {
            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();

            SubPoint();
        }
        Next();

    }


    private void TimerStart() {

  new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                mainBinding.timerTextView.setText("remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {

                prefs.SaveHighScore(scorescore.getScores());

                Intent intent= new Intent(MainActivity.this,StarGameActivity.class);
                startActivity(intent);

            }

          }.start();

    }



    private void AddPoint(){
        scoreCounter += 2;
        scorescore.setScores(scoreCounter);
        mainBinding.scoreTextView.setText("Current Score: "+scorescore.getScores()+"");
    }
    private void SubPoint(){
        scoreCounter -= 1;
        if (scoreCounter <= 0) {
            scoreCounter = 0;
        }
        scorescore.setScores(scoreCounter);

        mainBinding.scoreTextView.setText("Current Score: "+scorescore.getScores()+"");
    }
}