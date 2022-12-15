package catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogItem {

    private String registrationNumber;

    private int pieces;

    private List<LibraryItem> libraryItems;

    public CatalogItem(String registrationNumber, int pieces, LibraryItem... libraryItems) {
        validate(registrationNumber, pieces, libraryItems);
        this.registrationNumber = registrationNumber;
        this.pieces = pieces;
        this.libraryItems = Arrays.asList(libraryItems);
    }

    public boolean hasAudioFeature() {
        boolean isAudio = false;
        for (LibraryItem actual : libraryItems) {
            if (actual.isAudio()) {
                isAudio = true;
            }
        }
        return isAudio;
    }

    public boolean hasPrintedFeature() {
        boolean isPrinted = false;
        for (LibraryItem actual : libraryItems) {
            if (actual.isPrinted()) {
                isPrinted = true;
            }
        }
        return isPrinted;
    }

    public int getNumberOfPagesAtOneItem() {
        int sum = 0;
        for (LibraryItem actual : libraryItems) {
            if (actual.isPrinted()) {
                sum += ((Book) actual).getNumberOfPages();
            }
        }
        return sum;
    }

    public List<String> getContributors() {
        List<String> contributors = new ArrayList<>();
        for (LibraryItem libraryItem : libraryItems) {
            List<String> contributorsToAdd = getContributorsToAdd(contributors, libraryItem.getContributors());
            contributors.addAll(contributorsToAdd);
        }
        return contributors;
    }

    private List <String> getContributorsToAdd(List <String> contributorsAlready, List <String> contributors) {
        List <String> contributorsToAdd = new ArrayList<>();
        for (String contributor : contributors) {
            if (!contributorsAlready.contains(contributor)) {
                contributorsToAdd.add(contributor);
            }
        }
        return contributorsToAdd;
    }

    public List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        for (LibraryItem libraryItem : libraryItems) {
            titles.add(libraryItem.getTitle());
        }
        return titles;
    }

    private void validate(String registrationNumber, int pieces, LibraryItem... libraryItems) {
        if (Validators.isBlank(registrationNumber)) {
            throw new IllegalArgumentException("Empty registration number");
        }
        if (pieces < 0) {
            throw new IllegalArgumentException("Pieces must be at least 0");
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPieces() {
        return pieces;
    }

    public List<LibraryItem> getLibraryItems() {
        return libraryItems;
    }
}
