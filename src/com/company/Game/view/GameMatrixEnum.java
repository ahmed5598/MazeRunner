package com.company.Game.view;

public enum GameMatrixEnum {
    PATH(0),
    TREE(1),
    NORMAL_BOMB(2),
    NUCLEAR_BOMB(3),
    HEALTH_GIFT(4),
    BULLET_GIFT(5),
    COIN_GIFT(6),
    ARMOR_GIFT(7),
    BRICK(8),
    TARGET(9);

    // need next to lines to get rid of error
    private int num;
    private GameMatrixEnum(int num) {}


    //use this method zay feh paint component feh class Panel
    public static GameMatrixEnum getEquivalent(int numInMatrix)
    {
        switch (numInMatrix)
        {
            case 0:
                return GameMatrixEnum.PATH;
            case 1:
                return GameMatrixEnum.TREE;
            case 2:
                return GameMatrixEnum.NORMAL_BOMB;
            case 3:
                return GameMatrixEnum.NUCLEAR_BOMB;
            case 4:
                return GameMatrixEnum.HEALTH_GIFT;
            case 5:
                return GameMatrixEnum.BULLET_GIFT;
            case 6:
                return GameMatrixEnum.COIN_GIFT;
            case 7:
                return GameMatrixEnum.ARMOR_GIFT;
            case 8:
                return GameMatrixEnum.BRICK;
            case 9:
                return GameMatrixEnum.TARGET;
            default:
                //this case should never happen aslun bas just in case
                System.out.println("MOSHKLA FEl ENUM CLASS");
                break;
        }
        return null; // to remove error
    }
}