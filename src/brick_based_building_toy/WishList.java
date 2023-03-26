package brick_based_building_toy;

import java.util.*;

public class WishList {
    private SortedSet<BrickSet> wishList;
    public WishList() {
        wishList = new TreeSet<>();
    }
    public Collection<BrickSet> getSets(){
        return new TreeSet<>(wishList);
    }
    public boolean addSet(BrickSet set){
        if(!wishList.contains(set)){
            wishList.add(set);
            return true;
        }
        return false;
    }
    public boolean removeSet(BrickSet set){
        if(wishList.contains(set)){
            wishList.remove(set);
            return true;
        }
        return false;
    }

}
