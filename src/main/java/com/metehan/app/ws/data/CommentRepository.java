package com.metehan.app.ws.data;

import org.springframework.data.repository.CrudRepository;

import com.metehan.app.ws.data.model.entity.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long>  {

}