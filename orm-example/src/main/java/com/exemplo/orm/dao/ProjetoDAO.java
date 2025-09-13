package com.exemplo.orm.dao;

import com.exemplo.orm.model.Projeto;
import org.hibernate.Session;

import java.util.List;

public class ProjetoDAO extends GenericDAO<Projeto> {
    
    public ProjetoDAO() {
        super(Projeto.class);
    }
    
    public List<Projeto> buscarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projeto where nome like :nome", Projeto.class)
                    .setParameter("nome", "%" + nome + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Projeto> buscarProjetosComPessoas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select distinct p from Projeto p left join fetch p.pessoas", Projeto.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}