package com.metehan.app.ws.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metehan.app.ws.data.model.request.CreateCommentReq;
import com.metehan.app.ws.data.model.request.CreateRestaurantReq;
import com.metehan.app.ws.data.model.response.CreateCommentRes;
import com.metehan.app.ws.data.model.response.CreateRestaurantRes;
import com.metehan.app.ws.service.CommentService;
import com.metehan.app.ws.service.RestaurantService;
import com.metehan.app.ws.shared.CommentDto;
import com.metehan.app.ws.shared.RestaurantDto;

@SpringBootApplication
@RestController
@RequestMapping("/{userId}/{restaurantName}/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<CreateCommentRes> createComment(@PathVariable("userId") String userId, @PathVariable("restaurantName") String restaurantName , @Valid @RequestBody CreateCommentReq commentDetails ) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		CommentDto commentDto = modelMapper.map(commentDetails, CommentDto.class);
		CommentDto createdComment = commentService.createComment(commentDto, userId, restaurantName);
		
		if(createdComment==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			
		}
		
		CreateCommentRes returnValue = modelMapper.map(createdComment, CreateCommentRes.class);
		
		returnValue.setRestaurantName(createdComment.getRestaurant().getRestaurantName());
		returnValue.setUserEmail(createdComment.getUser().getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	
	@DeleteMapping(
			path="/{commentId}"
			)
	public String deleteComment(@PathVariable("userId") String userId, @PathVariable("restaurantName") String restaurantName, @PathVariable("commentId") String commentId) {
		
		if(commentService.deleteComment(userId, restaurantName, commentId)) {
			return "Comment with id " + commentId+" deleted from restaurant "+ restaurantName +" and user with id "+userId;
		}
		
		return "Unsuccessful operation";
	}
	
	

}
