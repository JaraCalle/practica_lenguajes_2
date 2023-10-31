import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    static List<Estudiante> estudiantes;
    public static void main(String[] args) throws IOException {
        cargarArchivo();
        promPuntajeCarrera();
        estudianteMasPuntajePorCarrera();
        cantEstudiantesPorCarrera();
        carreraConMasPuntajePromedio();
    }

    static void cargarArchivo() throws IOException{
        Pattern pattern =Pattern.compile(",");
        String filename= "estudiante.csv";

        try(Stream<String> lines = Files.lines(Path.of(filename))){
            estudiantes=lines.skip(1).map(line->{
                String[]arr=pattern.split(line);
                return new Estudiante(arr[0],arr[1],arr[2], arr[4],arr[9],Double.parseDouble(arr[10]));
            }).collect(Collectors.toList());
            estudiantes.forEach(System.out::println);
        }
    }

    static void promPuntajeCarrera(){
        Map<String, List<Estudiante>> agrupadoPorDepartamento =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        System.out.println("\nPromedio de puntajes por carrera:");
        agrupadoPorDepartamento.forEach((carrera, estudiantesPorCarrera)-> {
            System.out.print(carrera+": ");
            System.out.println(estudiantesPorCarrera
                    .stream()
                    .mapToDouble(Estudiante::getPuntaje)
                    .average()
                    .getAsDouble());
        });
    }

    static void estudianteMasPuntajePorCarrera(){
        Function<Estudiante, Double> porPuntaje = Estudiante::getPuntaje;
        Comparator<Estudiante> puntajeDescendente =
                Comparator.comparing(porPuntaje);
        System.out.printf("%nEstudiantes con mayor puntaje por carrera: %n");
        Map<String, List<Estudiante>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        agrupadoPorCarrera.forEach(
                (carrera, estudiantesEnCarrera) ->
                {
                    System.out.print(carrera+": ");
                    Estudiante estudianteMas=estudiantesEnCarrera.stream().sorted(puntajeDescendente.reversed())
                            .findFirst()
                            .get();
                    System.out.println(estudianteMas.getPrimerNombre()+" "+estudianteMas.getApellido()+
                            " ///Cuanto puntaje? ==> puntaje: "+estudianteMas.getPuntaje()+" puntos.");
                }
        );
    }

    static void cantEstudiantesPorCarrera(){
        // cuenta el número de empleados en cada departamento
        System.out.printf("%nConteo de estudiantes por carrera:%n");
        Map<String, Long> conteoEstudiantesPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera,
                                TreeMap::new, Collectors.counting()));
        conteoEstudiantesPorCarrera.forEach(
                (carrera, conteo) -> System.out.printf(
                        "%s tiene %d estudiantes/aspirantes %n", carrera, conteo));
    }

    static void carreraConMasPuntajePromedio(){
        Map<String, List<Estudiante>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Estudiante::getCarrera));
        System.out.println("\nPromedios de carrera ::");
        Double mayor = 0.0;
        Double[] array = new Double[17];
        String[] array2 = new String[17];
        AtomicInteger i= new AtomicInteger();
        agrupadoPorCarrera.forEach((carrera, estudiantesPorCarrera)-> {
            array[i.get()] = estudiantesPorCarrera
                    .stream()
                    .mapToDouble(Estudiante::getPuntaje)
                    .average()
                    .getAsDouble();
            array2[i.get()] = carrera;
            System.out.print(carrera+": ");
            System.out.println(estudiantesPorCarrera
                    .stream()
                    .mapToDouble(Estudiante::getPuntaje)
                    .average()
                    .getAsDouble()
                    );
            i.getAndIncrement();
        });
        System.out.println("\nCarrera con mayor puntaje promedio:");
        System.out.println(array2[IntStream.range(0, array.length).boxed().max(Comparator.comparing(x -> array[x])).get()]);
        System.out.println("Puntaje: ");
        System.out.println(array[IntStream.range(0, array.length).boxed().max(Comparator.comparing(x -> array[x])).get()]);
    }

}