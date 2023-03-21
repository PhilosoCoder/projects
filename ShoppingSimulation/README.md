Feladat:

A feladat egy egyszerű bevásárlás szimulációjának fejlesztése:
- 1 vásárló belép egy boltba, felvesz egy kosarat
- A bevásárló listája elemeit, ha kaphatóak, bepakolja a kosarába
- A pénztárhoz megy
- A pénztáros átveszi a kosár tartalmát
- A vonalkód olvasóval leolvassa a termékek vonalkódját, és átviszi azt a pénztárgépbe,
ami megkeresi az egyes termékeket és összegzi az áraikat
- A pénztárgép kiírja a végösszeget
- A pénztáros megkéri a vásárlót, hogy fizessen (most csak készpénzzel tud fizetni)
- A vásárló megvizsgálja, van-e elég pénz a pénztárcájában (tegyük fel, hogy mindig van
nála elegendő összeg)
- A vásárló kiveszi a tárcájából a megfelelő összeget és átadja a pénztárosnak
- A pénztáros a kasszába helyezi a kapott pénzt és a pénztárgép blokkot nyomtat
- A pénztáros átadja a blokkot a vásárlónak, ezzel a vásárlás véget ér

Az általad preferált programnyelvet használhatod. Nincs szükség adatbázisra és
egyebekre, elég az osztályokat és az alap metódusokat/propertyket megtervezned és
megírnod (pl. raktár ellenőrzésénél elég csak egy metódus, ami true-val tér vissza, tehát
mindig kaphatóak a termékek; vagy a blokk akár lehet egy üres string is; vagy a
vásárlónak mindig van elég pénze stb.). Törekedj arra, hogy a rendszer a későbbiekben
továbbfejleszthető legyen (pl. újabb fizetési módok, áfás számla igény, stb.).