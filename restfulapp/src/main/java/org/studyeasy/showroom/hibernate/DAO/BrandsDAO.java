package org.studyeasy.showroom.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.showroom.hibernate.entities.BrandEntity;

public class BrandsDAO {

	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(BrandEntity.class)
			.buildSessionFactory();


	public List<BrandEntity> getBrands() {
		// (1) create session factory
		Session session = factory.getCurrentSession();
		// (2) begin session transaction
		session.beginTransaction();
		// (3) This method returns a list so we need to create a list of BrandEntity
		// we use hibernate query language
		List<BrandEntity> list = session.createQuery("from brands").getResultList();
		return list;
	}

	public void addBrand(BrandEntity brand) {
		// (1) create session factory
		Session session = factory.getCurrentSession();
		// (2) begin session transaction
		session.beginTransaction();	
		session.save(brand);
		session.getTransaction().commit();
	}

	public void updateBrand(BrandEntity updatedBrand) {
		// (1) create session factory
		Session session = factory.getCurrentSession();
		// (2) begin session transaction
		session.beginTransaction();	
		int id = updatedBrand.getBrandId();
		BrandEntity brand = session.get(BrandEntity.class, id);
		brand.setBrandName(updatedBrand.getBrandName());
		session.getTransaction().commit();

	}

	public void deleteBrand(int brandId) {
		// (1) create session factory
		Session session = factory.getCurrentSession();
		// (2) begin session transaction
		session.beginTransaction();	
		BrandEntity brand = session.get(BrandEntity.class, brandId);
		session.delete(brand);
		session.getTransaction().commit();
		
	}

}
