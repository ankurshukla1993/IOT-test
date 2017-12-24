package com.arlib.floatingsearchview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter.Listener;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter.OnBindSuggestionCallback;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.arlib.floatingsearchview.util.adapter.GestureDetectorListenerAdapter;
import com.arlib.floatingsearchview.util.adapter.OnItemTouchListenerAdapter;
import com.arlib.floatingsearchview.util.adapter.TextWatcherAdapter;
import com.arlib.floatingsearchview.util.view.MenuView;
import com.arlib.floatingsearchview.util.view.MenuView.OnVisibleWidthChangedListener;
import com.arlib.floatingsearchview.util.view.SearchInputView;
import com.arlib.floatingsearchview.util.view.SearchInputView.OnKeyboardDismissedListener;
import com.arlib.floatingsearchview.util.view.SearchInputView.OnKeyboardSearchKeyClickListener;
import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class FloatingSearchView extends FrameLayout {
    private static final boolean ATTRS_DISMISS_FOCUS_ON_ITEM_SELECTION_DEFAULT = false;
    private static final boolean ATTRS_DISMISS_ON_KEYBOARD_DISMISS_DEFAULT = false;
    private static final boolean ATTRS_DISMISS_ON_OUTSIDE_TOUCH_DEFAULT = true;
    private static final int ATTRS_QUERY_TEXT_SIZE_SP_DEFAULT = 18;
    private static final int ATTRS_SEARCH_BAR_LEFT_ACTION_MODE_DEFAULT = 4;
    private static final int ATTRS_SEARCH_BAR_MARGIN_DEFAULT = 0;
    private static final boolean ATTRS_SEARCH_BAR_SHOW_SEARCH_KEY_DEFAULT = true;
    private static final boolean ATTRS_SHOW_DIM_BACKGROUND_DEFAULT = true;
    private static final boolean ATTRS_SHOW_MOVE_UP_SUGGESTION_DEFAULT = false;
    private static final int ATTRS_SUGGESTION_ANIM_DURATION_DEFAULT = 250;
    private static final int ATTRS_SUGGESTION_TEXT_SIZE_SP_DEFAULT = 18;
    private static final int BACKGROUND_DRAWABLE_ALPHA_SEARCH_FOCUSED = 150;
    private static final int BACKGROUND_DRAWABLE_ALPHA_SEARCH_NOT_FOCUSED = 0;
    private static final int BACKGROUND_FADE_ANIM_DURATION = 250;
    private static final int CARD_VIEW_CORNERS_AND_TOP_BOTTOM_SHADOW_HEIGHT = 5;
    private static final int CARD_VIEW_CORNERS_HEIGHT = 2;
    private static final int CARD_VIEW_TOP_BOTTOM_SHADOW_HEIGHT = 3;
    private static final long CLEAR_BTN_FADE_ANIM_DURATION = 500;
    private static final int CLEAR_BTN_WIDTH_DP = 48;
    private static final int LEFT_ACTION_MODE_NOT_SET = -1;
    public static final int LEFT_ACTION_MODE_NO_LEFT_ACTION = 4;
    public static final int LEFT_ACTION_MODE_SHOW_HAMBURGER = 1;
    public static final int LEFT_ACTION_MODE_SHOW_HOME = 3;
    public static final int LEFT_ACTION_MODE_SHOW_SEARCH = 2;
    private static final int LEFT_MENU_WIDTH_AND_MARGIN_START_DP = 52;
    private static final float MENU_BUTTON_PROGRESS_ARROW = 1.0f;
    private static final float MENU_BUTTON_PROGRESS_HAMBURGER = 0.0f;
    private static final int MENU_ICON_ANIM_DURATION = 250;
    private static final Interpolator SUGGEST_ITEM_ADD_ANIM_INTERPOLATOR = new LinearInterpolator();
    private static final String TAG = FloatingSearchView.class.getSimpleName();
    private int mActionMenuItemColor;
    private OnMenuItemClickListener mActionMenuItemListener;
    private int mBackgroundColor;
    private Drawable mBackgroundDrawable;
    private int mClearBtnColor;
    private ImageView mClearButton;
    private boolean mCloseSearchOnSofteKeyboardDismiss;
    private boolean mDimBackground;
    private boolean mDismissFocusOnItemSelection;
    private boolean mDismissOnOutsideTouch;
    private View mDivider;
    private int mDividerColor;
    private android.support.v4.widget.DrawerLayout.DrawerListener mDrawerListener;
    private OnFocusChangeListener mFocusChangeListener;
    private Activity mHostActivity;
    private Drawable mIconBackArrow;
    private Drawable mIconClear;
    private Drawable mIconSearch;
    private boolean mIsFocused;
    private boolean mIsInitialLayout;
    private boolean mIsSuggestionsSectionHeightSet;
    private boolean mIsTitleSet;
    private ImageView mLeftAction;
    private int mLeftActionIconColor;
    int mLeftActionMode;
    private OnClickListener mLeftMenuClickListener;
    private View mMainLayout;
    private DrawerArrowDrawable mMenuBtnDrawable;
    private int mMenuId;
    private boolean mMenuOpen;
    private MenuView mMenuView;
    private String mOldQuery;
    private OnBindSuggestionCallback mOnBindSuggestionCallback;
    private OnClearSearchActionListener mOnClearSearchActionListener;
    private OnHomeActionClickListener mOnHomeActionClickListener;
    private OnLeftMenuClickListener mOnMenuClickListener;
    private OnSuggestionsListHeightChanged mOnSuggestionsListHeightChanged;
    private int mOverflowIconColor;
    private OnQueryChangeListener mQueryListener;
    private CardView mQuerySection;
    private int mQueryTextSize;
    private String mSearchHint;
    private SearchInputView mSearchInput;
    private int mSearchInputHintColor;
    private View mSearchInputParent;
    private int mSearchInputTextColor;
    private OnSearchListener mSearchListener;
    private ProgressBar mSearchProgress;
    private boolean mShowMoveUpSuggestion;
    private boolean mShowSearchKey;
    private boolean mSkipQueryFocusChangeEvent;
    private boolean mSkipTextChangeEvent;
    private View mSuggestionListContainer;
    private int mSuggestionRightIconColor;
    private OnSuggestionSecHeightSetListener mSuggestionSecHeightListener;
    private long mSuggestionSectionAnimDuration;
    private int mSuggestionTextColor;
    private SearchSuggestionsAdapter mSuggestionsAdapter;
    private RecyclerView mSuggestionsList;
    private RelativeLayout mSuggestionsSection;
    private int mSuggestionsTextSizePx;
    private String mTitleText;

    private interface OnSuggestionSecHeightSetListener {
        void onSuggestionSecHeightSet();
    }

    class C06052 implements OnGlobalLayoutListener {
        C06052() {
        }

        public void onGlobalLayout() {
            Util.removeGlobalLayoutObserver(FloatingSearchView.this.mQuerySection, this);
            FloatingSearchView.this.inflateOverflowMenu(FloatingSearchView.this.mMenuId);
        }
    }

    class C06063 implements Callback {
        C06063() {
        }

        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            if (FloatingSearchView.this.mActionMenuItemListener != null) {
                FloatingSearchView.this.mActionMenuItemListener.onActionMenuItemSelected(item);
            }
            return false;
        }

        public void onMenuModeChange(MenuBuilder menu) {
        }
    }

    class C06074 implements OnVisibleWidthChangedListener {
        C06074() {
        }

        public void onItemsMenuVisibleWidthChanged(int newVisibleWidth) {
            FloatingSearchView.this.handleOnVisibleMenuItemsWidthChanged(newVisibleWidth);
        }
    }

    class C06085 implements OnClickListener {
        C06085() {
        }

        public void onClick(View v) {
            FloatingSearchView.this.mSearchInput.setText("");
            if (FloatingSearchView.this.mOnClearSearchActionListener != null) {
                FloatingSearchView.this.mOnClearSearchActionListener.onClearSearchClicked();
            }
        }
    }

    class C06096 extends TextWatcherAdapter {
        C06096() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (FloatingSearchView.this.mSkipTextChangeEvent || !FloatingSearchView.this.mIsFocused) {
                FloatingSearchView.this.mSkipTextChangeEvent = false;
            } else {
                if (FloatingSearchView.this.mSearchInput.getText().toString().length() != 0 && FloatingSearchView.this.mClearButton.getVisibility() == 4) {
                    FloatingSearchView.this.mClearButton.setAlpha(0.0f);
                    FloatingSearchView.this.mClearButton.setVisibility(0);
                    ViewCompat.animate(FloatingSearchView.this.mClearButton).alpha(1.0f).setDuration(FloatingSearchView.CLEAR_BTN_FADE_ANIM_DURATION).start();
                } else if (FloatingSearchView.this.mSearchInput.getText().toString().length() == 0) {
                    FloatingSearchView.this.mClearButton.setVisibility(4);
                }
                if (!(FloatingSearchView.this.mQueryListener == null || !FloatingSearchView.this.mIsFocused || FloatingSearchView.this.mOldQuery.equals(FloatingSearchView.this.mSearchInput.getText().toString()))) {
                    FloatingSearchView.this.mQueryListener.onSearchTextChanged(FloatingSearchView.this.mOldQuery, FloatingSearchView.this.mSearchInput.getText().toString());
                }
            }
            FloatingSearchView.this.mOldQuery = FloatingSearchView.this.mSearchInput.getText().toString();
        }
    }

    class C06107 implements android.view.View.OnFocusChangeListener {
        C06107() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (FloatingSearchView.this.mSkipQueryFocusChangeEvent) {
                FloatingSearchView.this.mSkipQueryFocusChangeEvent = false;
            } else if (hasFocus != FloatingSearchView.this.mIsFocused) {
                FloatingSearchView.this.setSearchFocusedInternal(hasFocus);
            }
        }
    }

    class C06118 implements OnKeyboardDismissedListener {
        C06118() {
        }

        public void onKeyboardDismissed() {
            if (FloatingSearchView.this.mCloseSearchOnSofteKeyboardDismiss) {
                FloatingSearchView.this.setSearchFocusedInternal(false);
            }
        }
    }

    class C06129 implements OnKeyboardSearchKeyClickListener {
        C06129() {
        }

        public void onSearchKeyClicked() {
            if (FloatingSearchView.this.mSearchListener != null) {
                FloatingSearchView.this.mSearchListener.onSearchAction(FloatingSearchView.this.getQuery());
            }
            FloatingSearchView.this.mSkipTextChangeEvent = true;
            FloatingSearchView.this.mSkipTextChangeEvent = true;
            if (FloatingSearchView.this.mIsTitleSet) {
                FloatingSearchView.this.setSearchBarTitle(FloatingSearchView.this.getQuery());
            } else {
                FloatingSearchView.this.setSearchText(FloatingSearchView.this.getQuery());
            }
            FloatingSearchView.this.setSearchFocusedInternal(false);
        }
    }

    private class DrawerListener implements android.support.v4.widget.DrawerLayout.DrawerListener {
        private DrawerListener() {
        }

        public void onDrawerSlide(View drawerView, float slideOffset) {
            FloatingSearchView.this.setMenuIconProgress(slideOffset);
        }

        public void onDrawerOpened(View drawerView) {
        }

        public void onDrawerClosed(View drawerView) {
        }

        public void onDrawerStateChanged(int newState) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LeftActionMode {
    }

    public interface OnLeftMenuClickListener {
        void onMenuClosed();

        void onMenuOpened();
    }

    private class NavDrawerLeftMenuClickListener implements OnLeftMenuClickListener {
        DrawerLayout mDrawerLayout;

        public NavDrawerLeftMenuClickListener(DrawerLayout drawerLayout) {
            this.mDrawerLayout = drawerLayout;
        }

        public void onMenuOpened() {
            this.mDrawerLayout.openDrawer((int) GravityCompat.START);
        }

        public void onMenuClosed() {
        }
    }

    public interface OnClearSearchActionListener {
        void onClearSearchClicked();
    }

    public interface OnFocusChangeListener {
        void onFocus();

        void onFocusCleared();
    }

    public interface OnHomeActionClickListener {
        void onHomeClicked();
    }

    public interface OnMenuItemClickListener {
        void onActionMenuItemSelected(MenuItem menuItem);
    }

    public interface OnQueryChangeListener {
        void onSearchTextChanged(String str, String str2);
    }

    public interface OnSearchListener {
        void onSearchAction(String str);

        void onSuggestionClicked(SearchSuggestion searchSuggestion);
    }

    public interface OnSuggestionsListHeightChanged {
        void onSuggestionsListHeightChanged(float f);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C06131();
        private int actionOverflowMenuColor;
        private int backgroundColor;
        private int clearBtnColor;
        private boolean dimBackground;
        private boolean dismissFocusOnSuggestionItemClick;
        private boolean dismissOnOutsideClick;
        private boolean dismissOnSoftKeyboardDismiss;
        private int dividerColor;
        private boolean isFocused;
        private boolean isTitleSet;
        private int leftActionMode;
        private int leftIconColor;
        private int menuId;
        private int menuItemIconColor;
        private String query;
        private int queryTextColor;
        private int queryTextSize;
        private String searchHint;
        private int searchHintTextColor;
        private boolean showMoveSuggestionUpBtn;
        private boolean showSearchKey;
        private int suggestionTextSize;
        private int suggestionUpBtnColor;
        private List<? extends SearchSuggestion> suggestions;
        private long suggestionsSectionAnimSuration;
        private int suggestionsTextColor;

        static class C06131 implements Creator<SavedState> {
            C06131() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        SavedState(Parcelable superState) {
            super(superState);
            this.suggestions = new ArrayList();
        }

        private SavedState(Parcel in) {
            boolean z;
            boolean z2 = true;
            super(in);
            this.suggestions = new ArrayList();
            in.readList(this.suggestions, getClass().getClassLoader());
            this.isFocused = in.readInt() != 0;
            this.query = in.readString();
            this.queryTextSize = in.readInt();
            this.suggestionTextSize = in.readInt();
            this.searchHint = in.readString();
            if (in.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.dismissOnOutsideClick = z;
            if (in.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.showMoveSuggestionUpBtn = z;
            if (in.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.showSearchKey = z;
            if (in.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.isTitleSet = z;
            this.backgroundColor = in.readInt();
            this.suggestionsTextColor = in.readInt();
            this.queryTextColor = in.readInt();
            this.searchHintTextColor = in.readInt();
            this.actionOverflowMenuColor = in.readInt();
            this.menuItemIconColor = in.readInt();
            this.leftIconColor = in.readInt();
            this.clearBtnColor = in.readInt();
            this.suggestionUpBtnColor = in.readInt();
            this.dividerColor = in.readInt();
            this.menuId = in.readInt();
            this.leftActionMode = in.readInt();
            this.dimBackground = in.readInt() != 0;
            this.suggestionsSectionAnimSuration = in.readLong();
            if (in.readInt() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.dismissOnSoftKeyboardDismiss = z;
            if (in.readInt() == 0) {
                z2 = false;
            }
            this.dismissFocusOnSuggestionItemClick = z2;
        }

        public void writeToParcel(Parcel out, int flags) {
            int i;
            int i2 = 1;
            super.writeToParcel(out, flags);
            out.writeList(this.suggestions);
            out.writeInt(this.isFocused ? 1 : 0);
            out.writeString(this.query);
            out.writeInt(this.queryTextSize);
            out.writeInt(this.suggestionTextSize);
            out.writeString(this.searchHint);
            if (this.dismissOnOutsideClick) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            if (this.showMoveSuggestionUpBtn) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            if (this.showSearchKey) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            if (this.isTitleSet) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            out.writeInt(this.backgroundColor);
            out.writeInt(this.suggestionsTextColor);
            out.writeInt(this.queryTextColor);
            out.writeInt(this.searchHintTextColor);
            out.writeInt(this.actionOverflowMenuColor);
            out.writeInt(this.menuItemIconColor);
            out.writeInt(this.leftIconColor);
            out.writeInt(this.clearBtnColor);
            out.writeInt(this.suggestionUpBtnColor);
            out.writeInt(this.dividerColor);
            out.writeInt(this.menuId);
            out.writeInt(this.leftActionMode);
            out.writeInt(this.dimBackground ? 1 : 0);
            out.writeLong(this.suggestionsSectionAnimSuration);
            if (this.dismissOnSoftKeyboardDismiss) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeInt(i);
            if (!this.dismissFocusOnSuggestionItemClick) {
                i2 = 0;
            }
            out.writeInt(i2);
        }
    }

    public FloatingSearchView(Context context) {
        this(context, null);
    }

    public FloatingSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDismissOnOutsideTouch = true;
        this.mDismissFocusOnItemSelection = false;
        this.mSearchInputTextColor = -1;
        this.mSearchInputHintColor = -1;
        this.mOldQuery = "";
        this.mLeftActionMode = -1;
        this.mMenuOpen = false;
        this.mMenuId = -1;
        this.mSuggestionTextColor = -1;
        this.mIsInitialLayout = true;
        this.mShowMoveUpSuggestion = false;
        this.mDrawerListener = new DrawerListener();
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.mHostActivity = Util.getHostActivity(getContext());
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        this.mMainLayout = inflate(getContext(), C0614R.layout.floating_search_layout, this);
        this.mBackgroundDrawable = new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
        this.mQuerySection = (CardView) findViewById(C0614R.id.search_query_section);
        this.mClearButton = (ImageView) findViewById(C0614R.id.clear_btn);
        this.mSearchInput = (SearchInputView) findViewById(C0614R.id.search_bar_text);
        this.mSearchInputParent = findViewById(C0614R.id.search_input_parent);
        this.mLeftAction = (ImageView) findViewById(C0614R.id.left_action);
        this.mSearchProgress = (ProgressBar) findViewById(C0614R.id.search_bar_search_progress);
        initDrawables();
        this.mClearButton.setImageDrawable(this.mIconClear);
        this.mMenuView = (MenuView) findViewById(C0614R.id.menu_view);
        this.mDivider = findViewById(C0614R.id.divider);
        this.mSuggestionsSection = (RelativeLayout) findViewById(C0614R.id.search_suggestions_section);
        this.mSuggestionListContainer = findViewById(C0614R.id.suggestions_list_container);
        this.mSuggestionsList = (RecyclerView) findViewById(C0614R.id.suggestions_list);
        setupViews(attrs);
    }

    private void initDrawables() {
        this.mMenuBtnDrawable = new DrawerArrowDrawable(getContext());
        this.mIconClear = Util.getWrappedDrawable(getContext(), C0614R.drawable.ic_clear_black_24dp);
        this.mIconBackArrow = Util.getWrappedDrawable(getContext(), C0614R.drawable.ic_arrow_back_black_24dp);
        this.mIconSearch = Util.getWrappedDrawable(getContext(), C0614R.drawable.ic_search_black_24dp);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mIsInitialLayout) {
            final int finalHeight = this.mSuggestionsSection.getHeight() + (Util.dpToPx(5) * 3);
            this.mSuggestionsSection.getLayoutParams().height = finalHeight;
            this.mSuggestionsSection.requestLayout();
            this.mSuggestionListContainer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (FloatingSearchView.this.mSuggestionsSection.getHeight() == finalHeight) {
                        Util.removeGlobalLayoutObserver(FloatingSearchView.this.mSuggestionListContainer, this);
                        FloatingSearchView.this.mIsSuggestionsSectionHeightSet = true;
                        FloatingSearchView.this.moveSuggestListToInitialPos();
                        if (FloatingSearchView.this.mSuggestionSecHeightListener != null) {
                            FloatingSearchView.this.mSuggestionSecHeightListener.onSuggestionSecHeightSet();
                            FloatingSearchView.this.mSuggestionSecHeightListener = null;
                        }
                    }
                }
            });
            this.mIsInitialLayout = false;
            refreshDimBackground();
            if (isInEditMode()) {
                inflateOverflowMenu(this.mMenuId);
            }
        }
    }

    private void setupViews(AttributeSet attrs) {
        this.mSuggestionsSection.setEnabled(false);
        if (attrs != null) {
            applyXmlAttributes(attrs);
        }
        if (VERSION.SDK_INT >= 16) {
            setBackground(this.mBackgroundDrawable);
        } else {
            setBackgroundDrawable(this.mBackgroundDrawable);
        }
        setupQueryBar();
        if (!isInEditMode()) {
            setupSuggestionSection();
        }
    }

    private void applyXmlAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0614R.styleable.FloatingSearchView);
        try {
            int searchBarWidth = a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchBarWidth, -1);
            this.mQuerySection.getLayoutParams().width = searchBarWidth;
            this.mDivider.getLayoutParams().width = searchBarWidth;
            this.mSuggestionListContainer.getLayoutParams().width = searchBarWidth;
            int searchBarLeftMargin = a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchBarMarginLeft, 0);
            int searchBarTopMargin = a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchBarMarginTop, 0);
            int searchBarRightMargin = a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchBarMarginRight, 0);
            LayoutParams querySectionLP = (LayoutParams) this.mQuerySection.getLayoutParams();
            LayoutParams dividerLP = (LayoutParams) this.mDivider.getLayoutParams();
            LinearLayout.LayoutParams suggestListSectionLP = (LinearLayout.LayoutParams) this.mSuggestionsSection.getLayoutParams();
            int cardPadding = Util.dpToPx(3);
            querySectionLP.setMargins(searchBarLeftMargin, searchBarTopMargin, searchBarRightMargin, 0);
            dividerLP.setMargins(searchBarLeftMargin + cardPadding, 0, searchBarRightMargin + cardPadding, ((MarginLayoutParams) this.mDivider.getLayoutParams()).bottomMargin);
            suggestListSectionLP.setMargins(searchBarLeftMargin, 0, searchBarRightMargin, 0);
            this.mQuerySection.setLayoutParams(querySectionLP);
            this.mDivider.setLayoutParams(dividerLP);
            this.mSuggestionsSection.setLayoutParams(suggestListSectionLP);
            setQueryTextSize(a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchInputTextSize, 18));
            setSearchHint(a.getString(C0614R.styleable.FloatingSearchView_floatingSearch_searchHint));
            setShowSearchKey(a.getBoolean(C0614R.styleable.FloatingSearchView_floatingSearch_showSearchKey, true));
            setCloseSearchOnKeyboardDismiss(a.getBoolean(C0614R.styleable.f19xd210cbe7, false));
            setDismissOnOutsideClick(a.getBoolean(C0614R.styleable.FloatingSearchView_floatingSearch_dismissOnOutsideTouch, true));
            setDismissFocusOnItemSelection(a.getBoolean(C0614R.styleable.FloatingSearchView_floatingSearch_dismissFocusOnItemSelection, false));
            setSuggestionItemTextSize(a.getDimensionPixelSize(C0614R.styleable.FloatingSearchView_floatingSearch_searchSuggestionTextSize, Util.spToPx(18)));
            this.mLeftActionMode = a.getInt(C0614R.styleable.FloatingSearchView_floatingSearch_leftActionMode, 4);
            if (a.hasValue(C0614R.styleable.FloatingSearchView_floatingSearch_menu)) {
                this.mMenuId = a.getResourceId(C0614R.styleable.FloatingSearchView_floatingSearch_menu, -1);
            }
            setDimBackground(a.getBoolean(C0614R.styleable.FloatingSearchView_floatingSearch_dimBackground, true));
            setShowMoveUpSuggestion(a.getBoolean(C0614R.styleable.FloatingSearchView_floatingSearch_showMoveSuggestionUp, false));
            this.mSuggestionSectionAnimDuration = (long) a.getInt(C0614R.styleable.FloatingSearchView_floatingSearch_suggestionsListAnimDuration, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            setBackgroundColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_backgroundColor, Util.getColor(getContext(), C0614R.color.background)));
            setLeftActionIconColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_leftActionColor, Util.getColor(getContext(), C0614R.color.left_action_icon)));
            setActionMenuOverflowColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_actionMenuOverflowColor, Util.getColor(getContext(), C0614R.color.overflow_icon_color)));
            setMenuItemIconColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_menuItemIconColor, Util.getColor(getContext(), C0614R.color.menu_icon_color)));
            setDividerColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_dividerColor, Util.getColor(getContext(), C0614R.color.divider)));
            setClearBtnColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_clearBtnColor, Util.getColor(getContext(), C0614R.color.clear_btn_color)));
            int viewTextColor = a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_viewTextColor, Util.getColor(getContext(), C0614R.color.dark_gray));
            setViewTextColor(viewTextColor);
            setQueryTextColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_viewSearchInputTextColor, viewTextColor));
            setSuggestionsTextColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_viewSuggestionItemTextColor, viewTextColor));
            setHintTextColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_hintTextColor, Util.getColor(getContext(), C0614R.color.hint_color)));
            setSuggestionRightIconColor(a.getColor(C0614R.styleable.FloatingSearchView_floatingSearch_suggestionRightIconColor, Util.getColor(getContext(), C0614R.color.gray_active_icon)));
        } finally {
            a.recycle();
        }
    }

    private void setupQueryBar() {
        this.mSearchInput.setTextColor(this.mSearchInputTextColor);
        this.mSearchInput.setHintTextColor(this.mSearchInputHintColor);
        if (!(isInEditMode() || this.mHostActivity == null)) {
            this.mHostActivity.getWindow().setSoftInputMode(32);
        }
        this.mQuerySection.getViewTreeObserver().addOnGlobalLayoutListener(new C06052());
        this.mMenuView.setMenuCallback(new C06063());
        this.mMenuView.setOnVisibleWidthChanged(new C06074());
        this.mMenuView.setActionIconColor(this.mActionMenuItemColor);
        this.mMenuView.setOverflowColor(this.mOverflowIconColor);
        this.mClearButton.setVisibility(4);
        this.mClearButton.setOnClickListener(new C06085());
        this.mSearchInput.addTextChangedListener(new C06096());
        this.mSearchInput.setOnFocusChangeListener(new C06107());
        this.mSearchInput.setOnKeyboardDismissedListener(new C06118());
        this.mSearchInput.setOnSearchKeyListener(new C06129());
        this.mLeftAction.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (FloatingSearchView.this.isSearchBarFocused()) {
                    FloatingSearchView.this.setSearchFocusedInternal(false);
                    return;
                }
                switch (FloatingSearchView.this.mLeftActionMode) {
                    case 1:
                        if (FloatingSearchView.this.mLeftMenuClickListener != null) {
                            FloatingSearchView.this.mLeftMenuClickListener.onClick(FloatingSearchView.this.mLeftAction);
                            return;
                        } else {
                            FloatingSearchView.this.toggleLeftMenu();
                            return;
                        }
                    case 2:
                        FloatingSearchView.this.setSearchFocusedInternal(true);
                        return;
                    case 3:
                        if (FloatingSearchView.this.mOnHomeActionClickListener != null) {
                            FloatingSearchView.this.mOnHomeActionClickListener.onHomeClicked();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        refreshLeftIcon();
    }

    private void handleOnVisibleMenuItemsWidthChanged(int menuItemsWidth) {
        int paddingRight;
        if (menuItemsWidth == 0) {
            this.mClearButton.setTranslationX((float) (-Util.dpToPx(4)));
            paddingRight = Util.dpToPx(4);
            if (this.mIsFocused) {
                paddingRight += Util.dpToPx(48);
            } else {
                paddingRight += Util.dpToPx(14);
            }
            this.mSearchInput.setPadding(0, 0, paddingRight, 0);
            return;
        }
        this.mClearButton.setTranslationX((float) (-menuItemsWidth));
        paddingRight = menuItemsWidth;
        if (this.mIsFocused) {
            paddingRight += Util.dpToPx(48);
        }
        this.mSearchInput.setPadding(0, 0, paddingRight, 0);
    }

    public void setLeftActionIconColor(int color) {
        this.mLeftActionIconColor = color;
        this.mMenuBtnDrawable.setColor(color);
        DrawableCompat.setTint(this.mIconBackArrow, color);
        DrawableCompat.setTint(this.mIconSearch, color);
    }

    public void setOnMenuClickListener(OnLeftMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }

    public void setClearBtnColor(int color) {
        this.mClearBtnColor = color;
        DrawableCompat.setTint(this.mIconClear, this.mClearBtnColor);
    }

    public void setMenuItemIconColor(int color) {
        this.mActionMenuItemColor = color;
        if (this.mMenuView != null) {
            this.mMenuView.setActionIconColor(this.mActionMenuItemColor);
        }
    }

    public List<MenuItemImpl> getCurrentMenuItems() {
        return this.mMenuView.getCurrentMenuItems();
    }

    public void setActionMenuOverflowColor(int color) {
        this.mOverflowIconColor = color;
        if (this.mMenuView != null) {
            this.mMenuView.setOverflowColor(this.mOverflowIconColor);
        }
    }

    public void setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        if (this.mQuerySection != null && this.mSuggestionsList != null) {
            this.mQuerySection.setCardBackgroundColor(color);
            this.mSuggestionsList.setBackgroundColor(color);
        }
    }

    public void setViewTextColor(int color) {
        setSuggestionsTextColor(color);
        setQueryTextColor(color);
    }

    public void setDismissFocusOnItemSelection(boolean dismissFocusOnItemSelection) {
        this.mDismissFocusOnItemSelection = dismissFocusOnItemSelection;
    }

    public void setSuggestionsTextColor(int color) {
        this.mSuggestionTextColor = color;
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.setTextColor(this.mSuggestionTextColor);
        }
    }

    public void setSuggestionsAnimDuration(long duration) {
        this.mSuggestionSectionAnimDuration = duration;
    }

    public void setQueryTextColor(int color) {
        this.mSearchInputTextColor = color;
        if (this.mSearchInput != null) {
            this.mSearchInput.setTextColor(this.mSearchInputTextColor);
        }
    }

    public void setQueryTextSize(int sizePx) {
        this.mQueryTextSize = sizePx;
        this.mSearchInput.setTextSize((float) this.mQueryTextSize);
    }

    public void setHintTextColor(int color) {
        this.mSearchInputHintColor = color;
        if (this.mSearchInput != null) {
            this.mSearchInput.setHintTextColor(color);
        }
    }

    public void setDividerColor(int color) {
        this.mDividerColor = color;
        if (this.mDivider != null) {
            this.mDivider.setBackgroundColor(this.mDividerColor);
        }
    }

    public void setSuggestionRightIconColor(int color) {
        this.mSuggestionRightIconColor = color;
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.setRightIconColor(this.mSuggestionRightIconColor);
        }
    }

    private void setSuggestionItemTextSize(int sizePx) {
        this.mSuggestionsTextSizePx = sizePx;
    }

    public void setLeftActionMode(int mode) {
        this.mLeftActionMode = mode;
        refreshLeftIcon();
    }

    private void refreshLeftIcon() {
        int leftActionWidthAndMarginLeft = Util.dpToPx(52);
        int queryTranslationX = 0;
        this.mLeftAction.setVisibility(0);
        switch (this.mLeftActionMode) {
            case 1:
                this.mLeftAction.setImageDrawable(this.mMenuBtnDrawable);
                this.mMenuBtnDrawable.setProgress(0.0f);
                break;
            case 2:
                this.mLeftAction.setImageDrawable(this.mIconSearch);
                break;
            case 3:
                this.mLeftAction.setImageDrawable(this.mMenuBtnDrawable);
                this.mMenuBtnDrawable.setProgress(1.0f);
                break;
            case 4:
                this.mLeftAction.setVisibility(4);
                queryTranslationX = -leftActionWidthAndMarginLeft;
                break;
        }
        this.mSearchInputParent.setTranslationX((float) queryTranslationX);
    }

    private void toggleLeftMenu() {
        if (this.mMenuOpen) {
            closeMenu(true);
        } else {
            openMenu(true);
        }
    }

    public void setMenuIconProgress(float progress) {
        this.mMenuBtnDrawable.setProgress(progress);
        if (progress == 0.0f) {
            closeMenu(false);
        } else if (((double) progress) == 1.0d) {
            openMenu(false);
        }
    }

    public void openMenu(boolean withAnim) {
        this.mMenuOpen = true;
        openMenuDrawable(this.mMenuBtnDrawable, withAnim);
        if (this.mOnMenuClickListener != null) {
            this.mOnMenuClickListener.onMenuOpened();
        }
    }

    public void closeMenu(boolean withAnim) {
        this.mMenuOpen = false;
        closeMenuDrawable(this.mMenuBtnDrawable, withAnim);
        if (this.mOnMenuClickListener != null) {
            this.mOnMenuClickListener.onMenuClosed();
        }
    }

    public void setLeftMenuOpen(boolean isOpen) {
        this.mMenuOpen = isOpen;
        this.mMenuBtnDrawable.setProgress(isOpen ? 1.0f : 0.0f);
    }

    public void showProgress() {
        this.mLeftAction.setVisibility(8);
        this.mSearchProgress.setAlpha(0.0f);
        this.mSearchProgress.setVisibility(0);
        ObjectAnimator.ofFloat(this.mSearchProgress, "alpha", new float[]{0.0f, 1.0f}).start();
    }

    public void hideProgress() {
        this.mSearchProgress.setVisibility(8);
        this.mLeftAction.setAlpha(0.0f);
        this.mLeftAction.setVisibility(0);
        ObjectAnimator.ofFloat(this.mLeftAction, "alpha", new float[]{0.0f, 1.0f}).start();
    }

    public void inflateOverflowMenu(int menuId) {
        this.mMenuId = menuId;
        this.mMenuView.reset(menuId, actionMenuAvailWidth());
        if (this.mIsFocused) {
            this.mMenuView.hideIfRoomItems(false);
        }
    }

    private int actionMenuAvailWidth() {
        if (isInEditMode()) {
            return this.mQuerySection.getMeasuredWidth() / 2;
        }
        return this.mQuerySection.getWidth() / 2;
    }

    public void setSearchHint(String searchHint) {
        if (searchHint == null) {
            searchHint = getResources().getString(C0614R.string.abc_search_hint);
        }
        this.mSearchHint = searchHint;
        this.mSearchInput.setHint(this.mSearchHint);
    }

    public void setShowSearchKey(boolean show) {
        this.mShowSearchKey = show;
        if (show) {
            this.mSearchInput.setImeOptions(3);
        } else {
            this.mSearchInput.setImeOptions(1);
        }
    }

    public void setCloseSearchOnKeyboardDismiss(boolean closeSearchOnKeyboardDismiss) {
        this.mCloseSearchOnSofteKeyboardDismiss = closeSearchOnKeyboardDismiss;
    }

    public void setDismissOnOutsideClick(boolean enable) {
        this.mDismissOnOutsideTouch = enable;
        this.mSuggestionsSection.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (FloatingSearchView.this.mDismissOnOutsideTouch && FloatingSearchView.this.mIsFocused) {
                    FloatingSearchView.this.setSearchFocusedInternal(false);
                }
                return true;
            }
        });
    }

    public void setDimBackground(boolean dimEnabled) {
        this.mDimBackground = dimEnabled;
        refreshDimBackground();
    }

    private void refreshDimBackground() {
        if (this.mDimBackground && this.mIsFocused) {
            this.mBackgroundDrawable.setAlpha(150);
        } else {
            this.mBackgroundDrawable.setAlpha(0);
        }
    }

    public void setShowMoveUpSuggestion(boolean show) {
        this.mShowMoveUpSuggestion = show;
        refreshShowMoveUpSuggestion();
    }

    private void refreshShowMoveUpSuggestion() {
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.setShowMoveUpIcon(this.mShowMoveUpSuggestion);
        }
    }

    public void setSearchFocusable(boolean focusable) {
        this.mSearchInput.setFocusable(focusable);
        this.mSearchInput.setFocusableInTouchMode(focusable);
    }

    public void setSearchBarTitle(CharSequence title) {
        this.mTitleText = title.toString();
        this.mIsTitleSet = true;
        this.mSearchInput.setText(title);
    }

    public void setSearchText(CharSequence text) {
        this.mIsTitleSet = false;
        setQueryText(text);
    }

    public String getQuery() {
        return this.mOldQuery;
    }

    public void clearQuery() {
        this.mSearchInput.setText("");
    }

    public boolean setSearchFocused(final boolean focused) {
        boolean updatedToNotFocused = !focused && this.mIsFocused;
        if (focused != this.mIsFocused && this.mSuggestionSecHeightListener == null) {
            if (this.mIsSuggestionsSectionHeightSet) {
                setSearchFocusedInternal(focused);
            } else {
                this.mSuggestionSecHeightListener = new OnSuggestionSecHeightSetListener() {
                    public void onSuggestionSecHeightSet() {
                        FloatingSearchView.this.setSearchFocusedInternal(focused);
                        FloatingSearchView.this.mSuggestionSecHeightListener = null;
                    }
                };
            }
        }
        return updatedToNotFocused;
    }

    private void setupSuggestionSection() {
        this.mSuggestionsList.setLayoutManager(new LinearLayoutManager(getContext(), 1, true));
        this.mSuggestionsList.setItemAnimator(null);
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetectorListenerAdapter() {
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (FloatingSearchView.this.mHostActivity != null) {
                    Util.closeSoftKeyboard(FloatingSearchView.this.mHostActivity);
                }
                return false;
            }
        });
        this.mSuggestionsList.addOnItemTouchListener(new OnItemTouchListenerAdapter() {
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetector.onTouchEvent(e);
                return false;
            }
        });
        this.mSuggestionsAdapter = new SearchSuggestionsAdapter(getContext(), this.mSuggestionsTextSizePx, new Listener() {
            public void onItemSelected(SearchSuggestion item) {
                if (FloatingSearchView.this.mSearchListener != null) {
                    FloatingSearchView.this.mSearchListener.onSuggestionClicked(item);
                }
                if (FloatingSearchView.this.mDismissFocusOnItemSelection) {
                    FloatingSearchView.this.mIsFocused = false;
                    FloatingSearchView.this.mSkipTextChangeEvent = true;
                    if (FloatingSearchView.this.mIsTitleSet) {
                        FloatingSearchView.this.setSearchBarTitle(item.getBody());
                    } else {
                        FloatingSearchView.this.setSearchText(item.getBody());
                    }
                    FloatingSearchView.this.setSearchFocusedInternal(false);
                }
            }

            public void onMoveItemToSearchClicked(SearchSuggestion item) {
                FloatingSearchView.this.setQueryText(item.getBody());
            }
        });
        refreshShowMoveUpSuggestion();
        this.mSuggestionsAdapter.setTextColor(this.mSuggestionTextColor);
        this.mSuggestionsAdapter.setRightIconColor(this.mSuggestionRightIconColor);
        this.mSuggestionsList.setAdapter(this.mSuggestionsAdapter);
        this.mSuggestionsSection.setTranslationY((float) (-Util.dpToPx(5)));
    }

    private void setQueryText(CharSequence text) {
        this.mSearchInput.setText(text);
        this.mSearchInput.setSelection(this.mSearchInput.getText().length());
    }

    private void moveSuggestListToInitialPos() {
        this.mSuggestionListContainer.setTranslationY((float) (-this.mSuggestionListContainer.getHeight()));
    }

    public void swapSuggestions(List<? extends SearchSuggestion> newSearchSuggestions) {
        swapSuggestions(newSearchSuggestions, true);
    }

    private void swapSuggestions(final List<? extends SearchSuggestion> newSearchSuggestions, final boolean withAnim) {
        this.mSuggestionsList.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Util.removeGlobalLayoutObserver(FloatingSearchView.this.mSuggestionsList, this);
                LinearLayoutManager suggestionsListLm = (LinearLayoutManager) FloatingSearchView.this.mSuggestionsList.getLayoutManager();
                if (FloatingSearchView.this.updateSuggestionsSectionHeight(newSearchSuggestions, withAnim)) {
                    suggestionsListLm.setReverseLayout(false);
                } else {
                    FloatingSearchView.this.mSuggestionsAdapter.reverseList();
                    suggestionsListLm.setReverseLayout(true);
                }
                FloatingSearchView.this.mSuggestionsList.setAlpha(1.0f);
            }
        });
        this.mSuggestionsList.setAdapter(this.mSuggestionsAdapter);
        this.mSuggestionsList.setAlpha(0.0f);
        this.mSuggestionsAdapter.swapData(newSearchSuggestions);
        this.mDivider.setVisibility(!newSearchSuggestions.isEmpty() ? 0 : 8);
    }

    private boolean updateSuggestionsSectionHeight(List<? extends SearchSuggestion> newSearchSuggestions, boolean withAnim) {
        int cardTopBottomShadowPadding = Util.dpToPx(5);
        int cardRadiusSize = Util.dpToPx(3);
        int visibleSuggestionHeight = calculateSuggestionItemsHeight(newSearchSuggestions, this.mSuggestionListContainer.getHeight());
        int diff = this.mSuggestionListContainer.getHeight() - visibleSuggestionHeight;
        int addedTranslationYForShadowOffsets = diff <= cardTopBottomShadowPadding ? -(cardTopBottomShadowPadding - diff) : diff < this.mSuggestionListContainer.getHeight() - cardTopBottomShadowPadding ? cardRadiusSize : 0;
        final float newTranslationY = (float) (((-this.mSuggestionListContainer.getHeight()) + visibleSuggestionHeight) + addedTranslationYForShadowOffsets);
        final float fullyInvisibleTranslationY = (float) ((-this.mSuggestionListContainer.getHeight()) + cardRadiusSize);
        ViewCompat.animate(this.mSuggestionListContainer).cancel();
        if (withAnim) {
            ViewCompat.animate(this.mSuggestionListContainer).setInterpolator(SUGGEST_ITEM_ADD_ANIM_INTERPOLATOR).setDuration(this.mSuggestionSectionAnimDuration).translationY(newTranslationY).setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
                public void onAnimationUpdate(View view) {
                    if (FloatingSearchView.this.mOnSuggestionsListHeightChanged != null) {
                        FloatingSearchView.this.mOnSuggestionsListHeightChanged.onSuggestionsListHeightChanged(Math.abs(view.getTranslationY() - fullyInvisibleTranslationY));
                    }
                }
            }).setListener(new ViewPropertyAnimatorListenerAdapter() {
                public void onAnimationCancel(View view) {
                    FloatingSearchView.this.mSuggestionListContainer.setTranslationY(newTranslationY);
                }
            }).start();
        } else {
            this.mSuggestionListContainer.setTranslationY(newTranslationY);
            if (this.mOnSuggestionsListHeightChanged != null) {
                this.mOnSuggestionsListHeightChanged.onSuggestionsListHeightChanged(Math.abs(this.mSuggestionListContainer.getTranslationY() - fullyInvisibleTranslationY));
            }
        }
        if (this.mSuggestionListContainer.getHeight() == visibleSuggestionHeight) {
            return true;
        }
        return false;
    }

    private int calculateSuggestionItemsHeight(List<? extends SearchSuggestion> suggestions, int max) {
        int visibleItemsHeight = 0;
        int i = 0;
        while (i < suggestions.size() && i < this.mSuggestionsList.getChildCount()) {
            visibleItemsHeight += this.mSuggestionsList.getChildAt(i).getHeight();
            if (visibleItemsHeight > max) {
                return max;
            }
            i++;
        }
        return visibleItemsHeight;
    }

    public void setOnBindSuggestionCallback(OnBindSuggestionCallback callback) {
        this.mOnBindSuggestionCallback = callback;
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.setOnBindSuggestionCallback(this.mOnBindSuggestionCallback);
        }
    }

    public void clearSuggestions() {
        swapSuggestions(new ArrayList());
    }

    public void clearSearchFocus() {
        setSearchFocusedInternal(false);
    }

    public boolean isSearchBarFocused() {
        return this.mIsFocused;
    }

    private void setSearchFocusedInternal(boolean focused) {
        int i = 0;
        this.mIsFocused = focused;
        if (focused) {
            this.mSearchInput.requestFocus();
            moveSuggestListToInitialPos();
            this.mSuggestionsSection.setVisibility(0);
            if (this.mDimBackground) {
                fadeInBackground();
            }
            handleOnVisibleMenuItemsWidthChanged(0);
            this.mMenuView.hideIfRoomItems(true);
            transitionInLeftSection(true);
            Util.showSoftKeyboard(getContext(), this.mSearchInput);
            if (this.mMenuOpen) {
                closeMenu(false);
            }
            if (this.mIsTitleSet) {
                this.mSkipTextChangeEvent = true;
                this.mSearchInput.setText("");
            } else {
                this.mSearchInput.setSelection(this.mSearchInput.getText().length());
            }
            this.mSearchInput.setLongClickable(true);
            ImageView imageView = this.mClearButton;
            if (this.mSearchInput.getText().toString().length() == 0) {
                i = 4;
            }
            imageView.setVisibility(i);
            if (this.mFocusChangeListener != null) {
                this.mFocusChangeListener.onFocus();
            }
        } else {
            this.mMainLayout.requestFocus();
            clearSuggestions();
            if (this.mDimBackground) {
                fadeOutBackground();
            }
            handleOnVisibleMenuItemsWidthChanged(0);
            this.mMenuView.showIfRoomItems(true);
            transitionOutLeftSection(true);
            this.mClearButton.setVisibility(8);
            if (this.mHostActivity != null) {
                Util.closeSoftKeyboard(this.mHostActivity);
            }
            if (this.mIsTitleSet) {
                this.mSkipTextChangeEvent = true;
                this.mSearchInput.setText(this.mTitleText);
            }
            this.mSearchInput.setLongClickable(false);
            if (this.mFocusChangeListener != null) {
                this.mFocusChangeListener.onFocusCleared();
            }
        }
        this.mSuggestionsSection.setEnabled(focused);
    }

    private void changeIcon(ImageView imageView, Drawable newIcon, boolean withAnim) {
        imageView.setImageDrawable(newIcon);
        if (withAnim) {
            ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, 1.0f}).start();
        } else {
            imageView.setAlpha(1.0f);
        }
    }

    private void transitionInLeftSection(boolean withAnim) {
        if (this.mSearchProgress.getVisibility() != 0) {
            this.mLeftAction.setVisibility(0);
        } else {
            this.mLeftAction.setVisibility(4);
        }
        AnimatorSet animSet;
        switch (this.mLeftActionMode) {
            case 1:
                openMenuDrawable(this.mMenuBtnDrawable, withAnim);
                if (!this.mMenuOpen) {
                    return;
                }
                return;
            case 2:
                this.mLeftAction.setImageDrawable(this.mIconBackArrow);
                if (withAnim) {
                    this.mLeftAction.setRotation(45.0f);
                    this.mLeftAction.setAlpha(0.0f);
                    ObjectAnimator rotateAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).rotation(0.0f).get();
                    ObjectAnimator fadeAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).alpha(1.0f).get();
                    animSet = new AnimatorSet();
                    animSet.setDuration(CLEAR_BTN_FADE_ANIM_DURATION);
                    animSet.playTogether(new Animator[]{rotateAnim, fadeAnim});
                    animSet.start();
                    return;
                }
                return;
            case 4:
                this.mLeftAction.setImageDrawable(this.mIconBackArrow);
                if (withAnim) {
                    ObjectAnimator searchInputTransXAnim = ViewPropertyObjectAnimator.animate(this.mSearchInputParent).translationX(0.0f).get();
                    this.mLeftAction.setScaleX(0.5f);
                    this.mLeftAction.setScaleY(0.5f);
                    this.mLeftAction.setAlpha(0.0f);
                    this.mLeftAction.setTranslationX((float) Util.dpToPx(8));
                    ObjectAnimator transXArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).translationX(1.0f).get();
                    ObjectAnimator scaleXArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).scaleX(1.0f).get();
                    ObjectAnimator scaleYArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).scaleY(1.0f).get();
                    ObjectAnimator fadeArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).alpha(1.0f).get();
                    transXArrowAnim.setStartDelay(150);
                    scaleXArrowAnim.setStartDelay(150);
                    scaleYArrowAnim.setStartDelay(150);
                    fadeArrowAnim.setStartDelay(150);
                    animSet = new AnimatorSet();
                    animSet.setDuration(CLEAR_BTN_FADE_ANIM_DURATION);
                    animSet.playTogether(new Animator[]{searchInputTransXAnim, transXArrowAnim, scaleXArrowAnim, scaleYArrowAnim, fadeArrowAnim});
                    animSet.start();
                    return;
                }
                this.mSearchInputParent.setTranslationX(0.0f);
                return;
            default:
                return;
        }
    }

    private void transitionOutLeftSection(boolean withAnim) {
        switch (this.mLeftActionMode) {
            case 1:
                closeMenuDrawable(this.mMenuBtnDrawable, withAnim);
                return;
            case 2:
                changeIcon(this.mLeftAction, this.mIconSearch, withAnim);
                return;
            case 4:
                this.mLeftAction.setImageDrawable(this.mIconBackArrow);
                if (withAnim) {
                    ObjectAnimator searchInputTransXAnim = ViewPropertyObjectAnimator.animate(this.mSearchInputParent).translationX((float) (-Util.dpToPx(52))).get();
                    ObjectAnimator scaleXArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).scaleX(0.5f).get();
                    ObjectAnimator scaleYArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).scaleY(0.5f).get();
                    ObjectAnimator fadeArrowAnim = ViewPropertyObjectAnimator.animate(this.mLeftAction).alpha(0.5f).get();
                    scaleXArrowAnim.setDuration(300);
                    scaleYArrowAnim.setDuration(300);
                    fadeArrowAnim.setDuration(300);
                    scaleXArrowAnim.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            FloatingSearchView.this.mLeftAction.setScaleX(1.0f);
                            FloatingSearchView.this.mLeftAction.setScaleY(1.0f);
                            FloatingSearchView.this.mLeftAction.setAlpha(1.0f);
                            FloatingSearchView.this.mLeftAction.setVisibility(4);
                        }
                    });
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.setDuration(350);
                    animSet.playTogether(new Animator[]{scaleXArrowAnim, scaleYArrowAnim, fadeArrowAnim, searchInputTransXAnim});
                    animSet.start();
                    return;
                }
                this.mLeftAction.setVisibility(4);
                return;
            default:
                return;
        }
    }

    public void setOnSuggestionsListHeightChanged(OnSuggestionsListHeightChanged onSuggestionsListHeightChanged) {
        this.mOnSuggestionsListHeightChanged = onSuggestionsListHeightChanged;
    }

    public void setOnQueryChangeListener(OnQueryChangeListener listener) {
        this.mQueryListener = listener;
    }

    public void setOnSearchListener(OnSearchListener listener) {
        this.mSearchListener = listener;
    }

    public void setOnFocusChangeListener(OnFocusChangeListener listener) {
        this.mFocusChangeListener = listener;
    }

    public void setOnLeftMenuClickListener(OnLeftMenuClickListener listener) {
        this.mOnMenuClickListener = listener;
    }

    public void setOnHomeActionClickListener(OnHomeActionClickListener listener) {
        this.mOnHomeActionClickListener = listener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mActionMenuItemListener = listener;
    }

    public void setOnClearSearchActionListener(OnClearSearchActionListener listener) {
        this.mOnClearSearchActionListener = listener;
    }

    private void openMenuDrawable(final DrawerArrowDrawable drawerArrowDrawable, boolean withAnim) {
        if (withAnim) {
            ValueAnimator anim = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            anim.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    drawerArrowDrawable.setProgress(((Float) animation.getAnimatedValue()).floatValue());
                }
            });
            anim.setDuration(250);
            anim.start();
            return;
        }
        drawerArrowDrawable.setProgress(1.0f);
    }

    private void closeMenuDrawable(final DrawerArrowDrawable drawerArrowDrawable, boolean withAnim) {
        if (withAnim) {
            ValueAnimator anim = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            anim.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    drawerArrowDrawable.setProgress(((Float) animation.getAnimatedValue()).floatValue());
                }
            });
            anim.setDuration(250);
            anim.start();
            return;
        }
        drawerArrowDrawable.setProgress(0.0f);
    }

    private void fadeOutBackground() {
        ValueAnimator anim = ValueAnimator.ofInt(new int[]{150, 0});
        anim.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FloatingSearchView.this.mBackgroundDrawable.setAlpha(((Integer) animation.getAnimatedValue()).intValue());
            }
        });
        anim.setDuration(250);
        anim.start();
    }

    private void fadeInBackground() {
        ValueAnimator anim = ValueAnimator.ofInt(new int[]{0, 150});
        anim.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FloatingSearchView.this.mBackgroundDrawable.setAlpha(((Integer) animation.getAnimatedValue()).intValue());
            }
        });
        anim.setDuration(250);
        anim.start();
    }

    private boolean isRTL() {
        Configuration config = getResources().getConfiguration();
        if (ViewCompat.getLayoutDirection(this) == 1) {
            return true;
        }
        return false;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.suggestions = this.mSuggestionsAdapter.getDataSet();
        savedState.isFocused = this.mIsFocused;
        savedState.query = getQuery();
        savedState.suggestionTextSize = this.mSuggestionsTextSizePx;
        savedState.searchHint = this.mSearchHint;
        savedState.dismissOnOutsideClick = this.mDismissOnOutsideTouch;
        savedState.showMoveSuggestionUpBtn = this.mShowMoveUpSuggestion;
        savedState.showSearchKey = this.mShowSearchKey;
        savedState.isTitleSet = this.mIsTitleSet;
        savedState.backgroundColor = this.mBackgroundColor;
        savedState.suggestionsTextColor = this.mSuggestionTextColor;
        savedState.queryTextColor = this.mSearchInputTextColor;
        savedState.searchHintTextColor = this.mSearchInputHintColor;
        savedState.actionOverflowMenuColor = this.mOverflowIconColor;
        savedState.menuItemIconColor = this.mActionMenuItemColor;
        savedState.leftIconColor = this.mLeftActionIconColor;
        savedState.clearBtnColor = this.mClearBtnColor;
        savedState.suggestionUpBtnColor = this.mSuggestionTextColor;
        savedState.dividerColor = this.mDividerColor;
        savedState.menuId = this.mMenuId;
        savedState.leftActionMode = this.mLeftActionMode;
        savedState.queryTextSize = this.mQueryTextSize;
        savedState.dimBackground = this.mDimBackground;
        savedState.dismissOnSoftKeyboardDismiss = this.mDismissOnOutsideTouch;
        savedState.dismissFocusOnSuggestionItemClick = this.mDismissFocusOnItemSelection;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        final SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mIsFocused = savedState.isFocused;
        this.mIsTitleSet = savedState.isTitleSet;
        this.mMenuId = savedState.menuId;
        this.mOldQuery = savedState.query;
        setSearchText(this.mOldQuery);
        this.mSuggestionSectionAnimDuration = savedState.suggestionsSectionAnimSuration;
        setSuggestionItemTextSize(savedState.suggestionTextSize);
        setDismissOnOutsideClick(savedState.dismissOnOutsideClick);
        setShowMoveUpSuggestion(savedState.showMoveSuggestionUpBtn);
        setShowSearchKey(savedState.showSearchKey);
        setSearchHint(savedState.searchHint);
        setBackgroundColor(savedState.backgroundColor);
        setSuggestionsTextColor(savedState.suggestionsTextColor);
        setQueryTextColor(savedState.queryTextColor);
        setQueryTextSize(savedState.queryTextSize);
        setHintTextColor(savedState.searchHintTextColor);
        setActionMenuOverflowColor(savedState.actionOverflowMenuColor);
        setMenuItemIconColor(savedState.menuItemIconColor);
        setLeftActionIconColor(savedState.leftIconColor);
        setClearBtnColor(savedState.clearBtnColor);
        setSuggestionRightIconColor(savedState.suggestionUpBtnColor);
        setDividerColor(savedState.dividerColor);
        setLeftActionMode(savedState.leftActionMode);
        setDimBackground(savedState.dimBackground);
        setCloseSearchOnKeyboardDismiss(savedState.dismissOnSoftKeyboardDismiss);
        setDismissFocusOnItemSelection(savedState.dismissFocusOnSuggestionItemClick);
        this.mSuggestionsSection.setEnabled(this.mIsFocused);
        if (this.mIsFocused) {
            this.mBackgroundDrawable.setAlpha(150);
            this.mSkipTextChangeEvent = true;
            this.mSkipQueryFocusChangeEvent = true;
            this.mSuggestionsSection.setVisibility(0);
            this.mSuggestionSecHeightListener = new OnSuggestionSecHeightSetListener() {
                public void onSuggestionSecHeightSet() {
                    FloatingSearchView.this.swapSuggestions(savedState.suggestions, false);
                    FloatingSearchView.this.mSuggestionSecHeightListener = null;
                    FloatingSearchView.this.transitionInLeftSection(false);
                }
            };
            this.mClearButton.setVisibility(savedState.query.length() == 0 ? 4 : 0);
            this.mLeftAction.setVisibility(0);
            Util.showSoftKeyboard(getContext(), this.mSearchInput);
        }
    }

    public void attachNavigationDrawerToMenuButton(@NonNull DrawerLayout drawerLayout) {
        drawerLayout.addDrawerListener(this.mDrawerListener);
        setOnLeftMenuClickListener(new NavDrawerLeftMenuClickListener(drawerLayout));
    }

    public void detachNavigationDrawerFromMenuButton(@NonNull DrawerLayout drawerLayout) {
        drawerLayout.removeDrawerListener(this.mDrawerListener);
        setOnLeftMenuClickListener(null);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewCompat.animate(this.mSuggestionListContainer).cancel();
    }
}
