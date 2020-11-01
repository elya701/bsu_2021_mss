package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Cashier> cashiers = new LinkedList<>();
        /*

        2 часть
        ConcurrentLinkedQueue<E>
        В имплементации используется wait-free алгоритм от Michael & Scott,
        адаптированный для работы с garbage collector'ом.
        Этот алгоритм довольно эффективен и, что самое важное, очень быстр, т.к. построен на CAS.
        Метод size() может работать долго, т.ч. лучше постоянно его не дергать.
        */

        ConcurrentLinkedQueue<Customer> customers = new ConcurrentLinkedQueue<Customer>();

        for (int i = 0; i < 3; i++) {
            cashiers.add(new Cashier("Cashier "+ i, customers));
        }

        /*
         synchronized ( по сути как в си )

          Если один поток зашел внутрь блока кода, который помечен словом synchronized, он моментально захватывает мьютекс объекта,
          и все другие потоки, которые попытаются зайти в этот же блок или метод вынуждены ждать,
          пока предыдущий поток не завершит свою работу и не освободит монитор.
         */
        for (int i = 0; i < 10; i++) {
            synchronized (customers) {
                customers.add(new Customer("Customer " + i, 1 + (int) (9 * Math.random())));
                customers.notifyAll();
            }
        }

        synchronized (customers){
            while (!customers.isEmpty()){
                customers.wait();
            }
        }

        System.out.println("All customers have been served");


    }
}