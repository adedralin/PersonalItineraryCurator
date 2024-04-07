import java.util.*;

public class Location{
    private Double fun;
    private String name;
    HashMap<String, Integer> adjacents = new HashMap<>();
    
    public Location(String locInfo) throws Exception{
        String[] lineInfo = locInfo.split(": ");
        name = lineInfo[0];
        fun = Double.parseDouble(lineInfo[1].split(", ")[0]);
        String[] adjacentsList = lineInfo[1].split(", ");

        for(int i = 0; i < adjacentsList.length; i++){
            if(i == 0){
                continue;
            }

            String adj = adjacentsList[i].split("-")[0];
            int distance = Integer.parseInt(adjacentsList[i].split("-")[1]);
            adjacents.put(adj, distance);
        }
    }

    public void setFun(Double f){
        fun = f;
    }

    public Double getFun(){
        return fun;
    }

    public void setName(String s){
        name = s;
    }

    public String getName(){
        return name;
    }

    public String getAdjacents(){
        String getAdj = "";

        for(String key : adjacents.keySet()){
            getAdj += key + "-" + adjacents.get(key) + " mins" + ", ";
        }

        if(getAdj.length() >= 2){
            getAdj = getAdj.substring(0, getAdj.length() - 2);
        }

        return getAdj;
    }

    @Override
    public String toString(){
        return(": Fun=" + fun + ", Adjacent Locations: " + getAdjacents());
    }
}
