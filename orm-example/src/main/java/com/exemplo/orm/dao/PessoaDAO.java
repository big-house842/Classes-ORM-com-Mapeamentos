package com.exemplo.orm.dao;

import com.exemplo.orm.model.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PessoaDAO extends GenericDAO<Pessoa> {
    
    public PessoaDAO() {
        super(Pessoa.class);
    }
    
    public List<Pessoa> buscarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Pessoa where nome like :nome", Pessoa.class)
                    .setParameter("nome", "%" + nome + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}