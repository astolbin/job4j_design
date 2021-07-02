package ru.job4j.spr;

import java.util.function.Predicate;

public class ReportEngine implements Report {
    private final Store store;
    private final ReportFormatter reportFormatter;

    public ReportEngine(Store store, ReportFormatter reportFormatter) {
        this.store = store;
        this.reportFormatter = reportFormatter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return reportFormatter.format(store.findBy(filter));
    }
}
