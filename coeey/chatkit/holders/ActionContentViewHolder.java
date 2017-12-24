package chatkit.holders;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import chatkit.Action;
import chatkit.Content;
import chatkit.events.ActionSelectedEvent;
import com.cooey.maya.R;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.EventBus;

public class ActionContentViewHolder extends ContentViewHolder {
    public void onBind(Content content) {
        Button actionButton = (Button) this.itemView.findViewById(R.id.action_button);
        final Action action = (Action) new GsonBuilder().create().fromJson(content.getParameters(), Action.class);
        actionButton.setText(action.getName());
        actionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new ActionSelectedEvent(action));
            }
        });
    }

    public ActionContentViewHolder(View itemView) {
        super(itemView);
    }
}
