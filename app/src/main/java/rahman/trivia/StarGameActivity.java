package rahman.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import rahman.trivia.databinding.ActivityStarGameBinding;
import rahman.trivia.util.Prefs;

public class StarGameActivity extends AppCompatActivity {

    ActivityStarGameBinding starGameBinding;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starGameBinding=ActivityStarGameBinding.inflate(getLayoutInflater());
        setContentView(starGameBinding.getRoot());

        prefs=new Prefs(StarGameActivity.this);
        starGameBinding.startHighScore.setText(prefs.GetHighScore()+"");

        starGameBinding.buttonStart.setOnClickListener(v ->{

            //starGameBinding.buttonStop


            Intent intent= new Intent(StarGameActivity.this,MainActivity.class);
            startActivity(intent);




        });
    }
}