public class Counter {
    private int count=0;
    private int max=10;

    public Counter(int count, int max) {
        this.count = count;
        this.max = max;
    }

    public Counter() {
    }
    public void increase(){
        int a =getCount();
        setCount(a+2);
        if ((a+2)>10){
            setCount(10);
        }
    }
    public void increase(int m){
        int a =getCount();
        setCount(a+m);
        if ((a+m)>10){
            setCount(10);
        }
    }
    public void decrease(){
        int a =getCount();
        setCount(a-1);
        if ((a-1)<0){
            setCount(0);
        }
    }

    public void decrease(int y){
        int a =getCount();
        setCount(a-y);
        if ((a-y)<0){
            setCount(0);
        }
    }
    public void doubler(){
        int a =getCount();
        setCount(a*2);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "count=" + count +
                ", max=" + max +
                '}';
    }

    public void reset(){
        setCount(0);
        System.out.println("Counter Reset!");
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static void main(String[] args) {
        Counter hh=new Counter();
        hh.setCount(3);
        hh.increase();
        int a= hh.getCount();
        System.out.println(a);
        System.out.println(hh);
    }
}
