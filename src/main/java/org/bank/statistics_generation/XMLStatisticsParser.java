package org.bank.statistics_generation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLStatisticsParser {

    public static void parseStatisticsToXML(String filePath, Map<String, Integer> statisticsData) {

        List<StatisticsItem> items = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: statisticsData.entrySet()) {
            items.add(new StatisticsItem(entry.getKey(), entry.getValue()));
        }

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


}
