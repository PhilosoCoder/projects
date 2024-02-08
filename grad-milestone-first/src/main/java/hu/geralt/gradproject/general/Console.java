package hu.geralt.gradproject.general;

import java.util.HashMap;
import java.util.List;

import hu.geralt.gradproject.enums.Brand;
import hu.geralt.gradproject.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Console {

    private final Brand brand;
    private final Type type;
    private int controllers;
    private HashMap<Game, List<Save<Progress<String>>>> saves = new HashMap<>();

    protected Console(Type type, int controllers) {
        this.brand = setBrand(type);
        this.type = type;
        this.controllers = controllers;
    }

    public static Brand setBrand(Type type) {
        final Brand calculatedBrand;
        if (type == Type.PLAYSTATION_5 || type == Type.PLAYSTATION_4) {
            calculatedBrand = Brand.SONY;
        } else if (type == Type.XBOX_SERIES_S || type == Type.XBOX_SERIES_X) {
            calculatedBrand = Brand.MICROSOFT;
        } else {
            calculatedBrand = Brand.NINTENDO;
        }
        return calculatedBrand;
    }
}
