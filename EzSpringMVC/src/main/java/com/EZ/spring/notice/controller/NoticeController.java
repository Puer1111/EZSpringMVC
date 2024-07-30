package com.EZ.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.EZ.spring.notice.Service.NoticeService;
import com.EZ.spring.notice.domain.NoticeVO;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService nService;

	@RequestMapping(value="/notice/search.kh", method=RequestMethod.POST)
	public String showSearchNotice(Model model
			,@RequestParam("searchCondition") String searchCondition
			,@RequestParam("searchKeyword") String searchKeyword) {
		try {
			Map<String,String> paramMap =new HashMap<String,String>();
			paramMap.put("searchCondition",searchCondition);
			paramMap.put("searchKeyword",searchKeyword);
			//VO 대신에 똑같이 객체 담을 수 있는 Map , Key-value 로 이루어짐
			List<NoticeVO>searchList=nService.searchNoticeByKeyword(paramMap);
			model.addAttribute("sList",searchList);
			model.addAttribute("searchKeyword",searchKeyword);
			model.addAttribute("searchCondition",searchCondition);
			return "notice/search";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";	
		}
		
	}
	
	
	
	@RequestMapping(value="/notice/update.kh",method=RequestMethod.POST)
	public String updateNotice(Model model
			,@ModelAttribute NoticeVO notice) {
//			,@RequestParam("noticeNo") int noticeNo
//			,@RequestParam("noticeSubject")String noticeSubject
//			,@RequestParam("noticeContent") String noticeContent) {
//		NoticeVO notice = new NoticeVO(noticeNo,noticeSubject,noticeContent);
	// 본래 RequestParam 으로 jsp 에서 불러와야하는 값들의 name 명이 필드명과 같으면 객체 생성
//		코딩 도 줄여서 한번에 modelAttribute 로 작성할 수 있따
		try {
			int result = nService.updateNotice(notice);
			if(result>0) {
				return "redirect:/notice/detail.kh?noticeNo="+notice.getNoticeNo();
			}else {
				model.addAttribute("msg","수정이 완료되지 않았습니다");
				return "common/errorPage";
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";		
			}
		
		
	}
	
	
	@RequestMapping(value="/notice/update.kh" ,method=RequestMethod.GET)
	public String showUPdateForm(Model model
			,@RequestParam("noticeNo")Integer noticeNo) {
		try {
			//Integer 여도 int 로 오토언박싱되어 들어간다.
			
			NoticeVO notice = nService.selectOneByNo(noticeNo);
			if(notice!= null) {
			model.addAttribute("notice",notice);
				return "notice/update";
			}else {
				model.addAttribute("msg","존재하지 않는 정보입니다");
				return "common/errorPage";
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/notice/delete.kh" , method = RequestMethod.GET)
	public String deleteNotice(Model model
			,@RequestParam("noticeNo")Integer noticeNo //Integer 를 쓰면 NULL 체크가 가능하다
			) {
		try {
			int result=nService.deleteNotice(noticeNo);
			if(result>0) {
				// 삭제 성공시 리스트로 이동
				return "redirect:/notice/list.kh";
			}else {
				model.addAttribute("msg","삭제가 완료되지 않았습니다");
				return "common/errorPage";
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "common/errorPage";
		}
	}
	
	
	@RequestMapping(value = "/notice/list.kh", method = RequestMethod.GET)
	public String showNoticeList(Model model
			,@RequestParam(value= "currentPage" ,defaultValue ="1") Integer currentPage
			,HttpServletRequest request) {
		try {
			List<NoticeVO> nList = nService.selectNoticeList(currentPage);
			
			int totalCount = nService.getTotalCount();
			int recordCountPerPage = 10;
//			한페이지에 보여줄 게시글 개수
			int naviTotalCount = 0;

			if (totalCount % recordCountPerPage > 0) {
				naviTotalCount = totalCount / recordCountPerPage + 1;
			} else {
				naviTotalCount = totalCount / recordCountPerPage;
			}
			int naviCountPerPage = 10;
			int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
			int endNavi = startNavi + naviCountPerPage - 1;
			if (endNavi > naviTotalCount) {
				endNavi = naviTotalCount;
			}
//			if (nList.size()> 0) {
				model.addAttribute("startNavi", startNavi);
				model.addAttribute("endNavi", endNavi);
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("naviTotalCount", naviTotalCount);
				model.addAttribute("nList",nList);
				return "notice/list";
//			}else {
//				model.addAttribute("msg","정보가 없슴다");
//				return "common/errorPage";
//			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}

	
	@RequestMapping(value="/notice/detail.kh", method=RequestMethod.GET)
	public String showNoticeDetail(Model model
			,@RequestParam("noticeNo")int noticeNo) {
		try {
			NoticeVO notice=nService.selectOneByNo(noticeNo);
			if(notice!=null) {
			model.addAttribute("notice",notice);
			return "notice/detail";
			}else {
			
				model.addAttribute("msg", "정보 없슴");
				return "common/errorPage";
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
		}
	
	
	@RequestMapping(value = "/notice/register.kh", method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "notice/register";
	}

	@RequestMapping(value = "/notice/register.kh", method = RequestMethod.POST)
	public String noticeRegister(Model model, HttpSession session, @RequestParam("noticeSubject") String noticeSubject,
			@RequestParam("noticeContent") String noticeContent) {
		try {
			String noticeWriter = (String) session.getAttribute("memberId");
			NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter);
			int result = nService.insertNotice(notice);
			if (result > 0) {
				return "redirect:/notice/list.kh";
			} else {
				model.addAttribute("msg", "에러");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

}
