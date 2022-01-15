package com.demo.project.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.request.dto.UserRequestDto;
import com.demo.project.response.dto.ResponseDto;
import com.demo.project.response.dto.UserResponseDto;
import com.demo.project.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseDto<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		log.info("Added new user is {}", userRequestDto);

		UserResponseDto userResponseDto = userService.createUser(userRequestDto);
		log.info("Added new user with id: {} ", userResponseDto.getMobileNo());
		return Objects.nonNull(userResponseDto) ? ResponseDto.success("New User Created", userResponseDto)
				: ResponseDto.failure("User Not Created");
	}

	@GetMapping("get/{mobileNo}")
	public ResponseDto<UserResponseDto> getUserDetails(@PathVariable("mobileNo") String mobileNo) {
		log.info("get user Information {} ", mobileNo);

		UserResponseDto userInformation = userService.getUserDetails(mobileNo);

		return ResponseDto.success("User details", userInformation);
	}

	@GetMapping("list")
	public ResponseDto<List<UserResponseDto>> getAllUser() {
		log.info("get all user ");

		List<UserResponseDto> userInformations = userService.getAllUser();

		return ResponseDto.success("User List", userInformations);
	}

	@DeleteMapping("delete/{mobileNo}")
	public ResponseDto<Boolean> deActivateUser(@PathVariable("mobileNo") String mobileNo) {
		log.info("delete user Information {} ", mobileNo);

		boolean status = userService.deActivateUser(mobileNo);

		return status ? ResponseDto.success("User deactivated succesfully", status)
				: ResponseDto.failure("User not deleted");
	}

	@PutMapping("update")
	public ResponseDto<UserResponseDto> updateUser(@RequestBody @Valid UserRequestDto userRequestDto) {
		log.info("Update user request is {} ", userRequestDto);

		UserResponseDto userResponseDto = userService.updateUser(userRequestDto);

		log.info("Update user with id:{} ", userResponseDto.getMobileNo());

		return ResponseDto.success("User Updated", userResponseDto);
	}

}
