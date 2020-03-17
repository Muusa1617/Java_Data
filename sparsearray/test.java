package sparsearray;


public class test {
    public static void main(String[] arg) {
        // 原始数组
        int[][] arr = new int[11][11];
        // 1是白子 2是黑字 0是未下
        arr[5][4] = 1;
        arr[5][5] = 2;
        arr[4][3] = 2;

        // 遍历数组 找出元素数
        int count = 0;
        System.out.println("原数组");
        for (int[] is : arr) {
            for (int is2 : is) {
                if (is2 != 0)
                    count++;
                System.out.print(is2 + "   ");
            }
            System.out.println();
        }

        // 建立稀疏数组
        int[][] sa = new int[count + 1][3];
        int line = 0;
        int row = 0;
        // 获取行列值
        sa[0][0] = 11;
        sa[0][1] = 11;
        sa[0][2] = count;
        int con = 1;
        // 控制稀疏数组行数
        for (int[] is : arr) {
            for (int is2 : is) {
                if (is2 != 0) {
                    sa[con][0] = line;
                    sa[con][1] = row;
                    sa[con][2] = is2;
                    con++;
                }
                row++;
            }
            row = 0;
            line++;
        }

        // 输出稀疏数组
        System.out.println("行   列   值");
        for (int[] is : sa) {
            for (int is2 : is) {
                System.out.print(is2 + "  ");
            }
            System.out.println();
        }

        // 稀疏数组转换为二维数组
        int[][] arr2 = new int[sa[0][0]][sa[0][1]];
        for(int i=1;i<count+1;i++){
            if(sa[i][2]==1)arr2[sa[i][0]][sa[i][1]]=1;
            if(sa[i][2]==2)arr2[sa[i][0]][sa[i][1]]=2;
        } 
        System.out.println("还原数组");
        for (int[] is : arr2) {
            for (int is2 : is) {
                if (is2 != 0)
                    count++;
                System.out.print(is2 + "   ");
            }
            System.out.println();
        }

       
    }
}