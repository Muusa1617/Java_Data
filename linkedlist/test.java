package linkedlist;

import java.util.Stack;

public class test {
    public static void main(String[] arg) {
        HNode hero1 = new HNode(1, "宋江", "及时雨");
        HNode hero2 = new HNode(5, "卢俊义", "玉麒麟");
        HNode hero3 = new HNode(3, "吴用", "智多星");
        HNode hero4 = new HNode(4, "林冲", "豹子头");

        List list = new List();
        
        //加入
		list.add(hero1);
		list.add(hero4);
		list.addNo(hero2);
		list.addNo(hero3);

		// 测试一下单链表的反转功能
		System.out.println("原来链表的情况~~");
        list.dis();

        System.out.println("修改");
        list.updata(new HNode(5,"卤","玉"));
        list.dis();
        
        System.out.println("删除");
        list.del(5);
        list.dis();
        
        System.out.println("有效的节点个数"+ getLength(list.getHead()));
        System.out.println("倒数第2个节点信息" + findLastK(list.getHead(), 2));
        
        System.out.println("反转");
        reversetList(list.getHead());
        list.dis();
        reversetList(list.getHead());
    }

    //从尾到头打印单链表 1、方向遍历 2、Stack栈
    public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			return;//空链表，不能打印
		}
		//创建要给一个栈，将各个节点压入栈
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		//将链表的所有节点压入栈
		while(cur != null) {
			stack.push(cur);
			cur = cur.next; //cur后移，这样就可以压入下一个节点
		}
		//将栈中的节点进行打印,pop 出栈
		while (stack.size() > 0) {
			System.out.println(stack.pop()); //stack的特点是先进后出
		}
	}

    //将单链表反转
    public static void reversetList(HNode head) {
        
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if(head.next == null || head.next.next == null) {
			return ;
        }
        HNode cur=head.next;
        HNode next;
        HNode reversetHead = new HNode(0, "", "");
        while(cur!=null){
            next=cur.next;
            cur.next=reversetHead.next;
            reversetHead.next=cur;
            cur=next;
        }
        head.next=reversetHead.next;
    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
	/**
	 * 
	 * @param head 链表的头节点
	 * @return 返回的就是有效节点的个数
	 */
    public static int getLength(HNode head){
        if(head.next==null){
            return 0;
        }
        int length=1;
        HNode count=head.next;
        while(true){
            if(count.next==null)break;
            length++;
            count=count.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
	//思路
	//1. 编写一个方法，接收head节点，同时接收一个index 
	//2. index 表示是倒数第index个节点
	//3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
	//4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
	//5. 如果找到了，则返回该节点，否则返回nulll
    
    public static HNode findLastK(HNode head, int k){
        if(head.next==null){
            return null;
        }
        int length=getLength(head);
        int location=length-k;
        if(k <=0 || k > length) {
			return null; 
        }
        HNode temp=head.next;
        for(int i=0;i<location;i++){
            temp=temp.next;
        }
        return temp;
    }
}

class List {
    // 初始化头节点
    private HNode head = new HNode(0, "", "");

    public HNode getHead() {
        return head;
    }

    
    // 添加功能
    public void add(HNode hero) {
        // 头结点不能动
        HNode temp = head;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
    }

    // 根据排名加入到链表中,从小到大
    public void addNo(HNode hero) {
        HNode temp = head;
        boolean flag = false;// 已存在标号
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > hero.no) {
                break;
            } else if (temp.no == hero.no) {
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next;
        }
        // 判断flag 的值
        if (flag) { // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", hero.no);
        } else {
            // 插入到链表中, temp的后面
            hero.next = temp.next;
            temp.next = hero;
        }
    }

    // 修改信息,根据no编号来修改，即no编号不能改
    public void updata(HNode hero) {

        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = hero.name;
            temp.title = hero.title;
        } else { // 没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", hero.no);
        }
    }

    // 删除信息，根据no修改
    public void del(int no) {
        HNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp.next == null) { // 已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    // 显示链表
    public void dis() {

        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        HNode temp = head.next;
        while(true){
            if(temp==null)break;
            System.out.println(temp);
            temp=temp.next;
        }
    }
}

class HNode {
    public int no;
    public String name;
    public String title;
    public HNode next;

    public HNode(int no, String name, String title) {
        this.no = no;
        this.name = name;
        this.title = title;
    }

    // 为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + title + "]";
    }
}