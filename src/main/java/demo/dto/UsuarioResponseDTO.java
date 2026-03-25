package demo.dto;

public class UsuarioResponseDTO {

    private final long id;
    private final String nombre;
    private final String email;
    private final String servidor;

    public UsuarioResponseDTO(long id, String nombre, String email, String servidor) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.servidor = servidor;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getServidor() { return servidor; }

}
