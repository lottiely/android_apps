package com.example.sweethotchocolate.raiseadaughter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //      0 = baby
    //      1 = toddler
    //      2 = elementary
    //      3 = middle school
    //      4 = high school
    //      5 = grown up
    private int counter = 0;
    private int growthCounter = 0;
     private int closetCounter = 0;
    private int currentPicture = 0;
    private Boolean restartTheGame = false;
    private int currentState =0;
    private String daughternamestring;
    private String nicknamestring;
    private int toddlernum, elementarynum, middlenum, highnum, grownupnum, endNum;
    MediaPlayer mysound;
    MediaPlayer buttonClick;
    MediaPlayer buttonCancel;
    MediaPlayer gameMusic;
    MediaPlayer quickSelect;
    MediaPlayer endSong;
    TextView timeunit;
    Button photoalbum;
    Button next;
    Button back;
    Button room;
    Button start;
    Button closetno;
    Button closetyes;
    Button closet;
    View textbox;
    TextView story_text;
    RelativeLayout relativeLayout;
    SharedPreferences preferences;
    private ArrayList<String> story = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeArrayList();
        textbox = findViewById(R.id.textbox);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        start = (Button) findViewById(R.id.startbutton);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        timeunit = (TextView) findViewById(R.id.timeunit);
        photoalbum=(Button) findViewById(R.id.photoalbum);
        room=(Button) findViewById(R.id.room);
        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);
        mysound = MediaPlayer.create(this, R.raw.flattery);
        mysound.setLooping(true);
        mysound.start();
        buttonClick = MediaPlayer.create(this, R.raw.itemgetsea);
        buttonCancel = MediaPlayer.create(this, R.raw.cancel8);
        quickSelect = MediaPlayer.create(this, R.raw.enter);
        gameMusic = MediaPlayer.create(this, R.raw.high_tech2);
        gameMusic.setLooping(true);
        endSong = MediaPlayer.create(this, R.raw.breakfast);
        endSong.setLooping(true);
        story_text = (TextView) findViewById(R.id.story_text);
        closetno = (Button) findViewById(R.id.closetno);
        closetyes = (Button) findViewById(R.id.closetyes);
        closet = (Button) findViewById(R.id.button);
        endNum = 17;
    }

    public void startClick(View view){
        buttonClick.start();
        mysound.release();
        startgame(view);
    }

    public void cancelClick(View view){
        buttonCancel.start();
        namingThingsScreen(view);
    }

    public void returnToGameClick(View view){
        quickSelect.start();
        closetCounter=0;
        story_text.setText("");
        textbox.setVisibility(View.INVISIBLE);
        playGame(view);
        closetno.setVisibility(View.INVISIBLE);
        closetyes.setVisibility(View.INVISIBLE);
    }

    public void resetGame() {
        counter = 0;
        growthCounter = 0;
        currentPicture = 0;
        currentState = 0;
        daughternamestring = "";
        nicknamestring = "";
        mysound = MediaPlayer.create(this, R.raw.flattery);
        mysound.setLooping(true);
        endSong = MediaPlayer.create(this, R.raw.breakfast);
        endSong.setLooping(true);
        closetno.setVisibility(View.INVISIBLE);
        closetyes.setVisibility(View.INVISIBLE);
        story_text.setText("");
        restartTheGame = false;
    }

    public void enableListener(){
        relativeLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                counter++;
                readStory(v);
            }
        });
    }

    public void enableEndListener(){
        relativeLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //counter++;
                readStory(v);
            }
        });
    }

    public void enableListenerForGame() {
        relativeLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView theCounter = (TextView)findViewById(R.id.timeunit);
                growthCounter++;
                theCounter.setText("Magical Time Unit: " + growthCounter);
                if (growthCounter == 5) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    currentState++;
                    startgame(v);
                }
                if (growthCounter == 10) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    currentState++;
                    startgame(v);
                }
                if (growthCounter == 15) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    currentState++;
                    startgame(v);
                }
                if (growthCounter == 20) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    currentState++;
                    startgame(v);
                }
                if (growthCounter == 25) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    currentState++;
                    startgame(v);
                } else if (growthCounter >= 25 && currentState == 5) {
                    closet.setVisibility(View.INVISIBLE);
                    pauseGame();
                    endGame(v);
                }
            }
        });
    }

    public void initializeArrayList(){

        //Counter starts at 0
        story.add("While I was cleaning out the guest room, I found a baby in the closet. " +
                "After all the initial questions of who, what why, how went through my head, I took action" +
                "and tried to find a proper home for the baby.");
        story.add("But when I tried to find help, everyone thought I was crazy for finding a baby in my closet." +
        "Most thought I was just trying to hide an affair.");
        story.add("Since no one would believe me, I wasn't left with much options.");
        story.add("So I agreed to take care of the baby.");
        //counter = 3, next will be 4
    }

    public void addToArrayList(){
        //counter starts at 4
        // Toddler Story
        story.add("Time went by really fast. Before I knew it, " +daughternamestring+" is already a toddler." +
                "The little baby of what felt like a few seconds ago grew to be a very energetic girl.");
        // Elementary Story starts at 5
        toddlernum=4;
        story.add("She was so energetic when it was just me and her, but soon she had to start going to school. " +
        "Her wild antics slowly started to go away, and before I knew it, she had become such a shy girl.");
        story.add("She always told me she had fun at school, so I wondered why she became so shy." +
                " Once I asked her why she stopped all her crazy games, and she responded by telling me " +
        " that most teachers didn't like noisy students, and that her classmates would call her childish.");
        story.add("I told her it was fine to be the way she wanted to be, but she only gave me a dismissive "+
        "response. I guess this is just how kids are when they start growing up.");
        // Middle Story starts at 8
        elementarynum=8;
        story.add("Her time in elementary school went by, and she was now in middle school. The crazy little " +
        "toddler I knew was naught but a memory. Now, I saw a little girl that tried to grow up too fast.");
        story.add("But I knew she was still a child at heart, since I knew all her favorite games and favorite "+
        "candies. I was always around the corner to tempt out her childish side. At least when she's at home, "+
        "she doesn't have to pretend.");
        middlenum=10;
        story.add("She was in high school now, and she was certainly more distant. She no longer went to sit next "+
        "to me just so she could go on and on about how the other kids were childish and how she was praised "+
        "for something about something.");
        story.add("But now, I have no idea what kind of student she is since she never tells me about what goes on "+
        "in school. She only ever stares at her little phone screen. I asked her once if she was chatting her boyfriend.");
        story.add("She snorted and looked at me as if I asked a silly question and told me that high school boys "+
        "are all idiots. She refused to stoop down to their level just to date someone. I couldn't help but wonder "+
        "where she developed that mindset.");
        highnum=13;
        story.add("It's been a few days after her graduation, and she was already preparing to go off... somewhere." +
        " I told her to do whatever she wanted to do, and she responded by saying that's what I've always been "+
        "telling her since she was little.");
        story.add("One morning, I went to her room and found that all her things were packed away. She stood in the room "+
        "looking at it sadly.");
        grownupnum=15;
        story.add("Thank you for all that you've done for me. I'm happy that you chose to take care of me.");
        story.add("Goodbye, " + nicknamestring);
    }

    public void pauseGame(){
        gameMusic.pause();
        timeunit.setVisibility(View.INVISIBLE);
        photoalbum.setVisibility(View.INVISIBLE);
        relativeLayout.setOnClickListener(null);
    }

    public void startgame(View view) {
        relativeLayout.setBackgroundColor(Color.BLACK);
        start.setVisibility(View.INVISIBLE);
        readStory(view);

    }

    public void readStory(View view){
        enableListener();
        textbox.setVisibility(View.VISIBLE);
        if(counter == 4 && currentState==0) {
            namingThingsScreen(view);
            story_text.setText("");
        }else if(counter == toddlernum+1 && currentState==1) {
            story_text.setText("");
            playGame(view);
        }else if(counter == elementarynum && currentState==2) {
            story_text.setText("");
            playGame(view);
        }else if(counter == middlenum && currentState==3) {
            story_text.setText("");
            playGame(view);
        }else if(counter == highnum && currentState==4) {
            story_text.setText("");
            playGame(view);
        }else if(counter == grownupnum && currentState==5) {
            story_text.setText("");
            playGame(view);
        }else if(counter == endNum){
            story_text.setText("");
            resetGame();
            restart();
        }else
            story_text.setText(story.get(counter));
    }

    public void namingThingsScreen(View view){

        relativeLayout.setOnClickListener(null);
        textbox.setVisibility(View.INVISIBLE);
        relativeLayout.setBackgroundResource(R.drawable.promptbackground);

        TextView prompt = (TextView) findViewById(R.id.prompt);
        prompt.setVisibility(View.VISIBLE);

        EditText daughtername = (EditText) findViewById(R.id.daughternametextfield);
        daughtername.setVisibility(View.VISIBLE);


        prompt=(TextView) findViewById(R.id.prompt2);
        prompt.setVisibility(View.VISIBLE);

        EditText yourname = (EditText) findViewById(R.id.enteryourname);
        yourname.setVisibility(View.VISIBLE);

        Button button = (Button) findViewById(R.id.send);
        button.setVisibility(View.VISIBLE);

        button = (Button) findViewById(R.id.no);
        if(button.getVisibility() == View.VISIBLE)
        {
            button.setVisibility(View.INVISIBLE);
            button = (Button) findViewById(R.id.yes);
            button.setVisibility(View.INVISIBLE);
        }
    }

    public void confirmNames(View view){
        quickSelect.start();
        EditText daughtername = (EditText) findViewById(R.id.daughternametextfield);
        EditText yourname = (EditText) findViewById(R.id.enteryourname);
        Button button = (Button) findViewById(R.id.send);
        button.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.no);
        button.setVisibility(View.VISIBLE);
        button = (Button) findViewById(R.id.yes);
        button.setVisibility(View.VISIBLE);

        if(daughtername.getText().toString().matches("") || yourname.getText().toString().matches("")){
            namingThingsScreen(view);
        }
        else {

            TextView prompt = (TextView) findViewById(R.id.prompt2);
            prompt.setVisibility(View.INVISIBLE);

            daughtername.setVisibility(View.INVISIBLE);
            daughternamestring = daughtername.getText().toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Daughter Name", daughternamestring);
            editor.commit();

            yourname.setVisibility(View.INVISIBLE);
            nicknamestring = yourname.getText().toString();
            editor.putString("Nickname", nicknamestring);
            editor.commit();

            prompt = (TextView) findViewById(R.id.prompt);
            prompt.setText("Is " + preferences.getString("Daughter Name", "") + " and "
                    + preferences.getString("Nickname", "") + " okay?");
        }

    }

    public void makeButtonsInvisible(View view){
        buttonClick.start();
        Button button = (Button) findViewById(R.id.yes);
        button.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.no);
        button.setVisibility(View.INVISIBLE);
        TextView prompt = (TextView) findViewById(R.id.prompt);
        prompt.setVisibility(View.INVISIBLE);
        addToArrayList();
        playGame(view);
    }

    public void checkWhichRoomPicture(){
        if(currentState == 0)
            relativeLayout.setBackgroundResource(R.drawable.babyroom);
        else if(currentState == 1)
            relativeLayout.setBackgroundResource(R.drawable.toddlerroom);
        else if(currentState==2)
            relativeLayout.setBackgroundResource(R.drawable.elementaryroom);
        else if(currentState == 3)
            relativeLayout.setBackgroundResource(R.drawable.middleschoolroom);
        else if(currentState==4)
            relativeLayout.setBackgroundResource(R.drawable.highschoolroom);
        else if(currentState==5) {
            relativeLayout.setBackgroundResource(R.drawable.adultroom);
            timeunit.setVisibility(View.INVISIBLE);
            photoalbum.setVisibility(View.INVISIBLE);
        }
    }

    public void checkWhichClosePicture(){
        if(currentPicture == 0)
            relativeLayout.setBackgroundResource(R.drawable.babycloseup);
        else if(currentPicture == 1)
            relativeLayout.setBackgroundResource(R.drawable.toddlercloseup);
        else if(currentPicture==2)
            relativeLayout.setBackgroundResource(R.drawable.elementarycloseup);
        else if(currentPicture == 3)
            relativeLayout.setBackgroundResource(R.drawable.middleschoolcloseup);
        else if(currentPicture==4)
            relativeLayout.setBackgroundResource(R.drawable.highschoolcloseup);
        else if(currentPicture==5)
            relativeLayout.setBackgroundResource(R.drawable.adultcloseup);
    }

    public void playGame(View view){
        timeunit.setVisibility(View.VISIBLE);
        photoalbum.setVisibility(View.VISIBLE);
        textbox.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        closet.setVisibility(View.VISIBLE);

        gameMusic.start();

        checkWhichRoomPicture();
        enableListenerForGame();
    }

    public void nextPicture(View view){
        quickSelect.start();
        currentPicture++;
        checkWhichClosePicture();
        if(currentState > currentPicture)
            next.setVisibility(View.VISIBLE);
        else
            next.setVisibility(View.INVISIBLE);
        if(currentPicture!=0)
            back.setVisibility(View.VISIBLE);
    }

    public void backPicture(View view){
        quickSelect.start();
        currentPicture--;
        checkWhichClosePicture();
        if(currentState > currentPicture)
            next.setVisibility(View.VISIBLE);
        else
            next.setVisibility(View.INVISIBLE);
        if(currentPicture!=0)
            back.setVisibility(View.VISIBLE);
        else
            back.setVisibility(View.INVISIBLE);
    }

    public void photoAlbum(View view){
        quickSelect.start();
        closet.setVisibility(View.INVISIBLE);
        currentPicture = 0;

        room.setVisibility(View.VISIBLE);
        photoalbum.setVisibility(View.INVISIBLE);
        relativeLayout.setBackgroundResource(R.drawable.babycloseup);
        if(currentState >= 1)
            next.setVisibility(View.VISIBLE);
    }

    public void endGame(View view){
        currentState++;
        relativeLayout.setBackgroundResource(R.drawable.adultcloseup);
        endSong.start();
        enableEndListener();
    }

    public void restart(){
        relativeLayout.setBackgroundResource(R.drawable.titlescreen);
        start.setVisibility(View.VISIBLE);
        relativeLayout.setOnClickListener(null);
        textbox.setVisibility(View.INVISIBLE);
        endSong.release();
        mysound.start();
    }


    public void closet(View view){
        if(restartTheGame==true){
            resetGame();
            restart();
        }
        else {
            pauseGame();
            relativeLayout.setBackgroundColor(Color.BLACK);
            textbox.setVisibility(View.VISIBLE);
            story_text.setText("Investigate closet?");
            closetno.setVisibility(View.VISIBLE);
            closetyes.setVisibility(View.VISIBLE);
        }
    }

    public void incrementClosetCount(View view){
        closetCounter++;
        if(closetCounter==7)
        {
            story_text.setText("..........................Something is here.");
            closetno.setVisibility(View.INVISIBLE);
            closetyes.setVisibility(View.INVISIBLE);
            restartTheGame=true;
        }
        else
            story_text.setText("..........................Nothing is here.");
    }
}
