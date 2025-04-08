/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author admin
 */
public class Deadlock {
     static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
//        public void bow(Friend bower) {
//            System.out.print(Thread.currentThread().getName()+""+this);
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);

        }
        public synchronized void bowBack(Friend bower) {
//        public void bowBack(Friend bower) {
//            System.out.print(Thread.currentThread().getName());
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args)  throws Exception{
        final Friend alphonse =            new Friend("Alphonse");
        final Friend gaston =            new Friend("Gaston");
//        final Friend alphonse =            new Deadlock().new Friend("Alphonse");
//        final Friend gaston =            new Deadlock().new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
//        Thread.sleep(1000);
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}
