package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression.Companion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000b\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007\u001a\u0012\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0012\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n\u001a'\u0010\u000b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u0012\u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0012\u0010\u000b\u001a\u00020\n*\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a3\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\f\u001a\u0004\u0018\u0001H\u0001¢\u0006\u0002\u0010\u000e\u001a/\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u001a\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u001a\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0018\u0010\r\u001a\u00020\b*\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u001a\u001a\u0010\r\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0018\u0010\r\u001a\u00020\t*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u0017\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u0017\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0016\u001a\u00020\bH\u0002¢\u0006\u0002\b\u0017\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0016\u001a\u00020\tH\u0002¢\u0006\u0002\b\u0017\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0002\b\u0017\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u0018\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u0018\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0016\u001a\u00020\bH\u0002¢\u0006\u0002\b\u0018\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0016\u001a\u00020\tH\u0002¢\u0006\u0002\b\u0018\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0002\b\u0018\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0016\u001a\u00020\bH\u0002¢\u0006\u0002\b\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0016\u001a\u00020\tH\u0002¢\u0006\u0002\b\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0002\b\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001a\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001a\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001a\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0016\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001a\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001a\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0016\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0016\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u0016\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001c\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\u00052\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u0010\u001d\u001a\u00020!*\u00020\"2\u0006\u0010\u001f\u001a\u00020\"H\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\b2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\b2\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\t2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\t2\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\n2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u0010\u001d\u001a\u00020 *\u00020\n2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\n\u0010#\u001a\u00020!*\u00020!\u001a\n\u0010#\u001a\u00020\u001e*\u00020\u001e\u001a\n\u0010#\u001a\u00020 *\u00020 \u001a\u0015\u0010$\u001a\u00020!*\u00020!2\u0006\u0010$\u001a\u00020\bH\u0004\u001a\u0015\u0010$\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010$\u001a\u00020\bH\u0004\u001a\u0015\u0010$\u001a\u00020 *\u00020 2\u0006\u0010$\u001a\u00020\tH\u0004\u001a\u0013\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0000¢\u0006\u0002\u0010&\u001a\u0013\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000¢\u0006\u0002\u0010'\u001a\u0013\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\bH\u0000¢\u0006\u0002\u0010(\u001a\u0013\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000¢\u0006\u0002\u0010)\u001a\u0013\u0010%\u001a\u0004\u0018\u00010\u0005*\u00020\nH\u0000¢\u0006\u0002\u0010*\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0000¢\u0006\u0002\u0010,\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0000¢\u0006\u0002\u0010-\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000¢\u0006\u0002\u0010.\u001a\u0013\u0010/\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000¢\u0006\u0002\u00100\u001a\u0013\u0010/\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0000¢\u0006\u0002\u00101\u001a\u0013\u00102\u001a\u0004\u0018\u00010\n*\u00020\u0006H\u0000¢\u0006\u0002\u00103\u001a\u0013\u00102\u001a\u0004\u0018\u00010\n*\u00020\u0007H\u0000¢\u0006\u0002\u00104\u001a\u0013\u00102\u001a\u0004\u0018\u00010\n*\u00020\bH\u0000¢\u0006\u0002\u00105\u001a\u0013\u00102\u001a\u0004\u0018\u00010\n*\u00020\tH\u0000¢\u0006\u0002\u00106\u001a\u0015\u00107\u001a\u000208*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\u00052\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u00107\u001a\u00020:*\u00020\"2\u0006\u0010\u001f\u001a\u00020\"H\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\b2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\b2\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\t2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\t2\u0006\u0010\u001f\u001a\u00020\nH\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0005H\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\n2\u0006\u0010\u001f\u001a\u00020\bH\u0004\u001a\u0015\u00107\u001a\u000209*\u00020\n2\u0006\u0010\u001f\u001a\u00020\tH\u0004\u001a\u0015\u00107\u001a\u000208*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0004¨\u0006;"}, d2 = {"coerceAtLeast", "T", "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "value", "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/ranges/CharRange;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/ranges/RangesKt")
/* compiled from: _Ranges.kt */
class RangesKt___RangesKt extends RangesKt__RangesKt {
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> $receiver, byte value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Integer.valueOf(value));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> $receiver, byte value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Long.valueOf((long) value));
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> $receiver, byte value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Short.valueOf((short) value));
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> $receiver, byte value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Double.valueOf((double) value));
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> $receiver, byte value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Float.valueOf((float) value));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> $receiver, double value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Integer it = toIntExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> $receiver, double value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Long it = toLongExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> $receiver, double value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Byte it = toByteExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> $receiver, double value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Short it = toShortExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> $receiver, double value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Float.valueOf((float) value));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Integer it = toIntExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Long it = toLongExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Byte it = toByteExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Short it = toShortExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Double.valueOf((double) value));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Long.valueOf((long) value));
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Byte it = toByteExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Short it = toShortExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Double.valueOf((double) value));
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Float.valueOf((float) value));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> $receiver, long value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Integer it = toIntExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> $receiver, long value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Byte it = toByteExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> $receiver, long value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Short it = toShortExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> $receiver, long value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Double.valueOf((double) value));
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> $receiver, long value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Float.valueOf((float) value));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> $receiver, short value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Integer.valueOf(value));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> $receiver, short value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Long.valueOf((long) value));
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> $receiver, short value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Byte it = toByteExactOrNull(value);
        return it != null ? $receiver.contains(it) : false;
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> $receiver, short value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Double.valueOf((double) value));
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> $receiver, short value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.contains(Float.valueOf((float) value));
    }

    @NotNull
    public static final IntProgression downTo(int $receiver, byte to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(long $receiver, byte to) {
        return LongProgression.Companion.fromClosedRange($receiver, (long) to, -1);
    }

    @NotNull
    public static final IntProgression downTo(byte $receiver, byte to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final IntProgression downTo(short $receiver, byte to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final CharProgression downTo(char $receiver, char to) {
        return CharProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final IntProgression downTo(int $receiver, int to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(long $receiver, int to) {
        return LongProgression.Companion.fromClosedRange($receiver, (long) to, -1);
    }

    @NotNull
    public static final IntProgression downTo(byte $receiver, int to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final IntProgression downTo(short $receiver, int to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(int $receiver, long to) {
        return LongProgression.Companion.fromClosedRange((long) $receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(long $receiver, long to) {
        return LongProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(byte $receiver, long to) {
        return LongProgression.Companion.fromClosedRange((long) $receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(short $receiver, long to) {
        return LongProgression.Companion.fromClosedRange((long) $receiver, to, -1);
    }

    @NotNull
    public static final IntProgression downTo(int $receiver, short to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final LongProgression downTo(long $receiver, short to) {
        return LongProgression.Companion.fromClosedRange($receiver, (long) to, -1);
    }

    @NotNull
    public static final IntProgression downTo(byte $receiver, short to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final IntProgression downTo(short $receiver, short to) {
        return IntProgression.Companion.fromClosedRange($receiver, to, -1);
    }

    @NotNull
    public static final IntProgression reversed(@NotNull IntProgression $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return IntProgression.Companion.fromClosedRange($receiver.getLast(), $receiver.getFirst(), -$receiver.getStep());
    }

    @NotNull
    public static final LongProgression reversed(@NotNull LongProgression $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return LongProgression.Companion.fromClosedRange($receiver.getLast(), $receiver.getFirst(), -$receiver.getStep());
    }

    @NotNull
    public static final CharProgression reversed(@NotNull CharProgression $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return CharProgression.Companion.fromClosedRange($receiver.getLast(), $receiver.getFirst(), -$receiver.getStep());
    }

    @NotNull
    public static final IntProgression step(@NotNull IntProgression $receiver, int step) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        RangesKt__RangesKt.checkStepIsPositive(step > 0, Integer.valueOf(step));
        Companion companion = IntProgression.Companion;
        int first = $receiver.getFirst();
        int last = $receiver.getLast();
        if ($receiver.getStep() <= 0) {
            step = -step;
        }
        return companion.fromClosedRange(first, last, step);
    }

    @NotNull
    public static final LongProgression step(@NotNull LongProgression $receiver, long step) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        RangesKt__RangesKt.checkStepIsPositive(step > 0, Long.valueOf(step));
        return LongProgression.Companion.fromClosedRange($receiver.getFirst(), $receiver.getLast(), $receiver.getStep() > 0 ? step : -step);
    }

    @NotNull
    public static final CharProgression step(@NotNull CharProgression $receiver, int step) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        RangesKt__RangesKt.checkStepIsPositive(step > 0, Integer.valueOf(step));
        CharProgression.Companion companion = CharProgression.Companion;
        char first = $receiver.getFirst();
        char last = $receiver.getLast();
        if ($receiver.getStep() <= 0) {
            step = -step;
        }
        return companion.fromClosedRange(first, last, step);
    }

    @Nullable
    public static final Byte toByteExactOrNull(int $receiver) {
        return (-128 <= $receiver && 127 >= $receiver) ? Byte.valueOf((byte) $receiver) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(long $receiver) {
        return (((long) -128) <= $receiver && ((long) 127) >= $receiver) ? Byte.valueOf((byte) ((int) $receiver)) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(short $receiver) {
        return (((short) -128) <= $receiver && ((short) 127) >= $receiver) ? Byte.valueOf((byte) $receiver) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(double $receiver) {
        return (((double) -128) <= $receiver && ((double) 127) >= $receiver) ? Byte.valueOf((byte) ((int) $receiver)) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(float $receiver) {
        return (((float) -128) <= $receiver && ((float) 127) >= $receiver) ? Byte.valueOf((byte) ((int) $receiver)) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(long $receiver) {
        return (((long) Integer.MIN_VALUE) <= $receiver && ((long) Integer.MAX_VALUE) >= $receiver) ? Integer.valueOf((int) $receiver) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(double $receiver) {
        return (((double) Integer.MIN_VALUE) <= $receiver && ((double) Integer.MAX_VALUE) >= $receiver) ? Integer.valueOf((int) $receiver) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(float $receiver) {
        return (((float) Integer.MIN_VALUE) <= $receiver && ((float) Integer.MAX_VALUE) >= $receiver) ? Integer.valueOf((int) $receiver) : null;
    }

    @Nullable
    public static final Long toLongExactOrNull(double $receiver) {
        return (((double) Long.MIN_VALUE) <= $receiver && ((double) Long.MAX_VALUE) >= $receiver) ? Long.valueOf((long) $receiver) : null;
    }

    @Nullable
    public static final Long toLongExactOrNull(float $receiver) {
        return (((float) Long.MIN_VALUE) <= $receiver && ((float) Long.MAX_VALUE) >= $receiver) ? Long.valueOf((long) $receiver) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(int $receiver) {
        return (-32768 <= $receiver && 32767 >= $receiver) ? Short.valueOf((short) $receiver) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(long $receiver) {
        return (((long) -32768) <= $receiver && ((long) 32767) >= $receiver) ? Short.valueOf((short) ((int) $receiver)) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(double $receiver) {
        return (((double) -32768) <= $receiver && ((double) 32767) >= $receiver) ? Short.valueOf((short) ((int) $receiver)) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(float $receiver) {
        return (((float) -32768) <= $receiver && ((float) 32767) >= $receiver) ? Short.valueOf((short) ((int) $receiver)) : null;
    }

    @NotNull
    public static final IntRange until(int $receiver, byte to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(long $receiver, byte to) {
        return new LongRange($receiver, ((long) to) - 1);
    }

    @NotNull
    public static final IntRange until(byte $receiver, byte to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final IntRange until(short $receiver, byte to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final CharRange until(char $receiver, char to) {
        if (to <= '\u0000') {
            return CharRange.Companion.getEMPTY();
        }
        return new CharRange($receiver, (char) (to - 1));
    }

    @NotNull
    public static final IntRange until(int $receiver, int to) {
        if (to <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(long $receiver, int to) {
        return new LongRange($receiver, ((long) to) - 1);
    }

    @NotNull
    public static final IntRange until(byte $receiver, int to) {
        if (to <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final IntRange until(short $receiver, int to) {
        if (to <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(int $receiver, long to) {
        if (to <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        return new LongRange((long) $receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(long $receiver, long to) {
        if (to <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        return new LongRange($receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(byte $receiver, long to) {
        if (to <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        return new LongRange((long) $receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(short $receiver, long to) {
        if (to <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        return new LongRange((long) $receiver, to - 1);
    }

    @NotNull
    public static final IntRange until(int $receiver, short to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final LongRange until(long $receiver, short to) {
        return new LongRange($receiver, ((long) to) - 1);
    }

    @NotNull
    public static final IntRange until(byte $receiver, short to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final IntRange until(short $receiver, short to) {
        return new IntRange($receiver, to - 1);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtLeast(@NotNull T $receiver, @NotNull T minimumValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(minimumValue, "minimumValue");
        return $receiver.compareTo(minimumValue) < 0 ? minimumValue : $receiver;
    }

    public static final byte coerceAtLeast(byte $receiver, byte minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    public static final short coerceAtLeast(short $receiver, short minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    public static final int coerceAtLeast(int $receiver, int minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    public static final long coerceAtLeast(long $receiver, long minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    public static final float coerceAtLeast(float $receiver, float minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    public static final double coerceAtLeast(double $receiver, double minimumValue) {
        return $receiver < minimumValue ? minimumValue : $receiver;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtMost(@NotNull T $receiver, @NotNull T maximumValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(maximumValue, "maximumValue");
        return $receiver.compareTo(maximumValue) > 0 ? maximumValue : $receiver;
    }

    public static final byte coerceAtMost(byte $receiver, byte maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    public static final short coerceAtMost(short $receiver, short maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    public static final int coerceAtMost(int $receiver, int maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    public static final long coerceAtMost(long $receiver, long maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    public static final float coerceAtMost(float $receiver, float maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    public static final double coerceAtMost(double $receiver, double maximumValue) {
        return $receiver > maximumValue ? maximumValue : $receiver;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T $receiver, @Nullable T minimumValue, @Nullable T maximumValue) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (minimumValue == null || maximumValue == null) {
            if (minimumValue != null && $receiver.compareTo(minimumValue) < 0) {
                return minimumValue;
            }
            if (maximumValue != null && $receiver.compareTo(maximumValue) > 0) {
                return maximumValue;
            }
        } else if (minimumValue.compareTo(maximumValue) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver.compareTo(minimumValue) < 0) {
            return minimumValue;
        } else {
            if ($receiver.compareTo(maximumValue) > 0) {
                return maximumValue;
            }
        }
        return $receiver;
    }

    public static final byte coerceIn(byte $receiver, byte minimumValue, byte maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    public static final short coerceIn(short $receiver, short minimumValue, short maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    public static final int coerceIn(int $receiver, int minimumValue, int maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    public static final long coerceIn(long $receiver, long minimumValue, long maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    public static final float coerceIn(float $receiver, float minimumValue, float maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    public static final double coerceIn(double $receiver, double minimumValue, double maximumValue) {
        if (minimumValue > maximumValue) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
        } else if ($receiver < minimumValue) {
            return minimumValue;
        } else {
            return $receiver > maximumValue ? maximumValue : $receiver;
        }
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T $receiver, @NotNull ClosedFloatingPointRange<T> range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        } else if (range.lessThanOrEquals($receiver, range.getStart()) && !range.lessThanOrEquals(range.getStart(), $receiver)) {
            return range.getStart();
        } else {
            if (!range.lessThanOrEquals(range.getEndInclusive(), $receiver) || range.lessThanOrEquals($receiver, range.getEndInclusive())) {
                return $receiver;
            }
            return range.getEndInclusive();
        }
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T $receiver, @NotNull ClosedRange<T> range) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return coerceIn((Comparable) $receiver, (ClosedFloatingPointRange) range);
        }
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        } else if ($receiver.compareTo(range.getStart()) < 0) {
            return range.getStart();
        } else {
            if ($receiver.compareTo(range.getEndInclusive()) > 0) {
                return range.getEndInclusive();
            }
            return $receiver;
        }
    }

    public static final int coerceIn(int $receiver, @NotNull ClosedRange<Integer> range) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return ((Number) coerceIn((Comparable) Integer.valueOf($receiver), (ClosedFloatingPointRange) range)).intValue();
        }
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        } else if ($receiver < ((Number) range.getStart()).intValue()) {
            return ((Number) range.getStart()).intValue();
        } else {
            if ($receiver > ((Number) range.getEndInclusive()).intValue()) {
                return ((Number) range.getEndInclusive()).intValue();
            }
            return $receiver;
        }
    }

    public static final long coerceIn(long $receiver, @NotNull ClosedRange<Long> range) {
        Intrinsics.checkParameterIsNotNull(range, "range");
        if (range instanceof ClosedFloatingPointRange) {
            return ((Number) coerceIn((Comparable) Long.valueOf($receiver), (ClosedFloatingPointRange) range)).longValue();
        }
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
        } else if ($receiver < ((Number) range.getStart()).longValue()) {
            return ((Number) range.getStart()).longValue();
        } else {
            if ($receiver > ((Number) range.getEndInclusive()).longValue()) {
                return ((Number) range.getEndInclusive()).longValue();
            }
            return $receiver;
        }
    }
}
