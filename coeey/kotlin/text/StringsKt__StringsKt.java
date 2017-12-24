package kotlin.text;

import com.facebook.internal.FacebookRequestErrorClassification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CharIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a?\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010 \u001a\u00020\r*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a&\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b$\u001a&\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010&\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010&\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010'\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010(\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010)\u001a\u00020\r*\u00020\u0002H\b\u001a\u000f\u0010*\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u001a\u000f\u0010+\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u001a\r\u0010,\u001a\u00020-*\u00020\u0002H\u0002\u001a&\u0010.\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010.\u001a\u00020\u0006*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010/\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010/\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00100\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u0002\u001a\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u0002\u001a\u0015\u00104\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\f\u001a\u000f\u00105\u001a\u00020\n*\u0004\u0018\u00010\nH\b\u001a\u001c\u00106\u001a\u00020\u0002*\u00020\u00022\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00106\u001a\u00020\n*\u00020\n2\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00109\u001a\u00020\u0002*\u00020\u00022\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00109\u001a\u00020\n*\u00020\n2\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001aG\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000101*\u00020\u00022\u000e\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006H\u0002¢\u0006\u0004\b>\u0010?\u001a=\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000101*\u00020\u00022\u0006\u0010;\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006H\u0002¢\u0006\u0002\b>\u001a4\u0010@\u001a\u00020\r*\u00020\u00022\u0006\u0010A\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010B\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010C\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u0002\u001a\u0012\u0010C\u001a\u00020\n*\u00020\n2\u0006\u0010D\u001a\u00020\u0002\u001a\u001a\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006\u001a\u0012\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u001d\u0010E\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\b\u001a\u0015\u0010E\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u0001H\b\u001a\u0012\u0010G\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010G\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010I\u001a\u00020\u0002\u001a\u001a\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010H\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u0002\u001a\u001a\u0010H\u001a\u00020\n*\u00020\n2\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a+\u0010J\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010K\u001a\u000e\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u00020LH\b\u001a\u001d\u0010J\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\nH\b\u001a$\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010R\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010R\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010S\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010S\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001d\u0010T\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\nH\b\u001a\"\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0002\u001a\u001a\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u0002\u001a%\u0010U\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0002H\b\u001a\u001d\u0010U\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u0002H\b\u001a=\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006¢\u0006\u0002\u0010W\u001a0\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\n\u0010;\u001a\u00020\u0019\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006\u001a%\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010=\u001a\u00020\u0006H\b\u001a=\u0010X\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u00022\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006¢\u0006\u0002\u0010Y\u001a0\u0010X\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u00022\n\u0010;\u001a\u00020\u0019\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006\u001a\u001c\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010[\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u001d\u0010[\u001a\u00020\u0002*\u00020\n2\u0006\u0010\\\u001a\u00020\u00062\u0006\u0010]\u001a\u00020\u0006H\b\u001a\u001f\u0010^\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u0006H\b\u001a\u0012\u0010^\u001a\u00020\n*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u0012\u0010^\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u0001\u001a\u001c\u0010_\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010_\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010`\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010`\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010b\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010b\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\n\u0010c\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010c\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010c\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010c\u001a\u00020\n*\u00020\nH\b\u001a!\u0010c\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010c\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\n\u0010e\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010e\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010e\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010e\u001a\u00020\n*\u00020\nH\b\u001a!\u0010e\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010e\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\n\u0010f\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010f\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010f\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010f\u001a\u00020\n*\u00020\nH\b\u001a!\u0010f\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\b\u001a\u0016\u0010f\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006g"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "chars", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "strings", "", "findLastAnyOf", "hasSurrogatePairAt", "index", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: Strings.kt */
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int startIndex = 0;
        int endIndex = $receiver.length() - 1;
        boolean startFound = false;
        while (startIndex <= endIndex) {
            int index;
            if (startFound) {
                index = endIndex;
            } else {
                index = startIndex;
            }
            boolean match = ((Boolean) predicate.invoke(Character.valueOf($receiver.charAt(index)))).booleanValue();
            if (startFound) {
                if (!match) {
                    break;
                }
                endIndex--;
            } else if (match) {
                startIndex++;
            } else {
                startFound = true;
            }
        }
        return $receiver.subSequence(startIndex, endIndex + 1);
    }

    @NotNull
    public static final String trim(@NotNull String $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharSequence $receiver$iv = $receiver;
        int startIndex$iv = 0;
        int endIndex$iv = $receiver$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv;
            if (startFound$iv) {
                index$iv = endIndex$iv;
            } else {
                index$iv = startIndex$iv;
            }
            boolean match$iv = ((Boolean) predicate.invoke(Character.valueOf($receiver$iv.charAt(index$iv)))).booleanValue();
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        return $receiver$iv.subSequence(startIndex$iv, endIndex$iv + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = $receiver.length();
        for (int index = 0; index < length; index++) {
            if (!((Boolean) predicate.invoke(Character.valueOf($receiver.charAt(index)))).booleanValue()) {
                return $receiver.subSequence(index, $receiver.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Object subSequence;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharSequence $receiver$iv = $receiver;
        int length = $receiver$iv.length();
        for (int index$iv = 0; index$iv < length; index$iv++) {
            if (!((Boolean) predicate.invoke(Character.valueOf($receiver$iv.charAt(index$iv)))).booleanValue()) {
                subSequence = $receiver$iv.subSequence(index$iv, $receiver$iv.length());
                break;
            }
        }
        CharSequence charSequence = "";
        return subSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        int i;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression reversed = RangesKt___RangesKt.reversed((IntProgression) getIndices($receiver));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step > 0) {
            if (first <= last) {
                i = first;
            }
            return "";
        }
        if (first >= last) {
            i = first;
        }
        return "";
        while (((Boolean) predicate.invoke(Character.valueOf($receiver.charAt(i)))).booleanValue()) {
            if (i == last) {
                return "";
            }
            i += step;
        }
        return $receiver.subSequence(0, i + 1).toString();
    }

    @NotNull
    public static final String trimEnd(@NotNull String $receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        int i;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharSequence $receiver$iv = $receiver;
        IntProgression reversed = RangesKt___RangesKt.reversed((IntProgression) getIndices($receiver$iv));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step > 0) {
            if (first <= last) {
                i = first;
            }
            CharSequence charSequence = "";
            return r1.toString();
        }
        if (first >= last) {
            i = first;
        }
        CharSequence charSequence2 = "";
        return r1.toString();
        while (((Boolean) predicate.invoke(Character.valueOf($receiver$iv.charAt(i)))).booleanValue()) {
            if (i == last) {
                CharSequence charSequence22 = "";
                break;
            }
            i += step;
        }
        Object obj = $receiver$iv.subSequence(0, i + 1).toString();
        return obj.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv = $receiver;
        int startIndex$iv = 0;
        int endIndex$iv = $receiver$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv;
            if (startFound$iv) {
                index$iv = endIndex$iv;
            } else {
                index$iv = startIndex$iv;
            }
            boolean match$iv = ArraysKt___ArraysKt.contains(chars, $receiver$iv.charAt(index$iv));
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        return $receiver$iv.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @NotNull
    public static final String trim(@NotNull String $receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv$iv = $receiver;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $receiver$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv;
            if (startFound$iv$iv) {
                index$iv$iv = endIndex$iv$iv;
            } else {
                index$iv$iv = startIndex$iv$iv;
            }
            boolean match$iv$iv = ArraysKt___ArraysKt.contains(chars, $receiver$iv$iv.charAt(index$iv$iv));
            if (startFound$iv$iv) {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            } else if (match$iv$iv) {
                startIndex$iv$iv++;
            } else {
                startFound$iv$iv = true;
            }
        }
        return $receiver$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv = $receiver;
        int length = $receiver$iv.length();
        for (int index$iv = 0; index$iv < length; index$iv++) {
            if (!ArraysKt___ArraysKt.contains(chars, $receiver$iv.charAt(index$iv))) {
                return $receiver$iv.subSequence(index$iv, $receiver$iv.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String $receiver, @NotNull char... chars) {
        Object subSequence;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv$iv = $receiver;
        int length = $receiver$iv$iv.length();
        for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
            if (!ArraysKt___ArraysKt.contains(chars, $receiver$iv$iv.charAt(index$iv$iv))) {
                subSequence = $receiver$iv$iv.subSequence(index$iv$iv, $receiver$iv$iv.length());
                break;
            }
        }
        CharSequence charSequence = "";
        return subSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv = $receiver;
        IntProgression reversed = RangesKt___RangesKt.reversed((IntProgression) getIndices($receiver$iv));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (ArraysKt___ArraysKt.contains(chars, $receiver$iv.charAt(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return $receiver$iv.subSequence(0, first + 1).toString();
        }
        return "";
    }

    @NotNull
    public static final String trimEnd(@NotNull String $receiver, @NotNull char... chars) {
        Object obj;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        CharSequence $receiver$iv$iv = $receiver;
        IntProgression reversed = RangesKt___RangesKt.reversed((IntProgression) getIndices($receiver$iv$iv));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (ArraysKt___ArraysKt.contains(chars, $receiver$iv$iv.charAt(first))) {
                if (first != last) {
                    first += step;
                }
            }
            obj = $receiver$iv$iv.subSequence(0, first + 1).toString();
            return obj.toString();
        }
        CharSequence charSequence = "";
        return obj.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        CharSequence $receiver$iv = $receiver;
        int startIndex$iv = 0;
        int endIndex$iv = $receiver$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv;
            if (startFound$iv) {
                index$iv = endIndex$iv;
            } else {
                index$iv = startIndex$iv;
            }
            boolean match$iv = CharsKt__CharJVMKt.isWhitespace($receiver$iv.charAt(index$iv));
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        return $receiver$iv.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @InlineOnly
    private static final String trim(@NotNull String $receiver) {
        if ($receiver != null) {
            return trim((CharSequence) $receiver).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        CharSequence $receiver$iv = $receiver;
        int length = $receiver$iv.length();
        for (int index$iv = 0; index$iv < length; index$iv++) {
            if (!CharsKt__CharJVMKt.isWhitespace($receiver$iv.charAt(index$iv))) {
                return $receiver$iv.subSequence(index$iv, $receiver$iv.length());
            }
        }
        return "";
    }

    @InlineOnly
    private static final String trimStart(@NotNull String $receiver) {
        if ($receiver != null) {
            return trimStart((CharSequence) $receiver).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        CharSequence $receiver$iv = $receiver;
        IntProgression reversed = RangesKt___RangesKt.reversed((IntProgression) getIndices($receiver$iv));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (CharsKt__CharJVMKt.isWhitespace($receiver$iv.charAt(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return $receiver$iv.subSequence(0, first + 1).toString();
        }
        return "";
    }

    @InlineOnly
    private static final String trimEnd(@NotNull String $receiver) {
        if ($receiver != null) {
            return trimEnd((CharSequence) $receiver).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence $receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        } else if (length <= $receiver.length()) {
            return $receiver.subSequence(0, $receiver.length());
        } else {
            StringBuilder sb = new StringBuilder(length);
            int i = 1;
            int length2 = length - $receiver.length();
            if (1 <= length2) {
                while (true) {
                    sb.append(padChar);
                    if (i == length2) {
                        break;
                    }
                    i++;
                }
            }
            sb.append($receiver);
            return sb;
        }
    }

    @NotNull
    public static final String padStart(@NotNull String $receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return padStart((CharSequence) $receiver, length, padChar).toString();
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence $receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        } else if (length <= $receiver.length()) {
            return $receiver.subSequence(0, $receiver.length());
        } else {
            StringBuilder sb = new StringBuilder(length);
            sb.append($receiver);
            int i = 1;
            int length2 = length - $receiver.length();
            if (1 <= length2) {
                while (true) {
                    sb.append(padChar);
                    if (i == length2) {
                        break;
                    }
                    i++;
                }
            }
            return sb;
        }
    }

    @NotNull
    public static final String padEnd(@NotNull String $receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return padEnd((CharSequence) $receiver, length, padChar).toString();
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable CharSequence $receiver) {
        return $receiver == null || $receiver.length() == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(@NotNull CharSequence $receiver) {
        return $receiver.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(@NotNull CharSequence $receiver) {
        return $receiver.length() > 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(@NotNull CharSequence $receiver) {
        return !StringsKt__StringsJVMKt.isBlank($receiver);
    }

    @InlineOnly
    private static final boolean isNullOrBlank(@Nullable CharSequence $receiver) {
        return $receiver == null || StringsKt__StringsJVMKt.isBlank($receiver);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new StringsKt__StringsKt$iterator$1($receiver);
    }

    @InlineOnly
    private static final String orEmpty(@Nullable String $receiver) {
        return $receiver != null ? $receiver : "";
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new IntRange(0, $receiver.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence $receiver, int index) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return index >= 0 && $receiver.length() - 2 >= index && Character.isHighSurrogate($receiver.charAt(index)) && Character.isLowSurrogate($receiver.charAt(index + 1));
    }

    @NotNull
    public static final String substring(@NotNull String $receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        String substring = $receiver.substring(range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence $receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return $receiver.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    private static final CharSequence subSequence(@NotNull String $receiver, int start, int end) {
        return $receiver.subSequence(start, end);
    }

    @InlineOnly
    private static final String substring(@NotNull CharSequence $receiver, int startIndex, int endIndex) {
        return $receiver.subSequence(startIndex, endIndex).toString();
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ String substring$default(CharSequence $receiver, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            endIndex = $receiver.length();
        }
        return $receiver.subSequence(startIndex, endIndex).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence $receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return $receiver.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1).toString();
    }

    @NotNull
    public static final String substringBefore(@NotNull String $receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringBefore(@NotNull String $receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringAfter(@NotNull String $receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(index + 1, $receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringAfter(@NotNull String $receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(delimiter.length() + index, $receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String $receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String $receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String $receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(index + 1, $receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String $receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        missingDelimiterValue = $receiver.substring(delimiter.length() + index, $receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(missingDelimiterValue, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return missingDelimiterValue;
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence $receiver, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        StringBuilder sb = new StringBuilder();
        sb.append($receiver, 0, startIndex);
        sb.append(replacement);
        sb.append($receiver, endIndex, $receiver.length());
        return sb;
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String $receiver, int startIndex, int endIndex, CharSequence replacement) {
        if ($receiver != null) {
            return replaceRange((CharSequence) $receiver, startIndex, endIndex, replacement).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence $receiver, @NotNull IntRange range, @NotNull CharSequence replacement) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        return replaceRange($receiver, range.getStart().intValue(), range.getEndInclusive().intValue() + 1, replacement);
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String $receiver, IntRange range, CharSequence replacement) {
        if ($receiver != null) {
            return replaceRange((CharSequence) $receiver, range, replacement).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence $receiver, int startIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        } else if (endIndex == startIndex) {
            return $receiver.subSequence(0, $receiver.length());
        } else {
            StringBuilder sb = new StringBuilder($receiver.length() - (endIndex - startIndex));
            sb.append($receiver, 0, startIndex);
            sb.append($receiver, endIndex, $receiver.length());
            return sb;
        }
    }

    @InlineOnly
    private static final String removeRange(@NotNull String $receiver, int startIndex, int endIndex) {
        if ($receiver != null) {
            return removeRange((CharSequence) $receiver, startIndex, endIndex).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence $receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return removeRange($receiver, range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @InlineOnly
    private static final String removeRange(@NotNull String $receiver, IntRange range) {
        if ($receiver != null) {
            return removeRange((CharSequence) $receiver, range).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence $receiver, @NotNull CharSequence prefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (startsWith$default($receiver, prefix, false, 2, null)) {
            return $receiver.subSequence(prefix.length(), $receiver.length());
        }
        return $receiver.subSequence(0, $receiver.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String $receiver, @NotNull CharSequence prefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!startsWith$default((CharSequence) $receiver, prefix, false, 2, null)) {
            return $receiver;
        }
        $receiver = $receiver.substring(prefix.length());
        Intrinsics.checkExpressionValueIsNotNull($receiver, "(this as java.lang.String).substring(startIndex)");
        return $receiver;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence $receiver, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (endsWith$default($receiver, suffix, false, 2, null)) {
            return $receiver.subSequence(0, $receiver.length() - suffix.length());
        }
        return $receiver.subSequence(0, $receiver.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String $receiver, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (!endsWith$default((CharSequence) $receiver, suffix, false, 2, null)) {
            return $receiver;
        }
        $receiver = $receiver.substring(0, $receiver.length() - suffix.length());
        Intrinsics.checkExpressionValueIsNotNull($receiver, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return $receiver;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence $receiver, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if ($receiver.length() >= prefix.length() + suffix.length() && startsWith$default($receiver, prefix, false, 2, null) && endsWith$default($receiver, suffix, false, 2, null)) {
            return $receiver.subSequence(prefix.length(), $receiver.length() - suffix.length());
        }
        return $receiver.subSequence(0, $receiver.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String $receiver, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if ($receiver.length() < prefix.length() + suffix.length() || !startsWith$default((CharSequence) $receiver, prefix, false, 2, null) || !endsWith$default((CharSequence) $receiver, suffix, false, 2, null)) {
            return $receiver;
        }
        $receiver = $receiver.substring(prefix.length(), $receiver.length() - suffix.length());
        Intrinsics.checkExpressionValueIsNotNull($receiver, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return $receiver;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence $receiver, @NotNull CharSequence delimiter) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        return removeSurrounding($receiver, delimiter, delimiter);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String $receiver, @NotNull CharSequence delimiter) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        return removeSurrounding($receiver, delimiter, delimiter);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String $receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        return index == -1 ? missingDelimiterValue : replaceRange((CharSequence) $receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceBefore(@NotNull String $receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        return index == -1 ? missingDelimiterValue : replaceRange((CharSequence) $receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceAfter(@NotNull String $receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return replaceRange((CharSequence) $receiver, index + 1, $receiver.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceAfter(@NotNull String $receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = indexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return replaceRange((CharSequence) $receiver, delimiter.length() + index, $receiver.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String $receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return replaceRange((CharSequence) $receiver, delimiter.length() + index, $receiver.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String $receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return replaceRange((CharSequence) $receiver, index + 1, $receiver.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String $receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        return index == -1 ? missingDelimiterValue : replaceRange((CharSequence) $receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String $receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = lastIndexOf$default((CharSequence) $receiver, delimiter, 0, false, 6, null);
        return index == -1 ? missingDelimiterValue : replaceRange((CharSequence) $receiver, 0, index, (CharSequence) replacement).toString();
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence $receiver, Regex regex, String replacement) {
        return regex.replace($receiver, replacement);
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence $receiver, Regex regex, Function1<? super MatchResult, ? extends CharSequence> transform) {
        return regex.replace($receiver, (Function1) transform);
    }

    @InlineOnly
    private static final String replaceFirst(@NotNull CharSequence $receiver, Regex regex, String replacement) {
        return regex.replaceFirst($receiver, replacement);
    }

    @InlineOnly
    private static final boolean matches(@NotNull CharSequence $receiver, Regex regex) {
        return regex.matches($receiver);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence $receiver, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        if (otherOffset < 0 || thisOffset < 0 || thisOffset > $receiver.length() - length || otherOffset > other.length() - length) {
            return false;
        }
        int i = length - 1;
        if (0 <= i) {
            int index = 0;
            while (CharsKt__CharKt.equals($receiver.charAt(thisOffset + index), other.charAt(otherOffset + index), ignoreCase)) {
                if (index != i) {
                    index++;
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean startsWith(@NotNull CharSequence $receiver, char char_, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.length() > 0 && CharsKt__CharKt.equals($receiver.charAt(0), char_, ignoreCase);
    }

    public static final boolean endsWith(@NotNull CharSequence $receiver, char char_, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.length() > 0 && CharsKt__CharKt.equals($receiver.charAt(getLastIndex($receiver)), char_, ignoreCase);
    }

    public static final boolean startsWith(@NotNull CharSequence $receiver, @NotNull CharSequence prefix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase && ($receiver instanceof String) && (prefix instanceof String)) {
            return StringsKt__StringsJVMKt.startsWith$default((String) $receiver, (String) prefix, false, 2, null);
        }
        return regionMatchesImpl($receiver, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, i, z);
    }

    public static final boolean startsWith(@NotNull CharSequence $receiver, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase && ($receiver instanceof String) && (prefix instanceof String)) {
            return StringsKt__StringsJVMKt.startsWith$default((String) $receiver, (String) prefix, startIndex, false, 4, null);
        }
        return regionMatchesImpl($receiver, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static final boolean endsWith(@NotNull CharSequence $receiver, @NotNull CharSequence suffix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (!ignoreCase && ($receiver instanceof String) && (suffix instanceof String)) {
            return StringsKt__StringsJVMKt.endsWith$default((String) $receiver, (String) suffix, false, 2, null);
        }
        return regionMatchesImpl($receiver, $receiver.length() - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence $receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        int shortestLength = Math.min($receiver.length(), other.length());
        int i = 0;
        while (i < shortestLength && CharsKt__CharKt.equals($receiver.charAt(i), other.charAt(i), ignoreCase)) {
            i++;
        }
        if (hasSurrogatePairAt($receiver, i - 1) || hasSurrogatePairAt(other, i - 1)) {
            i--;
        }
        return $receiver.subSequence(0, i).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonSuffixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence $receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        int thisLength = $receiver.length();
        int otherLength = other.length();
        int shortestLength = Math.min(thisLength, otherLength);
        int i = 0;
        while (i < shortestLength && CharsKt__CharKt.equals($receiver.charAt((thisLength - i) - 1), other.charAt((otherLength - i) - 1), ignoreCase)) {
            i++;
        }
        if (hasSurrogatePairAt($receiver, (thisLength - i) - 1) || hasSurrogatePairAt(other, (otherLength - i) - 1)) {
            i--;
        }
        return $receiver.subSequence(thisLength - i, thisLength).toString();
    }

    private static final Pair<Integer, Character> findAnyOf$StringsKt__StringsKt(@NotNull CharSequence $receiver, char[] chars, int startIndex, boolean ignoreCase, boolean last) {
        if (!ignoreCase && chars.length == 1 && ($receiver instanceof String)) {
            char charR = ArraysKt___ArraysKt.single(chars);
            int index = !last ? ((String) $receiver).indexOf(charR, startIndex) : ((String) $receiver).lastIndexOf(charR, startIndex);
            if (index < 0) {
                return null;
            }
            return TuplesKt.to(Integer.valueOf(index), Character.valueOf(charR));
        }
        IntProgression indices = !last ? new IntRange(RangesKt___RangesKt.coerceAtLeast(startIndex, 0), getLastIndex($receiver)) : RangesKt___RangesKt.downTo(RangesKt___RangesKt.coerceAtMost(startIndex, getLastIndex($receiver)), 0);
        int first = indices.getFirst();
        int last2 = indices.getLast();
        int step = indices.getStep();
        if (step <= 0 ? first >= last2 : first <= last2) {
            while (true) {
                char charAtIndex = $receiver.charAt(first);
                char[] $receiver$iv = chars;
                int matchingCharIndex = 0;
                int length = $receiver$iv.length;
                while (matchingCharIndex < length) {
                    if (CharsKt__CharKt.equals($receiver$iv[matchingCharIndex], charAtIndex, ignoreCase)) {
                        break;
                    }
                    matchingCharIndex++;
                }
                matchingCharIndex = -1;
                if (matchingCharIndex < 0) {
                    if (first == last2) {
                        break;
                    }
                    first += step;
                } else {
                    return TuplesKt.to(Integer.valueOf(first), Character.valueOf(chars[matchingCharIndex]));
                }
            }
        }
        return null;
    }

    public static final int indexOfAny(@NotNull CharSequence $receiver, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Pair findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt($receiver, chars, startIndex, ignoreCase, false);
        if (findAnyOf$StringsKt__StringsKt != null) {
            Integer num = (Integer) findAnyOf$StringsKt__StringsKt.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public static final int lastIndexOfAny(@NotNull CharSequence $receiver, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Pair findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt($receiver, chars, startIndex, ignoreCase, true);
        if (findAnyOf$StringsKt__StringsKt != null) {
            Integer num = (Integer) findAnyOf$StringsKt__StringsKt.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    static /* bridge */ /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int indexOf$StringsKt__StringsKt(@org.jetbrains.annotations.NotNull java.lang.CharSequence r9, java.lang.CharSequence r10, int r11, int r12, boolean r13, boolean r14) {
        /*
        r1 = 0;
        if (r14 != 0) goto L_0x0041;
    L_0x0003:
        r2 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r11, r1);
        r0 = new kotlin.ranges.IntRange;
        r3 = r9.length();
        r3 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r12, r3);
        r0.<init>(r2, r3);
        r0 = (kotlin.ranges.IntProgression) r0;
        r6 = r0;
    L_0x0017:
        r0 = r9 instanceof java.lang.String;
        if (r0 == 0) goto L_0x005a;
    L_0x001b:
        r0 = r10 instanceof java.lang.String;
        if (r0 == 0) goto L_0x005a;
    L_0x001f:
        r3 = r6.getFirst();
        r7 = r6.getLast();
        r8 = r6.getStep();
        if (r8 <= 0) goto L_0x0052;
    L_0x002d:
        if (r3 > r7) goto L_0x0054;
    L_0x002f:
        r0 = r10;
        r0 = (java.lang.String) r0;
        r2 = r9;
        r2 = (java.lang.String) r2;
        r4 = r10.length();
        r5 = r13;
        r0 = kotlin.text.StringsKt__StringsJVMKt.regionMatches(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x0056;
    L_0x0040:
        return r3;
    L_0x0041:
        r0 = getLastIndex(r9);
        r0 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r11, r0);
        r2 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r12, r1);
        r6 = kotlin.ranges.RangesKt___RangesKt.downTo(r0, r2);
        goto L_0x0017;
    L_0x0052:
        if (r3 >= r7) goto L_0x002f;
    L_0x0054:
        r3 = -1;
        goto L_0x0040;
    L_0x0056:
        if (r3 == r7) goto L_0x0054;
    L_0x0058:
        r3 = r3 + r8;
        goto L_0x002f;
    L_0x005a:
        r3 = r6.getFirst();
        r7 = r6.getLast();
        r8 = r6.getStep();
        if (r8 <= 0) goto L_0x007b;
    L_0x0068:
        if (r3 > r7) goto L_0x0054;
    L_0x006a:
        r4 = r10.length();
        r0 = r10;
        r2 = r9;
        r5 = r13;
        r0 = regionMatchesImpl(r0, r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0040;
    L_0x0077:
        if (r3 == r7) goto L_0x0054;
    L_0x0079:
        r3 = r3 + r8;
        goto L_0x006a;
    L_0x007b:
        if (r3 < r7) goto L_0x0054;
    L_0x007d:
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.indexOf$StringsKt__StringsKt(java.lang.CharSequence, java.lang.CharSequence, int, int, boolean, boolean):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final kotlin.Pair<java.lang.Integer, java.lang.String> findAnyOf$StringsKt__StringsKt(@org.jetbrains.annotations.NotNull java.lang.CharSequence r17, java.util.Collection<java.lang.String> r18, int r19, boolean r20, boolean r21) {
        /*
        if (r20 != 0) goto L_0x0037;
    L_0x0002:
        r1 = r18.size();
        r4 = 1;
        if (r1 != r4) goto L_0x0037;
    L_0x0009:
        r18 = (java.lang.Iterable) r18;
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.single(r18);
        r2 = (java.lang.String) r2;
        if (r21 != 0) goto L_0x0022;
    L_0x0013:
        r4 = 0;
        r5 = 4;
        r6 = 0;
        r1 = r17;
        r3 = r19;
        r12 = indexOf$default(r1, r2, r3, r4, r5, r6);
    L_0x001e:
        if (r12 >= 0) goto L_0x002e;
    L_0x0020:
        r1 = 0;
    L_0x0021:
        return r1;
    L_0x0022:
        r4 = 0;
        r5 = 4;
        r6 = 0;
        r1 = r17;
        r3 = r19;
        r12 = lastIndexOf$default(r1, r2, r3, r4, r5, r6);
        goto L_0x001e;
    L_0x002e:
        r1 = java.lang.Integer.valueOf(r12);
        r1 = kotlin.TuplesKt.to(r1, r2);
        goto L_0x0021;
    L_0x0037:
        if (r21 != 0) goto L_0x0096;
    L_0x0039:
        r1 = 0;
        r0 = r19;
        r4 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r0, r1);
        r1 = new kotlin.ranges.IntRange;
        r5 = r17.length();
        r1.<init>(r4, r5);
        r1 = (kotlin.ranges.IntProgression) r1;
        r13 = r1;
    L_0x004c:
        r0 = r17;
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x00b1;
    L_0x0052:
        r6 = r13.getFirst();
        r1 = r13.getLast();
        r9 = r13.getStep();
        if (r9 <= 0) goto L_0x00a6;
    L_0x0060:
        if (r6 > r1) goto L_0x00a8;
    L_0x0062:
        r10 = r18;
        r10 = (java.lang.Iterable) r10;
        r15 = r10.iterator();
    L_0x006a:
        r4 = r15.hasNext();
        if (r4 == 0) goto L_0x00ab;
    L_0x0070:
        r11 = r15.next();
        r3 = r11;
        r3 = (java.lang.String) r3;
        r4 = 0;
        r5 = r17;
        r5 = (java.lang.String) r5;
        r7 = r3.length();
        r8 = r20;
        r4 = kotlin.text.StringsKt__StringsJVMKt.regionMatches(r3, r4, r5, r6, r7, r8);
        if (r4 == 0) goto L_0x006a;
    L_0x0088:
        r14 = r11;
    L_0x0089:
        r14 = (java.lang.String) r14;
        if (r14 == 0) goto L_0x00ad;
    L_0x008d:
        r1 = java.lang.Integer.valueOf(r6);
        r1 = kotlin.TuplesKt.to(r1, r14);
        goto L_0x0021;
    L_0x0096:
        r1 = getLastIndex(r17);
        r0 = r19;
        r1 = kotlin.ranges.RangesKt___RangesKt.coerceAtMost(r0, r1);
        r4 = 0;
        r13 = kotlin.ranges.RangesKt___RangesKt.downTo(r1, r4);
        goto L_0x004c;
    L_0x00a6:
        if (r6 >= r1) goto L_0x0062;
    L_0x00a8:
        r1 = 0;
        goto L_0x0021;
    L_0x00ab:
        r14 = 0;
        goto L_0x0089;
    L_0x00ad:
        if (r6 == r1) goto L_0x00a8;
    L_0x00af:
        r6 = r6 + r9;
        goto L_0x0062;
    L_0x00b1:
        r7 = r13.getFirst();
        r1 = r13.getLast();
        r15 = r13.getStep();
        if (r15 <= 0) goto L_0x00f7;
    L_0x00bf:
        if (r7 > r1) goto L_0x00a8;
    L_0x00c1:
        r10 = r18;
        r10 = (java.lang.Iterable) r10;
        r16 = r10.iterator();
    L_0x00c9:
        r4 = r16.hasNext();
        if (r4 == 0) goto L_0x00fa;
    L_0x00cf:
        r11 = r16.next();
        r3 = r11;
        r3 = (java.lang.String) r3;
        r4 = r3;
        r4 = (java.lang.CharSequence) r4;
        r5 = 0;
        r8 = r3.length();
        r6 = r17;
        r9 = r20;
        r4 = regionMatchesImpl(r4, r5, r6, r7, r8, r9);
        if (r4 == 0) goto L_0x00c9;
    L_0x00e8:
        r14 = r11;
    L_0x00e9:
        r14 = (java.lang.String) r14;
        if (r14 == 0) goto L_0x00fc;
    L_0x00ed:
        r1 = java.lang.Integer.valueOf(r7);
        r1 = kotlin.TuplesKt.to(r1, r14);
        goto L_0x0021;
    L_0x00f7:
        if (r7 < r1) goto L_0x00a8;
    L_0x00f9:
        goto L_0x00c1;
    L_0x00fa:
        r14 = 0;
        goto L_0x00e9;
    L_0x00fc:
        if (r7 == r1) goto L_0x00a8;
    L_0x00fe:
        r7 = r7 + r15;
        goto L_0x00c1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(java.lang.CharSequence, java.util.Collection, int, boolean, boolean):kotlin.Pair<java.lang.Integer, java.lang.String>");
    }

    @Nullable
    public static /* bridge */ /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence $receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        return findAnyOf$StringsKt__StringsKt($receiver, (Collection) strings, startIndex, ignoreCase, false);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findLastAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence $receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        return findAnyOf$StringsKt__StringsKt($receiver, (Collection) strings, startIndex, ignoreCase, true);
    }

    public static final int indexOfAny(@NotNull CharSequence $receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        Pair findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt($receiver, (Collection) strings, startIndex, ignoreCase, false);
        if (findAnyOf$StringsKt__StringsKt != null) {
            Integer num = (Integer) findAnyOf$StringsKt__StringsKt.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public static final int lastIndexOfAny(@NotNull CharSequence $receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        Pair findAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt($receiver, (Collection) strings, startIndex, ignoreCase, true);
        if (findAnyOf$StringsKt__StringsKt != null) {
            Integer num = (Integer) findAnyOf$StringsKt__StringsKt.getFirst();
            if (num != null) {
                return num.intValue();
            }
        }
        return -1;
    }

    public static final int indexOf(@NotNull CharSequence $receiver, char char_, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (!ignoreCase && ($receiver instanceof String)) {
            return ((String) $receiver).indexOf(char_, startIndex);
        }
        return indexOfAny($receiver, new char[]{char_}, startIndex, ignoreCase);
    }

    public static final int indexOf(@NotNull CharSequence $receiver, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!ignoreCase && ($receiver instanceof String)) {
            return ((String) $receiver).indexOf(string, startIndex);
        }
        return indexOf$StringsKt__StringsKt$default($receiver, string, startIndex, $receiver.length(), ignoreCase, false, 16, null);
    }

    public static final int lastIndexOf(@NotNull CharSequence $receiver, char char_, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (!ignoreCase && ($receiver instanceof String)) {
            return ((String) $receiver).lastIndexOf(char_, startIndex);
        }
        return lastIndexOfAny($receiver, new char[]{char_}, startIndex, ignoreCase);
    }

    public static final int lastIndexOf(@NotNull CharSequence $receiver, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!ignoreCase && ($receiver instanceof String)) {
            return ((String) $receiver).lastIndexOf(string, startIndex);
        }
        return indexOf$StringsKt__StringsKt($receiver, string, startIndex, 0, ignoreCase, true);
    }

    public static final boolean contains(@NotNull CharSequence $receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        if (other instanceof String) {
            return indexOf$default($receiver, (String) other, 0, ignoreCase, 2, null) >= 0;
        } else {
            return indexOf$StringsKt__StringsKt$default($receiver, other, 0, $receiver.length(), ignoreCase, false, 16, null) >= 0;
        }
    }

    public static final boolean contains(@NotNull CharSequence $receiver, char char_, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indexOf$default($receiver, char_, 0, ignoreCase, 2, null) >= 0;
    }

    @InlineOnly
    private static final boolean contains(@NotNull CharSequence $receiver, Regex regex) {
        return regex.containsMatchIn($receiver);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence $receiver, char[] delimiters, int startIndex, boolean ignoreCase, int limit) {
        if ((limit >= 0 ? 1 : null) != null) {
            return new DelimitedRangesSequence($receiver, startIndex, limit, new StringsKt__StringsKt$rangesDelimitedBy$2(delimiters, ignoreCase));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence $receiver, String[] delimiters, int startIndex, boolean ignoreCase, int limit) {
        if ((limit >= 0 ? 1 : null) != null) {
            return new DelimitedRangesSequence($receiver, startIndex, limit, new StringsKt__StringsKt$rangesDelimitedBy$4(ArraysKt___ArraysKt.asList((Object[]) delimiters), ignoreCase));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence $receiver, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default($receiver, delimiters, 0, ignoreCase, limit, 2, null), new StringsKt__StringsKt$splitToSequence$1($receiver));
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence $receiver, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        Iterable<IntRange> $receiver$iv = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default($receiver, delimiters, 0, ignoreCase, limit, 2, null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault($receiver$iv, 10));
        for (IntRange item$iv$iv : $receiver$iv) {
            destination$iv$iv.add(substring($receiver, item$iv$iv));
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence $receiver, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default($receiver, delimiters, 0, ignoreCase, limit, 2, null), new StringsKt__StringsKt$splitToSequence$2($receiver));
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence $receiver, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        Iterable<IntRange> $receiver$iv = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default($receiver, delimiters, 0, ignoreCase, limit, 2, null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault($receiver$iv, 10));
        for (IntRange item$iv$iv : $receiver$iv) {
            destination$iv$iv.add(substring($receiver, item$iv$iv));
        }
        return (List) destination$iv$iv;
    }

    @InlineOnly
    private static final List<String> split(@NotNull CharSequence $receiver, Regex regex, int limit) {
        return regex.split($receiver, limit);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ List split$default(CharSequence $receiver, Regex regex, int limit, int i, Object obj) {
        if ((i & 2) != 0) {
            limit = 0;
        }
        return regex.split($receiver, limit);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return splitToSequence$default($receiver, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return SequencesKt___SequencesKt.toList(lineSequence($receiver));
    }
}
