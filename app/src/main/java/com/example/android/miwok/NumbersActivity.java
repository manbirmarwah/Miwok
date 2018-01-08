package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteController;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(00);
            // Pause playback
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            // Resume playback
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            releaseMediaPlayer();
            // Stop playback
        }
    }
};

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final AudioManager.OnAudioFocusChangeListener afChangeListener = null;

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "Na'aacha", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(this, numbers);

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = numbers.get(position);
                //Log.v("NumbersActivity", "Current word: " + word);
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }}
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}

