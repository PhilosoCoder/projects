package schoolrecords;

public class Mark {

    private MarkType markType;

    private Subject subject;

    private Tutor tutor;

    public Mark(MarkType markType, Subject subject, Tutor tutor) {
       if (subject != null) {
            this.markType = markType;
            this.subject = subject;
            this.tutor = tutor;
        } else {
           throw new IllegalArgumentException("Both subject and tutor must be provided!");
       }
    }

    public MarkType getMarkType() {
        return markType;
    }

    public int getMarkValue() {
        return markType.getValue();
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getSubjectName() {
        return subject.getName();
    }

    @Override
    public String toString() {
        return subject.getName() + " - " + markType.getDescription() + "(" + markType.getValue() + ")";
    }
}
