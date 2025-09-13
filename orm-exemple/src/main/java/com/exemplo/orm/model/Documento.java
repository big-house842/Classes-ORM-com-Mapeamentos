package com.exemplo.orm.model;

import javax.persistence.*;

@Entity
@Table(name = "documento")
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero", length = 50, nullable = false)
    private String numero;
    
    @Column(name = "tipo", length = 30, nullable = false)
    private String tipo;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
    
    public Documento() {
    }
    
    public Documento(String numero, String tipo, Pessoa pessoa) {
        this.numero = numero;
        this.tipo = tipo;
        this.pessoa = pessoa;
    }
    
    // gets e sets
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @Override
    public String toString() {
        return "Documento [id=" + id + ", numero=" + numero + ", tipo=" + tipo + "]";
    }
}