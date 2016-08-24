package courseassignment.nitin.primeornot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        int randomNo = intent.getIntExtra(MainActivity.PRIME_MESSAGE,1);
        String text="";
        TextView cheatText = (TextView) findViewById(R.id.cheat_text);
        if(MainActivity.isPrime(randomNo)){
            text = randomNo + " is Prime";
            cheatText.setText(text);
        }
        else {
            text = randomNo + " is Not Prime";
            cheatText.setText(text);
        }

    }

    public void backButtonClick(View view){
        switch (view.getId()){
            case R.id.cheat_back_btn:

                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
