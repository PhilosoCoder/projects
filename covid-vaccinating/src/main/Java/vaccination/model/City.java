package vaccination.model;

public class City {

    private Long id;

    private String name;

    private String district;

    private String zipCode;

    public City(Long id, String name, String district, String zipCode) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getZipCode() {
        return zipCode;
    }
}
