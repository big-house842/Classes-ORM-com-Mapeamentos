package com.exemplo.orm.model;

import javax.persistence.*;

@Entity
@Table(name = "telefone")
public class Telefone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero", length = 20, nullable = false)
    private String numero;
    
    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
    
    public Telefone() {
    }
    
    public Telefone(String numero, Pessoa pessoa) {
        this.numero = numero;
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
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @Override
    public String toString() {
        return "Telefone [id=" + id + ", numero=" + numero + "]";
    }
}