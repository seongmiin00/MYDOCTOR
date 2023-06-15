package hello.login.web.controller;


import hello.login.service.CommentService;
import hello.login.web.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto>create(@PathVariable Long articleId,
                                                  @RequestBody CommentDto dto){
        CommentDto createdDto =commentService.create(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto>update(@PathVariable Long id,
                                            @RequestBody CommentDto dto){
        CommentDto updatedDto = commentService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

    }
    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
