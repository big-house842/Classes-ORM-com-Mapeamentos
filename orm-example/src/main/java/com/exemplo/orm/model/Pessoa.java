package com.exemplo.orm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();
    
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "pessoa_projeto",
        joinColumns = @JoinColumn(name = "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Projeto> projetos = new ArrayList<>();
    
    public Pessoa() {
    }
    
    public Pessoa(String nome) {
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
    
    public List<Documento> getDocumentos() {
        return documentos;
    }
    
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    
    public List<Telefone> getTelefones() {
        return telefones;
    }
    
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
    public List<Projeto> getProjetos() {
        return projetos;
    }
    
    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
    
    // Métodos utilitários para manter a consistência bidirecional
    public void addDocumento(Documento documento) {
        documentos.add(documento);
        documento.setPessoa(this);
    }
    
    public void removeDocumento(Documento documento) {
        documentos.remove(documento);
        documento.setPessoa(null);
    }
    
    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
        telefone.setPessoa(this);
    }
    
    public void removeTelefone(Telefone telefone) {
        telefones.remove(telefone);
        telefone.setPessoa(null);
    }
    
    public void addProjeto(Projeto projeto) {
        projetos.add(projeto);
        projeto.getPessoas().add(this);
    }
    
    public void removeProjeto(Projeto projeto) {
        projetos.remove(projeto);
        projeto.getPessoas().remove(this);
    }
    
    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + "]";
    }
}