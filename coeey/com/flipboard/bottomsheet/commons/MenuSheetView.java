package com.flipboard.bottomsheet.commons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.flipboard.bottomsheet.commons.Util.ShadowOutline;
import flipboard.bottomsheet.commons.R;
import java.util.ArrayList;

@SuppressLint({"ViewConstructor"})
public class MenuSheetView extends FrameLayout {
    public static final int DEFAULT_LAYOUT_GRID_ITEM = R.layout.sheet_grid_item;
    public static final int DEFAULT_LAYOUT_LIST_ITEM = R.layout.sheet_list_item;
    private AbsListView absListView;
    private Adapter adapter;
    private int columnWidthDp;
    private int gridItemLayoutRes;
    private ArrayList<SheetMenuItem> items;
    private int listItemLayoutRes;
    private Menu menu;
    private final MenuType menuType;
    protected final int originalListPaddingTop;
    private final TextView titleView;

    private class Adapter extends BaseAdapter {
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private final LayoutInflater inflater;

        class NormalViewHolder {
            final ImageView icon;
            final TextView label;

            NormalViewHolder(View root) {
                this.icon = (ImageView) root.findViewById(R.id.icon);
                this.label = (TextView) root.findViewById(R.id.label);
            }

            public void bindView(SheetMenuItem item) {
                this.icon.setImageDrawable(item.getMenuItem().getIcon());
                this.label.setText(item.getMenuItem().getTitle());
            }
        }

        public Adapter() {
            this.inflater = LayoutInflater.from(MenuSheetView.this.getContext());
        }

        public int getCount() {
            return MenuSheetView.this.items.size();
        }

        public SheetMenuItem getItem(int position) {
            return (SheetMenuItem) MenuSheetView.this.items.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int getItemViewType(int position) {
            SheetMenuItem item = getItem(position);
            if (item.isSeparator()) {
                return 2;
            }
            if (item.getMenuItem().hasSubMenu()) {
                return 1;
            }
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            SheetMenuItem item = getItem(position);
            switch (getItemViewType(position)) {
                case 0:
                    NormalViewHolder holder;
                    if (convertView == null) {
                        convertView = this.inflater.inflate(MenuSheetView.this.menuType == MenuType.GRID ? MenuSheetView.this.gridItemLayoutRes : MenuSheetView.this.listItemLayoutRes, parent, false);
                        holder = new NormalViewHolder(convertView);
                        convertView.setTag(holder);
                    } else {
                        holder = (NormalViewHolder) convertView.getTag();
                    }
                    holder.bindView(item);
                    return convertView;
                case 1:
                    if (convertView == null) {
                        convertView = this.inflater.inflate(R.layout.sheet_list_item_subheader, parent, false);
                    }
                    ((TextView) convertView).setText(item.getMenuItem().getTitle());
                    return convertView;
                case 2:
                    if (convertView == null) {
                        return this.inflater.inflate(R.layout.sheet_list_item_separator, parent, false);
                    }
                    return convertView;
                default:
                    return convertView;
            }
        }

        public boolean areAllItemsEnabled() {
            return false;
        }

        public boolean isEnabled(int position) {
            return getItem(position).isEnabled();
        }
    }

    public enum MenuType {
        LIST,
        GRID
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    private static class SheetMenuItem {
        private static final SheetMenuItem SEPARATOR = new SheetMenuItem(null);
        private final MenuItem menuItem;

        private SheetMenuItem(MenuItem item) {
            this.menuItem = item;
        }

        public static SheetMenuItem of(MenuItem item) {
            return new SheetMenuItem(item);
        }

        public boolean isSeparator() {
            return this == SEPARATOR;
        }

        public MenuItem getMenuItem() {
            return this.menuItem;
        }

        public boolean isEnabled() {
            return (this.menuItem == null || this.menuItem.hasSubMenu() || !this.menuItem.isEnabled()) ? false : true;
        }
    }

    public MenuSheetView(Context context, MenuType menuType, @StringRes int titleRes, OnMenuItemClickListener listener) {
        this(context, menuType, context.getString(titleRes), listener);
    }

    public MenuSheetView(Context context, MenuType menuType, @Nullable CharSequence title, final OnMenuItemClickListener listener) {
        super(context);
        this.items = new ArrayList();
        this.columnWidthDp = 100;
        this.listItemLayoutRes = DEFAULT_LAYOUT_LIST_ITEM;
        this.gridItemLayoutRes = DEFAULT_LAYOUT_GRID_ITEM;
        this.menu = new MenuBuilder(context);
        this.menuType = menuType;
        inflate(context, menuType == MenuType.GRID ? R.layout.grid_sheet_view : R.layout.list_sheet_view, this);
        this.absListView = (AbsListView) findViewById(menuType == MenuType.GRID ? R.id.grid : R.id.list);
        if (listener != null) {
            this.absListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    listener.onMenuItemClick(MenuSheetView.this.adapter.getItem(position).getMenuItem());
                }
            });
        }
        this.titleView = (TextView) findViewById(R.id.title);
        this.originalListPaddingTop = this.absListView.getPaddingTop();
        setTitle(title);
        ViewCompat.setElevation(this, (float) Util.dp2px(getContext(), 16.0f));
    }

    public void inflateMenu(@MenuRes int menuRes) {
        if (menuRes != -1) {
            new SupportMenuInflater(getContext()).inflate(menuRes, this.menu);
        }
        prepareMenuItems();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adapter = new Adapter();
        this.absListView.setAdapter(this.adapter);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.menuType == MenuType.GRID) {
            ((GridView) this.absListView).setNumColumns((int) (((float) MeasureSpec.getSize(widthMeasureSpec)) / (((float) this.columnWidthDp) * getResources().getDisplayMetrics().density)));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ShadowOutline(w, h));
        }
    }

    private void prepareMenuItems() {
        this.items.clear();
        int currentGroupId = 0;
        int i = 0;
        while (i < this.menu.size()) {
            MenuItem item = this.menu.getItem(i);
            if (item.isVisible()) {
                if (item.hasSubMenu()) {
                    SubMenu subMenu = item.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (this.menuType == MenuType.LIST) {
                            this.items.add(SheetMenuItem.SEPARATOR);
                            if (!TextUtils.isEmpty(item.getTitle())) {
                                this.items.add(SheetMenuItem.of(item));
                            }
                        }
                        int size = subMenu.size();
                        for (int subI = 0; subI < size; subI++) {
                            MenuItem subMenuItem = subMenu.getItem(subI);
                            if (subMenuItem.isVisible()) {
                                this.items.add(SheetMenuItem.of(subMenuItem));
                            }
                        }
                        if (this.menuType == MenuType.LIST && i != this.menu.size() - 1) {
                            this.items.add(SheetMenuItem.SEPARATOR);
                        }
                    }
                } else {
                    int groupId = item.getGroupId();
                    if (groupId != currentGroupId && this.menuType == MenuType.LIST) {
                        this.items.add(SheetMenuItem.SEPARATOR);
                    }
                    this.items.add(SheetMenuItem.of(item));
                    currentGroupId = groupId;
                }
            }
            i++;
        }
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void updateMenu() {
        prepareMenuItems();
    }

    public MenuType getMenuType() {
        return this.menuType;
    }

    public void setTitle(@StringRes int resId) {
        setTitle(getResources().getText(resId));
    }

    public void setTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)) {
            this.titleView.setVisibility(8);
            this.absListView.setPadding(this.absListView.getPaddingLeft(), this.originalListPaddingTop + Util.dp2px(getContext(), 8.0f), this.absListView.getPaddingRight(), this.absListView.getPaddingBottom());
            return;
        }
        this.titleView.setText(title);
    }

    public void setColumnWidthDp(int columnWidthDp) {
        this.columnWidthDp = columnWidthDp;
    }

    public void setListItemLayoutRes(@LayoutRes int listItemLayoutRes) {
        this.listItemLayoutRes = listItemLayoutRes;
    }

    public void setGridItemLayoutRes(@LayoutRes int gridItemLayoutRes) {
        this.gridItemLayoutRes = gridItemLayoutRes;
    }

    public CharSequence getTitle() {
        return this.titleView.getText();
    }
}
