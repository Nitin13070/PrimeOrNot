package courseassignment.nitin.primeornot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView scoreTextView = (TextView) findViewById(R.id.score);

        int highScore = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE).getInt(getString(R.string.score_title),0);
        scoreTextView.setText(Integer.toString(highScore));
    }


}
