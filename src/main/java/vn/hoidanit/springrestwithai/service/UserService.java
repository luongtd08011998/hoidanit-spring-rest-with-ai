package vn.hoidanit.springrestwithai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.springrestwithai.exception.DuplicateResourceException;
import vn.hoidanit.springrestwithai.exception.ResourceNotFoundException;
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

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Người dùng", "id", id));
	}

	public User create(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new DuplicateResourceException("Người dùng", "email", user.getEmail());
		}
		return userRepository.save(user);
	}

	public User update(Long id, User updatedUser) {
		User currentUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Người dùng", "id", id));
		currentUser.setName(updatedUser.getName());
		currentUser.setEmail(updatedUser.getEmail());
		currentUser.setAge(updatedUser.getAge());
		currentUser.setAddress(updatedUser.getAddress());
		currentUser.setGender(updatedUser.getGender());
		currentUser.setAvatar(updatedUser.getAvatar());
		currentUser.setPassword(updatedUser.getPassword());
		return userRepository.save(currentUser);
	}

	public void deleteById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("Người dùng", "id", id);
		}
		userRepository.deleteById(id);
	}
}