package AliceAndBobFlipCoinGame;

import static Moti.AliceAndBobFlipCoinGame.GameStrategy.GameStrategy;

public class main {

    public static void main(String[] args) {

        int count = 10000000;
        int GameStrategy = 0;
        boolean result = false;
        for (int i = 0; i < count; i++) {
            result = GameStrategy();
            if (result == true){
                GameStrategy++;
            }
        }
        System.out.println("Alice & Bob game : Strategy probability = " + 100*((double) GameStrategy / (double) count) + "%");
    }
}
