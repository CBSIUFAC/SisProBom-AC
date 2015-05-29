package DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.DataException;

import util.HibernateUtil;

public class MasterDAO implements Serializable {

	public Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	//Inserir
	public void inserirObjeto(Object obj) throws DataException {
		Session s = getSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
		s.close();
	}
	
	//Deletar
	public void deletarObjeto(Object obj){
		Session s = getSession();
		s.beginTransaction();
		s.delete(obj);
		s.getTransaction().commit();
		s.close();
	}
	
	//Atualizar
	public void atualizarObjeto(Object obj) {
		Session s = getSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
		s.close();
	}
	
	//Get
	public <T extends Serializable> T getObjeto(Class<T> classe, int id) {
		Session s = getSession();
		s.beginTransaction();
		Serializable retorno = (Serializable)s.get(classe, id);
		s.getTransaction().commit();
		s.close();
		return (T)retorno;
	}
	
	//Lista
	public <T extends Serializable> List<T> getLista(String str) {
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery(str);
		List<T> retorno = qr.list();
		s.getTransaction().commit();
		s.close();
		return retorno;
	}
	
}