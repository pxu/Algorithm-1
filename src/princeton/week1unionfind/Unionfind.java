package princeton.week1unionfind;

/**
 * Created by zhahua on 11/27/17.
 */
public class Unionfind {

    public static class QuickFind {
        public void union(int[] id, int p, int q) {
            int idp = id[p];
            int idq = id[q];
            for(int i = 0; i < id.length; i++) {
                if(id[i] == idq) {
                    id[i] = idp;
                }
            }
        }
        public boolean find(int []id, int p, int q) {
            return id[p] == id[q];
        }
    }
    public static class QuickUnion {
        public int root(int[] id, int p) {
            int i = p;
            while(id[i] != i) {
                i = id[i];
            }
            return i;
        }
        public void union(int[] id, int p, int q) {
            int pr = root(id, p);
            int qr = root(id, q);
            id[pr] = qr;
        }
        public boolean find(int[] id, int p, int q) {
            return root(id, p) == root(id, q);
        }

    }
    public static class QuickUnionWithWeight {
        public int root(int[] id, int p) {
            int i = p;
            while(id[i] != i) {

                i = id[i];
            }

            return i;
        }
        public boolean find(int[] id, int p, int q) {
            return root(id, p) == root(id, q);
        }
        public void union(int[] id, int[] sz, int p, int q) {
            int pr = root(id, p);
            int qr = root(id, q);
            if (sz[pr] > sz[qr]) {
                id[qr] = pr;
                sz[pr] += sz[qr];
            } else {
                id[pr] = qr;
                sz[qr] += sz[pr];
            }
        }
    }
}
