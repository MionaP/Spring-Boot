package com.it355.backend.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties.Hibernate;

import com.it355.backend.entities.Follow;
import com.it355.backend.entities.Post;
import com.it355.backend.entities.User;

public class FollowRepositoryImpl implements FollowCustomRepository<Follow, Integer> {

	@Autowired
	private SessionFactory session;

	@Override
	public Follow getFollowing(String email) {
		Follow follow = new Follow();
		int followed = 0;
		try {
			List res = session.openSession()
					.createSQLQuery("SELECT f.user_id,COUNT(f.FOLLOWED) as followed " + "FROM Following f "
							+ "INNER JOIN User u " + "    on u.USER_ID = f.USER_ID WHERE u.EMAIL = '" + email + "'")
					.addScalar("user_id", IntegerType.INSTANCE).addScalar("followed", IntegerType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(Follow.class)).list();
			follow = (Follow) res.get(0);

			return follow;
		} catch (Exception ex) {
			follow.setUser_id(0);
			List res = session.openSession()
					.createSQLQuery("SELECT COUNT(f.FOLLOWED) as followed " + "FROM Following f " + "INNER JOIN User u "
							+ "    on u.USER_ID = f.USER_ID WHERE u.EMAIL = '" + email + "'")
					.addScalar("followed", IntegerType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(Follow.class)).list();
			follow = follow = (Follow) res.get(0);
			return follow;
		}
	}

	@Override
	public Follow getFollowed(String email) {
		try {
			List res = session.openSession()
					.createSQLQuery("SELECT COUNT(f.user_id) as user_id ,f.FOLLOWED FROM "
							+ "following f INNER JOIN user u on u.USER_ID = f.FOLLOWED WHERE u.EMAIL = '" + email + "'")
					.addScalar("user_id", IntegerType.INSTANCE).addScalar("followed", IntegerType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(Follow.class)).list();
			Follow follow = (Follow) res.get(0);

			return follow;
		} catch (Exception ex) {
			Follow follow = new Follow();
			follow.setFollowed(0);
			follow.setUser_id(0);
			return follow;
		}
	}

	@Override
	public Follow checkIfFollow(int user_id, int selected_id) {
		try {
			List res = session.openSession()
					.createSQLQuery("SELECT f.user_id, f.FOLLOWED FROM following f INNER JOIN user u "
							+ "on u.USER_ID = f.USER_ID WHERE u.user_id = " + user_id + " AND f.FOLLOWED = "
							+ selected_id)
					.addScalar("user_id", IntegerType.INSTANCE).addScalar("followed", IntegerType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(Follow.class)).list();
			Follow follow = (Follow) res.get(0);

			return follow;
		} catch (Exception ex) {
			Follow follow = new Follow();
			follow.setUser_id(0);
			follow.setFollowed(0);
			return follow;
		}
	}

}
