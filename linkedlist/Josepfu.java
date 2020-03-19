package linkedlist;

public class Josepfu {
    public static void main(String[] arg) {

        // Josephu 问题
        // Josephu 问题为：设编号为 1，2，… n 的 n 个人围坐一圈，
        // 约定编号为 k（1<=k<=n）的人从 1 开始报数，
        // 数到m 的那个人出列，它的下一位又从 1 开始报数，
        // 数到 m 的那个人又出列，
        // 依次类推，直到所有人出列为止，由此
        // 产生一个出队编号的序列。
        // n,k,m
        int n=12;
        List3 list = new List3(n);
        list.del(n,5, 4);
        //list.dis();
    }
}

class List3 {
    private CircleList josepfu = new CircleList(1);

    public CircleList getJosepFu() {
        return this.josepfu;
    }

    // 定义长度为m
    public List3(int n) {
        CircleList first = josepfu;
        for (int i = 2; i < n + 1; i++) {
            josepfu.next = new CircleList(i);
            josepfu = josepfu.next;
        }
        josepfu.next = first;
        josepfu = josepfu.next;
    }

    

    // 显示约瑟夫环
    public void dis() {
        while (josepfu.next != null) {
            System.out.println(josepfu);
            josepfu=josepfu.next;
        }
    }
    
    //开始k人开始报数  报数m的人出列
    public void del(int n,int k,int m){
        int count=0;
        int flag=0;
        while((count+m-1)!=n){
            while((josepfu.no!=k)&(flag==0)){
                josepfu=josepfu.next;
            }
            if(flag==1)josepfu=josepfu.next;
                for(int i=1;i<m-1;i++){
                josepfu.shot=i;
                josepfu=josepfu.next;
                
            }
            count++;
            System.out.println(josepfu.next.no);
            josepfu.next=josepfu.next.next;
            flag=1;
        }

    }
    

}

//构造循环链表
class CircleList{
    public int no;
    public int shot;
    public CircleList next;
    public CircleList(int no){
        this.no=no;
    }
    //为了显示方法，我们重新toString
	@Override
	public String toString() {
		return "约瑟夫环 [no=" + no +  "]";
	}
}