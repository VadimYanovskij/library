package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.AuthenticationUserDto;
import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.entity.Token;
import com.senla.training.library.entity.User;

public interface SecurityDtoConverter {

    User userForRegisterDtoToUser(UserForRegisterDto userForRegisterDto);

    Token tokenDtoToToken (TokenDto tokenDto);
}
