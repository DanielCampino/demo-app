package demo.dto;

import org.springframework.data.domain.Slice;

public class PageResponseDTO {

    private final String machineId;
    private final Slice<UsuarioResponseDTO> data;

    public PageResponseDTO (String machineId, Slice<UsuarioResponseDTO> data){
        this.machineId = machineId;
        this.data = data;
    }

    public Slice<UsuarioResponseDTO> getData() {
        return data;
    }

    public String getMachineId() {
        return machineId;
    }

}
