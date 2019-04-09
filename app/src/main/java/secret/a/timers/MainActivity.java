package secret.a.timers;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Button btn;
    Boolean check=false;
    CountDownTimer countDownTimer;

    public void reset(){
        btn.setText("GO!");
        textView.setText("00:30");
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        countDownTimer.cancel();
        check=false;
    }


    public void click(View view)
    {
        if(check)
        {
            reset();
        }

        else{
            check = true;
            seekBar.setEnabled(false);
            btn.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBar.getProgress()*1000 +100, 1000) {
                @Override
                public void onTick(long l) {
                    change((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer);
                    mediaPlayer.start();
                    reset();

                }
            }.start();
    }

    }

    public void change(int secondsLeft)
    {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondstring = Integer.toString(seconds);
        if (seconds < 10) {
            secondstring = '0' + secondstring;
        }

        String minutestring = Integer.toString(minutes);
        if (minutes < 10) {
            minutestring = '0' + minutestring;
        }

        textView.setText(minutestring + ":" + secondstring);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);

        seekBar.setMax(600);
        setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                change(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}

