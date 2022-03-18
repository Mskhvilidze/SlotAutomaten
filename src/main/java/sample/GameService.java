package sample;

public class GameService {
    private int money;

    public void setMoney(int money) {
        this.money = money;
    }

    public void insert(int cash) {
        this.money += cash;
    }

    public void raiseMoney(int cash) {
        this.money -= cash;
    }

    public void won(int cash) {
        this.money += cash;
    }

    public int getMoney() {
        return money;
    }
}
