package com.green.boardextra;

import com.green.boardextra.model.PostBoardReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
@Tag(name = "게시판", description = "게시판 CRUD")
public class BoardController {
    private final BoardService service ;

    @ApiResponse(
            responseCode = "200",
            description = "적용완료"
    )
    @PostMapping
    @Operation(summary = "게시글 등록", description = "게시글 등록 가능")
    public int postBoard(@RequestBody PostBoardReq p) {
        return service.postBoard(p);
    }

    @PostMapping("file")
    @Operation(summary = "사진과 게시글 등록", description = "사진과 게시글 등록 가능")
    public int PostBoardFile(@RequestPart MultipartFile pic,
                             @RequestPart PostBoardReq p){
        log.info("pic: {}", pic.getOriginalFilename());
        log.info("p: {}", p);
        return 0;
    }

    //Quety String - RequestParam(page, size)
    @GetMapping
    public int getBoard(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
       log.info("page: {}", page);
       log.info("size: {}", size);

       return 1;
       //return "{\"test\": \"반가워\",\"age\": 23}";
    }

    @DeleteMapping
    public int delBoard(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        log.info("page: {}", page);
        log.info("size: {}", size);

        return 1;
        //return "{\"test\": \"반가워\",\"age\": 23}";
    }

    @GetMapping("req")
    public int getBoardReq(HttpServletRequest req){
        String strPage = req.getParameter("page");
        int page = 0;
        if(strPage != null){
            page = Integer.parseInt(strPage);
        }
        log.info("Page: {}", page);
        log.info("strPage: {}", strPage);
        return 12;
    }


    @GetMapping("model")
    public int getBoardModel(@ModelAttribute BoardGetReq p){
        log.info("p: {}", p);
        return 15;
    }
    @GetMapping("model2")
    public int getBoardModel2(@ParameterObject @ModelAttribute BoardGetReq p){
        log.info("p2: {}", p);
        return 15;
    }
}

@Getter
@Setter
@ToString
class BoardGetReq{
    private int page;
    private int size;
}
