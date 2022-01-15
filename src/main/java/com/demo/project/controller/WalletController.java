package com.demo.project.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.request.dto.WalletRequestDto;
import com.demo.project.response.dto.ResponseDto;
import com.demo.project.response.dto.WalletResponseDto;
import com.demo.project.service.WalletService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@PostMapping("/add")
	public ResponseDto<Boolean> addUser(@Valid @RequestBody WalletRequestDto walletRequestDto) {

		log.info("add money in wallet {}", walletRequestDto);

		Boolean status = walletService.addMoney(walletRequestDto);

		return Objects.nonNull(status) ? ResponseDto.success("Money Added Successfully", status)
				: ResponseDto.failure("User Not Created");
	}

	@GetMapping("/get/{mobileNo}")
	public ResponseDto<Double> getWalletMoney(@PathVariable("mobileNo") String mobileNo) {

		log.info("get user wallet Information {} ", mobileNo);

		Double amount = walletService.getWalletMoney(mobileNo);

		return ResponseDto.success("User wallet Details", amount);
	}

	@GetMapping("/list")
	public ResponseDto<List<WalletResponseDto>> getWallet() {
		log.info("wallet list");

		List<WalletResponseDto> wallets = walletService.getWalletList();

		return ResponseDto.success("user wallet List", wallets);
	}
}
