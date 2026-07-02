public record Point(int i,int j,int h){}

int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};

public boolean findSafeWalk(List<List<Integer>> grid, int health) {
    int r = grid.size();
    int c = grid.get(0).size();

    Boolean[][][] memo = new Boolean[r][c][health+1];
    boolean[][][] v = new boolean[r][c][health+1];

    return go(0,0,health,new HashSet<>(),grid,new HashMap<>(),memo,v);
}

private boolean go(int i,int j,int h,HashSet<Point> v,List<List<Integer>> grid,HashMap<Point,Boolean> map,Boolean[][][] memo,boolean[][][] va)
{
    h = h - grid.get(i).get(j);
    //Point p = new Point(i,j,h);
    // if(map.containsKey(p))
    //     return map.get(p);
    
    if(memo[i][j][h]!=null)
        return memo[i][j][h];

    // if(v.contains(p))
    //     return false;
    // //System.out.println(p+,health=+h);
    
    if(va[i][j][h])
        return false;

    if(h==0)
        return false;
    
    if(i==grid.size()-1 && j==grid.get(0).size()-1)
        return true;

    //v.add(p);
    va[i][j][h] = true;
    for(int[] move:moves){
        int nextI = i+move[0];
        int nextJ = j+move[1];
        if(isValid(nextI,nextJ,grid.size(),grid.get(0).size())){
            if(go(nextI,nextJ,h,v,grid,map,memo,va))
                return true;
        }
    }
    //v.remove(p);
    va[i][j][h] = false;
    //map.put(p,false);
    memo[i][j][h] = false;
    return false;
}

private boolean isValid(int i,int j,int r,int c){
    if(i>=0 && j>=0 && i<r && j<c)
        return true;
    return false;
}