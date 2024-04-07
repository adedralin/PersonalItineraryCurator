public class HeapElement implements Comparable<HeapElement>{
    String totalPath;
    Double recommendation;
    
    public HeapElement(String path, Double r){
        recommendation = r;
        totalPath = path;
    }

    @Override
    public int compareTo(HeapElement o) {
        if(this.recommendation < o.recommendation){
            return -1;
        }
        else if(this.recommendation != 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    String getPath(){
        return totalPath;
    }

    Double getRecommendation(){
        return recommendation;
    }
}
