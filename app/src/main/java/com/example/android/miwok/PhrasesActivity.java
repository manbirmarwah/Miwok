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

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("Where are you going?","Minto wuksus?", R.raw.phrase_where_are_you_going));
        numbers.add(new Word("What is your name?","Tinnә oyaase'nә?", R.raw.phrase_what_is_your_name));
        numbers.add(new Word("My name is...","Oyaaset...", R.raw.phrase_my_name_is));
        numbers.add(new Word("How are you feeling?","Michәksәs?", R.raw.phrase_how_are_you_feeling));
        numbers.add(new Word("I’m feeling good.","Kuchi achit.", R.raw.phrase_im_feeling_good));
        numbers.add(new Word("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        numbers.add(new Word("Yes, I’m coming.","Hәә’ әәnәm.", R.raw.phrase_yes_im_coming));
        numbers.add(new Word("I’m coming.","әәnәm.", R.raw.phrase_im_coming));
        numbers.add(new Word("Let’s go.","Yoowutis", R.raw.phrase_lets_go));
        numbers.add(new Word("Come here.","әnni'nem.", R.raw.phrase_come_here));


        WordAdapter adapter = new WordAdapter(this, numbers);

        ListView listView = (ListView) findViewById(R.id.phrases_activity);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = numbers.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
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
