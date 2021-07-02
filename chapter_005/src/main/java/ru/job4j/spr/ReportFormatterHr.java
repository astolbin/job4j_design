package ru.job4j.spr;

import java.util.Comparator;
import java.util.List;

public class ReportFormatterHr implements ReportFormatter {
    @Override
    public String format(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee em : employees) {
            text.append(em.getName()).append(";")
                    .append(em.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
