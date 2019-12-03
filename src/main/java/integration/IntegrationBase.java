package integration;

import core.Student;

public class IntegrationBase {
    private String nameHolder;
    private String idHolder;
    private String dobHolder;

    public IntegrationBase(String name, String id, String dob) {
        this.nameHolder = name;
        this.idHolder = id;
        this.dobHolder = dob;
    }

    public Student getStudent() {
        return new Student(nameHolder, idHolder, dobHolder);
    }


}