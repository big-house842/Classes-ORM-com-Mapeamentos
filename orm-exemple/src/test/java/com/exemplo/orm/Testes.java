package com.exemplo.orm;

import com.exemplo.orm.dao.PessoaDAO;
import com.exemplo.orm.dao.ProjetoDAO;
import com.exemplo.orm.model.*;
import com.exemplo.orm.util.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Testes {
    
    private PessoaDAO pessoaDAO;
    private ProjetoDAO projetoDAO;
    
    @Before
    public void setUp() {
        pessoaDAO = new PessoaDAO();
        projetoDAO = new ProjetoDAO();
    }
    
    @After
    public void tearDown() {
        HibernateUtil.shutdown();
    }
    
    @Test
    public void testSalvarPessoa() {
        Pessoa pessoa = new Pessoa("Maria Santos");
        pessoaDAO.salvar(pessoa);
        
        assertNotNull("ID não deve ser nulo após salvar", pessoa.getId());
        assertEquals("Nome deve ser igual", "Maria Santos", pessoa.getNome());
    }
    
    @Test
    public void testBuscarPessoaPorId() {
        Pessoa pessoa = new Pessoa("Carlos Oliveira");
        pessoaDAO.salvar(pessoa);
        
        Pessoa encontrada = pessoaDAO.buscarPorId(pessoa.getId());
        assertNotNull("Pessoa deve ser encontrada", encontrada);
        assertEquals("Nomes devem ser iguais", "Carlos Oliveira", encontrada.getNome());
    }
    
    @Test
    public void testAdicionarDocumentoAPessoa() {
        Pessoa pessoa = new Pessoa("Ana Costa");
        Documento documento = new Documento("11122233344", "CPF", pessoa);
        
        pessoa.addDocumento(documento);
        pessoaDAO.salvar(pessoa);
        
        Pessoa pessoaComDocumentos = pessoaDAO.buscarPorId(pessoa.getId());
        assertEquals("Deve ter 1 documento", 1, pessoaComDocumentos.getDocumentos().size());
        assertEquals("Número do documento deve ser igual", "11122233344", 
                     pessoaComDocumentos.getDocumentos().get(0).getNumero());
    }
    
    @Test
    public void testAdicionarTelefoneAPessoa() {
        Pessoa pessoa = new Pessoa("Pedro Alves");
        Telefone telefone = new Telefone("(11) 8888-7777", pessoa);
        
        pessoa.addTelefone(telefone);
        pessoaDAO.salvar(pessoa);
        
        Pessoa pessoaComTelefones = pessoaDAO.buscarPorId(pessoa.getId());
        assertEquals("Deve ter 1 telefone", 1, pessoaComTelefones.getTelefones().size());
        assertEquals("Número do telefone deve ser igual", "(11) 8888-7777", 
                     pessoaComTelefones.getTelefones().get(0).getNumero());
    }
    
    @Test
    public void testRelacionamentoManyToMany() {
        Pessoa pessoa = new Pessoa("João Silva");
        Projeto projeto = new Projeto("Sistema de Gestão");
        
        pessoa.addProjeto(projeto);
        pessoaDAO.salvar(pessoa);
        projetoDAO.salvar(projeto);
        
        List<Projeto> projetos = projetoDAO.buscarProjetosComPessoas();
        assertFalse("Lista de projetos não deve estar vazia", projetos.isEmpty());
        assertFalse("Projeto deve ter pessoas associadas", projetos.get(0).getPessoas().isEmpty());
    }
    
    @Test
    public void testBuscarPorNome() {
        Pessoa pessoa1 = new Pessoa("Mariana Lima");
        Pessoa pessoa2 = new Pessoa("Mariano Souza");
        
        pessoaDAO.salvar(pessoa1);
        pessoaDAO.salvar(pessoa2);
        
        List<Pessoa> resultados = pessoaDAO.buscarPorNome("Mari");
        assertEquals("Deve encontrar 2 pessoas", 2, resultados.size());
    }
    
    @Test
    public void testAtualizarPessoa() {
        Pessoa pessoa = new Pessoa("Fernanda Torres");
        pessoaDAO.salvar(pessoa);
        
        pessoa.setNome("Fernanda Torres Silva");
        pessoaDAO.atualizar(pessoa);
        
        Pessoa atualizada = pessoaDAO.buscarPorId(pessoa.getId());
        assertEquals("Nome deve ser atualizado", "Fernanda Torres Silva", atualizada.getNome());
    }
    
    @Test
    public void testDeletarPessoa() {
        Pessoa pessoa = new Pessoa("Ricardo Mendes");
        pessoaDAO.salvar(pessoa);
        
        Long id = pessoa.getId();
        pessoaDAO.deletar(pessoa);
        
        Pessoa deletada = pessoaDAO.buscarPorId(id);
        assertNull("Pessoa deve ser deletada", deletada);
    }
}