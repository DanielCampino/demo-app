package demo.dto;

import org.springframework.data.domain.Page;

public class PageResponseDTO {

    private final String machineId;
    private final Page<UsuarioResponseDTO> data;

    public PageResponseDTO (String machineId, Page<UsuarioResponseDTO> data){
        this.machineId = machineId;
        this.data = data;
    }

    public Page<UsuarioResponseDTO> getData() {
        return data;
    }

    public String getMachineId() {
        return machineId;
    }

}
