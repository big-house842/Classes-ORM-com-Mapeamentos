package com.exemplo.orm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projeto")
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @ManyToMany(mappedBy = "projetos")
    private List<Pessoa> pessoas = new ArrayList<>();
    
    public Projeto() {
    }
    
    public Projeto(String nome) {
        this.nome = nome;
    }
    
    // gets e sets
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    @Override
    public String toString() {
        return "Projeto [id=" + id + ", nome=" + nome + "]";
    }
}