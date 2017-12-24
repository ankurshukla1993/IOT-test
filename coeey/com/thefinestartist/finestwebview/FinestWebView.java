package com.thefinestartist.finestwebview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.webkit.WebSettings.LayoutAlgorithm;
import com.thefinestartist.Base;
import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.listeners.BroadCastManager;
import com.thefinestartist.finestwebview.listeners.WebViewListener;
import com.thefinestartist.utils.content.ContextUtil;
import com.thefinestartist.utils.content.ResourcesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinestWebView {

    public static class Builder implements Serializable {
        protected Integer animationCloseEnter;
        protected Integer animationCloseExit;
        protected Integer animationOpenEnter = Integer.valueOf(C2332R.anim.modal_activity_open_enter);
        protected Integer animationOpenExit = Integer.valueOf(C2332R.anim.modal_activity_open_exit);
        protected Boolean backPressToClose;
        protected final transient Context context;
        protected String data;
        protected Boolean disableIconBack;
        protected Boolean disableIconClose;
        protected Boolean disableIconForward;
        protected Boolean disableIconMenu;
        protected Integer dividerColor;
        protected Float dividerHeight;
        protected String encoding;
        protected Boolean gradientDivider;
        protected Integer iconDefaultColor;
        protected Integer iconDisabledColor;
        protected Integer iconPressedColor;
        protected Integer iconSelector;
        protected String injectJavaScript;
        protected Integer key;
        protected transient List<WebViewListener> listeners = new ArrayList();
        protected Integer menuColor;
        protected Integer menuDropShadowColor;
        protected Float menuDropShadowSize;
        protected Integer menuSelector;
        protected Integer menuTextColor;
        protected String menuTextFont;
        protected Integer menuTextGravity;
        protected Float menuTextPaddingLeft;
        protected Float menuTextPaddingRight;
        protected Float menuTextSize;
        protected String mimeType;
        protected Integer progressBarColor;
        protected Float progressBarHeight;
        protected Position progressBarPosition;
        protected Boolean rtl;
        protected Boolean showDivider;
        protected Boolean showIconBack;
        protected Boolean showIconClose;
        protected Boolean showIconForward;
        protected Boolean showIconMenu;
        protected Boolean showMenuCopyLink;
        protected Boolean showMenuFind;
        protected Boolean showMenuOpenWith;
        protected Boolean showMenuRefresh;
        protected Boolean showMenuShareVia;
        protected Boolean showProgressBar;
        protected Boolean showSwipeRefreshLayout;
        protected Boolean showUrl;
        protected Integer statusBarColor;
        protected Integer stringResCopiedToClipboard;
        protected Integer stringResCopyLink;
        protected Integer stringResFind;
        protected Integer stringResOpenWith;
        protected Integer stringResRefresh;
        protected Integer stringResShareVia;
        protected Integer swipeRefreshColor;
        protected Integer[] swipeRefreshColors;
        protected Integer theme;
        protected Integer titleColor;
        protected String titleDefault;
        protected String titleFont;
        protected Float titleSize;
        protected Integer toolbarColor;
        protected Integer toolbarScrollFlags;
        protected Boolean updateTitleFromHtml;
        protected String url;
        protected Integer urlColor;
        protected String urlFont;
        protected Float urlSize;
        protected Boolean webViewAllowContentAccess;
        protected Boolean webViewAllowFileAccess;
        protected Boolean webViewAllowFileAccessFromFileURLs;
        protected Boolean webViewAllowUniversalAccessFromFileURLs;
        protected Boolean webViewAppCacheEnabled;
        protected String webViewAppCachePath;
        protected Boolean webViewBlockNetworkImage;
        protected Boolean webViewBlockNetworkLoads;
        protected Boolean webViewBuiltInZoomControls;
        protected Integer webViewCacheMode;
        protected String webViewCursiveFontFamily;
        protected Boolean webViewDatabaseEnabled;
        protected Integer webViewDefaultFixedFontSize;
        protected Integer webViewDefaultFontSize;
        protected String webViewDefaultTextEncodingName;
        protected Boolean webViewDisplayZoomControls;
        protected Boolean webViewDomStorageEnabled;
        protected String webViewFantasyFontFamily;
        protected String webViewFixedFontFamily;
        protected String webViewGeolocationDatabasePath;
        protected Boolean webViewGeolocationEnabled;
        protected Boolean webViewJavaScriptCanOpenWindowsAutomatically;
        protected Boolean webViewJavaScriptEnabled;
        protected LayoutAlgorithm webViewLayoutAlgorithm;
        protected Boolean webViewLoadWithOverviewMode;
        protected Boolean webViewLoadsImagesAutomatically;
        protected Boolean webViewMediaPlaybackRequiresUserGesture;
        protected Integer webViewMinimumFontSize;
        protected Integer webViewMinimumLogicalFontSize;
        protected Integer webViewMixedContentMode;
        protected Boolean webViewNeedInitialFocus;
        protected Boolean webViewOffscreenPreRaster;
        protected String webViewSansSerifFontFamily;
        protected Boolean webViewSaveFormData;
        protected String webViewSerifFontFamily;
        protected String webViewStandardFontFamily;
        protected Boolean webViewSupportMultipleWindows;
        protected Boolean webViewSupportZoom;
        protected Integer webViewTextZoom;
        protected Boolean webViewUseWideViewPort;
        protected String webViewUserAgentString;

        public Builder setWebViewListener(WebViewListener listener) {
            this.listeners.clear();
            this.listeners.add(listener);
            return this;
        }

        public Builder addWebViewListener(WebViewListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public Builder removeWebViewListener(WebViewListener listener) {
            this.listeners.remove(listener);
            return this;
        }

        public Builder rtl(boolean rtl) {
            this.rtl = Boolean.valueOf(rtl);
            return this;
        }

        public Builder(@NonNull Activity activity) {
            this.context = activity;
            Base.initialize(activity);
        }

        public Builder(@NonNull Context context) {
            this.context = context;
            Base.initialize(context);
        }

        public Builder theme(@StyleRes int theme) {
            this.theme = Integer.valueOf(theme);
            return this;
        }

        public Builder statusBarColor(@ColorInt int color) {
            this.statusBarColor = Integer.valueOf(color);
            return this;
        }

        public Builder statusBarColorRes(@ColorRes int colorRes) {
            this.statusBarColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder toolbarColor(@ColorInt int color) {
            this.toolbarColor = Integer.valueOf(color);
            return this;
        }

        public Builder toolbarColorRes(@ColorRes int colorRes) {
            this.toolbarColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder toolbarScrollFlags(int flags) {
            this.toolbarScrollFlags = Integer.valueOf(flags);
            return this;
        }

        public Builder iconDefaultColor(@ColorInt int color) {
            this.iconDefaultColor = Integer.valueOf(color);
            return this;
        }

        public Builder iconDefaultColorRes(@ColorRes int color) {
            this.iconDefaultColor = Integer.valueOf(ResourcesUtil.getColor(color));
            return this;
        }

        public Builder iconDisabledColor(@ColorInt int color) {
            this.iconDisabledColor = Integer.valueOf(color);
            return this;
        }

        public Builder iconDisabledColorRes(@ColorRes int colorRes) {
            this.iconDisabledColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder iconPressedColor(@ColorInt int color) {
            this.iconPressedColor = Integer.valueOf(color);
            return this;
        }

        public Builder iconPressedColorRes(@ColorRes int colorRes) {
            this.iconPressedColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder iconSelector(@DrawableRes int selectorRes) {
            this.iconSelector = Integer.valueOf(selectorRes);
            return this;
        }

        public Builder showIconClose(boolean showIconClose) {
            this.showIconClose = Boolean.valueOf(showIconClose);
            return this;
        }

        public Builder disableIconClose(boolean disableIconClose) {
            this.disableIconClose = Boolean.valueOf(disableIconClose);
            return this;
        }

        public Builder showIconBack(boolean showIconBack) {
            this.showIconBack = Boolean.valueOf(showIconBack);
            return this;
        }

        public Builder disableIconBack(boolean disableIconBack) {
            this.disableIconBack = Boolean.valueOf(disableIconBack);
            return this;
        }

        public Builder showIconForward(boolean showIconForward) {
            this.showIconForward = Boolean.valueOf(showIconForward);
            return this;
        }

        public Builder disableIconForward(boolean disableIconForward) {
            this.disableIconForward = Boolean.valueOf(disableIconForward);
            return this;
        }

        public Builder showIconMenu(boolean showIconMenu) {
            this.showIconMenu = Boolean.valueOf(showIconMenu);
            return this;
        }

        public Builder disableIconMenu(boolean disableIconMenu) {
            this.disableIconMenu = Boolean.valueOf(disableIconMenu);
            return this;
        }

        public Builder showSwipeRefreshLayout(boolean showSwipeRefreshLayout) {
            this.showSwipeRefreshLayout = Boolean.valueOf(showSwipeRefreshLayout);
            return this;
        }

        public Builder swipeRefreshColor(@ColorInt int color) {
            this.swipeRefreshColor = Integer.valueOf(color);
            return this;
        }

        public Builder swipeRefreshColorRes(@ColorRes int colorRes) {
            this.swipeRefreshColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder swipeRefreshColors(int[] colors) {
            Integer[] swipeRefreshColors = new Integer[colors.length];
            for (int i = 0; i < colors.length; i++) {
                swipeRefreshColors[i] = Integer.valueOf(colors[i]);
            }
            this.swipeRefreshColors = swipeRefreshColors;
            return this;
        }

        public Builder swipeRefreshColorsRes(@ArrayRes int colorsRes) {
            return swipeRefreshColors(ResourcesUtil.getIntArray(colorsRes));
        }

        public Builder showDivider(boolean showDivider) {
            this.showDivider = Boolean.valueOf(showDivider);
            return this;
        }

        public Builder gradientDivider(boolean gradientDivider) {
            this.gradientDivider = Boolean.valueOf(gradientDivider);
            return this;
        }

        public Builder dividerColor(@ColorInt int color) {
            this.dividerColor = Integer.valueOf(color);
            return this;
        }

        public Builder dividerColorRes(@ColorRes int colorRes) {
            this.dividerColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder dividerHeight(float height) {
            this.dividerHeight = Float.valueOf(height);
            return this;
        }

        public Builder dividerHeight(int height) {
            this.dividerHeight = Float.valueOf((float) height);
            return this;
        }

        public Builder dividerHeightRes(@DimenRes int height) {
            this.dividerHeight = Float.valueOf(ResourcesUtil.getDimension(height));
            return this;
        }

        public Builder showProgressBar(boolean showProgressBar) {
            this.showProgressBar = Boolean.valueOf(showProgressBar);
            return this;
        }

        public Builder progressBarColor(@ColorInt int color) {
            this.progressBarColor = Integer.valueOf(color);
            return this;
        }

        public Builder progressBarColorRes(@ColorRes int colorRes) {
            this.progressBarColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder progressBarHeight(float height) {
            this.progressBarHeight = Float.valueOf(height);
            return this;
        }

        public Builder progressBarHeight(int height) {
            this.progressBarHeight = Float.valueOf((float) height);
            return this;
        }

        public Builder progressBarHeightRes(@DimenRes int height) {
            this.progressBarHeight = Float.valueOf(ResourcesUtil.getDimension(height));
            return this;
        }

        public Builder progressBarPosition(@NonNull Position position) {
            this.progressBarPosition = position;
            return this;
        }

        public Builder titleDefault(@NonNull String title) {
            this.titleDefault = title;
            return this;
        }

        public Builder titleDefaultRes(@StringRes int stringRes) {
            this.titleDefault = ResourcesUtil.getString(stringRes);
            return this;
        }

        public Builder updateTitleFromHtml(boolean updateTitleFromHtml) {
            this.updateTitleFromHtml = Boolean.valueOf(updateTitleFromHtml);
            return this;
        }

        public Builder titleSize(float titleSize) {
            this.titleSize = Float.valueOf(titleSize);
            return this;
        }

        public Builder titleSize(int titleSize) {
            this.titleSize = Float.valueOf((float) titleSize);
            return this;
        }

        public Builder titleSizeRes(@DimenRes int titleSize) {
            this.titleSize = Float.valueOf(ResourcesUtil.getDimension(titleSize));
            return this;
        }

        public Builder titleFont(String titleFont) {
            this.titleFont = titleFont;
            return this;
        }

        public Builder titleColor(@ColorInt int color) {
            this.titleColor = Integer.valueOf(color);
            return this;
        }

        public Builder titleColorRes(@ColorRes int colorRes) {
            this.titleColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder showUrl(boolean showUrl) {
            this.showUrl = Boolean.valueOf(showUrl);
            return this;
        }

        public Builder urlSize(float urlSize) {
            this.urlSize = Float.valueOf(urlSize);
            return this;
        }

        public Builder urlSize(int urlSize) {
            this.urlSize = Float.valueOf((float) urlSize);
            return this;
        }

        public Builder urlSizeRes(@DimenRes int urlSize) {
            this.urlSize = Float.valueOf(ResourcesUtil.getDimension(urlSize));
            return this;
        }

        public Builder urlFont(String urlFont) {
            this.urlFont = urlFont;
            return this;
        }

        public Builder urlColor(@ColorInt int color) {
            this.urlColor = Integer.valueOf(color);
            return this;
        }

        public Builder urlColorRes(@ColorRes int colorRes) {
            this.urlColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder menuColor(@ColorInt int color) {
            this.menuColor = Integer.valueOf(color);
            return this;
        }

        public Builder menuColorRes(@ColorRes int colorRes) {
            this.menuColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder menuTextGravity(int gravity) {
            this.menuTextGravity = Integer.valueOf(gravity);
            return this;
        }

        public Builder menuTextPaddingLeft(float menuTextPaddingLeft) {
            this.menuTextPaddingLeft = Float.valueOf(menuTextPaddingLeft);
            return this;
        }

        public Builder menuTextPaddingLeft(int menuTextPaddingLeft) {
            this.menuTextPaddingLeft = Float.valueOf((float) menuTextPaddingLeft);
            return this;
        }

        public Builder menuTextPaddingLeftRes(@DimenRes int menuTextPaddingLeft) {
            this.menuTextPaddingLeft = Float.valueOf(ResourcesUtil.getDimension(menuTextPaddingLeft));
            return this;
        }

        public Builder menuTextPaddingRight(float menuTextPaddingRight) {
            this.menuTextPaddingRight = Float.valueOf(menuTextPaddingRight);
            return this;
        }

        public Builder menuTextPaddingRight(int menuTextPaddingRight) {
            this.menuTextPaddingRight = Float.valueOf((float) menuTextPaddingRight);
            return this;
        }

        public Builder menuTextPaddingRightRes(@DimenRes int menuTextPaddingRight) {
            this.menuTextPaddingRight = Float.valueOf(ResourcesUtil.getDimension(menuTextPaddingRight));
            return this;
        }

        public Builder menuDropShadowColor(@ColorInt int color) {
            this.menuDropShadowColor = Integer.valueOf(color);
            return this;
        }

        public Builder menuDropShadowColorRes(@ColorRes int colorRes) {
            this.menuDropShadowColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder menuDropShadowSize(float menuDropShadowSize) {
            this.menuDropShadowSize = Float.valueOf(menuDropShadowSize);
            return this;
        }

        public Builder menuDropShadowSize(int menuDropShadowSize) {
            this.menuDropShadowSize = Float.valueOf((float) menuDropShadowSize);
            return this;
        }

        public Builder menuDropShadowSizeRes(@DimenRes int menuDropShadowSize) {
            this.menuDropShadowSize = Float.valueOf(ResourcesUtil.getDimension(menuDropShadowSize));
            return this;
        }

        public Builder menuSelector(@DrawableRes int selectorRes) {
            this.menuSelector = Integer.valueOf(selectorRes);
            return this;
        }

        public Builder menuTextSize(float menuTextSize) {
            this.menuTextSize = Float.valueOf(menuTextSize);
            return this;
        }

        public Builder menuTextSize(int menuTextSize) {
            this.menuTextSize = Float.valueOf((float) menuTextSize);
            return this;
        }

        public Builder menuTextSizeRes(@DimenRes int menuTextSize) {
            this.menuTextSize = Float.valueOf(ResourcesUtil.getDimension(menuTextSize));
            return this;
        }

        public Builder menuTextFont(String menuTextFont) {
            this.menuTextFont = menuTextFont;
            return this;
        }

        public Builder menuTextColor(@ColorInt int color) {
            this.menuTextColor = Integer.valueOf(color);
            return this;
        }

        public Builder menuTextColorRes(@ColorRes int colorRes) {
            this.menuTextColor = Integer.valueOf(ResourcesUtil.getColor(colorRes));
            return this;
        }

        public Builder showMenuRefresh(boolean showMenuRefresh) {
            this.showMenuRefresh = Boolean.valueOf(showMenuRefresh);
            return this;
        }

        public Builder stringResRefresh(@StringRes int stringResRefresh) {
            this.stringResRefresh = Integer.valueOf(stringResRefresh);
            return this;
        }

        public Builder showMenuFind(boolean showMenuFind) {
            this.showMenuFind = Boolean.valueOf(showMenuFind);
            return this;
        }

        public Builder stringResFind(@StringRes int stringResFind) {
            this.stringResFind = Integer.valueOf(stringResFind);
            return this;
        }

        public Builder showMenuShareVia(boolean showMenuShareVia) {
            this.showMenuShareVia = Boolean.valueOf(showMenuShareVia);
            return this;
        }

        public Builder stringResShareVia(@StringRes int stringResShareVia) {
            this.stringResShareVia = Integer.valueOf(stringResShareVia);
            return this;
        }

        public Builder showMenuCopyLink(boolean showMenuCopyLink) {
            this.showMenuCopyLink = Boolean.valueOf(showMenuCopyLink);
            return this;
        }

        public Builder stringResCopyLink(@StringRes int stringResCopyLink) {
            this.stringResCopyLink = Integer.valueOf(stringResCopyLink);
            return this;
        }

        public Builder showMenuOpenWith(boolean showMenuOpenWith) {
            this.showMenuOpenWith = Boolean.valueOf(showMenuOpenWith);
            return this;
        }

        public Builder stringResOpenWith(@StringRes int stringResOpenWith) {
            this.stringResOpenWith = Integer.valueOf(stringResOpenWith);
            return this;
        }

        public Builder setCustomAnimations(@AnimRes int animationOpenEnter, @AnimRes int animationOpenExit, @AnimRes int animationCloseEnter, @AnimRes int animationCloseExit) {
            this.animationOpenEnter = Integer.valueOf(animationOpenEnter);
            this.animationOpenExit = Integer.valueOf(animationOpenExit);
            this.animationCloseEnter = Integer.valueOf(animationCloseEnter);
            this.animationCloseExit = Integer.valueOf(animationCloseExit);
            return this;
        }

        public Builder setCloseAnimations(@AnimRes int animationCloseEnter, @AnimRes int animationCloseExit) {
            this.animationCloseEnter = Integer.valueOf(animationCloseEnter);
            this.animationCloseExit = Integer.valueOf(animationCloseExit);
            return this;
        }

        public Builder backPressToClose(boolean backPressToClose) {
            this.backPressToClose = Boolean.valueOf(backPressToClose);
            return this;
        }

        public Builder stringResCopiedToClipboard(@StringRes int stringResCopiedToClipboard) {
            this.stringResCopiedToClipboard = Integer.valueOf(stringResCopiedToClipboard);
            return this;
        }

        public Builder webViewSupportZoom(boolean webViewSupportZoom) {
            this.webViewSupportZoom = Boolean.valueOf(webViewSupportZoom);
            return this;
        }

        public Builder webViewMediaPlaybackRequiresUserGesture(boolean webViewMediaPlaybackRequiresUserGesture) {
            this.webViewMediaPlaybackRequiresUserGesture = Boolean.valueOf(webViewMediaPlaybackRequiresUserGesture);
            return this;
        }

        public Builder webViewBuiltInZoomControls(boolean webViewBuiltInZoomControls) {
            this.webViewBuiltInZoomControls = Boolean.valueOf(webViewBuiltInZoomControls);
            return this;
        }

        public Builder webViewDisplayZoomControls(boolean webViewDisplayZoomControls) {
            this.webViewDisplayZoomControls = Boolean.valueOf(webViewDisplayZoomControls);
            return this;
        }

        public Builder webViewAllowFileAccess(boolean webViewAllowFileAccess) {
            this.webViewAllowFileAccess = Boolean.valueOf(webViewAllowFileAccess);
            return this;
        }

        public Builder webViewAllowContentAccess(boolean webViewAllowContentAccess) {
            this.webViewAllowContentAccess = Boolean.valueOf(webViewAllowContentAccess);
            return this;
        }

        public Builder webViewLoadWithOverviewMode(boolean webViewLoadWithOverviewMode) {
            this.webViewLoadWithOverviewMode = Boolean.valueOf(webViewLoadWithOverviewMode);
            return this;
        }

        public Builder webViewSaveFormData(boolean webViewSaveFormData) {
            this.webViewSaveFormData = Boolean.valueOf(webViewSaveFormData);
            return this;
        }

        public Builder webViewTextZoom(int webViewTextZoom) {
            this.webViewTextZoom = Integer.valueOf(webViewTextZoom);
            return this;
        }

        public Builder webViewUseWideViewPort(boolean webViewUseWideViewPort) {
            this.webViewUseWideViewPort = Boolean.valueOf(webViewUseWideViewPort);
            return this;
        }

        public Builder webViewSupportMultipleWindows(boolean webViewSupportMultipleWindows) {
            this.webViewSupportMultipleWindows = Boolean.valueOf(webViewSupportMultipleWindows);
            return this;
        }

        public Builder webViewLayoutAlgorithm(LayoutAlgorithm webViewLayoutAlgorithm) {
            this.webViewLayoutAlgorithm = webViewLayoutAlgorithm;
            return this;
        }

        public Builder webViewStandardFontFamily(String webViewStandardFontFamily) {
            this.webViewStandardFontFamily = webViewStandardFontFamily;
            return this;
        }

        public Builder webViewFixedFontFamily(String webViewFixedFontFamily) {
            this.webViewFixedFontFamily = webViewFixedFontFamily;
            return this;
        }

        public Builder webViewSansSerifFontFamily(String webViewSansSerifFontFamily) {
            this.webViewSansSerifFontFamily = webViewSansSerifFontFamily;
            return this;
        }

        public Builder webViewSerifFontFamily(String webViewSerifFontFamily) {
            this.webViewSerifFontFamily = webViewSerifFontFamily;
            return this;
        }

        public Builder webViewCursiveFontFamily(String webViewCursiveFontFamily) {
            this.webViewCursiveFontFamily = webViewCursiveFontFamily;
            return this;
        }

        public Builder webViewFantasyFontFamily(String webViewFantasyFontFamily) {
            this.webViewFantasyFontFamily = webViewFantasyFontFamily;
            return this;
        }

        public Builder webViewMinimumFontSize(int webViewMinimumFontSize) {
            this.webViewMinimumFontSize = Integer.valueOf(webViewMinimumFontSize);
            return this;
        }

        public Builder webViewMinimumLogicalFontSize(int webViewMinimumLogicalFontSize) {
            this.webViewMinimumLogicalFontSize = Integer.valueOf(webViewMinimumLogicalFontSize);
            return this;
        }

        public Builder webViewDefaultFontSize(int webViewDefaultFontSize) {
            this.webViewDefaultFontSize = Integer.valueOf(webViewDefaultFontSize);
            return this;
        }

        public Builder webViewDefaultFixedFontSize(int webViewDefaultFixedFontSize) {
            this.webViewDefaultFixedFontSize = Integer.valueOf(webViewDefaultFixedFontSize);
            return this;
        }

        public Builder webViewLoadsImagesAutomatically(boolean webViewLoadsImagesAutomatically) {
            this.webViewLoadsImagesAutomatically = Boolean.valueOf(webViewLoadsImagesAutomatically);
            return this;
        }

        public Builder webViewBlockNetworkImage(boolean webViewBlockNetworkImage) {
            this.webViewBlockNetworkImage = Boolean.valueOf(webViewBlockNetworkImage);
            return this;
        }

        public Builder webViewBlockNetworkLoads(boolean webViewBlockNetworkLoads) {
            this.webViewBlockNetworkLoads = Boolean.valueOf(webViewBlockNetworkLoads);
            return this;
        }

        public Builder webViewJavaScriptEnabled(boolean webViewJavaScriptEnabled) {
            this.webViewJavaScriptEnabled = Boolean.valueOf(webViewJavaScriptEnabled);
            return this;
        }

        public Builder webViewAllowUniversalAccessFromFileURLs(boolean webViewAllowUniversalAccessFromFileURLs) {
            this.webViewAllowUniversalAccessFromFileURLs = Boolean.valueOf(webViewAllowUniversalAccessFromFileURLs);
            return this;
        }

        public Builder webViewAllowFileAccessFromFileURLs(boolean webViewAllowFileAccessFromFileURLs) {
            this.webViewAllowFileAccessFromFileURLs = Boolean.valueOf(webViewAllowFileAccessFromFileURLs);
            return this;
        }

        public Builder webViewGeolocationDatabasePath(String webViewGeolocationDatabasePath) {
            this.webViewGeolocationDatabasePath = webViewGeolocationDatabasePath;
            return this;
        }

        public Builder webViewAppCacheEnabled(boolean webViewAppCacheEnabled) {
            this.webViewAppCacheEnabled = Boolean.valueOf(webViewAppCacheEnabled);
            return this;
        }

        public Builder webViewAppCachePath(String webViewAppCachePath) {
            this.webViewAppCachePath = webViewAppCachePath;
            return this;
        }

        public Builder webViewDatabaseEnabled(boolean webViewDatabaseEnabled) {
            this.webViewDatabaseEnabled = Boolean.valueOf(webViewDatabaseEnabled);
            return this;
        }

        public Builder webViewDomStorageEnabled(boolean webViewDomStorageEnabled) {
            this.webViewDomStorageEnabled = Boolean.valueOf(webViewDomStorageEnabled);
            return this;
        }

        public Builder webViewGeolocationEnabled(boolean webViewGeolocationEnabled) {
            this.webViewGeolocationEnabled = Boolean.valueOf(webViewGeolocationEnabled);
            return this;
        }

        public Builder webViewJavaScriptCanOpenWindowsAutomatically(boolean webViewJavaScriptCanOpenWindowsAutomatically) {
            this.webViewJavaScriptCanOpenWindowsAutomatically = Boolean.valueOf(webViewJavaScriptCanOpenWindowsAutomatically);
            return this;
        }

        public Builder webViewDefaultTextEncodingName(String webViewDefaultTextEncodingName) {
            this.webViewDefaultTextEncodingName = webViewDefaultTextEncodingName;
            return this;
        }

        public Builder webViewUserAgentString(String webViewUserAgentString) {
            this.webViewUserAgentString = webViewUserAgentString;
            return this;
        }

        public Builder webViewNeedInitialFocus(boolean webViewNeedInitialFocus) {
            this.webViewNeedInitialFocus = Boolean.valueOf(webViewNeedInitialFocus);
            return this;
        }

        public Builder webViewCacheMode(int webViewCacheMode) {
            this.webViewCacheMode = Integer.valueOf(webViewCacheMode);
            return this;
        }

        public Builder webViewMixedContentMode(int webViewMixedContentMode) {
            this.webViewMixedContentMode = Integer.valueOf(webViewMixedContentMode);
            return this;
        }

        public Builder webViewOffscreenPreRaster(boolean webViewOffscreenPreRaster) {
            this.webViewOffscreenPreRaster = Boolean.valueOf(webViewOffscreenPreRaster);
            return this;
        }

        public Builder webViewDesktopMode(boolean webViewDesktopMode) {
            return webViewUserAgentString("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0");
        }

        public Builder injectJavaScript(String injectJavaScript) {
            this.injectJavaScript = injectJavaScript;
            return this;
        }

        public void load(@StringRes int dataRes) {
            load(ResourcesUtil.getString(dataRes));
        }

        public void load(String data) {
            load(data, "text/html", "UTF-8");
        }

        public void load(String data, String mimeType, String encoding) {
            this.mimeType = mimeType;
            this.encoding = encoding;
            show(null, data);
        }

        public void show(@StringRes int urlRes) {
            show(ResourcesUtil.getString(urlRes));
        }

        public void show(@NonNull String url) {
            show(url, null);
        }

        protected void show(String url, String data) {
            this.url = url;
            this.data = data;
            this.key = Integer.valueOf(System.identityHashCode(this));
            if (!this.listeners.isEmpty()) {
                BroadCastManager broadCastManager = new BroadCastManager(this.context, this.key.intValue(), this.listeners);
            }
            Intent intent = new Intent(this.context, FinestWebViewActivity.class);
            intent.putExtra("builder", this);
            ContextUtil.startActivity(intent);
            if (this.context instanceof Activity) {
                ((Activity) this.context).overridePendingTransition(this.animationOpenEnter.intValue(), this.animationOpenExit.intValue());
            }
        }
    }
}
