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

public class WeightContentViewHolder extends ContentViewHolder {

    class C05251 extends TypeToken<Map<String, String>> {
        C05251() {
        }
    }

    public WeightContentViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Content content) {
        String parameters = content.getParameters();
        Gson gson = new GsonBuilder().create();
        Card card = (Card) gson.fromJson(parameters, Card.class);
        Map<String, String> parameterMap = (Map) gson.fromJson(card.getParameters(), new C05251().getType());
        String time = (String) parameterMap.get("TIME");
        TextView weightValue = (TextView) this.itemView.findViewById(R.id.weight_value);
        TextView dateValue = (TextView) this.itemView.findViewById(R.id.date_text);
        weightValue.setText((String) parameterMap.get("WEIGHT"));
        dateValue.setText(TimeAgo.using(Calendar.getInstance().getTimeInMillis()));
    }
}
