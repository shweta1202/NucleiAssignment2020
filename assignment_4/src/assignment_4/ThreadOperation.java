package assignment_4;

public class ThreadOperation {

    DatabaseConnection obj = new DatabaseConnection();
    public void run() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.fetchData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.calculateAndDisplayoperation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        obj.closeConnection();
    }
}
