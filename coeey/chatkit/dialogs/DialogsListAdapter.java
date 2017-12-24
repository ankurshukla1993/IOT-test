package chatkit.dialogs;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import chatkit.commons.ImageLoader;
import chatkit.commons.ViewHolder;
import chatkit.commons.models.IDialog;
import chatkit.commons.models.IMessage;
import chatkit.utils.DateFormatter;
import chatkit.utils.DateFormatter.Template;
import com.cooey.maya.R;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DialogsListAdapter<DIALOG extends IDialog> extends Adapter<BaseDialogViewHolder> {
    private DialogListStyle dialogStyle;
    private Class<? extends BaseDialogViewHolder> holderClass;
    private ImageLoader imageLoader;
    private int itemLayoutId;
    private List<DIALOG> items;
    private OnDialogClickListener<DIALOG> onDialogClickListener;
    private OnDialogLongClickListener<DIALOG> onLongItemClickListener;

    public static abstract class BaseDialogViewHolder<DIALOG extends IDialog> extends ViewHolder<DIALOG> {
        protected ImageLoader imageLoader;
        protected OnDialogClickListener onDialogClickListener;
        protected OnDialogLongClickListener onLongItemClickListener;

        public BaseDialogViewHolder(View itemView) {
            super(itemView);
        }

        void setImageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
        }

        void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
            this.onDialogClickListener = onDialogClickListener;
        }

        void setOnLongItemClickListener(OnDialogLongClickListener onLongItemClickListener) {
            this.onLongItemClickListener = onLongItemClickListener;
        }
    }

    public static class DialogViewHolder<DIALOG extends IDialog> extends BaseDialogViewHolder<DIALOG> {
        protected ViewGroup container;
        protected DialogListStyle dialogStyle;
        protected View divider;
        protected ViewGroup dividerContainer;
        protected ImageView ivAvatar;
        protected ImageView ivLastMessageUser;
        protected ViewGroup root;
        protected TextView tvBubble;
        protected TextView tvDate;
        protected TextView tvLastMessage;
        protected TextView tvName;

        public DialogViewHolder(View itemView) {
            super(itemView);
            this.root = (ViewGroup) itemView.findViewById(R.id.dialogRootLayout);
            this.container = (ViewGroup) itemView.findViewById(R.id.dialogContainer);
            this.tvName = (TextView) itemView.findViewById(R.id.dialogName);
            this.tvDate = (TextView) itemView.findViewById(R.id.dialogDate);
            this.tvLastMessage = (TextView) itemView.findViewById(R.id.dialogLastMessage);
            this.tvBubble = (TextView) itemView.findViewById(R.id.dialogUnreadBubble);
            this.ivLastMessageUser = (ImageView) itemView.findViewById(R.id.dialogLastMessageUserAvatar);
            this.ivAvatar = (ImageView) itemView.findViewById(R.id.dialogAvatar);
            this.dividerContainer = (ViewGroup) itemView.findViewById(R.id.dialogDividerContainer);
            this.divider = itemView.findViewById(R.id.dialogDivider);
        }

        private void applyStyle() {
            if (this.dialogStyle != null) {
                this.tvName.setTextSize(0, (float) this.dialogStyle.getDialogTitleTextSize());
                this.tvLastMessage.setTextSize(0, (float) this.dialogStyle.getDialogMessageTextSize());
                this.tvDate.setTextSize(0, (float) this.dialogStyle.getDialogDateSize());
                this.divider.setBackgroundColor(this.dialogStyle.getDialogDividerColor());
                this.dividerContainer.setPadding(this.dialogStyle.getDialogDividerLeftPadding(), 0, this.dialogStyle.getDialogDividerRightPadding(), 0);
                this.ivAvatar.getLayoutParams().width = this.dialogStyle.getDialogAvatarWidth();
                this.ivAvatar.getLayoutParams().height = this.dialogStyle.getDialogAvatarHeight();
                this.ivLastMessageUser.getLayoutParams().width = this.dialogStyle.getDialogMessageAvatarWidth();
                this.ivLastMessageUser.getLayoutParams().height = this.dialogStyle.getDialogMessageAvatarHeight();
                ((GradientDrawable) this.tvBubble.getBackground()).setColor(this.dialogStyle.getDialogUnreadBubbleBackgroundColor());
                this.tvBubble.setVisibility(this.dialogStyle.isDialogDividerEnabled() ? 0 : 8);
                this.tvBubble.setTextSize(0, (float) this.dialogStyle.getDialogUnreadBubbleTextSize());
                this.tvBubble.setTextColor(this.dialogStyle.getDialogUnreadBubbleTextColor());
                this.tvBubble.setTypeface(this.tvBubble.getTypeface(), this.dialogStyle.getDialogUnreadBubbleTextStyle());
            }
        }

        private void applyDefaultStyle() {
            if (this.dialogStyle != null) {
                this.root.setBackgroundColor(this.dialogStyle.getDialogItemBackground());
                this.tvName.setTextColor(this.dialogStyle.getDialogTitleTextColor());
                this.tvName.setTypeface(this.tvName.getTypeface(), this.dialogStyle.getDialogTitleTextStyle());
                this.tvDate.setTextColor(this.dialogStyle.getDialogDateColor());
                this.tvDate.setTypeface(this.tvDate.getTypeface(), this.dialogStyle.getDialogDateStyle());
                this.tvLastMessage.setTextColor(this.dialogStyle.getDialogMessageTextColor());
                this.tvLastMessage.setTypeface(this.tvLastMessage.getTypeface(), this.dialogStyle.getDialogMessageTextStyle());
            }
        }

        private void applyUnreadStyle() {
            if (this.dialogStyle != null) {
                this.root.setBackgroundColor(this.dialogStyle.getDialogUnreadItemBackground());
                this.tvName.setTextColor(this.dialogStyle.getDialogUnreadTitleTextColor());
                this.tvName.setTypeface(this.tvName.getTypeface(), this.dialogStyle.getDialogUnreadTitleTextStyle());
                this.tvDate.setTextColor(this.dialogStyle.getDialogUnreadDateColor());
                this.tvDate.setTypeface(this.tvDate.getTypeface(), this.dialogStyle.getDialogUnreadDateStyle());
                this.tvLastMessage.setTextColor(this.dialogStyle.getDialogUnreadMessageTextColor());
                this.tvLastMessage.setTypeface(this.tvLastMessage.getTypeface(), this.dialogStyle.getDialogUnreadMessageTextStyle());
            }
        }

        public void onBind(final DIALOG dialog) {
            int i = 0;
            if (dialog.getUnreadCount() > 0) {
                applyUnreadStyle();
            } else {
                applyDefaultStyle();
            }
            this.tvName.setText(dialog.getDialogName());
            this.tvDate.setText(getDateString(dialog.getLastMessage().getCreatedAt()));
            if (this.imageLoader != null) {
                this.imageLoader.loadImage(this.ivAvatar, dialog.getDialogPhoto());
            }
            if (this.imageLoader != null) {
                this.imageLoader.loadImage(this.ivLastMessageUser, dialog.getLastMessage().getUser().getAvatar());
            }
            ImageView imageView = this.ivLastMessageUser;
            int i2 = (!this.dialogStyle.isDialogMessageAvatarEnabled() || dialog.getUsers().size() <= 1) ? 8 : 0;
            imageView.setVisibility(i2);
            this.tvLastMessage.setText(dialog.getLastMessage().getText());
            this.tvBubble.setText(String.valueOf(dialog.getUnreadCount()));
            TextView textView = this.tvBubble;
            if (!this.dialogStyle.isDialogUnreadBubbleEnabled() || dialog.getUnreadCount() <= 0) {
                i = 8;
            }
            textView.setVisibility(i);
            if (this.onDialogClickListener != null) {
                this.container.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DialogViewHolder.this.onDialogClickListener.onDialogClick(dialog);
                    }
                });
            }
            if (this.onLongItemClickListener != null) {
                this.container.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        DialogViewHolder.this.onLongItemClickListener.onDialogLongClick(dialog);
                        return true;
                    }
                });
            }
        }

        protected String getDateString(Date date) {
            return DateFormatter.format(date, Template.TIME);
        }

        protected DialogListStyle getDialogStyle() {
            return this.dialogStyle;
        }

        protected void setDialogStyle(DialogListStyle dialogStyle) {
            this.dialogStyle = dialogStyle;
            applyStyle();
        }
    }

    public interface OnDialogClickListener<DIALOG extends IDialog> {
        void onDialogClick(DIALOG dialog);
    }

    public interface OnDialogLongClickListener<DIALOG extends IDialog> {
        void onDialogLongClick(DIALOG dialog);
    }

    public DialogsListAdapter(ImageLoader imageLoader) {
        this(R.layout.item_dialog, DialogViewHolder.class, imageLoader);
    }

    public DialogsListAdapter(@LayoutRes int itemLayoutId, ImageLoader imageLoader) {
        this(itemLayoutId, DialogViewHolder.class, imageLoader);
    }

    public DialogsListAdapter(@LayoutRes int itemLayoutId, Class<? extends BaseDialogViewHolder> holderClass, ImageLoader imageLoader) {
        this.items = new ArrayList();
        this.itemLayoutId = itemLayoutId;
        this.holderClass = holderClass;
        this.imageLoader = imageLoader;
    }

    public void onBindViewHolder(BaseDialogViewHolder holder, int position) {
        holder.setImageLoader(this.imageLoader);
        holder.setOnDialogClickListener(this.onDialogClickListener);
        holder.setOnLongItemClickListener(this.onLongItemClickListener);
        holder.onBind(this.items.get(position));
    }

    public BaseDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.itemLayoutId, parent, false);
        try {
            Constructor<? extends BaseDialogViewHolder> constructor = this.holderClass.getDeclaredConstructor(new Class[]{View.class});
            constructor.setAccessible(true);
            BaseDialogViewHolder baseDialogViewHolder = (BaseDialogViewHolder) constructor.newInstance(new Object[]{v});
            if (!(baseDialogViewHolder instanceof DialogViewHolder)) {
                return baseDialogViewHolder;
            }
            ((DialogViewHolder) baseDialogViewHolder).setDialogStyle(this.dialogStyle);
            return baseDialogViewHolder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void deleteById(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            if (((IDialog) this.items.get(i)).getId().equals(id)) {
                this.items.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void clear() {
        if (this.items != null) {
            this.items.clear();
        }
        notifyDataSetChanged();
    }

    public void setItems(List<DIALOG> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<DIALOG> newItems) {
        if (newItems != null) {
            if (this.items == null) {
                this.items = new ArrayList();
            }
            int curSize = this.items.size();
            this.items.addAll(newItems);
            notifyItemRangeInserted(curSize, this.items.size());
        }
    }

    public void addItem(DIALOG dialog) {
        this.items.add(dialog);
        notifyItemInserted(0);
    }

    public void addItem(int position, DIALOG dialog) {
        this.items.add(position, dialog);
        notifyItemInserted(position);
    }

    public void updateItem(int position, DIALOG item) {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        this.items.set(position, item);
        notifyItemChanged(position);
    }

    public void updateItemById(DIALOG item) {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (((IDialog) this.items.get(i)).getId().equals(item.getId())) {
                this.items.set(i, item);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public boolean updateDialogWithMessage(String dialogId, IMessage message) {
        for (int i = 0; i < this.items.size(); i++) {
            if (((IDialog) this.items.get(i)).getId().equals(dialogId)) {
                ((IDialog) this.items.get(i)).setLastMessage(message);
                notifyItemChanged(i);
                if (i != 0) {
                    Collections.swap(this.items, i, 0);
                    notifyItemMoved(i, 0);
                }
                return true;
            }
        }
        return false;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public OnDialogClickListener getOnDialogClickListener() {
        return this.onDialogClickListener;
    }

    public void setOnDialogClickListener(OnDialogClickListener<DIALOG> onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public OnDialogLongClickListener getOnLongItemClickListener() {
        return this.onLongItemClickListener;
    }

    public void setOnDialogLongClickListener(OnDialogLongClickListener<DIALOG> onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    void setStyle(DialogListStyle dialogStyle) {
        this.dialogStyle = dialogStyle;
    }
}
