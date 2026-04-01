package vn.hoidanit.springrestwithai.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.hoidanit.springrestwithai.model.User;

@Repository
public class UserRepository {

	private final List<User> users = new ArrayList<>();

	public UserRepository() {
		users.add(new User(1L, "Nguyen Van A", "a@example.com"));
		users.add(new User(2L, "Tran Thi B", "b@example.com"));
		users.add(new User(3L, "Le Van C", "c@example.com"));
	}

	public List<User> findAll() {
		return new ArrayList<>(users);
	}

	public User findById(Long id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(getNextId());
		}
		users.add(user);
		return user;
	}

	public User update(Long id, User updatedUser) {
		for (int i = 0; i < users.size(); i++) {
			User currentUser = users.get(i);
			if (currentUser.getId().equals(id)) {
				currentUser.setName(updatedUser.getName());
				currentUser.setEmail(updatedUser.getEmail());
				return currentUser;
			}
		}
		return null;
	}

	public boolean deleteById(Long id) {
		return users.removeIf(user -> user.getId().equals(id));
	}

	private Long getNextId() {
		long maxId = 0L;
		for (User user : users) {
			if (user.getId() > maxId) {
				maxId = user.getId();
			}
		}
		return maxId + 1;
	}
}