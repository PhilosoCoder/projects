Projecttask - schoolrecords

Elektronikus osztálynaplót (E-napló) kell megvalósítani. Az alkalmazásban a School osztály az iskolát, a ClassRecords osztály pedig magát a naplót reprezentálja, ahol felvehetők a diákok (Student). A Student osztályban “tároljuk” a diákok jegyeit (Mark). A főbb funkciók a következők: A diákok feleltethetők, osztályozhatók, (jegyeket kapnak), a jegyeik alapján tantárgyak szerinti átlag számítható, két tizedesjegy pontossággal. A jegyeik ki is listázhatók a tesztesetekben megadott formában.

A schoolrecords csomagba dolgozz!



A projektfeladathoz tartozó tesztesetek megtalálhatóak a képzés publikus GitHub repojában.

Megvalósítás:
Subject osztály: Egy attribútuma van, a tantárgy nevének tárolására. Ennek alapján azonosítható a tantárgy.

Tutor osztály: Két attribútuma van, a tanár neve és a tanított tantárgyak listája, mindkettő konstruktorból tölthető fel. Az isTutorTeachingSubject(String subjectName) metódussal lekérhető, hogy a tanár tanítja-e a megadott nevű tantárgyat.

MarkType enum: A(5, "excellent"), B(4, "very good"), C(3, "improvement needed"), D(2, "close fail"), F(1, "fail") értékeket vehet fel, a klasszikus osztályzatot reprezentálja.

Mark osztály: A diák számára adott jegyeket reprezentálja, az osztályzat “értékét” az attribútumai között egy MarkType enum adja. További attribútumai a következők: Subject a tantárgy, amiből kapta a jegyet, Tutor a tanár, aki adja a jegyet. A jegy attribútumai nem állíthatók, ezért csak lekérdező metódusai vannak.

SubjectResult osztály: Speciális osztály, a diák nevét és tantárgyi átlagát tartalmazza. Attribútumai a diák neve és a tantárgyi átlaga, konstruktorból feltöltve.

Student osztály: A diák adatait - jelen esetben csak a nevét (konstruktorból feltöltve, csak getter metódus) - és a jegyeit tárolja, metódusai ezeken dolgoznak. A diák azonosítása a nevén keresztül történik. A toString() metódus a teszteseteknél látható módon a diák nevét és a jegyeit listázza ki szöveges formában.

Publikus metódusok:

public void addGrading(Mark mark) 
// érdemjegy rögzítése
public double calculateSubjectAverage(String subjectName) 
// tantárgyhoz tartozó átlag számítása, két tizedesjegy pontossággal
public String toString() 
// diák szöveges reprezentációja
ClassRecords osztály, a régi papíralapú napló egyes funkcióit reprezentálja. Attribútumai az osztály neve, egy Random objektum (konstruktorból beállítva), valamint a diákok listája. A Random objektum ilyen formában történő átadására a tesztelhetőség miatt van szükség. Diákot adhatunk hozzá és el is távolíthatunk, előbbi esetben már létező nevű diákot nem adhatunk hozzá, és eltávolítani is csak olyat lehet, aki már ott van a listában (ismét név alapján). Osztályátlagot tud számolni egy tantárgy neve alapján, véletlenszerűen ki tud választani egy diákot felelésre, meg tud keresni egy diákot név alapján és ki tudja listázni a diákok neveit és tantárgyi átlagát a SubjectResult osztály objektumainak listájaként.

Publikus metódusok:

public boolean addStudent(String name) 
// felvesz egy diákot az osztályba, ha az még nincs felvéve; 
// azzal tér vissza, hogy sikeres volt-e a diák regisztrálása 
public boolean removeStudent(String name) 
// kivesz egy diákot az osztályból;
// azzal tér vissza, hogy sikeres volt-e a  törlés
public double calculateClassAverageBySubject(String subjectName) 
//tantárgy szerinti osztályátlagot számol,
// kihagyja azon diákokat, akiknek az adott tantárgyból nincs jegye
public Student findStudentByName(String name) 
// név szerint megkeres egy diákot az osztályban
public Student repetition() 
//felelethez a listából random módon kiválaszt egy diákot
public List<SubjectResult> listSubjectResults(String subjectName) 
//a diákok nevét és tanulmányi átlagát 
// egy-egy új objektumba viszi, és azok listáját adja vissza
public String listStudentNames() 
//kilistázza a diákok neveit, vesszővel elválasztva
School osztály: Tárol egy listát a tanárokról és egyet az iskolában oktatott tantárgyakról. Ezeket fájlból tölti fel.

A listák minden osztályban privát attribútumok legyenek, ne legyen hozzájuk getter metódus! A String paraméterek nem lehetnek üresek vagy null értékűek. Ezt a vizsgálatot célszerűen egy külön, privát metódus végezze, amit minden olyan osztályban létre kell hozni, ahol használatra kerül.

Hibakezelés
Törekedjünk az átfogó hibakezelésre! A teszteseteknél látható módon, ha a metódust null paraméter értékkel hívták meg, vagy ha a lista vagy a String paraméter üres (diák keresésénél például), akkor a megfelelő szöveggel IllegalArgumentException-t várunk.

Konzolos alkalmazás
Az elsődleges cél, hogy a tesztesetek hiba nélkül lefussanak. Azonban ha szeretnél belőle működő konzolos alkalmazást, akkor oldd meg ezt a feladatot is!

Hozz létre egy SchoolController osztályt, melyben van a main() metódus! Ez csak példányosítsa az osztályt, és hívja meg a metódusait. Van egy ClassRecords és egy School attribútuma, melyeket példányosít, majd egy
initClass() metódusban létrehoz egy osztályt. (Ezeket az adatokat nem kell a felhasználótól bekérni.)

Majd írja ki a következő menüt:

1. Diákok nevének listázása
2. Diák név alapján keresése
3. Diák létrehozása
4. Diák név alapján törlése
5. Diák feleltetése
6. Tantárgyi osztályátlag kiszámolása
7. Diákok átlagának listázása egy tantárgyból
8. Egy diák egy tantárgyhoz tartozó átlagának kiszámolása
9. Kilépés
Amíg a kilépés menüpontját meg nem adja a felhasználó, az adott menüpont elvégzése után újra ki kell írni a menüt.

Menüpontonként a következő működés az elvárt:

Diákok nevének listázása - kilistázza a diákok neveit (listStudentNames())
Diák név alapján keresése - bekéri a diák nevét, és a diák szöveges reprezentációját írja ki (findStudentByName())
Diák létrehozása - bekéri a diák nevét, és felviszi az osztálynaplóba az új diákot
Diák név alapján törlése - be kell kérni a nevét, majd törölni a megfelelő diákot a naplóból
Diák feleltetése - előbb sorsolni kell egy diákot (meg kell hívni a repetition() metódust), majd bekérni az érdemjegyet, tárgy nevét, oktató nevét. A tárgyat és az oktatót ki kell keresni a listából, példányosítani ezekkel egy Mark-ot, és meghívni a addGrading() metódust.
Tantárgyi átlag kiszámolása - A felhasználó által megadott tantárgy nevével a calculateClassAverageBySubject() eredményének kiírása
Diákok átlagának listázása egy tantárgyból - A felhasználó által megadott tantárgy nevével a listSubjectResults() eredményének kiírása
Egy diák egy tantárgyhoz tartozó átlagának kiszámolása - be kell kérni a nevét, majd a tantárgy nevét, és kiírni a megfelelő átlagot.
Kilépés