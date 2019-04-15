public class StackOverFlow {

    private long i;

    public void plus() {
        i++;
        plus();

    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Exception e) { // Exception是抓不到的
            System.out.println(String.format("Exception:stack length:%d", stackOverFlow.i));
            //System.out.println("Exception:stack length:"+stackOverFlow.i);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println( String.format("Error:stack length::%d", stackOverFlow.i));
            //System.out.println("Error:stack length:"+stackOverFlow.i);
            e.printStackTrace();
        }
    }
}
