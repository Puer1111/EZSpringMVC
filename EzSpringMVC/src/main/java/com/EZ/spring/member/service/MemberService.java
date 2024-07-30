package com.EZ.spring.member.service;


import com.EZ.spring.member.domain.MemberVO;

public interface MemberService {
/**
 * 회원 정보 등록
 * @param member
 * @return int
 */
	int insertMember(MemberVO member);
/**
 * 회원 정보 삭제
 * @return int
 */
	int deleteMember(String memberId);
/**
 * 회원 정보 수정
 * @param String
 * @return int
 */
	int updatemember(MemberVO member);
/**
 * 회원 로그인 서비스
 * @param member
 * @return member
 */
	//회원 로그인(checkMemberLogin)
	MemberVO checkMemberLogin(MemberVO member);
	
	/**
	 * 회원 아이디 검색
	 * @param memberId
	 * @return member
	 */
	// 회원 아이디 검색(selectOneById)
	MemberVO selectOneById(String memberId);
	
	
}
