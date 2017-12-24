package com.cooey.messaging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import chatkit.Message;
import chatkit.commons.ImageLoader;
import chatkit.messages.MessageInput;
import chatkit.messages.MessageInput.InputListener;
import chatkit.messages.MessagesList;
import chatkit.messages.MessagesListAdapter;
import chatkit.messages.MessagesListAdapter.HoldersConfig;
import com.cooey.api.ApiClient;
import com.cooey.api.client.MessagesApi;
import com.cooey.maya.C0981R;
import com.cooey.maya.CustomIncomingMessageViewHolder;
import com.cooey.maya.CustomOutcomingMessageViewHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {
    private MessagesListAdapter<Message> adapter;
    private ApiClient apiClient;
    private String authToken;
    private MessageInput input;
    private MessagesList messagesList;
    private MessagesWebSocketConnector messagesWebSocketConnector;
    private String receiverId;
    private String senderId;

    class C09851 implements Callback<List<com.cooey.api.client.models.Message>> {
        C09851() {
        }

        public void onResponse(Call<List<com.cooey.api.client.models.Message>> call, Response<List<com.cooey.api.client.models.Message>> response) {
            int i = 0;
            while (response != null && i < ((List) response.body()).size()) {
                com.cooey.api.client.models.Message newMessage = (com.cooey.api.client.models.Message) ((List) response.body()).get(i);
                Message message = new Message(newMessage.getId(), newMessage.getText());
                message.setSender(newMessage.getFromUserId());
                MessagesActivity.this.adapter.addToStart(message, true);
                i++;
            }
        }

        public void onFailure(Call<List<com.cooey.api.client.models.Message>> call, Throwable t) {
            Log.d("data", "Getting messages fialed");
        }
    }

    class C09883 implements ImageLoader {
        C09883() {
        }

        public void loadImage(ImageView imageView, String url) {
            Picasso.with(MessagesActivity.this).load(C0981R.drawable.imv_maya).into(imageView);
        }
    }

    class C09894 implements MessageReceivedCallback {
        C09894() {
        }

        public void onMessageReceived(UserMessage userMessage) {
            Message message = new Message(userMessage.getFromUserId(), userMessage.getText());
            message.setSender(userMessage.getFromUserId());
            MessagesActivity.this.adapter.addToStart(message, true);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0981R.layout.activity_messages);
        this.senderId = getIntent().getStringExtra("senderId");
        this.receiverId = getIntent().getStringExtra("receiverId");
        this.authToken = getIntent().getStringExtra("authToken");
        this.apiClient = new ApiClient();
        getMessages();
        Toolbar toolbar = (Toolbar) findViewById(C0981R.id.toolbar);
        this.input = (MessageInput) findViewById(C0981R.id.input);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeState();
        initializeAdapeter();
        initializeWebSockets();
        initializeInput();
    }

    private void getMessages() {
        ((MessagesApi) this.apiClient.createService(MessagesApi.class)).getMessages_0(this.authToken, this.senderId, this.receiverId).enqueue(new C09851());
    }

    private void initializeInput() {
        final Gson gson = new GsonBuilder().create();
        this.input.setInputListener(new InputListener() {
            public boolean onSubmit(CharSequence input) {
                MessagesActivity.this.adapter.addToStart(new Message(MessagesActivity.this.senderId, input.toString()), true);
                Object userMessage = new UserMessage();
                userMessage.setFromUserId(MessagesActivity.this.senderId);
                userMessage.setToUserId(MessagesActivity.this.receiverId);
                userMessage.setCreatedOn(Calendar.getInstance().getTimeInMillis());
                userMessage.setUpdatedOn(Calendar.getInstance().getTimeInMillis());
                userMessage.setText(input.toString());
                final String mayaRequestJson = gson.toJson(userMessage);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            MessagesActivity.this.messagesWebSocketConnector.getmWebSocketClient().send(mayaRequestJson);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return true;
            }
        });
    }

    private void initializeState() {
        Bundle extras = getIntent().getExtras();
        this.senderId = extras.getString("senderId");
        this.receiverId = extras.getString("receiverId");
    }

    private void initializeAdapeter() {
        ImageLoader imageLoader = new C09883();
        this.messagesList = (MessagesList) findViewById(C0981R.id.messagesList);
        HoldersConfig holdersConfig = new HoldersConfig();
        holdersConfig.setIncoming(CustomIncomingMessageViewHolder.class, C0981R.layout.item_custom_holder_incoming_message);
        holdersConfig.setOutcoming(CustomOutcomingMessageViewHolder.class, C0981R.layout.item_custom_holder_outcoming_message);
        this.adapter = new MessagesListAdapter(this.senderId, holdersConfig, imageLoader);
        this.messagesList.setAdapter(this.adapter);
    }

    private void initializeWebSockets() {
        this.messagesWebSocketConnector = new MessagesWebSocketConnector(this.senderId);
        this.messagesWebSocketConnector.connect(new C09894());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
