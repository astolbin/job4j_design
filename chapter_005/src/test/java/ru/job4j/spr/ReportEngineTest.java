package ru.job4j.spr;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;

public class ReportEngineTest {
    @Test
    public void whenReportForHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportEngine(store, new ReportFormatterHr());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportForBuh() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 1000);
        store.add(worker);
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        currencyInstance.setCurrency(Currency.getInstance("USD"));
        Report engine = new ReportEngine(store, new ReportFormatterBuh());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(currencyInstance.format(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportForProg() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 1000);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportFormatterHtml());
        String expect = String.join(
                System.lineSeparator(),
                "<html>",
                "<body>",
                "<table>",
                "<tr>",
                "<td>Name</td><td>Hired<td/><td>Fired</td><td>Salary</td>",
                "<td>", worker.getName(), "</td>",
                "<td>", String.valueOf(worker.getHired()), "</td>",
                "<td>", String.valueOf(worker.getFired()), "</td>",
                "<td>", String.valueOf(worker.getSalary()), "</td>",
                "</tr>",
                "</table>",
                "</body>",
                "</html>"
                );
        assertThat(engine.generate(em -> true), is(expect));
    }
}