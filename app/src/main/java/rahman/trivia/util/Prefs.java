package rahman.trivia.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import rahman.trivia.MainActivity;
import rahman.trivia.StarGameActivity;

public class Prefs {

    private SharedPreferences preferences;
    private Activity activities;


    public Prefs(Activity activities) {

        this.preferences = activities.getPreferences(Context.MODE_PRIVATE);
    }

    public void SaveHighScore(int scores){

        int currentScore=scores;

        int lastScore=preferences.getInt("high_score", 0);

        if (currentScore>lastScore){
            preferences.edit().putInt("high_score",currentScore).apply();
        }

    }

    public int GetHighScore(){

        return preferences.getInt("high_score",0);

    }

}
