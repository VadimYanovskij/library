package com.senla.training.library.converter;

import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.entity.Token;
import com.senla.training.library.entity.User;

public interface SecurityDtoConverter {

    User userForRegisterDtoToUser(UserForRegisterDto userForRegisterDto);

    UserForRegisterDto userToUserForRegisterDto (User user);

    Token tokenDtoToToken (TokenDto tokenDto);
}
