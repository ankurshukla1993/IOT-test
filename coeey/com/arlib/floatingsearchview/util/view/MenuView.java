package com.arlib.floatingsearchview.util.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.arlib.floatingsearchview.C0614R;
import com.arlib.floatingsearchview.util.MenuPopupHelper;
import com.arlib.floatingsearchview.util.Util;
import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MenuView extends LinearLayout {
    private final float ACTION_DIMENSION_PX;
    private final int HIDE_IF_ROOM_ITEMS_ANIM_DURATION;
    private final int SHOW_IF_ROOM_ITEMS_ANIM_DURATION;
    private List<ObjectAnimator> anims;
    private int mActionIconColor;
    private List<MenuItemImpl> mActionItems;
    private List<MenuItemImpl> mActionShowAlwaysItems;
    private boolean mHasOverflow;
    private int mMenu;
    private MenuBuilder mMenuBuilder;
    private Callback mMenuCallback;
    private SupportMenuInflater mMenuInflater;
    private List<MenuItemImpl> mMenuItems;
    private MenuPopupHelper mMenuPopupHelper;
    private OnVisibleWidthChangedListener mOnVisibleWidthChangedListener;
    private int mOverflowIconColor;
    private int mVisibleWidth;

    public interface OnVisibleWidthChangedListener {
        void onItemsMenuVisibleWidthChanged(int i);
    }

    class C06191 implements Comparator<MenuItemImpl> {
        C06191() {
        }

        public int compare(MenuItemImpl lhs, MenuItemImpl rhs) {
            return Integer.valueOf(lhs.getOrder()).compareTo(Integer.valueOf(rhs.getOrder()));
        }
    }

    private interface MenuItemImplPredicate {
        boolean apply(MenuItemImpl menuItemImpl);
    }

    class C06202 implements MenuItemImplPredicate {
        C06202() {
        }

        public boolean apply(MenuItemImpl menuItem) {
            return menuItem.getIcon() != null && (menuItem.requiresActionButton() || menuItem.requestsActionButton());
        }
    }

    class C06224 implements OnClickListener {
        C06224() {
        }

        public void onClick(View v) {
            MenuView.this.mMenuPopupHelper.show();
        }
    }

    class C06235 implements MenuItemImplPredicate {
        C06235() {
        }

        public boolean apply(MenuItemImpl menuItem) {
            return menuItem.getIcon() != null && menuItem.requiresActionButton();
        }
    }

    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.HIDE_IF_ROOM_ITEMS_ANIM_DURATION = 400;
        this.SHOW_IF_ROOM_ITEMS_ANIM_DURATION = 450;
        this.mMenu = -1;
        this.mActionItems = new ArrayList();
        this.mActionShowAlwaysItems = new ArrayList();
        this.mHasOverflow = false;
        this.anims = new ArrayList();
        this.ACTION_DIMENSION_PX = context.getResources().getDimension(C0614R.dimen.square_button_size);
        init();
    }

    public List<MenuItemImpl> getCurrentMenuItems() {
        return this.mMenuItems;
    }

    private void init() {
        this.mMenuBuilder = new MenuBuilder(getContext());
        this.mMenuPopupHelper = new MenuPopupHelper(getContext(), this.mMenuBuilder, this);
        this.mActionIconColor = Util.getColor(getContext(), C0614R.color.gray_active_icon);
        this.mOverflowIconColor = Util.getColor(getContext(), C0614R.color.gray_active_icon);
    }

    public void setActionIconColor(int actionColor) {
        this.mActionIconColor = actionColor;
        refreshColors();
    }

    public void setOverflowColor(int overflowColor) {
        this.mOverflowIconColor = overflowColor;
        refreshColors();
    }

    private void refreshColors() {
        int i = 0;
        while (i < getChildCount()) {
            Util.setIconColor((ImageView) getChildAt(i), this.mActionIconColor);
            if (this.mHasOverflow && i == getChildCount() - 1) {
                Util.setIconColor((ImageView) getChildAt(i), this.mOverflowIconColor);
            }
            i++;
        }
    }

    public void setMenuCallback(Callback menuCallback) {
        this.mMenuCallback = menuCallback;
    }

    public void reset(int menu, int availWidth) {
        this.mMenu = menu;
        if (this.mMenu != -1) {
            this.mActionShowAlwaysItems = new ArrayList();
            this.mActionItems = new ArrayList();
            this.mMenuItems = new ArrayList();
            this.mMenuBuilder = new MenuBuilder(getContext());
            this.mMenuPopupHelper = new MenuPopupHelper(getContext(), this.mMenuBuilder, this);
            removeAllViews();
            getMenuInflater().inflate(this.mMenu, this.mMenuBuilder);
            this.mMenuItems = this.mMenuBuilder.getActionItems();
            this.mMenuItems.addAll(this.mMenuBuilder.getNonActionItems());
            Collections.sort(this.mMenuItems, new C06191());
            List<MenuItemImpl> localActionItems = filter(this.mMenuItems, new C06202());
            int availItemRoom = availWidth / ((int) this.ACTION_DIMENSION_PX);
            boolean addOverflowAtTheEnd = false;
            if (localActionItems.size() < this.mMenuItems.size() || availItemRoom < localActionItems.size()) {
                addOverflowAtTheEnd = true;
                availItemRoom--;
            }
            ArrayList<Integer> actionItemsIds = new ArrayList();
            if (availItemRoom > 0) {
                for (int i = 0; i < localActionItems.size(); i++) {
                    final MenuItemImpl menuItem = (MenuItemImpl) localActionItems.get(i);
                    if (menuItem.getIcon() != null) {
                        ImageView action = createActionView();
                        action.setContentDescription(menuItem.getTitle());
                        action.setImageDrawable(menuItem.getIcon());
                        Util.setIconColor(action, this.mActionIconColor);
                        addView(action);
                        this.mActionItems.add(menuItem);
                        actionItemsIds.add(Integer.valueOf(menuItem.getItemId()));
                        action.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                if (MenuView.this.mMenuCallback != null) {
                                    MenuView.this.mMenuCallback.onMenuItemSelected(MenuView.this.mMenuBuilder, menuItem);
                                }
                            }
                        });
                        availItemRoom--;
                        if (availItemRoom == 0) {
                            break;
                        }
                    }
                }
            }
            this.mHasOverflow = addOverflowAtTheEnd;
            if (addOverflowAtTheEnd) {
                ImageView overflowAction = getOverflowActionView();
                overflowAction.setImageResource(C0614R.drawable.ic_more_vert_black_24dp);
                Util.setIconColor(overflowAction, this.mOverflowIconColor);
                addView(overflowAction);
                overflowAction.setOnClickListener(new C06224());
                this.mMenuBuilder.setCallback(this.mMenuCallback);
            }
            Iterator it = actionItemsIds.iterator();
            while (it.hasNext()) {
                this.mMenuBuilder.removeItem(((Integer) it.next()).intValue());
            }
            if (this.mOnVisibleWidthChangedListener != null) {
                this.mVisibleWidth = (getChildCount() * ((int) this.ACTION_DIMENSION_PX)) - (this.mHasOverflow ? Util.dpToPx(8) : 0);
                this.mOnVisibleWidthChangedListener.onItemsMenuVisibleWidthChanged(this.mVisibleWidth);
            }
        }
    }

    public int getVisibleWidth() {
        return this.mVisibleWidth;
    }

    private ImageView createActionView() {
        return (ImageView) LayoutInflater.from(getContext()).inflate(C0614R.layout.action_item_layout, this, false);
    }

    private ImageView getOverflowActionView() {
        return (ImageView) LayoutInflater.from(getContext()).inflate(C0614R.layout.overflow_action_item_layout, this, false);
    }

    public void hideIfRoomItems(boolean withAnim) {
        if (this.mMenu != -1) {
            int i;
            this.mActionShowAlwaysItems.clear();
            cancelChildAnimListAndClear();
            List<MenuItemImpl> showAlwaysActionItems = filter(this.mMenuItems, new C06235());
            int actionItemIndex = 0;
            while (actionItemIndex < this.mActionItems.size() && actionItemIndex < showAlwaysActionItems.size()) {
                final MenuItemImpl showAlwaysActionItem = (MenuItemImpl) showAlwaysActionItems.get(actionItemIndex);
                if (((MenuItemImpl) this.mActionItems.get(actionItemIndex)).getItemId() != showAlwaysActionItem.getItemId()) {
                    ImageView action = (ImageView) getChildAt(actionItemIndex);
                    action.setImageDrawable(showAlwaysActionItem.getIcon());
                    Util.setIconColor(action, this.mOverflowIconColor);
                    action.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (MenuView.this.mMenuCallback != null) {
                                MenuView.this.mMenuCallback.onMenuItemSelected(MenuView.this.mMenuBuilder, showAlwaysActionItem);
                            }
                        }
                    });
                }
                this.mActionShowAlwaysItems.add(showAlwaysActionItem);
                actionItemIndex++;
            }
            int diff = (this.mActionItems.size() - actionItemIndex) + (this.mHasOverflow ? 1 : 0);
            this.anims = new ArrayList();
            for (i = 0; i < actionItemIndex; i++) {
                final View currentChild = getChildAt(i);
                final float destTransX = (((float) diff) * this.ACTION_DIMENSION_PX) - ((float) (this.mHasOverflow ? Util.dpToPx(8) : 0));
                this.anims.add(ViewPropertyObjectAnimator.animate(currentChild).setDuration(withAnim ? 400 : 0).setInterpolator(new AccelerateInterpolator()).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        currentChild.setTranslationX(destTransX);
                    }
                }).translationXBy(destTransX).get());
            }
            for (i = actionItemIndex; i < diff + actionItemIndex; i++) {
                final View currentView = getChildAt(i);
                currentView.setClickable(false);
                if (i != getChildCount() - 1) {
                    this.anims.add(ViewPropertyObjectAnimator.animate(currentView).setDuration(withAnim ? 400 : 0).addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            currentView.setTranslationX(MenuView.this.ACTION_DIMENSION_PX);
                        }
                    }).translationXBy(this.ACTION_DIMENSION_PX).get());
                }
                this.anims.add(ViewPropertyObjectAnimator.animate(currentView).setDuration(withAnim ? 400 : 0).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        currentView.setScaleX(0.5f);
                    }
                }).scaleX(0.5f).get());
                this.anims.add(ViewPropertyObjectAnimator.animate(currentView).setDuration(withAnim ? 400 : 0).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        currentView.setScaleY(0.5f);
                    }
                }).scaleY(0.5f).get());
                this.anims.add(ViewPropertyObjectAnimator.animate(currentView).setDuration(withAnim ? 400 : 0).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        currentView.setAlpha(0.0f);
                    }
                }).alpha(0.0f).get());
            }
            final int actionItemsCount = actionItemIndex;
            if (!this.anims.isEmpty()) {
                AnimatorSet animSet = new AnimatorSet();
                if (!withAnim) {
                    animSet.setDuration(0);
                }
                animSet.playTogether((Animator[]) this.anims.toArray(new ObjectAnimator[this.anims.size()]));
                animSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        if (MenuView.this.mOnVisibleWidthChangedListener != null) {
                            MenuView.this.mVisibleWidth = ((int) MenuView.this.ACTION_DIMENSION_PX) * actionItemsCount;
                            MenuView.this.mOnVisibleWidthChangedListener.onItemsMenuVisibleWidthChanged(MenuView.this.mVisibleWidth);
                        }
                    }
                });
                animSet.start();
            }
        }
    }

    public void showIfRoomItems(boolean withAnim) {
        if (this.mMenu != -1) {
            cancelChildAnimListAndClear();
            if (!this.mMenuItems.isEmpty()) {
                this.anims = new ArrayList();
                for (int i = 0; i < getChildCount(); i++) {
                    final View currentView = getChildAt(i);
                    if (i < this.mActionItems.size()) {
                        ImageView action = (ImageView) currentView;
                        final MenuItem actionItem = (MenuItem) this.mActionItems.get(i);
                        action.setImageDrawable(actionItem.getIcon());
                        Util.setIconColor(action, this.mActionIconColor);
                        action.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                if (MenuView.this.mMenuCallback != null) {
                                    MenuView.this.mMenuCallback.onMenuItemSelected(MenuView.this.mMenuBuilder, actionItem);
                                }
                            }
                        });
                    }
                    Interpolator interpolator = new DecelerateInterpolator();
                    if (i > this.mActionShowAlwaysItems.size() - 1) {
                        interpolator = new LinearInterpolator();
                    }
                    currentView.setClickable(true);
                    this.anims.add(ViewPropertyObjectAnimator.animate(currentView).addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            currentView.setTranslationX(0.0f);
                        }
                    }).setInterpolator(interpolator).translationX(0.0f).get());
                    this.anims.add(ViewPropertyObjectAnimator.animate(currentView).addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            currentView.setScaleX(FlexItem.FLEX_SHRINK_DEFAULT);
                        }
                    }).setInterpolator(interpolator).scaleX(FlexItem.FLEX_SHRINK_DEFAULT).get());
                    this.anims.add(ViewPropertyObjectAnimator.animate(currentView).addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            currentView.setScaleY(FlexItem.FLEX_SHRINK_DEFAULT);
                        }
                    }).setInterpolator(interpolator).scaleY(FlexItem.FLEX_SHRINK_DEFAULT).get());
                    this.anims.add(ViewPropertyObjectAnimator.animate(currentView).addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            currentView.setAlpha(FlexItem.FLEX_SHRINK_DEFAULT);
                        }
                    }).setInterpolator(interpolator).alpha(FlexItem.FLEX_SHRINK_DEFAULT).get());
                }
                if (!this.anims.isEmpty()) {
                    AnimatorSet animSet = new AnimatorSet();
                    if (!withAnim) {
                        animSet.setDuration(0);
                    }
                    animSet.playTogether((Animator[]) this.anims.toArray(new ObjectAnimator[this.anims.size()]));
                    animSet.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            if (MenuView.this.mOnVisibleWidthChangedListener != null) {
                                MenuView.this.mVisibleWidth = (((int) MenuView.this.ACTION_DIMENSION_PX) * MenuView.this.getChildCount()) - (MenuView.this.mHasOverflow ? Util.dpToPx(8) : 0);
                                MenuView.this.mOnVisibleWidthChangedListener.onItemsMenuVisibleWidthChanged(MenuView.this.mVisibleWidth);
                            }
                        }
                    });
                    animSet.start();
                }
            }
        }
    }

    private List<MenuItemImpl> filter(List<MenuItemImpl> target, MenuItemImplPredicate predicate) {
        List<MenuItemImpl> result = new ArrayList();
        for (MenuItemImpl element : target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    public void setOnVisibleWidthChanged(OnVisibleWidthChangedListener listener) {
        this.mOnVisibleWidthChangedListener = listener;
    }

    private void cancelChildAnimListAndClear() {
        for (ObjectAnimator animator : this.anims) {
            animator.cancel();
        }
        this.anims.clear();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelChildAnimListAndClear();
    }
}
