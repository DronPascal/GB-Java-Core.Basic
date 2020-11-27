package hw2;

import static java.lang.Math.abs;

public class hw2 {
    public static void main(String[] args) {
        System.out.println("TASK №1");
        //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int arr[] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArr(arr);
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 0)
                arr[i] = 1;
            else if (arr[i] == 1)
                arr[i] = 0;
        printArr(arr);
        System.out.println();

        System.out.println("TASK №2");
        //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        int arr2[] = new int[8];
        for (int i = 0; i < arr2.length; i++)
            arr2[i] = i * 3;
        printArr(arr2);
        System.out.println();

        System.out.println("TASK №3");
        //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int arr3[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArr(arr3);
        for (int i = 0; i < arr2.length; i++)
            if (arr3[i] < 6)
                arr3[i] *= 2;
        printArr(arr3);
        System.out.println();

        System.out.println("TASK №4");
        //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        int n = 5;
        int arr4[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr4[i][i] = 1;
            arr4[n-i-1][i] = 1;
        }
        print2dArr(arr4);

        System.out.println("TASK №5");
        //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        int arr5[] = new int[8];
        for (int i = 0; i < arr5.length; i++)
            arr5[i] = (int) (Math.random() * 100);
        printArr(arr5);

        int max = arr5[0], min = arr5[0];
        for (int i : arr5) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        System.out.println("Max = " + max + " Min = " + min + "\n");

        System.out.println("TASK №6");
        //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
        System.out.println(checkBalance(new int[]{1, 1, 1, 2, 1}));  //→ true
        System.out.println(checkBalance(new int[]{2, 1, 1, 2, 1}));  //→ false
        System.out.println(checkBalance(new int[]{10, 10}));  //→ true
        System.out.println();

        System.out.println("TASK №7");
        //7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        int arr7[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8};  //10 эл-в
        printArr(arr7);
        displace(arr7, 2);
        printArr(arr7);
        displace(arr7, -2);
        printArr(arr7);
        displace(arr7, -2);
        printArr(arr7);
    }

    static boolean checkBalance(int[] arr) {
        int rightSum = 0, leftSum = 0;
        for (int i : arr) rightSum += i;
        for (int i : arr) {
            if (rightSum == leftSum) return true;
            else {
                leftSum += i;
                rightSum -= i;
            }
        }
        return false;
    }

    static void displace(int[] arr, int n) {  //можно быстрее
        int buf;
        int l = arr.length;
        boolean direction = (n > 0) ? true : false;
        n = abs(n % l);
        for (int i = 0; i < n; i++) {
            if (direction) {
                buf = arr[l - 1];
                for (int j = l - 1; j > 0; j--)
                    arr[j] = arr[j - 1];
                arr[0] = buf;
            } else {
                buf = arr[0];
                for (int j = 0; j < l - 1; j++)
                    arr[j] = arr[j + 1];
                arr[l - 1] = buf;
            }
        }
        return;
    }

    static void printArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    static void print2dArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
        System.out.println("\n");
    }
}
