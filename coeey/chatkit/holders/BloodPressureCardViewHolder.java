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

public class BloodPressureCardViewHolder extends ContentViewHolder {
    public static final String DIASTOLIC = "DIASTOLIC";
    public static final String SYSTOLIC = "SYSTOLIC";

    class C05231 extends TypeToken<Map<String, String>> {
        C05231() {
        }
    }

    public BloodPressureCardViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Content content) {
        String parameters = content.getParameters();
        Gson gson = new GsonBuilder().create();
        Card card = (Card) gson.fromJson(parameters, Card.class);
        Map<String, String> parameterMap = (Map) gson.fromJson(card.getParameters(), new C05231().getType());
        String diastoilc = (String) parameterMap.get(DIASTOLIC);
        String time = (String) parameterMap.get("TIME");
        TextView systolicValue = (TextView) this.itemView.findViewById(R.id.systolic_value);
        TextView diastoicValue = (TextView) this.itemView.findViewById(R.id.diastolic_value);
        TextView dateValue = (TextView) this.itemView.findViewById(R.id.date_text);
        systolicValue.setText((String) parameterMap.get(SYSTOLIC));
        diastoicValue.setText(diastoilc);
        dateValue.setText(TimeAgo.using(Calendar.getInstance().getTimeInMillis()));
    }
}
