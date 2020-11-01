package com.company;

import java.util.Queue;

public class Cashier extends Thread {

    private Queue<Customer> customerQueue;

    public Cashier(String name, Queue<Customer> customerQueue) {
        super(name);
        this.customerQueue = customerQueue;
        this.setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Customer currentCustomer = null;
                synchronized (customerQueue) {
                    while (customerQueue.size() == 0) {
                        // Нету людей - нету работы
                        customerQueue.wait();
                    }
                    // Удаляем челоека из очереди
                    currentCustomer = customerQueue.poll();
                    // Будем все очереди
                    customerQueue.notifyAll();
                }

                System.out.println(this + " have start to serve " + currentCustomer);
                Thread.sleep(500 * currentCustomer.getTaskQty());
                System.out.println(currentCustomer.getTaskQty()+ " tasks of " + currentCustomer + " was served by " + this);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}