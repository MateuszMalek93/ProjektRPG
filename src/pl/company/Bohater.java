package pl.company;

public class Bohater extends Istotaa {
    int LvUpPoint = 300;
    int currentLV = 1;
    public Bohater(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        super(hp, dmgDOWN, dmgUP, exp, Name);
    }
    public void Nazywacz(String imię){
        this.setName(imię);
    }

    public void LVupCheck(){
        if (getExp()>=getLvUpPoint()) {
            setCurrentLV(getCurrentLV() + 1);
            setExp(getExp()-getLvUpPoint());
            setLvUpPoint(getLvUpPoint()*2);
            System.out.println(getName() + " awansował na " +getCurrentLV() + " poziom");
            LVupGains();
        }

    }

    public void LVupGains(){
        setHP(getHP()+10);
        setCurrentHP(getHP());
        if (getCurrentLV()%2==0)
        setDmgD(getDmgD()+1);
        else
            setDmgU(getDmgU()+1);
    }

    public void info(){
        System.out.println("HP " +getHP()+"/"+getCurrentHP());
        System.out.println("DMG " +getDmgD()+" - "+ getDmgU() );
        System.out.println("LV " + getCurrentLV());
    }

    @Override
    public void dealDmg(Potwór monster) {
        super.dealDmg(monster);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            LVupCheck();
        }
    }


    //SETERY GETERY
    public int getCurrentLV() {
        return currentLV;
    }

    public int getLvUpPoint() {
        return LvUpPoint;
    }

    public void setCurrentLV(int currentLV) {
        this.currentLV = currentLV;
    }

    public void setLvUpPoint(int lvUpPoint) {
        LvUpPoint = lvUpPoint;
    }

}

