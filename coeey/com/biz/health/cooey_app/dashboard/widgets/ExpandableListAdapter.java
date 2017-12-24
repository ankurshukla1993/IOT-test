package com.biz.health.cooey_app.dashboard.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String fragmentType;
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData, String fragmentType) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        this.fragmentType = fragmentType;
    }

    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    public int getChildrenCount(int groupPosition) {
        if (this.listDataChild == null || this.listDataHeader == null || this.listDataChild.size() <= 0 || this.listDataHeader.size() <= 0) {
            return 0;
        }
        return ((List) this.listDataChild.get(this.listDataHeader.get(groupPosition))).size();
    }

    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    public Object getChild(int groupPosition, int childPosititon) {
        return ((List) this.listDataChild.get(this.listDataHeader.get(groupPosition))).get(childPosititon);
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public long getChildId(int groupPosition, int childPosititon) {
        return (long) childPosititon;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0644R.layout.list_group, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(C0644R.id.lblListHeader);
        lblListHeader.setTypeface(null, 1);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
            if (this.fragmentType == "ABOUT") {
                convertView = infalInflater.inflate(C0644R.layout.list_about, null);
            } else {
                convertView = infalInflater.inflate(C0644R.layout.list_intervention, null);
            }
        }
        ((TextView) convertView.findViewById(C0644R.id.lblListItem)).setText(childText);
        return convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosititon) {
        return false;
    }
}
