package org.bank.statistics_generation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class StatisticsItem {

    @XmlElement(name = "value")
    private String value;

    @XmlElement(name = "count")
    private int count;

    public StatisticsItem(String value, int count) {
        this.value = value;
        this.count = hashCode();
    }

    public StatisticsItem() {};

    public int getCount() {
        return count;
    }
}
