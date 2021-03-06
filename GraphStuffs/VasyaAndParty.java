package GraphTheory;

import java.io.*;
import java.util.*;
/**
 *
 * @author Sourav Kumar Paul (spaul100)
 * NIT Silchar    
 */

public class VasyaAndParty {
    public static ArrayList<Integer> adj[];
    public static boolean marked[];
    public static HashMap<Long,Long> map;
    public static void main(String[] args) throws IOException{
        Reader in = new Reader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
       
      
        int n = in.nextInt();
        int m = in.nextInt();
        long arr[] = new long[n];
        marked = new boolean[n];
        adj = new ArrayList[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = in.nextLong();
            adj[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            adj[x].add(y);
            adj[y].add(x);
        }
        long ans = 1l;
        for(int i=0; i<n; i++)
        {
            if(!marked[i])
            {
                max = Long.MIN_VALUE;
                count = 0;
                map = new HashMap<>();
                dfs(i,arr);
                long x = map.containsKey(max)? map.get(max):0;
                ans = (ans * x)%mod;
            }
        }
        out.println(ans);
        out.flush();
        out.close();
    }
    public static long max,count;
    private static void dfs(int start, long arr[]) {
       
        Stack<Integer> st = new Stack<>();
        st.push(start);
        while(!st.isEmpty())
        {
            start = st.pop();
            if(marked[start])
                continue;
            marked[start] = true;
       
            /*if(arr[start] == max)
                count++;
            else if(arr[start] > max)
            {
                count =1;
                max = arr[start];
            }*/
            
            long x = map.containsKey(arr[start])? map.get(arr[start])+1 : 1;
            map.put(arr[start], x);
            max = Math.max(max,arr[start]);
             for(int v: adj[start])
            {
                if(!marked[v])
                {
                    st.push(v);
                }
            }
        }
        
        //max = Math.max(max, arr[start]);
        
    }

/**
 *  ############################### Template ################################
 */
    
    public static long mod = 1000000007, inf = 100000000000000000l;
    public static long fac[],inv[];
    public static int union[];

    
    public static class Pair implements Comparable{
        int x,y;
       
        Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Object o)
        {
            Pair pp = (Pair)o;
            if(pp.x == x)
                return 0;
            else if (x>pp.x)
                return 1;
            else 
                return -1;
        }
    }

   
    public static void init()
    {
        for(int i=0; i<union.length; i++)
            union[i] = i;
    }
    public static int find(int n)
    {
        return (union[n]==n)?n:(union[n]=find(union[n]));
    }
    public static void unionSet(int i ,int j)
    {
        union[find(i)]=find(j);
    }
    public static boolean connected(int i,int j)
    {   
        return union[i]==union[j];
    }

    public static long gcd(long a, long b) {
        long x = Math.min(a,b);
        long y = Math.max(a,b);
        while(x!=0)
        {
            long temp = x;
            x = y%x;
            y = temp;
        }
        return y;       
    }

    public static long modPow(long base, long exp, long mod) {
        base = base % mod;
        long result =1;
        while(exp > 0)
        {
            if(exp % 2== 1)
            {
                result = (result * base) % mod;
                exp --;
            }
            else
            {
                base = (base * base) % mod;
                exp = exp >> 1;
            }
            
        }
        return result;
    }
    
    public static void cal()
    {
        fac = new long[1000005];
        inv = new long[1000005];
        fac[0]=1;
        inv[0]=1;
        for(int i=1; i<=1000000; i++)
        {
            fac[i]=(fac[i-1]*i)%mod;
            inv[i]=(inv[i-1]*modPow(i,mod-2,mod))%mod;
        }
    }
    public static long ncr(int n, int r)
    {
        return (((fac[n]*inv[r])%mod)*inv[n-r])%mod;
    }

    public static class Reader {
        public BufferedReader reader;
        public StringTokenizer st;

        public Reader(InputStreamReader stream) {
            reader = new BufferedReader(stream);
            st = null;
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
        public String nextLine() throws IOException{
            return reader.readLine();
        }
        public long nextLong(){
            return Long.parseLong(next());
        }
        public double nextDouble(){
            return Double.parseDouble(next());
        }

    }
}

