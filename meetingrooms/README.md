# java-sv3-projects-NagyGabor

Projecttask - meetingroom register

Készíts egy programot, mely egy irodaházban lévő tárgyalókat tart nyilván!

A program induláskor kérje be a rögzítendő tárgyalók adatait a felhasználótól!

Ezután írjon ki a felhasználónak egy menüt! Ennek tartalma:

1. Tárgyalók sorrendben
2. Tárgyalók visszafele sorrendben
3. Minden második tárgyaló
4. Területek
5. Keresés pontos név alapján
6. Keresés névtöredék alapján
7. Keresés terület alapján
8. Kilépés
   Majd kérje be a felhasználótól a menüpont számát és hajtsa végre a számhoz tartozó funkciót! A funkció végrehajtása után érjen véget a program!

Tárgyalók sorrendben: csak a neveket kell kiírni olyan sorrendben, ahogy a felhasználó beírta.
Tárgyalók visszafele sorrendben: csak a neveket kell kiírni, fordított sorrendben, mint ahogy a felhasználó beírta
Minden második tárgyaló: minden második tárgyaló nevét kell kiírni (A felhasználó adhassa meg, hogy a páros vagy a páratlan sorszámú tárgyalókat szeretné lekérni).
Területek: ki kell írni minden tárgyaló nevét, szélességét, hosszúságát és területét.
Keresés pontos név alapján: kérj be a felhasználótól egy nevet, és annak a tárgyalónak írd ki a szélességét, hosszúságát és területét, melynek ez a neve! Ha nincs ilyen nevű, nem kell kiírni semmit.
Keresés névtöredék alapján: kérj be a felhasználótól egy névtöredéket, és annak a tárgyalónak írd ki a szélességét, hosszúságát és területét, amelynek a nevében benne van ez a névtöredék! Ne számítson a kis- és nagybetű különbség!
Keresés terület alapján: kérj be egy egész számot, és csak azoknak a tárgyalóknak írd ki a nevét, szélességét, hosszúságát és területét, melyeknek a területe nagyobb, mint a felhasználó által beírt terület!
Megvalósítás
Dolgozz a meetingrooms csomagba!



A MeetingRoomController osztálynak legyen egy main() metódusa! Ez példányosít egy MeetingRoomController példányt, és meghívja a runMenu() metódust.

A runMenu() először bekéri a felhasználótól, hogy hány tárgyalót szeretne rögzíteni a rendszerben, majd sorban kérje be azok adatait (nevét, szélességét és hosszúságát méterben, egész számként)! Ezeket az adatokat adja át az Office addMeetingRoom() metódusának, az pedig példányosítson a kapott adatok alapján egy új tárgyalót, majd adja hozzá azt az osztály listájához!

A runMenu() ezután meghívja a printMenu()-t (ami kiírja a menüt a felhasználónak), bekéri a felhasználótól a menüpont számát, majd egy elágazás alapján meghívja az Office megfelelő metódusát. Ha az vár valamilyen paramétert, akkor azt is ez a metódus kéri be a felhasználótól.

Az Office metódusai rendre megvalósítják a leírt funkciókat. Ciklusokkal és feltételekkel dolgoznak. Amelyiknek listát kell visszaadnia, az egy olyan listát adjon vissza, amelyet újonnan példányosított, majd összegyűjtötte benne a kiválogatott tárgyalókat. Amelyiknek pedig csak egy tárgyalót kell visszaadnia, az a megfelelő feltétel alapján válassza ki azt, majd adja vissza. Gondolj arra az esetre is, ha nincs találat! Az eredményt végül a MeetingRoomController valamely metódusa írja ki a konzolra!

A MeetingRoom egy egyszerű osztály, három attribútummal. Van egy getArea() metódusa, mely kiszámolja a tárgyaló területét.

Fontos irányelvek, amikre figyelj oda a projekt megírása közben
A felhasználóval történő kommunikációt kizárólag a MeetingRoomController osztály metódusai valósítsák meg, a másik két osztályban sehol ne történjen a felhasználótól adatbekérés vagy konzolra kiíratás!

A megadott UML diagram a mindenképpen szükséges metódusok neveit tartalmazza. Ahol szükséged lenne más kisegítő metódusra, nyugodtan hozd azt létre, private láthatósággal.

Törekedj arra, hogy egy metódus egyféle dolgot csináljon! Ha több feladatot szeretnél neki adni, akkor a többlet feladatokat szervezd ki újabb metódusokba, és hívd azokat!

Törekedj arra is, hogy minél kevesebb legyen a kódodban az ismétlés! Ha az utasítások egy csoportját többször is le kellene írnod, inkább szervezd ki az ismétlődő utasításokat egy külön metódusba, hogy csak egyszer őket kelljen leírni!

Törekedj a minél rövidebb metódusokra! Egy metódus lehetőleg legfeljebb 15 sor hosszú legyen! (És ettől csak nagyon indokolt esetben térj el.)