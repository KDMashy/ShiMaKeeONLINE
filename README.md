# Ideiglenes Cím: A fantasy online rpg

## Design terv "magyarázat"

 A design-t nem terveztem úgy, hogy alapvetően nagy szerepet játszon
azon kívűl, hogy a megjelenést és a UI szerepét szolgálja.
 UX szempontjából viszonylag elfogadható, a színek ideiglenesen színpalettán
lesznek kiválasztva, majd később implementálva, a tervezet ebből a szempontból
kicsit megtévesztő lehet, de az csak egy kezdetleges terv az elrendezés szempontjából.
 A design terv pencil project-tel készült.

## Webapp részei

* MainWeb
    * Bal felső sarokban:
        * Logo a játéknak
        * Játék címe, kattintható link
    * Logo, cím alatt:
        * Frissítések és ujdonságok lista
    * About us:
        * Külön minimális, rólunk/rólam szóló adatot tartalmazó weblap
        * VAGY egy beszédablak JS-segítségével
    * Jobb oldalon:
        * Login:
            * Felhasználónév
            * Jelszó
            * Belépés ellenőrzés (+Capcha)
        * Register:
            * Email cím
            * Felhasználónév
            * Jelszó (+annak megerősítése)
            * Capcha
            * Register gomb
    * A háttérkép még eldöntendő, saját grafika valószínűleg
* LoggedIn
    * Baloldal:
        * Side menu, itt lehet navigálni a weblapon
    * Felül:
        * Játék logo, név (kattintható link)
        * Játékos statisztikái
            * Tapasztalatipontok mennyisége
            * Karakter Szintje
            * Életerő
            * Sebzés
            * Pénz mennyiség
        * A háttérkép még eldöntendő, saját grafika valószínűleg
    * Középen / Main:
        * A kiválasztott menüpont tartalma itt jelenik meg
            * Valószínűleg a menüpontok:
                * Kezdőlap (Frissítések, Újdonságok)
                * Karakter statisztikák
                * Küldetések (felvétel/harc/leadás [Kudarc/siker lehetőségek])
                * Map (Statikus, csak mapID-ket tárolunk róla)
                * Bolt (?valódi pénzes? / Játék pénzt használó)
                * Információk a játékról, illetve rólunk / rólam
    * Jobb oldalon a kisebb sáv:
        * Újdonságok illetve egyéb dolgok megjelenítése (még eldöntendő)
            * Minden "cikk" a tervben rendelkezik:
                * Cím
                * Leírás / Információ
                * Link ami a teljes Change-loghoz vezet

## Nyelv, osztályok, változók (felépítés) illetve tervek a fejlesztésre

GitHub/Gitlab Használata
WebApplication lesz a projekt/"játék"
A kód Java nyelven fog íródni
Programok amik használva lesznek fixen:
* NetBeans 8.2 (Backend - Java)
* MySQL Workbench (még nem, de ezen fogunk tanulni is elvileg)
* Brackets (Frontend - HTML5/CSS/JS)

> A programkód nyomokban kommenteket tartalmazhat majd.

### Osztályok - Tulajdonságok

ERD modell alapján elkészített rajz (ProgSoloGameDBPlan.png)

* RegPlayer (Regisztráció)
    * Nickname (Egyedi)
    * Password
    * Email
    * PID (Azonosítás a felhasználóval)
    * Megjegyzendő, hogy ez össze van kapcsolva:
        * A karakterrel a PID-n keresztül 1:1 kapcsolatban, meghatározó
* GameActor (Felhasználó/player)
    * PID (Őstől kapott fix érték)
    * XP (tapaszalati pontok)
    * LvL (Kiszámolt érték az XP alapján)
    * Money
    * HP (Őstől kapott, számolt érték)
    * ATK (Őstől kapott, számolt érték)
    * Inventory (PID alapján azonosított)
        * Weapon
            * Name
            * WID
            * ATK
        * Armor
            * Name
            * AID
            * HP
    * MapID (Helyszín raktározása)
* GeneralEntity (Absztrakt osztály)
    * HP (életerő)
    * ATK (sebzés)
    * Megjegyzendő, hogy ez össze van kapcsolva:
        * A karakterrel PID alapján
        * Az ellenféllel EID alapján
* Enemy (ellenfél)
    * EID (Azonosítás egy ellenféllel)
    * LvL (Quest függő, kiszámolt érték)
    * HP (Őstől kapott, számolt érték)
    * ATK (Őstől kapott, számolt érték)
* Quest (Küldetések)
    * MapID (Karaktertől kapott infó, ez alapján kap questet adott helyen)
    * QID (Adott questek megcímkézésére használatos)
    * QuestName
    * QuestDescription
    * QuestSuggestedLvL (Nehézség alapján kiszámolt érték)
    * QuestReward (Lemaradt a db tervről, Questhez kapcsolható, nehézség alapján számolt érték)
    * Megjegyzendő, hogy ez össze van kapcsolva:
        * A karakterrel PID alapján
        * Az ellenféllel EID alapján
* Functions (Generikus funkciók)

### Előbbiek alkalmazása / feladataik

 Regplayer felelős a regisztrált karakter létrehozásáért,
amikor regisztrálunk, létrehoz egy egyedi Player ID-t (PID),
hogy egyértelmúen azonosítható legyen adatbázisból, és létrehoz hozzá
egy karaktert.

 GameActor felelős a karakter létrehozásáért mint külön objektum,
és kezeléséért a játék menete közben, tapasztalati pontjainak száma
egyedi, karakterhez kötött, a szint ebből számolt érték,
az életerő és sebzés egy ősosztályból (mely absztrakt) származó értékek,
melyek a számolt szintből lesznek létrehozva.
 
 > A későbbiekben lehet lesznek bevezetve +fegyverek illetve +armor
 
 GeneralEntity az absztrakt osztály, mely értékei az életerő és
sebzés, innen öröklődnek ezek tovább a GameActor, illetve Enemy osztályoknak.

 Enemy felelős az ellenfél létrehozásáért betöltéskor, szintje egy
küldetés nehézsége alapján számított érték, egyedi azonosítójuk az 
Enemy ID (EID), illetve a HP és ATK tulajdonsága származtatott, majd
szint alapján kiszámolt értékek

 Quest osztály felelős a küldetések létrehozásáért, a Quest ID (QID)
segítségével listázzuk adatbázisban ki őket, tulajdonságai a küldetés neve
leírása, és egy a karakter szintjéből számolt érték, a QuestSuggestedLvL
ami alapján a küldetéshez tartozó ellenfelek szintjét is számolja.
A quest PID és EID-vel tájékozódik a karakter és ellenfelek körül.
A felhasználó egyszerre egy küldetést teljesíthet, mely lehet nem tartalmaz
ellenfelet sem, csak jutalmat (XP + pénz).

 A Functions class egy generikus osztályként szolgál az egyes funkciók,
algoritmusok, melyek szükséges a webapp működéséhez:

> classok
- [ ] Regplayer
- [ ] GameActor
- [ ] GeneralEntity
- [ ] Enemy
- [ ] Quest

> Funkciók
- [ ] Login
- [ ] Register
- [ ] XP ből szint számolás - Char
- [ ] HP és ATK számolás szintből - Char, Enemy
- [ ] Küldetés felvétele/elvetése (amíg a quest megy, halálig, oldal elhagyásig)
- [ ] Küldetés jutalmak kiosztása siker esetén
- [ ] Weapon/Armor +hp +atk számolás
- [ ] Shop-ban vásárlás
- [ ] Map

> Egyéb infók gyanánt, még tervezés alatt áll a projekt, 
 Weapon illetve Armor téren változhat majd minimálisan, de nem változtatja
 meg annyira az adatszerkezetet

> Készítette: Klepe Dominik / KDMashy