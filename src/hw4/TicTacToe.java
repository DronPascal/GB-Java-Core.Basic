package hw4;
/*
 * "Крестики-нолики N*N" в процедурном стиле c выбором уровня сложности и длины победной последовательности
 */

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] map;
    private static int SIZE = 5;
    private static int WIN_STREAK = SIZE < 4 ? 3 : (SIZE < 10 ? 4 : 5);
    private static boolean EASY_MODE = false;
    private static boolean isManEverWin = false;

    private static final char DOT_X = '❌';
    private static final char DOT_O = '◯';
    private static final char DOT_U = '⬩';
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        while(!isManEverWin) {
        initMap();
        printMap();
        
            while (true) {
                //makeSmartMoveAgainstEnemy(DOT_X, DOT_O);
                //humanTurn();
                randomTurn(DOT_X);

                int result = isGameOver(DOT_X, WIN_STREAK);
                if (result == 1) {  //если DOT_X победил
                    System.out.println("Well Done!");
                    isManEverWin=true;
                    break;
                } else if (result == 0) {  //если ничья
                    System.out.println("Draw!");
                    break;
                }

                computerTurn();

                result = isGameOver(DOT_O, WIN_STREAK);
                if (result == 1) {  //если DOT_X победил
                    System.out.println("You Lose!");
                    break;
                } else if (result == 0) {  //если ничья
                    System.out.println("Draw!");
                    break;
                }

                printMap();
            }

            printMap();
            System.out.println("Game Over!");
        }
    }

    /*
     * движок игры 😎
     */
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_U;
    }

    private static void printMap() {
        for (int i = 1; i < SIZE + 1; i++)
            System.out.print("\t" + i);
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + "\t");
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + "\t");
            System.out.println();
        }
    }

    private static boolean isCellValid(int x, int y) {
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE && map[x][y] == DOT_U);
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты ячейки через пробел (строка, затем столбец): ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (!isCellValid(x, y)) {
                printMap();
                System.out.println("Координаты некорректны!");
            }
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    public static void randomTurn(char myC) {
        int x = -1, y = -1;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[x][y] = myC;
        System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
    }

    private static void computerTurn() {
        int x = -1, y = -1;
        if (EASY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
            map[x][y] = DOT_O;
        } else {
            makeSmartMoveAgainstEnemy(DOT_O, DOT_X);  //сделать ход против противника(в нашем случае человека)
        }
    }

    private static int isGameOver(char myC) {
        return isGameOver(myC, WIN_STREAK);
    }

    private static int isGameOver(char myC, int winStreak) {
        for (int rowOffset = 0; rowOffset <= SIZE - winStreak; rowOffset++) {
            for (int colOffset = 0; colOffset <= SIZE - winStreak; colOffset++) {
                if (checkDiagonals(myC, rowOffset, colOffset, winStreak) || checkLanes(myC, rowOffset, colOffset, winStreak))
                    return 1;
            }
        }
        // Если есть свободные поля, игра продолжается
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_U)
                    return 2;
        return 0;
    }

    private static boolean checkDiagonals(char myC, int rowOffset, int colOffset, int winStreak) {
        boolean isD1Win = true, isD2Win = true;
        for (int i = 0; i < winStreak; i++) {
            isD1Win &= (map[i + rowOffset][i + colOffset] == myC);
            isD2Win &= (map[rowOffset + winStreak - i - 1][i + colOffset] == myC);
        }
        return (isD1Win || isD2Win);
    }

    private static boolean checkLanes(char myC, int rowOffset, int colOffset, int winStreak) {
        boolean isColWin, isRowWin;
        for (int row = rowOffset; row < winStreak + rowOffset; row++) {
            isRowWin = true;
            isColWin = true;
            for (int col = colOffset; col < winStreak + colOffset; col++) {
                isRowWin &= (map[row][col] == myC);
                isColWin &= (map[col][row] == myC);
            }
            if (isRowWin || isColWin) return true;
        }
        return false;
    }

    private static void makeSmartMoveAgainstEnemy(char myC, char enemyC) {  //автоматически сыграть символом myC против симвла enemyC
        makeSmartMoveAgainstEnemy(myC, enemyC, WIN_STREAK);
    }

    private static void makeSmartMoveAgainstEnemy(char myC, char enemyC, int winStreak) {  //автоматически сыграть символом myC против симвла enemyC
        int smartX = -1, smartY = -1;  //координаты предстоящего хода
        /*
         * 1) Проверяем всё поле. Если можем победить, завершаем игру
         */
        if (tryToWin(myC, winStreak)) return;
        /*
         * 2) Спасаемся, если у противника есть winStreak-1 символов в ряд, а рядом свободная ячейка(занимаем ее)
         * Сходить так, как мог бы победить противник(если передать ему ход), но сделать это своим символом
         */
        if (tryToWin(enemyC, myC, winStreak)) return;
        /*
         * 3) Если победить не вышло, будем обороняться. Находим самые опасные для нас точки.
         * Для этого заполним карту уровня опасности.
         */
        int[][] hazardMap = getHazardMap(myC, enemyC, winStreak);
        // Узнаем максимальный уровень опасности и кол-во клеток с максимальным уровнем опасности.
        int maxHazardLevel = 0;
        int mhlCellCount = 0;  // Кол-во ячеек с макс. уровнем опасности (mhl)
        int maxHazardLevelRow = -1, maxHazardLevelCol = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (hazardMap[i][j] > maxHazardLevel) {
                    maxHazardLevel = hazardMap[i][j];
                    mhlCellCount = 1;
                    maxHazardLevelRow = i;
                    maxHazardLevelCol = j;
                } else if (hazardMap[i][j] == maxHazardLevel)
                    mhlCellCount++;
            }
        }
        /*
         * 4) Защищаемся с максимальной помехой для противника.
         * Если ячейка с максимальным уровнем всего одна, то занимаем ее.
         * Иначе, делаем ход с максимальной помехой для противника,
         * выбирая из всех ячеек с максимальным уровнем опасности.
         */
        if (mhlCellCount == 1) {
            smartX = maxHazardLevelRow;
            smartY = maxHazardLevelCol;
        } else {
            int maxHindrance = 0;
            int mhCellRow = -1, mhCellCol = -1;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (hazardMap[i][j] == maxHazardLevel) {
                        int curHindrance = hindranceToEnemy(i, j, winStreak);
                        if (curHindrance > maxHindrance) {
                            maxHindrance = curHindrance;
                            mhCellRow = i;
                            mhCellCol = j;
                        }
                        if (curHindrance == maxHindrance) {
                            mhCellRow = i;
                            mhCellCol = j;
                        }
                    }
                }
            }
            smartX = mhCellRow;
            smartY = mhCellCol;
        }

        /*
         * В итоге ходим!!!
         */
        map[smartX][smartY] = myC;
        System.out.println("Компьютер выбрал ячейку " + (smartX + 1) + " " + (smartY + 1));
    }

    private static boolean tryToWin(char myC, int winStreak) {
        return tryToWin(myC, myC, winStreak);
    }

    private static boolean tryToWin(char myC, char charToUse, int winStreak) {
        for (int rowOffset = 0; rowOffset <= SIZE - winStreak; rowOffset++) {
            for (int colOffset = 0; colOffset <= SIZE - winStreak; colOffset++) {
                if (tryToWinDiagonals(myC, charToUse, rowOffset, colOffset, winStreak) || tryToWinLanes(myC, charToUse, rowOffset, colOffset, winStreak))
                    return true;
            }
        }
        return false;
    }

    private static boolean tryToWinDiagonals(char myC, char charToUse, int rowOffset, int colOffset, int winStreak) {
        int d1 = 0, d2 = 0, d1U = 0, d2U = 0;
        boolean d1Winnable = false, d2Winnable = false;
        for (int i = 0; i < winStreak; i++) {
            char curD1Char = map[i + rowOffset][i + colOffset];
            char curD2Char = map[rowOffset + winStreak - i - 1][i + colOffset];

            if (curD1Char == myC) d1++;
            else if (curD1Char == DOT_U) {
                d1U = i;
                d1Winnable = true;
            }
            if (curD2Char == myC) d2++;
            else if (curD2Char == DOT_U) {
                d2U = i;
                d2Winnable = true;
            }
        }
        if (d1 == winStreak - 1 && d1Winnable) {
            map[d1U + rowOffset][d1U + colOffset] = charToUse;
            System.out.println("Компьютер выбрал ячейку " + (d1U + rowOffset + 1) + " " + (d1U + colOffset + 1));
            return true;
        } else if (d2 == winStreak - 1 && d2Winnable) {
            map[rowOffset + winStreak - d2U - 1][d2U + colOffset] = charToUse;
            System.out.println("Компьютер выбрал ячейку " + (rowOffset + winStreak - d2U) + " " + (d2U + colOffset + 1));
            return true;
        }
        return false;
    }

    private static boolean tryToWinLanes(char myC, char charToUse, int rowOffset, int colOffset, int winStreak) {
        int h, v, hUi = 0, hUj = 0, vUi = 0, vUj = 0;
        boolean hWinnable = false, vWinnable = false;
        for (int row = rowOffset; row < winStreak + rowOffset; row++) {
            h = 0;
            v = 0;
            hWinnable = false;
            vWinnable = false;
            for (int col = colOffset; col < winStreak + colOffset; col++) {
                char curHChar = map[row][col];
                char curVChar = map[col][row];
                if (curHChar == myC) h++;
                else if (curHChar == DOT_U) {
                    hWinnable = true;
                    hUi = row;
                    hUj = col;
                }
                if (curVChar == myC) v++;
                else if (curVChar == DOT_U) {
                    vWinnable = true;
                    vUi = col;
                    vUj = row;
                }
            }
            if (h == winStreak - 1 && hWinnable) {
                map[hUi][hUj] = charToUse;
                System.out.println("Компьютер выбрал ячейку " + (hUi + 1) + " " + (hUj + 1));
                return true;
            } else if (v == winStreak - 1 && vWinnable) {
                map[vUi][vUj] = charToUse;
                System.out.println("Компьютер выбрал ячейку " + (vUi + 1) + " " + (vUj + 1));
                return true;
            }
        }
        return false;
    }

    public static int[][] getHazardMap(char myC, char enemyC, int winStreak) {
        /*
         * -2 - символ противника
         * -1 - наш символ
         * от 0 до 8*(winStreak-1)+1 - уровень опасности клетки.
         */
        int[][] hazardMap = new int[SIZE][SIZE];
        int hazardRange = winStreak - 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == enemyC) {
                    hazardMap[i][j] = -2;
                } else if (map[i][j] == myC) {
                    hazardMap[i][j] = -1;
                } else {
                    hazardMap[i][j] = 0;
                    /*
                     * Просчитываем кол-во символов противника на расстоянии winStreak-1 по
                     * диагоналям, горизонтали и вертикали относительно точки [i,j].
                     * Это кол-во будет равняться уровню опасности клетки [i,j].
                     */
                    //  Вертикаль
                    for (int row = Math.max(0, i - hazardRange); row <= Math.min(SIZE - 1, i + hazardRange); row++) {
                        if (map[row][j] == enemyC) hazardMap[i][j]++;
                    }
                    //  Горизонталь
                    for (int col = Math.max(0, j - hazardRange); col <= Math.min(SIZE - 1, j + hazardRange); col++) {
                        if (map[i][col] == enemyC) hazardMap[i][j]++;
                    }
                    // Главная диагональ
                    int startRow = i, startCol = j, offset = 0;
                    while (startRow > 0 && startCol > 0 && offset < hazardRange) {
                        startCol--;
                        startRow--;
                        offset++;
                    }
                    for (int row = startRow, col = startCol; row <= Math.min(SIZE - 1, i + hazardRange) && col <= Math.min(SIZE - 1, j + hazardRange); row++, col++) {  // Главная диагональ
                        if (map[row][col] == enemyC) hazardMap[i][j]++;
                    }
                    // Побочная диагональ
                    startRow = i;
                    startCol = j;
                    offset = 0;
                    while (startRow < SIZE - 1 && startCol > 0 && offset < hazardRange) {
                        startCol--;
                        startRow++;
                        offset++;
                    }
                    for (int row = startRow, col = startCol; row >= Math.max(0, i - hazardRange) && col <= Math.min(SIZE - 1, j + hazardRange); row--, col++) {  // Главная диагональ
                        if (map[row][col] == enemyC) hazardMap[i][j]++;
                    }
                }
            }
        }
        return hazardMap;
    }

    private static int hindranceToEnemy(int x, int y) {
        return hindranceToEnemy(x, y, WIN_STREAK);
    }

    private static int hindranceToEnemy(int i, int j, int winStreak) {  //просчет помехи(hindrance) для противника, в случае, если мы займем ячейку
        /*
         * Представим, что из точки [i,j] выходит 8 лучей.
         * Луч прерывается, если касается границы поля.
         * Если длина отдельно взятого луча равна хотя бы winStreak-1,
         * то мы увеличиваем помеху на 1.
         * Максимальный уровень помехи равен 8.
         */
        int hindrance = 0;
        boolean N = false, S = false, W = false, E = false; // Север, Юг, Запад, Восток.
        if (i >= winStreak - 1) {  // N
            hindrance++;
            N = true;
        }
        if (j >= winStreak - 1) {  // S
            hindrance++;
            S = true;
        }
        if (i <= SIZE - winStreak) {  // W
            hindrance++;
            W = true;
        }
        if (j <= SIZE - winStreak) {  // E
            hindrance++;
            E = true;
        }
        if (N && W) hindrance++;
        if (N && E) hindrance++;
        if (S && W) hindrance++;
        if (S && E) hindrance++;

        return hindrance;
    }
}





