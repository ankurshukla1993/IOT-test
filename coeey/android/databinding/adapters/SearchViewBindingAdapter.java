package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Build.VERSION;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;

@BindingMethods({@BindingMethod(attribute = "android:onQueryTextFocusChange", method = "setOnQueryTextFocusChangeListener", type = SearchView.class), @BindingMethod(attribute = "android:onSearchClick", method = "setOnSearchClickListener", type = SearchView.class), @BindingMethod(attribute = "android:onClose", method = "setOnCloseListener", type = SearchView.class)})
public class SearchViewBindingAdapter {

    @TargetApi(11)
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    @TargetApi(11)
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    @TargetApi(11)
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    @TargetApi(11)
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    @BindingAdapter(requireAll = false, value = {"android:onQueryTextSubmit", "android:onQueryTextChange"})
    public static void setOnQueryTextListener(SearchView view, final OnQueryTextSubmit submit, final OnQueryTextChange change) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnQueryTextListener(null);
        } else {
            view.setOnQueryTextListener(new OnQueryTextListener() {
                public boolean onQueryTextSubmit(String query) {
                    if (submit != null) {
                        return submit.onQueryTextSubmit(query);
                    }
                    return false;
                }

                public boolean onQueryTextChange(String newText) {
                    if (change != null) {
                        return change.onQueryTextChange(newText);
                    }
                    return false;
                }
            });
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSuggestionSelect", "android:onSuggestionClick"})
    public static void setOnSuggestListener(SearchView view, final OnSuggestionSelect submit, final OnSuggestionClick change) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnSuggestionListener(null);
        } else {
            view.setOnSuggestionListener(new OnSuggestionListener() {
                public boolean onSuggestionSelect(int position) {
                    if (submit != null) {
                        return submit.onSuggestionSelect(position);
                    }
                    return false;
                }

                public boolean onSuggestionClick(int position) {
                    if (change != null) {
                        return change.onSuggestionClick(position);
                    }
                    return false;
                }
            });
        }
    }
}
