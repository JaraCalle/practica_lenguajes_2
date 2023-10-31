public class Estudiante {
    private String id;
    private String primerNombre;
    private String apellido;
    private String genero;
    private String carrera;
    private Double puntaje;

    public Estudiante(String id, String primerNombre, String apellido, String genero, String carrera, Double puntaje) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.apellido = apellido;
        this.genero = genero;
        this.carrera = carrera;
        this.puntaje = puntaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "Id='" + id + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", apellido=" + apellido +
                ", genero='" + genero +
                ", carrera='" + carrera +
                ", puntaje=' " + puntaje + '\'' +
                '}';
    }
}
