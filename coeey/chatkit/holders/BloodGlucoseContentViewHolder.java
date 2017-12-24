package chatkit.holders;

import android.view.View;
import android.widget.TextView;
import chatkit.Card;
import chatkit.Content;
import com.cooey.maya.R;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.Calendar;
import java.util.Map;

public class BloodGlucoseContentViewHolder extends ContentViewHolder {
    public static final String GLUCOSE = "GLUCOSE";

    class C05221 extends TypeToken<Map<String, String>> {
        C05221() {
        }
    }

    public BloodGlucoseContentViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Content content) {
        String parameters = content.getParameters();
        Gson gson = new GsonBuilder().create();
        Card card = (Card) gson.fromJson(parameters, Card.class);
        Map<String, String> parameterMap = (Map) gson.fromJson(card.getParameters(), new C05221().getType());
        String time = (String) parameterMap.get("TIME");
        TextView glucoseValue = (TextView) this.itemView.findViewById(R.id.glucose_value);
        TextView dateValue = (TextView) this.itemView.findViewById(R.id.date_text);
        glucoseValue.setText((String) parameterMap.get(GLUCOSE));
        dateValue.setText(TimeAgo.using(Calendar.getInstance().getTimeInMillis()));
    }
}
