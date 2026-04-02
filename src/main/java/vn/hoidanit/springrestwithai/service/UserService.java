package vn.hoidanit.springrestwithai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.springrestwithai.model.User;
import vn.hoidanit.springrestwithai.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public Optional<User> update(Long id, User updatedUser) {
		return userRepository.findById(id).map(currentUser -> {
			currentUser.setName(updatedUser.getName());
			currentUser.setEmail(updatedUser.getEmail());
			currentUser.setAge(updatedUser.getAge());
			currentUser.setAddress(updatedUser.getAddress());
			currentUser.setGender(updatedUser.getGender());
			currentUser.setAvatar(updatedUser.getAvatar());
			currentUser.setPassword(updatedUser.getPassword());
			return userRepository.save(currentUser);
		});
	}

	public boolean deleteById(Long id) {
		if (!userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}
}