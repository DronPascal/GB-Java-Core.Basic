package hw4;
/*
 * "Крестики-нолики N*N" в процедурном стиле c выбором уровня сложности (на харде я не смог его выйграть)
 *
 */

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] map;
    private static int SIZE = 3;
    private static boolean EASY_MODE = false;

    private static final char DOT_X = '❌';
    private static final char DOT_O = '◯';
    private static final char DOT_U = '⬩';
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();

            int result = isGameOver(DOT_X);
            if (result == 1) {  //если DOT_X победил
                System.out.println("Well Done!");
                break;
            } else if (result == 0) {  //если ничья
                System.out.println("Draw!");
                break;
            }

            computerTurn();

            result = isGameOver(DOT_O);
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

    private static int isGameOver(char С) {
        return isGameOver(С, SIZE);
    }

    private static int isGameOver2(char С, int winStreak) {
        /*
         * Ничья -> 0
         * Игрок с символом (С) победил -> 1
         * Игра продолжается -> 2
         */
        // Проверяем победил ли C на одной из линий
        int d1 = 0, d2 = 0;  // Один раз посчитаем кол-во (С) в ряд на главной и побочной диагоналях
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == С) d1++;
            else d1 = 0;
            if (map[SIZE - i - 1][i] == С) d2++;
            else d2 = 0;

            int h = 0, v = 0;  // SIZE раз посчитаем кол-во (С) в ряд на горизонтальных и вертикальных линиях
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == С) h++;
                else h = 0;
                if (map[j][i] == С) v++;
                else v = 0;
                if (h == winStreak || v == winStreak)  // После каждой проверки символа удостоверяемся, можно ли закончить игру
                    return 1;
            }
            if (d1 == winStreak || d2 == winStreak)  // После каждой проверки символа...
                return 1;
        }
        // Если есть свободные поля, игра продолжается
        for (int i = 1; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_U)
                    return 2;
        return 0;
    }

    private static int isGameOver(char symb, int winStreak) {
        for (int row = 0; row < SIZE - winStreak; row++) {
            for (int col = 0; col < SIZE - winStreak; col++) {
                if (checkDiagonals(symb, row, col, winStreak) || checkLanes(symb, row, col, winStreak)) return 1;
            }
        }
        // Если есть свободные поля, игра продолжается
        for (int i = 1; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_U)
                    return 2;
        return 0;
    }

    private static boolean checkDiagonals(char symb, int rowOffset, int colOffset, int winStreak) {
        boolean isD1Win = true, isD2Win = true;
        for (int i = 0; i < winStreak; i++) {
            isD1Win &= (map[i + rowOffset][i + colOffset] == symb);
            isD2Win &= (map[rowOffset + winStreak - i - 1][i + colOffset] == symb);
        }
        return isD1Win || isD2Win;
    }

    private static boolean checkLanes(char symb, int rowOffset, int colOffset, int winStreak) {
        boolean isColWin, isRowWin;
        for (int row = rowOffset; row < winStreak + rowOffset; row++) {
            isColWin = true;
            isRowWin = true;
            for (int col = colOffset; col < winStreak + colOffset; col++) {
                isColWin &= (map[row][col] == symb);  // Горизонталь
                isRowWin &= (map[col][row] == symb);  // Вертикаль
            }
            if (isColWin || isRowWin) return true;
        }
        return false;
    }

    private static void makeSmartMoveAgainstEnemy(char myC, char enemyC) {  //автоматически сыграть символом myC против симвла enemyC (не руководствуясь тем, куда был сделан ход противником)
        int weakX = -1, weakY = -1;  //координаты предстоящего хода (самое слабое место противника)
        /*
         * 1) проверяем всё поле. Если можем победить, завершаем игру
         */
        int myd1 = 0, myd2 = 0, d1i = -1, d2i = -1, d2j = -1;  //один раз посчитаем кол-во НАШИХ ячеек на главной и побочной диагоналях
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == myC) myd1++;  //поиск на главной диагонали
            else if (isCellValid(i, i)) d1i = i;  //запоминаем координаты ячейки только если можем ее занять
            if (map[SIZE - i - 1][i] == myC) myd2++;  //поиск на побочной диагонали
            else if (isCellValid(SIZE - i - 1, i)) {  //запоминаем координаты ячейки только если можем ее занять
                d2i = SIZE - i - 1;
                d2j = i;
            }

            int myh = 0, myv = 0, hi = -1, hj = -1, vi = -1, vj = -1;  // SIZE раз посчитаем кол-во НАШИХ ячеек на горизонтальных и вертикальных линиях
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == myC) myh++;
                else if (isCellValid(i, j)) {  //запоминаем координаты ячейки только если можем ее занять
                    hi = i;
                    hj = j;
                }
                if (map[j][i] == myC) myv++;
                else if (isCellValid(j, i)) {  //запоминаем координаты ячейки только если можем ее занять
                    vi = j;
                    vj = i;
                }
            }
            if (myh == SIZE - 1 && hi != -1 && hj != -1) {  //если есть координаты свободной ячейки на горизонтальной линии и все остальные ячейки на ней наши
                weakX = hi;
                weakY = hj;
            }
            if (myv == SIZE - 1 && vi != -1 && vj != -1) {  //если есть координаты свободной ячейки на вертикальной линии и все остальные ячейки на ней наши
                weakX = vi;
                weakY = vj;
            }
        }
        if (myd1 == SIZE - 1 && d1i != -1) {
            weakX = d1i;
            weakY = d1i;
        }
        if (myd2 == SIZE - 1 && d2i != -1 && d2j != -1) {
            weakX = d2i;
            weakY = d2j;
        }

        /*
         * 2) если победить не вышло, будем обороняться
         */
        if (weakX == -1 && weakY == -1) {

            int maxH = 0, maxV = 0;  //максимумы занятых противником ячеек в опасных строках и столбцах
            //опасность - это кол-во занятых противником ячеек в линини.
            int dangerousH = -1, dangerousV = -1;  //самая опасная строка и столбец. У читываться будут только те, которые мы еще не заблокировали своим символом.
            int d1 = 0, d2 = 0;  //для рассчета кол-ва занятых противником ячеек на главной и побочной диагоналях
            boolean d1IsDanger = true, d2IsDanger = true;  //если в линии найдется наш знак, то изменим на false
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == enemyC) d1++;
                else if (map[i][i] == myC) d1IsDanger = false;
                if (map[SIZE - i - 1][i] == enemyC) d2++;
                else if (map[SIZE - i - 1][i] == myC) d2IsDanger = false;

                int h = 0, v = 0;  // SIZE раз посчитаем кол-во крестиков на горизонтальных и вертикальных линиях
                boolean hIsDanger = true, vIsDanger = true;
                for (int j = 0; j < SIZE; j++) {  //проверим горизонтальные и вертикальные в одном цикле
                    if (map[i][j] == enemyC) h++;
                    else if (map[i][j] == myC) hIsDanger = false;
                    if (map[j][i] == enemyC) v++;
                    else if (map[j][i] == myC) vIsDanger = false;
                }
                if (h > maxH && hIsDanger) {
                    dangerousH = i;
                    maxH = h;
                }
                if (v > maxV && vIsDanger) {
                    dangerousV = i;
                    maxV = v;
                }
            }
            if (!d1IsDanger) d1 = -1;  //если диагонали не опасны, то дальше они не будут учитываться
            if (!d2IsDanger)
                d2 = -1;  //не забываем, что если опасных горизонталей и вертикалей не было, то все еще dangerousH = -1, dangerousV = -1
            //если нет ни одной опасной линии, значит поле пусто, тогда просто сходим в центр
            if (d1 == -1 && d2 == -1 && dangerousH == -1 && dangerousV == -1) {
                weakX = (int) (SIZE / 2);
                weakY = (int) (SIZE / 2);
            }


            //здесь мы уже имеем представление о самой опасной строке, столбце и диагонялях. Осталось выбрать координаты нашего хода оптимальным способом.
            int maxDanger = Math.max(Math.max(maxH, maxV), Math.max(d1, d2));

            if (d1 == maxDanger) {  //главная диагональ
                int hm = -1;
                for (int i = 0; i < SIZE; i++) {
                    if (isCellValid(i, i)) {
                        int hn = hindranceToEnemy(i, i, myC);
                        if (hn > hm) {
                            hm = hn;
                            weakX = i;
                            weakY = i;
                        }
                    }
                }
            } else if (d2 == maxDanger) {  //побочная диагональ
                int hm = -1;
                for (int i = 0; i < SIZE; i++) {
                    int xp = SIZE - 1 - i;
                    if (isCellValid(xp, i)) {
                        int hn = hindranceToEnemy(xp, i, myC);
                        if (hn > hm) {
                            hm = hn;
                            weakX = xp;
                            weakY = i;
                        }
                    }
                }
            } else if (maxH == maxDanger) {  //опасная горизонтальная линия
                int hm = -1;
                for (int i = 0; i < SIZE; i++) {
                    if (isCellValid(dangerousH, i)) {
                        int hn = hindranceToEnemy(dangerousH, i, myC);
                        if (hn > hm) {
                            hm = hn;
                            weakX = dangerousH;
                            weakY = i;
                        }
                    }
                }
            } else if (maxV == maxDanger) {  //опасная вертикальная линия
                int hm = -1;
                for (int i = 0; i < SIZE; i++) {
                    if (isCellValid(i, dangerousV)) {
                        int hn = hindranceToEnemy(i, dangerousV, myC);
                        if (hn > hm) {
                            hm = hn;
                            weakX = i;
                            weakY = dangerousV;
                        }
                    }
                }
            } else
                do {
                    weakX = random.nextInt(SIZE);
                    weakY = random.nextInt(SIZE);
                } while (!isCellValid(weakX, weakY));

        }
        //в итоге ходим!!!
        map[weakX][weakY] = myC;
        System.out.println("Компьютер выбрал ячейку " + (weakX + 1) + " " + (weakY + 1));  //объективно это он походил, но можно и вернуть функцией пару индексов в основной цикл программы (если что, можно легко поправить в общем)
    }

    private static int hindranceToEnemy(int x, int y, char myC) {  //просчет помехи(hindrance) для противника, в случае, если мы займем ячейку
        return hindranceToEnemy(x, y, myC, SIZE);
    }

    private static int hindranceToEnemy(int x, int y, char myC, int winStreak) {  //просчет помехи(hindrance) для противника, в случае, если мы займем ячейку
        /*
         * занятие данной ячейки не помешает противнику (бессмысленно) -> 0
         * мы защитим только одну линию -> 1
         * защитим 2 линии -> 2
         * защитим 3 линии -> 3
         * защитим 4 линии -> 4
         */
        int hDefended = 0, vDefended = 0, d1Defended = 0, d2Defended = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[x][i] == myC)
                hDefended = 1;
            if (map[i][y] == myC)
                vDefended = 1;
            if (map[i][i] == myC)
                d1Defended = 1;
            if (map[SIZE - 1 - i][i] == myC)
                d2Defended = 1;
        }
        return hDefended + vDefended + d1Defended + d2Defended;
    }

}





