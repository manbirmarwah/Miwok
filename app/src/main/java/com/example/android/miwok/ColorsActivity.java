package com.example.android.miwok;

import android.media.MediaPlayer;
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

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("Red","Weṭeṭṭi",R.drawable.color_red, R.raw.color_red));
        numbers.add(new Word("Green","Chokokki",R.drawable.color_green, R.raw.color_green));
        numbers.add(new Word("Brown","Takaakki",R.drawable.color_brown, R.raw.color_brown));
        numbers.add(new Word("Mustard Yellow","Chiwiiṭә",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        numbers.add(new Word("Black","Kululli",R.drawable.color_black, R.raw.color_black));
        numbers.add(new Word("White","Kelelli",R.drawable.color_white, R.raw.color_white));
        numbers.add(new Word("Gray","Topoppi",R.drawable.color_gray, R.raw.color_gray));
        numbers.add(new Word("Dusty Yellow","Topiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));


        WordAdapter adapter = new WordAdapter(this, numbers);

        ListView listView = (ListView) findViewById(R.id.colours);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = numbers.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
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
        }
    }
}
