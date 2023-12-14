package Day5;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapping {
    private String sourceCategory;
    private String destinationCategory;
    private List<Convertion> convertions;

    public CategoryMapping(String sourceCategory, String destinationCategory, List<Convertion> convertions) {
        this.sourceCategory = sourceCategory;
        this.destinationCategory = destinationCategory;
        this.convertions = convertions;
    }

    public String getSourceCategory() {
        return sourceCategory;
    }

    public void setSourceCategory(String sourceCategory) {
        this.sourceCategory = sourceCategory;
    }

    public String getDestinationCategory() {
        return destinationCategory;
    }

    public void setDestinationCategory(String destinationCategory) {
        this.destinationCategory = destinationCategory;
    }

    public List<Convertion> getConvertions() {
        return convertions;
    }

    public void setConvertions(List<Convertion> convertions) {
        this.convertions = convertions;
    }

    public  Long calculateConvertion(Long seedValue){
        Long convertionValue= seedValue;
        List<Convertion> convertion = convertions.stream().filter(c->c.isInRange(seedValue)).collect(Collectors.toList());
        if(!convertion.isEmpty()){
            convertionValue=convertion.get(0).calculateDestination(seedValue);
        }
        return convertionValue;
    }
}
