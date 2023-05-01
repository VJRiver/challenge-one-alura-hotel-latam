package jdbc.modelo;

import java.sql.Date;

public class ReservasModelo {
    
    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String formaDePago;
    private String valor;

    public ReservasModelo(Date fechaEntrada, Date fechaSalida,  String valor, String formaDePago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.formaDePago = formaDePago;
        this.valor = valor;
    }


    public ReservasModelo(Integer id, Date fechaEntrada, Date fechaSalida, String formaDePago, String valor) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.formaDePago = formaDePago;
        this.valor = valor;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFechaEntrada() {
        return fechaEntrada;
    }


    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }


    public Date getFechaSalida() {
        return fechaSalida;
    }


    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }


    public String getFormaDePago() {
        return formaDePago;
    }


    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }


    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }
    
    

}


























