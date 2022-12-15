A feladat egy online könyvtár katalógus rendszerének a szimulációja. A könyvtárban vannak könyvek, tankönyvek, zenei albumok és hangoskönyvek. A katalógus ezeket katalóguscikkekbe rendezve tárolja.



Megvalósítás:
A catalog csomagba dolgozz!

LibraryItem interfész: Ez az interfész reprezentál egy könyvtári elemet.

Book osztály: A könyv attribútumai a címe, az oldalai száma és a szerzőinek a listája. Az osztály implementálja a LibraryItem interfészt.

CourseBook osztály: A tankönyv a könyv leszármazottja, saját attribútuma a lektor neve. Egy tankönyv megírásában a szerzők és a lektor is közreműködnek.

AudioBook osztály: A hangoskönyv attribútumai a címe, a szerzőinek listája, az előadóinak a listája (akik hanganyagra mondták fel), és a hossza (percben). Létrejöttében a szerzők és az előadók is közreműködtek. Az osztály implementálja a LibraryItem interfészt.

MusicAlbum osztály: A zenei album attribútumai a címe, a hossza (percben), a zeneszerzők listája és az előadók listája. Létrejöttében a zeneszerzők és az előadók is közreműködtek. Az osztály implementálja a LibraryItem interfészt.

CatalogItem osztály: Minden katalóguselemnek van egy könyvtári elem listája melyek lehetnek akár nyomtatottak vagy audio-k. Továbbá minden katalóguselemnek van darabszáma és egy regisztrációs száma. A következő metódusokkal tud dolgozni:

public boolean hasAudioFeature() - Az adott katalóguselem listájában található-e audio tulajdonságú könyvtári elem.
public boolean hasPrintedFeature() - Az adott katalóguselem listájában található-e nyomtatott tulajdonságú könyvtári elem.
public int getNumberOfPagesAtOneItem() - A katalóguselem listájában található összes nyomtatott könyvtári elem össz-oldalszáma.
public List<String> getContributors() - A katalóguselem listájában található összes könyvtári elem összes közreműködőjét (szerzők, lektorok, stb.) adja vissza egy közös listában.
public List<String> getTitles() - A katalóguselem listájában található összes könyvtári elem címét adja vissza egy közös listában.
Catalog osztály: A katalógus tartalmazza a katalóguselemek listáját. Az osztályban a következő lekérdezéseket lehet végrehajtani:

public List<CatalogItem> getAudioLibraryItems() - Az audio típusú könyvtári elemet (is) tartalmazó katalóguselemeket lehet lekérdezni.
public List<CatalogItem> getPrintedLibraryItems() - A nyomtatott könyvtári elemet (is) tartalmazó katalóguselemeket lehet lekérdezni.
public int getAllPageNumber() - Visszaadja a nyomtatott könyvtári elemek összoldalszámát.
public double getAveragePageNumberMoreThan(int pageNumber) - Visszaadja az átlag oldalszámot egy paraméterként átadott oldalszám felett.
public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria) - Keresni lehet egy SearchCriteria alapján (lásd lejjebb).
public void readBooksFromFile(Path path) - Amennyiben a katalóguselem csak egy könyvet tartalmaz, azt fájlból is be lehet olvasni.
A readBooksFromFile() metódus esetében a példa CSV fájl tartalma:

15;Tüskevár;325;Fekete István
2;Fantomas;257;Marcel Allain;Pierre Souvestre
8;Egri Csillagok;651;Gárdonyi Géza
1;Állítsuk meg az időt;241;Heller Zsolt;Szénási Miklós;Láng Eszter
Az első szám a darabszám, majd a könyv címe, oldalszáma és végül egymás után felsorolva a szerző(k). A fájl tartalmát Scanner segítségével, soronként olvasd be, minden egyes sorból készíts új katalóguselemet, majd add hozzá azt a Catalog osztály listájához! A regisztrációs számok azt a számozási formátumot kövessék, ami a tesztben meg van adva, tehát pl. R-1! (Az “R” betűhöz kapcsolt szám az a szám legyen, ahányadik helyen áll az adott katalóguselem a listában!) A metódus IllegalStateException-t dobjon, ha nem található a keresett fájl, és egy általad létrehozott WrongFormatException-t, ha a sorok formátuma valamiért nem megfelelő! A sorok formátuma többféle módon is lehet rossz, nézd meg a tesztesetekhez mellékelt fájlokat, és kezeld a metódusban a megfelelő kivételeket! Használj multi catch-et!

SearchCriteria osztály: Egy immutable osztály melynek attribútumai egy cím és egy szerző. Az osztályt úgy kell megvalósítani, hogy akár cím, akár szerző alapján, sőt mindkettő alapján lehessen keresni a katalógusban (statikus metódusokkal lehet létrehozni).

Validators osztály: Létre kell hozni egy isBlank() és isEmpty() statikus metódust, az egyik egy Stringről vizsgálja meg, null vagy üres-e, a másik egy listáról dönti el, hogy null vagy üres-e. Ezeket a metódusokat használhatjuk a metódusok paramétereinek ellenőrzésekor.