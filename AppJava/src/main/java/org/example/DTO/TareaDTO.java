package org.example.DTO;

public class TareaDTO {
    private int id;
    private String descripcion;
    private int inquilinoId;

    public TareaDTO(int id, String descripcion, int inquilinoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.inquilinoId = inquilinoId;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }
}
