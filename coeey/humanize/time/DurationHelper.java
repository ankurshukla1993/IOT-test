package humanize.time;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.DurationImpl;

public class DurationHelper {
    public static Duration calculateDuration(Date ref, Date then, List<TimeUnit> timeUnits) {
        return calculateDuration(then.getTime() - ref.getTime(), timeUnits);
    }

    public static Duration calculateDuration(long difference, List<TimeUnit> timeUnits) {
        long absoluteDifference = Math.abs(difference);
        List<TimeUnit> units = new ArrayList(timeUnits);
        DurationImpl result = new DurationImpl();
        int i = 0;
        while (i < units.size()) {
            TimeUnit unit = (TimeUnit) units.get(i);
            long millisPerUnit = Math.abs(unit.getMillisPerUnit());
            long quantity = Math.abs(unit.getMaxQuantity());
            boolean isLastUnit = i == units.size() + -1;
            if (0 == quantity && !isLastUnit) {
                quantity = ((TimeUnit) units.get(i + 1)).getMillisPerUnit() / unit.getMillisPerUnit();
            }
            if (millisPerUnit * quantity > absoluteDifference || isLastUnit) {
                result.setUnit(unit);
                if (millisPerUnit > absoluteDifference) {
                    result.setQuantity(0 > difference ? -1 : 1);
                } else {
                    result.setQuantity(difference / millisPerUnit);
                }
                result.setDelta(difference - (result.getQuantity() * millisPerUnit));
                return result;
            }
            i++;
        }
        return result;
    }

    public static List<Duration> calculatePreciseDuration(Date ref, Date then, List<TimeUnit> units) {
        Preconditions.checkNotNull(then, "Date to calculate must not be null.");
        if (ref == null) {
            ref = new Date();
        }
        List<Duration> result = new ArrayList();
        Duration duration = calculateDuration(then.getTime() - ref.getTime(), units);
        result.add(duration);
        while (0 != duration.getDelta()) {
            duration = calculateDuration(duration.getDelta(), units);
            result.add(duration);
        }
        return result;
    }
}
