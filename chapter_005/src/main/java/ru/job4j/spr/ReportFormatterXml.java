package ru.job4j.spr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.List;

public class ReportFormatterXml implements ReportFormatter {
    @Override
    public String format(List<Employee> employees) {
        String xmlEmployees;
        try {
            JAXBContext context = JAXBContext.newInstance(Report.class);
            Marshaller marshaller = context.createMarshaller();

            Report report = new Report(employees);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(report, writer);
                xmlEmployees = writer.getBuffer().toString();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return xmlEmployees;
    }

    @XmlRootElement(name = "report")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Report {
        @XmlElementWrapper(name = "employees")
        @XmlElement(name = "employee")
        private List<Employee> employees;

        public Report() {
        }

        public Report(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
