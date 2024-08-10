package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập module object: ");
        int L = scanner.nextInt();
        System.out.print("Nhập số loại Item cần cắt: ");
        int m = scanner.nextInt();
        System.out.println();

        List<Item> items = new ArrayList<>();
        System.out.println("Nhập thông tin cho  từng loại Item ");

        for (int i = 0; i < m; i++) {
            System.out.println();
            System.out.println("Nhập thông tin cho loại Item thứ " + (i + 1) + ":");
            int d, l;

            do {
                System.out.print("Note: Nhập số Item và chiều dài Item phải >=0, chiều dài Item khng được  lớn hơn module object \n");
                System.out.print("Nhập số Item cần cắt: ");
                d = scanner.nextInt();
                System.out.print("Nhập chiều dài Item: ");
                l = scanner.nextInt();

            }
            while(d<=0 || l<=0 || l>L);

            items.add(new Item(d, d, l));
        }

        //P1
        items.sort((a, b) -> b.getL() - a.getL());

        System.out.println();
        System.out.println("Danh sách các Item đã nhập và được sắp xếp lại thoe thú tự l giảm dần:");
        for (int i = 0; i < m; i++) {
            System.out.println(items.get(i).toString());
        }

        //P2
        int k = 1;
        boolean STOP = false;

        //P3

        // Khởi tạo mảng 2 chiều aik
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            a.add(new ArrayList<>());
        }

        ArrayList<Integer> x = new ArrayList<>();

        while (!STOP)
        {
            int REST = L;
            for (int i = 0; i < m; i++) {
                a.get(i).add(0);
            }

            int i =1;
            while (i<=m)
            {
                if(REST >= items.get(i-1).getL())
                {
                    Item item = items.get(i-1);
                    a.get(i-1).set(k-1, Math.min((int) Math.floor(REST/item.getL()), item.getR()));
                    REST -= a.get(i-1).get(k-1)*item.getL();
                }
                i++;
            }

        //P4

            int xk = 9999999;

            for (int j = 0; j < m; j++) {
                if(a.get(j).get(k-1)==0) continue;
                int division = (int) Math.floor(items.get(j).getR()/a.get(j).get(k-1)) ;
                xk = Math.min(division, xk);
            }
            x.add(xk);

            for (int j = 0; j < m; j++) {
                Item item = items.get(j);
                item.setR(item.getR() - x.get(k-1)*a.get(j).get(k-1));
            }

        //P5
            for (int j = 0; j < m; j++) {
                if(items.get(j).getR()!=0)
                {
                    k += 1;
                    break;
                }
                else if(j==m-1)
                    STOP = true;
            }
        }

        System.out.println();
        System.out.println("Số item loại i trong pattern k");
        for (ArrayList<Integer> row : a) {
            System.out.print("[ \t");
            for (Integer element : row) {
                System.out.print(element + "\t");
            }
            System.out.print("]");
            System.out.println();  // Xuống dòng sau khi in xong một hàng
        }

        System.out.println();
        System.out.println("Số pattern các loại");
        for(int i = 1; i<=k; i++)
            System.out.print(x.get(i-1) + "\t");
    }
}
