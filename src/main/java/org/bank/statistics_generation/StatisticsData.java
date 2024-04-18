package org.bank.statistics_generation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "statistics")
public class StatisticsData {

    @XmlElement(name = "item")
    private List<StatisticsItem> items;

    public StatisticsData(List<StatisticsItem> items) {
        this.items = items;
    }

    public StatisticsData() {}
}
