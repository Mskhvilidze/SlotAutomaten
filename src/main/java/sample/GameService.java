package sample;

public class GameService {
    private int money;

    public void setMoney(int money) {
        this.money = money;
    }

    public void insert(int cash){
        this.money += cash;
    }

    public int getMoney() {
        return money;
    }
}
