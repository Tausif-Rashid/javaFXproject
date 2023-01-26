package Client;

public class RefreshThread implements Runnable{
    private Thread t;
    public static boolean transferred=false;
    public static boolean exitCall=false;
    private TableViewController controller;
    public RefreshThread (TableViewController controller)
    {
        this.controller = controller;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
            while (true)
            {
                if(transferred){
                    controller.OnReloadClick();
                    transferred =false;
                    System.out.println("clicked refresh");
                }
                else
                {
                    if(exitCall)break;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
    }
}
