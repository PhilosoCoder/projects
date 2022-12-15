package catalog;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalog {

    private List<CatalogItem> catalogItems = new ArrayList<>();

    public List<CatalogItem> getAudioLibraryItems() {
        List<CatalogItem> hasAudio = new ArrayList<>();
        for (CatalogItem actual : catalogItems) {
            if (actual.hasAudioFeature()) {
                hasAudio.add(actual);
            }
        }
        return hasAudio;
    }

    public void addItem(CatalogItem catalogItem) {
        if (catalogItem == null) {
            throw new IllegalArgumentException("Catalog item is empty");
        }
        catalogItems.add(catalogItem);
    }

    public void deleteItemByRegistrationNumber(String registrationNumber) {
        if (Validators.isBlank(registrationNumber)) {
            throw new IllegalArgumentException("Empty registration number");
        }
        CatalogItem catalogItemToRemove = null;
        for (CatalogItem actual : catalogItems) {
            if (actual.getRegistrationNumber().equals(registrationNumber)) {
                catalogItemToRemove = actual;
            }
        }
        catalogItems.remove(catalogItemToRemove);
    }

    public List<CatalogItem> getPrintedLibraryItems() {
        List<CatalogItem> printed = new ArrayList<>();
        for (CatalogItem actual : catalogItems) {
            if (actual.hasPrintedFeature()) {
                printed.add(actual);
            }
        }
        return printed;
    }

    public int getAllPageNumber() {
        int sum = 0;
        for (CatalogItem actual : catalogItems) {
            sum += actual.getNumberOfPagesAtOneItem();
        }
        return sum;
    }

    public double getAveragePageNumberMoreThan(int pageNumber) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must be positive");
        }
        if (pageNumber >= 2000) {
            throw new IllegalArgumentException("No such book");
        }
        double sum = 0;
        int count = 0;
        for (CatalogItem actual : catalogItems) {
            if (actual.getNumberOfPagesAtOneItem() > pageNumber) {
                sum += actual.getNumberOfPagesAtOneItem();
                count++;
            }
        }
        return sum / count;
    }

    public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria) {
        if (searchCriteria == null) {
            throw new IllegalArgumentException("Empty criteria");
        }
        List<CatalogItem> catalogItemsByCriteria = new ArrayList<>();
        for (CatalogItem catalogItem : catalogItems) {
            boolean found = false;
            if (searchCriteria.hasTitle() && catalogItem.getTitles().contains(searchCriteria.getTitle())) {
                found = true;
            }
            if (searchCriteria.hasContributor() && catalogItem.getContributors().contains(searchCriteria.getContributor())) {
                found = true;
            }
            if (found) {
                catalogItemsByCriteria.add(catalogItem);
            }
        }
        return catalogItemsByCriteria;
    }

    public void readBooksFromFile(Path path) {
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can not read from file", e);
        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException ex) {
            throw new WrongFormatException("Line format in file is wrong", ex);
        }
    }
    private void processLine(String line) {
        String[] temp = line.split(";");
        String registrationNumber = "R-" + (catalogItems.size() + 1);
        int pieces = Integer.parseInt(temp[0]);
        String title = temp[1];
        int numberOfPages = Integer.parseInt(temp[2]);
        List<String> authors = new ArrayList<>();
        for (int i = 3; i < temp.length; i++) {
            authors.add(temp[i]);
        }
        catalogItems.add(new CatalogItem(registrationNumber, pieces, new Book(title, numberOfPages, authors)));
    }

        public List<CatalogItem> getCatalogItems () {
            return catalogItems;
        }
    }
