package com.exemplo.orm;

import com.exemplo.orm.dao.PessoaDAO;
import com.exemplo.orm.dao.GenericDAO;
import com.exemplo.orm.model.*;
import com.exemplo.orm.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        try {
            // Criar DAOs
            PessoaDAO pessoaDAO = new PessoaDAO();
            GenericDAO<Documento> documentoDAO = new GenericDAO<>(Documento.class);
            GenericDAO<Telefone> telefoneDAO = new GenericDAO<>(Telefone.class);
            GenericDAO<Projeto> projetoDAO = new GenericDAO<>(Projeto.class);
            
            // Criar uma pessoa
            Pessoa pessoa = new Pessoa("João Silva");
            pessoaDAO.salvar(pessoa);
            
            // Adicionar documentos
            Documento doc1 = new Documento("123456789", "CPF", pessoa);
            Documento doc2 = new Documento("987654321", "RG", pessoa);
            pessoa.addDocumento(doc1);
            pessoa.addDocumento(doc2);
            
            documentoDAO.salvar(doc1);
            documentoDAO.salvar(doc2);
            
            // Adicionar telefones
            Telefone tel1 = new Telefone("(11) 9999-8888", pessoa);
            Telefone tel2 = new Telefone("(11) 7777-6666", pessoa);
            pessoa.addTelefone(tel1);
            pessoa.addTelefone(tel2);
            
            telefoneDAO.salvar(tel1);
            telefoneDAO.salvar(tel2);
            
            // Criar projetos
            Projeto projeto1 = new Projeto("Sistema de Gestão");
            Projeto projeto2 = new Projeto("Portal Web");
            projetoDAO.salvar(projeto1);
            projetoDAO.salvar(projeto2);
            
            // Associar pessoa aos projetos
            pessoa.addProjeto(projeto1);
            pessoa.addProjeto(projeto2);
            pessoaDAO.atualizar(pessoa);
            
            // Listar todas as pessoas
            System.out.println("=== PESSOAS CADASTRADAS ===");
            pessoaDAO.listarTodos().forEach(System.out::println);
            
            // Buscar pessoa por nome
            System.out.println("\n=== BUSCA POR NOME ===");
            pessoaDAO.buscarPorNome("João").forEach(p -> {
                System.out.println("Pessoa: " + p.getNome());
                System.out.println("Documentos: " + p.getDocumentos());
                System.out.println("Telefones: " + p.getTelefones());
                System.out.println("Projetos: " + p.getProjetos());
            });
            
        } finally {
            HibernateUtil.shutdown();
        }
    }
}