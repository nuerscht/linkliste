package ch.ffhs.jpa.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ch.ffhs.jpa.dao.intf.UserDao;
import ch.ffhs.jpa.domain.User;

@Named("userDao")
public class UserDaoJpa implements UserDao {
	final private static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public User save(User user) {
		em.persist(user);
		
		return user;
	}
	
	@Override
	public User findByUsername(String username) {
		User user = null;
		TypedQuery<User> q = em.createQuery("SELECT u FROM ch.ffhs.jpa.domain.User u WHERE u.username = :username", User.class);
		q.setParameter("username", username);
		if (!q.getResultList().isEmpty())
			user = q.getResultList().get(0);
		return user;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = findByUsername(username);
		
		if (user != null) {
			String sha512Password = "";
			//check password
			try {
				String passwordSalt = password + user.getSalt();
				MessageDigest sha = MessageDigest.getInstance("SHA-512");
				sha.update(passwordSalt.getBytes("UTF-8"));
				byte[] sha512Bytes = sha.digest();
				sha512Password = bytesToHex(sha512Bytes);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if (!sha512Password.equals(user.getPassword())) {
				user = null;
			}
		}
		
		return user;
	}

	@Override
	public List<User> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		CriteriaQuery<User> qr = query.select(query.from(User.class));
		return em.createQuery(qr).getResultList();
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
