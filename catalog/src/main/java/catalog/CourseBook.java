package catalog;

import java.util.ArrayList;
import java.util.List;

public class CourseBook extends Book {

    private String lector;


    public CourseBook(String title, int numberOfPages, List<String> authors, String lector) {
        super(title, numberOfPages, authors);
        validate(lector);
        this.lector = lector;
    }

    @Override
    public List<String> getContributors() {
        List <String> contributors = new ArrayList<>();
        contributors.addAll(super.getContributors());
        contributors.add(lector);
        return contributors;
    }

    private void validate(String lector) {
        if (Validators.isBlank(lector)) {
            throw new IllegalArgumentException("No lector");
        }
    }

    public String getLector() {
        return lector;
    }
}
