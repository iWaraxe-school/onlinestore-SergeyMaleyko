package by.issoft.domain.discounters;

import java.util.Calendar;

// Strategy pattern
public class WeeklyDiscounter implements Discounter {

    private final static Double VALUE_OF_DISCOUNT = 0.8;

    @Override
    public Double applyDiscount(Double price) {
        return Math.round(price * VALUE_OF_DISCOUNT * 100.00) / 100.00;
    }

    @Override
    public boolean checkCondition() {
        boolean ret = false;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            ret = true;
        }
        return ret;
    }
}
