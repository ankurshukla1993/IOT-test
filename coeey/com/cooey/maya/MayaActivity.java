package com.cooey.maya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import chatkit.Message;
import chatkit.commons.ImageLoader;
import chatkit.messages.MessageInput;
import chatkit.messages.MessageInput.InputListener;
import chatkit.messages.MessagesList;
import chatkit.messages.MessagesListAdapter;
import chatkit.messages.MessagesListAdapter.HoldersConfig;
import chatkit.messages.MessagesListAdapter.OnMessageLongClickListener;
import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.trncic.library.DottedProgressBar;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import javax.websocket.MessageHandler.Whole;

public class MayaActivity extends AppCompatActivity implements RecognitionListener {
    private static long time;
    private MessagesListAdapter adapter;
    ContentManager contentManager;
    private Editor editor;
    Gson gson = new GsonBuilder().create();
    private ImageView imvSpeech;
    private MessageInput input;
    private boolean isListening = false;
    private MessagesList messagesList;
    private boolean mute;
    private RelativeLayout relativeLayout;
    private SharedPreferences sharedPreferences;
    private DottedProgressBar speechProgressBar;
    private SpeechRecognizer speechRecognizer;
    private Intent speetchIntent;
    private Timer timer;
    private Toolbar toolbar;
    private TextToSpeech tts;
    private WebSocketConnector webSocketConnector;

    class C09691 implements OnInitListener {
        C09691() {
        }

        public void onInit(int status) {
            if (status == 0) {
                int result = MayaActivity.this.tts.setLanguage(Locale.US);
                if (result != -1 && result != -2) {
                }
            }
        }
    }

    class C09712 implements InputListener {
        C09712() {
        }

        public boolean onSubmit(CharSequence input) {
            MayaActivity.this.adapter.addToStart(new Message(UUID.randomUUID().toString(), input.toString()), true);
            Object mayaRequest = new MayaRequest();
            mayaRequest.setQuery(input.toString());
            if (ContentManager.contextId != null) {
                mayaRequest.setContextId(ContentManager.contextId);
            }
            final String mayaRequestJson = MayaActivity.this.gson.toJson(mayaRequest);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        MayaActivity.this.webSocketConnector.getSession().getBasicRemote().sendText(mayaRequestJson);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        }
    }

    class C09723 implements OnClickListener {
        C09723() {
        }

        public void onClick(View v) {
            if (MayaActivity.this.isListening) {
                MayaActivity.this.speechRecognizer.stopListening();
                MayaActivity.this.speechProgressBar.setVisibility(8);
                MayaActivity.this.speechProgressBar.startProgress();
                MayaActivity.this.isListening = false;
                return;
            }
            MayaActivity.this.tts.stop();
            MayaActivity.this.speechRecognizer.startListening(MayaActivity.this.speetchIntent);
            MayaActivity.this.speechProgressBar.setVisibility(0);
            MayaActivity.this.speechProgressBar.startProgress();
            MayaActivity.this.isListening = true;
        }
    }

    class C09734 implements OnClickListener {
        C09734() {
        }

        public void onClick(View v) {
            MayaActivity.this.recreate();
        }
    }

    class C09745 implements OnInitListener {
        C09745() {
        }

        public void onInit(int status) {
            if (status == 0) {
                int result = MayaActivity.this.tts.setLanguage(Locale.US);
                if (result != -1 && result != -2) {
                }
            }
        }
    }

    class C09766 implements Whole<String> {
        C09766() {
        }

        public void onMessage(String message) {
            final MayaResponse mayaResponse = (MayaResponse) MayaActivity.this.gson.fromJson(message, MayaResponse.class);
            ContentManager.contextId = mayaResponse.getContextId();
            MayaActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (mayaResponse.getDialog() != null) {
                        MayaActivity.this.convertTextToSpeech(mayaResponse.getDialog());
                        MayaActivity.time = 50000;
                        MayaActivity.this.adapter.addToStart(new Message("maya", mayaResponse.getDialog()), true);
                        if (mayaResponse.getContentList() != null) {
                            Message contentMessage = new Message(UUID.randomUUID().toString());
                            contentMessage.setContents(mayaResponse.getContentList());
                            MayaActivity.this.adapter.addToStart(contentMessage, true);
                            return;
                        }
                        return;
                    }
                    contentMessage = new Message("maya");
                    contentMessage.setContents(mayaResponse.getContentList());
                    MayaActivity.this.adapter.addToStart(contentMessage, true);
                }
            });
        }
    }

    class C09777 implements ImageLoader {
        C09777() {
        }

        public void loadImage(ImageView imageView, String url) {
            Picasso.with(MayaActivity.this).load(C0981R.drawable.imv_maya).into(imageView);
        }
    }

    class C09788 implements OnMessageLongClickListener<Message> {
        C09788() {
        }

        public void onMessageLongClick(Message message) {
        }
    }

    class C09799 implements Runnable {
        C09799() {
        }

        public void run() {
        }
    }

    class secondTask extends TimerTask {

        class C09801 implements Runnable {
            C09801() {
            }

            public void run() {
                MayaActivity.time = MayaActivity.time - 1000;
                if (MayaActivity.time == 2000) {
                    MayaActivity.this.stopProcesses();
                    MayaActivity.this.showReconnectSnackBar();
                }
            }
        }

        secondTask() {
        }

        public void run() {
            MayaActivity.this.runOnUiThread(new C09801());
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0981R.layout.activity_maya);
        this.messagesList = (MessagesList) findViewById(C0981R.id.messagesList);
        this.input = (MessageInput) findViewById(C0981R.id.input);
        this.imvSpeech = (ImageView) findViewById(C0981R.id.imv_speech);
        this.speechProgressBar = (DottedProgressBar) findViewById(C0981R.id.pb_speech);
        this.toolbar = (Toolbar) findViewById(C0981R.id.toolbar);
        this.relativeLayout = (RelativeLayout) findViewById(C0981R.id.relative_layout);
        initMessagesAdapter();
        initSpeechRecognizer();
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Maya");
        this.sharedPreferences = getSharedPreferences("userInfo", 0);
        this.editor = this.sharedPreferences.edit();
        if (Boolean.valueOf(this.sharedPreferences.getBoolean("isFirstTime", true)).booleanValue()) {
            displayTuto1();
            this.editor.putBoolean("isFirstTime", false);
            this.editor.commit();
        }
        Bundle data = getIntent().getExtras();
        if (Boolean.valueOf(data.getBoolean("isCaretaker")).booleanValue()) {
            this.webSocketConnector = new WebSocketConnector(data.getString(CTConstants.PATIENT_ID), data.getString(CTConstants.CARETAKER_ID));
        } else {
            this.webSocketConnector = new WebSocketConnector(data.getString(CTConstants.PATIENT_ID));
        }
        this.contentManager = new ContentManager(this, this.webSocketConnector, this.adapter);
        this.tts = new TextToSpeech(this, new C09691());
        this.input.setInputListener(new C09712());
        this.speechRecognizer.setRecognitionListener(this);
        this.imvSpeech.setOnClickListener(new C09723());
        initializeWebSockets();
        this.timer = new Timer();
        this.timer.schedule(new secondTask(), 0, 1000);
    }

    private void showReconnectSnackBar() {
        Snackbar.make(this.relativeLayout, "", -2).setAction("RECONNECT", new C09734()).show();
    }

    private void displayTuto1() {
    }

    private void displayTuto2() {
    }

    private void displayTuto3() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0981R.menu.menu_material_maya, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == 16908332) {
            onBackPressed();
        } else if (i == C0981R.id.refresh_maya) {
            stopProcesses();
            recreate();
        } else if (i != C0981R.id.button_mute) {
            return super.onOptionsItemSelected(item);
        } else {
            if (this.mute) {
                item.setIcon(C0981R.drawable.ic_icon_mic);
                item.setTitle("Mute");
                this.mute = false;
                this.tts = null;
                this.tts = new TextToSpeech(this, new C09745());
            } else {
                item.setIcon(C0981R.drawable.ic_icon_mic_mute);
                item.setTitle("UnMute");
                this.mute = true;
                this.tts.shutdown();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
        stopProcesses();
    }

    private void initSpeechRecognizer() {
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        this.speetchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.speetchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        this.speetchIntent.putExtra("calling_package", getPackageName());
    }

    private void convertTextToSpeech(String dialog) {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (dialog == null || "".equals(dialog)) {
            this.tts.speak("Content not available", 1, null, null);
            return;
        }
        this.tts.speak(dialog, 1, null, null);
    }

    protected void onPause() {
        super.onPause();
        stopProcesses();
        showReconnectSnackBar();
    }

    public void onDestroy() {
        super.onDestroy();
        stopProcesses();
    }

    private void initializeWebSockets() {
        this.webSocketConnector.connect(new C09766());
    }

    private void initMessagesAdapter() {
        ImageLoader imageLoader = new C09777();
        HoldersConfig holdersConfig = new HoldersConfig();
        holdersConfig.setIncoming(CustomIncomingMessageViewHolder.class, C0981R.layout.item_custom_holder_incoming_message);
        holdersConfig.setOutcoming(CustomOutcomingMessageViewHolder.class, C0981R.layout.item_custom_holder_outcoming_message);
        this.adapter = new MessagesListAdapter("maya", holdersConfig, imageLoader);
        this.adapter.setOnMessageLongClickListener(new C09788());
        this.messagesList.setAdapter(this.adapter);
    }

    private void stopProcesses() {
        this.tts.shutdown();
        this.timer.cancel();
        this.timer.purge();
        this.speechRecognizer.destroy();
        this.webSocketConnector.close();
    }

    private void loadMessages() {
        new Handler().postDelayed(new C09799(), 1000);
    }

    public void onReadyForSpeech(Bundle params) {
    }

    public void onBeginningOfSpeech() {
    }

    public void onRmsChanged(float rmsdB) {
    }

    public void onBufferReceived(byte[] buffer) {
        for (byte b : buffer) {
            System.out.println("MayaActivity -> Speech -> " + b);
        }
    }

    public void onEndOfSpeech() {
        this.speechRecognizer.stopListening();
        this.speechProgressBar.setVisibility(8);
        this.speechProgressBar.stopProgress();
    }

    public void onError(int error) {
    }

    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList("results_recognition");
        if (matches != null && matches.size() > 0) {
            this.adapter.addToStart(new Message(UUID.randomUUID().toString(), (String) matches.get(0)), true);
            Object mayaRequest = new MayaRequest();
            mayaRequest.setQuery((String) matches.get(0));
            if (ContentManager.contextId != null) {
                mayaRequest.setContextId(ContentManager.contextId);
            }
            final String mayaRequestJson = this.gson.toJson(mayaRequest);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        MayaActivity.this.webSocketConnector.getSession().getBasicRemote().sendText(mayaRequestJson);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void onPartialResults(Bundle partialResults) {
    }

    public void onEvent(int eventType, Bundle params) {
    }
}
