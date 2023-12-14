package Day5;

import java.util.ArrayList;
import java.util.List;

public class Seed {
    private Long id;
    private List<Long> destinations = new ArrayList<>();

    public Seed (Long id){
        this.id=id;
        destinations.add(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Long> destinations) {
        this.destinations = destinations;
    }

    public void addDestinationValue(Long destination){
        destinations.add(destination);
    }

    public Long lastDestination(){
        return destinations.get(destinations.size()-1);
    }
}
