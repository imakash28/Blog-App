package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.exceptions.ResourceNotFoundException;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired	/*Here, we are autowiring the Modelmapper so that we can use it in this class to convert the one class object into another class object*/
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepository.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
			/*firstly, we are fetching the user which is corresponding to this id*/
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepository.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1 ;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		List<UserDto> userDtos=users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		 this.userRepository.delete(user);
		
	}
	
/**------------------------------------------Using ModelMapping to map the classes----------------------------------------------------------------------*/
	/*Converting Dto class to user class: This method provide us userDto and return us userEntity means it converts one object into another object */
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);	//this map contains two parameters 1.Source class(UserDto) 2. class literal(User)

		/* Now we are using the modelmapper class so we can remove our  mannually conversion implementation */
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	
	//Converting User class to Dto class: This method is doing the vice-versa of dtoToUser() i.e, it converts userEntity to userDto onject
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);	//this map contains two parameters 1.Source class(User) 2. class literal(UserDto)
		
/* Now we are using the modelmapper class so we can remove our  mannually conversion implementation */

//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
/**-----------------------------------------------------------------------------------------------------------------------------------------------------------*/	
}
