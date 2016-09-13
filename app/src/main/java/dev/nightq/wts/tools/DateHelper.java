package dev.nightq.wts.tools;

import android.text.format.DateFormat;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    /**
     * 获取 ymd
     *
     * @param date
     * @return
     */
    public static String formatYMDDate(Date date) {
        return DateFormat.format("yyyy-MM-dd", date).toString();
    }


    private static final long DAY_TIME = 24 * 3600 * 1000l;

    public static int[] getMD(Date start, Date end) {
        int[] ymd = getYMD(start, end);
        return new int[]{ymd[0] * 12 + ymd[1], ymd[2]};
    }

    public static int[] getYMD(Date start, Date end) {
        if (start == null || end == null) {
            return new int[]{0, 0, 0};
        }
        Date from;
        Date to;
        boolean beforeFlag = false;
        if (start.after(end)) {
            from = end;
            to = start;
            beforeFlag = true;
        } else {
            from = start;
            to = end;
            beforeFlag = false;
        }

        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        fromCalendar.setTime(from);
        toCalendar.setTime(to);

        int fy, fm, fd;
        int ty, tm, td;
        fy = fromCalendar.get(Calendar.YEAR);
        fm = fromCalendar.get(Calendar.MONTH);
        fd = fromCalendar.get(Calendar.DATE);

        ty = toCalendar.get(Calendar.YEAR);
        tm = toCalendar.get(Calendar.MONTH);
        td = toCalendar.get(Calendar.DATE);

        {
            int[] ymd = new int[3];
            int y = 0, m = 0, d = 0;
            // year
            int year, month, day;
            // 如果月份更大或者月份相等天数更大
            if (tm > fm || (tm == fm && td >= fd)) {
                // 相当于从todate那一年的from的月日开始算起
                year = ty;
            } else {
                // 不然就向todate的前一年开始算。
                year = ty - 1;
            }
            // 这样得到年的数目
            y = year - fy;
            // month
            // 然后判断day的大小
            // 如果from day 小于to day
            // 那么tm > fm
            if (fd <= td) {
                // 那么从year to的月 from的day 算起就行
                year = ty;
                month = tm;
                day = fd;
            } else {
                // 否则day计算的时候需要从月份借位。
                // 否则如果to的月＝0的话，
                if (tm == 0) {
                    // 月份为0的话要向年借位
                    year = ty - 1;
                    month = 11;
                } else {
                    // 月份不为0的话直接向月借位
                    year = ty;
                    month = tm - 1;
                }
                // 这样从
                Calendar calendar = Calendar.getInstance();
                calendar.set(ty, month, 1);
                day = Math.min(fd,
                        calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            // month
            m = (month + 12 - fm) % 12;
            // to.get
            Calendar tmpCalendar = Calendar.getInstance();
            tmpCalendar.set(year, month, day);
            Date last = tmpCalendar.getTime();
            d = getDays(last, to);

            // 如果td也是那个月的最后一天, 且相隔天数少于这个月天数。那么进位
            if (td == toCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                    && d >= td) {
                if (m == 11) {
                    y++;
                    m = 0;
                } else {
                    m++;
                }
                d = 0;
            }
            if (beforeFlag) {
                ymd[0] = -y;
                ymd[1] = -m;
                ymd[2] = -d;
            } else {
                ymd[0] = y;
                ymd[1] = m;
                ymd[2] = d;

            }
            return ymd;
        }
    }

    public static int getDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return -1;
        }

        int result = 0;
        if (startDate.before(endDate) || compareByYMD(startDate, endDate)) {
            while (!compareByYMD(startDate, endDate)
                    && startDate.before(endDate)) {
                startDate.setTime(startDate.getTime() + DAY_TIME);
                result++;
            }
        } else {
            return -1;
        }
        return result;
    }

    public static boolean compareByYMD(Date date, Date date2) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        Calendar mCalendar2 = Calendar.getInstance();
        mCalendar2.setTime(date2);
        return mCalendar.get(Calendar.YEAR) == mCalendar2.get(Calendar.YEAR)
                && mCalendar.get(Calendar.MONTH) == mCalendar2
                .get(Calendar.MONTH)
                && mCalendar.get(Calendar.DATE) == mCalendar2
                .get(Calendar.DATE);
    }

    /**
     * 获取两个日期之间的间隔天数 ，以较小日期的0点开始计算
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 获得两个日期间的天数，妈蛋上两个方法都会修改传入日期
     *
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {
        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * 获得岁数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getYears(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return -1;
        }

        // 1. 获得baby当前岁数
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(startDate);
        Calendar babyCalendar = Calendar.getInstance();
        babyCalendar.setTime(endDate);
        int currentAge = todayCalendar.get(Calendar.YEAR) - babyCalendar.get(Calendar.YEAR);

        // 1. 根据月日对比看是否满了那一岁
        if (todayCalendar.get(Calendar.DAY_OF_YEAR) < babyCalendar.get(Calendar.DAY_OF_YEAR)) {
            currentAge = currentAge - 1;
        }

        return currentAge;
    }


    public static Integer getMonths(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }

        LocalDate startLD = toLocalDateTime(startDate.getTime()).toLocalDate();
        LocalDate endLT = toLocalDateTime(endDate.getTime()).toLocalDate();

        Period period = Period.between(startLD, endLT);
        int year = period.getYears();
        return year * 12 + period.getMonths();
    }

    /**
     * 返回[天][时][分][秒] 主要为了时光胶囊显示界面
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int[] getDayHourMinSecond(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return new int[]{0, 0, 0, 0};
        }

        long quot = (endDate.getTime() - startDate.getTime()) / 1000;
        if (quot < 0) {
            return new int[]{0, 0, 0, 0};
        } else {
            int[] result = new int[4];
            result[0] = (int) (quot / (24 * 3600));// 天数
            result[1] = (int) (quot % (24 * 3600) / 3600);// 小时数
            result[2] = (int) (quot % 3600 / 60);// 分钟数
            result[3] = (int) (quot % 60);// 秒数
            return result;
        }
    }

    /**
     * 獲取TC的封藏时间
     *
     * @param createDate
     * @return
     */
    public static Date getTimeCapsuleSealTime(Date createDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DATE, 14);
        return calendar.getTime();
    }

    // 获得某个时间的年月日时分秒
    public static int[] getDateByYMDHMS(long time) {
        LocalDateTime localDateTime = toLocalDateTime(time);
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
        int hour = localDateTime.getHour();
        int min = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        return new int[]{year, month, day, hour, min, second};
    }

    public static LocalDateTime toLocalDateTime(long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static long localDateTimeToTimestamp(LocalDateTime dateTime) {
        ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }
}
