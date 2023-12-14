package Day6;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    private List<Race> races = new ArrayList<>(); // RaceTime and Record

    public void readAndProcessInput() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/inputs/inputDay6"));
        String[] timeRaces = input.get(0).split(":")[1].trim().split("\\s* ");;
        String[] recordRaces = input.get(1).split(":")[1].trim().split("\\s* ");;
        for(long i=0;i<timeRaces.length;i++){
            races.add(new Race(i+1,Long.valueOf(timeRaces[(int)i]),Long.valueOf(recordRaces[(int)i])));
        }
    }
    public Long partOne() throws IOException {
        readAndProcessInput();
        return races.stream().map(r->pressToWinOptions(r)).reduce(1L,(a,b)->a*b);
    }

    public Long partTwo() throws IOException {
        Long timeRace = Long.valueOf(races.stream().map(r->r.getRaceTime().toString()).reduce("",(a,b)->a+b));
        Long recordRace = Long.valueOf(races.stream().map(r->r.getLastRecord().toString()).reduce("",(a,b)->a+b));
        return pressToWinOptions(new Race(0L,timeRace,recordRace));
    }

    public Long pressToWinOptions(Race race){
        Long[] solutions = bascara(race.getRaceTime(),-(race.getLastRecord()));
        return 1+solutions[1]-solutions[0];
    }

    public Long[] bascara(Long b,Long c){
        Long a = -1L;
        double discriminante = (Math.pow(-b, 2) - (4 * a * c));
        if (discriminante >= 0) {
            Long soluciones[];
            // Una solucion
            if(discriminante == 0){
                soluciones = new Long[1];
                soluciones[0] = ((-b) - (4 * a * c)) / (2 * a);
                // Dos soluciones
            }else{
                soluciones = new Long[2];
                soluciones[0] = (long) Math.floor(((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a))+1;
                soluciones[1] = (long) Math.ceil(((-b) - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a))-1;
            }
            return soluciones;
        } else {
            // Sin solucion
            return null;
        }
    }

}
