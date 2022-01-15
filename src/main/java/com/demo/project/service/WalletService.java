package com.demo.project.service;

import java.util.List;

import com.demo.project.request.dto.WalletRequestDto;
import com.demo.project.response.dto.WalletResponseDto;

public interface WalletService {

	Boolean addMoney(WalletRequestDto walletRequestDto);

	Double getWalletMoney(String mobileNo);

	List<WalletResponseDto> getWalletList();

}
