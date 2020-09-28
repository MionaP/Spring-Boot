package com.it355.backend.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.ByteType;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;

import com.it355.backend.entities.Follow;
import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;

public class PostRepositoryImpl implements PostCustomRepository<Post, Integer> {

	@Autowired
	private SessionFactory session;

	@Override
	public List<Post> getPostsById(int user_id) {
		Criteria criteria = session.openSession().createCriteria(Post.class)
				.add(Restrictions.eq("user.user_id", user_id));
		List<Post> posts = criteria.list();
		return posts;
	}

	@Override
	public List<Post> getCurrentUsersPost(String email) {
		Criteria postCriteria = session.openSession().createCriteria(Post.class);
		Criteria userCriteria = postCriteria.createCriteria("user", "u");
		userCriteria.add(Restrictions.eq("email", email));

		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.property("user_id"), "user_id");
		properties.add(Projections.property("image"), "image");

		return postCriteria.setProjection(properties).setResultTransformer(Transformers.aliasToBean(Post.class)).list();

	}

	@Override
	public List<Post> getPostFromFollowed(int user_id) {
		String sql = "SELECT p.image,p.user_id,p.post_id FROM posts p "
				+ "	INNER JOIN following f on p.USER_ID = f.FOLLOWED "
				+ "	INNER JOIN user u ON  u.USER_ID = f.USER_ID " + "	WHERE u.USER_ID =  " + user_id
				+ "  ORDER BY p.POST_ID DESC";
		SQLQuery query = session.openSession().createSQLQuery(sql);
		query.addEntity(Post.class);
		return query.list();

	}

}
