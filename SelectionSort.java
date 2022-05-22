import java.util.*;
import java.io.*;


public class SelectionSort{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter array size");
        int size = sc.nextInt();
        int[] array1 = new int[size];
        System.out.println("Enter array values");

        for(int i=0;i < size;i++)
        {
            array1[i] = sc.nextInt();
        }
        int min,temp = 0;
        for(int i =0; i < array1.length ; i++){
            min = i;
            for(int j = i + 1; j < array1.length ; j++){
                if(array1[j] < array1[min]){
                    min = j;
                }
            }
            temp = array1[i];
            array1[i] = array1[min];
            array1[min] = temp;
        }
        System.out.println("Sorted array");
        for(int i =0; i < array1.length ; i++){
            System.out.println(array1[i]);
        }
    }
}
