package net.softsociety.spring5.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.service.BoardService;
import net.softsociety.spring5.util.FileService;
import net.softsociety.spring5.util.PageNavigator;


@Controller
@RequestMapping("board")
@Slf4j
public class BoardController {
	@Autowired
	BoardService service;

	//application.properties의 사용자 정의 설정 사용. 바로 아래에 대입해준다. (spring framework)
	//게시판 글 노출 수
	@Value("${user.board.page}")
	int countPerPage;
	//페이지 번호 그룹 당 수
	@Value("${user.board.group}")
	int pagePerGroup;
	//파일 업로드 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath; 
	
	//홈화면에서 게시판 이동
	@GetMapping("list")
	public String list(
			Model model, 
			@RequestParam(name="page", defaultValue="1") int page, //처음 게시판 목록에 들어올 때는, page와 type, searchWord가 null임.
			String type, 										   //type과 searchWord는 없어도 되지만, page는 null이면 오류뜨기 때문에 기본값을 1로!
			String searchWord) {
		
		log.debug("페이지 당 글수 : {}, 페이지 이동 링크 수 : {} ", countPerPage, pagePerGroup);
		log.debug("현재 페이지 : {}, 검색 대상 : {}, 검색어 : {}", page, type, searchWord);
		
		//목록의 글 개수 표시부분 '전체' or '검색결과' 출력 변환용 int flag
		int flag = 0;
		if(type != null && searchWord != null) {
			flag = 1;
		}
		
		//pageNavigator 객체생성. 이 객체를 생성하려면 여러 계산이 필요하기 때문에 service에 이부분을 넣어둔다.
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);
		
		
		//DB에서 게시판의 모든 글을 조회하여 ArrayList타입으로 저장. 글은 최대 10개까지 저장되어있음.
		ArrayList<Board> list = service.selectAllBoard(navi, type, searchWord);

		
		log.debug("게시글 수 : {}", list.size());
		
		//모델에 ArrayList 저장
		model.addAttribute("navi", navi);
		model.addAttribute("list", list);
		model.addAttribute("flag", flag);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		
		return "/boardView/list";
	}
	
	//게시판화면에서 글쓰기 이동
	@GetMapping("write")
	public String write() {
		return "/boardView/writeForm";
	}
	
	/**
	 * 글쓰기 폼에 대한 컨트롤러
	 * @param board : 작성한 게시글의 정보가 포함된 VO
	 * @param user : 로그인 정보를 받아오는 객체
	 * @param upload : 업로드된 파일
	 * @return
	 */
	@PostMapping("writeBoard")
	public String writeBoard(
			Board board 
			, @AuthenticationPrincipal UserDetails user
			, MultipartFile upload						//업로드된 파일
			) {
		//넘어온 게시글 정보
		log.debug("저장할 글 정보 : {}", board);
		//게시글 정보에 로그인 정보 기입
		board.setMemberid(user.getUsername());
		
		//파일이 null이 아닌 경우, 파일의 정보를 보여줌 (테스트용)
		if(upload != null) {
			log.debug("이름 : {}", upload.getName());						//파라미터의 이름
			log.debug("원래 이름 : {}", upload.getOriginalFilename()); 	//원래 올렸던 파일의 이름
			log.debug("타입 : {}", upload.getContentType());				//파일의 컨텐트 타입
			log.debug("사이즈 : {}", upload.getSize());					//파일의 사이즈 : 파일의 사이즈에 제한을 둘 수 있음
			log.debug("비었는지 여부 : {}", upload.isEmpty());				//파일이 비어있는지...?
		} else {
			log.debug("file is null");
		}
		
		
		/* 파일 저장 시, 이름의 중복 문제를 어떻게 할 것인지가 중요함.
		 * 예를 들어, 기존에 같은 이름이 있는지 보고 이름에 +1을 한다던지
		 * 아니면 아예 새로운 이름을 부여하고 저장을 할지 등등...
		 * 우리는 파일명 저장 칼럼을 2개를 준비했음... 원래 이름, 저장된 이름
		 * 때문에 오늘 날짜 정보로 이름을 붙여 저장할 것 (날짜 + 숫자). 여기선 util.FileService에 있음
		 */
		//업로드
		if(upload != null && !upload.isEmpty()) {
			//저장할 파일, 경로. 리턴값은 저장된 파일명
			String savefile = FileService.saveFile(upload, uploadPath);
			
			//게시글 정보에 병합
			board.setOriginalfile(upload.getOriginalFilename());
			board.setSavedfile(savefile);
		}
		
		log.debug("저장할 글 정보 : {}", board);

		int result = service.insertBoard(board);
		
		return "redirect:/board/list";
	}
	
	//글읽기
	@GetMapping("read")
	public String read(
			@RequestParam(name="boardnum", defaultValue = "0") int boardnum,
			Model model) {	
		/*	DB에서 해당 글의 번호를 가져온다. 글 제목 누르면 번호가 param으로 같이 와야한다.
		 *	"/board/read?boardnum=1"이런식...! (list.html에서 th:href="@{/board/read(boardnum=${board.boardnum})}")
		 *	이 컨트롤러에 int boardnum의 파라미터가 있는데, html의 th:href에서 그냥 /board/read라고만 쓰면 null이 오기 때문에 오류가 난다.
		 *	이에 대한 오류의 예외처리를 위해 한가지 장치를 해야하는데, @RequestParam으로 기본값을 0으로 하여, 이때에는  아무 글도 보이지 않게 해준다.
		 */
		Board board = service.selectBoard(boardnum);
		//만약 글이 없으면 다시 목록으로!
		if(board == null) {
			log.debug("글 없음!");
			return "redirect:/board/list";
		}
		//조회수를 update해줌
		int result = service.updateHits(boardnum);
		
		/**
		 * DB에서 해당 글의 리플들을 다 가져온다(입력시간순으로)
		 * 모델에 넣어 보낸다.
		 */
		ArrayList<Reply> rplist = service.selectAllReply(boardnum);
		log.debug("가져온 rp목록 : {}", rplist);
	
		//model에 저장하여 보낸다.
		model.addAttribute("board", board);
		model.addAttribute("rplist", rplist);
		
		return "/boardView/read";
	}
	
	//파일 다운로드
	/**
	 * 파일 다운로드 받는 컨트롤러
	 * @param boardnum : 다운 받을 파일이 저장된 게시글 번호
	 * @param model	   : HttpServletResponse는 서버에서 클라이언트로 무엇인가를 내보낼때 사용. ex) 쿠키를 클라이언트 측에 저장한다.
	 * @param response
	 * @return
	 */
	@GetMapping("download")
	public String fileDownload(int boardnum, Model model, HttpServletResponse response) {
		//파일의 원본이름과, 저장된 이름을 알아내기 위해서 해당 게시글을 불러온다.
		Board board = service.selectBoard(boardnum);
		
		//원래의 파일명
		String originalfile = new String(board.getOriginalfile());
		try {
			//헤더: 웹브라우저에게 지금부터 나가는 응답의 종류가 무엇인지 알려줌. 주소에 originalfile의 이름을 붙여서 보내는데,
			//한글이나 특수문자가 있기 때문에 encoding해서 보냄
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + board.getSavedfile();
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림 생성
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력 (filein --> file out)방향으로 카피한다.
			FileCopyUtils.copy(filein, fileout);
			
			//IO스트림 닫기.
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//다운로드는 웹브라우저가 알아들을 수 없는 언어가 오면 자동으로 다운로드 받는 것. img파일은 img태그가 없으면 알아먹지 못해서...
		//클라이언트 입장에서 다운로드 받는 경로는 해당 웹브라우저의 설정을 따른다.
		//중요!!!! 글 삭제 시, 해당 게시글에 파일이 있는지 확인하고 같이 삭제해야함.
		//중요!!!! 글 수정 시, 해당 게시글에 파일이 있는지 확인하고, 해당 파일이 수정되면 기존 파일도 삭제해야함.
		return null;
	}
	
	/**
	 * 게시글 삭제 컨트롤러
	 * @param boardnum : 삭제할 글 번호
	 * @param user : 현재 로그인 정보
	 * @return
	 */
	@GetMapping("delete")
	public String delete(
			int boardnum,
			@AuthenticationPrincipal UserDetails user
			) {
		//삭제 전에 해당 글 정보 조회 (해당 글에 첨부파일이 있는지 확인하기 위해)
		Board board = service.selectBoard(boardnum);
		if(board == null) {
			return "redirect:list";
		}
		
		//해당 글에 첨부파일 이름 확인하기
		String savedFile = board.getSavedfile();
		
		//현재 로그인 아이디 확인
		String memberid = user.getUsername();
		
		//글 삭제 (글 번호와 아이디 전달)
		int result = service.deleteBoard(boardnum, memberid);
		
		//글이 성공적으로 삭제가 되었고 첨부파일이 있다면 해당 파일도 삭제하기
		if(result == 1 && savedFile != null) {
			String fullpath = uploadPath + "/" + savedFile;
			FileService.deleteFile(fullpath);
		}
		
		return "redirect:list";
	}
	
	//게시글 수정 폼으로 이동
	@GetMapping("update")
	public String update(int boardnum, Model model) {
		//전달 받은 글의 정보를 가지고 해당 페이지로 이동한다.
		Board board = service.selectBoard(boardnum);
		model.addAttribute("board", board);
		
		return "/boardView/updateForm";
	}
	
	//게시글 수정 컨트롤러
	@PostMapping("update")
	public String update(
			Board board,
			MultipartFile upload,
			@AuthenticationPrincipal UserDetails user) {
		//첨부파일 처리
		
		//본인 글인지 확인
		
		//수정
		
		//읽던 본인 글로 이동
		return "redirect:/board/read?boardnum=" + board.getBoardnum();
	}
	
	
	//댓글 입력
	@PostMapping("replyWrite")
	public String replyWrite(
			int boardnum, 
			String replytext,
			@AuthenticationPrincipal UserDetails user) {
		//reply객체(본문 글번호, 리플 내용을 포함)에 로그인 아이디를 추가하고
		Reply reply = new Reply();
		reply.setBoardnum(boardnum);
		reply.setReplytext(replytext);
		reply.setMemberid(user.getUsername());
		
		//DB 저장
		int result = service.replyWrite(reply);
		
		//읽던 글로 돌아가기
		return "redirect:/board/read?boardnum=" + boardnum;
	}
	
	//댓글 삭제
	@GetMapping("deleteReply")
	public String deleteReply(
			int boardnum,
			int replynum,
			@AuthenticationPrincipal UserDetails user) {
		//해당 댓글의 정보를 가져온다.
		Reply reply = service.selectReply(replynum);
		log.debug("가져온 리플 : {}", reply.toString());

		//현재 접속자 계정명을 reply 정보에 넣는다. (sql단에서 해당 접속자가 다른 접속자라면 where절이 부합하지 않기 때문에 삭제되지 않는다.)
		reply.setMemberid(user.getUsername());
		log.debug("작성자 변경 리플 : {}", reply.toString());
		
		//해당 리플 정보를 service에 보내 삭제한다
		int result = service.deleteReply(reply);
		
		return "redirect:/board/read?boardnum=" + boardnum;
	}
}



















