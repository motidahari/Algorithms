package AliceAndBobFlipCoinGame;

public class GameStrategy {
    //100% true
    public static boolean GameStrategy(){
        boolean result = false;
        int AliceResult = (int)(Math.round(Math.random()));
        int BobResult = (int)(Math.round(Math.random()));
        if ((AliceResult == BobResult) || (BobResult == 1 - AliceResult)){
            result = true;
        }
        return result;
    }
}
