package chatkit.holders;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import chatkit.Content;
import chatkit.events.OptionSelectedEvent;
import com.cooey.maya.R;
import org.greenrobot.eventbus.EventBus;

public class OptionContentViewHolder extends ContentViewHolder {
    public OptionContentViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(final Content content) {
        Button button = (Button) this.itemView.findViewById(R.id.option_button);
        button.setText(content.getParameters());
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new OptionSelectedEvent(content));
            }
        });
    }
}
