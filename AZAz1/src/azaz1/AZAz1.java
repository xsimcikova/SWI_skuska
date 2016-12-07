package azaz1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AZAz1 {

    public int[] id;

    //inic. pom. pole s N prvkami (max mozny pocet bodov)
    public AZAz1(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    //urcuje root-a
    public int root(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int id1, int id2) {
        return root(id1) == root(id2);
    }

    //spaja do jedneho komponentu
    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    //funkcia na ziskanie cisla komponentu
     public static Integer getKeyFromValue(Map hm, Object value) {
            for (Object o : hm.keySet()) {
              if (hm.get(o).equals(value)) {
                return parseInt(o.toString());
              }
            }
            return null;
          }
     
    //hlada rozlicne hodnoty v poli-pre ziskanie poctu komponentov
    public static int diffValues(int[] numArray){
    int numOfDifferentVals = 0;

    ArrayList<Integer> diffNum = new ArrayList<>();

    for(int i=0; i<numArray.length; i++){
        if(!diffNum.contains(numArray[i])){
            diffNum.add(numArray[i]);
        }
    }

    if(diffNum.size()==1){
            numOfDifferentVals = 0;
    }
    else{
          numOfDifferentVals = diffNum.size();
        } 

   return numOfDifferentVals;
}    

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String txtName = "graf1.txt";
        Integer riadky=0;
        
        //ziskame pocet riadkov v subore
        try (BufferedReader br = new BufferedReader(new FileReader(txtName))) {
            //StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
               // sb.append(line);
                riadky++;
                line = br.readLine();
            }
        }
        System.out.println("Pocet riadkov: " + riadky);
        
        HashMap<Integer, Vrchol> newMap = new HashMap<>();
        Integer idx = 0;
        int N = riadky*2; //pocet max poctu komponentov, v pripade, ak by kazdy komp. bol sam bod
        AZAz1 q = new AZAz1(N); //vytvorime pom. pole

        try (BufferedReader br = new BufferedReader(new FileReader(txtName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String prvyBod = "";
            String druhyBod = "";
            while (line != null) {
                sb.append(line);
                // parsuje bod prvy, bod druhy, kontorluje ci existuje, vlozi do hashmap, spaja pomocou union
                prvyBod = line.substring(1, line.indexOf("]"));
                druhyBod = line.substring(line.lastIndexOf("[") + 1, line.lastIndexOf("]"));
                Vrchol p1 = new Vrchol(parseInt(prvyBod.split(",")[0]), parseInt(prvyBod.split(",")[1]));
                Vrchol p2 = new Vrchol(parseInt(druhyBod.split(",")[0]), parseInt(druhyBod.split(",")[1]));
                if (!newMap.containsValue(p1)) {
                    newMap.put(idx++, p1);
                }
                if (!newMap.containsValue(p2)) {
                    newMap.put(idx++, p2);
                }
                q.union(getKeyFromValue(newMap,p1), getKeyFromValue(newMap,p2));

                line = br.readLine();
            }
        }
        
            //nove pole komponentov
        int[] a = new int[idx];
        for (int i=0;i<idx; i++) a[i] = q.root(i);
        
        //predelenie do komponentov
        Map<Integer,Set<Vrchol>> map = new HashMap<>();
        int index = 0;
        for(int i :a){
            if(map.containsKey(i))
                map.get(i).add(newMap.get(index++));
            else{
                map.put(i, new HashSet<>());
                map.get(i).add(newMap.get(index++));
            }   
        }
        
        //pretvorenie komponentov na vrcholy
        List<Vrchol> roots = new ArrayList<>();
        index = 0;
        for (Map.Entry<Integer, Set<Vrchol>> entry : map.entrySet())
        {
            int averX = 0, averY = 0;
            for(Vrchol v :entry.getValue()){
                averX += v.x;
                averY += v.y;
                v.setRootNum(entry.getKey());
            }
            int size = entry.getValue().size();
            roots.add(new Vrchol(averX/size, averY/size,entry.getKey(), index++));            
        }
        
        //prepojenie komponentov
        List<Edge> hrany = new ArrayList<>();
        for(Vrchol v: roots){
            for(Vrchol p:roots){
                if(v == p)
                    continue;
                hrany.add(new Edge(v.getMeno(), p.getMeno(), v.distance(p)));
            }
        }
        
        //kruskal
        List<Edge> noveHrany = Kruskal.kruskalAlgorithm(hrany, roots.size());
        
        //prepojenie miest najblizsimi bodmi
        int dist = 0;
        for(Edge e : noveHrany){
            Set<Vrchol> tmpList = new HashSet<>();
            tmpList.addAll(map.get(roots.get(e.getFrom()).getRootNum()));
            tmpList.addAll(map.get(roots.get(e.getTo()).getRootNum()));
            Vrchol[] vrcholy = tmpList.toArray(new Vrchol[tmpList.size()]);
            
            Vrchol[] par = ClosestPair.closestPairCalc(vrcholy);
            System.out.printf("[%d,%d] [%d,%d]\n",par[0].x,par[0].y,par[1].x,par[1].y);
            dist += par[0].distance(par[1]);
        }
        System.out.println("distance: " + dist);

        System.out.println("");
        System.out.println("Pocet komponentov: " + diffValues(a));
        
//        for (int j=0; j < a.length; j++)
//            if (a[j] == 53)
//        System.out.println(a[j] + " ");
        
    }
}