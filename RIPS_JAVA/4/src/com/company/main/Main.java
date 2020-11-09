package com.company.main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.company.cashier.Cashier;
import com.company.customer.Customer;
import org.apache.log4j.*;

public class Main  {
    private static final Logger MAIN_LOGGER = LogManager.getLogger("main");

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

        MAIN_LOGGER.info("Creating cashiers");
        for (int i = 0; i < 3; i++) {
            cashiers.add(new Cashier("cashier "+ i, customers));
        }

        /*
         synchronized ( по сути как в си )

          Если один поток зашел внутрь блока кода, который помечен словом synchronized, он моментально захватывает мьютекс объекта,
          и все другие потоки, которые попытаются зайти в этот же блок или метод вынуждены ждать,
          пока предыдущий поток не завершит свою работу и не освободит монитор.
         */
        MAIN_LOGGER.info("Creating customers");
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
        MAIN_LOGGER.info("All customers have been served");
        System.out.println("All customers have been served");


    }
}