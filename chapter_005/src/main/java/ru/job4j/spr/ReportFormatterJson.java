package ru.job4j.spr;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportFormatterJson implements ReportFormatter {
    @Override
    public String format(List<Employee> employees) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<JSONObject> employeeList = new ArrayList<>();

        for (Employee em : employees) {
            JSONObject jsonEm = new JSONObject();
            jsonEm.put("name", em.getName());
            jsonEm.put("fired", dateFormat.format(em.getFired().getTime()));
            jsonEm.put("hired", dateFormat.format(em.getFired().getTime()));
            jsonEm.put("salary", em.getSalary());
            employeeList.add(jsonEm);
        }

        JSONObject reportEmployees = new JSONObject();
        reportEmployees.put("employees", new JSONArray(employeeList));

        return reportEmployees.toString();
    }
}
