package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    private List<EnginePart> parts = new ArrayList<>();
    private List<Coordinate> symbols = new ArrayList<>();

    private Integer width, height;

    public void partOne() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src/inputs/inputDay3"));
        height =allLines.size();
        width =allLines.get(0).length();
        String buffer="";
        List<Coordinate> numberCoordinates = new ArrayList<>();
        for(int i=0;i<allLines.size();i++){
            String line = allLines.get(i);
            for(int j=0;j<line.length();j++){
                char character = line.charAt(j);
                if(Character.isDigit(character)){
                    buffer+=character;
                    numberCoordinates.add(new Coordinate(i,j));
                    if(j+1== width || !Character.isDigit(line.charAt(j+1))){
                        EnginePart newEnginePart = new EnginePart(Integer.valueOf(buffer));
                        newEnginePart.setCoordinates(new ArrayList<>(numberCoordinates));
                        parts.add(newEnginePart);
                        buffer="";
                        numberCoordinates.clear();
                    }
                }else
                if(!(character=='.')){
                    Coordinate newSymbol = new Coordinate(i,j);
                    if(character=='*'){
                        newSymbol.setIsGear();
                    }
                    symbols.add(newSymbol);
                }
            }
        }
        checkParts();
        checkGears();

        System.out.println("cantParts: "+parts.size());
        System.out.println("checks: "+parts.stream().filter(p->p.isCheck()).count());
        System.out.println("cantSymbols: "+symbols.size());
        System.out.println("totalSumCheckParts: "+sumCheckParts());
        Integer total=0;
        for(Coordinate symbol:symbols){
            total+=symbol.calculateGear();
        }
        System.out.println("GearTotal: "+symbols.stream().filter(s->s.getIsGear()).mapToInt(s->s.calculateGear()).sum());

    }
    public Integer sumCheckParts(){
        return parts.stream().filter(p->p.isCheck()).mapToInt(p->p.getNumber()).sum();
    }
    public void checkParts(){
        for(Coordinate symbol:symbols){
            symbol.checkAdyacent(height, width,parts);
        }
    }

    public void checkGears(){
        for(Coordinate symbol:symbols){
            symbol.checkGears(height, width,parts);
        }
    }
}
