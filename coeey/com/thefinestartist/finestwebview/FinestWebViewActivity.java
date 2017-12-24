package com.thefinestartist.finestwebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.flexbox.FlexItem;
import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.converters.UnitConverter;
import com.thefinestartist.finestwebview.FinestWebView.Builder;
import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.BitmapHelper;
import com.thefinestartist.finestwebview.helpers.ColorHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;
import com.thefinestartist.finestwebview.helpers.UrlParser;
import com.thefinestartist.finestwebview.listeners.BroadCastManager;
import com.thefinestartist.finestwebview.views.ShadowLayout;
import com.thefinestartist.utils.etc.APILevel;
import com.thefinestartist.utils.service.ClipboardManagerUtil;
import com.thefinestartist.utils.ui.DisplayUtil;
import com.thefinestartist.utils.ui.ViewUtil;

public class FinestWebViewActivity extends AppCompatActivity implements OnOffsetChangedListener, OnClickListener {
    protected int animationCloseEnter;
    protected int animationCloseExit;
    protected AppBarLayout appBar;
    protected AppCompatImageButton back;
    protected boolean backPressToClose;
    protected AppCompatImageButton close;
    protected CoordinatorLayout coordinatorLayout;
    protected String data;
    protected boolean disableIconBack;
    protected boolean disableIconClose;
    protected boolean disableIconForward;
    protected boolean disableIconMenu;
    protected View divider;
    protected int dividerColor;
    protected float dividerHeight;
    DownloadListener downloadListener = new C23274();
    protected String encoding;
    protected AppCompatImageButton forward;
    protected View gradient;
    protected boolean gradientDivider;
    protected int iconDefaultColor;
    protected int iconDisabledColor;
    protected int iconPressedColor;
    protected int iconSelector;
    protected String injectJavaScript;
    protected int key;
    protected LinearLayout menuBackground;
    protected int menuColor;
    protected LinearLayout menuCopyLink;
    protected TextView menuCopyLinkTv;
    protected int menuDropShadowColor;
    protected float menuDropShadowSize;
    protected LinearLayout menuFind;
    protected TextView menuFindTv;
    protected RelativeLayout menuLayout;
    protected LinearLayout menuOpenWith;
    protected TextView menuOpenWithTv;
    protected LinearLayout menuRefresh;
    protected TextView menuRefreshTv;
    protected int menuSelector;
    protected LinearLayout menuShareVia;
    protected TextView menuShareViaTv;
    protected int menuTextColor;
    protected String menuTextFont;
    protected int menuTextGravity;
    protected float menuTextPaddingLeft;
    protected float menuTextPaddingRight;
    protected float menuTextSize;
    protected String mimeType;
    protected AppCompatImageButton more;
    protected ProgressBar progressBar;
    protected int progressBarColor;
    protected float progressBarHeight;
    protected Position progressBarPosition;
    protected boolean rtl;
    protected ShadowLayout shadowLayout;
    protected boolean showDivider;
    protected boolean showIconBack;
    protected boolean showIconClose;
    protected boolean showIconForward;
    protected boolean showIconMenu;
    protected boolean showMenuCopyLink;
    protected boolean showMenuFind;
    protected boolean showMenuOpenWith;
    protected boolean showMenuRefresh;
    protected boolean showMenuShareVia;
    protected boolean showProgressBar;
    protected boolean showSwipeRefreshLayout;
    protected boolean showUrl;
    protected int statusBarColor;
    protected int stringResCopiedToClipboard;
    protected int stringResCopyLink;
    protected int stringResFind;
    protected int stringResOpenWith;
    protected int stringResRefresh;
    protected int stringResShareVia;
    protected int swipeRefreshColor;
    protected int[] swipeRefreshColors;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected int theme;
    protected TextView title;
    protected int titleColor;
    protected String titleDefault;
    protected String titleFont;
    protected float titleSize;
    protected Toolbar toolbar;
    protected int toolbarColor;
    protected RelativeLayout toolbarLayout;
    protected int toolbarScrollFlags;
    protected boolean updateTitleFromHtml;
    protected String url;
    protected int urlColor;
    protected String urlFont;
    protected float urlSize;
    protected TextView urlTv;
    protected FrameLayout webLayout;
    protected WebView webView;
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

    class C23241 implements Runnable {
        C23241() {
        }

        public void run() {
            FinestWebViewActivity.this.swipeRefreshLayout.setRefreshing(true);
        }
    }

    class C23252 implements OnRefreshListener {
        C23252() {
        }

        public void onRefresh() {
            FinestWebViewActivity.this.webView.reload();
        }
    }

    class C23263 implements AnimationListener {
        C23263() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            FinestWebViewActivity.this.menuLayout.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C23274 implements DownloadListener {
        C23274() {
        }

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            BroadCastManager.onDownloadStart(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url, userAgent, contentDisposition, mimetype, contentLength);
        }
    }

    class C23285 implements Runnable {
        C23285() {
        }

        public void run() {
            if (FinestWebViewActivity.this.webView != null) {
                FinestWebViewActivity.this.webView.destroy();
            }
        }
    }

    public class MyWebChromeClient extends WebChromeClient {

        class C23301 implements Runnable {
            C23301() {
            }

            public void run() {
                FinestWebViewActivity.this.swipeRefreshLayout.setRefreshing(false);
            }
        }

        class C23312 implements Runnable {
            C23312() {
            }

            public void run() {
                FinestWebViewActivity.this.swipeRefreshLayout.setRefreshing(true);
            }
        }

        public void onProgressChanged(WebView view, int progress) {
            BroadCastManager.onProgressChanged(FinestWebViewActivity.this, FinestWebViewActivity.this.key, progress);
            if (FinestWebViewActivity.this.showSwipeRefreshLayout) {
                if (FinestWebViewActivity.this.swipeRefreshLayout.isRefreshing() && progress == 100) {
                    FinestWebViewActivity.this.swipeRefreshLayout.post(new C23301());
                }
                if (!(FinestWebViewActivity.this.swipeRefreshLayout.isRefreshing() || progress == 100)) {
                    FinestWebViewActivity.this.swipeRefreshLayout.post(new C23312());
                }
            }
            if (progress == 100) {
                progress = 0;
            }
            FinestWebViewActivity.this.progressBar.setProgress(progress);
        }

        public void onReceivedTitle(WebView view, String title) {
            BroadCastManager.onReceivedTitle(FinestWebViewActivity.this, FinestWebViewActivity.this.key, title);
        }

        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            BroadCastManager.onReceivedTouchIconUrl(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url, precomposed);
        }
    }

    public class MyWebViewClient extends WebViewClient {
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            BroadCastManager.onPageStarted(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url);
            if (!url.contains("docs.google.com") && url.endsWith(".pdf")) {
                FinestWebViewActivity.this.webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
            }
        }

        public void onPageFinished(WebView view, String url) {
            boolean z = true;
            int i = 8;
            BroadCastManager.onPageFinished(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url);
            if (FinestWebViewActivity.this.updateTitleFromHtml) {
                FinestWebViewActivity.this.title.setText(view.getTitle());
            }
            FinestWebViewActivity.this.urlTv.setText(UrlParser.getHost(url));
            FinestWebViewActivity.this.requestCenterLayout();
            if (view.canGoBack() || view.canGoForward()) {
                FinestWebViewActivity.this.back.setVisibility(FinestWebViewActivity.this.showIconBack ? 0 : 8);
                AppCompatImageButton appCompatImageButton = FinestWebViewActivity.this.forward;
                if (FinestWebViewActivity.this.showIconForward) {
                    i = 0;
                }
                appCompatImageButton.setVisibility(i);
                AppCompatImageButton appCompatImageButton2 = FinestWebViewActivity.this.back;
                boolean z2 = !FinestWebViewActivity.this.disableIconBack && (FinestWebViewActivity.this.rtl ? view.canGoForward() : view.canGoBack());
                appCompatImageButton2.setEnabled(z2);
                appCompatImageButton = FinestWebViewActivity.this.forward;
                if (FinestWebViewActivity.this.disableIconForward || (FinestWebViewActivity.this.rtl ? view.canGoBack() : view.canGoForward())) {
                    z = false;
                }
                appCompatImageButton.setEnabled(z);
            } else {
                FinestWebViewActivity.this.back.setVisibility(8);
                FinestWebViewActivity.this.forward.setVisibility(8);
            }
            if (FinestWebViewActivity.this.injectJavaScript != null) {
                FinestWebViewActivity.this.webView.loadUrl(FinestWebViewActivity.this.injectJavaScript);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent;
            if (url.endsWith(".mp4")) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse(url), "video/*");
                intent.setFlags(268435456);
                view.getContext().startActivity(intent);
                return true;
            } else if (!url.startsWith("tel:") && !url.startsWith("sms:") && !url.startsWith("smsto:") && !url.startsWith("mms:") && !url.startsWith("mmsto:")) {
                return super.shouldOverrideUrlLoading(view, url);
            } else {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                intent.setFlags(268435456);
                view.getContext().startActivity(intent);
                return true;
            }
        }

        public void onLoadResource(WebView view, String url) {
            BroadCastManager.onLoadResource(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url);
        }

        public void onPageCommitVisible(WebView view, String url) {
            BroadCastManager.onPageCommitVisible(FinestWebViewActivity.this, FinestWebViewActivity.this.key, url);
        }
    }

    protected void initializeOptions() {
        Intent intent = getIntent();
        if (intent != null) {
            int intValue;
            Builder builder = (Builder) intent.getSerializableExtra("builder");
            setTheme(builder.theme != null ? builder.theme.intValue() : 0);
            TypedArray typedArray = obtainStyledAttributes(new TypedValue().data, new int[]{C2332R.attr.colorPrimaryDark, C2332R.attr.colorPrimary, C2332R.attr.colorAccent, 16842806, 16842808, 16843534, 16843868});
            int colorPrimaryDark = typedArray.getColor(0, ContextCompat.getColor(this, C2332R.color.finestGray));
            int colorPrimary = typedArray.getColor(1, ContextCompat.getColor(this, C2332R.color.finestWhite));
            int colorAccent = typedArray.getColor(2, ContextCompat.getColor(this, C2332R.color.finestBlack));
            int color = typedArray.getColor(3, ContextCompat.getColor(this, C2332R.color.finestBlack));
            int color2 = typedArray.getColor(4, ContextCompat.getColor(this, C2332R.color.finestSilver));
            int resourceId = VERSION.SDK_INT >= 11 ? typedArray.getResourceId(5, 0) : C2332R.drawable.selector_light_theme;
            int resourceId2 = VERSION.SDK_INT >= 21 ? typedArray.getResourceId(6, 0) : C2332R.drawable.selector_light_theme;
            typedArray.recycle();
            this.key = builder.key.intValue();
            this.rtl = builder.rtl != null ? builder.rtl.booleanValue() : getResources().getBoolean(C2332R.bool.is_right_to_left);
            if (builder.statusBarColor != null) {
                colorPrimaryDark = builder.statusBarColor.intValue();
            }
            this.statusBarColor = colorPrimaryDark;
            if (builder.toolbarColor != null) {
                colorPrimary = builder.toolbarColor.intValue();
            }
            this.toolbarColor = colorPrimary;
            this.toolbarScrollFlags = builder.toolbarScrollFlags != null ? builder.toolbarScrollFlags.intValue() : 5;
            if (builder.iconDefaultColor != null) {
                intValue = builder.iconDefaultColor.intValue();
            } else {
                intValue = colorAccent;
            }
            this.iconDefaultColor = intValue;
            this.iconDisabledColor = builder.iconDisabledColor != null ? builder.iconDisabledColor.intValue() : ColorHelper.disableColor(this.iconDefaultColor);
            this.iconPressedColor = builder.iconPressedColor != null ? builder.iconPressedColor.intValue() : this.iconDefaultColor;
            if (builder.iconSelector != null) {
                resourceId2 = builder.iconSelector.intValue();
            }
            this.iconSelector = resourceId2;
            this.showIconClose = builder.showIconClose != null ? builder.showIconClose.booleanValue() : true;
            this.disableIconClose = builder.disableIconClose != null ? builder.disableIconClose.booleanValue() : false;
            this.showIconBack = builder.showIconBack != null ? builder.showIconBack.booleanValue() : true;
            this.disableIconBack = builder.disableIconBack != null ? builder.disableIconBack.booleanValue() : false;
            this.showIconForward = builder.showIconForward != null ? builder.showIconForward.booleanValue() : true;
            this.disableIconForward = builder.disableIconForward != null ? builder.disableIconForward.booleanValue() : false;
            this.showIconMenu = builder.showIconMenu != null ? builder.showIconMenu.booleanValue() : true;
            this.disableIconMenu = builder.disableIconMenu != null ? builder.disableIconMenu.booleanValue() : false;
            this.showSwipeRefreshLayout = builder.showSwipeRefreshLayout != null ? builder.showSwipeRefreshLayout.booleanValue() : true;
            if (builder.swipeRefreshColor != null) {
                intValue = builder.swipeRefreshColor.intValue();
            } else {
                intValue = colorAccent;
            }
            this.swipeRefreshColor = intValue;
            if (builder.swipeRefreshColors != null) {
                int[] colors = new int[builder.swipeRefreshColors.length];
                for (int i = 0; i < builder.swipeRefreshColors.length; i++) {
                    colors[i] = builder.swipeRefreshColors[i].intValue();
                }
                this.swipeRefreshColors = colors;
            }
            this.showDivider = builder.showDivider != null ? builder.showDivider.booleanValue() : true;
            this.gradientDivider = builder.gradientDivider != null ? builder.gradientDivider.booleanValue() : true;
            this.dividerColor = builder.dividerColor != null ? builder.dividerColor.intValue() : ContextCompat.getColor(this, C2332R.color.finestBlack10);
            this.dividerHeight = builder.dividerHeight != null ? builder.dividerHeight.floatValue() : getResources().getDimension(C2332R.dimen.defaultDividerHeight);
            this.showProgressBar = builder.showProgressBar != null ? builder.showProgressBar.booleanValue() : true;
            if (builder.progressBarColor != null) {
                colorAccent = builder.progressBarColor.intValue();
            }
            this.progressBarColor = colorAccent;
            this.progressBarHeight = builder.progressBarHeight != null ? builder.progressBarHeight.floatValue() : getResources().getDimension(C2332R.dimen.defaultProgressBarHeight);
            this.progressBarPosition = builder.progressBarPosition != null ? builder.progressBarPosition : Position.BOTTON_OF_TOOLBAR;
            this.titleDefault = builder.titleDefault;
            this.updateTitleFromHtml = builder.updateTitleFromHtml != null ? builder.updateTitleFromHtml.booleanValue() : true;
            this.titleSize = builder.titleSize != null ? builder.titleSize.floatValue() : getResources().getDimension(C2332R.dimen.defaultTitleSize);
            this.titleFont = builder.titleFont != null ? builder.titleFont : "Roboto-Medium.ttf";
            if (builder.titleColor != null) {
                color = builder.titleColor.intValue();
            }
            this.titleColor = color;
            this.showUrl = builder.showUrl != null ? builder.showUrl.booleanValue() : true;
            this.urlSize = builder.urlSize != null ? builder.urlSize.floatValue() : getResources().getDimension(C2332R.dimen.defaultUrlSize);
            this.urlFont = builder.urlFont != null ? builder.urlFont : "Roboto-Regular.ttf";
            if (builder.urlColor != null) {
                color2 = builder.urlColor.intValue();
            }
            this.urlColor = color2;
            this.menuColor = builder.menuColor != null ? builder.menuColor.intValue() : ContextCompat.getColor(this, C2332R.color.finestWhite);
            this.menuDropShadowColor = builder.menuDropShadowColor != null ? builder.menuDropShadowColor.intValue() : ContextCompat.getColor(this, C2332R.color.finestBlack10);
            this.menuDropShadowSize = builder.menuDropShadowSize != null ? builder.menuDropShadowSize.floatValue() : getResources().getDimension(C2332R.dimen.defaultMenuDropShadowSize);
            if (builder.menuSelector != null) {
                resourceId = builder.menuSelector.intValue();
            }
            this.menuSelector = resourceId;
            this.menuTextSize = builder.menuTextSize != null ? builder.menuTextSize.floatValue() : getResources().getDimension(C2332R.dimen.defaultMenuTextSize);
            this.menuTextFont = builder.menuTextFont != null ? builder.menuTextFont : "Roboto-Regular.ttf";
            this.menuTextColor = builder.menuTextColor != null ? builder.menuTextColor.intValue() : ContextCompat.getColor(this, C2332R.color.finestBlack);
            this.menuTextGravity = builder.menuTextGravity != null ? builder.menuTextGravity.intValue() : 8388627;
            float floatValue = builder.menuTextPaddingLeft != null ? builder.menuTextPaddingLeft.floatValue() : this.rtl ? getResources().getDimension(C2332R.dimen.defaultMenuTextPaddingRight) : getResources().getDimension(C2332R.dimen.defaultMenuTextPaddingLeft);
            this.menuTextPaddingLeft = floatValue;
            floatValue = builder.menuTextPaddingRight != null ? builder.menuTextPaddingRight.floatValue() : this.rtl ? getResources().getDimension(C2332R.dimen.defaultMenuTextPaddingLeft) : getResources().getDimension(C2332R.dimen.defaultMenuTextPaddingRight);
            this.menuTextPaddingRight = floatValue;
            this.showMenuRefresh = builder.showMenuRefresh != null ? builder.showMenuRefresh.booleanValue() : true;
            this.stringResRefresh = builder.stringResRefresh != null ? builder.stringResRefresh.intValue() : C2332R.string.refresh;
            this.showMenuFind = builder.showMenuFind != null ? builder.showMenuFind.booleanValue() : false;
            this.stringResFind = builder.stringResFind != null ? builder.stringResFind.intValue() : C2332R.string.find;
            this.showMenuShareVia = builder.showMenuShareVia != null ? builder.showMenuShareVia.booleanValue() : true;
            this.stringResShareVia = builder.stringResShareVia != null ? builder.stringResShareVia.intValue() : C2332R.string.share_via;
            this.showMenuCopyLink = builder.showMenuCopyLink != null ? builder.showMenuCopyLink.booleanValue() : true;
            this.stringResCopyLink = builder.stringResCopyLink != null ? builder.stringResCopyLink.intValue() : C2332R.string.copy_link;
            this.showMenuOpenWith = builder.showMenuOpenWith != null ? builder.showMenuOpenWith.booleanValue() : true;
            this.stringResOpenWith = builder.stringResOpenWith != null ? builder.stringResOpenWith.intValue() : C2332R.string.open_with;
            this.animationCloseEnter = builder.animationCloseEnter != null ? builder.animationCloseEnter.intValue() : C2332R.anim.modal_activity_close_enter;
            this.animationCloseExit = builder.animationCloseExit != null ? builder.animationCloseExit.intValue() : C2332R.anim.modal_activity_close_exit;
            this.backPressToClose = builder.backPressToClose != null ? builder.backPressToClose.booleanValue() : false;
            this.stringResCopiedToClipboard = builder.stringResCopiedToClipboard != null ? builder.stringResCopiedToClipboard.intValue() : C2332R.string.copied_to_clipboard;
            this.webViewSupportZoom = builder.webViewSupportZoom;
            this.webViewMediaPlaybackRequiresUserGesture = builder.webViewMediaPlaybackRequiresUserGesture;
            this.webViewBuiltInZoomControls = Boolean.valueOf(builder.webViewBuiltInZoomControls != null ? builder.webViewBuiltInZoomControls.booleanValue() : false);
            this.webViewDisplayZoomControls = Boolean.valueOf(builder.webViewDisplayZoomControls != null ? builder.webViewDisplayZoomControls.booleanValue() : false);
            this.webViewAllowFileAccess = Boolean.valueOf(builder.webViewAllowFileAccess != null ? builder.webViewAllowFileAccess.booleanValue() : true);
            this.webViewAllowContentAccess = builder.webViewAllowContentAccess;
            this.webViewLoadWithOverviewMode = Boolean.valueOf(builder.webViewLoadWithOverviewMode != null ? builder.webViewLoadWithOverviewMode.booleanValue() : true);
            this.webViewSaveFormData = builder.webViewSaveFormData;
            this.webViewTextZoom = builder.webViewTextZoom;
            this.webViewUseWideViewPort = builder.webViewUseWideViewPort;
            this.webViewSupportMultipleWindows = builder.webViewSupportMultipleWindows;
            this.webViewLayoutAlgorithm = builder.webViewLayoutAlgorithm;
            this.webViewStandardFontFamily = builder.webViewStandardFontFamily;
            this.webViewFixedFontFamily = builder.webViewFixedFontFamily;
            this.webViewSansSerifFontFamily = builder.webViewSansSerifFontFamily;
            this.webViewSerifFontFamily = builder.webViewSerifFontFamily;
            this.webViewCursiveFontFamily = builder.webViewCursiveFontFamily;
            this.webViewFantasyFontFamily = builder.webViewFantasyFontFamily;
            this.webViewMinimumFontSize = builder.webViewMinimumFontSize;
            this.webViewMinimumLogicalFontSize = builder.webViewMinimumLogicalFontSize;
            this.webViewDefaultFontSize = builder.webViewDefaultFontSize;
            this.webViewDefaultFixedFontSize = builder.webViewDefaultFixedFontSize;
            this.webViewLoadsImagesAutomatically = builder.webViewLoadsImagesAutomatically;
            this.webViewBlockNetworkImage = builder.webViewBlockNetworkImage;
            this.webViewBlockNetworkLoads = builder.webViewBlockNetworkLoads;
            this.webViewJavaScriptEnabled = Boolean.valueOf(builder.webViewJavaScriptEnabled != null ? builder.webViewJavaScriptEnabled.booleanValue() : true);
            this.webViewAllowUniversalAccessFromFileURLs = builder.webViewAllowUniversalAccessFromFileURLs;
            this.webViewAllowFileAccessFromFileURLs = builder.webViewAllowFileAccessFromFileURLs;
            this.webViewGeolocationDatabasePath = builder.webViewGeolocationDatabasePath;
            this.webViewAppCacheEnabled = Boolean.valueOf(builder.webViewAppCacheEnabled != null ? builder.webViewAppCacheEnabled.booleanValue() : true);
            this.webViewAppCachePath = builder.webViewAppCachePath;
            this.webViewDatabaseEnabled = builder.webViewDatabaseEnabled;
            this.webViewDomStorageEnabled = Boolean.valueOf(builder.webViewDomStorageEnabled != null ? builder.webViewDomStorageEnabled.booleanValue() : true);
            this.webViewGeolocationEnabled = builder.webViewGeolocationEnabled;
            this.webViewJavaScriptCanOpenWindowsAutomatically = builder.webViewJavaScriptCanOpenWindowsAutomatically;
            this.webViewDefaultTextEncodingName = builder.webViewDefaultTextEncodingName;
            this.webViewUserAgentString = builder.webViewUserAgentString;
            this.webViewNeedInitialFocus = builder.webViewNeedInitialFocus;
            this.webViewCacheMode = builder.webViewCacheMode;
            this.webViewMixedContentMode = builder.webViewMixedContentMode;
            this.webViewOffscreenPreRaster = builder.webViewOffscreenPreRaster;
            this.injectJavaScript = builder.injectJavaScript;
            this.mimeType = builder.mimeType;
            this.encoding = builder.encoding;
            this.data = builder.data;
            this.url = builder.url;
        }
    }

    protected void bindViews() {
        this.coordinatorLayout = (CoordinatorLayout) findViewById(C2332R.id.coordinatorLayout);
        this.appBar = (AppBarLayout) findViewById(C2332R.id.appBar);
        this.toolbar = (Toolbar) findViewById(C2332R.id.toolbar);
        this.toolbarLayout = (RelativeLayout) findViewById(C2332R.id.toolbarLayout);
        this.title = (TextView) findViewById(C2332R.id.title);
        this.urlTv = (TextView) findViewById(C2332R.id.url);
        this.close = (AppCompatImageButton) findViewById(C2332R.id.close);
        this.back = (AppCompatImageButton) findViewById(C2332R.id.back);
        this.forward = (AppCompatImageButton) findViewById(C2332R.id.forward);
        this.more = (AppCompatImageButton) findViewById(C2332R.id.more);
        this.close.setOnClickListener(this);
        this.back.setOnClickListener(this);
        this.forward.setOnClickListener(this);
        this.more.setOnClickListener(this);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(C2332R.id.swipeRefreshLayout);
        this.gradient = findViewById(C2332R.id.gradient);
        this.divider = findViewById(C2332R.id.divider);
        this.progressBar = (ProgressBar) findViewById(C2332R.id.progressBar);
        this.menuLayout = (RelativeLayout) findViewById(C2332R.id.menuLayout);
        this.shadowLayout = (ShadowLayout) findViewById(C2332R.id.shadowLayout);
        this.menuBackground = (LinearLayout) findViewById(C2332R.id.menuBackground);
        this.menuRefresh = (LinearLayout) findViewById(C2332R.id.menuRefresh);
        this.menuRefreshTv = (TextView) findViewById(C2332R.id.menuRefreshTv);
        this.menuFind = (LinearLayout) findViewById(C2332R.id.menuFind);
        this.menuFindTv = (TextView) findViewById(C2332R.id.menuFindTv);
        this.menuShareVia = (LinearLayout) findViewById(C2332R.id.menuShareVia);
        this.menuShareViaTv = (TextView) findViewById(C2332R.id.menuShareViaTv);
        this.menuCopyLink = (LinearLayout) findViewById(C2332R.id.menuCopyLink);
        this.menuCopyLinkTv = (TextView) findViewById(C2332R.id.menuCopyLinkTv);
        this.menuOpenWith = (LinearLayout) findViewById(C2332R.id.menuOpenWith);
        this.menuOpenWithTv = (TextView) findViewById(C2332R.id.menuOpenWithTv);
        this.webLayout = (FrameLayout) findViewById(C2332R.id.webLayout);
        this.webView = new WebView(this);
        this.webLayout.addView(this.webView);
    }

    protected void layoutViews() {
        LayoutParams params;
        setSupportActionBar(this.toolbar);
        float toolbarHeight = getResources().getDimension(C2332R.dimen.toolbarHeight);
        if (!this.gradientDivider) {
            toolbarHeight += this.dividerHeight;
        }
        this.appBar.setLayoutParams(new LayoutParams(-1, (int) toolbarHeight));
        this.coordinatorLayout.requestLayout();
        toolbarHeight = getResources().getDimension(C2332R.dimen.toolbarHeight);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(-1, (int) toolbarHeight);
        this.toolbarLayout.setMinimumHeight((int) toolbarHeight);
        this.toolbarLayout.setLayoutParams(params2);
        this.coordinatorLayout.requestLayout();
        int maxWidth = getMaxWidth();
        this.title.setMaxWidth(maxWidth);
        this.urlTv.setMaxWidth(maxWidth);
        requestCenterLayout();
        updateIcon(this.close, this.rtl ? C2332R.drawable.more : C2332R.drawable.close);
        updateIcon(this.back, C2332R.drawable.back);
        updateIcon(this.forward, C2332R.drawable.forward);
        updateIcon(this.more, this.rtl ? C2332R.drawable.close : C2332R.drawable.more);
        if (this.gradientDivider) {
            params = (LayoutParams) this.gradient.getLayoutParams();
            params.setMargins(0, (int) getResources().getDimension(C2332R.dimen.toolbarHeight), 0, 0);
            this.gradient.setLayoutParams(params);
        }
        this.progressBar.setMinimumHeight((int) this.progressBarHeight);
        params = new LayoutParams(-1, (int) this.progressBarHeight);
        toolbarHeight = getResources().getDimension(C2332R.dimen.toolbarHeight);
        switch (this.progressBarPosition) {
            case TOP_OF_TOOLBAR:
                params.setMargins(0, 0, 0, 0);
                break;
            case BOTTON_OF_TOOLBAR:
                params.setMargins(0, ((int) toolbarHeight) - ((int) this.progressBarHeight), 0, 0);
                break;
            case TOP_OF_WEBVIEW:
                params.setMargins(0, (int) toolbarHeight, 0, 0);
                break;
            case BOTTOM_OF_WEBVIEW:
                params.setMargins(0, DisplayUtil.getHeight() - ((int) this.progressBarHeight), 0, 0);
                break;
        }
        this.progressBar.setLayoutParams(params);
        float webLayoutMinimumHeight = (((float) DisplayUtil.getHeight()) - getResources().getDimension(C2332R.dimen.toolbarHeight)) - ((float) DisplayUtil.getStatusBarHeight());
        if (this.showDivider && !this.gradientDivider) {
            webLayoutMinimumHeight -= this.dividerHeight;
        }
        this.webLayout.setMinimumHeight((int) webLayoutMinimumHeight);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void initializeViews() {
        setSupportActionBar(this.toolbar);
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(this.statusBarColor);
        }
        this.appBar.addOnOffsetChangedListener(this);
        this.toolbar.setBackgroundColor(this.toolbarColor);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) this.toolbar.getLayoutParams();
        params.setScrollFlags(this.toolbarScrollFlags);
        this.toolbar.setLayoutParams(params);
        this.title.setText(this.titleDefault);
        this.title.setTextSize(0, this.titleSize);
        this.title.setTypeface(TypefaceHelper.get(this, this.titleFont));
        this.title.setTextColor(this.titleColor);
        this.urlTv.setVisibility(this.showUrl ? 0 : 8);
        this.urlTv.setText(UrlParser.getHost(this.url));
        this.urlTv.setTextSize(0, this.urlSize);
        this.urlTv.setTypeface(TypefaceHelper.get(this, this.urlFont));
        this.urlTv.setTextColor(this.urlColor);
        requestCenterLayout();
        this.close.setBackgroundResource(this.iconSelector);
        this.back.setBackgroundResource(this.iconSelector);
        this.forward.setBackgroundResource(this.iconSelector);
        this.more.setBackgroundResource(this.iconSelector);
        this.close.setVisibility(this.showIconClose ? 0 : 8);
        this.close.setEnabled(!this.disableIconClose);
        if ((this.showMenuRefresh || this.showMenuFind || this.showMenuShareVia || this.showMenuCopyLink || this.showMenuOpenWith) && this.showIconMenu) {
            this.more.setVisibility(0);
        } else {
            this.more.setVisibility(8);
        }
        this.more.setEnabled(!this.disableIconMenu);
        this.webView.setWebChromeClient(new MyWebChromeClient());
        this.webView.setWebViewClient(new MyWebViewClient());
        this.webView.setDownloadListener(this.downloadListener);
        WebSettings settings = this.webView.getSettings();
        if (this.webViewSupportZoom != null) {
            settings.setSupportZoom(this.webViewSupportZoom.booleanValue());
        }
        if (this.webViewMediaPlaybackRequiresUserGesture != null && VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(this.webViewMediaPlaybackRequiresUserGesture.booleanValue());
        }
        if (this.webViewBuiltInZoomControls != null) {
            settings.setBuiltInZoomControls(this.webViewBuiltInZoomControls.booleanValue());
            if (this.webViewBuiltInZoomControls.booleanValue()) {
                ((ViewGroup) this.webView.getParent()).removeAllViews();
                this.swipeRefreshLayout.addView(this.webView);
                this.swipeRefreshLayout.removeViewAt(1);
            }
        }
        if (this.webViewDisplayZoomControls != null && VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(this.webViewDisplayZoomControls.booleanValue());
        }
        if (this.webViewAllowFileAccess != null) {
            settings.setAllowFileAccess(this.webViewAllowFileAccess.booleanValue());
        }
        if (this.webViewAllowContentAccess != null && VERSION.SDK_INT >= 11) {
            settings.setAllowContentAccess(this.webViewAllowContentAccess.booleanValue());
        }
        if (this.webViewLoadWithOverviewMode != null) {
            settings.setLoadWithOverviewMode(this.webViewLoadWithOverviewMode.booleanValue());
        }
        if (this.webViewSaveFormData != null) {
            settings.setSaveFormData(this.webViewSaveFormData.booleanValue());
        }
        if (this.webViewTextZoom != null && VERSION.SDK_INT >= 14) {
            settings.setTextZoom(this.webViewTextZoom.intValue());
        }
        if (this.webViewUseWideViewPort != null) {
            settings.setUseWideViewPort(this.webViewUseWideViewPort.booleanValue());
        }
        if (this.webViewSupportMultipleWindows != null) {
            settings.setSupportMultipleWindows(this.webViewSupportMultipleWindows.booleanValue());
        }
        if (this.webViewLayoutAlgorithm != null) {
            settings.setLayoutAlgorithm(this.webViewLayoutAlgorithm);
        }
        if (this.webViewStandardFontFamily != null) {
            settings.setStandardFontFamily(this.webViewStandardFontFamily);
        }
        if (this.webViewFixedFontFamily != null) {
            settings.setFixedFontFamily(this.webViewFixedFontFamily);
        }
        if (this.webViewSansSerifFontFamily != null) {
            settings.setSansSerifFontFamily(this.webViewSansSerifFontFamily);
        }
        if (this.webViewSerifFontFamily != null) {
            settings.setSerifFontFamily(this.webViewSerifFontFamily);
        }
        if (this.webViewCursiveFontFamily != null) {
            settings.setCursiveFontFamily(this.webViewCursiveFontFamily);
        }
        if (this.webViewFantasyFontFamily != null) {
            settings.setFantasyFontFamily(this.webViewFantasyFontFamily);
        }
        if (this.webViewMinimumFontSize != null) {
            settings.setMinimumFontSize(this.webViewMinimumFontSize.intValue());
        }
        if (this.webViewMinimumLogicalFontSize != null) {
            settings.setMinimumLogicalFontSize(this.webViewMinimumLogicalFontSize.intValue());
        }
        if (this.webViewDefaultFontSize != null) {
            settings.setDefaultFontSize(this.webViewDefaultFontSize.intValue());
        }
        if (this.webViewDefaultFixedFontSize != null) {
            settings.setDefaultFixedFontSize(this.webViewDefaultFixedFontSize.intValue());
        }
        if (this.webViewLoadsImagesAutomatically != null) {
            settings.setLoadsImagesAutomatically(this.webViewLoadsImagesAutomatically.booleanValue());
        }
        if (this.webViewBlockNetworkImage != null) {
            settings.setBlockNetworkImage(this.webViewBlockNetworkImage.booleanValue());
        }
        if (this.webViewBlockNetworkLoads != null && VERSION.SDK_INT >= 8) {
            settings.setBlockNetworkLoads(this.webViewBlockNetworkLoads.booleanValue());
        }
        if (this.webViewJavaScriptEnabled != null) {
            settings.setJavaScriptEnabled(this.webViewJavaScriptEnabled.booleanValue());
        }
        if (this.webViewAllowUniversalAccessFromFileURLs != null && VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(this.webViewAllowUniversalAccessFromFileURLs.booleanValue());
        }
        if (this.webViewAllowFileAccessFromFileURLs != null && VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(this.webViewAllowFileAccessFromFileURLs.booleanValue());
        }
        if (this.webViewGeolocationDatabasePath != null) {
            settings.setGeolocationDatabasePath(this.webViewGeolocationDatabasePath);
        }
        if (this.webViewAppCacheEnabled != null) {
            settings.setAppCacheEnabled(this.webViewAppCacheEnabled.booleanValue());
        }
        if (this.webViewAppCachePath != null) {
            settings.setAppCachePath(this.webViewAppCachePath);
        }
        if (this.webViewDatabaseEnabled != null) {
            settings.setDatabaseEnabled(this.webViewDatabaseEnabled.booleanValue());
        }
        if (this.webViewDomStorageEnabled != null) {
            settings.setDomStorageEnabled(this.webViewDomStorageEnabled.booleanValue());
        }
        if (this.webViewGeolocationEnabled != null) {
            settings.setGeolocationEnabled(this.webViewGeolocationEnabled.booleanValue());
        }
        if (this.webViewJavaScriptCanOpenWindowsAutomatically != null) {
            settings.setJavaScriptCanOpenWindowsAutomatically(this.webViewJavaScriptCanOpenWindowsAutomatically.booleanValue());
        }
        if (this.webViewDefaultTextEncodingName != null) {
            settings.setDefaultTextEncodingName(this.webViewDefaultTextEncodingName);
        }
        if (this.webViewUserAgentString != null) {
            settings.setUserAgentString(this.webViewUserAgentString);
        }
        if (this.webViewNeedInitialFocus != null) {
            settings.setNeedInitialFocus(this.webViewNeedInitialFocus.booleanValue());
        }
        if (this.webViewCacheMode != null) {
            settings.setCacheMode(this.webViewCacheMode.intValue());
        }
        if (this.webViewMixedContentMode != null && VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(this.webViewMixedContentMode.intValue());
        }
        if (this.webViewOffscreenPreRaster != null && VERSION.SDK_INT >= 23) {
            settings.setOffscreenPreRaster(this.webViewOffscreenPreRaster.booleanValue());
        }
        if (this.data != null) {
            this.webView.loadData(this.data, this.mimeType, this.encoding);
        } else if (this.url != null) {
            this.webView.loadUrl(this.url);
        }
        this.swipeRefreshLayout.setEnabled(this.showSwipeRefreshLayout);
        if (this.showSwipeRefreshLayout) {
            this.swipeRefreshLayout.post(new C23241());
        }
        if (this.swipeRefreshColors == null) {
            this.swipeRefreshLayout.setColorSchemeColors(new int[]{this.swipeRefreshColor});
        } else {
            this.swipeRefreshLayout.setColorSchemeColors(this.swipeRefreshColors);
        }
        this.swipeRefreshLayout.setOnRefreshListener(new C23252());
        View view = this.gradient;
        int i = (this.showDivider && this.gradientDivider) ? 0 : 8;
        view.setVisibility(i);
        view = this.divider;
        i = (!this.showDivider || this.gradientDivider) ? 8 : 0;
        view.setVisibility(i);
        if (this.gradientDivider) {
            ViewUtil.setBackground(this.gradient, new BitmapDrawable(getResources(), BitmapHelper.getGradientBitmap(DisplayUtil.getWidth(), (int) this.dividerHeight, this.dividerColor)));
            LayoutParams params2 = (LayoutParams) this.gradient.getLayoutParams();
            params2.height = (int) this.dividerHeight;
            this.gradient.setLayoutParams(params2);
        } else {
            this.divider.setBackgroundColor(this.dividerColor);
            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) this.divider.getLayoutParams();
            params3.height = (int) this.dividerHeight;
            this.divider.setLayoutParams(params3);
        }
        this.progressBar.setVisibility(this.showProgressBar ? 0 : 8);
        this.progressBar.getProgressDrawable().setColorFilter(this.progressBarColor, Mode.SRC_IN);
        this.progressBar.setMinimumHeight((int) this.progressBarHeight);
        RelativeLayout.LayoutParams params4 = new LayoutParams(-1, (int) this.progressBarHeight);
        float toolbarHeight = getResources().getDimension(C2332R.dimen.toolbarHeight);
        switch (this.progressBarPosition) {
            case TOP_OF_TOOLBAR:
                params4.setMargins(0, 0, 0, 0);
                break;
            case BOTTON_OF_TOOLBAR:
                params4.setMargins(0, ((int) toolbarHeight) - ((int) this.progressBarHeight), 0, 0);
                break;
            case TOP_OF_WEBVIEW:
                params4.setMargins(0, (int) toolbarHeight, 0, 0);
                break;
            case BOTTOM_OF_WEBVIEW:
                params4.setMargins(0, DisplayUtil.getHeight() - ((int) this.progressBarHeight), 0, 0);
                break;
        }
        this.progressBar.setLayoutParams(params4);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(getResources().getDimension(C2332R.dimen.defaultMenuCornerRadius));
        drawable.setColor(this.menuColor);
        if (VERSION.SDK_INT >= 16) {
            this.menuBackground.setBackground(drawable);
        } else {
            this.menuBackground.setBackgroundDrawable(drawable);
        }
        this.shadowLayout.setShadowColor(this.menuDropShadowColor);
        this.shadowLayout.setShadowSize(this.menuDropShadowSize);
        params4 = new RelativeLayout.LayoutParams(-2, -2);
        int margin = (int) (getResources().getDimension(C2332R.dimen.defaultMenuLayoutMargin) - this.menuDropShadowSize);
        params4.setMargins(0, margin, margin, 0);
        params4.addRule(10);
        params4.addRule(this.rtl ? 9 : 11);
        this.shadowLayout.setLayoutParams(params4);
        this.menuRefresh.setVisibility(this.showMenuRefresh ? 0 : 8);
        this.menuRefresh.setBackgroundResource(this.menuSelector);
        this.menuRefresh.setGravity(this.menuTextGravity);
        this.menuRefreshTv.setText(this.stringResRefresh);
        this.menuRefreshTv.setTextSize(0, this.menuTextSize);
        this.menuRefreshTv.setTypeface(TypefaceHelper.get(this, this.menuTextFont));
        this.menuRefreshTv.setTextColor(this.menuTextColor);
        this.menuRefreshTv.setPadding((int) this.menuTextPaddingLeft, 0, (int) this.menuTextPaddingRight, 0);
        this.menuFind.setVisibility(this.showMenuFind ? 0 : 8);
        this.menuFind.setBackgroundResource(this.menuSelector);
        this.menuFind.setGravity(this.menuTextGravity);
        this.menuFindTv.setText(this.stringResFind);
        this.menuFindTv.setTextSize(0, this.menuTextSize);
        this.menuFindTv.setTypeface(TypefaceHelper.get(this, this.menuTextFont));
        this.menuFindTv.setTextColor(this.menuTextColor);
        this.menuFindTv.setPadding((int) this.menuTextPaddingLeft, 0, (int) this.menuTextPaddingRight, 0);
        this.menuShareVia.setVisibility(this.showMenuShareVia ? 0 : 8);
        this.menuShareVia.setBackgroundResource(this.menuSelector);
        this.menuShareVia.setGravity(this.menuTextGravity);
        this.menuShareViaTv.setText(this.stringResShareVia);
        this.menuShareViaTv.setTextSize(0, this.menuTextSize);
        this.menuShareViaTv.setTypeface(TypefaceHelper.get(this, this.menuTextFont));
        this.menuShareViaTv.setTextColor(this.menuTextColor);
        this.menuShareViaTv.setPadding((int) this.menuTextPaddingLeft, 0, (int) this.menuTextPaddingRight, 0);
        this.menuCopyLink.setVisibility(this.showMenuCopyLink ? 0 : 8);
        this.menuCopyLink.setBackgroundResource(this.menuSelector);
        this.menuCopyLink.setGravity(this.menuTextGravity);
        this.menuCopyLinkTv.setText(this.stringResCopyLink);
        this.menuCopyLinkTv.setTextSize(0, this.menuTextSize);
        this.menuCopyLinkTv.setTypeface(TypefaceHelper.get(this, this.menuTextFont));
        this.menuCopyLinkTv.setTextColor(this.menuTextColor);
        this.menuCopyLinkTv.setPadding((int) this.menuTextPaddingLeft, 0, (int) this.menuTextPaddingRight, 0);
        this.menuOpenWith.setVisibility(this.showMenuOpenWith ? 0 : 8);
        this.menuOpenWith.setBackgroundResource(this.menuSelector);
        this.menuOpenWith.setGravity(this.menuTextGravity);
        this.menuOpenWithTv.setText(this.stringResOpenWith);
        this.menuOpenWithTv.setTextSize(0, this.menuTextSize);
        this.menuOpenWithTv.setTypeface(TypefaceHelper.get(this, this.menuTextFont));
        this.menuOpenWithTv.setTextColor(this.menuTextColor);
        this.menuOpenWithTv.setPadding((int) this.menuTextPaddingLeft, 0, (int) this.menuTextPaddingRight, 0);
    }

    protected int getMaxWidth() {
        if (this.forward.getVisibility() == 0) {
            return DisplayUtil.getWidth() - UnitConverter.dpToPx(100);
        }
        return DisplayUtil.getWidth() - UnitConverter.dpToPx(52);
    }

    protected void updateIcon(ImageButton icon, @DrawableRes int drawableRes) {
        StateListDrawable states = new StateListDrawable();
        int[] iArr = new int[]{16842919};
        states.addState(iArr, new BitmapDrawable(getResources(), BitmapHelper.getColoredBitmap(this, drawableRes, this.iconPressedColor)));
        iArr = new int[]{-16842910};
        states.addState(iArr, new BitmapDrawable(getResources(), BitmapHelper.getColoredBitmap(this, drawableRes, this.iconDisabledColor)));
        states.addState(new int[0], new BitmapDrawable(getResources(), BitmapHelper.getColoredBitmap(this, drawableRes, this.iconDefaultColor)));
        icon.setImageDrawable(states);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeOptions();
        setContentView(C2332R.layout.finest_web_view);
        bindViews();
        layoutViews();
        initializeViews();
    }

    public void onBackPressed() {
        if (this.menuLayout.getVisibility() == 0) {
            hideMenu();
        } else if (this.backPressToClose || !this.webView.canGoBack()) {
            close();
        } else {
            this.webView.goBack();
        }
    }

    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == C2332R.id.close) {
            if (this.rtl) {
                showMenu();
            } else {
                close();
            }
        } else if (viewId == C2332R.id.back) {
            if (this.rtl) {
                this.webView.goForward();
            } else {
                this.webView.goBack();
            }
        } else if (viewId == C2332R.id.forward) {
            if (this.rtl) {
                this.webView.goBack();
            } else {
                this.webView.goForward();
            }
        } else if (viewId == C2332R.id.more) {
            if (this.rtl) {
                close();
            } else {
                showMenu();
            }
        } else if (viewId == C2332R.id.menuLayout) {
            hideMenu();
        } else if (viewId == C2332R.id.menuRefresh) {
            this.webView.reload();
            hideMenu();
        } else if (viewId == C2332R.id.menuFind) {
            if (VERSION.SDK_INT >= 11) {
                this.webView.showFindDialog("", true);
            }
            hideMenu();
        } else if (viewId == C2332R.id.menuShareVia) {
            Intent sendIntent = new Intent();
            sendIntent.setAction("android.intent.action.SEND");
            sendIntent.putExtra("android.intent.extra.TEXT", this.webView.getUrl());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getString(this.stringResShareVia)));
            hideMenu();
        } else if (viewId == C2332R.id.menuCopyLink) {
            ClipboardManagerUtil.setText(this.webView.getUrl());
            Snackbar snackbar = Snackbar.make(this.coordinatorLayout, getString(this.stringResCopiedToClipboard), 0);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(this.toolbarColor);
            if (snackbarView instanceof ViewGroup) {
                updateChildTextView((ViewGroup) snackbarView);
            }
            snackbar.show();
            hideMenu();
        } else if (viewId == C2332R.id.menuOpenWith) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.webView.getUrl())));
            hideMenu();
        }
    }

    protected void updateChildTextView(ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() != 0) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setTextColor(this.titleColor);
                    textView.setTypeface(TypefaceHelper.get(this, this.titleFont));
                    textView.setLineSpacing(0.0f, 1.1f);
                    textView.setIncludeFontPadding(false);
                }
                if (view instanceof ViewGroup) {
                    updateChildTextView((ViewGroup) view);
                }
            }
        }
    }

    protected void showMenu() {
        this.menuLayout.setVisibility(0);
        this.shadowLayout.startAnimation(AnimationUtils.loadAnimation(this, C2332R.anim.popup_flyout_show));
    }

    protected void hideMenu() {
        Animation popupAnim = AnimationUtils.loadAnimation(this, C2332R.anim.popup_flyout_hide);
        this.shadowLayout.startAnimation(popupAnim);
        popupAnim.setAnimationListener(new C23263());
    }

    protected void close() {
        super.onBackPressed();
        overridePendingTransition(this.animationCloseEnter, this.animationCloseExit);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (this.toolbarScrollFlags != 0) {
            ViewHelper.setTranslationY(this.gradient, (float) verticalOffset);
            ViewHelper.setAlpha(this.gradient, FlexItem.FLEX_SHRINK_DEFAULT - (((float) Math.abs(verticalOffset)) / ((float) appBarLayout.getTotalScrollRange())));
            switch (this.progressBarPosition) {
                case BOTTON_OF_TOOLBAR:
                    ViewHelper.setTranslationY(this.progressBar, Math.max((float) verticalOffset, this.progressBarHeight - ((float) appBarLayout.getTotalScrollRange())));
                    break;
                case TOP_OF_WEBVIEW:
                    ViewHelper.setTranslationY(this.progressBar, (float) verticalOffset);
                    break;
            }
            if (this.menuLayout.getVisibility() == 0 && VERSION.SDK_INT >= 11) {
                ViewHelper.setTranslationY(this.menuLayout, Math.max((float) verticalOffset, -getResources().getDimension(C2332R.dimen.defaultMenuLayoutMargin)));
            }
        }
    }

    protected void requestCenterLayout() {
        int maxWidth;
        if (this.webView.canGoBack() || this.webView.canGoForward()) {
            maxWidth = DisplayUtil.getWidth() - (UnitConverter.dpToPx(48) * 4);
        } else {
            maxWidth = DisplayUtil.getWidth() - (UnitConverter.dpToPx(48) * 2);
        }
        this.title.setMaxWidth(maxWidth);
        this.urlTv.setMaxWidth(maxWidth);
        this.title.requestLayout();
        this.urlTv.requestLayout();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 2) {
            layoutViews();
        } else if (newConfig.orientation == 1) {
            layoutViews();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        BroadCastManager.unregister(this, this.key);
        if (this.webView != null) {
            if (APILevel.require(11)) {
                this.webView.onPause();
            }
            destroyWebView();
        }
    }

    private void destroyWebView() {
        new Handler().postDelayed(new C23285(), ViewConfiguration.getZoomControlsTimeout() + 1000);
    }
}
