package vn.hoidanit.springrestwithai.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.springrestwithai.dto.ApiResponse;
import vn.hoidanit.springrestwithai.model.User;
import vn.hoidanit.springrestwithai.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(ApiResponse.success("Lấy danh sách người dùng thành công", users));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(ApiResponse.success("Lấy thông tin người dùng thành công", user));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
		User createdUser = userService.create(user);
		URI location = URI.create("/users/" + createdUser.getId());
		return ResponseEntity.created(location)
				.body(ApiResponse.created("Tạo người dùng mới thành công", createdUser));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		User updated = userService.update(id, user);
		return ResponseEntity.ok(ApiResponse.success("Cập nhật người dùng thành công", updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
