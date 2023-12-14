package Day5;

import java.util.Comparator;

public class Convertion {
    private Long destinationValue;
    private Long sourceValue;

    private Long range;

    public Convertion(Long destinationValue, Long sourceValue,  Long range) {
        this.destinationValue = destinationValue;
        this.sourceValue = sourceValue;
        this.range = range;
    }

    public Long getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(Long sourceValue) {
        this.sourceValue = sourceValue;
    }

    public Long getDestinationValue() {
        return destinationValue;
    }

    public void setDestinationValue(Long destinationValue) {
        this.destinationValue = destinationValue;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public Boolean isInRange(Long value){
        return value>=sourceValue && value<sourceValue+range;
    }

    public Long calculateDestination(Long value){
        return value+(destinationValue-sourceValue);
    }
}
