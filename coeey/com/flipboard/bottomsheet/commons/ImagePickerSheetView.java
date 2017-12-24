package com.flipboard.bottomsheet.commons;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import flipboard.bottomsheet.commons.C0664R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ViewConstructor"})
public class ImagePickerSheetView extends FrameLayout {
    protected Adapter adapter;
    protected Drawable cameraDrawable = null;
    private int columnWidthDp = 100;
    protected ImageProvider imageProvider;
    protected int maxItems = 25;
    protected final int originalGridPaddingTop;
    protected Drawable pickerDrawable = null;
    protected boolean showCameraOption = true;
    protected boolean showPickerOption = true;
    protected final int spacing;
    protected int thumbnailSize;
    protected final GridView tileGrid;
    protected String title;
    protected final TextView titleView;

    private class Adapter extends BaseAdapter {
        final LayoutInflater inflater;
        private final ContentResolver resolver;
        private List<ImagePickerTile> tiles = new ArrayList();

        public Adapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            if (ImagePickerSheetView.this.showCameraOption) {
                this.tiles.add(new ImagePickerTile(2));
            }
            if (ImagePickerSheetView.this.showPickerOption) {
                this.tiles.add(new ImagePickerTile(3));
            }
            String[] projection = new String[]{"_id", "_data", "bucket_display_name", "datetaken", "mime_type"};
            this.resolver = context.getContentResolver();
            Cursor cursor = this.resolver.query(Media.EXTERNAL_CONTENT_URI, projection, null, null, "datetaken DESC");
            if (cursor != null) {
                int count = 0;
                while (cursor.moveToNext() && count < ImagePickerSheetView.this.maxItems) {
                    File imageFile = new File(cursor.getString(1));
                    if (imageFile.exists()) {
                        this.tiles.add(new ImagePickerTile(Uri.fromFile(imageFile)));
                    }
                    count++;
                }
                cursor.close();
            }
        }

        public int getCount() {
            return this.tiles.size();
        }

        public ImagePickerTile getItem(int position) {
            return (ImagePickerTile) this.tiles.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View recycled, @NonNull ViewGroup parent) {
            ImageView thumb;
            if (recycled == null) {
                thumb = (ImageView) this.inflater.inflate(C0664R.layout.sheet_image_grid_item, parent, false);
            } else {
                thumb = (ImageView) recycled;
            }
            ImagePickerTile tile = (ImagePickerTile) this.tiles.get(position);
            thumb.setMinimumWidth(ImagePickerSheetView.this.thumbnailSize);
            thumb.setMinimumHeight(ImagePickerSheetView.this.thumbnailSize);
            thumb.setMaxHeight(ImagePickerSheetView.this.thumbnailSize);
            thumb.setMaxWidth(ImagePickerSheetView.this.thumbnailSize);
            if (tile.imageUri != null) {
                ImagePickerSheetView.this.imageProvider.onProvideImage(thumb, tile.imageUri, ImagePickerSheetView.this.thumbnailSize);
            } else {
                thumb.setScaleType(ScaleType.CENTER_INSIDE);
                if (tile.isCameraTile()) {
                    thumb.setBackgroundResource(17170444);
                    if (ImagePickerSheetView.this.cameraDrawable == null) {
                        thumb.setImageResource(C0664R.drawable.bottomsheet_camera);
                    } else {
                        thumb.setImageDrawable(ImagePickerSheetView.this.cameraDrawable);
                    }
                } else if (tile.isPickerTile()) {
                    thumb.setBackgroundResource(17170432);
                    if (ImagePickerSheetView.this.pickerDrawable == null) {
                        thumb.setImageResource(C0664R.drawable.bottomsheet_collections);
                    } else {
                        thumb.setImageDrawable(ImagePickerSheetView.this.pickerDrawable);
                    }
                }
            }
            return thumb;
        }
    }

    public static class Builder {
        Drawable cameraDrawable = null;
        Context context;
        ImageProvider imageProvider;
        int maxItems = 25;
        OnTileSelectedListener onTileSelectedListener;
        Drawable pickerDrawable = null;
        boolean showCameraOption = true;
        boolean showPickerOption = true;
        String title = null;

        public Builder(@NonNull Context context) {
            if (VERSION.SDK_INT < 16 || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                this.context = context;
                return;
            }
            throw new RuntimeException("Missing required READ_EXTERNAL_STORAGE permission. Did you remember to request it first?");
        }

        public Builder setMaxItems(int maxItems) {
            this.maxItems = maxItems;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            return setTitle(this.context.getString(title));
        }

        public Builder setTitle(@Nullable String title) {
            this.title = title;
            return this;
        }

        public Builder setOnTileSelectedListener(OnTileSelectedListener onTileSelectedListener) {
            this.onTileSelectedListener = onTileSelectedListener;
            return this;
        }

        public Builder setImageProvider(ImageProvider imageProvider) {
            this.imageProvider = imageProvider;
            return this;
        }

        public Builder setShowCameraOption(boolean showCameraOption) {
            this.showCameraOption = showCameraOption;
            return this;
        }

        public Builder setShowPickerOption(boolean showPickerOption) {
            this.showPickerOption = showPickerOption;
            return this;
        }

        public Builder setCameraDrawable(@DrawableRes int resId) {
            return setCameraDrawable(ResourcesCompat.getDrawable(this.context.getResources(), resId, null));
        }

        public Builder setCameraDrawable(@Nullable Drawable cameraDrawable) {
            this.cameraDrawable = cameraDrawable;
            return this;
        }

        public Builder setPickerDrawable(@DrawableRes int resId) {
            return setPickerDrawable(ResourcesCompat.getDrawable(this.context.getResources(), resId, null));
        }

        public Builder setPickerDrawable(Drawable pickerDrawable) {
            this.pickerDrawable = pickerDrawable;
            return this;
        }

        @CheckResult
        public ImagePickerSheetView create() {
            if (this.imageProvider != null) {
                return new ImagePickerSheetView(this);
            }
            throw new IllegalStateException("Must provide an ImageProvider!");
        }
    }

    public static class ImagePickerTile {
        public static final int CAMERA = 2;
        public static final int IMAGE = 1;
        public static final int PICKER = 3;
        protected final Uri imageUri;
        @TileType
        protected final int tileType;

        public @interface SpecialTileType {
        }

        public @interface TileType {
        }

        ImagePickerTile(@SpecialTileType int tileType) {
            this(null, tileType);
        }

        ImagePickerTile(@NonNull Uri imageUri) {
            this(imageUri, 1);
        }

        protected ImagePickerTile(@Nullable Uri imageUri, @TileType int tileType) {
            this.imageUri = imageUri;
            this.tileType = tileType;
        }

        @Nullable
        public Uri getImageUri() {
            return this.imageUri;
        }

        @TileType
        public int getTileType() {
            return this.tileType;
        }

        public boolean isImageTile() {
            return this.tileType == 1;
        }

        public boolean isCameraTile() {
            return this.tileType == 2;
        }

        public boolean isPickerTile() {
            return this.tileType == 3;
        }

        public String toString() {
            if (isImageTile()) {
                return "ImageTile: " + this.imageUri;
            }
            if (isCameraTile()) {
                return "CameraTile";
            }
            if (isPickerTile()) {
                return "PickerTile";
            }
            return "Invalid item";
        }
    }

    public interface ImageProvider {
        void onProvideImage(ImageView imageView, Uri uri, int i);
    }

    public interface OnTileSelectedListener {
        void onTileSelected(ImagePickerTile imagePickerTile);
    }

    protected ImagePickerSheetView(final Builder builder) {
        super(builder.context);
        inflate(getContext(), C0664R.layout.grid_sheet_view, this);
        this.tileGrid = (GridView) findViewById(C0664R.id.grid);
        this.spacing = getResources().getDimensionPixelSize(C0664R.dimen.bottomsheet_image_tile_spacing);
        this.tileGrid.setDrawSelectorOnTop(true);
        this.tileGrid.setVerticalSpacing(this.spacing);
        this.tileGrid.setHorizontalSpacing(this.spacing);
        this.tileGrid.setPadding(this.spacing, 0, this.spacing, 0);
        this.titleView = (TextView) findViewById(C0664R.id.title);
        this.originalGridPaddingTop = this.tileGrid.getPaddingTop();
        setTitle(builder.title);
        if (builder.onTileSelectedListener != null) {
            this.tileGrid.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(@NonNull AdapterView<?> adapterView, @NonNull View view, int position, long id) {
                    builder.onTileSelectedListener.onTileSelected(ImagePickerSheetView.this.adapter.getItem(position));
                }
            });
        }
        this.maxItems = builder.maxItems;
        this.imageProvider = builder.imageProvider;
        this.showCameraOption = builder.showCameraOption;
        this.showPickerOption = builder.showPickerOption;
        this.cameraDrawable = builder.cameraDrawable;
        this.pickerDrawable = builder.pickerDrawable;
        ViewCompat.setElevation(this, (float) Util.dp2px(getContext(), 16.0f));
    }

    public void setTitle(@StringRes int titleRes) {
        setTitle(getResources().getString(titleRes));
    }

    public void setTitle(String title) {
        this.title = title;
        if (TextUtils.isEmpty(title)) {
            this.titleView.setVisibility(8);
            this.tileGrid.setPadding(this.tileGrid.getPaddingLeft(), this.originalGridPaddingTop + this.spacing, this.tileGrid.getPaddingRight(), this.tileGrid.getPaddingBottom());
            return;
        }
        this.titleView.setText(title);
    }

    public void setColumnWidthDp(int columnWidthDp) {
        this.columnWidthDp = columnWidthDp;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adapter = new Adapter(getContext());
        this.tileGrid.setAdapter(this.adapter);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ShadowOutline(w, h));
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int numColumns = (int) (((float) width) / (((float) this.columnWidthDp) * getResources().getDisplayMetrics().density));
        this.thumbnailSize = Math.round(((float) (width - ((numColumns - 1) * this.spacing))) / 3.0f);
        this.tileGrid.setNumColumns(numColumns);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
