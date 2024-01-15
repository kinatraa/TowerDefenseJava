package main;

public enum GameStates {
    MENU, PLAYING, SETTINGS, MENU2;
    public static GameStates gameState = MENU;
    public static void SetGameState(GameStates state){
        gameState = state;
//        System.out.println(gameState);
    }
}
