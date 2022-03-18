package sample;

public class GameService {
    private int money;
    final private int[] arr = new int[2];

    public void setMoney(int money) {
        this.money += money;
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

    public int[] onDice() {
        int a = (int)((Math.random()) * 5 + 1);
        int b = (int)((Math.random()) * 5 + 1);
        arr[0] = a;
        arr[1] = b;
        return arr;
    }
}
