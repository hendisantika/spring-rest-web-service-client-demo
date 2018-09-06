package com.hendisantika.currency;

import java.util.Date;

/**
 * Created by hendisantika on 5/1/17.
 */
public class Currency {
    String base;
    Date date;
    Rates rates;

    public Currency(String base, Date date, Rates rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }
}
