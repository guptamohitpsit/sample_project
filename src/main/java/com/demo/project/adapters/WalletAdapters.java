package com.demo.project.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.demo.project.entity.UserEntity;
import com.demo.project.response.dto.WalletResponseDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletAdapters {

	public static WalletResponseDto getDto(UserEntity userEntity) {

		if (Objects.isNull(userEntity)) {
			return null;
		}
		WalletResponseDto walletResponseDto = WalletResponseDto.builder().MobileNo(userEntity.getMobileNo())
				.wallet(userEntity.getWallet()).build();

		return walletResponseDto;
	}

	public static List<WalletResponseDto> getDtoList(List<UserEntity> users) {

		List<WalletResponseDto> walletList = new ArrayList<>();
		for (UserEntity user : users) {
			walletList.add(getDto(user));
		}
		return walletList;
	}
}
