package linkedlist;

public class test2 {
    public static void main(String[] arg) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HNode2 hero1 = new HNode2(1, "宋江", "及时雨");
        HNode2 hero2 = new HNode2(2, "卢俊义", "玉麒麟");
        HNode2 hero3 = new HNode2(3, "吴用", "智多星");
        HNode2 hero4 = new HNode2(4, "林冲", "豹子头");

        List2 list = new List2();

        list.add(hero1);
		list.add(hero2);
		list.add(hero3);
		list.add(hero4);
		
		list.list();
		
		// 修改
		HNode2 newHeroNode = new HNode2(4, "公孙胜", "入云龙");
		list.update(newHeroNode);
		System.out.println("修改后的链表情况");
		list.list();
		
		// 删除
		list.del(3);
		System.out.println("删除后的链表情况~~");
		list.list();

    }
}

class List2 {
    private HNode2 head = new HNode2(0, "", "");

    public HNode2 getHead() {
        return this.head;
    }

    // 遍历双向链表的方法
	// 显示链表[遍历]
    public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HNode2 temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出节点的信息
			System.out.println(temp);
			// 将temp后移， 一定小心
			temp = temp.next;
		}
    }
    
    // 添加一个节点到双向链表的最后.
    public void add(HNode2 HNode) {

		// 因为head节点不能动，因此我们需要一个辅助遍历 temp
		HNode2 temp = head;
		// 遍历链表，找到最后
		while (true) {
			// 找到链表的最后
			if (temp.next == null) {//
				break;
			}
			// 如果没有找到最后, 将将temp后移
			temp = temp.next;
		}
		// 当退出while循环时，temp就指向了链表的最后
		// 形成一个双向链表
		temp.next = HNode;
		HNode.pre = temp;
	}

    // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    // 只是 节点类型改成 HNode2
    public void update(HNode2 newHeroNode){
        // 判断是否空
		if (head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		// 找到需要修改的节点, 根据no编号
		// 定义一个辅助变量
		HNode2 temp = head.next;
		boolean flag = false; // 表示是否找到该节点
		while (true) {
			if (temp == null) {
				break; // 已经遍历完链表
			}
			if (temp.no == newHeroNode.no) {
				// 找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根据flag 判断是否找到要修改的节点
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else { // 没有找到
			System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
		}
    }

    // 从双向链表中删除一个节点,
	// 说明
	// 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void del(int no) {

		// 判断当前链表是否为空
		if (head.next == null) {// 空链表
			System.out.println("链表为空，无法删除");
			return;
		}

		HNode2 temp = head.next; // 辅助变量(指针)
		boolean flag = false; // 标志是否找到待删除节点的
		while (true) {
			if (temp == null) { // 已经到链表的最后
				break;
			}
			if (temp.no == no) {
				// 找到的待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next; // temp后移，遍历
		}
		// 判断flag
		if (flag) { // 找到
			// 可以删除
			// temp.next = temp.next.next;[单向链表]
			temp.pre.next = temp.next;
			// 这里我们的代码有问题?
			// 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
			if (temp.next != null) {
				temp.next.pre = temp.pre;
			}
		} else {
			System.out.printf("要删除的 %d 节点不存在\n", no);
		}
	}
}

class HNode2 {
    public int no;
    public String name;
    public String nickname;
    public HNode2 next; // 指向下一个节点, 默认为null
    public HNode2 pre; // 指向前一个节点, 默认为null
    // 构造器

    public HNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}