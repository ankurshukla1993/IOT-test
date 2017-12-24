package com.biz.health.cooey_app.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.ExpandableListAdapter;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.careplan.Intervention;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class InterventionFragment extends Fragment {
    private Context context;
    ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    public List<Intervention> interventionList = new ArrayList();
    private HashMap<String, List<String>> listDataChild = new HashMap();
    private List<String> listDataHeader = new ArrayList();

    public class DefaultHashMap<K, V> extends HashMap<K, V> {
        protected V defaultValue;

        public DefaultHashMap(V defaultValue) {
            this.defaultValue = defaultValue;
        }

        public V get(Object k) {
            return containsKey(k) ? super.get(k) : this.defaultValue;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HashSet<String> d = new HashSet();
        if (this.interventionList.size() > 0) {
            for (int i = 0; i < this.interventionList.size(); i++) {
                Intervention intervention = (Intervention) this.interventionList.get(i);
                String type = intervention.getType();
                List<String> temp = (List) this.listDataChild.get(intervention.getType());
                if (temp == null) {
                    temp = new ArrayList();
                }
                temp.add(intervention.getName());
                d.add(intervention.getType());
                this.listDataChild.put(intervention.getType(), temp);
            }
            this.listDataHeader.addAll(d);
        } else {
            String[] headers = new String[]{CTConstants.TODO_TYPE, CTConstants.ACTIVITY_TYPE, CTConstants.DIET_TYPE, CTConstants.VITAL_TYPE};
            this.listDataChild.put(CTConstants.TODO_TYPE, Arrays.asList(new String[0]));
            this.listDataChild.put(CTConstants.ACTIVITY_TYPE, Arrays.asList(new String[0]));
            this.listDataChild.put(CTConstants.DIET_TYPE, Arrays.asList(new String[0]));
            this.listDataChild.put(CTConstants.VITAL_TYPE, Arrays.asList(new String[0]));
            this.listDataHeader.addAll(Arrays.asList(headers));
        }
        this.expandableListAdapter = new ExpandableListAdapter(this.context, this.listDataHeader, this.listDataChild, "INTERVENTION");
        View view = inflater.inflate(C0644R.layout.fragment_intervention, container, false);
        this.expandableListView = (ExpandableListView) view.findViewById(C0644R.id.exp_lv);
        this.expandableListView.setAdapter(this.expandableListAdapter);
        this.expandableListAdapter.notifyDataSetChanged();
        return view;
    }
}
