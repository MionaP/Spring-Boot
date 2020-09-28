package com.it355.backend.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.it355.backend.entities.Follow;
import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;

public class UserRepositoryImpl implements UserCustomRepository<User, Integer> {

	@Autowired
	private SessionFactory session;

	@Override
	public List<User> getEmailAndPass() {
		Criteria cr = session.openSession().createCriteria(User.class)
				.setProjection(Projections.projectionList().add(Projections.property("email"), "email")
						.add(Projections.property("password"), "password"))
				.setResultTransformer(Transformers.aliasToBean(User.class));
		List<User> list = cr.list();
		return list;
	}

	@Override
	public User getUser(String email) {
		Criteria criteria = session.openSession().createCriteria(User.class).add(Restrictions.eq("email", email));
		User result = (User) criteria.uniqueResult();
		return result;
	}

	@Override
	public List<User> getUsersSearch(String name, String email) {
		Criteria criteria = session.openSession().createCriteria(User.class).add(Restrictions.eq("name", name))
				.add(Restrictions.ne("email", email));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = criteria.list();
		return users;
	}

	@Override
	public User getUserById(int id) {
		Criteria criteria = session.openSession().createCriteria(User.class).add(Restrictions.eq("user_id", id));
		User result = (User) criteria.uniqueResult();
		return result;
	}

	@Override
	public User getIdByEmail(String email) {
		Criteria criteria = session.openSession().createCriteria(User.class).add(Restrictions.eq("email", email));
		User result = (User) criteria.uniqueResult();

		return result;
	}

	@Override
	public List<User> getProfileImgForPosts(int user_id) {
		String sql = "SELECT DISTINCT u.PROFILE_IMAGE,u.about,u.email,u.name,u.last_name,u.password, u.user_id FROM user u "
				+ " INNER JOIN following f on u.USER_ID = f.FOLLOWED "
				+ " INNER JOIN posts p On f.FOLLOWED = p.USER_ID WHERE u.USER_ID != " + user_id;
		SQLQuery query = session.openSession().createSQLQuery(sql);
		query.addEntity(User.class);
		return query.list();
	}

	@Override
	public List<User> getCommentators() {
		String sql = "SELECT DISTINCT u.name,u.LAST_NAME,u.USER_ID,u.password,u.email,u.about,u.profile_image "
				+ " FROM user u " + " INNER JOIN comment c " + " on u.USER_ID = c.USER_ID";
		SQLQuery query = session.openSession().createSQLQuery(sql);
		query.addEntity(User.class);
		return query.list();
	}

}
