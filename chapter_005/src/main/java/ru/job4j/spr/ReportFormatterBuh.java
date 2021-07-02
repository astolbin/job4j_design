package ru.job4j.spr;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class ReportFormatterBuh implements ReportFormatter {
    @Override
    public String format(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        currencyInstance.setCurrency(Currency.getInstance("USD"));
        for (Employee em : employees) {
            String salary = currencyInstance.format(em.getSalary());
            text.append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append(salary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
