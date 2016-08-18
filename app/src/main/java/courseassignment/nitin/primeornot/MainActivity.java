package courseassignment.nitin.primeornot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int randomNumber;
    public final int MIN_DISTANCE=150;
    private float y1,y2;
    private int highScore,currentScore;
    TextView currScoreDisplay;

    private boolean isPrime(int n){
        if (n%2==0) return false;
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(1000)+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            randomNumber = getRandomNumber();
            currentScore = 0;
        }
        else {
            randomNumber = savedInstanceState.getInt("RandomNumber");
            currentScore = savedInstanceState.getInt("CurrentScore");
        }
        TextView randNumDisplay = (TextView) findViewById(R.id.prime_display);

        currScoreDisplay = (TextView) findViewById(R.id.curr_score);


        randNumDisplay.setText(Integer.toString(randomNumber));
        highScore = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE).getInt(getString(R.string.score_title),0);
        //currentScore=0;
        currScoreDisplay.setText(Integer.toString(currentScore));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("RandomNumber",randomNumber);
        outState.putInt("CurrentScore",currentScore);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();

                    float distance = y1 - y2;
                    if(distance > MIN_DISTANCE){
                        //go to new activity
                        Intent intent = new Intent(this,Score.class);
                        startActivity(intent);
                    }


                break;
        }
        return super.onTouchEvent(event);
    }


    public void checkAnswer(View view){
        TextView randNumDisplay;
        Toast toast = new Toast(getApplicationContext());
        View toastLayout = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_layout));
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fontawesome-webfont.ttf");
        TextView toastIcon = (TextView) toastLayout.findViewById(R.id.toast_icon);
        TextView toastText = (TextView) toastLayout.findViewById(R.id.toast_text);
        toastIcon.setTypeface(font);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,100);

        switch (view.getId()){
            case R.id.correct_btn:
                if(isPrime(randomNumber)){


                    toastIcon.setText(getResources().getString(R.string.font_awesome_smile_happy));
                    toastText.setText("Correct");
                    toast.setView(toastLayout);
                    toast.show();
                    currentScore++;
                    //Toast.makeText(getApplicationContext(),"Your are Right",Toast.LENGTH_SHORT).show();

                }
                else {
                    toastIcon.setText(getResources().getString(R.string.font_awesome_smile_sad));
                    toastText.setText("Wrong");
                    toast.setView(toastLayout);
                    toast.show();
                    currentScore=0;
                }
                randomNumber = getRandomNumber();
                randNumDisplay = (TextView) findViewById(R.id.prime_display);
                randNumDisplay.setText(Integer.toString(randomNumber));
                break;
            case R.id.incorrect_btn:
                if(isPrime(randomNumber)){
                    toastIcon.setText(getResources().getString(R.string.font_awesome_smile_sad));
                    toastText.setText("Wrong");
                    toast.setView(toastLayout);
                    toast.show();
                    currentScore=0;
                }
                else {
                    toastIcon.setText(getResources().getString(R.string.font_awesome_smile_happy));
                    toastText.setText("Correct");
                    toast.setView(toastLayout);
                    toast.show();
                    currentScore++;
                }
                randomNumber = getRandomNumber();
                randNumDisplay = (TextView) findViewById(R.id.prime_display);
                randNumDisplay.setText(Integer.toString(randomNumber));
                break;
        }
        currScoreDisplay.setText(Integer.toString(currentScore));
        if(currentScore > highScore){
            highScore = currentScore;

            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE).edit();
            editor.putInt(getString(R.string.score_title), highScore);
            editor.commit();
        }
    }

    public void nextNumber(View view){
        randomNumber = getRandomNumber();
        TextView randNumDisplay = (TextView) findViewById(R.id.prime_display);
        randNumDisplay.setText(Integer.toString(randomNumber));
        
    }

}
