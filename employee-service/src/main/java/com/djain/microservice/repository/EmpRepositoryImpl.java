package com.djain.microservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.djain.microservice.model.Emp;

public class EmpRepositoryImpl {
	@PersistenceContext
	private EntityManager em;

	public List<Emp> getempdetails(String val) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Emp> q = cb.createQuery(Emp.class);
		Root<Emp> c = q.from(Emp.class);
		ParameterExpression<String> p = cb.parameter(String.class);
		q.select(c).where(cb.equal(c.get("firstName"), p));

		return em.createQuery(q).setParameter(p, val).getResultList();
	}
}
