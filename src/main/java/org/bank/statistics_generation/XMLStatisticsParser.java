package org.bank.statistics_generation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class XMLStatisticsParser {

    public static void parseStatisticsToXML(String filePath, Map<String, Integer> statisticsData) {

        List<StatisticsItem> items = convertMapToList(statisticsData);

        try {
            StatisticsData data = new StatisticsData(items);
            JAXBContext context = JAXBContext.newInstance(StatisticsData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(data, new File(filePath));

        } catch (JAXBException jaxbException) {
            jaxbException.printStackTrace();
        }
    }

    private static List<StatisticsItem> convertMapToList(Map<String, Integer> map) {
        List<StatisticsItem> items = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            items.add(new StatisticsItem(entry.getKey(), entry.getValue()));
        }

        items.sort(Comparator.comparingInt(StatisticsItem::getCount).reversed());

        return items;
    }


}
