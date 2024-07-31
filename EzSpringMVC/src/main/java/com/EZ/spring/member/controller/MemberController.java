package com.EZ.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.EZ.spring.member.domain.MemberVO;
import com.EZ.spring.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;

	@RequestMapping(value = "/member/register.kh", method = RequestMethod.GET)
// method 가 다르면 Mapping 주소 같아도 가능
	public String showInsertForm(Model model) {
		return "member/insert";
	}
	// Servlet 처럼 Login, Delete ,Update,Logout etc... 따로 분리하지 않아도 됌
// 본래 안에 있던 String Id = request.getParameter("memberId") 같은 것들은 이제 메소드 안에서 작성
////그외에 이름 나이 성별 등등 포함 이제 insertMember(HttpServletRequest request)하지 않아도 되고
//	parameter로 받지 않아도 됌.
//	파일을 불러오거나 할때 Session 에서 받아오기 위해 킬 수도 있다.

// Controller 에서 메소드를 쓸때는 @RequestMapping , 메소드의 String , Model 이 필요하다
	@RequestMapping(value = "/member/register.kh", method = RequestMethod.POST)
	public String insertMember(

			@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw,
			@RequestParam("memberName") String memberName,
			@RequestParam(value = "memberAge", required = false, defaultValue = "0") int memberAge,
//			필수 값 외에 선택사항은 값을 입력 안할 수도 있는데 그게 인식이 안되기 때문에 value , 
//			required 작성 required는 필수값 아니라 해주는거
			@RequestParam(value = "memberGender", required = false) String memberGender,
			@RequestParam(value = "memberEmail", required = false) String memberEmail,
			@RequestParam(value = "memberPhone", required = false) String memberPhone,
			@RequestParam(value = "memberAddress", required = false) String memberAddress, Model model) {
//		String memberId = request.getParameter("memberId");
//		String memberPw=request.getParameter("memberPw");
		try {
			MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender, memberEmail,
					memberPhone, memberAddress);
			int result = mService.insertMember(member);
			if (result > 0) {
//			메인페이지 이동
				return "redirect:/index.jsp";
			} else {
//			에러 페이지 이동
				model.addAttribute("msg", "회원가입이 되지 않았습니다");
				return "common/errorPage";
//			Spring Context 에 /WEB-INF/views , .jsp 가 있음
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}

	@RequestMapping(value = "/member/login.kh", method = RequestMethod.POST)
	public String checkLogin(Model model, HttpSession session, @RequestParam("memberId") String memberId,
			@RequestParam("memberPw") String memberPw
//		RequestParam 에는 input type 의 name 값이 들어간다.
	) {
		try {
//			MemberVo member= new Member(memberId,memberPw)랑 같다.
			MemberVO member = new MemberVO();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member = mService.checkMemberLogin(member);
			if (member != null) {
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				return "redirect:/index.jsp";
				// 세션에 저장
			} else {
				model.addAttribute("msg", "로그인이 완료되지 않았다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";

		}
	}

	@RequestMapping(value = "/member/logout.kh", method = RequestMethod.GET)
	public String memberLogout(Model model, HttpSession session) {
		try {
			if (session != null) {
				session.invalidate();
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "로그아웃 실패");
				return "common/errorPage.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}

	@RequestMapping(value = "/member/mypage.kh", method = RequestMethod.GET)
	public String showMypage(Model model, HttpSession session) {
		try {
			String memberId = (String) session.getAttribute("memberId");
			// CheckLogin 의 setAttribute 값 가져오는것
//	로그인 성공 후 세션에 정보를 저장할 때 키값을 전달해줌

//	mypage.jsp 에 뿌릴 데이터를 비즈니스 로직을 통해서 가져와야함
			MemberVO member = mService.selectOneById(memberId);
			if (member != null) {
				model.addAttribute("member", member);
				return "/member/mypage";
			} else {
				model.addAttribute("msg", "정보가 존재하지 않습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}

	@RequestMapping(value = "/member/update.kh", method = RequestMethod.GET)
	public String showUpdateForm(Model model, HttpSession session) {
		try {
			String memberId = (String) session.getAttribute("memberId");
			MemberVO member = mService.selectOneById(memberId);
			if (member != null) {
				model.addAttribute("member", member);
				return "/member/update";
			} else {
				model.addAttribute("msg", "정보가 존재하지 않습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

	@RequestMapping(value = "/member/update.kh", method = RequestMethod.POST)
	public String updateMember(Model model, @RequestParam("memberId") String memberId,
			@RequestParam(value = "memberPw") String memberPw,
			@RequestParam(value = "memberAge", required = false, defaultValue = "0") int memberAge,
			@RequestParam(value = "memberGender", required = false) String memberGender,
			@RequestParam(value = "memberEmail", required = false) String memberEmail,
			@RequestParam(value = "memberPhone", required = false) String memberPhone,
			@RequestParam(value = "memberAddress", required = false) String memberAddress) {
		try {
			MemberVO modifyOne = new MemberVO(memberId, memberPw, memberAge, memberGender, memberEmail, memberPhone,
					memberAddress);
			int result = mService.updatemember(modifyOne);
			if (result > 0) {
//		response.sendRedirect("/member/mypage.kh?memberId="+memberId);
				return "redirect:/member/mypage.kh";
//		redirect-> Servlet 들렸다가 jsp로
			} else {
				model.addAttribute("msg", "정보가 존재하지 않습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}

	@RequestMapping(value = "/member/delete.kh", method = RequestMethod.GET)
	public String deleteMember(Model model, HttpSession session) {
		try {
			String memberId = (String) session.getAttribute("memberId");
			MemberVO member = mService.selectOneById(memberId);
			if (member != null) {
				int result = mService.deleteMember(memberId);
				if (result > 0) {
//				session.invalidate();
//				return "redirect:/";
					return "redirect:/member/logout.kh";
				} else {
					model.addAttribute("msg", "회원탈퇴가 되지 않았습니다");
					return "common/errorPage";
				}
			} else {
				model.addAttribute("msg", "회원탈퇴가 되지 않았습니다");
				return "common/errorPage";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}

	}
}
