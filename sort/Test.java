package sort;

import java.util.Arrays;

public class Test {
    public static void main(String[] arg) {
        long startTime=System.currentTimeMillis(); 
        long endTime=System.currentTimeMillis(); 
        //System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        int n = 10;// 定义数组大小
        int[] arrr = new int[n];
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            arrr[i] = (int) (Math.random() * n); 
        }

        temp = Arrays.copyOf(arrr, n) ;

        print(arrr);
        print(temp);
        System.out.println("冒泡：时间复杂度 O(n^2)");
        startTime=System.currentTimeMillis(); 
        print(bubble(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("选择：时间复杂度 O(n^2)");
        startTime=System.currentTimeMillis(); 
        print(select(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("插入：时间复杂度 O(n^2)");
        startTime=System.currentTimeMillis(); 
        print(insert(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("希尔(交换)：时间复杂度 O(nlogn)");
        startTime=System.currentTimeMillis(); 
        print(shell(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("希尔(移位)：时间复杂度 O(nlogn)");
        startTime=System.currentTimeMillis(); 
        print(shell2(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("快速：时间复杂度 O(n^2)");
        startTime=System.currentTimeMillis(); 
        print(quick(temp,0,temp.length-1));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        int [] temp2 = new int [arrr.length];
        System.out.println("归并：时间复杂度 O(nlogn)");
        startTime=System.currentTimeMillis(); 
        print(merget(temp,0,temp.length-1,temp2));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;

        System.out.println("基数：时间复杂度 O(n+k)");
        startTime=System.currentTimeMillis(); 
        print(radix(temp));
        endTime=System.currentTimeMillis(); 
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        temp = Arrays.copyOf(arrr, n) ;



    }

    // 冒泡排序
    public static int[] bubble(int[] arr) {
        
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
        return arr;
    }

    // 选择排序
    public static int[] select(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值，并不是最小
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }

            // 将最小值，放在arr[0], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }

    // 插入排序
    public static int[] insert(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        // 使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1; // 即arr[1]的前面这个数的下标

            // 给insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到, insertIndex + 1
            // 举例：理解不了，我们一会 debug
            // 这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        return arr;
    }

    // 希尔排序
    public static int [] shell(int[] arr) {
		
		int temp = 0;
		int count = 0;
		// 根据前面的逐步分析，使用循环处理
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				// 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
				for (int j = i - gap; j >= 0; j -= gap) {
					// 如果当前元素大于加上步长后的那个元素，说明交换
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
        }
        return arr;
	}
	
	//对交换式的希尔排序进行优化->移位法
	public static int [] shell2(int[] arr) {
		
		// 增量gap, 并逐步的缩小增量
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素，逐个对其所在的组进行直接插入排序
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						//移动
						arr[j] = arr[j-gap];
						j -= gap;
					}
					//当退出while后，就给temp找到插入的位置
					arr[j] = temp;
				}

			}
        }
        return arr;
	}

    //快速排序
    public static int [] quick(int [] arr,int left,int right){
        int l = left; //左下标
		int r = right; //右下标
		//pivot 中轴值
		int pivot = arr[(left + right) / 2];
		int temp = 0; //临时变量，作为交换时使用
		//while循环的目的是让比pivot 值小放到左边
		//比pivot 值大放到右边
		while( l < r) { 
			//在pivot的左边一直找,找到大于等于pivot值,才退出
			while( arr[l] < pivot) {
				l += 1;
			}
			//在pivot的右边一直找,找到小于等于pivot值,才退出
			while(arr[r] > pivot) {
				r -= 1;
			}
			//如果l >= r说明pivot 的左右两的值，已经按照左边全部是
			//小于等于pivot值，右边全部是大于等于pivot值
			if( l >= r) {
				break;
			}
			
			//交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			
			//如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
			if(arr[l] == pivot) {
				r -= 1;
			}
			//如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
			if(arr[r] == pivot) {
				l += 1;
			}
		}
		
		// 如果 l == r, 必须l++, r--, 否则为出现栈溢出
		if (l == r) {
			l += 1;
			r -= 1;
		}
		//向左递归
		if(left < r) {
			quick(arr, left, r);
		}
		//向右递归
		if(right > l) {
			quick(arr, l, right);
		}
		
		return arr;
	}
    
    //归并排序
    public static int [] merget(int [] arr,int left,int right,int [] temp){
        if(left < right) {
			int mid = (left + right) / 2; //中间索引
			//向左递归进行分解
			merget(arr, left, mid, temp);
			//向右递归进行分解
			merget(arr, mid + 1, right, temp);
			//合并
			merge(arr, left, mid, right, temp);
			
        }
        return arr;
	}
	//合并的方法
	/**
	 * 
	 * @param arr 排序的原始数组
	 * @param left 左边有序序列的初始索引
	 * @param mid 中间索引
	 * @param right 右边索引
	 * @param temp 做中转的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		
		int i = left; // 初始化i, 左边有序序列的初始索引
		int j = mid + 1; //初始化j, 右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引
		
		//(一)
		//先把左右两边(有序)的数据按照规则填充到temp数组
		//直到左右两边的有序序列，有一边处理完毕为止
		while (i <= mid && j <= right) {//继续
			//如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
			//即将左边的当前元素，填充到 temp数组 
			//然后 t++, i++
			if(arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else { //反之,将右边有序序列的当前元素，填充到temp数组
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}
		
		//(二)
		//把有剩余数据的一边的数据依次全部填充到temp
		while( i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[i];
			t += 1;
			i += 1;	
		}
		
		while( j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[j];
			t += 1;
			j += 1;	
		}
		
		
		//(三)
		//将temp数组的元素拷贝到arr
		//注意，并不是每次都拷贝所有
		t = 0;
		int tempLeft = left; // 
		//第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
		//最后一次 tempLeft = 0  right = 7
		while(tempLeft <= right) { 
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
		
	
    }

    //基数排序
    public static   int [] radix(int [] arr){
        //根据前面的推导过程，我们可以得到最终的基数排序代码
		
		//1. 得到数组中最大的数的位数
		int max = arr[0]; //假设第一数就是最大数
		for(int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max + "").length();
		
		
		//定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
		//说明
		//1. 二维数组包含10个一维数组
		//2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
		//3. 名明确，基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];
		
		//为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
		//可以这里理解
		//比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
		int[] bucketElementCounts = new int[10];
		
		
		//这里我们使用循环将代码处理
		
		for(int i = 0 , n = 1; i < maxLength; i++, n *= 10) {
			//(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
			for(int j = 0; j < arr.length; j++) {
				//取出每个元素的对应位的值
				int digitOfElement = arr[j] / n % 10;
				//放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			//按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
			int index = 0;
			//遍历每一桶，并将桶中是数据，放入到原数组
			for(int k = 0; k < bucketElementCounts.length; k++) {
				//如果桶中，有数据，我们才放入到原数组
				if(bucketElementCounts[k] != 0) {
					//循环该桶即第k个桶(即第k个一维数组), 放入
					for(int l = 0; l < bucketElementCounts[k]; l++) {
						//取出元素放入到arr
						arr[index++] = bucket[k][l];
					}
				}
				//第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
				bucketElementCounts[k] = 0;
				
			}
			//System.out.println("第"+(i+1)+"轮，对个位的排序处理 arr =" + Arrays.toString(arr));
			
		}
		
		/*
		
		//第1轮(针对每个元素的个位进行排序处理)
		for(int j = 0; j < arr.length; j++) {
			//取出每个元素的个位的值
			int digitOfElement = arr[j] / 1 % 10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		//按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		int index = 0;
		//遍历每一桶，并将桶中是数据，放入到原数组
		for(int k = 0; k < bucketElementCounts.length; k++) {
			//如果桶中，有数据，我们才放入到原数组
			if(bucketElementCounts[k] != 0) {
				//循环该桶即第k个桶(即第k个一维数组), 放入
				for(int l = 0; l < bucketElementCounts[k]; l++) {
					//取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			//第l轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
			bucketElementCounts[k] = 0;
			
		}
		System.out.println("第1轮，对个位的排序处理 arr =" + Arrays.toString(arr));
		
		
		//==========================================
		
		//第2轮(针对每个元素的十位进行排序处理)
		for (int j = 0; j < arr.length; j++) {
			// 取出每个元素的十位的值
			int digitOfElement = arr[j] / 10  % 10; //748 / 10 => 74 % 10 => 4
			// 放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		index = 0;
		// 遍历每一桶，并将桶中是数据，放入到原数组
		for (int k = 0; k < bucketElementCounts.length; k++) {
			// 如果桶中，有数据，我们才放入到原数组
			if (bucketElementCounts[k] != 0) {
				// 循环该桶即第k个桶(即第k个一维数组), 放入
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					// 取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			//第2轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
			bucketElementCounts[k] = 0;
		}
		System.out.println("第2轮，对个位的排序处理 arr =" + Arrays.toString(arr));
		
		
		//第3轮(针对每个元素的百位进行排序处理)
		for (int j = 0; j < arr.length; j++) {
			// 取出每个元素的百位的值
			int digitOfElement = arr[j] / 100 % 10; // 748 / 100 => 7 % 10 = 7
			// 放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		index = 0;
		// 遍历每一桶，并将桶中是数据，放入到原数组
		for (int k = 0; k < bucketElementCounts.length; k++) {
			// 如果桶中，有数据，我们才放入到原数组
			if (bucketElementCounts[k] != 0) {
				// 循环该桶即第k个桶(即第k个一维数组), 放入
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					// 取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			//第3轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
			bucketElementCounts[k] = 0;
		}
		System.out.println("第3轮，对个位的排序处理 arr =" + Arrays.toString(arr)); */
		return arr;
	}
    

    // 输出数组
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}