package pl.company;

import java.util.Scanner;

public class Hero extends Being {
    int lvUpPoint = 200;
    int currentLV = 1;
    int attackModifier = 0;
    int critSwordAttackModifier = 0;
    int critShieldAttackModifier = 0;
    int shieldAttackChanceModifier = 0;
    String rank = "Pachołek";
    Scanner scan = new Scanner(System.in);
    int potionQuantity = 3;

    //konstruktor
    public Hero(int hp, int dmgDOWN, int dmgUP, int exp, String name) {
        super(hp, dmgDOWN, dmgUP, exp, name);
    }

    //funkcja nazywająca bohatera
    public void namer(String name) {
        this.setName(name);
    }

    //funkcja sprawdzająca czy bohater kwalifikuje się do awansu na wyższy poziom, jeżeli tak następuje awans
    public void lvUpCheck() {
        if (getExp() >= getLvUpPoint()) {
            setCurrentLv(getCurrentLv() + 1);
            setExp(getExp() - getLvUpPoint());
            setLvUpPoint(getLvUpPoint() * 2 - getLvUpPoint() / 15 * 10);
            System.out.println(getName() + " awansował na " + getCurrentLv() + " poziom");
            lvUpGains();
        }

    }

    //Funkcja zwiększająca statystyki w skutek zwiększenia poziomu
    public void lvUpGains() {
        setHP(getHP() + 10);
        setCurrentHP(getHP());
        if (getCurrentLv() % 2 == 0)
            setDmgD(getDmgD() + 1);
        else
            setDmgU(getDmgU() + 1);
        if (getCurrentLv() == 5)
            rank5Lv();
        if (getCurrentLv() == 10)
            rank10Lv();
        if (getCurrentLv() == 15)
            rang15Lv();
        System.out.println();
    }

    //Funkcja dodająca rangę na 5 lv
    public void rank5Lv() {
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Wojownikiem");
            setRank("Wojownik");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"t\" Tarczownik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne");
            System.out.println("Wpisz \"m\" Miecznik - +2 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, jego atak zawsze trafia");

            switch (scan.next()) {
                case "m" -> {
                    attackModifier += 2;
                    critSwordAttackModifier += 15;
                    setRank("Wojownik - Miecznik ");
                    progresRanga = true;
                }
                case "t" -> {
                    shieldAttackChanceModifier += 10;
                    critShieldAttackModifier += 25;
                    setRank("Wojownik - Tarczownik");
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }


    }

    //Funkcja dodająca rangę na 10 lv
    public void rank10Lv() {
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRank("Mistrz");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"h\" Huskarl - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"s\" Szarmierz - +4 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "s" -> {
                    attackModifier += 4;
                    critSwordAttackModifier += 20;
                    setRank("Mistrz - Szermierz ");
                    progresRanga = true;
                }
                case "h" -> {
                    if (shieldAttackChanceModifier != 0) {
                        shieldAttackChanceModifier += 5;
                        critShieldAttackModifier += 5;
                    } else {
                        shieldAttackChanceModifier += 10;
                        critShieldAttackModifier += 25;
                        setRank("Mistrz - Huskarl");
                    }
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }

    }

    //Funkcja dodająca rangę na 15 lv
    public void rang15Lv() {
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRank("Czempion");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"p\" Pawężnik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"m\" Mistrz Miecza - +6 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "m" -> {
                    attackModifier += 5;
                    critSwordAttackModifier += 25;
                    setRank("Champion - Mistrz Miecza ");
                    progresRanga = true;
                }
                case "p" -> {
                    if (shieldAttackChanceModifier != 0) {
                        shieldAttackChanceModifier += 5;
                        critShieldAttackModifier += 5;
                    } else {
                        shieldAttackChanceModifier += 15;
                        critShieldAttackModifier += 25;
                        setRank("Champion - Pawężnik");
                    }
                    progresRanga = true;
                }
                default -> System.out.println("Błędna komenda");
            }
        }


    }

    //funkcja zwracająca informacje o bohaterze iloś HP, min max DMG i LV
    public void info() {
        System.out.println();
        System.out.println(getName());
        System.out.println("HP " + getCurrentHP() + "/" + getHP());
        System.out.print("DMG " + getDmgD() + "-" + getDmgU());
        if (attackModifier > 0)
            System.out.println(" + " + attackModifier);
        else
            System.out.println();
        System.out.println("LV " + getCurrentLv());
        System.out.println("Exp - " + getExp() + "/" + getLvUpPoint());
        System.out.println("Ranga " + getRanga());
        System.out.println("Mikstury - " + potionQuantity);
        System.out.println();
    }

    // funkcja przyznająca pkt doświadczenia i mikstury
    public void getExpAndSpoils(Monster monster) {
        int chance = rdm.nextInt(100) + 1;
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 85) {
                potionQuantity++;
                System.out.println("Znalazłeś miksturę!");
            }
            lvUpCheck();
        }
    }

    // Zmodyfikowana funkcja deal dmg dodająca exp bohaterowi po wygranej walce
    @Override
    public void dealDmg(Monster monster) {
        super.dealDmg(monster);
        getExpAndSpoils(monster);
    }

    @Override
    public void criticalHit(Monster monster) {
        super.criticalHit(monster);
        getExpAndSpoils(monster);
    }

    @Override
    public void parryAttackSuccess(Monster monster, Hero hero) {
        super.parryAttackSuccess(monster, hero);
        getExpAndSpoils(monster);
    }

    //Użycie mikstury
    public void usePotion() {
        int HP = rdm.nextInt(getHP() / 4) + getHP() / 6;
        setCurrentHP(getCurrentHP() + HP);
        if (getCurrentHP() > getHP())
            setCurrentHP(getHP());
        potionQuantity--;
    }



    //SETERY GETERY

    @Override
    public int getAttackDmg() { return super.getAttackDmg() + attackModifier; }

    public int getCurrentLv() {
        return currentLV;
    }

    public int getLvUpPoint() {
        return lvUpPoint;
    }

    public void setCurrentLv(int currentLV) {
        this.currentLV = currentLV;
    }

    public void setLvUpPoint(int lvUpPoint) {
        this.lvUpPoint = lvUpPoint;
    }

    public String getRanga() {
        return rank;
    }

    public void setRank(String ranga) {
        this.rank = ranga;
    }


}

