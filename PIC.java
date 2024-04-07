import java.util.*;
import java.io.*;

public class PIC {
    public ArrayList<String> bestRoutes(HashMap<String, Location> locations, Location start, Location end){
        PriorityQueue<HeapElement> heap = new PriorityQueue<>();    //first heap for holding values (paths)
        PriorityQueue<HeapElement> results = new PriorityQueue<>(); //second heap for getting full paths to destination
        
        HeapElement he = new HeapElement(start.getName(), 0.0);
        heap.add(he);       //he is analogous to the $ in a PDA, referencing when an operation via a particular data structure is complete

        while(!heap.isEmpty()){
            HeapElement heapEntry = heap.poll();
            String path = heapEntry.getPath();
            Double recom = heapEntry.getRecommendation();

            String[] places = path.split("/");
            String currPlace = places[places.length - 1];

            if(locations.get(currPlace) != null && !locations.get(currPlace).adjacents.isEmpty()){
                for(String key : locations.get(currPlace).adjacents.keySet()){
                    String newPath = path + key;            //current path from start to current
                    Double fun = locations.get(currPlace).getFun();
                    int time = locations.get(currPlace).adjacents.get(key);
                    Double newRecom = fun + (100 / time);    //recommendation of this path so far

                    //iterate through newPath for sumFun & sumTime

                    HeapElement newHE = new HeapElement(newPath, newRecom);
                    if(key.equals(end.getName())){
                        results.add(newHE);
                    }
                    else{
                        heap.add(newHE);
                    }
                }
            }
        }

        ArrayList<String> finalPaths = new ArrayList<>();      //the top 5 best routes to go (unless there are less than 5 routes)
        int counter = 0;

        while(counter < 5 && counter < results.size()){
            String finalPath = "";

            String[] p = results.poll().getPath().split("/");
            
            for(int i = 0; i < p.length; i++){
                finalPath += p[i];
                if(i != p.length - 1){
                    finalPath += ", ";
                }
            }

            finalPaths.add(finalPath);

            counter++;
        }

        return finalPaths;
    }

    public static void main(String[] args) throws Exception{
        PIC pic = new PIC();

        Scanner scan = new Scanner(new File("locInfo.txt"));

        HashMap<String, Location> locations = new HashMap<>();

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            Location l = new Location(line);
            locations.put(l.getName(), l);
        }

        scan.close();

        ArrayList<String> bestRoutes = pic.bestRoutes(locations, locations.get("Golden Gate Park"), locations.get("Bay Bridge"));

        for(String r : bestRoutes){
            System.out.println(r);
        }
    }
}
