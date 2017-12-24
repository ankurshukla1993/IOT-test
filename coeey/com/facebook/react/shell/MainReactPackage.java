package com.facebook.react.shell;

import android.preference.PreferenceManager;
import com.facebook.react.LazyReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.flat.FlatARTSurfaceViewManager;
import com.facebook.react.flat.RCTImageViewManager;
import com.facebook.react.flat.RCTModalHostManager;
import com.facebook.react.flat.RCTRawTextManager;
import com.facebook.react.flat.RCTTextInlineImageManager;
import com.facebook.react.flat.RCTTextInputManager;
import com.facebook.react.flat.RCTTextManager;
import com.facebook.react.flat.RCTViewManager;
import com.facebook.react.flat.RCTViewPagerManager;
import com.facebook.react.flat.RCTVirtualTextManager;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.picker.ReactDialogPickerManager;
import com.facebook.react.views.picker.ReactDropdownPickerManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.slider.ReactSliderManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.toolbar.ReactToolbarManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.facebook.react.views.webview.ReactWebViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;

public class MainReactPackage extends LazyReactPackage {
    private MainPackageConfig mConfig;

    public MainReactPackage(MainPackageConfig config) {
        this.mConfig = config;
    }

    public List<ModuleSpec> getNativeModules(final ReactApplicationContext context) {
        return Arrays.asList(new ModuleSpec[]{new ModuleSpec(AppStateModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new AppStateModule(context);
            }
        }), new ModuleSpec(AsyncStorageModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new AsyncStorageModule(context);
            }
        }), new ModuleSpec(CameraRollManager.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new CameraRollManager(context);
            }
        }), new ModuleSpec(ClipboardModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ClipboardModule(context);
            }
        }), new ModuleSpec(DatePickerDialogModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new DatePickerDialogModule(context);
            }
        }), new ModuleSpec(DialogModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new DialogModule(context);
            }
        }), new ModuleSpec(FrescoModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new FrescoModule(context, MainReactPackage.this.mConfig != null ? MainReactPackage.this.mConfig.getFrescoConfig() : null);
            }
        }), new ModuleSpec(I18nManagerModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new I18nManagerModule(context);
            }
        }), new ModuleSpec(ImageEditingManager.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ImageEditingManager(context);
            }
        }), new ModuleSpec(ImageLoaderModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ImageLoaderModule(context);
            }
        }), new ModuleSpec(ImageStoreManager.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ImageStoreManager(context);
            }
        }), new ModuleSpec(IntentModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new IntentModule(context);
            }
        }), new ModuleSpec(LocationModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new LocationModule(context);
            }
        }), new ModuleSpec(NativeAnimatedModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new NativeAnimatedModule(context);
            }
        }), new ModuleSpec(NetworkingModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new NetworkingModule(context);
            }
        }), new ModuleSpec(NetInfoModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new NetInfoModule(context);
            }
        }), new ModuleSpec(PermissionsModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new PermissionsModule(context);
            }
        }), new ModuleSpec(ShareModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ShareModule(context);
            }
        }), new ModuleSpec(StatusBarModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new StatusBarModule(context);
            }
        }), new ModuleSpec(TimePickerDialogModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new TimePickerDialogModule(context);
            }
        }), new ModuleSpec(ToastModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ToastModule(context);
            }
        }), new ModuleSpec(VibrationModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new VibrationModule(context);
            }
        }), new ModuleSpec(WebSocketModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new WebSocketModule(context);
            }
        })});
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        List<ViewManager> viewManagers = new ArrayList();
        viewManagers.add(ARTRenderableViewManager.createARTGroupViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTShapeViewManager());
        viewManagers.add(ARTRenderableViewManager.createARTTextViewManager());
        viewManagers.add(new ARTSurfaceViewManager());
        viewManagers.add(new ReactDialogPickerManager());
        viewManagers.add(new ReactDrawerLayoutManager());
        viewManagers.add(new ReactDropdownPickerManager());
        viewManagers.add(new ReactHorizontalScrollViewManager());
        viewManagers.add(new ReactImageManager());
        viewManagers.add(new ReactModalHostManager());
        viewManagers.add(new ReactProgressBarViewManager());
        viewManagers.add(new ReactRawTextManager());
        viewManagers.add(new ReactScrollViewManager());
        viewManagers.add(new ReactSliderManager());
        viewManagers.add(new ReactSwitchManager());
        viewManagers.add(new FrescoBasedReactTextInlineImageViewManager());
        viewManagers.add(new ReactTextInputManager());
        viewManagers.add(new ReactTextViewManager());
        viewManagers.add(new ReactToolbarManager());
        viewManagers.add(new ReactViewManager());
        viewManagers.add(new ReactViewPagerManager());
        viewManagers.add(new ReactVirtualTextViewManager());
        viewManagers.add(new ReactWebViewManager());
        viewManagers.add(new SwipeRefreshLayoutManager());
        if (PreferenceManager.getDefaultSharedPreferences(reactContext).getBoolean("flat_uiimplementation", false)) {
            viewManagers.addAll(Arrays.asList(new ViewManager[]{new RCTViewManager(), new RCTTextManager(), new RCTRawTextManager(), new RCTVirtualTextManager(), new RCTTextInlineImageManager(), new RCTImageViewManager(), new RCTTextInputManager(), new RCTViewPagerManager(), new FlatARTSurfaceViewManager(), new RCTModalHostManager()}));
        }
        return viewManagers;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return LazyReactPackage.getReactModuleInfoProviderViaReflection(this);
    }
}
