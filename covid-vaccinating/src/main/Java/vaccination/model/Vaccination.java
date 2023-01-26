package vaccination.model;

import java.time.LocalDate;

public class Vaccination {

    private Long id;

    private long citizenId;

    private LocalDate date;

    private VaccineType type;

    private Status status;

    private String note;

    public Vaccination(long citizenId, LocalDate date, Status status) {
        this.citizenId = citizenId;
        this.date = date;
        this.status = status;
    }

    public Vaccination(long id, long citizenId, LocalDate date, VaccineType type, Status status, String note) {
        this.id = id;
        this.citizenId = citizenId;
        this.date = date;
        this.type = type;
        this.status = status;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public long getCitizenId() {
        return citizenId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public VaccineType getType() {
        return type;
    }

    public void setType(VaccineType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
