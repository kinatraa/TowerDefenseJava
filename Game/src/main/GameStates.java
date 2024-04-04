package main;

public enum GameStates {
    MENU, PLAYING, SETTINGS, MENU2, EDIT, GAME_OVER;
    public static GameStates gameState = MENU;
    public static void SetGameState(GameStates state){
        gameState = state;
    }
}
