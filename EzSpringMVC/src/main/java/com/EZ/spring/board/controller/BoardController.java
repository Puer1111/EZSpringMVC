package com.EZ.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.EZ.spring.board.Service.BoardService;
import com.EZ.spring.board.domain.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService bService;

	@RequestMapping(value="/detail.kh",method=RequestMethod.GET)
	public String showBoardDetail(Model model
			,@RequestParam("boardNo")Integer boardNo) {
		BoardVO board = bService.selectOneByNo(boardNo);
		if(board!=null) {
			model.addAttribute("board",board);
			return "board/detail";
		}else {
			model.addAttribute("msg", "없는 내용");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/board/list.kh", method = RequestMethod.GET)
	public String ShowBoardInfo(Model model,
			@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage) {
		
			List<BoardVO> bList = bService.selectBoardList(currentPage);
			int recordCountPerPage = 10;
			int naviCountPerPage = 10;
			int naviTotalCount = 0;
			int TotalCount = bService.getTotalCount();
			
			if (TotalCount % recordCountPerPage > 0) {
				naviTotalCount = TotalCount / recordCountPerPage + 1;
			}else {
				naviTotalCount=TotalCount/recordCountPerPage;
			}
			int startNavi = (currentPage - 1) / recordCountPerPage * recordCountPerPage+1;
			int endNavi = (startNavi + naviCountPerPage) - 1;

			if (endNavi > naviTotalCount) {
				endNavi = naviTotalCount;
			}
			model.addAttribute("startNavi",startNavi);
			model.addAttribute("endNavi",endNavi);
			model.addAttribute("naviTotalCount",naviTotalCount);
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("bList",bList);
			return "board/list";

		
	}

	@RequestMapping(value = "/board/register.kh", method = RequestMethod.POST)
	public String RegistBoardInfo(Model model, HttpSession session, RedirectAttributes rattr,
			@ModelAttribute BoardVO board)
//		,@RequestParam("boardTitle") String boardTitle
//		,@RequestParam("boardContent") String boardContent) 
	{
		try {
			String boardWriter = (String) session.getAttribute("memberId");
			boardWriter = boardWriter != null ? boardWriter : "anonymous";
			board.setBoardWriter(boardWriter);
			int result = bService.RegistBoardInfo(board);
			if (result > 0) {
				rattr.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
//			alert 같은 역할의 성공시 뜨는 Flash RedirectAttritbute 선언 1회성 메시지
//			list.kh 로 보내는 메시지
				return "redirect:/board/list.kh";
			} else {
				model.addAttribute("msg", "등록 안됨");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}

	@RequestMapping(value = "/board/register.kh", method = RequestMethod.GET)
	public String InsertBoardInfo(Model model) {
		return "board/register";
	}

}