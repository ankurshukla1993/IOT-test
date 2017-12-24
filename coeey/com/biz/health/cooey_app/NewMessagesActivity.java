package com.biz.health.cooey_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.cooey.maya.CustomIncomingMessageViewHolder;
import com.cooey.maya.CustomOutcomingMessageViewHolder;
import com.cooey.messaging.MessageReceivedCallback;
import com.cooey.messaging.MessagesWebSocketConnector;
import com.cooey.messaging.UserMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMessagesActivity extends AppCompatActivity {
    private MessagesListAdapter<Message> adapter;
    private ApiClient apiClient;
    private String authToken;
    private MessageInput input;
    private MessagesList messagesList;
    private MessagesWebSocketConnector messagesWebSocketConnector;
    private String receiverId;
    private String senderId;

    class C06371 implements Callback<List<com.cooey.api.client.models.Message>> {
        C06371() {
        }

        public void onResponse(Call<List<com.cooey.api.client.models.Message>> call, Response<List<com.cooey.api.client.models.Message>> response) {
            if (response != null && response.body() != null) {
                int i = 0;
                while (response != null && i < ((List) response.body()).size()) {
                    com.cooey.api.client.models.Message newMessage = (com.cooey.api.client.models.Message) ((List) response.body()).get(i);
                    Message message = new Message(newMessage.getId(), newMessage.getText());
                    message.setSender(newMessage.getFromUserId());
                    NewMessagesActivity.this.adapter.addToStart(message, true);
                    i++;
                }
            }
        }

        public void onFailure(Call<List<com.cooey.api.client.models.Message>> call, Throwable t) {
        }
    }

    class C06403 implements ImageLoader {
        C06403() {
        }

        public void loadImage(ImageView imageView, String url) {
            Picasso.with(NewMessagesActivity.this).load((int) C0644R.drawable.imv_maya).into(imageView);
        }
    }

    class C06414 implements MessageReceivedCallback {
        C06414() {
        }

        public void onMessageReceived(UserMessage userMessage) {
            Message message = new Message(userMessage.getFromUserId(), userMessage.getText());
            message.setSender(userMessage.getFromUserId());
            NewMessagesActivity.this.adapter.addToStart(message, true);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0644R.layout.activity_messages);
        this.senderId = getIntent().getStringExtra("senderId");
        this.receiverId = getIntent().getStringExtra("receiverId");
        this.authToken = getIntent().getStringExtra("authToken");
        this.apiClient = new ApiClient();
        getMessages();
        Toolbar toolbar = (Toolbar) findViewById(C0644R.id.toolbar);
        this.input = (MessageInput) findViewById(C0644R.id.input);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeState();
        initializeAdapeter();
        initializeWebSockets();
        initializeInput();
    }

    private void getMessages() {
        ((MessagesApi) this.apiClient.createService(MessagesApi.class)).getMessages_0(this.authToken, this.senderId, this.receiverId).enqueue(new C06371());
    }

    private void initializeInput() {
        final Gson gson = new GsonBuilder().create();
        this.input.setInputListener(new InputListener() {
            public boolean onSubmit(CharSequence input) {
                NewMessagesActivity.this.adapter.addToStart(new Message(NewMessagesActivity.this.senderId, input.toString()), true);
                UserMessage userMessage = new UserMessage();
                userMessage.setFromUserId(NewMessagesActivity.this.senderId);
                userMessage.setToUserId(NewMessagesActivity.this.receiverId);
                userMessage.setCreatedOn(Calendar.getInstance().getTimeInMillis());
                userMessage.setUpdatedOn(Calendar.getInstance().getTimeInMillis());
                userMessage.setText(input.toString());
                final String mayaRequestJson = gson.toJson(userMessage);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            NewMessagesActivity.this.messagesWebSocketConnector.getmWebSocketClient().send(mayaRequestJson);
                        } catch (Exception var2) {
                            var2.printStackTrace();
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
        ImageLoader imageLoader = new C06403();
        this.messagesList = (MessagesList) findViewById(C0644R.id.messagesList);
        HoldersConfig holdersConfig = new HoldersConfig();
        holdersConfig.setIncoming(CustomIncomingMessageViewHolder.class, C0644R.layout.item_custom_holder_incoming_message);
        holdersConfig.setOutcoming(CustomOutcomingMessageViewHolder.class, C0644R.layout.item_custom_holder_outcoming_message);
        this.adapter = new MessagesListAdapter(this.senderId, holdersConfig, imageLoader);
        this.messagesList.setAdapter(this.adapter);
    }

    private void initializeWebSockets() {
        this.messagesWebSocketConnector = new MessagesWebSocketConnector(this.senderId);
        this.messagesWebSocketConnector.connect(new C06414());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
