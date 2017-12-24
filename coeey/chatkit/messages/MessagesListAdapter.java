package chatkit.messages;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import chatkit.ContentListViewHolder;
import chatkit.Message;
import chatkit.commons.ImageLoader;
import chatkit.commons.ViewHolder;
import chatkit.commons.models.IMessage;
import chatkit.utils.DateFormatter;
import chatkit.utils.DateFormatter.Template;
import com.cooey.maya.R;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MessagesListAdapter<MESSAGE extends IMessage> extends Adapter<ViewHolder> implements OnLoadMoreListener {
    private static final int VIEW_TYPE_CONTENTS = 3;
    private static final int VIEW_TYPE_DATE_HEADER = 2;
    private static final int VIEW_TYPE_INCOMING_MESSAGE = 0;
    private static final int VIEW_TYPE_OUTCOMING_MESSAGE = 1;
    private HoldersConfig holders;
    private ImageLoader imageLoader;
    private boolean isSelectMode;
    private List<Wrapper> items;
    private LayoutManager layoutManager;
    private OnLoadMoreListener loadMoreListener;
    private MessagesListStyle messagesListStyle;
    private OnMessageClickListener<MESSAGE> onMessageClickListener;
    private OnMessageLongClickListener<MESSAGE> onMessageLongClickListener;
    private int selectedItemsCount;
    private SelectionListener selectionListener;
    private String senderId;

    public static abstract class BaseMessageViewHolder<MESSAGE extends IMessage> extends ViewHolder<MESSAGE> {
        protected ImageLoader imageLoader;
        private boolean isSelected;

        public BaseMessageViewHolder(View itemView) {
            super(itemView);
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public ImageLoader getImageLoader() {
            return this.imageLoader;
        }
    }

    interface DefaultMessageViewHolder {
        void applyStyle(MessagesListStyle messagesListStyle);
    }

    public static class DefaultDateHeaderViewHolder extends ViewHolder<Date> implements DefaultMessageViewHolder {
        protected String dateFormat;
        protected TextView text;

        public DefaultDateHeaderViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
        }

        public void onBind(Date date) {
            this.text.setText(DateFormatter.format(date, this.dateFormat));
        }

        public void applyStyle(MessagesListStyle style) {
            this.text.setTextSize(0, (float) style.getDateHeaderTextSize());
            this.text.setTextColor(style.getDateHeaderTextColor());
            this.text.setPadding(style.getDateHeaderPadding(), style.getDateHeaderPadding(), style.getDateHeaderPadding(), style.getDateHeaderPadding());
            this.dateFormat = style.getDateHeaderFormat();
            this.dateFormat = this.dateFormat == null ? Template.STRING_MONTH.get() : this.dateFormat;
        }
    }

    public static class IncomingMessageViewHolder<MESSAGE extends IMessage> extends BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {
        protected ViewGroup bubble;
        protected TextView text;
        protected TextView time;
        protected ImageView userAvatar;

        public IncomingMessageViewHolder(View itemView) {
            super(itemView);
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
            this.time = (TextView) itemView.findViewById(R.id.messageTime);
            this.userAvatar = (ImageView) itemView.findViewById(R.id.messageUserAvatar);
        }

        public void onBind(MESSAGE message) {
            boolean isAvatarExists;
            int i = 0;
            this.bubble.setSelected(isSelected());
            this.text.setText(message.getText());
            this.time.setText(DateFormatter.format(message.getCreatedAt(), Template.TIME));
            if (this.imageLoader == null || message.getUser().getAvatar() == null || message.getUser().getAvatar().isEmpty()) {
                isAvatarExists = false;
            } else {
                isAvatarExists = true;
            }
            ImageView imageView = this.userAvatar;
            if (!isAvatarExists) {
                i = 8;
            }
            imageView.setVisibility(i);
            if (isAvatarExists) {
                this.imageLoader.loadImage(this.userAvatar, message.getUser().getAvatar());
            }
        }

        public void applyStyle(MessagesListStyle style) {
            this.bubble.setPadding(style.getIncomingDefaultBubblePaddingLeft(), style.getIncomingDefaultBubblePaddingTop(), style.getIncomingDefaultBubblePaddingRight(), style.getIncomingDefaultBubblePaddingBottom());
            this.bubble.setBackground(style.getIncomingBubbleDrawable());
            this.text.setTextColor(style.getIncomingTextColor());
            this.text.setTextSize(0, (float) style.getIncomingTextSize());
            this.userAvatar.getLayoutParams().width = style.getIncomingAvatarWidth();
            this.userAvatar.getLayoutParams().height = style.getIncomingAvatarHeight();
            this.time.setTextColor(style.getIncomingTimeTextColor());
            this.time.setTextSize(0, (float) style.getIncomingTimeTextSize());
        }
    }

    private static class DefaultIncomingMessageViewHolder extends IncomingMessageViewHolder<IMessage> {
        public DefaultIncomingMessageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class OutcomingMessageViewHolder<MESSAGE extends IMessage> extends BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {
        protected ViewGroup bubble;
        protected TextView text;
        protected TextView time;
        protected ImageView userAvatar;

        public OutcomingMessageViewHolder(View itemView) {
            super(itemView);
            this.bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
            this.text = (TextView) itemView.findViewById(R.id.messageText);
            this.time = (TextView) itemView.findViewById(R.id.messageTime);
            this.userAvatar = (ImageView) itemView.findViewById(R.id.messageUserAvatar);
        }

        public void onBind(MESSAGE message) {
            int i = 0;
            this.bubble.setSelected(isSelected());
            this.text.setText(message.getText());
            this.time.setText(DateFormatter.format(message.getCreatedAt(), Template.TIME));
            if (this.userAvatar != null) {
                boolean isAvatarExists = (message.getUser().getAvatar() == null || message.getUser().getAvatar().isEmpty()) ? false : true;
                ImageView imageView = this.userAvatar;
                if (!isAvatarExists) {
                    i = 8;
                }
                imageView.setVisibility(i);
                if (isAvatarExists && this.imageLoader != null) {
                    this.imageLoader.loadImage(this.userAvatar, message.getUser().getAvatar());
                }
            }
        }

        public void applyStyle(MessagesListStyle style) {
            this.bubble.setPadding(style.getOutcomingDefaultBubblePaddingLeft(), style.getOutcomingDefaultBubblePaddingTop(), style.getOutcomingDefaultBubblePaddingRight(), style.getOutcomingDefaultBubblePaddingBottom());
            this.bubble.setBackground(style.getOutcomingBubbleDrawable());
            this.text.setTextColor(style.getOutcomingTextColor());
            this.text.setTextSize(0, (float) style.getOutcomingTextSize());
            this.time.setTextColor(style.getOutcomingTimeTextColor());
            this.time.setTextSize(0, (float) style.getOutcomingTimeTextSize());
        }
    }

    private static class DefaultOutcomingMessageViewHolder extends OutcomingMessageViewHolder<IMessage> {
        public DefaultOutcomingMessageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class HoldersConfig {
        private Class<? extends ViewHolder<Date>> dateHeaderHolder = DefaultDateHeaderViewHolder.class;
        private int dateHeaderLayout = R.layout.item_date_header;
        private Class<? extends BaseMessageViewHolder<? extends IMessage>> incomingHolder = DefaultIncomingMessageViewHolder.class;
        private int incomingLayout = R.layout.item_incoming_message;
        private Class<? extends BaseMessageViewHolder<? extends IMessage>> outcomingHolder = DefaultOutcomingMessageViewHolder.class;
        private int outcomingLayout = R.layout.item_outcoming_message;

        public void setIncoming(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, @LayoutRes int layout) {
            this.incomingHolder = holder;
            this.incomingLayout = layout;
        }

        public void setIncomingHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder) {
            this.incomingHolder = holder;
        }

        public void setIncomingLayout(@LayoutRes int layout) {
            this.incomingLayout = layout;
        }

        public void setOutcoming(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder, @LayoutRes int layout) {
            this.outcomingHolder = holder;
            this.outcomingLayout = layout;
        }

        public void setOutcomingHolder(Class<? extends BaseMessageViewHolder<? extends IMessage>> holder) {
            this.outcomingHolder = holder;
        }

        public void setOutcomingLayout(@LayoutRes int layout) {
            this.outcomingLayout = layout;
        }

        public void setDateHeader(Class<? extends ViewHolder<Date>> holder, @LayoutRes int layout) {
            this.dateHeaderHolder = holder;
            this.dateHeaderLayout = layout;
        }

        public void setDateHeaderHolder(Class<? extends ViewHolder<Date>> holder) {
            this.dateHeaderHolder = holder;
        }

        public void setDateHeaderLayout(@LayoutRes int layout) {
            this.dateHeaderLayout = layout;
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int i, int i2);
    }

    public interface OnMessageClickListener<MESSAGE extends IMessage> {
        void onMessageClick(MESSAGE message);
    }

    public interface OnMessageLongClickListener<MESSAGE extends IMessage> {
        void onMessageLongClick(MESSAGE message);
    }

    public interface SelectionListener {
        void onSelectionChanged(int i);
    }

    private class Wrapper<DATA> {
        boolean isSelected;
        private DATA item;

        Wrapper(DATA item) {
            this.item = item;
        }
    }

    public MessagesListAdapter(String senderId, ImageLoader imageLoader) {
        this(senderId, new HoldersConfig(), imageLoader);
    }

    public MessagesListAdapter(String senderId, HoldersConfig holders, ImageLoader imageLoader) {
        this.senderId = senderId;
        this.holders = holders;
        this.imageLoader = imageLoader;
        this.items = new ArrayList();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return getHolder(parent, this.holders.incomingLayout, this.holders.incomingHolder);
            case 1:
                return getHolder(parent, this.holders.outcomingLayout, this.holders.outcomingHolder);
            case 2:
                return getHolder(parent, this.holders.dateHeaderLayout, this.holders.dateHeaderHolder);
            case 3:
                return new ContentListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content, parent, false));
            default:
                return null;
        }
    }

    private <HOLDER extends ViewHolder> ViewHolder getHolder(ViewGroup parent, @LayoutRes int layout, Class<HOLDER> holderClass) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        try {
            Constructor<HOLDER> constructor = holderClass.getDeclaredConstructor(new Class[]{View.class});
            constructor.setAccessible(true);
            ViewHolder holder = (ViewHolder) constructor.newInstance(new Object[]{v});
            if (!(holder instanceof DefaultMessageViewHolder)) {
                return holder;
            }
            ((DefaultMessageViewHolder) holder).applyStyle(this.messagesListStyle);
            return holder;
        } catch (Exception e) {
            return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Wrapper wrapper = (Wrapper) this.items.get(position);
        if (holder instanceof ContentListViewHolder) {
            ((ContentListViewHolder) holder).onBind((Message) wrapper.item);
            return;
        }
        if (wrapper.item instanceof IMessage) {
            ((BaseMessageViewHolder) holder).isSelected = wrapper.isSelected;
            ((BaseMessageViewHolder) holder).imageLoader = this.imageLoader;
            holder.itemView.setOnLongClickListener(getMessageLongClickListener(wrapper));
            holder.itemView.setOnClickListener(getMessageClickListener(wrapper));
        }
        holder.onBind(wrapper.item);
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int position) {
        Wrapper wrapper = (Wrapper) this.items.get(position);
        if (!(wrapper.item instanceof IMessage)) {
            return 2;
        }
        IMessage message = (IMessage) wrapper.item;
        if (message.getContents() != null) {
            return 3;
        }
        String userId = message.getUser().getId();
        if (message.getUser().getId().contentEquals(this.senderId)) {
            return 1;
        }
        return 0;
    }

    public void onLoadMore(int page, int total) {
        if (this.loadMoreListener != null) {
            this.loadMoreListener.onLoadMore(page, total);
        }
    }

    public void addToStart(MESSAGE message, boolean scroll) {
        boolean isNewMessageToday;
        int i = 1;
        if (isPreviousSameDate(0, message.getCreatedAt())) {
            isNewMessageToday = false;
        } else {
            isNewMessageToday = true;
        }
        if (isNewMessageToday) {
            this.items.add(0, new Wrapper(message.getCreatedAt()));
        }
        this.items.add(0, new Wrapper(message));
        if (isNewMessageToday) {
            i = 2;
        }
        notifyItemRangeInserted(0, i);
        if (this.layoutManager != null && scroll) {
            this.layoutManager.scrollToPosition(0);
        }
    }

    public void addToEnd(List<MESSAGE> messages, boolean reverse) {
        if (reverse) {
            Collections.reverse(messages);
        }
        if (!this.items.isEmpty()) {
            int lastItemPosition = this.items.size() - 1;
            if (DateFormatter.isSameDay(((IMessage) messages.get(0)).getCreatedAt(), (Date) ((Wrapper) this.items.get(lastItemPosition)).item)) {
                this.items.remove(lastItemPosition);
                notifyItemRemoved(lastItemPosition);
            }
        }
        int oldSize = this.items.size();
        generateDateHeaders(messages);
        notifyItemRangeInserted(oldSize, this.items.size() - oldSize);
    }

    public void update(MESSAGE message) {
        update(message.getId(), message);
    }

    public void update(String oldId, MESSAGE newMessage) {
        int position = getMessagePositionById(oldId);
        if (position >= 0) {
            this.items.set(position, new Wrapper(newMessage));
            notifyItemChanged(position);
        }
    }

    public void delete(MESSAGE message) {
        deleteById(message.getId());
    }

    public void delete(List<MESSAGE> messages) {
        for (MESSAGE message : messages) {
            int index = getMessagePositionById(message.getId());
            this.items.remove(index);
            notifyItemRemoved(index);
        }
        recountDateHeaders();
    }

    public void deleteById(String id) {
        int index = getMessagePositionById(id);
        if (index >= 0) {
            this.items.remove(index);
            notifyItemRemoved(index);
            recountDateHeaders();
        }
    }

    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            int index = getMessagePositionById(id);
            this.items.remove(index);
            notifyItemRemoved(index);
        }
        recountDateHeaders();
    }

    public void clear() {
        this.items.clear();
    }

    public void enableSelectionMode(SelectionListener selectionListener) {
        if (selectionListener == null) {
            throw new IllegalArgumentException("SelectionListener must not be null. Use `disableSelectionMode()` if you want tp disable selection mode");
        }
        this.selectionListener = selectionListener;
    }

    public void disableSelectionMode() {
        this.selectionListener = null;
        unselectAllItems();
    }

    public ArrayList<MESSAGE> getSelectedMessages() {
        ArrayList<MESSAGE> selectedMessages = new ArrayList();
        for (Wrapper wrapper : this.items) {
            if ((wrapper.item instanceof IMessage) && wrapper.isSelected) {
                selectedMessages.add((IMessage) wrapper.item);
            }
        }
        return selectedMessages;
    }

    public void unselectAllItems() {
        for (int i = 0; i < this.items.size(); i++) {
            Wrapper wrapper = (Wrapper) this.items.get(i);
            if (wrapper.isSelected) {
                wrapper.isSelected = false;
                notifyItemChanged(i);
            }
        }
        this.isSelectMode = false;
        this.selectedItemsCount = 0;
        notifySelectionChanged();
    }

    public void deleteSelectedMessages() {
        delete(getSelectedMessages());
        unselectAllItems();
    }

    public void setOnMessageClickListener(OnMessageClickListener<MESSAGE> onMessageClickListener) {
        this.onMessageClickListener = onMessageClickListener;
    }

    public void setOnMessageLongClickListener(OnMessageLongClickListener<MESSAGE> onMessageLongClickListener) {
        this.onMessageLongClickListener = onMessageLongClickListener;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    private void recountDateHeaders() {
        int i;
        List<Integer> indicesToDelete = new ArrayList();
        for (i = 0; i < this.items.size(); i++) {
            if (((Wrapper) this.items.get(i)).item instanceof Date) {
                if (i == 0) {
                    indicesToDelete.add(Integer.valueOf(i));
                } else if (((Wrapper) this.items.get(i - 1)).item instanceof Date) {
                    indicesToDelete.add(Integer.valueOf(i));
                }
            }
        }
        Collections.reverse(indicesToDelete);
        for (Integer intValue : indicesToDelete) {
            i = intValue.intValue();
            this.items.remove(i);
            notifyItemRemoved(i);
        }
    }

    private void generateDateHeaders(List<MESSAGE> messages) {
        for (int i = 0; i < messages.size(); i++) {
            IMessage message = (IMessage) messages.get(i);
            this.items.add(new Wrapper(message));
            if (messages.size() > i + 1) {
                if (!DateFormatter.isSameDay(message.getCreatedAt(), ((IMessage) messages.get(i + 1)).getCreatedAt())) {
                    this.items.add(new Wrapper(message.getCreatedAt()));
                }
            } else {
                this.items.add(new Wrapper(message.getCreatedAt()));
            }
        }
    }

    private int getMessagePositionById(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            Wrapper wrapper = (Wrapper) this.items.get(i);
            if ((wrapper.item instanceof IMessage) && ((IMessage) wrapper.item).getId().contentEquals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isPreviousSameDate(int position, Date dateToCompare) {
        if (this.items.size() <= position) {
            return false;
        }
        return ((Wrapper) this.items.get(position)).item instanceof IMessage ? DateFormatter.isSameDay(dateToCompare, ((IMessage) ((Wrapper) this.items.get(position)).item).getCreatedAt()) : false;
    }

    private boolean isPreviousSameAuthor(String id, int position) {
        int prevPosition = position + 1;
        if (this.items.size() <= prevPosition) {
            return false;
        }
        return ((Wrapper) this.items.get(prevPosition)).item instanceof IMessage ? ((IMessage) ((Wrapper) this.items.get(prevPosition)).item).getUser().getId().contentEquals(id) : false;
    }

    private void incrementSelectedItemsCount() {
        this.selectedItemsCount++;
        notifySelectionChanged();
    }

    private void decrementSelectedItemsCount() {
        this.selectedItemsCount--;
        this.isSelectMode = this.selectedItemsCount > 0;
        notifySelectionChanged();
    }

    private void notifySelectionChanged() {
        if (this.selectionListener != null) {
            this.selectionListener.onSelectionChanged(this.selectedItemsCount);
        }
    }

    private void notifyMessageClicked(MESSAGE message) {
        if (this.onMessageClickListener != null) {
            this.onMessageClickListener.onMessageClick(message);
        }
    }

    private void notifyMessageLongClicked(MESSAGE message) {
        if (this.onMessageLongClickListener != null) {
            this.onMessageLongClickListener.onMessageLongClick(message);
        }
    }

    private OnClickListener getMessageClickListener(final Wrapper<MESSAGE> wrapper) {
        return new OnClickListener() {
            public void onClick(View view) {
                if (MessagesListAdapter.this.selectionListener == null || !MessagesListAdapter.this.isSelectMode) {
                    MessagesListAdapter.this.notifyMessageClicked((IMessage) wrapper.item);
                    return;
                }
                wrapper.isSelected = !wrapper.isSelected;
                if (wrapper.isSelected) {
                    MessagesListAdapter.this.incrementSelectedItemsCount();
                } else {
                    MessagesListAdapter.this.decrementSelectedItemsCount();
                }
                MessagesListAdapter.this.notifyItemChanged(MessagesListAdapter.this.getMessagePositionById(((IMessage) wrapper.item).getId()));
            }
        };
    }

    private OnLongClickListener getMessageLongClickListener(final Wrapper<MESSAGE> wrapper) {
        return new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (MessagesListAdapter.this.selectionListener == null) {
                    MessagesListAdapter.this.notifyMessageLongClicked((IMessage) wrapper.item);
                } else {
                    MessagesListAdapter.this.isSelectMode = true;
                    view.callOnClick();
                }
                return true;
            }
        };
    }

    void setLayoutManager(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    void setStyle(MessagesListStyle style) {
        this.messagesListStyle = style;
    }
}
