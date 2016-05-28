package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.Author;
import com.nju.edu.cn.software.domain.AuthorImage;

public interface AuthorMapper {

	void saveAuthor(Author author);
	Author getAuthorById(int id);
	Author getAuthorByNameAndPass(@Param("loginName")String username,@Param("password")String password);
	void updateAuthor(Author author);
	void deleteAuthor(int id);
	void updateUserName(@Param("username") String username,@Param("authorId") int authorId);
	List<Author> getAuthor();
	void updateHeadIcon(@Param("id")int id,@Param("headIconUrl")String headIconUrl);
	void updateBgImg(@Param("id")int id,@Param("bgUrl")String bgUrl);
	String getTokenIdByAuthorId(@Param("author_id")int author_id);//depend author id get token_id
	void updateTokenId(@Param("tokenId")String tokenId);//update token_id
	String getAuthorName(@Param("id")int id);
	int validatePhoneIsRegister(@Param("phone") String phone);
	int validateEmailIsRegister(@Param("email")String email);
	int validateUsernameIsRegister(@Param("loginName") String username);
	AuthorImage getAuthorImage(@Param("authorId") int authorId);
	
	
}
