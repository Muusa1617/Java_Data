package recursion;



public class test{
    static int count=0;
    public static void main(String [] arg) {
        
        int[] q = new int[8];
        int line=0;
        for (int a : q) {
            System.out.print(a + " ");
        }
        System.out.println();
        set(q,line);
        System.out.println(count);
        
        
        
    }

    public static void set(int [] q,int line){
        
        for(int i=0;i<8;i++){
            //如果是第一列,不用判断
            if(line==0){
                q[line]=i;
                set(q,line+1);
            }
            else{
                q[line]=i;
                if(judge(q,line,i)&line!=7){
                    set(q,line+1);
                }
                else if(judge(q,line,i)&line==7){
                    for (int a : q) {
                        System.out.print(a+1 + " ");
                        
                    }
                    System.out.println();count++;
                    
                }
            }
        }
        
    }

    public static boolean judge(int [] q , int line,int n) {
        int temp=line-1;
        
        while(temp!=-1){
            if(q[line]==q[temp]+(line-temp))return false;
            if(q[line]==q[temp]-(line-temp))return false;
            if(q[temp]==q[line])return false;
            temp--;
        }
        return true;
    }
}