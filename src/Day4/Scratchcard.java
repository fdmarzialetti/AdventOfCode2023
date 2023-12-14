package Day4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Scratchcard {
    private List<Integer> winningNumbers;
    private List<Integer> cardNumbers;
    private Integer copies;

    private Integer cardID;
    public Scratchcard(){};

    public Scratchcard(List<Integer> winningNumbers, List<Integer> cardNumbers,Integer cardID) {
        this.cardID=cardID;
        this.winningNumbers = winningNumbers;
        this.cardNumbers = cardNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(List<Integer> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getCardID() {
        return cardID;
    }

    public void setCardID(Integer cardID) {
        this.cardID = cardID;
    }

    public List<Integer> getMatches(){
         return cardNumbers.stream().filter(cn->winningNumbers.contains(cn)).collect(toList());
    }
    public Integer calculatePoints(){
        List<Integer> points = getMatches();
        if (!points.isEmpty()){
            return IntStream.rangeClosed(1, points.size()).reduce((acc, v) -> acc * 2).getAsInt();
        }
        return 0;
    };

    public void accumCopies(Integer copies){
        this.copies+=copies;
    }


}
