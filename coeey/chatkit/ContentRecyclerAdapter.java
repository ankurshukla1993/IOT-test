package chatkit;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import chatkit.holders.ActionContentViewHolder;
import chatkit.holders.BloodGlucoseContentViewHolder;
import chatkit.holders.BloodPressureCardViewHolder;
import chatkit.holders.ContentViewHolder;
import chatkit.holders.OptionContentViewHolder;
import chatkit.holders.WeightContentViewHolder;
import com.cooey.maya.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class ContentRecyclerAdapter extends Adapter<ContentViewHolder> {
    private static final int VIEW_TYPE_ACTION = 1;
    private static final int VIEW_TYPE_BLOOD_GLUCOSE_CARD = 3;
    private static final int VIEW_TYPE_BLOOD_PRESSURE_CARD = 4;
    private static final int VIEW_TYPE_OPTION = 2;
    private static final int VIEW_TYPE_WEIGHT_CARD = 5;
    private final List<Content> contentList;
    Gson gson = new GsonBuilder().create();

    public ContentRecyclerAdapter(List<Content> contentList) {
        this.contentList = contentList;
    }

    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ActionContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_action_content, parent, false));
            case 2:
                return new OptionContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_option_content, parent, false));
            case 3:
                return new BloodGlucoseContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blood_glucose_card, parent, false));
            case 4:
                return new BloodPressureCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blood_pressure_card, parent, false));
            case 5:
                return new WeightContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weight_card, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ContentViewHolder holder, int position) {
        holder.onBind((Content) this.contentList.get(position));
    }

    public int getItemViewType(int position) {
        Content content = (Content) this.contentList.get(position);
        switch (content.getType()) {
            case ACTION:
                return 1;
            case OPTION:
                return 2;
            case CARD:
                Card card = (Card) this.gson.fromJson(content.getParameters(), Card.class);
                if (card.getType() == CardType.BLOOD_GLUCOSE) {
                    return 3;
                }
                if (card.getType() == CardType.BLOOD_PRESSURE) {
                    return 4;
                }
                if (card.getType() == CardType.WEIGHT) {
                    return 5;
                }
                break;
        }
        return 0;
    }

    public int getItemCount() {
        return this.contentList.size();
    }
}
