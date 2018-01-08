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

public class FamilyMembersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family_members);

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("Father", "әpә", R.drawable.family_father, R.raw.family_father));
        numbers.add(new Word("Mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        numbers.add(new Word("Son", "Angsi", R.drawable.family_son, R.raw.family_son));
        numbers.add(new Word("Daughter", "Tune", R.drawable.family_daughter, R.raw.family_daughter));
        numbers.add(new Word("Elder Brother", "Taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        numbers.add(new Word("Younger Brother", "Chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        numbers.add(new Word("Elder Sister", "Teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        numbers.add(new Word("Younger Sister", "Kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        numbers.add(new Word("Grandmother", "Ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        numbers.add(new Word("Grandfather", "Paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this, numbers);

        ListView listView = (ListView) findViewById(R.id.activity_family);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = numbers.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getmAudioResourceId());
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
        }
    }
}
