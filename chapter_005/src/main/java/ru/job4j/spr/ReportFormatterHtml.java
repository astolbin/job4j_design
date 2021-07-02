package ru.job4j.spr;

import java.util.List;

public class ReportFormatterHtml implements ReportFormatter {
    @Override
    public String format(List<Employee> employees) {
        String items = "";
        for (Employee em : employees) {
            items = String.join(
                    System.lineSeparator(),
                    "<td>", em.getName(), "</td>",
                    "<td>", String.valueOf(em.getHired()), "</td>",
                    "<td>", String.valueOf(em.getFired()), "</td>",
                    "<td>", String.valueOf(em.getSalary()), "</td>"
            );
        }

        return String.join(
                System.lineSeparator(),
                "<html>",
                "<body>",
                "<table>",
                "<tr>",
                "<td>Name</td><td>Hired<td/><td>Fired</td><td>Salary</td>",
                items,
                "</tr>",
                "</table>",
                "</body>",
                "</html>"
        );
    }
}
