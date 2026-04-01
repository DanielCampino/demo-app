package demo.dto;

import java.util.List;

public class PageResponseDTO {

    private final String machineId;
    private final List<UsuarioResponseDTO> data;

    public PageResponseDTO (String machineId, List<UsuarioResponseDTO> data){
        this.machineId = machineId;
        this.data = data;
    }

    public List<UsuarioResponseDTO> getData() {
        return data;
    }

    public String getMachineId() {
        return machineId;
    }

}
